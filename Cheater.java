
public class Cheater extends Player {

	public Cheater(String name) {
		super(name);
		
	}
	
	/*
	 * 
	 * Method  of cheating to submit wrong cards
	 */
	public boolean cheat(){
		
		int pick_time = (int) (Math.random()*100);
		if(pick_time>=50)//decide randomly when player to cheat
    		return true;
		
		return false;
	}
	
	

	@Override
	public void play(Pile pile, Judge judge) {
		

		int count = 0;
		do {
			super.play(pile, judge);// call super method
			if (super.getMatchedCards()) {//get real matched card
		
				judge.takePair(matched[0], matched[1], this);
				printHand();
				if (this.getNumberOfPlayerCard() == 0) {
					this.playerEndTheGame = true;// end game if the player
													// submitted all the cards
					judge.playerEnds(null, playerEndTheGame, pile);
					return;// end the method when user have submitted all the
							// cards
					
				/// if player decide to Cheat
				} else if (cheat()) {

					int card1 = (int) (Math.random() * (this.getNumberOfPlayerCard() - 1));
					int i = 0;
					int card2;
					do {
						card2 = (int) (Math.random() * (this.getNumberOfPlayerCard() - 1));
						if (card1 != card2)
							break;
					} while (++i < 100);
					Card c1 = playerCards.get(card1);
					Card c2 = playerCards.get(card2);
					
					judge.takePair(c1, c2, this);
					
					printHand();

				} else {

					Card temp = getTheHighestCard();// get the highest card in
													// the
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
			}
		} while (++count <= 3);

		printHand();

	}

}
