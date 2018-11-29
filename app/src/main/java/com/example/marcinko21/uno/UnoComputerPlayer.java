package com.example.marcinko21.uno;

import android.util.Log;

import com.example.marcinko21.uno.game.GameComputerPlayer;
import com.example.marcinko21.uno.game.infoMsg.GameInfo;

import java.util.Random;

/**
 * UnoComputerPlayer Class for Uno
 *
 * @author Andrew, Meredith, Ashley
 * @date 9 November 2018
 */
public class UnoComputerPlayer extends GameComputerPlayer {
    private Random r = new Random();

    public UnoComputerPlayer(String name){
            super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        Log.i("Dumb AI", "Receiving info");
        info.setGame(game);
        UnoState gs = (UnoState) info;
        int id = gs.player2Id;

        if(gs.hand1.size() == 1){
            int i = r.nextInt(9)+1;
            if(i < 5){
                Log.i("Dumb AI", "Did not declare UNO");
            }
            else{
                Log.i("Dumb AI", "Declaring UNO in 2 seconds");
                sleep(2000);
                gs.declareUno(id);
            }
        }

       if(gs.getTurn() == this.playerNum) {
            Log.i("Dumb AI", "Is turn");
            //Card Playing
                for (Card m : gs.hand2) {
                    if (m.type == 'd' && m.value == 4) {
                        int i = r.nextInt(4);
                        gs.color = i;
                        sleep(100);
                        Log.i("Dumb AI", "Playing card"+m.androidId);
                        gs.playCard(gs.hand2, m);
                        break;
                    }
                    if (m.type == 'w') {
                        int i = r.nextInt(4);
                        gs.color = i;
                        sleep(100);
                        Log.i("Dumb AI", "Playing card"+m.androidId);
                        gs.playCard(gs.hand2, m);
                        break;
                    }
                    if(m.color == gs.color){
                        sleep(100);
                        Log.i("Dumb AI", "Playing card"+m.androidId);
                        gs.playCard(gs.hand2,m);
                        break;
                    }
                    if(m.value == gs.value && gs.type == 'n'){
                        sleep(100);
                        Log.i("Dumb AI", "Playing card"+m.androidId);
                        gs.playCard(gs.hand2,m);
                        break;
                    }
                    if(m.type == gs.type && gs.type != 'n'){
                        sleep(100);
                        Log.i("Dumb AI", "Playing card"+m.androidId);
                        gs.playCard(gs.hand2,m);
                        break;
                    }
                }
            Log.i("Dumb AI", "Drawing card");
            gs.drawCard(gs.hand2);
        }
        return;
    }//receiveInfo()
}
