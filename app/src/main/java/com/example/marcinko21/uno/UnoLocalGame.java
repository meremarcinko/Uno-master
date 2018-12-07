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
public class UnoLocalGame extends LocalGame
{

    //the game's state
    protected UnoState state;

    /**
     *  Constructor for UnoLocalGame
     */
    public UnoLocalGame()
    {

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
    protected String checkIfGameOver()
    {
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
    protected void sendUpdatedStateTo(GamePlayer p)
    {

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
    protected boolean canMove(int playerIdx)
    {

        if(playerIdx != state.getTurn())
        {
            return false;
        }
        else
        {
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
    protected boolean makeMove(GameAction action)
    {

        GamePlayer p = action.getPlayer();
        int playerNum = 0;
        int play = getPlayerIdx(p);

        for (int i = 0; i < players.length; i++)
        {
            if (players[i] == p)
            {
                playerNum = i;
            }
        }

        //Log.i("Make Move","Checking Turn");
            //for every action, check that it's my turn
            //true for except for challenges

        if (state.getTurn() != playerNum)
        {
            return false;
        }


        //Log.i("Make Move","About to take action");
            if (action instanceof UnoDrawAction)
            {

                Log.i("Game","Drawing card w/ action call");
                state.drawCard(state.getHand(playerNum));

                return true;
                /*boolean played = state.drawCard(state.getHand(playerNum));
                //if player 1 drew, end turn and make i
                //check to see if there is a valid move
                //when there is no valid move, draw the card for them
                int turn = state.getTurn();
                //changes the turn if the card is played
                if(played) {
                    if (turn == 0) {
                        state.setTurn(1);
                    } else {
                        state.setTurn(1);
                    }
                    return true;
                }*/

            }
            else if (action instanceof UnoUnoAction)
            {
                //state.setPlayerDeclaredUno();
                if(state.playerDeclaredUno == false)
                {
                    state.declareUno(playerNum);
                }
                return true;
            }
            //went into office hours and recieved help on this method
            else if(action instanceof UnoPlayAction)
            {
                ArrayList<Card> hand;
                    if(state.turn == state.player1Id)
                    {
                        hand = state.hand1;
                    }
                    else
                    {
                        hand = state.hand2;
                    }
                state.playCard(hand, state.cbp);
                return true;
            }
            //went into office hours and received help on this method
            else if(action instanceof UnoSkip)
            {
                int turn = state.getTurn();
                Card cardToPlay = ((UnoSkip)action).getSkipCard();
                boolean played = state.playCard(state.getTurn(), cardToPlay);
                if(played)
                {
                    if (turn == 0)
                    {
                        state.setTurn(1);
                        state.setTurn(0);
                    }
                    else
                    {
                        state.setTurn(0);
                        state.setTurn(1);
                    }
                    return true;
                }
            }
            else if(action instanceof UnoReverse)
            {

                int turn = state.getTurn();
                Card cardBeingPlayed = ((UnoReverse)action).getReverseCard();
                boolean played = state.playCard(state.getTurn(), cardBeingPlayed);
                if(played)
                {
                    if (turn == 0)
                    {
                        state.setTurn(1);
                        state.setTurn(0);
                    }
                    else
                    {
                        state.setTurn(0);
                        state.setTurn(1);
                    }
                    return true;
                }
                //treat the reverse like a skip card
            }
            else if(action instanceof UnoDraw4)
            {
                //player 1's turn is 0
                int turn = state.getTurn();
                Card cardToPlay = ((UnoDraw4)action).getDraw4Card();
                boolean played = state.playCard(state.getTurn(), cardToPlay);
                if(played)
                {
                    if (turn == 0)
                    {
                        state.drawFour(state.hand2);
                        if (turn == 0)
                        {
                            state.setTurn(1);
                            state.setTurn(0);
                        }
                        else
                        {
                            state.setTurn(0);
                            state.setTurn(1);
                        }
                    }
                    else
                    {
                        state.drawFour(state.hand1);
                        if (turn == 0)
                        {
                            state.setTurn(1);
                            state.setTurn(0);
                        }
                        else
                        {
                            state.setTurn(0);
                            state.setTurn(1);
                        }
                    }
                    return true;
                }
                //treat the draw4 like a skip card
            }
            else if(action instanceof UnoDraw2)
            {
                //if this card is drawn, player receives two cards
                //else turn = person who played the card

                //player 1's turn is 0
                int turn = state.getTurn();
                Card cardPlayed = ((UnoDraw2)action).getDraw2Card();
                boolean played = state.playCard(state.getTurn(), cardPlayed);
                if(played)
                {
                    if (turn == 0)
                    {
                        state.drawTwo(state.hand2);
                        if (turn == 0)
                        {
                            state.setTurn(1);
                            state.setTurn(0);
                        }
                        else
                        {
                            state.setTurn(0);
                            state.setTurn(1);
                        }
                    }
                    else
                    {
                        Log.i("Game","Drawing two w/ action");
                        state.drawTwo(state.hand1);
                        if (turn == 0)
                        {
                            state.setTurn(1);
                            state.setTurn(0);
                        }
                        else
                        {
                            state.setTurn(0);
                            state.setTurn(1);
                        }
                    }
                    return true;
                }
                //treat the draw2 like a skip card
            }
            //went into office hours and received help on this method
            else if (action instanceof playCardAction)
            {
                //Log.i("Make Move", "have play card action");
                if(canMove(playerNum))
                { //if valid action

                    //Log.i("Make Move", "about to play card");
                    Card c = ((playCardAction)action).getCard();

                    //tell gameState to play card
                    boolean played = state.playCard(playerNum, c);

                    if(played)
                    { //not skip or reverse or draw 2 or draw 4, change turn)
                        int turn = state.getTurn();
                        if(turn == 0)
                        {
                            state.setTurn(1);
                        }
                        else
                        {
                            state.setTurn(0);
                        }
                        return true;

                    } //if ok return true
                    return true;
                }

            }

        //Log.i("Make Move","Didn't Move");
        return false;

    }//makeMove
}



