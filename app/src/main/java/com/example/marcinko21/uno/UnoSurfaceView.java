package com.example.marcinko21.uno;

import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

public class UnoSurfaceView extends SurfaceView implements View.OnTouchListener {

    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    public UnoSurfaceView() {
        setState();


    }
    public void setState(UnoState state) {
        this.state = state;
    }
}
