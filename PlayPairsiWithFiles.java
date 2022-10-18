/*
 * 
 * write the result to a file
 * 
 */
import java.io.*;
public class PlayPairsiWithFiles {
	
	public static void main(String[] args) {

		// deck of cards to play the game with
		Deck deck = new Deck();
		deck.shuffle(); // deck.printDeck();
		

		// judge to run the game
		Judge judy = new Judge();

		

		// some players to play the game
		Onecard p1 = new Onecard("Dog");
		Smart p2 = new Smart("Moneky");
		Fast p3 = new Fast("Cheetah");
		Fast p4 = new Fast("Tiger");

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
			if (i == 1)// the height score
				System.out.println("Player Rank: " + i + " " + p.getName()
						+ " is a winner!");
			else
				System.out.println("Player Rank: " + i + " " + p.getName());
		}

		if (args.length == 1) {
			BufferedWriter bw = null;
			try {
				// String fileName = (args[0]);
				bw = new BufferedWriter(new FileWriter(args[0],true));
				bw.write("Number of Played Turns: " + judy.getNumberOfTurn());
				bw.newLine();
				i=0;
				for (Player p : winners) {
					i++;
					if (i == 1){// the height score
						bw.write("Player Rank: " + i + " " + p.getName()
								+ " is a winner!");
						bw.newLine();
									
					}else{
						bw.write("Player Rank: " + i + " " + p.getName());
						bw.newLine();
					}
				}

			} catch (IOException f) {
				System.out.print(f.getStackTrace());
			} catch (Exception e) {
				System.out
						.println(e.getMessage() + " :Wrong argument input \n");
			} finally {
				try {
					bw.close();
				} catch (IOException ex) {

				}
			}
		}

	}

}
