package com.example.marcinko21.uno;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.marcinko21.uno.game.GameHumanPlayer;
import com.example.marcinko21.uno.game.infoMsg.GameInfo;
import com.example.marcinko21.uno.game.infoMsg.IllegalMoveInfo;
import com.example.marcinko21.uno.game.infoMsg.NotYourTurnInfo;

public class UnoHumanPlayer extends GameHumanPlayer implements View.OnTouchListener {

    // the current activity
    private Activity myActivity;

    // the surface view
    private UnoSurfaceView surfaceView;

    // the ID for the layout to use
    private int layoutId;

    /**
     * constructor
     *
     * @param name
     * 		the player's name
     * @param layoutId
     *      the id of the layout to use
     */
    public UnoHumanPlayer(String name, int layoutId) {
        super(name);
        this.layoutId = layoutId;
    }

    /**
     * Callback method, called when player gets a message
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info) {

        if (surfaceView == null) return;

        if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            // if the move was out of turn or otherwise illegal, flash the screen
            surfaceView.flash(Color.RED, 50);
        }
        else if (!(info instanceof UnoState))
            // if we do not have a UnoState, ignore
            return;
        else {
            surfaceView.setState((UnoState)info);
            surfaceView.invalidate();
            Log.i("human player", "receiving");
        }
    }

    /**
     * sets the current player as the activity's GUI
     */
    public void setAsGui(UnoMainActivity activity) {

        // remember our activitiy
        myActivity = activity;

        // Load the layout resource for the new configuration
        activity.setContentView(layoutId);

        // set the surfaceView instance variable
        surfaceView = (UnoSurfaceView)myActivity.findViewById(R.id.surfaceView);
        Log.i("set listener","OnTouch");
        surfaceView.setOnTouchListener(this);
    }


    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}
