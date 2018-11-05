package com.example.marcinko21.uno;

import com.example.marcinko21.uno.game.infoMsg.GameState;
import java.util.ArrayList;
import java.util.Random;

public class UnoState extends GameState
{
    int player1Id;
    int player2Id;
    int deckSize;
    int pileSize;
    int turn;
    Card deck[] = new Card[108];
    ArrayList<Card> hand1 = new ArrayList<Card>(7);
    ArrayList<Card> hand2 = new ArrayList<Card>(7);
    Card discardPile[] = new Card[108];
    Random r = new Random();

    //Default constructor
    public State() {
        player1Id = 0;
        player2Id = 1;
        deckSize = 108;
        pileSize = 0;
        turn = 0;
        makeDeck();
        shuffleDeck();
        int i;
        for(i=0; i < 7; i++){
            drawCard(hand1);
            drawCard(hand2);
        }
        discardPile[0] = deck[0];
        deck[0] = null;
        for(i = 1; i < 108; i++){
            deck[i-1] = deck[i];
            deck[i] = null;
            discardPile[i] = null;
        }
        updateDeckSize();
        updatePileSize();
    }

    /** Deep Copy Constructor
     *
     */
    public State (UnoState state) {
        player1Id = state.getPlayer1Id();
        player2Id = state.getPlayer2Id();
        deckSize = state.getDeckSize();
        pileSize = state.getPileSize();
        turn = state.getTurn();
        deck = new Card[108];
        hand1 = new ArrayList<Card>(7);
        // this for loop creates a clone of every value in arraylist hand1
        for (Card c : hand1) {
            hand1.add(c.clone());
        }
        hand2 = new ArrayList<Card>(7);
        // this for loop creates a clone of every value in arraylist hand2
        for(Card c : hand2) {
            hand2.add(c.clone());
        }
        discardPile = new Card[108];

    }


    //Method to make the deck for a new game
    public void makeDeck() {
        int i, n, k, j;

        //number cards with value 0
        for (i = 0; i < 4; i++) {
            deck[i] = new Card(i, 0, 'n',"card"+i);
        }

        //number cards with values 1-9
        while (i < 76) {
            for (n = 0; n < 2; n++) {
                for (k = 0; k < 10; k++) {
                    for (j = 0; j < 4; j++) {
                        deck[i] = new Card(j, k, 'n',"card"+i);
                        i++;
                    }
                }
            }
        }

        //skip cards
        while (i < 84) {
            for (k = 0; k < 2; k++) {
                for (j = 0; j < 4; j++) {
                    deck[i] = new Card(j, 0, 's',"card"+i);
                    i++;
                }
            }
        }

        //draw 2 cards
        while (i < 92) {
            for (k = 0; k < 2; k++) {
                for (j = 0; j < 4; j++) {
                    deck[i] = new Card(j, 0, 'd',"card"+i);
                    i++;
                }
            }
        }

        //reverse cards
        while (i < 100) {
            for (k = 0; k < 2; k++) {
                for (j = 0; j < 4; j++) {
                    deck[i] = new Card(j, 0, 'r',"card"+i);
                    i++;
                }
            }
        }

        //wild cards
        while (i < 104) {
            for (j = 0; j < 4; j++) {
                deck[i] = new Card(4, 0, 'w',"card"+i);
                i++;
            }
        }

        //wild draw 4 cards
        while (i < 108) {
            for (j = 0; j < 4; j++) {
                deck[i] = new Card(4, 0, 'd',"card"+i);
                deck[i].setId("card" + i);
                i++;
            }
        }
    }

    //Method to shuffle the deck
    public void shuffleDeck(){
        Card temp[] = new Card[108];
        int i;
        int n;
        int index = r.nextInt(108);
        temp[0] = deck[index];
        for(i = 1;  i < 108; i++){
            index = r.nextInt(108);
            temp[i] = deck[index];
            //detects if the value from the random index is equal to another one already in temp
            for(n = 0; n < 108; n++){
                while(temp[i] == temp[n] && temp[n] != null){
                    index = r.nextInt(108);
                    temp[i] = deck[index];
                }
            }
        }
        //sets the deck in use to the randomly ordered one
        for(i = 0; i < 108; i++){
            deck[i] = temp[i];
        }
    }

    //Method to add a card to a specific hand
    public void drawCard(ArrayList<Card> hand, int player1Id, int player2Id){
        int i;
        hand.add(deck[0]);
        deck[0] = null;
        for(i = 1; i < 108; i++){
            deck[i-1] = deck[i];
            deck[i] = null;
        }
    }

