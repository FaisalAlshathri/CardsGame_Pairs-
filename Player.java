import java.util.ArrayList;
public class Player{
  
  protected String name;    // each player must have a name:
  
  protected ArrayList<Card> playerCards;
  
  //if a player have submitted all cards ,Player inform the juge to game end 
  protected boolean playerEndTheGame;
  protected Card [] matched;
 
  
  public Player(String name){
    this.name = name;
    playerCards = new ArrayList<Card>();
    playerEndTheGame = false;
    matched = new Card[2];//for pair of card
    
    
  }
  public String getName(){ return name; }
  
  
  public int getNumberOfPlayerCard(){
	  
	  
	  return playerCards.size(); 
  }
  
  
  public void addCard(Card c){
    // adds the card to the players hand
	if(c==null){
		System.out.println("Can not add the card to player hand");
	   return ;
	}
    playerCards.add(c);
  }
  
  // return arrayList containing all the cards in your hand
  public java.util.ArrayList<Card> showHand(){
   
    return playerCards;
  }
  
  
  public void printHand(){
	  
	  // display the player's hand to standard output
	    System.out.print("Player: "+ name+ " Hand: ");
		for(Card in : playerCards)
	       System.out.print(in+" ");
		System.out.println();
	  
  }
  
  public boolean CheckHand(ArrayList<Card> playerCard){
	  
	  
	  for(int i = 0; i<playerCard.size(); ++i){
	     for(int j = 1; j<playerCard.size(); ++j)
		  if(playerCard.get(j).getRank() == playerCard.get(i).getRank() )
	    	 return true;
	  }
	  return false;
  }
  
  
 /*
  * get matched pair of card if player has
  * otherwise return false
  * 
  */
	public  boolean getMatchedCards(){
		
		for(int i=0; i<playerCards.size(); ++i)
			for(int j=i+1; j<playerCards.size(); ++j)
				if(playerCards.get(i).getRank() == playerCards.get(j).getRank()){
					matched[0] = playerCards.get(i);
					matched[1] = playerCards.get(j);
					return true;
				}
		
		return false;
		
	}
  
  public boolean removeCard(Card c1 , Card c2){
    // remove the pair cards from the players hand
    /*if(c1==null || c2==null)
    	return false;*/
    
    return playerCards.remove(c1) && playerCards.remove(c2);//return true if the cards exit
  }
  
  public void play(Pile pile, Judge judge){
    // play a turn
    //
    // take a card from the pile
    //    - take more cards from the pile if needed
    //    - submit pairs to judge if applicable
    // give zero or more cards to the judge
	  Card temp = null;
	 
	if(getNumberOfPlayerCard() == 0){
		playerEndTheGame = true;
		judge.playerEnds(temp , playerEndTheGame , pile);
		return ;
	}
	 temp = pile.remove();//get card from pile
	judge.playerEnds(temp , playerEndTheGame , pile);//check if there is no more cards , end the game
	if(temp == null)	
		return;//end the method if there is no cards
	
	System.out.println("Player: "+name+" Took from Pile: "+temp);
	playerCards.add( temp );
	printHand();
    
  }
  
  /*
   * return the highest card point in player hand	
   */
  public Card getTheHighestCard() {
		if (playerCards.size() < 1)
			return null;
		
		Card temp = null;
		
		for (Card in : playerCards) {//loop to find highest card from Ace , Jacks and queen
			if (in.getRank() == 1) {
				temp = in;
				return temp;//if we find ace (highest card) 
			} else if (in.getRank() >= 10)
				temp = in;
		}
		// if temp still null (no big cards in the player hand)
		if (temp == null) {
			int max = 0;
			for (Card in : playerCards) {//loop to find the biggest card between 9 and 2
				if (max < in.getRank()){
					max = in.getRank();
					temp = in;
				}
				if (max == 9)
					break;
			}
		}

		return temp;
	}
  /*
   * 
   * return the lowest card points in the player hand
   */
  public Card getTheLowestCard() {
	    //if player doesn't have card
		if (playerCards.size() < 1)
			return null;
		
		Card temp = null;
		int min = 100;
		for (Card in : playerCards) {//loop to find smallest card from 2 to 13
			if (in.getRank() < min && min>1) {
				 min = in.getRank();
				 temp = in;
			
		}
		// if temp still null or min=100 (no small cards in the player hand)
		// this in the case all the player hand are Ace	
		if (temp == null ||min == 100) {
		     
			temp = playerCards.get(0);//get the any card (first card in the arraylist)
			
			}
		}

		return temp;
	}
  
  
  
}
