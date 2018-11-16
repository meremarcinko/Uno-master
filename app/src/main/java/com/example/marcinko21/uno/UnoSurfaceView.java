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
        Card g0 = new Card(0,0,'g','g', R.drawable.green0);
        g0.draw(canvas, 350, 1100, 200, 300, this);

        //R.drawable.green1
        Card g1 = new Card(0,1,'g','g', R.drawable.green1);
        g1.draw(canvas, 650, 1100, 200, 300, this);

        //R.drawable.green2
        Card g2 = new Card(0,1,'g','g', R.drawable.green2);
        g2.draw(canvas, 950, 1100, 200, 300, this);

        //R.drawable.green3
        Card g3 = new Card(0,1,'g','g', R.drawable.green3);
        g3.draw(canvas, 1250, 1100, 200, 300, this);

        //R.drawable.green4
        Card g4 = new Card(0,1,'g','g', R.drawable.green4);
        g4.draw(canvas, 1550, 1100, 200, 300, this);

        //R.drawable.green5
        Card g5 = new Card(0,1,'g','g', R.drawable.green5);
        g5.draw(canvas, 1850, 1100, 200, 300, this);

        //R.drawable.green6
        Card g6 = new Card(0,1,'g','g', R.drawable.green6);
        g6.draw(canvas, 2150, 1100, 200, 300, this);

        //R.drawable.green7
        Card g7 = new Card(0,1,'g','g', R.drawable.green7);
        g7.draw(canvas, 350, 300, 200, 300, this);

        //R.drawable.green8
        Card g8 = new Card(0,1,'g','g', R.drawable.green8);
        g8.draw(canvas, 650, 300, 200, 300, this);

        //R.drawable.green9
        Card g9 = new Card(0,1,'g','g', R.drawable.green9);
        g9.draw(canvas, 950, 300, 200, 300, this);

        //R.drawable.green_wild
        Card gw = new Card(0,1,'g','g', R.drawable.green_wild);
        gw.draw(canvas, 1250, 300, 200, 300, this);

        //R.drawable.green_skip
        Card gs = new Card(0,1,'g','g', R.drawable.green_skip);
        gs.draw(canvas, 1550, 300, 200, 300, this);

        //R.drawable.green_reverse
        Card gr = new Card(0,1,'g','g', R.drawable.green_reverse);
        gr.draw(canvas, 1850, 300, 200, 300, this);

        //R.drawable.green_draw2
        Card gd2 = new Card(0,1,'g','g', R.drawable.green_draw2);
        gd2.draw(canvas, 2150, 300, 200, 300, this);

        //R.drawable.green_draw4
        Card gd4 = new Card(0,1,'g','g', R.drawable.green_draw4);
        gd4.draw(canvas, 1400, 700, 200, 300, this);

        //R.drawable.blue0
        Card b0 = new Card(0,1,'g','g', R.drawable.blue0);
        b0.draw(canvas, 1000, 700, 200, 300, this);


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
