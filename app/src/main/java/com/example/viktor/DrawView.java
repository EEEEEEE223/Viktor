package com.example.viktor;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.viktor.DrawThread;

public class DrawView extends SurfaceView implements SurfaceHolder.Callback {
    CountDownTimer upCountDownTimer;
    private DrawThread drawThread;

    public DrawView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getContext(), getHolder());
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        drawThread.requestStop();
        boolean retry = true;
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN) {

            upCountDownTimer = new CountDownTimer(Long.MAX_VALUE, 15) {
                public void onTick(long millisUntilFinished) {
                    if (true) {
                        drawThread.setTowardPoint((int) event.getX(), (int) event.getY());
                    }
                }

                public void onFinish() {
                }
            };
            upCountDownTimer.start();
        }else if(action == MotionEvent.ACTION_UP) upCountDownTimer.cancel();
        return false;
    }
}