/*
 * 
 * this judge pays attention for cards
 * if player try to submit un-matched pair of cards
 *  the judge will not accept un-matched cards from player
 * and the cheater will be assign to -1000 score and will be called cheater
 * 
 * 
 */
public class JudgeThatPaysAttention extends Judge {

	boolean cheater ;
	private int NumberOfCheateAttemps;//count # of cheat attempt in the game
	
	public JudgeThatPaysAttention(){
		super();
		NumberOfCheateAttemps = 0;
	}
	@Override
	public void takePair(Card c1, Card c2, Player p) {
		// take a pair of cards from player p
		try {
			if (c1.getRank() == c2.getRank()) {
				// submit the pair of cards
				p.removeCard(c1, c2);
				
				playersResult[PositionOfPlayer].addSubmittedCard(c1);
				playersResult[PositionOfPlayer].addSubmittedCard(c2);
				System.out.println("Player: " +p.getName() + " submit the card: "
						+ c1 + " and " + c2);

			} else {
				
				throw new CheaterException("CheaterException: attempted to submit Unmatched cards");
			}
		} catch (CheaterException e) {
			
			//e.printStackTrace();
			System.err.println("Player: " +p.getName() + " attemp to submit the card: "
					+ c1 + " and " + c2);
			 System.err.println(e.getMessage());
			 System.err.println("Cheater: "+playersResult[PositionOfPlayer].getName() );
			 playersResult[PositionOfPlayer].setCheat(true);//cheater
			 NumberOfCheateAttemps++;
			 
			 if(!cheater){
				 cheater = true;
				 String nickName = playersResult[PositionOfPlayer].getName();
				 playersResult[PositionOfPlayer].setName("Cheater: "+nickName);//call the player cheater before his name
				 //players.remove(PositionOfPlayer);
		     }
		}
		

		return;
	}
	
	public int getNumberOfCheateAttemps(){
		return NumberOfCheateAttemps ;
	}
	
	public class CheaterException extends Exception {
		
		public CheaterException(String name){
			   super(name);
		}
		
	}
}
