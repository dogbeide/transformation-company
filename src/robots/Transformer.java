package robots;

public class Transformer {
	// Enum for array indices
	public static int strength = 0,
					intelligence = 1,
					speed = 2,
					endurance = 3,
					rank = 4,
					courage = 5,
					firepower = 6,
					skill = 7;
	public int[] stats;
	private int overall;
	private char type;
	private String name;
	boolean fought;
	
	/* 
	 * Create a transformer
	 * */
	public Transformer(String name, char type, int[] stats){
		this.name = name;
		this.type = type;
		this.stats = new int[8];
		
		// Due diligence on valid stat values
		for (int element: stats){
			if (element < 1 || element > 10){
				System.out.println("Illegal stats, please enter values between 1 and 10 (inclusive)");
				System.exit(1);
			}
		}
		this.stats = stats;
		this.overall = stats[strength] +
						stats[intelligence] +
						stats[speed] +
						stats[endurance] +
						stats[firepower];
		this.fought = false;
	}
	
	public String getName(){
		return this.name;
	}
	
	public char getType(){
		return this.type;
	}
	
	public int getOverall(){
		return this.overall;
	}
	
	public int getRank(){
		return this.stats[rank];
	}
	
	public int[] getStats(){
		return this.stats;
	}
	
	/* 
	 * Get a stat of your choice
	 * */
	public int getStat(int index){
		// Due diligence, error checking out of bounds
		if (index < 0 || index > 7) {
			System.out.println("Error: stat being indexed doesn't exist (must be between 0-7).");
			System.exit(1);
		}
		return this.stats[index];
	}
	public Transformer clone(){
		return new Transformer(name, type, stats);
	}
	public void copy(Transformer toBeCopied){
		this.name = toBeCopied.getName();
		this.type = toBeCopied.getType();
		this.stats = toBeCopied.getStats();
		this.overall = toBeCopied.getOverall();
	}
	
}
