/*
 * @author Faisal Alshathri<br>100817338
 * 
 * Pile class (using Stack ADT)LIFO
 * 
 * Methods
 * void add(Card c)
 * push the cards of deck in the pile
 * 
 * Card remove()
 * pop the card when a player draw a card
 * if there is no cards return null
 * 
 * int size()
 * return the size of pile(Number of Cards in the pile)
 * 
 * String toString()
 * @Override the object method
 * and return a string represent the cards in Pile from top to bottom 
 **/

import java.util.Stack;

public class Pile{
  
  protected Stack<Card> pile; 
  
  
  public Pile(){
   
    pile = new Stack<Card>();
    
  }
  public void add(Card c){
    // add a card to the top of the pile
    pile.push(c);
   
  }
 
  //removes the card on the top of the pile and return the Card
 // return null if there is no card in the pile
  public Card remove() {
		
		if (pile.size() < 1) {
			 //System.err.println("there is no more Card in the Pile");
			// new java.util.Scanner(System.in).nextLine();//pause
			return null;//when there no card in the pile return null
		}
		return pile.pop();// removes the card on the top of the pile
	}
  
  public int size(){
    // returns the size of the pile
    // (how many Cards are in the pile)
    return pile.size();
  }
  
  @Override
  public String toString(){
    // return a string representation of the pile
    // this method does not change the current pile
    
    String PilesToString ="";
    for(int i=size()-1; 0<=i; --i){
       PilesToString+=pile.get(i)+" ";
    }
    
    return PilesToString;
  }
  /*
  //test the toString() method to print the cards from top to bottom
  public static void main(String [] args){
   Deck d = new Deck(); 
   Pile p = new Pile();
   
   for(int i=0; i<52; ++i){
     System.out.print(d.getCard(i)+" ");
       p.add(d.getCard(i) );
   }
   System.out.println("\n"+p);//print pile to test
    
  }
  */
}