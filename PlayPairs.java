public class PlayPairs{
	
	public static void main(String[] args) {

		// deck of cards to play the game with
		Deck deck = new Deck();
		deck.shuffle(); // deck.printDeck();

		// judge to run the game
		Judge judy = new Judge();

		// if commend line argument length == 1
		if (args.length == 1) {
			try {
				int max = Integer.parseInt(args[0]);//get the max turn
				judy.setMaxTurn(max);// Change the default max turn

			} catch (Exception e) {
                    System.out.println(e.getMessage()+" :Wrong argument input , the dfault max turn will be use\n");
			}
		}

		// some players to play the game
		Player p1 = new Onecard("Dog");
		Player p2 = new Onecard("Cat");
		Player p3 = new Fast("Cheetah");
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
			if (i == 1|| i == 2)//the highest two score is winner
				System.out.println("Player Rank: " + i + " " + p.getName()
						+ " is a winner!");
			else
				System.out.println("Player Rank: " + i + " " + p.getName());
		}

	}
}