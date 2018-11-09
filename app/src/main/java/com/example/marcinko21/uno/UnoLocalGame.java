package com.example.marcinko21.uno;

import com.example.marcinko21.uno.game.GamePlayer;
import com.example.marcinko21.uno.game.LocalGame;

//- return the new uno local game state
//check if game is over

public class UnoLocalGame extends LocalGame{

    protected UnoState state;

    /**
     *  Constructor for UnoLocalGame
     */
    public UnoLocalGame() {

        //perform superclass initialization
        super();

        //create a new, unfilled in UnoState object
        state = new UnoState();
    }
    
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new UnoState(state));
    }


}

