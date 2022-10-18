/*

a pairs player. This player will try to submit a pair to the judge in
every turn if possible. They will take as many cards from the pile as they need until
they have a pair to submit (or the pile is empty). The goal of this player is to try
and maximize their score by having lots of pairs. When they place a card on the pile
(giving it to the judge), they give a card that will give them the least amount of points
if it was part of a pair.

*/

public class Pairs extends Player {

	private String PlayerType;
	
	Pairs(String name){
		    
		super(name);
		PlayerType = "pairs";
		
	}
	
	
	//getter method of the player Type
	public String getPlayerType(){
		return PlayerType;
	}
	
	
	@Override
	public void play(Pile pile, Judge judge) {

		boolean foundPair = false;
		int count = 0;
		do {
			super.play(pile, judge);// call super method

			// submit the matched cards
			if (super.getMatchedCards()) {
				playerCards.remove(matched[0]);
				playerCards.remove(matched[1]);
				System.out.println("Player: " + name + " submit the card: "
						+ matched[0] + " and " + matched[1]);
				judge.takePair(matched[0], matched[1], this);
				printHand();

				if (this.getNumberOfPlayerCard() == 0) {
					this.playerEndTheGame = true;// end game if the player
													// submitted all the cards
					judge.playerEnds(null, playerEndTheGame, pile);
					return;
				}
				foundPair = true;
			}
			// take as many as possible cards until pair found and submitted
		} while (++count <= 3 && !foundPair);

		// return the to the pile highest card score in the player hand
		Card temp = getTheLowestCard();// get the highest card in the

		if (temp == null || pile.size() == 0)
			return;

		int index = playerCards.indexOf(temp);
		playerCards.remove(index);

		System.out.println("Player: " + name + " return the card: " + temp
				+ " to Pile");
		pile.pile.push(temp);
		printHand();
		// //end game if the return card in the pile is the last card in the
		// player hand
		if (this.getNumberOfPlayerCard() == 0) {
			this.playerEndTheGame = true;
			judge.playerEnds(null, playerEndTheGame, pile);
		}

	}
	
	
	
}
