package com.example.marcinko21.uno;

import com.example.marcinko21.uno.game.GamePlayer;
import com.example.marcinko21.uno.game.actionMsg.GameAction;

/**
 *  An action by which the player tells the game that it is a reverse card.
 *
 *
 * @author Meredith, Andrew, Ashley
 *  @version 09 November 2018
 */
public class UnoReverse extends GameAction
{
    /** An action by which the player tells the game that it wants to draw.
     * (typically the human's name, if it's a GameHumanPlayer).
     *
     * @author Meredith, Andrew, Ashley
     *  @version 09 November 2018
     */
    private Card reverseCard;
    /** constructor
     *
     * @param p
     * 		the player who sent the action
     */
    public UnoReverse(GamePlayer p, Card reverseCard)
    {
        super(p);
        this.reverseCard = reverseCard;
    }

    public Card setReverseCard(Card reverseCard)
    {
        return reverseCard;
    }

    public Card getReverseCard()
    {
        return reverseCard;
    }
}
