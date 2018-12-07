package com.example.marcinko21.uno;

import android.graphics.Color;
import android.util.Log;

import com.example.marcinko21.uno.game.infoMsg.GameState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Contains the state of a Uno game.  Sent by the game when
 * a player wants to enquire about the state of the game.  (E.g., to display
 * it, or to help figure out its next move.)
 *
 * @author Meredith, Andrew, Ashley
 * @version 09 November 2018
 */
public class UnoState extends GameState
{

    /**
     * Initialize Variables
     */
    int player1Id;
    int player2Id;
    int turn;
    int color;
    char type;
    int value;
    int deckSize;
    int playerUno;
    boolean playerDeclaredUno;
    Card cbp; //card being played
    ArrayList<Card> deck = new ArrayList<Card>(108);
    ArrayList<Card> hand1 = new ArrayList<Card>(7);
    ArrayList<Card> hand2 = new ArrayList<Card>(7);
    ArrayList<Card> discardPile = new ArrayList<Card>(0);
    Random r = new Random();

    /**
     * Constructor for objects of class UnoState
     */
    public UnoState()
    {
        player1Id = 0;
        player2Id = 1;
        turn = 0;
        makeDeck();
        shuffleDeck();
        playerUno = -1;
        playerDeclaredUno = false;
        int i;
        for(i=0; i < 7; i++)
        {
            drawCard(hand1);
            drawCard(hand2);
        }
        discardPile.add(0,deck.get(0));
        deck.remove(deck.get(0));
        color = discardPile.get(discardPile.size()-1).color;
        type = discardPile.get(discardPile.size()-1).type;
        value = discardPile.get(discardPile.size()-1).value;

        //Log.i("Game state constructor","hand1 size is: " + hand1.size());
    }//ctor

    /**
     * Copy constructor for class UnoState
     *
     * @param state
     * 		the UnoState object that we want to clone
     */
    public UnoState (UnoState state)
    {

        player1Id = state.getPlayer1Id();
        player2Id = state.getPlayer2Id();
        turn = state.getTurn();
        playerUno = state.playerUno;
        playerDeclaredUno = state.playerDeclaredUno;

        int i = 0;

        //Log.i("Game state copy constructor","deck size is: " + state.deck.size());
        deck = new ArrayList<>();
        for(Card c : state.deck)
        {
            //deck.set(i, c);
            deck.add(c.clone());
            i++;
        }
        //Log.i("Game state copy constructor","deck size is: " + deck.size());
        i = 0;
        discardPile = new ArrayList<>();
        for(Card c : state.discardPile)
        {

            //discardPile.set(i, c);
            discardPile.add(c.clone());
            i++;
        }

        //Log.i("Game state copy constructor","hand1 size is: " + state.hand1.size());
        hand1 = new ArrayList<Card>();
        // this for loop creates a clone of every value in arraylist hand1
        for (Card c : state.hand1)
        {
            hand1.add(c.clone());
        }
        //Log.i("Game state copy constructor","hand1 size is: " + hand1.size());
        hand2 = new ArrayList<Card>();
        // this for loop creates a clone of every value in arraylist hand2
        for(Card c : state.hand2)
        {
            hand2.add(c.clone());
        }
        color = state.color;
        type = state.type;
        value = state.value;
    }//copyCtor


