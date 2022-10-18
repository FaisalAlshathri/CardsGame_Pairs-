/*
 * 
 * 
 * 
 * This is a helper class for the judge to calculate 
 * the result of the player , display statistic and store the cards
 * 
 */
import java.util.ArrayList;

public class PlayerResult {
	private String PlayerName;
	private int totalScore;
	private int bonesScore;//for any player that submitted more than the same pair
	private boolean cheat;
	private ArrayList<Card> playerCards; // remain cards from player
	
	//store the submitted Cards from player
	private ArrayList<Card> submitedCards; 
											

	public PlayerResult(String name) {

		PlayerName = name;//to keep track of each player
		submitedCards = new ArrayList<Card>();
		playerCards = new ArrayList<Card>();
		totalScore = 0;
		bonesScore = 0;
		cheat = false;

	}
	
	/*
	 * setter method
	 * add the submitted card
	 */
	public void addSubmittedCard(Card c){
		
		submitedCards.add(c);
		
		
	}
	/*
	 * getter method
	 * return the name
	 */
	public String getName(){
		
		return PlayerName;
	}
	
	public void setName(String name){
		
		PlayerName = name;
	}
	
	public int getNumberOfRemainPlayerCard(){ return playerCards.size(); }
	public int getNumberOfSubmitedPlayerCard(){ return submitedCards.size(); }	
	
	
	public void addPlayerCards(ArrayList<Card> players){
		
		playerCards.addAll(players);
		
	}
  public void addPlayerCards(Card players){
		
		playerCards.add(players);
		
	}
  /*
   * Calcuate the score of submitted cards
   */
	public int getSubmitedCardsScore() {
		int score = 0;
		int countExtraAcePire = 0;// counter for bones score
		int countExtraJackPire = 0;
		int countExtraNumberPire = 0;
		for (Card in : submitedCards) {

			if (in.getRank() == 1){
				score += 30;//each single card of Ace
				countExtraAcePire++;
				if(countExtraAcePire % 4 == 0)//countExtraPire == 4 for one deck
				   bonesScore += 30;
				   
			//jack queen and king
			}else if (in.getRank() > 10){
					score += 20;
					countExtraJackPire++;
					if(countExtraJackPire % 4 == 0 )
						   bonesScore += 20;
			}else{
				score += 10;
				countExtraNumberPire++;
				if(countExtraNumberPire % 4 == 0)
					   bonesScore += 10;
				
			}
		}
		
		//dived by 2 to make the score for each pair of cards 
		//and the bones score of matched pairs
		return (score/2)+bonesScore;
	}
	
	/*
	 * 
	 * getter for bones score
	 */
	public int getBonesScore(){
		getSubmitedCardsScore();//Calculate the bones Score
		return bonesScore;} 
    
	/*
	 * 
	 * Calculate the remain card in the player hand when the game end
	 */
	public int getRemainCardsScore() {
		int score = 0;
		int rank;
		for (Card in : playerCards) {
			rank = in.getRank();
			if (rank == 1)// ace
				score -= 20;
			else if (rank >= 2 && rank <= 10)
				score -= rank;
			else
				// if(rank>10)
				score -= 15;
		}

		return score;
	}
	
    
	/*
	 * Calculate the total player score
	 */
	public int calculatePlayerScore() {

		return getSubmitedCardsScore() + getRemainCardsScore();
	}

	public void setTotalScore() {

		totalScore = calculatePlayerScore();
	}
    
	/*
	 * setter method when the judge find out cheating
	 */
	public void setCheat(boolean cheat){
		
		this.cheat = cheat;
	}
	
	/*
	 * 
	 * get the total result
	 * if player found to be cheater give him a penalty -1000 score
	 * 
	 */
	public int getTotalScore() {
		if(!cheat)
		  setTotalScore();//set the total score
		else if(cheat)
			totalScore = -1000;//give the cheater -1000 points
		return totalScore;//totalScore;
	}
    
	//print submitted cards of player
	public void printSubmittedCards(){
		System.out.println(this);//use toString()
		System.out.println("Submitted Cards Score: "+getSubmitedCardsScore());
		System.out.println("Remain Cards Score: "+getRemainCardsScore());
		System.out.println("Bones Score: "+getBonesScore());
		
	}
	/*
	 * setter for bones Score
	 */
	public void setBonesScore(int bonesScore){
		this.bonesScore = bonesScore;
	}

	@Override
	public String toString() {
		String submitedCard ="";
		String remainCard ="";
		for(Card in: submitedCards )
			submitedCard +=in+" ";
		for(Card in: playerCards )
			remainCard +=in+" ";
		
		
		return "Submitted Cards: "+submitedCard+"\nRemain Cards in Hand: "+remainCard;
	}
}
