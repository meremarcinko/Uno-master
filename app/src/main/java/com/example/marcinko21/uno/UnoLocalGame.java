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

    /** Check if the game is over. If it is over, return
     * a string that tells who the winner is. If the game
     * is not over, return null;
     *
     * @return
     *
     *      a message that tells who has wont the game, or
     *      null if the game is not over
     */


    @Override
    protected String checkIfGameOver()
    {
        //if a player does not have any card left, the
        //game is over

        //if(player 1 hand == 0)
        //return return playerNames[gameWinner]+" is the winner.";
        //else
        //game is not over

        //if(player 2 hand == 0)
        //return playerNames[gameWinner]+" is the winner.";
        //else
        //game is not over
        return null;
    }
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new UnoState(state));
    }//sendUpdatedStateTo


    @Override
    protected boolean makeMove(GameAction action) {
        /*if(action instanceof UnoDrawAction) {

            GamePlayer p = action.getPlayer();

            if (p == ) {
                state.drawCard(hand1);
            } else (p == ) {
                state.drawCard(hand2);
            }



        } else if (action instanceof UnoUnoAction){

            state.setPlayerDeclaredUno();
            return true;

        }*/

        return false;
    }
    /**
     * Tell whether the given player is allowed to make a move at the
     * present point in the game.
     *
     * @param playerIdx
     * 		the player's player-number (ID)
     * @return
     * 		true iff the player is allowed to move
     */
    protected boolean canMove(int playerIdx)
    {
        return true;
    }


}

