package com.example.marcinko21.uno;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UnoTest {

    @Test
    //testing the DrawCard function
    //Meredith Marcinko
    public void testDrawCard()
    {
        UnoState testUno = new UnoState();
        int startSize = testUno.getHand(0).size();
        testUno.drawCard(0, 1);
        int newSize = testUno.getHand(0).size();
        assertEquals(startSize +1, newSize);//start size + 1 = new size because a card was drawn

    }

    @Test
    //testing the makeDeck function
    public void  testMakeDeck()
    {
        UnoState testUno = new UnoState();
        int startDeckSize = testUno.getDeckSize();
        testUno.makeDeck();
        int newDeckSize = testUno.getDeckSize();
        assertEquals(startDeckSize, newDeckSize);

    }

    /** commented out because may not need ot be tested
     *

     @Test
     //testing the shuffleDeck function
     public void testShuffleDeck()
     {
     //todo see if there is anything to check
     UnoState testUno = new UnoState();

     }

     */

    /** commented out temporarily because I can't find these methods in classes outside of test class
     *
     @Test
     //testing the updating the pile size function
     public void testUpdatePileSize()
     {
     //todo
     UnoState testUno = new UnoState();
     int oldSize = testUno.getDiscardPile().size();
     testUno.testUpdatePileSize();
     int newSize = testUno.getDiscardPile().size();
     assertEquals(oldSize, newSize);

     }

     @Test
     //testing the update deck size function
     public void testUpdateDeckSize()
     {
     //todo
     UnoState testUno = new UnoState();

     }

     */

    @Test
    //testing the is uno function
    //Meredith Marcinko
    public void testIsUno()
    {

        UnoState testUno = new UnoState();
        //int have = testUno.getHand(0).size();
        ArrayList<Card> hand = testUno.getHand(0);
        boolean result = testUno.isUno(hand, 0);
        //int want = 1;

        //assertEquals(have, want);
        //we know starting hand size if 7 so testUno should be false
        assertFalse(result);
        //remove cards from hand to test actually uno
        for(int i = 0; i<6; i++)
        {
            hand.remove(6-i);
        }
        //there should only be one card in the hand
        result = testUno.isUno(hand,0);
        assertTrue(result);
    }

    @Test
    //testing the is empty function
    //Meredith Marcinko
    public void testIsEmpty()
    {
        //todo check do I need to see if it shuffled of discard pile is empty?
        UnoState testUno = new UnoState();

        testUno.checkIsEmpty();
        int after = testUno.getDiscardPile().size();
        //int after = 0;
        //no change

        assertEquals(0, after);
        //use up deck and fill the discard pile
        testUno.discardPile.add(testUno.getDeck().get(0));
        testUno.getDeck().clear();

        testUno.checkIsEmpty();//this should shuffle the one card from the discard pile into the deck
        //assert that the deck has 1 card
        assertEquals(1,testUno.getDeckSize());
        //assert that the discard pile has 0 cards
        assertEquals(0,testUno.getDiscardPile().size());

    }

    @Test
    //testing the select card function
    public void testSelectCard()
    {
        //too hard to test...don't try
        UnoState testUno = new UnoState();

        //boolean oldSelect = testUno.selectCard(0, 1);
        //boolean newSelect = testUno.selectCard(0,1);

        //assertEquals(oldSelect, newSelect);

    }

    /**
    @Test
    //testing the play card function
    public void testPlayCard()
    {
        //step 1 all the setup
        UnoState testUno = new UnoState();

        ArrayList<Card> hand = testUno.getHand(0);
        hand.clear();
        Card cardToPlay = new Card('b', 8,' ',R.drawable.blue8);
        hand.add(new Card('g',2,' ',R.drawable.green2));
        hand.add(cardToPlay);
        hand.add(new Card('y',5,' ',R.drawable.yellow5));


        int oldHandSize = testUno.getHand(0).size();
        //end of step 1

        testUno.playCard(0, cardToPlay); // step 2, actually run thing to test


        //step 3 tests that assert outcome of step 2

        //int discardSpot = testUno.getPileSize()-1;
        int discardSpot = testUno.getDeckSize()-1;
        //Card topOfDiscard = testUno.getDiscardPile()[discardSpot];
        Card topOfDiscard = testUno.getDiscardPile().size();
        int newHandSize = testUno.getHand(0).size();
        assertEquals(cardToPlay,topOfDiscard);

        assertEquals(newHandSize, oldHandSize-1);

    }


    @Test
    //testing the play card function
    public void testPlayCardIllegal()
    {
        //step 1 allllll the setup
        UnoState testUno = new UnoState();

        ArrayList<Card> hand = testUno.getHand(0);
        hand.clear();
        Card cardToPlay = new Card('b', 8,' ',R.drawable.blue8);
        hand.add(new Card('g',2,' ',R.drawable.green2));
        hand.add(new Card('y',5,' ',R.drawable.yellow5));


        int oldHandSize = testUno.getHand(0).size();
        //end of step 1

        testUno.playCard(0, cardToPlay); // step 2, acutually run thing to test


        //step 3 tests that assert outcome of step 2
        //int discardSpot = testUno.getPileSize()-1;
        int discardSpot = testUno.getDeckSize();
        //Card topOfDiscard = testUno.getDiscardPile()[discardSpot];
        Card topOfDiscard = testUno.getDiscardPile().size();
        int newHandSize = testUno.getHand(0).size();
        assertNotEquals(cardToPlay,topOfDiscard);

        assertEquals(newHandSize, oldHandSize);

    }

    @Test
    //testing the declare uno function
    public void testDeclareUno()
    {
        UnoState testUno = new UnoState();
        //todo get help
        boolean oldDeclare = testUno.declareUno(0);


        boolean newDeclare = testUno.declareUno(0);

        assertEquals(oldDeclare, newDeclare);

    }
    */
}
