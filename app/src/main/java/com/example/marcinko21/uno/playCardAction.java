package com.example.marcinko21.uno;

import com.example.marcinko21.uno.game.GamePlayer;
import com.example.marcinko21.uno.game.actionMsg.GameAction;

/**
 * An action by which the player tells the game which card it wants to play.
 * (typically the human's name, if it's a GameHumanPlayer).
 *
 * @author Meredith, Andrew, Ashley
 * @version 09 November 2018
 */
public class playCardAction extends GameAction {

    /** constructor
     *
     * @param p
     * 		the player who sent the action
     */
    public playCardAction(GamePlayer p) {
        super(p);
        //todo add player card number

        //todo check if play is valid
    }

}

