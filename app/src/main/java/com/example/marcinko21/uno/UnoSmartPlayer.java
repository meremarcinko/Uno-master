/*package com.example.marcinko21.uno;

import android.util.Log;

import com.example.marcinko21.uno.game.GameComputerPlayer;
import com.example.marcinko21.uno.game.infoMsg.GameInfo;

import java.util.ArrayList;
import java.util.Random;

public class UnoSmartPlayer extends GameComputerPlayer {
    Random r = new Random();

    public UnoSmartPlayer(String name){
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        Log.i("Smart AI", "Receiving info");
        info.setGame(game);
        UnoState gs = (UnoState) info;
        int id = gs.player2Id;

        if(gs.getTurn() == this.playerNum) {
            Log.i("Smart AI", "Is turn");

            //UNO declaration
            if(gs.hand2.size() == 1 || gs.hand1.size() == 1){
                Log.i("Dumb AI", "Declaring UNO in 2 seconds");
                sleep(r.nextInt(500)+500);
                gs.declareUno(id);
            }

            //Card Playing
            else{
                ArrayList<Card> c = new ArrayList<>(0);
                sleep(100);
                Card dTop = gs.discardPile[0];
                Log.i("Smart AI", "Choosing card");
                for (int i = 0; i < gs.hand2.size(); i++) {
                    if(gs.hand2.get(i).type == 'd' && gs.hand2.get(i).color == 4){
                        c.add(gs.hand2.get(i));
                        for (int j = i+1; j < gs.hand2.size(); j++) {
                            if(gs.hand2.get(i).type == 'd' && gs.hand2.get(i).color == 4) {
                                c.add(gs.hand2.get(i));
                            }
                            break;
                        }
                    } //draw 4 wild

                    else if( if (gs.hand2.get(i).type == 'd' && gs.hand2.get(i).color == 4 ){
                        c.add(gs.hand2.get(i));)
                }

            }
        }
        else {
            Log.i("Dumb AI", "Is not turn");
            return;
        }

        return;
    }
}
*/