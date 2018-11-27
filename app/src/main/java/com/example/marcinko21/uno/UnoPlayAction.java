package com.example.marcinko21.uno;

import com.example.marcinko21.uno.game.GamePlayer;
import com.example.marcinko21.uno.game.actionMsg.GameAction;

public class UnoPlayAction extends GameAction {
    /** constructor
     *
     * @param p
     * 		the player who sent the action
     */
    public UnoPlayAction(GamePlayer p) { super(p); }
}
