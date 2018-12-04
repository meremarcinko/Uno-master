package com.example.marcinko21.uno;

import android.util.Log;

import com.example.marcinko21.uno.game.GameComputerPlayer;
import com.example.marcinko21.uno.game.infoMsg.GameInfo;

import java.util.ArrayList;
import java.util.Random;

/**
 * UnoComputerPlayer Class for Uno
 *
 * @author Andrew, Meredith, Ashley
 * @date 9 November 2018
 */
public class UnoComputerPlayer extends GameComputerPlayer
{
    private Random r = new Random();

    public UnoComputerPlayer(String name)
    {
            super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info)
    {
        Log.i("Dumb AI", "Receiving info");
        info.setGame(game);
        UnoState gs = (UnoState) info;
        int id = gs.player2Id;

        if(gs.hand1.size() == 1)
        {
            int i = r.nextInt(9)+1;
            if(i < 5)
            {
                Log.i("Dumb AI", "Did not declare UNO");
            }
            else
            {
                Log.i("Dumb AI", "Declaring UNO in 2 seconds");
                sleep(2000);
                gs.declareUno(id);
            }
        }

       if(gs.getTurn() == this.playerNum)
       {
            Log.i("Dumb AI", "Is turn");
            //Card Playing
                for (Card m : gs.hand2)
                {
                    if (m.type == 'd' && m.value == 4)
                    {
                        int i = r.nextInt(4);
                        gs.color = i;
                        sleep(100);
                        Log.i("Dumb AI", "Playing card"+m.androidId);
                        playCardAction pc = new playCardAction(this, m);
                        game.sendAction(pc);
                        break;
                    }
                    if (m.type == 'w')
                    {
                        int i = r.nextInt(4);
                        gs.color = i;
                        sleep(100);
                        Log.i("Dumb AI", "Playing card"+m.androidId);
                        m.color = colorPick(gs.hand2);
                        playCardAction pc = new playCardAction(this, m);
                        game.sendAction(pc);
                        break;
                    }
                    if(m.color == gs.color)
                    {
                        sleep(100);
                        Log.i("Dumb AI", "Playing card"+m.androidId);
                        playCardAction pc = new playCardAction(this, m);
                        game.sendAction(pc);
                        break;
                    }
                    if(m.value == gs.value && gs.type == 'n')
                    {
                        sleep(100);
                        Log.i("Dumb AI", "Playing card"+m.androidId);
                        playCardAction pc = new playCardAction(this, m);
                        game.sendAction(pc);
                        break;
                    }
                    if(m.type == gs.type && gs.type != 'n')
                    {
                        sleep(100);
                        Log.i("Dumb AI", "Playing card"+m.androidId);
                        playCardAction pc = new playCardAction(this, m);
                        game.sendAction(pc);
                        break;
                    }
                }
            Log.i("Dumb AI", "Drawing card");
            gs.drawCard(gs.hand2);
        }
        return;
    }//receiveInfo()

    private int colorPick(ArrayList<Card> hand){
        int colors[] = new int[4];
        int size = 0;
        int out = 0;
        for(Card m:hand){
            if(m.color == 0) colors[0]++; //g
            else if(m.color == 1) colors[1]++; //b
            else if(m.color == 2) colors[2]++; //r
            else if(m.color == 3) colors[3]++; //y
        }
        for(int i = 0; i < colors.length; i++){
            if(colors[i] > size){
                out = i;
            }
        }
        return out;
    }
}
