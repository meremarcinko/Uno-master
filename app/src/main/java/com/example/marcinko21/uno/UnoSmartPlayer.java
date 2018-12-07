package com.example.marcinko21.uno;

import android.util.Log;

import com.example.marcinko21.uno.game.GameComputerPlayer;
import com.example.marcinko21.uno.game.infoMsg.GameInfo;
import com.example.marcinko21.uno.game.infoMsg.NotYourTurnInfo;

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
    public UnoSmartPlayer(String name)
    {
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
        if (!(info instanceof UnoState))
        {
            return;
        }
        info.setGame(game);
        UnoState gs = (UnoState) info;
        found = false;

        //UNO check
        if (gs.hand2.size() == 1 || gs.hand1.size() == 1)
        {
            sleep(300);
            if (gs.playerUno != -1 || gs.playerDeclaredUno)
            {
                Log.i("Smart AI", "Did not declare UNO");
            } else
            {
                Log.i("Smart AI", "Declaring UNO in variable seconds");
                sleep(r.nextInt(500) + 500);
                UnoUnoAction uno = new UnoUnoAction(this);
                game.sendAction(uno);
            }
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
                        Log.i("Dumb AI", "Playing draw 4");
                        sleep(500);
                        UnoDraw4 pc = new UnoDraw4(this, m);
                        game.sendAction(pc);
                        found = true;
                        break;
                    }//draw 4
                }//draw 4
            }

            if (found == false)
            {
                for (Card m : gs.hand2)
                {
                    if (m.type == 'd' && (m.color == gs.color || m.type == gs.type))
                    {
                        Log.i("Dumb AI", "Playing draw 2");
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
                    if ((m.type == 's' || m.type == 'r') && (m.color == gs.color || m.type == gs.type))
                    {
                        Log.i("Dumb AI", "Playing skip/reverse");
                        sleep(500);
                        UnoReverse rv = new UnoReverse(this, m);
                        game.sendAction(rv);
                        sleep(500);
                        found = true;
                        break;
                    }//skip/reverse
                }
            }

            if (found == false)
            {
                for (Card m : gs.hand2)
                {
                    if (m.value == gs.value && m.type == 'n' && m.type == gs.type)
                    {
                        Log.i("Dumb AI", "Playing number card of the same value");
                        sleep(500);
                        if (m.type == 'n')
                        {
                            playCardAction pc = new playCardAction(this, m);
                            game.sendAction(pc);
                        }
                        sleep(500);
                        found = true;
                        break;
                    }//same value number
                }
            }

            if (found == false)
            {
                for (Card m : gs.hand2)
                {
                    if (m.color == gs.color && m.type == 'n')
                    {
                        Log.i("Dumb AI", "Playing number card of the same color");
                        sleep(500);
                        playCardAction pc = new playCardAction(this, m);
                        game.sendAction(pc);
                        found = true;
                        break;
                    }//same color number
                }
            }

            if (found == false)
            {
                for (Card m : gs.hand2)
                {
                    if (m.type == 'w')
                    {
                        Log.i("Dumb AI", "Playing wild card");
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
                sleep(500);
                UnoDrawAction ud = new UnoDrawAction(this);
                game.sendAction(ud);
            }
        }
        else
        {
            Log.i("Smart AI", "Not turn");
        }
    }//receiveInfo
}//UnoSmartPlayer