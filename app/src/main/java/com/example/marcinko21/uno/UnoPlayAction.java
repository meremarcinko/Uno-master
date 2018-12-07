package com.example.marcinko21.uno;

import com.example.marcinko21.uno.game.GamePlayer;
import com.example.marcinko21.uno.game.actionMsg.GameAction;

/**
 *  An action by which the play Action is implemented
 *
 *
 * @author Meredith, Andrew, Ashley
 *  @version 09 November 2018
 */
public class UnoPlayAction extends GameAction
{

    /** constructor
     *
     * @param p
     * 		the player who sent the action
     */
    public UnoPlayAction(GamePlayer p)
    {
        super(p);
    }

    // add private variable for the card number
    //getter and setter for those
    //maybe an overloaded constructor

}
