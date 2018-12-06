package com.example.marcinko21.uno;

import com.example.marcinko21.uno.game.GamePlayer;
import com.example.marcinko21.uno.game.actionMsg.GameAction;
/**
 *  An action by which the player tells the game that it needs to draw 4 cards.
 *
 *
 * @author Meredith, Andrew, Ashley
 *  @version 09 November 2018
 */
public class UnoDraw4 extends GameAction
{

    private Card draw4Card;
    /** constructor
     *
     * @param p
     * 		the player who sent the action
     */
    public UnoDraw4(GamePlayer p, Card draw4Card)
    {
        super(p);
        this.draw4Card = draw4Card;
    }

    public Card setDraw4Card(Card draw4Card)
    {
        return draw4Card;
    }

    public Card getDraw4Card()
    {
        return draw4Card;
    }
}
