package com.example.marcinko21.uno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.marcinko21.uno.game.GameConfig;
import com.example.marcinko21.uno.game.GameMainActivity;
import com.example.marcinko21.uno.game.GamePlayer;
import com.example.marcinko21.uno.game.GamePlayerType;
import com.example.marcinko21.uno.game.LocalGame;

import java.util.ArrayList;

public class UnoMainActivity extends GameMainActivity {

    public static final int PORT_NUMBER = 5213;

    /**
     * an uno game for two players. The default is human vs. computer
     */
    @Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // yellow-on-blue GUI
        playerTypes.add(new GamePlayerType("Local Human Player (blue-yellow)") {
            public GamePlayer createPlayer(String name) {
                return new UnoHumanPlayer(name, R.layout.ttt_human_player1);
            }
        });


        // dumb computer player
        playerTypes.add(new GamePlayerType("Computer Player (dumb)") {
            public GamePlayer createPlayer(String name) {
                return new UnoComputerPlayer(name);
            }
        });


        // Create a game configuration class for Tic-tac-toe
        GameConfig defaultConfig = new GameConfig(playerTypes, 2,2, "Tic-Tac-Toe", PORT_NUMBER);

        // Add the default players
        defaultConfig.addPlayer("Human", 0); // yellow-on-blue GUI
        defaultConfig.addPlayer("Computer", 3); // dumb computer player

        // Set the initial information for the remote player
        defaultConfig.setRemoteData("Remote Player", "", 1); // red-on-yellow GUI

        //done!
        return defaultConfig;

    }//createDefaultConfig


    /**
     * createLocalGame
     *
     * Creates a new game that runs on the server tablet,
     *
     * @return a new, game-specific instance of a sub-class of the LocalGame
     *         class.
     */
    @Override
    public LocalGame createLocalGame() {
        return new TTTLocalGame();
    }

}
