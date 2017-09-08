package robots;

public class TransformationCompany {
	static Game game;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		game = new Game(); //Contains everything
		
		Transformer soundwave = new Transformer("Soundwave", 'D', new int[] {8,9,2,6,7,5,6,10});
		Transformer bluestreak = new Transformer("Bluestreak", 'A', new int[] {6,6,7,9,5,2,9,7});
		Transformer hubcap = new Transformer("Hubcap", 'A', new int[] {4,4,4,4,4,4,4,4});
		
		game.addTransformer(soundwave);
		game.addTransformer(bluestreak);
		game.addTransformer(hubcap);
		
		game.startGame(); // Run the whole program
	}
	
	/* 
	 * (DEPRECATED)
	 * Verify functionality of the transformer sorting algorithm
	 * */
	public static void quickSortTest(){
		Transformer soundwave = new Transformer("Soundwave", 'D', new int[] {8,9,2,6,7,5,6,10});
		Transformer bluestreak = new Transformer("Bluestreak", 'A', new int[] {6,6,7,9,5,2,9,7});
		Transformer hubcap = new Transformer("Hubcap", 'A', new int[] {4,4,4,4,4,4,4,4});
		Transformer abominus = new Transformer("Abominus", 'D', new int[] {10,1,3,10,5,10,8,4});
		
		game.addTransformer(soundwave);
		game.addTransformer(bluestreak);
		game.addTransformer(hubcap);
		game.addTransformer(abominus);
		
		game.sortCompetitorsByRank();
		game.printSortedRoster();
	}

}