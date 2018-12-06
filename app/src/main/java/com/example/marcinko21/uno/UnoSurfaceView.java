package com.example.marcinko21.uno;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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

                    if(c.type == 'd' && c.color == 4){
                        aGame.sendAction(d4);
                    }
                    else if(c.type == 'd' && c.color != 4){
                        aGame.sendAction(d2);
                    }
                    else if(c.type == 's'){
                        aGame.sendAction(sk);
                    }
                    else if(c.type == 'r'){
                        aGame.sendAction(rv);
                    }
                    else {
                        //other type
                        aGame.sendAction(a);
                    }
                    Log.i("SurfaceView onTouch", "sent playCard action: " + i);
                    return true;
                }
            }
        }

        //todo: move card from hand to discard
        //creates a new instance of playCardAction

        return true;
    }

    /**
     * Instance Variables
     */
    //the game's state
    protected UnoState state = new UnoState();
    private TextView text;

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

        //todo talk to tribelhorn about highlighting a text box instead of writing it
        //text = myActivity.findViewById(R.id.textBox);
        //text = findViewById(R.id.textBox);
        text.setOnClickListener((OnClickListener) this);



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
    {//setBackgroundColor(backgroundColor());
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


        int playerOne = state.getPlayer1Id();
        int playerTwo = state.getPlayer2Id();
        int turn = state.getTurn();
        String oneName = Integer.toString(playerOne);
        String twoName = Integer.toString(playerTwo);

        /***
        if(turn == 0) {

            text.setText("Current Turn: " + oneName);
            Log.i("Text says: ", " one name ");

        } else if (turn == 1) {

            text.setText("Current Turn: " + twoName);
            Log.i("Text says: ", " two name ");

        }

        Log.i("Text says: ", " no name ");
         */


        //if((state. = true);
          //  canvas.drawText("It is: " + turn + "'s turn");

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
            //TODO: change isHidden back to true when done debugging
            c.draw(canvas, xSize * i, y, xSize, ySize, this, true);
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

    private GamePlayer aHuman;
    private Game aGame;

    //Todo: if it is a special card, call the special card action, else play a regular card


}
