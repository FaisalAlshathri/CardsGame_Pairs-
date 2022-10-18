/*
 *  onecard player. This player submits pairs when they have them,
 * but never takes a second card from the pile in a given turn. This player always places
 *a card on the pile, by giving it to the judge (after submitting pairs) so that their
 *immediate score is minimized.
 * 
 */
public class Onecard extends Player {

	private String PlayerType;
	
	public Onecard(String name){
		
		super(name);
		PlayerType = "onecard";
		
	}
	
	//getter method of the player Type
	public String getPlayerType(){
		return PlayerType;
	}
	
	
	
	@Override
	public void play(Pile pile, Judge judge){
		
		super.play(pile, judge);//call super method
	
		
		if(super.getMatchedCards() ){
			playerCards.remove(matched[0]);
			playerCards.remove(matched[1]);
			System.out.println("Player: "+name+" submit the card: "+matched[0]+" and " + matched[1]);
			judge.takePair(matched[0] , matched[1] , this);
			printHand();
			if(this.getNumberOfPlayerCard() == 0){
				this.playerEndTheGame = true;//end game if the player submitted all the cards
				judge.playerEnds(null, playerEndTheGame, pile);
				return ;
			}
		}
		
		Card temp = getTheHighestCard();//get the heighest card in the hand
		if (temp == null || pile.size()==0)
			return;
		
		
		int index = playerCards.indexOf(temp);
		playerCards.remove(index);

		System.out.println("Player: " + name + " return the card: "+temp+" to Pile");
		pile.pile.push(temp);
		printHand();
		////end game if the return card in the pile is the last card in the player hand 
		if(this.getNumberOfPlayerCard() == 0){
			this.playerEndTheGame = true;
			judge.playerEnds(null, playerEndTheGame, pile);
		}
		
	}
	

	
	
}
