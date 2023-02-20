package d2s.historyfiller.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import d2s.historyfiller.SheduledMatchesRequester;

@SpringBootApplication  
@RestController  
public class HistoryFillerController {
	
	@Autowired
	SheduledMatchesRequester requester;
	@Autowired
	RestTemplate rest;
	
	@GetMapping("/")
	public String defaultAnswer() {
		return "hi, once an hour I ask www.dotaprotracker for recent pro matches then requesting them from www.stratz.com and then I send them to history DB service";
	}
	
	// manually does one extra update cycle
	@GetMapping("/update")
	public String update() throws InterruptedException {
//		System.out.println(requester.listOfStringsV2.get(0) + " " + requester.listOfStringsV2.get(1) + " " + requester.listOfStringsV2.get(2) + " " + requester.listOfStringsV2.get(requester.listOfStringsV2.size() -1));
//		requester.getRecentMatchesIDs();
		requester.updateUnparsed();
		return "";
	}
	
}
