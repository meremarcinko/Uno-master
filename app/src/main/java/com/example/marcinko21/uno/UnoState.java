package com.example.marcinko21.uno;

import com.example.marcinko21.uno.game.infoMsg.GameState;
import java.util.ArrayList;
import java.util.Random;

public class UnoState extends GameState
{
    int player1Id;
    int player2Id;
    int turn;
    int color;
    ArrayList<Card> deck = new ArrayList<Card>(0);
    ArrayList<Card> hand1 = new ArrayList<Card>(7);
    ArrayList<Card> hand2 = new ArrayList<Card>(7);
    ArrayList<Card> discardPile = new ArrayList<Card>(0); //make this an arraylist?
    Random r = new Random();

    //Default constructor
    public UnoState() {
        player1Id = 0;
        player2Id = 1;
        turn = 0;
        makeDeck();
        shuffleDeck();
        int i;
        for(i=0; i < 7; i++){
            drawCard(hand1);
            drawCard(hand2);
        }
        discardPile.add(0,deck.get(0));
        deck.remove(deck.get(0));
        color = discardPile.get(discardPile.size()).color;
    }

    /** Deep Copy Constructor
     *
     */
    public UnoState (UnoState state) {
        player1Id = state.getPlayer1Id();
        player2Id = state.getPlayer2Id();
        turn = state.getTurn();

        int i = 0;
        for(Card c : state.deck){
            deck.set(i, c);
            i++;
        }
        i = 0;
        for(Card c : state.discardPile)
        {
            discardPile.set(i, c);
            i++;
        }

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
        color = discardPile.get(discardPile.size()).color;
    }


    //Method to make the deck for a new game
    public void makeDeck() {
        int i, n, k, j;

        //number cards with value 0
        for (i = 0; i < 4; i++) {
            Card c = new Card(i, 0, 'n',"card"+i);
            deck.add(c);
        }

        //number cards with values 1-9
        while (i < 76) {
            for (n = 0; n < 2; n++) {
                for (k = 0; k < 10; k++) {
                    for (j = 0; j < 4; j++) {
                        Card c = new Card(j, k, 'n',"card"+i);
                        deck.add(c);
                        i++;
                    }
                }
            }
        }

        //skip cards
        while (i < 84) {
            for (k = 0; k < 2; k++) {
                for (j = 0; j < 4; j++) {
                    Card c = new Card(j, 0, 's',"card"+i);
                    deck.add(c);
                    i++;
                }
            }
        }

        //draw 2 cards
        while (i < 92) {
            for (k = 0; k < 2; k++) {
                for (j = 0; j < 4; j++) {
                    Card c = new Card(j, 0, 'd',"card"+i);
                    deck.add(c);
                    i++;
                }
            }
        }

        //reverse cards
        while (i < 100) {
            for (k = 0; k < 2; k++) {
                for (j = 0; j < 4; j++) {
                    Card c = new Card(j, 0, 'r',"card"+i);
                    deck.add(c);
                    i++;
                }
            }
        }

        //wild cards
        while (i < 104) {
            for (j = 0; j < 4; j++) {
                Card c = new Card(4, 0, 'w',"card"+i);
                deck.add(c);
                i++;
            }
        }

        //wild draw 4 cards
        while (i < 108) {
            for (j = 0; j < 4; j++) {
                Card c = new Card(4, 0, 'd',"card"+i);
                deck.add(c);
                i++;
            }
        }
    } //makeDeck()

    //Method to shuffle the deck
    public void shuffleDeck(){
        Card temp[] = new Card[108];
        int i;
        int n;
        int index = r.nextInt(108);
        temp[0] = deck.get(index);
        for(i = 1;  i < 108; i++){
            index = r.nextInt(108);
            temp[i] = deck.get(index);
            //detects if the value from the random index is equal to another one already in temp
            for(n = 0; n < 108; n++){
                while(temp[i] == temp[n] && temp[n] != null){
                    index = r.nextInt(108);
                    temp[i] = deck.get(index);
                }
            }
        }

        //sets the deck in use to the randomly ordered one
        for(i = 0; i < 108; i++){
            deck.set(i, temp[i]);
        }
    }

    //Method to add a card to a specific hand
    public void drawCard(ArrayList<Card> hand){
        hand.add(deck.get(0));
        deck.remove(0);
    }

    //Method to play a card from the hand
    public void playCard(ArrayList<Card> hand, Card c){
        hand.remove(c);
        discardPile.add(c);
        color = discardPile.get(discardPile.size()).color;
    }

    //Method to see if UNO can be called for a hand.
    public boolean isUno(ArrayList<Card> hand, int playerId){
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
        return "Player 1 ID: " + player1Id + "Player 2 ID: " + player2Id + ", Turn: " + turn;
    }

    //Method to check if the deck is empty and makes and shuffles a new one from the pile if it is
    public void checkIsEmpty(){
        int count = discardPile.size();
        if(deck.size() == 0){
            for(int i = 0; i < count-1; i++){
                deck.add(discardPile.get(i));
                discardPile.remove(i);
            }
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


    /** playCard action
     *
     * @return true if legal move
     */
    public boolean playCard(int playerId, Card cardToPlay)
    {
        if(playerId != turn) {
            return false;
        }
        else {
            if(playerId == 0) {
                playCard(hand1,cardToPlay);
            }
            //todo else for p1
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
    public boolean declareUno(int playerId)
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

    public Card[] getDiscardPile() {
        return discardPile;
    }

}
