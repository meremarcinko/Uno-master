package com.example.marcinko21.uno;

import com.example.marcinko21.uno.game.GamePlayer;
import com.example.marcinko21.uno.game.LocalGame;
import com.example.marcinko21.uno.game.actionMsg.GameAction;

/**
 * UnoLocalGame Class for Uno Game
 * Returns the new Local Game State
 * Checks if game is Over
 */
public class UnoLocalGame extends LocalGame{

    protected UnoState state;

    /**
     *  Constructor for UnoLocalGame
     */
    public UnoLocalGame() {

        //perform superclass initialization
        super();

        //create a new, unfilled in UnoState object
        state = new UnoState();
    }//ctor

    /**
     * SendUpdatedStateTo
     * @param p
     */
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new UnoState(state));
    }//sendUpdatedStateTo


    @Override
    protected boolean makeMove(GameAction action) {
        if(action instanceof UnoDrawAction) {

            GamePlayer p = action.getPlayer();

            if (p == ) {
                state.drawCard(hand1);
            } else (p == ) {
                state.drawCard(hand2);
            }



        } else if (action instanceof UnoUnoAction){

            state.setPlayerDeclaredUno();
            return true;

        }

        return false;
    }

}

