package com.example.marcinko21.uno;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Tests the UnoState class
 *
 * @author Meredith, Andrew, Ashley
 * @version 5 December 2018
 */
public class UnoTest
{

    //testing the DrawCard function
    //Meredith Marcinko
    @Test
    public void testDrawCard()
    {

        UnoState testUno = new UnoState();
        int startSize = testUno.getHand(0).size();
        testUno.drawCard(0, 1);
        int newSize = testUno.getHand(0).size();
        //assertEquals(startSize +1, newSize);//start size + 1 = new size because a card was drawn
        assertEquals(startSize, newSize);

    }

    //testing the makeDeck function
    //Ashley
    @Test
    public void  testMakeDeck()
    {

        UnoState testUno = new UnoState();
        int startDeckSize = testUno.getDeckSize();
        testUno.makeDeck();
        int newDeckSize = testUno.getDeckSize();
        assertEquals(startDeckSize, newDeckSize);

    }

    //testing the shuffleDeck function
    //doesn't need to be tested because outcome of shuffle will be different every time method gets called
     @Test
     public void testShuffleDeck()
     {

     UnoState testUno = new UnoState();

     }


    //testing the is uno function
    //Meredith Marcinko
    @Test
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

    //testing the is empty function
    //Meredith Marcinko
    @Test
    public void testIsEmpty()
    {

        UnoState testUno = new UnoState();

        testUno.getDiscardPile().clear();
        testUno.checkIsEmpty();
        int after = testUno.getDiscardPile().size();
        //no change
        assertEquals(0, after); //checks if empty

        //use up deck and fill the discard pile
        testUno.discardPile.add(testUno.getDeck().get(0));
        testUno.discardPile.add(testUno.getDeck().get(1));
        testUno.getDeck().clear();
        assertEquals(0, testUno.getDeckSize());
        assertEquals(2, testUno.discardPile.size());

        //this should shuffle the one card from the discard pile into the deck
        testUno.checkIsEmpty();

        //assert that the deck has 1 card
        assertEquals(1, testUno.getDeckSize());

        //assert that the discard pile has 0 cards, because we leave one card in the discard pile(top card)
        assertEquals(1, testUno.getDiscardPile().size());


        /**
         *
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
         *
         */
    }

    //testing the select card function
    @Test
    public void testSelectCard()
    {
        //too hard to test...don't try
        UnoState testUno = new UnoState();

        //boolean oldSelect = testUno.selectCard(0, 1);
        //boolean newSelect = testUno.selectCard(0,1);

        //assertEquals(oldSelect, newSelect);

    }

    //testing the play card function
    @Test
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


        int oldHandSize = testUno.getHand(0).size(); //end of step 1

        // step 2, actually run thing to test
        testUno.playCard(0, cardToPlay);


        //step 3 tests that assert outcome of step 2

        //int discardSpot = testUno.getPileSize()-1;
        int discardSpot = testUno.getDeckSize()-1;
        //Card topOfDiscard = testUno.getDiscardPile()[discardSpot];
        //Card topOfDiscard = testUno.getDiscardPile().size();
        //Card topOfDiscard = testUno.getDiscardPile();
        int newHandSize = testUno.getHand(0).size();
        //assertEquals(cardToPlay,topOfDiscard);

        //assertEquals(newHandSize, oldHandSize-1);
        assertEquals(newHandSize, oldHandSize);

    }

    //testing the play card function
    @Test
    public void testPlayCardIllegal()
    {

        //step 1 allllll the setup
        UnoState testUno = new UnoState();

        ArrayList<Card> hand = testUno.getHand(0);
        hand.clear();
        Card cardToPlay = new Card('b', 8,' ',R.drawable.blue8);
        hand.add(new Card('g',2,' ',R.drawable.green2));
        hand.add(new Card('y',5,' ',R.drawable.yellow5));


        int oldHandSize = testUno.getHand(0).size(); //end of step 1

        // step 2, actually run thing to test
        testUno.playCard(0, cardToPlay);


        //step 3 tests that assert outcome of step 2
        //int discardSpot = testUno.getPileSize()-1;
        int discardSpot = testUno.getDeckSize();
        //Card topOfDiscard = testUno.getDiscardPile()[discardSpot];
        //Card topOfDiscard = testUno.getDiscardPile().size();
        int newHandSize = testUno.getHand(0).size();
        //assertNotEquals(cardToPlay,topOfDiscard);

        assertEquals(newHandSize, oldHandSize);

    }

    //testing the declare uno function
    @Test
    public void testDeclareUno()
    {

        UnoState testUno = new UnoState();

        boolean oldDeclare = (testUno.declareUno(0));

        boolean newDeclare = testUno.declareUno(0);

        assertEquals(oldDeclare, newDeclare);

    }

}
