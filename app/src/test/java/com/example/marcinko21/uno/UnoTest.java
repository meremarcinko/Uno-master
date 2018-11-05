package com.example.marcinko21.uno;

import org.junit.Test;

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
        UnoState testUno = new UnoState();

        boolean oldSelect = testUno.selectCard(0, 1);
        boolean newSelect = testUno.selectCard(0,1);

        assertEquals(oldSelect, newSelect);


    }

    @Test
    //testing the play card function
    public void testPlayCard()
    {
        UnoState testUno = new UnoState();

        boolean oldPlayCard = testUno.playCard(0, 1);
        boolean newPlayCard = testUno.playCard(0,1);

        assertEquals(oldPlayCard, newPlayCard);

    }

    @Test
    //testing the declare uno function
    public void testDeclareUno()
    {
        UnoState testUno = new UnoState();

        boolean oldDeclare = testUno.declareUno(0,1);
        boolean newDeclare = testUno.declareUno(0,1);

        assertEquals(oldDeclare, newDeclare);

    }
}
