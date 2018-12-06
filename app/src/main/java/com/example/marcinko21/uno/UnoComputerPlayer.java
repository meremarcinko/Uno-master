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
    private boolean found = false;

    public UnoComputerPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        found = false;
        if (info instanceof UnoState) {
            Log.i("Dumb AI", "Receiving info");
            info.setGame(game);
            UnoState gs = (UnoState) info;

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
                    if(found == false) {
                        if (m.type == 'd' && m.color == 4) {
                            Log.i("Dumb AI", "Playing draw 4");
                            sleep(500);
                            UnoDraw4 pc = new UnoDraw4(this, m);
                            game.sendAction(pc);
                            found = true;
                            break;
                        }
                        if (gs.type == 'd' && (m.color == gs.color || m.type == gs.type)) {
                            Log.i("Dumb AI", "Playing draw 2");
                            sleep(500);
                            UnoDraw2 pc = new UnoDraw2(this, m);
                            game.sendAction(pc);
                            found = true;
                            break;
                        }//draw 2
                        if ((gs.type == 's' || gs.type == 'r') && (m.color == gs.color || m.type == gs.type)) {
                            Log.i("Dumb AI", "Playing skip/reverse");
                            sleep(500);
                            UnoReverse rv = new UnoReverse(this, m);
                            game.sendAction(rv);
                            found = true;
                            break;
                        }//skip/reverse
                        if (m.type == gs.type && m.value != gs.value) {
                            Log.i("Dumb AI", "Playing same value");
                            sleep(500);
                            if(m.type == 'n'){
                                playCardAction pc = new playCardAction(this, m);
                                game.sendAction(pc);
                            }
                            if(m.type == 'r' || m.type == 's'){
                                UnoReverse pc = new UnoReverse(this, m);
                                game.sendAction(pc);
                            }
                            found = true;
                            break;
                        }//same value
                        if (m.color == gs.color && m.type != 'd' && m.type != 'r' && m.type != 's') {
                            Log.i("Dumb AI", "Playing card of the same color");
                            sleep(500);
                            playCardAction pc = new playCardAction(this, m);
                            game.sendAction(pc);
                            found = true;
                            break;
                        }//same color
                        if (m.type == 'w') {
                            Log.i("Dumb AI", "Playing wild card");
                            sleep(500);
                            playCardAction pc = new playCardAction(this, m);
                            game.sendAction(pc);
                            found = true;
                            break;
                        }//wild
                    }
                }
                if(found == false) {
                    UnoDrawAction ud = new UnoDrawAction(this);
                    sleep(500);
                    game.sendAction(ud);
                }
            }
            else {
                Log.i("Dumb AI", "Not turn");
            }
        }
    }//receiveInfo()
}
