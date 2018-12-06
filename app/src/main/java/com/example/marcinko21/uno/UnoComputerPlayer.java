package com.example.marcinko21.uno;

import android.util.Log;

import com.example.marcinko21.uno.game.GameComputerPlayer;
import com.example.marcinko21.uno.game.infoMsg.GameInfo;

import java.util.ArrayList;
import java.util.Random;

/**
 * UnoComputerPlayer Class for Uno
 * This is where the Computer player is implemented
 *
 * @author Andrew, Meredith, Ashley
 * @date 9 November 2018
 */
public class UnoComputerPlayer extends GameComputerPlayer {
    private Random r = new Random();

    public UnoComputerPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if (info instanceof UnoState) {
            Log.i("Dumb AI", "Receiving info");
            info.setGame(game);
            UnoState gs = (UnoState) info;
            int id = gs.player2Id;

            if (gs.hand1.size() == 1 || gs.hand2.size() == 1) {
                int i = r.nextInt(9) + 1;
                if (i < 5) {
                    Log.i("Dumb AI", "Did not declare UNO");
                } else {
                    Log.i("Dumb AI", "Declaring UNO in 2 seconds");
                    sleep(2000);
                    UnoUnoAction uno = new UnoUnoAction(this);
                    game.sendAction(uno);
                }
            }
            Log.i("Dumb AI", "This playerNum is:" + this.playerNum + " turn is:" + gs.getTurn());
            if (gs.getTurn() == this.playerNum) {
                Log.i("Dumb AI", "Is turn");
                //Card Playing
                for (Card m : gs.hand2) {
                    sleep(500);
                    Log.i("Dumb AI", "Playing card" + m.androidId);
                    playCardAction pc = new playCardAction(this, m);
                    game.sendAction(pc);
                }
                UnoDrawAction ud = new UnoDrawAction(this);
                game.sendAction(ud);
                if (gs.hand1.size() == 1 || gs.hand2.size() == 1) {
                    int i = r.nextInt(9) + 1;
                    if (i < 5) {
                        Log.i("Dumb AI", "Did not declare UNO");
                    } else {
                        Log.i("Dumb AI", "Declaring UNO in 2 seconds");
                        sleep(2000);
                        UnoUnoAction uno = new UnoUnoAction(this);
                        game.sendAction(uno);
                    }
                }
            } else {
                Log.i("Dumb AI", "Not turn");
            }
        }
    }//receiveInfo()

    private int colorPick(ArrayList<Card> hand) {
        int colors[] = new int[4];
        int size = 0;
        int out = 0;
        for (Card m : hand) {
            if (m.color == 0) colors[0]++; //g
            else if (m.color == 1) colors[1]++; //b
            else if (m.color == 2) colors[2]++; //r
            else if (m.color == 3) colors[3]++; //y
        }
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] > size) {
                out = i;
            }
        }
        Log.i("Dumb AI", "Color: " + out);
        return out;
    }
}
