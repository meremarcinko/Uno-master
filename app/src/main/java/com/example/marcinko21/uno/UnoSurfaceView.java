package com.example.marcinko21.uno;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import java.lang.Math;


/**
 * A SurfaceView which allows which an animation to be drawn on it by a
 * Animator.
 *
 * @author Meredith, Andrew, Ashley
 * @version 09 November 2018
 *
 *
 */
public class UnoSurfaceView extends SurfaceView implements View.OnTouchListener
{

    public boolean onTouch(View v, MotionEvent event)
    {

        //locate card tapped
        //compare x,y to the rectangle where each card is drawn

        final double RATIO = 1.2;
        //for the human player's hand
        int y = 3*(this.getHeight())/4;
        //card width
        int xSize = (this.getWidth())/Math.max(7, state.getHandSize(state.getHand(0)));//how many cards are in the hand
        //card height
        int ySize = (int)(xSize*RATIO);
        for(int i=0; i < state.getHandSize(state.getHand(0)); i++)
        {
            Card c = state.getHand(0).get(i);
            //c.draw(canvas, xSize*i, y, xSize, ySize, this, false);
            //check rect
        }

        return true;
    }

    /**
     * Instance Variables
     */
    //the game's state
    protected UnoState state = new UnoState();

    /**
     * Constructor for the UnoSurfaceView class.
     *
     * @param context - a reference to the activity this animation is run under
     */
    public UnoSurfaceView(Context context)
    {
        super(context);
        setState(state);
        init();

    }//ctor


    /**
     * An alternate constructor for use when a subclass is directly specified
     * in the layout.
     *
     * @param context - a reference to the activity this animation is run under
     * @param attrs   - set of attributes passed from system
     */
    public UnoSurfaceView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setWillNotDraw(false);
        init();
    }//ctor

    /**
     * Helper-method for the constructors
     */
    private void init()
    {
        setBackgroundColor(backgroundColor());
    }// init

    public int backgroundColor()
    {
        return Color.BLUE;
    }

    public void setState(UnoState state)
    {
        this.state = state;
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        final double RATIO = 1.2;
        //for the human player's hand
        int y = 3*(this.getHeight())/4;
        //card width
        int xSize = (this.getWidth())/Math.max(7, state.getHandSize(state.getHand(0)));//how many cards are in the hand
        //card height
        int ySize = (int)(xSize*RATIO);
        for(int i=0; i < state.getHandSize(state.getHand(0)); i++)
        {
            Card c = state.getHand(0).get(i);
            c.draw(canvas, xSize*i, y, xSize, ySize, this, false);
        }


        Log.i("onDraw ", "handSizeIs "+state.getHandSize(state.getHand(0)));
        //drawing all the pieces of the game

        //to render the discard: not the action to draw
        //get discard from state and call that card's draw method
        int last = state.getDiscardPile().size()-1;

        //drawing on the surface view the discard pile
        Card discard = state.getDiscardPile().get(last);
        discard.draw(canvas, (int)(.25*this.getWidth()), (int)(.5*this.getHeight()), (int)(.1*this.getWidth()), (int)(RATIO*(int)(.1*this.getWidth())), this, false);

        //to draw pile:
        //drawing on the surface view the draw pile
        Card drawPile = state.getDeck().get(0);
        drawPile.draw(canvas, (int)(.75*this.getWidth()), (int)(.5*this.getHeight()), (int)(.1*this.getWidth()), (int)(RATIO*(int)(.1*this.getWidth())), this, true);
        //draw back side of the uno card's image


        //to draw text showing number of cards in computer player's hand, OR
        //draw backside card for every card in computer player's hand

        //For the computer's hand
        xSize = (this.getWidth())/Math.max(7, state.getHandSize(state.getHand(1)));//how many cards are in the hand
        y = 0;
        ySize = (int)(xSize*RATIO);
        for(int i=0; i < state.getHandSize(state.getHand(1)); i++)
        {
            Card c = state.getHand(0).get(i);
            c.draw(canvas, xSize * i, y, xSize, ySize, this, true);
        }
    }

}
