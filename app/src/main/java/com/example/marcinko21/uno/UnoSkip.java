package com.example.marcinko21.uno;

import com.example.marcinko21.uno.game.GamePlayer;
import com.example.marcinko21.uno.game.actionMsg.GameAction;
/**
 *  An action by which the player tells the game that it is a skip card.
 *
 *
 * @author Meredith, Andrew, Ashley
 *  @version 09 November 2018
 */
public class UnoSkip extends GameAction
{
     /** An action by which the player tells the game that it wants to draw.
            * (typically the human's name, if it's a GameHumanPlayer).
            *
            * @author Meredith, Andrew, Ashley
            *  @version 09 November 2018
        */
        private Card skipCard;
        /** constructor
         *
         * @param p
         * 		the player who sent the action
         */
        //went into office hours and received help on this class
        public UnoSkip(GamePlayer p, Card skipCard)
        {
            super(p);
            this.skipCard = skipCard;
        }

        public Card setSkipCard(Card skipCard)
        {
            return skipCard;
        }

        public Card getSkipCard()
        {
            return skipCard;
        }

    }

