package com.example.marcinko21.uno;

import android.util.Log;

import com.example.marcinko21.uno.game.GameComputerPlayer;
import com.example.marcinko21.uno.game.infoMsg.GameInfo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Contains the smart ai implications of the game
 *
 * @author Meredith, Andrew, Ashley
 * @version 05 December 2018
 */
public class UnoSmartPlayer extends GameComputerPlayer
{
    Random r = new Random();
    boolean legal = true;
    boolean found = true;

    /**
     * Constructor for UnoSmartPlayer
     *
     * @param name
     */
    public UnoSmartPlayer(String name) {
        super(name);
    }

    /**
     * RecieveInfo from GameInfo parent class
     *
     * @param info
     */
    @Override
    protected void receiveInfo(GameInfo info)
    {
        Log.i("Smart AI", "Receiving info");
        info.setGame(game);
        UnoState gs = (UnoState) info;
        int id = gs.player2Id;
        found = false;

        //UNO check
        if (gs.hand2.size() == 1 || gs.hand1.size() == 1)
        {
            Log.i("Smart AI", "Declaring UNO");
            sleep(r.nextInt(500) + 500);
            UnoUnoAction uno = new UnoUnoAction(this);
            game.sendAction(uno);
        }

        if (gs.getTurn() == this.playerNum)
        {
            Log.i("Smart AI", "Is turn");

            if (found == false)
            {
                for (Card m : gs.hand2)
                {
                    if (m.type == 'd' && m.color == 4)
                    {
                        Log.i("Smart AI", "Playing draw 4");
                        sleep(500);
                        UnoDraw4 pc = new UnoDraw4(this, m);
                        game.sendAction(pc);
                        found = true;
                        break;
                    }
                }//draw 4
            }

            if (found == false)
            {
                for (Card m : gs.hand2)
                {
                    if (gs.type == 'd' && (m.color == gs.color || m.type == gs.type))
                    {
                        Log.i("Smart AI", "Playing draw 2");
                        sleep(500);
                        UnoDraw2 pc = new UnoDraw2(this, m);
                        game.sendAction(pc);
                        found = true;
                        break;
                    }//draw 2
                }
            }

            if (found == false)
            {
                for (Card m : gs.hand2)
                {
                    if ((gs.type == 's' || gs.type == 'r') && (m.color == gs.color || m.type == gs.type))
                    {
                        Log.i("Smart AI", "Playing skip/reverse");
                        sleep(500);
                        UnoReverse rv = new UnoReverse(this, m);
                        game.sendAction(rv);
                        found = true;
                        break;
                    }//skip/reverse
                }
            }

            if (found == false)
            {
                for (Card m : gs.hand2)
                {
                    if (m.type == gs.type && m.value != gs.value)
                    {
                        Log.i("Smart AI", "Playing same value");
                        sleep(500);
                        if(m.type == 'n')
                        {
                            playCardAction pc = new playCardAction(this, m);
                            game.sendAction(pc);
                        }
                        if(m.type == 'r' || m.type == 's')
                        {
                            UnoReverse pc = new UnoReverse(this, m);
                            game.sendAction(pc);
                        }
                        found = true;
                        break;
                    }//same value
                }
            }

            if (found == false)
            {
                for (Card m : gs.hand2)
                {
                    if (m.color == gs.color)
                    {
                        Log.i("Smart AI", "Playing card of the same color");
                        sleep(500);
                        playCardAction pc = new playCardAction(this, m);
                        game.sendAction(pc);
                        found = true;
                        break;
                    }//same color
                }
            }

            if (found == false)
            {
                for (Card m : gs.hand2)
                {
                    if (m.type == 'w')
                    {
                        Log.i("Smart AI", "Playing wild card");
                        sleep(500);
                        playCardAction pc = new playCardAction(this, m);
                        game.sendAction(pc);
                        found = true;
                        break;
                    }//wild
                }
            }

            if (found == false)
            {
                Log.i("Smart AI", "No playable cards found, drawing card");
                sleep(100);
                UnoDrawAction ud = new UnoDrawAction(this);
                game.sendAction(ud);
            }

            //UNO declaration
            if (gs.hand2.size() == 1 || gs.hand1.size() == 1)
            {
                Log.i("Smart AI", "Declaring UNO");
                sleep(r.nextInt(500) + 500);
                UnoUnoAction uno = new UnoUnoAction(this);
                game.sendAction(uno);
            }
        } else
            {

            Log.i("Smart AI", "Not turn");

        }


    }//receiveInfo
}//UnoSmartPlayer