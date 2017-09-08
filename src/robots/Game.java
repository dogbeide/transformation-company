package robots;

import java.util.*;

import robots.Transformer;

public class Game {
	
	public Transformer[] roster;
	public ArrayList<Transformer> unsortedRoster;
	
	int battleCount;
	String winningTeam, losingTeam;
	ArrayList<String> winners, survivors;
	
	public Game(){
		roster = new Transformer[4];
		battleCount = 0;
		unsortedRoster = new ArrayList<Transformer>();
		winners = new ArrayList<String>();
		survivors = new ArrayList<String>();
	}
		
	public void addTransformer(Transformer trans){
		unsortedRoster.add(trans);
	}
	
	// It's easier and more efficient to sort a regular array
	public void sortCompetitorsByRank(){
		roster = unsortedRoster.toArray(new Transformer[unsortedRoster.size()]);
		this.sortCompetitorsByRank(0, unsortedRoster.size()-1);
	}
	
	//Algo: "quick sort" Order(n*logn)
	public void sortCompetitorsByRank(int start, int end){
		if (start >= end) return; //base case, don't waste any CPU/memory
		
		int pivot = roster[end].getRank();
		int left = start;
		int right = start;
		Transformer temp;
		
		if (end - start == 1){
			if (roster[start].getRank() > roster[end].getRank()){
				temp = roster[left].clone();
				roster[left].copy(roster[right+1]);
				roster[right+1].copy(temp);
			}
			return;
		}
		
		for (;right <  end; right++){
			if (roster[right].getRank() < pivot){
				temp = roster[left].clone();
				roster[left].copy(roster[right]);
				roster[right].copy(temp);
				left++;
			}
		}
		temp = roster[left].clone();
		roster[left].copy(roster[right]);
		roster[right].copy(temp);
		
		sortCompetitorsByRank(start, (end-1)/2);
		if (end % 2 == 0)
			sortCompetitorsByRank(end/2, end);
		else
			sortCompetitorsByRank(end/2 + 1, end);
	}
	
	public void printSortedRoster(){
		for (Transformer transformer: roster)
			System.out.println(transformer.getName() + ": rank " + transformer.getRank());
	}
	
	public void startTest(){
		
	}
	
	// Get it going
	public void startGame(){
		if (unsortedRoster.isEmpty()){
			System.out.println("Nobody showed up, please let them know for next time.");
			return;
		}
		else if (unsortedRoster.size() == 1){
			System.out.println("Only 1 transformer showed up, he wins by default!");
			winners.add(unsortedRoster.get(0).getName());
			winningTeam = unsortedRoster.get(0).getTeam();
			return;
		}
		
		this.sortCompetitorsByRank();
		
		for (int i=1; i < roster.length; i+=2){
			
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
