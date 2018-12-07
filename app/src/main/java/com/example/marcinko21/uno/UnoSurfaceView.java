package com.example.marcinko21.uno;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.example.marcinko21.uno.game.Game;
import com.example.marcinko21.uno.game.GamePlayer;
import com.example.marcinko21.uno.game.LocalGame;

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

        Log.i("SurfaceView onTouch", "surfaceView Touch starting");

        //locate card tapped
        //compare x,y to the rectangle where each card is drawn
        //if the tapped area is in the field of rectangle, then the program will know that THAT card is tapped

        int touchX = (int)event.getX();
        //sub note: can cast because primitive
        // casting happens later if not primitive
        int touchY = (int)event.getY();
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {


            final double RATIO = 1.2;
            //for the human player's hand
            int y = 3 * (this.getHeight()) / 4;
            //card width
            int xSize = (this.getWidth()) / Math.max(7, state.getHandSize(state.getHand(0)));//how many cards are in the hand
            //card height
            int ySize = (int) (xSize * RATIO);
            for (int i = 0; i < state.getHandSize(state.getHand(0)); i++)
            {
                Card c = state.getHand(0).get(i);
                //c.draw(canvas, xSize*i, y, xSize, ySize, this, false);
                int leftX = xSize * i;
                int rightX = xSize + xSize * i;
                int topY = y;
                int bottomY = ySize + y;
                Log.i("SurfaceView onTouch", "touch x, y " + touchX + " " + touchY + " left corner" + leftX + " " + topY + " bottom Corner" + rightX + "  " + bottomY);

                //check rect to see if we have the exact right card to playAction
                if ((touchX >= leftX) && (touchX < rightX) && (touchY >= topY) && (touchY < bottomY))
                {
                    UnoDraw2 d2 = new UnoDraw2(aHuman, c);
                    UnoDraw4 d4 = new UnoDraw4(aHuman, c);
                    UnoSkip sk = new UnoSkip(aHuman, c);
                    UnoReverse rv = new UnoReverse(aHuman, c);
                    playCardAction a = new playCardAction(aHuman, c);

                    if(c.type == 'd' && c.color == 4)
                    {
                        aGame.sendAction(d4);
                    }
                    else if(c.type == 'd' && c.color != 4)
                    {
                        aGame.sendAction(d2);
                    }
                    else if(c.type == 's')
                    {
                        aGame.sendAction(sk);
                    }
                    else if(c.type == 'r')
                    {
                        aGame.sendAction(rv);
                    }
                    else
                    {
                        //other type
                        aGame.sendAction(a);
                    }
                    Log.i("SurfaceView onTouch", "sent playCard action: " + i);
                    return true;
                }
            }
        }
        //creates a new instance of playCardAction

        return true;
    }

    /**
     * Instance Variables
     */
    //the game's state
    protected UnoState state = new UnoState();
    private TextView compText;
    private TextView yourText;
    private Activity myActivity;

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

        compText = myActivity.findViewById(R.id.compHand);
        yourText = myActivity.findViewById(R.id.yourHand);

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
        //setBackgroundColor(backgroundColor());
    }// init

    public int backgroundColor()
    {
        return Color.BLUE;
    }

    public void setState(UnoState state)
    {
        this.state = state;
    }

    public Paint myPaint(){

        //set highlight to pink
        int highlight = Color.argb(255, 255, 0, 255);
        myPaint().setColor(highlight);
        return myPaint();
    }

    /**
     * onDraw
     * draws on SurfaceView
     *
     * @param canvas
     */
    @Override
    public void onDraw(Canvas canvas)
    {

        /*float compTextX = compText.getX();
        float compTextY = compText.getY();
        float compTextH = compText.getHeight();
        float compTextW = compText.getWidth();
        canvas.drawRect(compTextX, compTextY, compTextW, compTextH, myPaint());*/


        /**
        //view class has an x, y, width, height, or get position
        float compTextX = compText.getX();
        float compTextY = compText.getY();
        float compTextH = compText.getHeight();
        float compTextW = compText.getWidth();

        float yourTextLeft = yourText.getX() - 10 ; //x
        float yourTextTop = yourText.getY() - 10; //y
        float yourTextBottom = yourText.getHeight() + 10; //height
        float yourTextRight = yourText.getWidth() + 50; //width


        //drawing highlight around textView depending on the turn
        if (state.getTurn() == 0){ //if player's turn

            //canvas.drawRect(x + 10, y + 10, 10, 10, color);
            canvas.drawRect(yourTextLeft, yourTextTop, yourTextRight, yourTextBottom, myPaint());

        } else if (state.getTurn() == 1){ //if computer's turn

            //canvas.drawRect(x + 10, y + 10, 10, 10, color);

        }
         */

        //green
        if(state.color == 0)
        {
            setBackgroundColor(Color.GREEN);
        }
        //blue
        else if(state.color == 1)
        {
            setBackgroundColor(Color.BLUE);
        }
        //red
        else if(state.color == 2)
        {
            setBackgroundColor(Color.RED);
        }
        //yellow
        else if(state.color == 3)
        {
            setBackgroundColor(Color.YELLOW);
        }

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
        //Card drawPile = state.getDeck().get(0);
        //drawPile.draw(canvas, (int)(.75*this.getWidth()), (int)(.5*this.getHeight()), (int)(.1*this.getWidth()), (int)(RATIO*(int)(.1*this.getWidth())), this, true);
        //draw back side of the uno card's image


        //to draw text showing number of cards in computer player's hand, OR
        //draw backside card for every card in computer player's hand

        //For the computer's hand
        xSize = (this.getWidth())/Math.max(7, state.getHandSize(state.getHand(1)));//how many cards are in the hand
        y = 0;
        ySize = (int)(xSize*RATIO);
        for(int i=0; i < state.getHandSize(state.getHand(1)); i++)
        {
            Card c = state.getHand(1).get(i);
            c.draw(canvas, xSize * i, y, xSize, ySize, this, false);
        }




    }
    
    public void setHumanPlayer(GamePlayer p)
    {
        this.aHuman = p;
    }

    public void setGame(Game g)
    {
        this.aGame = g;
    }

    public void setMyActivity(Activity a) { this.myActivity = a; }

    private GamePlayer aHuman;
    private Game aGame;
}
