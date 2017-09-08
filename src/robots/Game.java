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
	
	// Get it going
	public void startGame(){
		if (unsortedRoster.isEmpty()){
			System.out.println("Nobody showed up, please let them know for next time.");
			endGame();
		}
		else if (unsortedRoster.size() == 1){
			System.out.println("Only 1 transformer showed up, he wins by default!");
			winners.add(unsortedRoster.get(0).getName());
			
			if (unsortedRoster.get(0).getType() == 'A')
				winningTeam = "Autobots";
			else
				winningTeam = "Decepticons";
			endGame();
		}
		
		this.sortCompetitorsByRank();
		int A=0, D=0;
		
		for (int i=1; i < roster.length; i+=2){
			String name1 = roster[i-1].getName();
			String name2 = roster[i].getName();
			
			if (name1 == "Optimus Prime"){
				if (name2 == name1 || name2 == "Predaking"){
					battleCount++;
					rageQuit();
				}
				else {
					winners.add("Optimus Prime");
					battleCount++;
				}
			}
			else if (name1 == "Predaking"){
				if (name2 == name1 || name2 == "Optimus Prime"){
					battleCount++;
					rageQuit();
				}
				else {
					winners.add("Predaking");
					battleCount++;
				}
			}
			
			int str1 = roster[i-1].getStat(Transformer.strength);
			int crg1 = roster[i-1].getStat(Transformer.courage);
			int skl1 = roster[i-1].getStat(Transformer.skill);
			int ovra1 = roster[i-1].getOverall();
			
			int str2 = roster[i].getStat(Transformer.strength);
			int crg2 = roster[i].getStat(Transformer.courage);
			int skl2 = roster[i].getStat(Transformer.skill);
			int ovra2 = roster[i].getOverall();
			
			if ((str1 >= str2 - 3) && (crg1 >= crg2 - 4))
				winners.add(roster[i-1].getName());
			else if ((str1 <= str2 - 3) && (crg1 <= crg2 - 4))
				winners.add(roster[i].getName());
			else if (skl1 >= skl2)
				winners.add(roster[i-1].getName());
			else if (skl1 <= skl2)
				winners.add(roster[i].getName());
			else if (ovra1 > ovra2)
				winners.add(roster[i-1].getName());
			else if (ovra1 < ovra2)
				winners.add(roster[i].getName());
			// else if (ovra1 == ovra2) add nothing;
			
			battleCount++;
		}
				
		for (int j = 0; j < (roster.length/2)*2; j++){
			if (roster[j].getType() == 'A')
				A++;
			else
				D++;
		}
		
		if (A > D){
			winningTeam = "Autobots";
			losingTeam = "Decepticons";
		}
		else if (D > A){
			winningTeam = "Decepticons";
			losingTeam = "Autobots";
		}
		else {
			System.out.println("It's a DRAW!");
		}
		
		// Add the remaining loser to the survivors list
		if (roster.length % 2 == 1){
			if (roster[roster.length - 1].getType() == 'A' && winningTeam == "Decepticons" ||
				roster[roster.length - 1].getType() == 'D' && winningTeam == "Autobots")
				survivors.add(roster[roster.length - 1].getName());
		}
		
		endGame();
	}
	
	public void rageQuit(){
		winningTeam = null;
		losingTeam = null;
		survivors.clear();
		winners.clear();
		
		endGame();
	}
	
	public void endGame(){
		if (winningTeam == "Autobots")
			losingTeam = "Decepticons";
		else if (winningTeam == "Decepticons")
			losingTeam = "Autobots";
		
		System.out.println(this.battleCount + " battles");
		System.out.println("Winning team (" + this.winningTeam + "): " + this.winners);
		System.out.println("Survivors from the losing team (" + this.losingTeam + "): " + this.survivors);
	}
	
	
}
