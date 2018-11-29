package com.example.marcinko21.uno;

import android.util.Log;

import com.example.marcinko21.uno.game.GamePlayer;
import com.example.marcinko21.uno.game.LocalGame;
import com.example.marcinko21.uno.game.actionMsg.GameAction;

import java.util.ArrayList;

/**
 * UnoLocalGame Class for Uno Game
 * Returns the new Local Game State
 * Checks if game is Over
 */
public class UnoLocalGame extends LocalGame {

    //the game's state
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
        //test
        if(state.hand1.size() == 0)
        {
            return "You are the winner!";
        }

        if(state.hand2.size() == 0)
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

        if(playerIdx != state.getTurn()){
            return false;
        } else {
            return true;
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
            else if(action instanceof UnoPlayAction){
                ArrayList<Card> hand = new ArrayList<>(0);
                    if(state.turn == state.player1Id){
                        hand = state.hand1;
                    }
                    else{
                        hand = state.hand2;
                    }
                state.playCard(hand, state.cbp);
                return true;
            }
            else if(action instanceof UnoSkip)
            {
                // TODO: 11/29/2018 finish implementing this method 
                //if turn == 0 when the card is played return 0
                //else return 1
                if(turn == 0){
                    turn = 1;
                }
                else {
                    turn = 0;
                    return true;
                }
            }
            else if(action instanceof UnoReverse)
            {
                // TODO: 11/29/2018 implement this method 
                //if turn == 0 when the card is played return 0
                //else return 1
                //treat the reverse like a skip card
            }

            else if(action instanceof UnoDraw2)
            {
                // TODO: 11/29/2018 implement this method 
                //if this card is drawn, player receives two cards
                //else turn = person who played the card
            } 
            else if (action instanceof playCardAction){

                //todo get card number from action
                if(validPlay) {
                    //tell gameState to play card
                    if(){//not skip or reverse, change turn)
                        return true;
                    } //if ok return true
                    if(){
                        return true;
                    }
                }
            //todo for PlayCard action, actually do the action
        Log.i("Make Move","Didn't Move");

        return false;
    }//makeMove

}