    //Method to update pileSize
    public void updatePileSize(){
        int i = 0;
        while(discardPile[i] != null){
            i++;
        }
        pileSize = i;
    }

    //Method to get the discard pile's size
    public int getPileSize(){
        return pileSize;
    }

    //Method to update deckSize
    public void updateDeckSize(){
        int i = 0;
        while(deck[i] != null){
            i++;
        }
        deckSize = i;
    }

    //Method to get the deck size
    public int getDeckSize(){
        return deckSize;
    }

    //Method to play a card from the hand
    public void playCard(ArrayList<Card> hand, Card c, int player2Id, int player1Id){
        hand.remove(c);
        discardPile[getPileSize()+1] = c;
        updatePileSize();
    }

    //Method to see if UNO can be called for a hand.
    public boolean isUno(ArrayList<Card> hand, int player1Id, int player2Id){
        if(hand.size() == 1) {
            return true;
        }
        else
        {
            return false;
        }
    }

    //get method for the size of a hand
    public int getHandSize(ArrayList<Card> hand){
        return hand.size();
    }

    //Method to get a formatted String describing the basic game state
    public String getGameState() {
        return "Player 1 ID: " + player1Id + "Player 2 ID: " + player2Id + ", Deck Size: " + deckSize + ", Turn: " + turn;
    }

    //Method to check if the deck is empty and makes and shuffles a new one from the pile if it is
    public void checkIsEmpty(){
        int count = 0;
        int i;
        if(deckSize == 0){
            while(discardPile[count] != null) {
                count++;
            }
            for(i = 0; i < count-1; i++){
                deck[i] = discardPile[i];
                discardPile[i] = null;
            }
            discardPile[0] = discardPile[count];
            updatePileSize();
            updateDeckSize();
            shuffleDeck();
        }
    }

    /** selectCard action
     *
     * @return true if legal move
     */
    public boolean selectCard(int player1Id, int player2Id) {
        if(player1Id != turn) {
            return false;
        }
        else {
            return true;
        }
    }
}

    /** playCard action
     *
     * @return true if legal move
     */
    public boolean playCard(int player1Id, int player2Id)
    {
        if(player1Id != turn) {
            return false;
        }
        else {
            return true;
        }
    }

    public ArrayList<Card> getHand(int playerId)
        {
            if(playerId == player1Id) {
                return hand1;
            }
            return hand2;
        }

    /** drawCard action
     *
     * @return true if legal move
     */
    public boolean drawCard(int player1Id, int player2Id) {
        if (deckSize == 0) {
            return false;
        }
        else
        {
            return true;
        }
    }

    /** declareUno action
     *
     * @return true if legal move
     */
    public boolean declareUno(int player1Id, int player2Id)
    {
        return false;
    }



    /**
     * toString
     *
     * @return Game State
     */
    @Override
    public String toString() {
        int i = 0;
        updateDeckSize();
        String out = "Deck Cards: ";
        while(deck[i] != null) {
            out = out + "ID: "+deck[i].getId() + " Value: " + deck[i].getValue() + " Type: " + deck[i].getType() + " Color: " + deck[i].getColor() + ", ";
            i++;
        }
        i = 0;
        out = out + " Discard Pile: ";
        while(discardPile[i] != null) {
            out = out + "ID: "+ discardPile[i].getId() + " Value: " + discardPile[i].getValue() + " Type: " + discardPile[i].getType() + " Color: " + discardPile[i].getColor();
            i++;
        }
        out = out + "Deck size: " + getDeckSize() + " Discard Pile size: "+ getPileSize() + " Turns: " + turn + " ";
        for (i = 0; i < hand1.size(); i++) {
            out = out + "hand1 card"+i+": " + "Color: " + hand1.get(i).getColor() + " Value: " + hand1.get(i).getValue() + " Type: " + hand1.get(i).getType() + " " + "ID: " + hand1.get(i).getId();
        }
        for (i = 0; i < hand2.size(); i++) {
            out = out + "hand2 card"+i+": " + "Color: " + hand2.get(i).getColor() + " Value: " + hand2.get(i).getValue() + " Type: " + hand2.get(i).getType() + " " + "ID: " + hand2.get(i).getId();
        }
        out = out + "Player 1 ID: "+player1Id+" Player 2 ID: "+player2Id;
        return out;
    }


    //set/get methods
    public void setPlayer1Id(int pid){ player1Id = pid; }

    public int getPlayer1Id(){ return player1Id; }

    public void setPlayer2Id(int pid){ player2Id = pid; }

    public int getPlayer2Id(){ return player2Id; }

    public int getTurn(){ return turn; }

    public void setTurn(int newTurn) { turn = newTurn; }

}
