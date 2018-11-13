package com.example.marcinko21.uno;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UnoTest {
    @Test
    //testing the DrawCard function
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

    }

    @Test
    //testing the shuffleDeck function
    public void testShuffleDeck()
    {
        UnoState testUno = new UnoState();

    }

    @Test
    //testing the updating the pile size function
    public void testUpdatePileSize()
    {
        UnoState testUno = new UnoState();

    }

    @Test
    //testing the update deck size function
    public void testUpdateDeckSize()
    {
        UnoState testUno = new UnoState();

    }

    @Test
    //testing the is uno function
    public void testIsUno()
    {
        UnoState testUno = new UnoState();

    }

    @Test
    //testing the is empty function
    public void testIsEmpty()
    {
        UnoState testUno = new UnoState();

    }

    @Test
    //testing the select card function
    public void testSelectCard()
    {
        //too hard to test..dont try
        UnoState testUno = new UnoState();

        boolean oldSelect = testUno.selectCard(0, 1);
        boolean newSelect = testUno.selectCard(0,1);

        assertEquals(oldSelect, newSelect);


    }

    @Test
    //testing the play card function
    public void testPlayCard()
    {
        //step 1 all the setup
        UnoState testUno = new UnoState();

        ArrayList<Card> hand = testUno.getHand(0);
        hand.clear();
        Card cardToPlay = new Card('b', 8,' ',"");
        hand.add(new Card('g',2,' ',""));
        hand.add(cardToPlay);
        hand.add(new Card('y',5,' ',""));


        int oldHandSize = testUno.getHand(0).size();
        //end of step 1

        testUno.playCard(0, cardToPlay); // step 2, actually run thing to test


        //step 3 tests that assert outcome of step 2
        int discardSpot = testUno.getPileSize()-1;
        Card topOfDiscard = testUno.getDiscardPile()[discardSpot];
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
        Card cardToPlay = new Card('b', 8,' ',"");
        hand.add(new Card('g',2,' ',""));
        hand.add(new Card('y',5,' ',""));


        int oldHandSize = testUno.getHand(0).size();
        //end of step 1

        testUno.playCard(0, cardToPlay); // step 2, acutually run thing to test


        //step 3 tests that assert outcome of step 2
        int discardSpot = testUno.getPileSize()-1;
        Card topOfDiscard = testUno.getDiscardPile()[discardSpot];
        int newHandSize = testUno.getHand(0).size();
        assertNotEquals(cardToPlay,topOfDiscard);

        assertEquals(newHandSize, oldHandSize);

    }

    @Test
    //testing the declare uno function
    public void testDeclareUno()
    {
        UnoState testUno = new UnoState();

        boolean oldDeclare = testUno.declareUno(0);
        boolean newDeclare = testUno.declareUno(0,1);

        assertEquals(oldDeclare, newDeclare);

    }
}
