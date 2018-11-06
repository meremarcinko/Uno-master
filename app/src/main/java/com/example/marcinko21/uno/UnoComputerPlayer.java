package com.example.marcinko21.uno;

import android.util.Log;

import com.example.marcinko21.uno.game.GameComputerPlayer;
import com.example.marcinko21.uno.game.infoMsg.GameInfo;

import java.util.Random;

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

       if(gs.getTurn() == this.playerNum) {
            Log.i("Dumb AI", "Is turn");

            //UNO declaration
            if(gs.hand2.size() == 1 || gs.hand1.size() == 1){
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

            //Card Playing
            else{
                sleep(100);
                Log.i("Dumb AI", "Playing card"+gs.getHand(id).get(0));
                gs.playCard(id, gs.hand2.get(0)); //Plays first card in hand
            }
        }
        else {
           Log.i("Dumb AI", "Is not turn");
           return;
       }

        return;
    }//receiveInfo()
}
