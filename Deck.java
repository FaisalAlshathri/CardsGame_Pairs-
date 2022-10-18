public class Deck{
 
  protected Card[] cards = new Card[52];
  
  public int numCards(){ return cards.length; }
  
  public Card getCard(int pos){
    return cards[pos];
  }
   
  public Deck(){
    int c = 0;
    for(int rank = 1; rank <= Card.RANKS.length; rank += 1){
      for(String suit: Card.SUITS){
        cards[c] = new Card(rank,suit);
        c+=1;
      }
    }
  }
  
  
  public void shuffle(){
    int len = cards.length;
    Card swap;
    for(int i=0; i<len; i+=1){
      swap = cards[i];
      int j = (int) (Math.random()*(len-i) + i);
      cards[i] = cards[j];
      cards[j] = swap;
    }
  }
  
  public void printDeck(){
    int len = cards.length;
    for(int c=0; c<len; c+=1){
      System.out.print( cards[c] + (c<len-1 ? "," : "") );
      if((c+1)%13 == 0) System.out.println();
    }
   
  }
  public static void main(String [] args){
    
   Deck d =  new Deck();
     
   d.shuffle();
   d.printDeck(); 
    
  }
}