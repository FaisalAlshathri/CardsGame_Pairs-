import java.util.ArrayList;
public class Judge{
  public static final int NUM_CARDS = 8;
  public static final int DefaultMaxTurns = 52;
  private int maxTurn;
  protected ArrayList<Player> players;
  
  
  protected boolean keepPlaying = true;
  private int NumberOfPlayers;
  
  private int NumberOfTurn;
  
  protected int PositionOfPlayer;
  protected PlayerResult [] playersResult;
  
  
  
 
  
  
  public Judge(){
    
    players = new ArrayList<Player>();
    PositionOfPlayer = -1;
    NumberOfTurn = -1;
    maxTurn = DefaultMaxTurns;
  }
  
  public Judge(int turn){
	    
	    this();
	    if(turn >= 4)
	      maxTurn = turn;
	    else
	      maxTurn = DefaultMaxTurns;	
	  }
  
  public boolean keepPlaying(){
    // return true if the current game has not ended
    // return false if the current game has ended
	  if(++NumberOfTurn >= maxTurn ){
		  System.out.println("\nthe Game reach the end of turns: "+NumberOfTurn);
		  keepPlaying = false;
		  
	  }
    return keepPlaying;
  }
  
  /*
   * 
   * set the max the turn 4 and greater
   */
  public void setMaxTurn(int max){
	  if(max>=4)
	    maxTurn = max;
	  
  }
  
  public void startGame(Deck deck, java.util.ArrayList<Player> players, Pile pile){
    //
    // add your own code here, but do not change the way that the cards
    // are dealt out to the players
    //
    this.players.addAll(players);//copy the arrayList of players
    NumberOfPlayers = players.size();
   
   
    
    playersResult = new PlayerResult[NumberOfPlayers] ;
    
    for(int index=0; index<playersResult.length; ++index)
        playersResult[index] = new PlayerResult (players.get(index).getName() );//add the players name*/
    
    // starts game by dealing out NUM_CARDS = 8 cards from the deck to each player
    // and placing the remaining cards in the pile
    int numPlayers = players.size();
    int c = 0;
    for(int i=0; i<NUM_CARDS; i+=1){
      for(int p=0; p<numPlayers; p+=1){
        players.get(p).addCard(deck.getCard(c));
        c += 1;
      }
    }
   
    while( c < deck.numCards() ){
      pile.add( deck.getCard(c) );
      c+=1;
    }
    
    
    
    
  }
  
  //get each pair of cards from player and store them at judge
  public void takePair(Card c1, Card c2, Player p){
    // take a pair of cards from player p
    if(c1.getRank()==c2.getRank() ){       
    	//submit the pair of cards
    	playersResult[PositionOfPlayer].addSubmittedCard(c1);
    	playersResult[PositionOfPlayer].addSubmittedCard(c2);
    	
    }

      return ; 
  }
  
	public void playerEnds(Card c, boolean end, Pile pile) {
		// a player ends their turn by calling this method
		// and telling the judge what to do
		//
		// there are three possibilities
		// c == null
		// player has no more cards left and wants to end the game
		// c != null, end == true
		//
		// pile the current pile in the game
		//
		// judge should determine if the game has ended or not

		// check if player has no more cards
		if (end) {// && checkPlayerHand(players.get(PositionOfPlayer))
			System.out.println("Player: "
					+ players.get(PositionOfPlayer).getName()
					+ " has submited all cards to judge");
			keepPlaying = false;
			keepPlaying();
			return ;
		}
		if (pile.size() + 1 < 1 || c == null) {
			// end the game if there is no card in the pile
			keepPlaying = false;
			System.out.println("\nThere is no more Card in the pile");
			keepPlaying();
			return;
		}

	}
  
  /*
   * This method for judge to check(to confirm ) 
   * if player has no more cards in his hands
   * return true to end the game
   * otherwise return false
   * 
   */
  public boolean checkPlayerHand(Player p){
	  
	  if(p.getNumberOfPlayerCard()<1)
		  return true;
	  return false;
	  
  }
  public void displayResults(){
    // print summary of game to standard output
	  
	
	  for(int i = 0; i<playersResult.length; ++i)
	      playersResult[i].addPlayerCards(players.get(i).playerCards); //getRemainCardsScore
	  
	  System.out.println("\n**Game Resultes**\n");
	  System.out.println("Number of played Turns: "+getNumberOfTurn()+"\n");
	  for(PlayerResult p : playersResult){
		  
		  System.out.println("Player Name: "+p.getName());
		  System.out.println("Number Of Remain cards in player Hand: "+p.getNumberOfRemainPlayerCard());
		  System.out.println("Number Of Submited cards : "+p.getNumberOfSubmitedPlayerCard());
		  p.printSubmittedCards();
		  System.out.println("Total Score: "+p.getTotalScore());
		  System.out.println();
	  }
	  
  }
  public int getNumberOfTurn(){ return NumberOfTurn;}
  
  
	public Player[] declareWinner() {
		// returns the player (or players) that win the game
		// all players with the highest score are returned
		Player[] winners = new Player[playersResult.length];

		PlayerResult temp;
		//sort the player result in order
		for (int i = 0; i < playersResult.length; ++i) {
			for (int j = i + 1; j < playersResult.length; ++j) {
				if (playersResult[i].getTotalScore() < playersResult[j]
						.getTotalScore()) {
					//swap the players by rank from highest score to lowest
					temp = playersResult[i];
					playersResult[i] = playersResult[j];
					playersResult[j] = temp;
				}
			}			
		}
		int index=0;
		for(PlayerResult in : playersResult)
			winners[index++] = new Player( in.getName() );
		
		
		return winners;
	}
    
  public Player currentPlayer(){
    // returns a player in the game
    // returns the player whose turn it currently is
    //
    // Order of play is determined by the order of the players in the 
    // arraylist originally passed into the startGame method.  player
    // at position 0 has the first turn, player at positoin 1 has second
    // turn, etc.
    //
	  
	  // change the turn of each player
	  

	  if(NumberOfPlayers <=++PositionOfPlayer)
		  PositionOfPlayer = 0;
	  
    return players.get(PositionOfPlayer);//return the current player turn
  }
  
 
  
  
}