package com.example.marcinko21.uno;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Card Class for Uno
 *
 * @author Andrew, Meredith, Ashley
 * @date 9 November 2018
 */
public class Card
{

    /**
     * Initialize Variables
     */
    //two hand. One card in one hand. Two cards in the other hand. Have one card be played and show
    //that it was added to the discard pile. The discard pile has one more card and the hand now has
    //one less card
    int value;
    char type;
    int color;
    int androidId;

    /**
     * Constructor for Card Class
     *
     */
    public Card()
    {
        value = 0;
        type = ' ';
        color = 0;
    }//ctor

    /**
     *  Deep constructor
     *
     * @param c
     * @param v
     * @param t
     * @param aId
     */
    public Card(int c, int v, char t, int aId)
    {
        color = c;
        value = v;
        type = t;
        androidId = aId;
    }


    /**
     * Clone method to copy and return new card for deep constructor
     *
     * @return copy of Card class
     */
    public Card clone()
    {

        return new Card(color, value, type, androidId);
    }//clone

    /**
     *  Set and Get Methods
     *
     */
    //getter and setter methods
    public int getAndro()
    {
        return androidId;
    }

    public int getValue()
    {
        return value;
    }

    public char getType()
    {
        return type;
    }

    public int getColor()
    {
        return color;
    }

    public void setValue(int v)
    {
        this.value = v;
    }

    public void setType(char t)
    {
        this.type = t;
    }

    public void setColor(int c)
    {
        this.color = c;
    }

    public boolean equals(Object o)
    {
        //went into office hours and got help with this method
        if(o instanceof Card)
        {
            Card c = (Card)o;
            if(this.color == c.color && this.type == c.type && this.value == c.value)
            {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Method to draw card
     *
     * @param canvas
     * @param x
     * @param y
     * @param xSize
     * @param ySize
     * @param unoView
     *
     * @return card
     */
    public void draw(Canvas canvas, int x, int y, int xSize, int ySize, SurfaceView unoView, boolean isHidden)
    {
        if(!isHidden)
        {
            //Bitmap cardGreen0 = BitmapFactory.decodeResource(unoView.getResources(), R.drawable.green0);
            Bitmap cardGreen0 = BitmapFactory.decodeResource(unoView.getResources(), androidId);
            cardGreen0 = Bitmap.createScaledBitmap(cardGreen0, xSize, ySize, false);
            canvas.drawBitmap(cardGreen0, x, y, null);
        }
        else
        {
            Bitmap cardUno = BitmapFactory.decodeResource(unoView.getResources(),R.drawable.uno_logo_card);
            cardUno= Bitmap.createScaledBitmap(cardUno,xSize,ySize,false);
            canvas.drawBitmap(cardUno, x,y,null);
        }
    }


}