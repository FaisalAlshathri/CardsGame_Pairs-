/*
 * 
 * two player game: pairs versus smart
 * 
 */
public class Play6 {
	public static void main(String[] args) {

		// deck of cards to play the game with
		Deck deck = new Deck();
		deck.shuffle(); // deck.printDeck();

		// judge to run the game
		Judge judy = new Judge();//Default max turn is 52
        
		
		Pairs p1 = new Pairs("Wolf");
		
		Smart p2 = new Smart("Monkey");

		

		java.util.ArrayList<Player> players = new java.util.ArrayList<Player>();
		players.add(p1);
		players.add(p2);

		// the pile in the game
		Pile pile = new Pile();

		// judge deals 8 cards to each layer and places remaining cards on the
		judy.startGame(deck, players, pile);

		System.out.println("**onecard versus smart");
		
		while (judy.keepPlaying()) {

			Player currentPlayer = judy.currentPlayer();
			currentPlayer.showHand();
			System.out.println();
			currentPlayer.play(pile, judy);

		}

		judy.displayResults();

		Player[] winners = judy.declareWinner();
		int i = 0;
		for (Player p : winners) {
			i++;
			if (i == 1)
				System.out.println("Player Rank: " + i + " " + p.getName()
						+ " is a winner!");
			else
				System.out.println("Player Rank: " + i + " " + p.getName());
		}

	}
}

