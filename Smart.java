/*
 * 
 * 
 * Smart Player: take one card from the pile , submitted all the matched cards in the turn 
 * and return the height score card in the pile
 * 
 * 
 */
public class Smart extends Player {
	private String PlayerType;
	public Smart(String name) {
		super(name);
		PlayerType = "smart";
		
	}
	
	// getter method of the player Type
	public String getPlayerType() {
		return PlayerType;
	}
	
	@Override
	public void play(Pile pile, Judge judge) {

		int count = 0;
		do {
			super.play(pile, judge);// call super method
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
					return;// end the method when user have submitted all the
							// cards
				}
			} else {// if player did'n find matched cards

				Card temp = getTheHighestCard();// get the highest card in the
												// hand
				if (temp == null || pile.size() == 0)
					return;

				int index = playerCards.indexOf(temp);
				playerCards.remove(index);

				System.out.println("Player: " + name + " return the card: "
						+ temp + " to Pile");
				pile.pile.push(temp);
				break;// only one time to return the cards to pile

			}
		} while (++count <= 3);// loop until the matched pair of cards submitted

		printHand();
		// //end game if the return card in the pile is the last card in the
		// player hand
		if (this.getNumberOfPlayerCard() == 0) {
			this.playerEndTheGame = true;
			judge.playerEnds(null, playerEndTheGame, pile);
		}

	}
	

}