    /**
     * makeDeck
     * Method to make the deck for a new game
     */
    public void makeDeck()
    {
        deck.add(new Card(0,0,'n',R.drawable.green0));//1
        deck.add(new Card(0,1,'n',R.drawable.green1));//2
        deck.add(new Card(0,1,'n',R.drawable.green1_copy));//3
        deck.add(new Card(0,2,'n',R.drawable.green2));//4
        deck.add(new Card(0,2,'n',R.drawable.green2_copy));//5
        deck.add(new Card(0,3,'n',R.drawable.green3));//6
        deck.add(new Card(0,3,'n',R.drawable.green3_copy));//7
        deck.add(new Card(0,4,'n',R.drawable.green4));//8
        deck.add(new Card(0,4,'n',R.drawable.green4_copy));//9
        deck.add(new Card(0,5,'n',R.drawable.green5));//10
        deck.add(new Card(0,5,'n',R.drawable.green5_copy));//11
        deck.add(new Card(0,6,'n',R.drawable.green6));//12
        deck.add(new Card(0,6,'n',R.drawable.green6_copy));//13
        deck.add(new Card(0,7,'n',R.drawable.green7));//14
        deck.add(new Card(0,7,'n',R.drawable.green7_copy));//15
        deck.add(new Card(0,8,'n',R.drawable.green8));//16
        deck.add(new Card(0,8,'n',R.drawable.green8_copy));//17
        deck.add(new Card(0,9,'n',R.drawable.green9));//18
        deck.add(new Card(0,9,'n',R.drawable.green9_copy));//19
        deck.add(new Card(0,-1,'d',R.drawable.green_draw2));//20
        deck.add(new Card(0,-1,'d',R.drawable.green_draw2_copy));//21
        deck.add(new Card(4,-2,'w',R.drawable.green_wild));//22
        deck.add(new Card(0,-3,'r',R.drawable.green_reverse));//23
        deck.add(new Card(0,-3,'r',R.drawable.green_reverse_copy));//24
        deck.add(new Card(4,-5,'d',R.drawable.green_draw4));//25
        deck.add(new Card(0,-4,'s',R.drawable.green_skip));//26
        deck.add(new Card(0,-4,'s',R.drawable.green_skip_copy));//27
        deck.add(new Card(1,0,'n',R.drawable.blue0));//28
        deck.add(new Card(1,1,'n',R.drawable.blue1));//29
        deck.add(new Card(1,1,'n',R.drawable.blue1_copy));//30
        deck.add(new Card(1,2,'n',R.drawable.blue2));//31
        deck.add(new Card(1,2,'n',R.drawable.blue2_copy));//32
        deck.add(new Card(1,3,'n',R.drawable.blue3));//33
        deck.add(new Card(1,3,'n',R.drawable.blue3_copy));//34
        deck.add(new Card(1,4,'n',R.drawable.blue4));//35
        deck.add(new Card(1,4,'n',R.drawable.blue4_copy));//36
        deck.add(new Card(1,5,'n',R.drawable.blue5));//37
        deck.add(new Card(1,5,'n',R.drawable.blue5_copy));//38
        deck.add(new Card(1,6,'n',R.drawable.blue6));//39
        deck.add(new Card(1,6,'n',R.drawable.blue6_copy));//40
        deck.add(new Card(1,7,'n',R.drawable.blue7));//41
        deck.add(new Card(1,7,'n',R.drawable.blue7_copy));//42
        deck.add(new Card(1,8,'n',R.drawable.blue8));//43
        deck.add(new Card(1,8,'n',R.drawable.blue8_copy));//44
        deck.add(new Card(1,9,'n',R.drawable.blue9));//45
        deck.add(new Card(1,9,'n',R.drawable.blue9_copy));//46
        deck.add(new Card(1,-1,'d',R.drawable.blue_draw2));//47
        deck.add(new Card(1,-1,'d',R.drawable.blue_draw2_copy));//48
        deck.add(new Card(4,-5,'d',R.drawable.blue_draw4));//49
        deck.add(new Card(1,-3,'r',R.drawable.blue_reverse));//50
        deck.add(new Card(1,-3,'r',R.drawable.blue_reverse_copy));//51
        deck.add(new Card(1,-4,'s',R.drawable.blue_skip));//52
        deck.add(new Card(1,-4,'s',R.drawable.blue_skip_copy));//53
        deck.add(new Card(4,-2,'w',R.drawable.blue_wild));//54
        deck.add(new Card(2,0,'n',R.drawable.red0));//55
        deck.add(new Card(2,1,'n',R.drawable.red1));//56
        deck.add(new Card(2,1,'n',R.drawable.red1_copy));//57
        deck.add(new Card(2,2,'n',R.drawable.red2));//58
        deck.add(new Card(2,2,'n',R.drawable.red2_copy));//59
        deck.add(new Card(2,3,'n',R.drawable.red3));//60
        deck.add(new Card(2,3,'n',R.drawable.red3_copy));//61
        deck.add(new Card(2,4,'n',R.drawable.red4));//62
        deck.add(new Card(2,4,'n',R.drawable.red4_copy));//63
        deck.add(new Card(2,5,'n',R.drawable.red5));//64
        deck.add(new Card(2,5,'n',R.drawable.red5_copy));//65
        deck.add(new Card(2,6,'n',R.drawable.red6));//66
        deck.add(new Card(2,6,'n',R.drawable.red6_copy));//67
        deck.add(new Card(2,7,'n',R.drawable.red7));//68
        deck.add(new Card(2,7,'n',R.drawable.red7_copy));//69
        deck.add(new Card(2,8,'n',R.drawable.red8));//70
        deck.add(new Card(2,8,'n',R.drawable.red8_copy));//71
        deck.add(new Card(2,9,'n',R.drawable.red9));//72
        deck.add(new Card(2,9,'n',R.drawable.red9_copy));//73
        deck.add(new Card(2,-1,'d',R.drawable.red_draw2));//74
        deck.add(new Card(2,-1,'d',R.drawable.red_draw2_copy));//75
        deck.add(new Card(4,-5,'d',R.drawable.red_draw4));//76
        deck.add(new Card(2,-3,'r',R.drawable.red_reverse));//77
        deck.add(new Card(2,-3,'r',R.drawable.red_reverse_copy));//78
        deck.add(new Card(2,-4,'s',R.drawable.red_skip));//79
        deck.add(new Card(2,-4,'s',R.drawable.red_skip_copy));//80
        deck.add(new Card(4,-2,'w',R.drawable.red_wild));//81
        deck.add(new Card(3,0,'n',R.drawable.yellow0));//82
        deck.add(new Card(3,1,'n',R.drawable.yellow1));//83
        deck.add(new Card(3,1,'n',R.drawable.yellow1_copy));//84
        deck.add(new Card(3,2,'n',R.drawable.yellow2));//85
        deck.add(new Card(3,2,'n',R.drawable.yellow2_copy));//86
        deck.add(new Card(3,3,'n',R.drawable.yellow3));//87
        deck.add(new Card(3,3,'n',R.drawable.yellow3_copy));//88
        deck.add(new Card(3,4,'n',R.drawable.yellow4));//89
        deck.add(new Card(3,4,'n',R.drawable.yellow4_copy));//90
        deck.add(new Card(3,5,'n',R.drawable.yellow5));//91
        deck.add(new Card(3,5,'n',R.drawable.yellow5_copy));//92
        deck.add(new Card(3,6,'n',R.drawable.yellow6));//93
        deck.add(new Card(3,6,'n',R.drawable.yellow6_copy));//94
        deck.add(new Card(3,7,'n',R.drawable.yellow7));//95
        deck.add(new Card(3,7,'n',R.drawable.yellow7_copy));//96
        deck.add(new Card(3,8,'n',R.drawable.yellow8));//97
        deck.add(new Card(3,8,'n',R.drawable.yellow8_copy));//98
        deck.add(new Card(3,9,'n',R.drawable.yellow9));//99
        deck.add(new Card(3,9,'n',R.drawable.yellow9_copy));//100
        deck.add(new Card(3,-1,'d',R.drawable.yellow_draw2));//101
        deck.add(new Card(3,-1,'d',R.drawable.yellow_draw2_copy));//102
        deck.add(new Card(4,-5,'d',R.drawable.yellow_draw4));//103
        deck.add(new Card(3,-3,'r',R.drawable.yellow_reverse));//104
        deck.add(new Card(3,-3,'r',R.drawable.yellow_reverse_copy));//105
        deck.add(new Card(3,-4,'s',R.drawable.yellow_skip));//106
        deck.add(new Card(3,-4,'s',R.drawable.yellow_skip_copy));//107
        deck.add(new Card(4,-2,'w',R.drawable.yellow_wild));//108
    } //makeDeck()

