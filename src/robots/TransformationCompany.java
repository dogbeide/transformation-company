package robots;

import java.util.*;
import robots.Transformer;

public class TransformationCompany {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Game game = new Game();
		
		
		
		
		
		System.out.println(game.battleCount + " battle");
		System.out.println("Winning team (" + game.winningTeam + "): " + game.winners);
		System.out.println("Survivors from the losing team (" + game.losingTeam + "): " + game.survivors);
	}

}
