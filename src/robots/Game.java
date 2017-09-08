package robots;

import java.util.*;

import robots.Transformer;

public class Game {
	
	public Transformer[] roster; // The final sorted roster
	public ArrayList<Transformer> unsortedRoster; // The initial variable-size list
	
	int battleCount;
	String winningTeam, losingTeam;
	ArrayList<String> winners, survivors;
	
	/* 
	 * init the game
	 * */
	public Game(){
		roster = new Transformer[4];
		battleCount = 0;
		unsortedRoster = new ArrayList<Transformer>();
		winners = new ArrayList<String>();
		survivors = new ArrayList<String>();
	}
	
	/* 
	 * Insert into unsorted variable-size roster
	 * */
	public void addTransformer(Transformer trans){
		unsortedRoster.add(trans);
	}
	
	/* 
	 * It's easier and more efficient to sort a regular array
	 * */
	public void sortCompetitorsByRank(){
		roster = unsortedRoster.toArray(new Transformer[unsortedRoster.size()]);
		this.sortCompetitorsByRank(0, unsortedRoster.size()-1);
	}
	
	/* 
	 * It's easier and more efficient to sort a regular array.
	 * Algorithm: "quick sort" Order(n*logn)
	 * */
	public void sortCompetitorsByRank(int start, int end){
		if (start >= end) return; // Base case, don't waste any CPU/memory
		
		int pivot = roster[end].getRank();
		int left = start;
		int right = start;
		Transformer temp;
		
		// List of size 2
		if (end - start == 1){
			if (roster[start].getRank() > roster[end].getRank()){
				temp = roster[left].clone();
				roster[left].copy(roster[right+1]);
				roster[right+1].copy(temp);
			}
			return;
		}
		
		// Normal quicksort case
		for (;right <  end; right++){
			if (roster[right].getRank() < pivot){
				temp = roster[left].clone();
				roster[left].copy(roster[right]);
				roster[right].copy(temp);
				left++;
			}
		}
		// Swap the pivot
		temp = roster[left].clone();
		roster[left].copy(roster[right]);
		roster[right].copy(temp);
		
		//Partition list, recursively sort
		sortCompetitorsByRank(start, (end-1)/2);
		if (end % 2 == 0)
			sortCompetitorsByRank(end/2, end);
		else
			sortCompetitorsByRank(end/2 + 1, end);
	}
	
	/* 
	 * for test "API"
	 * */
	public void printSortedRoster(){
		for (Transformer transformer: roster)
			System.out.println(transformer.getName() + ": rank " + transformer.getRank());
	}
	
	// Get it going
	public void startGame(){
		// Empty roster
		if (unsortedRoster.isEmpty()){
			System.out.println("Nobody showed up, please let them know for next time.");
			endGame();
		}
		// Winner by default
		else if (unsortedRoster.size() == 1){
			System.out.println("Only 1 transformer showed up, he wins by default!");
			winners.add(unsortedRoster.get(0).getName());
			
			if (unsortedRoster.get(0).getType() == 'A')
				winningTeam = "Autobots";
			else
				winningTeam = "Decepticons";
			endGame();
		}
		
		// Prepare for battle
		this.sortCompetitorsByRank();
		int A=0, D=0;
		int i=0, j=1;
		
		// i = opponent1, j = opponent2, double-walk through list
		// Prevent double-fighting or friendly-fire
		// Incrementally create variables to save stack/CPU when not needed
		while (j < roster.length){
			String name1 = roster[i].getName();
			String name2 = roster[j].getName();
			
			// Main characters always win
			// Main characters always win against themselves
			if (name1 == "Optimus Prime"){
				if (name2 == name1 || name2 == "Predaking"){
					battleCount++;
					rageQuit();
				}
				else {
					winners.add("Optimus Prime");
					battleCount++;
					A++;
					i+=2;
					j+=2;
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
					D++;
					i+=2;
					j+=2;
				}
			}
			
			// Due diligence
			int type1 = roster[i].getType();
			int type2 = roster[j].getType();
			boolean fought1 = roster[i].fought;
			boolean fought2 = roster[j].fought;
			
			if (fought2 == true || type1 == type2){
				j++;
				continue;
			}
			if (fought1 == true){
				i++;
				continue;
			}
			
			// Normal battle
			int str1 = roster[i].getStat(Transformer.strength);
			int crg1 = roster[i].getStat(Transformer.courage);
			int skl1 = roster[i].getStat(Transformer.skill);
			int ovra1 = roster[i].getOverall();
			
			int str2 = roster[j].getStat(Transformer.strength);
			int crg2 = roster[j].getStat(Transformer.courage);
			int skl2 = roster[j].getStat(Transformer.skill);
			int ovra2 = roster[j].getOverall();
			
			// 1. Strength & Courage difference?
			if ((str1 >= str2 - 3) && (crg1 >= crg2 - 4)){
				winners.add(roster[i].getName());
				if (type1 == 'A') A++; else D++;
			}
			else if ((str1 <= str2 - 3) && (crg1 <= crg2 - 4)){
				winners.add(roster[j].getName());
				if (type2 == 'A') A++; else D++;
			}
			// 2. Skill difference?
			else if (skl1 > skl2){
				winners.add(roster[i].getName());
				if (type1 == 'A') A++; else D++;
			}
			else if (skl1 < skl2){
				winners.add(roster[j].getName());
				if (type2 == 'A') A++; else D++;
			}
			// 3. Overall rank difference?
			else if (ovra1 > ovra2){
				winners.add(roster[i].getName());
				if (type1 == 'A') A++; else D++;
			}
			else if (ovra1 < ovra2){
				winners.add(roster[j].getName());
				if (type2 == 'A') A++; else D++;
			}
			// else if (ovra1 == ovra2) add nothing;
			
			// Complete
			battleCount++;
			roster[i].fought = true;
			roster[j].fought = true;
			
			// Double-jump to next duo if adjacent (prevent overlap)
			if (j-i == 1){
				i+=2;
				j+=2;
			}
			// Single-step pair if far (no overlap)
			else {
				i++;
				j++;
			}
		}
		
		// Who had more victories?
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
		while (i < roster.length){
			if (roster[i].fought == false){
				if (roster[i].getType() == 'A' && winningTeam == "Decepticons" ||
					roster[i].getType() == 'D' && winningTeam == "Autobots")
					survivors.add(roster[i].getName());
				break;
			}
			i++;
		}
		endGame();
	}
	
	/*
	 * "When unstoppable forces meet an immovable objects"
	 * */
	public void rageQuit(){
		winningTeam = null;
		losingTeam = null;
		survivors.clear();
		winners.clear();
		
		endGame();
	}
	
	/*
	 * clean up (let JVM garbage-collector do the rest automatically)
	 */
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