    /**
     * shuffleDeck
     *
     * Method to Shuffle the Deck
     */
    public void shuffleDeck()
    {
        //how do you shuffle an arrayList
        Collections.shuffle(deck);//Collections.shuffle - shuffles an arrayList

    }//shuffleDeck

    /**
     * drawCard
     * Method to add a card to a specific hand
     *
     * @param hand
     */
    public void drawCard(ArrayList<Card> hand)
    {
        checkIsEmpty();
        hand.add(deck.get(0));
        deck.remove(0);
        if(turn == 0)
        {
            turn = 1;
        }
        else
        {
            turn = 0;
        }
        if(hand == hand1 && playerUno == player1Id)
        {
            playerUno = -1;
            playerDeclaredUno = false;
        }
        else if(hand == hand2 && playerUno == player2Id)
        {
            playerUno = -1;
            playerDeclaredUno = false;
        }
    }//drawCard

    /**
     * drawTwo
     * Method to add two cards to a specific hand
     *
     * @param hand
     */
    public void drawTwo(ArrayList<Card> hand)
    {
        Log.i("Game","Drawing two");
        checkIsEmpty();
        hand.add(deck.get(0));
        deck.remove(0);
        hand.add(deck.get(0));
        deck.remove(0);
        if(turn == 0)
        {
            turn = 1;
        }
        else
        {
            turn = 0;
        }
        if(hand == hand1 && playerUno == player1Id)
        {
            playerUno = -1;
            playerDeclaredUno = false;
        }
        else if(hand == hand2 && playerUno == player2Id)
        {
            playerUno = -1;
            playerDeclaredUno = false;
        }
    }//drawTwo

