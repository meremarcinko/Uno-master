package com.example.marcinko21.uno;

import android.util.Log;

import com.example.marcinko21.uno.game.GameMainActivity;
import com.example.marcinko21.uno.game.GamePlayer;
import com.example.marcinko21.uno.game.LocalGame;
import com.example.marcinko21.uno.game.actionMsg.GameAction;
import com.example.marcinko21.uno.game.infoMsg.GameInfo;

/**
 * UnoLocalGame Class for Uno Game
 * Returns the new Local Game State
 * Checks if game is Over
 */
public class UnoLocalGame extends LocalGame {

    //the game's state
    public UnoState state;

    GamePlayer p = new GamePlayer() {
        @Override
        public void gameSetAsGui(GameMainActivity activity) {

        }

        @Override
        public void setAsGui(GameMainActivity activity) {

        }

        @Override
        public void sendInfo(GameInfo info) {

        }

        @Override
        public void start() {

        }

        @Override
        public boolean requiresGui() {
            return false;
        }

        @Override
        public boolean supportsGui() {
            return false;
        }
    };

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
     * Check if the game is over. It is over, return a string that tells
     * who the winner(s), if any, are. If the game is not over, return null;
     *
     * @return a message that tells who has won the game, or null if the
     * game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //if a player does not have any card left, the
        //game is over

        if(state.hand1 == null)
        {
            return "You are the winner!";
        }

        if(state.hand2 == null)
        {
            return "You lose, better luck next time!";
        }

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



    /**
     * Tell whether the given player is allowed to make a move at the
     * present point in the game.
     *
     * @param playerIdx
     * 		the player's player-number (ID)
     * @return
     * 		true iff the player is allowed to move
     */
    /**
     * sendUpdatedStateTo
     * <p>
     * Notify the given player that its state has changed. This should involve sending
     * a GameInfo object to the player.
     *
     * @param p
     */
    protected void sendUpdatedStateTo(GamePlayer p) {

        //make a copy of the state, and send it to the player
        p.sendInfo(new UnoState(state));

        /** maybe another solution
        state.toString();
        p.sendInfo(state);
         */

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
    @Override
    protected boolean canMove(int playerIdx) {

        //playerIdx == state.getWhoseMove();
        //playerIdx = newTurn;
        //int newTurn = state.getTurn();

        if(playerIdx == getPlayerIdx(p)){
            return true;
        } else {
            return false;
        }

    }


    /**
     * MakeMove
    * Makes a move on behalf of a player
    *
    * @param action The move that the player has sent to the game
    * @return Tells whether the move was a legal one.
     */
    @Override
    protected boolean makeMove(GameAction action) {

        //todo for PlayCard action, actually do the action

        GamePlayer p = action.getPlayer();
        int playerNum = 0;

        for (int i = 0; i < players.length; i++) {
            if (players[i] == p) {
                playerNum = i;
            }
        }





        Log.i("Make Move","Checking Turn");
            //for every action, check that it's my turn
            //true for except for challenges
            if (state.getTurn() != playerNum) {
                return false;
            }
        Log.i("Make Move","About to take action");
            if (action instanceof UnoDrawAction) {

                state.drawCard(state.getHand(playerNum));
                return true;

            } else if (action instanceof UnoUnoAction) {

                state.setPlayerDeclaredUno();
                return true;

            }

        Log.i("Make Move","Didn't Move");
        return false;
    }//makeMove

}

