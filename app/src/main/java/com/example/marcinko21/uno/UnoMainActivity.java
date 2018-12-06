package com.example.marcinko21.uno;

import com.example.marcinko21.uno.game.GameConfig;
import com.example.marcinko21.uno.game.GameMainActivity;
import com.example.marcinko21.uno.game.GamePlayer;
import com.example.marcinko21.uno.game.config.GamePlayerType;
import com.example.marcinko21.uno.game.LocalGame;

import java.util.ArrayList;

/**
 * The Uno Main Activity Class
 *
 * @author Andrew, Meredith, Ashley
 * @date 9 November 2018
 */

public class UnoMainActivity extends GameMainActivity
{

    public static final int PORT_NUMBER = 5213;

    /**
     * an uno game for two players. The default is human vs. computer
     */
    @Override
    public GameConfig createDefaultConfig()
    {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Uno has two player types:  human and computer
        playerTypes.add(new GamePlayerType("Local Human Player")
        {
            public GamePlayer createPlayer(String name)
            {
                return new UnoHumanPlayer(name, R.layout.activity_uno_main);
            }
        });

        // dumb computer player
        playerTypes.add(new GamePlayerType("Computer Player (dumb)")
        {
            public GamePlayer createPlayer(String name)
            {
                return new UnoComputerPlayer(name);
            }
        });

        // smart computer player
        playerTypes.add(new GamePlayerType("Computer Player (smart)")
        {
            public GamePlayer createPlayer(String name)
            {
                return new UnoSmartPlayer(name);
            }
        });

        // Add the default players
        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 2, "Pig", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Dumb AI", 1); // player 2: a computer player
        defaultConfig.addPlayer("Smart AI", 2); // player 3: a smart computer player
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
    public LocalGame createLocalGame()
    {
        return new UnoLocalGame();
    }

}
