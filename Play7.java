/*
 * 
 * four player game involving one of each type of player (fast, onecard, pairs and smart)
 * 
 */
public class Play7 {
	public static void main(String[] args) {

		// deck of cards to play the game with
		Deck deck = new Deck();
		deck.shuffle(); // deck.printDeck();

		// judge to run the game
		Judge judy = new Judge();//Default max turn is 52
        
		Fast p1 = new Fast("Cheetah");
		Onecard p2 = new Onecard("Dog");
		Pairs p3 = new Pairs("Wolf");
		Smart p4 = new Smart("Monkey");

		

		java.util.ArrayList<Player> players = new java.util.ArrayList<Player>();
		//add players
		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);

		// the pile in the game
		Pile pile = new Pile();

		// judge deals 8 cards to each layer and places remaining cards on the
		judy.startGame(deck, players, pile);

		System.out.println("**(fast, onecard, pairs and smart)");
		
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
			if (i == 1 || i == 2)//the highest two score is winner
				System.out.println("Player Rank: " + i + " " + p.getName()
						+ " is a winner!");
			else
				System.out.println("Player Rank: " + i + " " + p.getName());
		}

	}
}

