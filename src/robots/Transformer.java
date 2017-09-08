package robots;

public class Transformer {
	
	public enum Stat { // Stats array indices
		strength,
		intelligence,
		speed,
		endurance,
		rank,
		courage,
		firepower,
		skill
	}
	private int[] stats;
	private int overall;
	private char type;
	private String name;
	
	/* Create a transformer */
	public Transformer(String name, char type, int[] stats){
		this.name = name;
		this.type = type;
		this.stats = stats;
	}
}
