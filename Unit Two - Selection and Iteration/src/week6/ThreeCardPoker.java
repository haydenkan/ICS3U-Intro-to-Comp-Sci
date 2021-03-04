package src.week6;

import java.util.Scanner;

public class ThreeCardPoker {

  private static final int HEARTS = 0;
  private static final int DIAMONDS = 1;
  private static final int CLUBS = 2;
  private static final int SPADES = 3;
  private static final int NUM_SUITS = 4;
  private static final int NUM_FACES = 13;
  private static final int JACK = 11;
  private static final int QUEEN = 12;
  private static final int KING = 13;
  private static final int ACE = 14;
  static Scanner in = new Scanner(System.in);
  private static final int NUM_MAX_ANTE = 100;
  private static final int NUM_MIN_ANTE = 50;


  public static void main(String[] args) {

    String playerHand = dealCards();
    String dealerHand = dealCards();

    System.out.println("Player: " + playerHand);
    System.out.println("Dealer: " + dealerHand);

    anteWager();

  }

  private static String dealCards() {
    String cards = "";

    for (int i = 0; i < 3; i++) {
      Boolean hasCard = false;
      while (!hasCard) {
        String card = getCard();
        if (isUnique(cards, card)) {
          cards += card + " ";
          hasCard = true;
        }
      }
    }
    return cards;
  }

  private static String getCard() {
    return getFace() + getSuit();
  }

  private static String getSuit() {
    int suit = (int) (Math.random() * NUM_SUITS);
    if (suit == HEARTS)
      return "H";
    else if (suit == DIAMONDS)
      return "D";
    else if (suit == CLUBS)
      return "C";
    else if (suit == SPADES)
      return "S";
    else
      return null;
  }

  private static String getFace() {
    int face = (int) (Math.random() * NUM_FACES + 2);
    if (face >= 2 && face <= 10)
      return "" + face;
    else if (face == JACK)
      return "J";
    else if (face == QUEEN)
      return "Q";
    else if (face == KING)
      return "K";
    else if (face == ACE)
      return "A";
    else
      return null;
  }

  public static boolean isUnique(String playerHand, String card) {
    return playerHand.indexOf(card) == -1;
  }

  private static double anteWager(){
    boolean wagerEntered = false;
    double anteAmount = 0;
    while(!wagerEntered){
        try{
            System.out.println("Please enter an ante wager between $50 - $100: ");
            anteAmount = Integer.parseInt(in.nextLine());
            if(anteAmount > NUM_MIN_ANTE && anteAmount < NUM_MAX_ANTE){
                wagerEntered = true;
            }

        }catch(NumberFormatException ex){
            System.out.println("Please enter a valid number: ");
        }
    }
    return anteAmount;
  }

}