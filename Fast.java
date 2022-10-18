/*
 *  
 *Fast Player: This player will start their turn by taking a card and
 *then immediately placing a card on the pile (by giving it to the judge) ending their
 *turn. They never take a second card and they never submit pairs in a turn. They
 *always place a card on the pile that will minimize their immediate score.
 *
 */
public class Fast extends Player {
	
	private String PlayerType;
	public Fast(String name){
	   
		super(name);
		PlayerType = "fast";
		
	}
	
	//getter method of the player Type
		public String getPlayerType(){
			return PlayerType;
		}
	@Override
	public void play(Pile pile, Judge judge) {

		Card temp = pile.remove();//get a card from pile

		judge.playerEnds(temp, false, pile);// in player end if temp == null
											// (end the game)
		if (temp == null)
			return;

		System.out.println("Player: " + name + " Took from Pile: " + temp);

		playerCards.add(temp);//add the card 
		printHand();

		temp = getTheHighestCard();//get the heighest card in the hand
		if (temp == null)
			return;
		
		int index = playerCards.indexOf(temp);
		playerCards.remove(index);

		System.out.println("Player: " + name + " return the card: "+temp+" to Pile");
		pile.pile.push(temp);
		printHand();

	}

}
