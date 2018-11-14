package com.example.marcinko21.uno;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;


/**
 * A SurfaceView which allows which an animation to be drawn on it by a
 * Animator.
 *
 * @author Meredith, Andrew, Ashley
 * @version 09 November 2018
 *
 *
 */
public class UnoSurfaceView extends SurfaceView implements View.OnTouchListener {

    public boolean onTouch(View v, MotionEvent event) {
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
    public UnoSurfaceView(Context context) {
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
    public UnoSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        init();
    }// ctor

    /**
     * Helper-method for the constructors
     */
    private void init() {
        setBackgroundColor(backgroundColor());
    }// init

    public int backgroundColor() {
        return Color.BLUE;
    }

    public void setState(UnoState state) {
        this.state = state;
    }

    @Override
    public void onDraw(Canvas canvas)
    {


        //R.drawable.green0
        //demo, fakes just to prove we were drawing them
        //reference
        Card c = new Card(0,0,'g','g', R.drawable.green0);
        c.draw(canvas, 150, 500, 200, 300, this);

        //R.drawable.green1
        Card a = new Card(0,1,'g','g', R.drawable.green1);
        a.draw(canvas, 300, 100, 125, 250, this);


        //todo
        //drawing all the pieces of the game

        // to draw player hand :
        //loop through player hand
        //call each card draw method and mathematically where should draw appropriately
        //in loop, have variable


        //to draw discard:
        //get discard from state and call that card's draw method


        //to draw pile:
        //draw back side of the uno card's image


        //to draw text showing number of cards in computer player's hand, OR
        //draw backside card for every card in computer player's hand

    }

}
