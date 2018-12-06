package com.example.marcinko21.uno;

import android.util.Log;

import com.example.marcinko21.uno.game.GameComputerPlayer;
import com.example.marcinko21.uno.game.infoMsg.GameInfo;

import java.util.ArrayList;
import java.util.Random;

public class UnoSmartPlayer extends GameComputerPlayer
{
    Random r = new Random();
    boolean legal = true;
    boolean found = true;

    public UnoSmartPlayer(String name)
    {
        super(name);
    }

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
            gs.declareUno(id);
        }

        if (gs.getTurn() == this.playerNum)
        {
            Log.i("Smart AI", "Is turn");
            //Card Playing
            Log.i("Smart AI", "Choosing card");
            legal = true;

            Log.i("Smart AI", "Determining legality of +4 cards");

            for(Card c : gs.hand2){
                if(c.color == gs.color || (c.type == 'n' && c.value == gs.value) || (c.type == gs.type && gs.type != 'n') || c.type == 'w'){
                    legal = false;
                }
            }

            Log.i("Smart AI", "Legality: " + legal);

            if (legal == true && found == false)
            {
                for (Card m : gs.hand2)
                {
                    if (m.type == 'd' && m.color == 4)
                    {
                        colorPick(gs.hand2);
                        sleep(500);
                        UnoDraw4 pc = new UnoDraw4(this, m);
                        game.sendAction(pc);
                        found = true;
                        break;
                    }
                }//draw 4 (legal)
            }
            else if (found == false)
            {
                for (Card m : gs.hand2)
                {
                    if (gs.type == 'd' || m.color == gs.color)
                    {
                        sleep(500);
                        UnoDraw2 pc = new UnoDraw2(this, m);
                        game.sendAction(pc);
                        found = true;
                        break;
                    }//draw 2
                }
            }
            else if (found == false)
            {
                for (Card m : gs.hand2)
                {
                    if (m.type == 'd' && m.value == 4)
                    {
                        colorPick(gs.hand2);
                        sleep(500);
                        UnoDraw4 pc = new UnoDraw4(this, m);
                        game.sendAction(pc);
                        found = true;
                        break;
                    }//draw 4 (illegal)
                }
            }
            else if (found == false)
            {
                for (Card m : gs.hand2)
                {
                    if ((gs.type == 's' || gs.type == 'r') && m.type == gs.type)
                    {
                        sleep(500);
                        UnoReverse rv = new UnoReverse(this, m);
                        game.sendAction(rv);
                        found = true;
                        break;
                    }//skip/reverse
                }
            }
            else if (found == false)
            {
                for (Card m : gs.hand2)
                {
                    if (m.value == gs.value && m.color != gs.color)
                    {
                        sleep(500);
                        playCardAction pc = new playCardAction(this, m);
                        game.sendAction(pc);
                        found = true;
                        break;
                    }//same number
                }
            }
            else if (found == false)
            {
                for (Card m : gs.hand2)
                {
                    if (m.color == gs.color)
                    {
                        sleep(500);
                        playCardAction pc = new playCardAction(this, m);
                        game.sendAction(pc);
                        found = true;
                        break;
                    }//same color
                }
            }
            else if (found == false)
            {
                for (Card m : gs.hand2)
                {
                    if (m.type == 'w')
                    {
                        colorPick(gs.hand2);
                        sleep(500);
                        playCardAction pc = new playCardAction(this, m);
                        game.sendAction(pc);
                        found = true;
                        break;
                    }//wild
                }
            }

            //UNO declaration
            if (gs.hand2.size() == 1 || gs.hand1.size() == 1)
            {
                Log.i("Smart AI", "Declaring UNO");
                sleep(r.nextInt(500) + 500);
                UnoUnoAction uno = new UnoUnoAction(this);
                game.sendAction(uno);
            }

            if (found == false)
            {
                Log.i("Smart AI", "No playable cards found, drawing card");
                sleep(100);
                UnoDrawAction ud = new UnoDrawAction(this);
                game.sendAction(ud);
            }
        }
    }

    private int colorPick(ArrayList<Card> hand)
    {
        int colors[] = new int[4];
        int size = 0;
        int out = 0;
        for(Card m:hand)
        {
            if(m.color == 0) colors[0]++;
            else if(m.color == 1) colors[1]++;
            else if(m.color == 2) colors[2]++;
            else if(m.color == 3) colors[3]++;
        }
        for(int i = 0; i < colors.length; i++)
        {
            if(colors[i] > size)
            {
                out = i;
            }
        }
        Log.i("Smart AI", "Color: "+out);
        return out;
    }
}