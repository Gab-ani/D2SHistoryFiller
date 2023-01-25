package d2s.historyfiller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import d2s.historyfiller.domain.Match;

@Service
public class SheduledMatchesRequester {
	
	@Autowired
	RestTemplate rest;

	/*
	Firstly, when we ask www.dota2protracker.com to send us last public matches played by professional players.
	www.dota2protracker.com always sends about 60-70 ids, ands they are just "last played". Ie we can ask for IDs
	at n-hour, then ask again at n+1-hour and if pro players played about 40 matches in this time,
	we'll have two id-lists, and first 40 ids from the second list will already be known as they are last 40 ids from the first list.
	
	Secondly, stratz.com takes some time to parse a match, usualy around 5 minutes, but in some cases it can be done in 3 days,
	and in some cases the match cant be parsed at all, and stratz.com stops trying to request it from dota2 servers.
	
	To work around these two points, we are doing the next operations:
		1) Periodically we ask dota2protracker.com for recent IDs and send them to history-db-service
		2) History-db-service ads only the unknown IDs from the list to db with "unparsed" status
		
		3) Periodically we ask history-db-service for the list of still unparsed matches.
			Note that we get all the matches where parsed = false, so more IDs than we've sent at the 1) stage.
		4) When we get the list of unparsed matches, we request the data about them stratz.com, pack thist data into List<Match>,
			and send this list to history-db-service to update unparsed matches with their parsed variant (id stays the same so it overwrites automatically).
	
	 */
	
	@Scheduled(cron = "* 0 * * * *")	// TODO write cron to .properies to be able to change the period more dynamically
	public void getRecentMatchesIDs() throws InterruptedException {
		
		ArrayList<Long> freshIDs = rest.getForObject("http://d2sprotracker/parse", ArrayList.class);
		System.out.println(freshIDs.size() + " matches");

		rest.postForObject("http://d2shistory/notice", freshIDs, ArrayList.class);
		
	}
	
	@Scheduled(cron = "*/5 */1 * * *")	// TODO write cron to .properies to be able to change the period more dynamically
	public void updateUnparsed() throws InterruptedException {
		ArrayList<Long> unparsedIDs = rest.getForObject("http://d2shistory/unparsed", ArrayList.class);
		
		ArrayList<Match> parsedMatches = new ArrayList<Match>();
		for(long id : unparsedIDs) {
			parsedMatches.add(rest.getForObject("http://d2stratzparser/" + id, Match.class));
		}
	}
	
}
