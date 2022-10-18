
/*
 * 
 * test the judge that pays attention
 * notice the total cheater score will be -1000
 * and there will be printed the numbers of cheating attempts
 * 
 */


// test the Judge that pays attention and cheataer player
public class TestJudgeThatPaysAttention {
	
	
	
	/*
	 * 
	 * This class to test the JudgeThatPaysAttention(); and cheater player
	 */

	public static void main(String[] args) {

		// deck of cards to play the game with
		Deck deck = new Deck();
		deck.shuffle(); // deck.printDeck();

		// judge to run the game
		JudgeThatPaysAttention judy = new JudgeThatPaysAttention();

		// if commend line argument length == 1
		if (args.length == 1) {
			try {
				int max = Integer.parseInt(args[0]);// get the max turn
				judy.setMaxTurn(max);// Change the default max turn

			} catch (Exception e) {
				System.out
						.println(e.getMessage()
								+ " :Wrong argument input , the dfault max turn will be use\n");
			}
		}

		// some players to play the game
		Player p1 = new Onecard("Dog");
		Player p2 = new Onecard("Cat");
		Cheater p3 = new Cheater("Fox");/////////////////////cheater
		Player p4 = new Fast("Tiger");

		java.util.ArrayList<Player> players = new java.util.ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		// the pile in the game
		Pile pile = new Pile();

		// judge deals 8 cards to each layer and places remaining cards on the
		// pile
		judy.startGame(deck, players, pile);// only once run

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

			if (i == 1 || i == 2) {// the height scores
				System.out.println("Player Rank: " + i + " " + p.getName()
						+ " is a winner!");
				continue;
			}
			if (p.getName().contains("Cheater"))
				// print in red to indicate the cheater
				System.err.println("Player Rank: " + i + " " + p.getName());
																			
			else
				System.out.println("Player Rank: " + i + " " + p.getName());
		}
		if (judy.getNumberOfCheateAttemps() >= 1)
			System.err.println("Number of attemps to Chaeate that the Judge stop : "+judy.getNumberOfCheateAttemps());

	}

}
