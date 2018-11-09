package com.example.marcinko21.uno;

import com.example.marcinko21.uno.game.GamePlayer;
import com.example.marcinko21.uno.game.actionMsg.GameAction;

/**
 * An action by which the player tells the game that it wants to draw.
 * (typically the human's name, if it's a GameHumanPlayer).
 *
 * @author Meredith, Andrew, Ashley
 * @version 09 November 2018
 */
public class UnoDrawAction extends GameAction {

    /** constructor
     *
     * @param p
     * 		the player who sent the action
     */
    public UnoDrawAction(GamePlayer p) { super(p); }

}
