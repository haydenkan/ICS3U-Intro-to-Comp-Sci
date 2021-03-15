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
  private static final int NUM_MAX_PAIRPLUS = 100;
  private static final int NUM_MIN_PAIRPLUS = 50;
  private static final int NUM_MIN_PLAY = 50;
  static double balance = 1000;
  static double anteWager = 0;
  static double pairPlusWager = 0;
  static double playWager = 0;
  static int pairPlusPayout = 0;
  static boolean playAgain = true;
  


  public static void main(String[] args) {
    System.out.println("████████╗██╗░░██╗██████╗░███████╗███████╗  ░█████╗░░█████╗░██████╗░██████╗░\n╚══██╔══╝██║░░██║██╔══██╗██╔════╝██╔════╝  ██╔══██╗██╔══██╗██╔══██╗██╔══██╗\n░░░██║░░░███████║██████╔╝█████╗░░█████╗░░  ██║░░╚═╝███████║██████╔╝██║░░██║\n░░░██║░░░██╔══██║██╔══██╗██╔══╝░░██╔══╝░░  ██║░░██╗██╔══██║██╔══██╗██║░░██║\n░░░██║░░░██║░░██║██║░░██║███████╗███████╗  ╚█████╔╝██║░░██║██║░░██║██████╔╝\n░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚═╝╚══════╝╚══════╝  ░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝╚═════╝░\n██████╗░░█████╗░██╗░░██╗███████╗██████╗░\n██╔══██╗██╔══██╗██║░██╔╝██╔════╝██╔══██╗\n██████╔╝██║░░██║█████═╝░█████╗░░██████╔╝\n██╔═══╝░██║░░██║██╔═██╗░██╔══╝░░██╔══██╗\n██║░░░░░╚█████╔╝██║░╚██╗███████╗██║░░██║\n╚═╝░░░░░░╚════╝░╚═╝░░╚═╝╚══════╝╚═╝░░╚═╝");
    while(balance - (NUM_MIN_ANTE + NUM_MIN_PLAY) >= 0 && playAgain){ //Makes sure that the player has enough money to play on each round.
      System.out.println("Balance: $" + String.format("%.2f", balance));
      
      anteWager = getAnteWager();

      pairPlusWager = getPairPlusWager(anteWager);

      String playerHand = dealCards();
      String dealerHand = dealCards();

      System.out.println("Player: " + playerHand);

      playWager = getPlayWager(anteWager);

      if(playWager != 0){ //A play wager of 0 means the player folded, which is why this is != 0
        System.out.println("Dealer: " + dealerHand);
        /**
         * First checks for rule A, and if dealer has Jack-high or worse, does not play. 
         * If dealer hand passes rule A, checks which hand is better based on the numerical value it is given. 
         */
        if(handRank(dealerHand, false) > 11){
          if(handRank(dealerHand, false) > handRank(playerHand, true)){
            balance -= anteWager;
            balance -= playWager;
            System.out.println("You lost the bet.");
          }else if(handRank(dealerHand, false) == handRank(playerHand, true)){
            System.out.println("You tied.");
            balance -= anteWager;
          }else{
            System.out.println("You won the bet.");
            balance += anteWager;
            balance += playWager;
          }
        }else{
          System.out.println("Rule A was met.");
          balance -= anteWager;
        }
        balance -= pairPlusWager;

        if(pairPlusPayout > 0 && pairPlusWager > 0){
          balance += pairPlusWager * (pairPlusPayout + 1);
          System.out.println("You won a pair plus payout of " + pairPlusPayout + ":1.");
          pairPlusWager = 0;
        }
        System.out.println("Your new balance is $" + String.format("%.2f", balance) + ".");
      }else{
        System.out.println("You have folded and lost your wagers.");
        balance -= anteWager;
        balance -= pairPlusWager;
        System.out.println("Your balance is: $" + String.format("%.2f", balance) + ".");
      }
      askPlayAgain();
    }
    if(balance - (NUM_MIN_ANTE + NUM_MIN_PLAY) < 0){
      System.out.println("You have run out of money to spend.");
    }else{
      System.out.println("You have left the table with $" + balance + ".");
    }
  }
  
  /**
   * Creates and distributes three full cards.
   * @return
   */
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
  /**
   * Combines the value and suit of the card to create a full card.
   * @return
   */
  private static String getCard() {
    return getFace() + getSuit();
  }
  /**
   * Returns a random suit for the card.
   * @return
   */
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
  /**
   * Returns a random value for the card.
   * @return
   */
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
  /**
   * Gets an ante wager from the player.
   * Checks to make sure ante wager is between $50 - $100.
   * Makes sure the player can pay out the ante wager and the play wager.
   * Makes sure the input is a number, and not a symbol or letter.
   * @return
   */
  private static double getAnteWager(){
    boolean wagerEntered = false;
    double anteAmount = 0;
    while(!wagerEntered){
        System.out.println("Please enter an ante wager between $50 - $100: ");
        try{
            anteAmount = Double.parseDouble(in.nextLine());
            if(anteAmount >= NUM_MIN_ANTE && anteAmount <= NUM_MAX_ANTE){
              if(anteAmount * 2 > balance){
                System.out.println("You will not have enough money to place a play wager. Please place a different ante wager.");
              }else{
                System.out.println("You have entered an ante wager of $" + String.format("%.2f", anteAmount) + ".");
                wagerEntered = true;
              }
            }
        }catch(NumberFormatException e){
          System.out.println("Please enter a valid number. ");
        }
    }
    return anteAmount;
  }
  /**
   * Asks if player would like to place a pair plus wager.
   * If player says yes, asks for a wager amount, checking for the same things as the getAnteWager() method.
   * If the player will not have enough money after placing a minimum pair plus wager for a play wager, automatically skips the pair plus wager quetion.
   * If the player says no, returns 0.
   * @param ante
   * @return
   */
  private static double getPairPlusWager(double ante){
    if(ante * 2 + 50 < balance){
      System.out.println("Would you like to place an optional pair plus wager? (Y/N)");
      boolean pairPlusResponded = false;

      while(!pairPlusResponded){
        String pairPlusAnswer = in.nextLine();
        if(pairPlusAnswer.equals("Y")){
          pairPlusResponded = true;
        }else if(pairPlusAnswer.equals("N")){
          return 0;
        }else{
          System.out.println("Please enter a valid response.");
        }
      }
      double pairPlusAmount = 0;
      boolean wagerEntered = false;
      while(!wagerEntered){
        System.out.println("Please enter a pair plus wager between $50 - $100: ");
        try{
            pairPlusAmount = Double.parseDouble(in.nextLine());
            if(pairPlusAmount >= NUM_MIN_PAIRPLUS && pairPlusAmount <= NUM_MAX_PAIRPLUS){
              if(balance - anteWager * 2 - pairPlusAmount < 0){
                System.out.println("You will not have enough money to place a play wager. Please place a different pair plus wager.");
              }
              System.out.println("You have entered a pair plus wager of $" + String.format("%.2f", pairPlusAmount) + ".");
              wagerEntered = true;
            }
        }catch(NumberFormatException e){
            System.out.println("Please enter a valid number. ");
        }
      }
      return pairPlusAmount;
    }
    System.out.println("You cannot place a pair plus wager due to insufficient funds.");
    return 0;
  }
  /**
   * Asks if player wants to fold or play.
   * Does not need to ask for a monetary amount because the play wager is always the same as the ante wager.
   * Checks to make sure the response is either "Y" or "N".
   * @param ante
   * @return
   */
  private static double getPlayWager(double ante){
    System.out.println("Would you like to place a play wager? (Y/N)");
    boolean playResponded = false;
    while(!playResponded){
      String playAnswer = in.nextLine();
      if(playAnswer.equals("Y")){
        System.out.println("You have placed a play wager of $" + String.format("%.2f", ante) + ".");
        return ante;
      }else if(playAnswer.equals("N")){
        return 0;
      }else{
        System.out.println("Please enter a valid response.");
      }
    }
    return 0;
  }
  /**
   * Seperates the cards in the hand into suits and values for easy hand ranking recognition.
   * Converts the card values into integers so that it is easy to input them in if statements.
   * Using the suit, value, and max/mid/min cards, determines which hand the player/dealer has.
   * The hands ranks are numbered 18-1 instead of their names to make it easier to compare hands. I can use greater than, equal to, and less than comparison operators to see which hand won.
   * 18 is a straight flush and 1 is an ace high. 
   * Hand ranks 1-13 are the high card ranks. 
   * Also changes the pair plus payout multiplier so that the player may get a pair plus multiplier for a pair rank or higher. 
   * Uses isPlayer parameter to make sure that the pair plus payout multiplier is only changed by the player's hand, and not the dealer's hand as well. 
   * @param hand
   * @return
   */
  private static double handRank(String hand, boolean isPlayer){
    double cardOne = Integer.parseInt(findFace(hand, 1));
    double cardTwo = Integer.parseInt(findFace(hand, 2));
    double cardThree = Integer.parseInt(findFace(hand, 3));

    String suitOne = findSuit(hand, 1);
    String suitTwo = findSuit(hand, 2);
    String suitThree = findSuit(hand, 3);

    double getMax = Math.max(cardOne, Math.max(cardTwo, cardThree)); 
    double getMin = -Math.max(-cardOne, Math.max(-cardTwo, -cardThree)); 
    double getMid= (cardOne + cardTwo + cardThree) - (getMax + getMin);

    if(suitOne.equals(suitTwo) && suitTwo.equals(suitThree) && getMin + 1 == getMid && getMax - 1 == getMid){
      if(isPlayer){
        pairPlusPayout = 40;
      }
      return 18;
    }else if(cardOne == cardTwo && cardTwo == cardThree){
      if(isPlayer){
        pairPlusPayout = 30;
      }
      return 17;
    }else if(getMin + 1 == getMid && getMax - 1 == getMid){
      if(isPlayer){
        pairPlusPayout = 6;
      }
      return 16;
    }else if(suitOne.equals(suitTwo) && suitTwo.equals(suitThree)){
      if(isPlayer){
        pairPlusPayout = 3;
      }
      return 15;
    }else if(cardOne == cardTwo || cardTwo == cardThree || cardThree == cardOne){
      if(isPlayer){
        pairPlusPayout = 1;
      }
      if(cardOne == cardTwo || cardTwo == cardThree){
        return 14 + cardTwo * 0.1;
      }else if(cardThree == cardOne){
        return 14 + cardOne * 0.1;
      }
    }else{
      if(isPlayer){
        pairPlusPayout = 0;
      }
      return getMax * 1;
    }
    return 0.0;
  }

  /**
   * Returns a face value based on an inputted hand and position.
   * Uses the substring method to take only the card value from the hand. 
   * Uses the try and catch statements to check if the card has a face value instead of an integer value. This is achieved by trying to parseInt the card value. 
   * If the card has a face value, it will not have an integer to parse and will throw the NumberFormatException. 
   * Catching the NumberFormatException tells the method if the card has a face value.
   * Assigns integers to the face values, increasing chronologically after 10 and decreasing after 2. 
   * @param hand
   * @param position
   * @return
   */
  private static String findFace(String hand, int position){
    String cardValue = "";

    if(position == 1){
      cardValue = hand.substring(0, hand.indexOf(" ") - 1);
    }else if(position == 2){
      cardValue = hand.substring(hand.indexOf(" ") + 1, hand.indexOf(" ", hand.indexOf(" ") + 1) - 1);
    }else if(position == 3){
      cardValue = hand.substring(hand.indexOf(" ", hand.indexOf(" ") + 1) + 1, hand.length() - 2);
    }

    boolean isFace = false;
    try{
      Integer.parseInt(cardValue);
    }catch(NumberFormatException e){
      isFace = true;
    }

    if(isFace){
      if(cardValue.equals("K")){
        cardValue = "13";
      }else if(cardValue.equals("Q")){
        cardValue = "12";
      }else if(cardValue.equals("J")){
        cardValue = "11";
      }else if(cardValue.equals("A")){
        cardValue = "1";
      }
    }
    return cardValue;

  }
  /**
   * Finds the suit using the substring method and returns it as a one character string. 
   * @param hand
   * @param position
   * @return
   */
  private static String findSuit(String hand, int position){
    String cardSuit = "";

    if(position == 1){
      cardSuit = hand.substring(hand.indexOf(" ") - 1, hand.indexOf(" "));
    }else if(position == 2){
      cardSuit = hand.substring(hand.indexOf(" ", hand.indexOf(" ") + 1) - 1, hand.indexOf(" ", hand.indexOf(" ") + 1));
    }else if(position == 3){
      cardSuit= hand.substring(hand.length() - 2, hand.length() - 1);
    }

    return cardSuit;
  }
  /**
   * Asks if the player would like to play again.
   * The answer will change the playAgain boolean, which determines whether or not the majority of the main method will run. 
   * @return
   */
  private static void askPlayAgain(){
    if(balance >= NUM_MIN_ANTE + NUM_MIN_PLAY){
      System.out.println("Would you like to play again? (Y/N)");
      boolean playAgainResponded = false;
      while(!playAgainResponded){
        String playAgainAnswer = in.nextLine();
        if(playAgainAnswer.equals("Y")){
          System.out.println("You have chosen to play again.");
          playAgain = true;
          playAgainResponded = true;
        }else if(playAgainAnswer.equals("N")){
          System.out.println("You have left the table.");
          playAgain = false;
          playAgainResponded = true;
        }else{
          System.out.println("Please enter a valid response.");
        }
      }
    }else{
      playAgain = false;
    }
  }
}