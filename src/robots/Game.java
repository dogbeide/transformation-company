package robots;

import java.util.*;

import robots.Transformer;

public class Game {
	
	public ArrayList<Transformer> competitors;
	
	int battleCount;
	String winningTeam, losingTeam;
	ArrayList<String> winners, survivors;
	
	public Game(){
		battleCount = 0;
	}
		
	
	public void addTransformer(Transformer trans){
		competitors.add(trans);
	}
	// private so no one can sort an empty list by accident
	private void sortCompetitorsByRank(){
		
	}
	
	public void startGame(){
		if (competitors.isEmpty()){
			System.out.println("Nobody showed up, please let them know for next time.");
			return;
		}
		else if (competitors.size() == 1){
			System.out.println("Only 1 transformer showed up, he wins by default!");
			winners.add(competitors.get(0).getName());
			winningTeam = competitors.get(0).getTeam();
			return;
		}
		
		this.sortCompetitorsByRank();
		
		for (int i = 1; i < competitors.size(); i+=2){
			
		}
	}
	
	public void endGame(){
		if (winningTeam == "Autobots")
			losingTeam = "Decepticons";
		else 
			losingTeam = "Autobots";
		
		System.out.println(this.battleCount + " battles");
		System.out.println("Winning team (" + this.winningTeam + "): " + this.winners);
		System.out.println("Survivors from the losing team (" + this.losingTeam + "): " + this.survivors);
	}
	
	
}
