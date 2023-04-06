package com.example.viktor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

public class DrawThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private volatile boolean running = true;
    private Paint backgroundPaint = new Paint();
    private Bitmap bitmap;
    private Bitmap but;
    private MyButton up;
    private MyButton down;
    private MyButton left;
    private MyButton right;
    private int towardPointX;
    private int towardPointY;
    {
        backgroundPaint.setColor(Color.WHITE);
        backgroundPaint.setStyle(Paint.Style.FILL);
    }

    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.smile);
        this.surfaceHolder = surfaceHolder;
        but = BitmapFactory.decodeResource(context.getResources(), R.drawable.but);
    }

    public void requestStop() {
        running = false;
    }

    public void setTowardPoint(int x, int y) {
        towardPointX = x;
        towardPointY = y;
    }

    @Override
    public void run() {
        int smileX = 0;
        int smileY = 0;
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            up=new MyButton(200,500,but,canvas);
            down=new MyButton(200,500,but,canvas);
            right=new MyButton(400,350,but,canvas);
            left=new MyButton(0,350,but,canvas);

            if (canvas != null) {
                try {
                    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
                    canvas.save();
                    canvas.scale(0.5F,0.5F);
                    canvas.drawBitmap(bitmap, smileX, smileY, backgroundPaint);
                    canvas.restore();
                    Rect src = new Rect( up.getBitmap().getWidth()/2,up.getBitmap().getHeight()/2,up.getBitmap().getWidth(), up.getBitmap().getHeight());
                    Rect src2 = new Rect( 0,down.getBitmap().getHeight()/2,down.getBitmap().getWidth()/2, down.getBitmap().getHeight());
                    Rect src3 = new Rect( 0,0,left.getBitmap().getWidth()/2, left.getBitmap().getHeight()/2);
                    Rect src4 = new Rect( right.getBitmap().getWidth()/2,0,right.getBitmap().getWidth(), right.getBitmap().getHeight()/2);
                    Rect destination = new Rect(up.getX(), up.getY2(), up.getX()+200,up.getY2()+200);
                    Rect destination2 = new Rect(down.getX(), canvas.getHeight()-200 ,down.getX()+200,canvas.getHeight());
                    Rect destination3 = new Rect(left.getX(), left.getY2(), left.getX()+200,left.getY2()+200);
                    Rect destination4 = new Rect(right.getX(), right.getY2(), right.getX()+200,right.getY2()+200);
                    canvas.drawBitmap(up.getBitmap(),src,destination,new Paint());
                    canvas.drawBitmap(down.getBitmap(),src2,destination2,new Paint());
                    canvas.drawBitmap(left.getBitmap(),src3,destination3,new Paint());
                    canvas.drawBitmap(right.getBitmap(),src4,destination4,new Paint());
                    if (destination4.contains(towardPointX,towardPointY)) {
                        if(canvas.getWidth()-smileX<=smileX)smileX=canvas.getWidth()-canvas.getWidth();
                        smileX += 10;
                    }
                    if (destination3.contains(towardPointX,towardPointY)){
                        if(smileX<=0) smileX=0;
                        smileX -= 10;
                    }
                    if (destination2.contains(towardPointX,towardPointY)){
                        if (smileY+bitmap.getHeight()+25>=up.getY2())smileY=up.getY2()-bitmap.getHeight()-25;
                        smileY += 10;
                    }
                    if (destination.contains(towardPointX,towardPointY)) {
                        if(smileY<=0) smileY=0;
                        smileY -= 10;
                    }
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }

            }
        }
    }
}
