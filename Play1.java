/*
 * 
 * two player game: fast versus onecard
 * 
 */
public class Play1 {
	public static void main(String[] args) {

		// deck of cards to play the game with
		Deck deck = new Deck();
		deck.shuffle(); // deck.printDeck();

		// judge to run the game
		Judge judy = new Judge();

		Fast p1 = new Fast("Cheetah");

		Onecard p2 = new Onecard("Dog");

		java.util.ArrayList<Player> players = new java.util.ArrayList<Player>();
		players.add(p1);
		players.add(p2);

		// the pile in the game
		Pile pile = new Pile();

		// judge deals 8 cards to each layer and places remaining cards on the
		judy.startGame(deck, players, pile);

		System.out.println("**fast versus onecard**");

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
