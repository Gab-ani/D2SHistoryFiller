package d2s.historyfiller.domain;

import java.util.ArrayList;
import java.util.List;

public class Match {

	private long matchId;
	 
	private String date;
	
	private String winner;
	
//	private String rCarry;
//	private String rMid;
//	private String rOfflane;
//	private String rSoft;
//	private String rHard;				
//	private String dCarry;
//	private String dMid;
//	private String dOfflane;
//	private String dSoft;
//	private String dHard;
	
	private ArrayList<String> teamRadiant;
	private ArrayList<String> teamDire;
	
	public Match() {
		
	}

	public long getMatchId() {
		return matchId;
	}

	public String getDate() {
		return date;
	}

	public String getWinner() {
		return winner;
	}

	public ArrayList<String> getTeamRadiant() {
		return teamRadiant;
	}

	public ArrayList<String> getTeamDire() {
		return teamDire;
	}

	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public void setTeamRadiant(ArrayList<String> teamRadiant) {
		this.teamRadiant = teamRadiant;
	}

	public void setTeamDire(ArrayList<String> teamDire) {
		this.teamDire = teamDire;
	}

	public static Match corrupted() {
		Match corrupted = new Match();
		corrupted.date = "-1";
		corrupted.matchId = -1;
		corrupted.winner = "none";
		return null;
	}

}