    /**
     * drawFour
     * Method to add a four cards to a specific hand
     *
     * @param hand
     */
    public void drawFour(ArrayList<Card> hand)
    {
        Log.i("Game","Drawing four");
        checkIsEmpty();
        for(int i = 0; i < 4; i++)
        {
            hand.add(deck.get(0));
            deck.remove(0);
        }
        if(turn == 0)
        {
            turn = 1;
        }
        else
        {
            turn = 0;
        }
        if(hand == hand1 && playerUno == player1Id)
        {
            playerUno = -1;
            playerDeclaredUno = false;
        }
        else if(hand == hand2 && playerUno == player2Id)
        {
            playerUno = -1;
            playerDeclaredUno = false;
        }
    }//drawFour

    /**
     * playCard
     * Method to play a card from the hand
     *
     * @param hand
     * @param c
     */
    public boolean playCard(ArrayList<Card> hand, Card c)
    {
        //went to office hours and got help with this method
        //check cardToPlay is valid move based on color, etc.
        if(this.color == c.color || this.value == c.value || c.type == 'w' || (c.type == 'd' && c.color == 4))
        {
            hand.remove(c);
            discardPile.add(c);
            color = discardPile.get(discardPile.size() - 1).color;
            type = discardPile.get(discardPile.size() - 1).type;
            value = discardPile.get(discardPile.size() - 1).value;
            //in case of wild, pick players max color
            //TODO: fix me
            //Log.i("playcard", "checking wild");
            if(c.type == 'w' || (c.type == 'd' && c.color == 4))
            {
                //int counts[] = {0,0,0,0};
                int colors[] = new int[4];
                int size = 0;
                int out = 0;
                for(Card m:hand)
                {
                    if(m.color == 0) colors[0]++; //g
                    else if(m.color == 1) colors[1]++; //b
                    else if(m.color == 2) colors[2]++; //r
                    else if(m.color == 3) colors[3]++; //y
                }
                for(int i = 0; i < colors.length; i++)
                {
                    if(colors[i] > size)
                    {
                        size = colors[i];
                        out = i;
                    }
                    Log.i("Game","Wild color: "+out);
                }

                //Log.i("playCard", "max count is" + max + "location is: "+ (location +1));
                color = out;
            }
            //if the card is played or the draw button is used, switch turns
            playerDeclaredUno = false;
            //SPECIAL CARD ACTIONS
            //if the card is a reverse card
           /* if(c.type == 'r')
            {

            }*/
            return true;
        }
        if(hand == hand1 && playerUno == player1Id)
        {
            playerUno = -1;
            playerDeclaredUno = false;
        }
        else if(hand == hand2 && playerUno == player2Id)
        {
            playerUno = -1;
            playerDeclaredUno = false;
        }
        return false;
    }//playCard

    /**
     * isUno
     * Method to see if UNO can be called for a hand.
     *
     * @param hand
     * @param playerId
     * @return true or false
     */
    public boolean isUno(ArrayList<Card> hand, int playerId)
    {
        if(hand.size() == 1)
        {
            return true;
        }
        else
        {
            return false;
        }

    }//isUno

    /**
     * getHandSize
     * get method for the size of a hand
     *
     * @param hand
     * @return size
     */
    public int getHandSize(ArrayList<Card> hand)
    {
        return hand.size();
    }

    /**
     * getGameState
     * Method to get a formatted String describing the basic game state
     *
     * @return player 1 and 2's id, with the turn
     */
    public String getGameState()
    {
        return "Player 1 ID: " + player1Id + "Player 2 ID: " + player2Id + ", Turn: " + turn;

    } //getGameState

    /**
     * Method to check if the deck is empty and makes and shuffles
     * a new one from the pile if it is
     */
    public void checkIsEmpty()
    {
        if(deck.size() == 0){ //if is empty
            while(discardPile.size() > 1)
            {
                deck.add(discardPile.get(0));
                discardPile.remove(0);
            }
            shuffleDeck();
        }
        //Log.i("checkIsEmpty","Deck Size Is : " + deck.size());
    } //checkIsEmpty

    /**
     * selectCard action
     */
    public void selectCard(Card c)
    {
        cbp = c;
    }//selectCard


