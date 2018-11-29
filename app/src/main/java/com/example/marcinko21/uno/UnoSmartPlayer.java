package com.example.marcinko21.uno;

import android.util.Log;

import com.example.marcinko21.uno.game.GameComputerPlayer;
import com.example.marcinko21.uno.game.infoMsg.GameInfo;

import java.util.ArrayList;
import java.util.Random;

public class UnoSmartPlayer extends GameComputerPlayer {
    Random r = new Random();
    boolean legal = true;

    public UnoSmartPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        Log.i("Smart AI", "Receiving info");
        info.setGame(game);
        UnoState gs = (UnoState) info;
        int id = gs.player2Id;
        boolean found = false;

        //UNO check
        if (gs.hand2.size() == 1 || gs.hand1.size() == 1) {
            Log.i("Smart AI", "Declaring UNO");
            sleep(r.nextInt(500) + 500);
            gs.declareUno(id);
        }

        if (gs.getTurn() == this.playerNum) {
            Log.i("Smart AI", "Is turn");
            //Card Playing
            Log.i("Smart AI", "Choosing card");
            legal = true;

            Log.i("Smart AI", "Determining legality of +4 cards");
            for (int i = 0; i < gs.hand2.size(); i++) {
                if (gs.hand2.get(i - 1).color == gs.color) { //matching colors
                    legal = false;
                } else if (gs.hand2.get(i).color != gs.color) {
                    if (gs.hand2.get(i).type == gs.type && gs.type != 'n') { //not a number card and matching types
                        legal = false;
                    } else if (gs.hand2.get(i).type == gs.type && gs.type == 'n') { //is a number card
                        if (gs.hand2.get(i).value == gs.value) { //same value
                            legal = false;
                        }
                    }
                } else if (gs.hand2.get(i).type == 'w') { //wild card in hand
                    legal = false;
                }
            }

            Log.i("Smart AI", "Legality: " + legal);

            if (legal == true && found == false) {
                for (Card m : gs.hand2) {
                    if (m.type == 'd' && m.color == 4) {
                        colorPick(gs.hand2);
                        sleep(100);
                        gs.playCard(gs.hand2, m);
                        found = true;
                        break;
                    }
                }//draw 4 (legal)
            }

            if (found == false) {
                for (Card m : gs.hand2) {
                    if (gs.type == 'd' || m.color == gs.color) {
                        sleep(100);
                        gs.playCard(gs.hand2, m);
                        found = true;
                        break;
                    }//draw 2
                }
            }
            if (found == false) {
                for (Card m : gs.hand2) {
                    if (m.type == 'd' && m.value == 4) {
                        colorPick(gs.hand2);
                        sleep(100);
                        gs.playCard(gs.hand2, m);
                        found = true;
                        break;
                    }//draw 4 (illegal)
                }
            }
            if (found == false) {
                for (Card m : gs.hand2) {
                    if ((gs.type == 's' || gs.type == 'r') && m.type == gs.type) {
                        sleep(100);
                        gs.playCard(gs.hand2, m);
                        found = true;
                        break;
                    }//skip/reverse
                }
            }
            if (found == false) {
                for (Card m : gs.hand2) {
                    if (m.value == gs.value && m.color != gs.color) {
                        sleep(100);
                        gs.playCard(gs.hand2, m);
                        found = true;
                        break;
                    }//same number
                }
            }
            if (found == false) {
                for (Card m : gs.hand2) {
                    if (m.color == gs.color) {
                        sleep(100);
                        gs.playCard(gs.hand2, m);
                        found = true;
                        break;
                    }//same color
                }
            }
            if (found == false) {
                for (Card m : gs.hand2) {
                    if (m.type == 'w') {
                        colorPick(gs.hand2);
                        sleep(100);
                        gs.playCard(gs.hand2, m);
                        found = true;
                        break;
                    }//wild
                }
            }

            //UNO declaration
            if (gs.hand2.size() == 1 || gs.hand1.size() == 1) {
                Log.i("Smart AI", "Declaring UNO");
                sleep(r.nextInt(500) + 500);
                gs.declareUno(id);
            }

            if (found == false) {
                Log.i("Smart AI", "No playable cards found, drawing card");
                sleep(100);
                gs.drawCard(gs.hand2);
            }
        }
    }

    private int colorPick(ArrayList<Card> hand){
        int colors[] = new int[4];
        int size = 0;
        int out = 0;
        for(Card m:hand){
            if(m.color == 0) colors[0]++;
            else if(m.color == 1) colors[1]++;
            else if(m.color == 2) colors[2]++;
            else if(m.color == 3) colors[3]++;
        }
        for(int i = 0; i < colors.length; i++){
            if(colors[i] > size){
                out = i;
            }
        }
        return out;
    }
}