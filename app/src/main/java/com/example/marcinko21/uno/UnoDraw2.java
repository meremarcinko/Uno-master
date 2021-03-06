package com.example.marcinko21.uno;

import com.example.marcinko21.uno.game.GamePlayer;
import com.example.marcinko21.uno.game.actionMsg.GameAction;
/**
 *  An action by which the player tells the game that it needs to draw 2 cards.
 *
 *
 * @author Meredith, Andrew, Ashley
 *  @version 09 November 2018
 */
public class UnoDraw2 extends GameAction
{

    private Card draw2Card;
    /** constructor
     *
     * @param p
     * 		the player who sent the action
     */
    public UnoDraw2(GamePlayer p, Card draw2Card)
    {
        super(p);
        this.draw2Card = draw2Card;
    }

    public Card setDraw2Card(Card draw2Card)
    {
        return draw2Card;
    }

    public Card getDraw2Card()
    {
        return draw2Card;
    }
}
