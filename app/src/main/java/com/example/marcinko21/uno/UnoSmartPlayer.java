package com.example.marcinko21.uno;

import android.util.Log;

import com.example.marcinko21.uno.game.GameComputerPlayer;
import com.example.marcinko21.uno.game.infoMsg.GameInfo;

import java.util.ArrayList;
import java.util.Random;

public class UnoSmartPlayer extends GameComputerPlayer {
    Random r = new Random();
    boolean legal = true;
    boolean found = false;

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
                Card dTop = gs.getDiscardPile().get(0);
                Log.i("Smart AI", "Choosing card");
                legal = true;
                Log.i("Smart AI", "Determining legality of +4 cards");
                for (int i = 0; i < gs.hand2.size(); i++) {
                    if(gs.hand2.get(i-1).color == gs.color ){ //matching colors
                        legal = false;
                    }
                    else if(gs.hand2.get(i).color != gs.color) {
                        if(gs.hand2.get(i).type == gs.type && gs.type != 'n'){ //not a number card and matching types
                            legal = false;
                        }
                        else if(gs.hand2.get(i).type == gs.type && gs.type == 'n'){ //is a number card
                            if(gs.hand2.get(i).value == gs.value){ //same value
                                legal = false;
                            }
                        }
                    }
                    else if(gs.hand2.get(i).type == 'w'){ //wild card in hand
                        legal = false;
                    }
                }
                Log.i("Smart AI", "Legality: "+legal);
                found = false;
                while(found == false) {
                    if (legal == true) {
                        for (Card m : gs.hand2) {
                            if (m.type == 'd' && m.value == 4) {
                                //decide color
                                //playAction
                                found = true;
                                break;
                            }
                        }//draw 4 (legal)
                    }
                    else {
                        for(Card m : gs.hand2) {
                            if (gs.type == 'd' || m.color == gs.color) {
                                //playAction
                                found = true;
                                break;
                            }//draw 2
                        }
                        for(Card m : gs.hand2) {
                            if (m.type == 'd' && m.value == 4) {
                                //decide color
                                //playAction
                                found = true;
                                break;
                            }//draw 4 (illegal)
                        }
                        for(Card m : gs.hand2) {
                            if (gs.type == 's' || gs.type == 'r' || m.color == gs.color) {
                                //playAction
                                found = true;
                                break;
                            }//skip/reverse
                        }
                        for(Card m : gs.hand2) {
                            if (m.value == gs.value && m.color != gs.color) {
                                //playAction
                                found = true;
                                break;
                            }//same number
                        }
                        for(Card m : gs.hand2) {
                            if (m.color == gs.color) {
                                //playAction
                                found = true;
                                break;
                            }//same color
                        }
                        //drawAction
                        found = true;
                        break;
                    }
                }
            }
        }
        else {
            Log.i("Smart AI", "Is not turn");
            return;
        }

        return;
    }
}