    /**
     * playCard action
     *
     * @return true if legal move
     */
    public boolean playCard(int playerId, Card cardToPlay)
    {
        if(playerId != turn)
        {
            return false;
        }
        else
        {
            if(playerId == player1Id)
            {
                return playCard(hand1, cardToPlay);
            }
            else
            {
                return playCard(hand2, cardToPlay);
            }

        }
        //if cardToPlay is played
        //remove from hand
    }//playCard

    /**
     * getHand action
     *
     * @return true if legal move
     */
    public ArrayList<Card> getHand(int playerId)
    {
        if(playerId == player1Id)
        {
            return hand1;
        }
        else
        {
            return hand2;
        }

    }//getHand

    /**
     * drawCard action
     *
     * @return true if legal move
     */
    public boolean drawCard(int player1Id, int player2Id)
    {
        if (deckSize == 0)
        {
            return false;
        }
        else
        {
            return true;
        }

    }//drawCard


    /**
     * declareUno action
     *
     * @return true if legal move
     */
    public boolean declareUno(int playerId)
    {
        if(((isUno(hand1, playerId) || isUno(hand2, playerId))) && (playerDeclaredUno == false) && playerUno == -1)
        {
            playerDeclaredUno = true;
            if(playerId == player1Id && hand2.size() == 1)
            {
                for(int i = 0; i < 2; i++)
                {
                    Log.i("Game","Uno penalty");
                    drawCard(hand2);
                }
                return true;
            }
            else if(playerId == player2Id && hand1.size() == 1)
            {
                for(int i = 0; i < 2; i++)
                {
                    Log.i("Game","Uno penalty");
                    drawCard(hand1);
                }
                return true;
            }
            else if(playerId == player1Id && hand1.size() == 1)
            {
                playerUno = playerId;
                return true;
            }
            else if(playerId == player2Id && hand2.size() == 1)
            {
                playerUno = playerId;
                return true;
            }
        }
        playerDeclaredUno = false;
        return false;
    }


    /**
     * toString
     *
     * @return Game State
     */
    @Override
    public String toString()
    {
        //todo check w tribelhorn if we need to implement this method
        int i = 0;
        /*
        updateDeckSize();
        String out = "Deck Cards: ";
        while(deck.get(i) != null) {
            out = out + "ID: "+ deck.get(i).getId() + " Value: " + deck.get(i).getValue() + " Type: " +
                    deck.get(i).getType() + " Color: " + deck.get(i).getColor() + ", ";
            i++;
        }
        i = 0;
        out = out + " Discard Pile: ";
        while(discardPile.get(i) != null) {
            out = out + "ID: "+ discardPile.get(i).getId() + " Value: " + discardPile.get(i).getValue() +
                    " Type: " + discardPile.get(i).getType() + " Color: " + discardPile.get(i).getColor();
            i++;
        }
        out = out + "Deck size: " + getDeckSize() + " Discard Pile size: "+ getDiscardPile() + " Turns: " + turn + " ";
        for (i = 0; i < hand1.size(); i++) {
            out = out + "hand1 card"+i+": " + "Color: " + hand1.get(i).getColor() + " Value: " +
                    hand1.get(i).getValue() + " Type: " + hand1.get(i).getType() + " " + "ID: " +
                    hand1.get(i).getId();
        }
        for (i = 0; i < hand2.size(); i++) {
            out = out + "hand2 card"+i+": " + "Color: " + hand2.get(i).getColor() + " Value: " +
                    hand2.get(i).getValue() + " Type: " + hand2.get(i).getType() + " " + "ID: " + hand2.get(i).getId();
        }
        out = out + "Player 1 ID: "+player1Id+" Player 2 ID: "+player2Id;
        return out;
        */
        return "";
    }//toString

    /**
     *  Set and Get Methods
     */
    public void setPlayer1Id(int pid)
    {
        player1Id = pid;
    }

    public int getPlayer1Id()
    {
        return player1Id;
    }

    public void setPlayer2Id(int pid)
    {
        player2Id = pid;
    }

    public int getPlayer2Id()
    {
        return player2Id;
    }

    public int getTurn()
    {
        return turn;
    }

    public void setTurn(int newTurn)
    {
        turn = newTurn;
        playerDeclaredUno = false;
    }

    public ArrayList<Card> getDiscardPile()
    {
        return discardPile;
    }

    public int getDeckSize()
    {
        return deck.size();
    }

    public ArrayList<Card> getDeck()
    {
        return deck;
    }

    public void setPlayerDeclaredUno()
    {
        playerDeclaredUno = true;
    }

    public boolean getPlayerDeclaredUno()
    {
        return playerDeclaredUno;
    }
}