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
    private Bitmap b1;
    private Bitmap b2;
    private MyButton up;
    private int towardPointX;
    private int towardPointY;
    {
        backgroundPaint.setColor(Color.WHITE);
        backgroundPaint.setStyle(Paint.Style.FILL);
    }

    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.smile);
        this.surfaceHolder = surfaceHolder;
        b1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.b1);
        b2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.b2);
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
            up=new MyButton(150,500,b1,canvas);
            if (canvas != null) {
                try {
                    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
                    canvas.drawBitmap(bitmap, smileX, smileY, backgroundPaint);
                    Rect src = new Rect(0, 0, up.getBitmap().getWidth(),up.getBitmap().getHeight());
                    Rect destination = new Rect(up.getX(), up.getY2(), up.getX()+200,up.getY2()+200);

                    canvas.drawBitmap(up.getBitmap(),src,destination,new Paint());
                    if (smileX + bitmap.getWidth() / 2 < towardPointX) smileX += 10;
                    if (smileX + bitmap.getWidth() / 2 > towardPointX) smileX -= 10;
                    if (smileY+bitmap.getHeight()/2<towardPointY) {
                        smileY += 10;
                    }
                    if (destination.contains(towardPointX,towardPointY)) {
                        if (smileY+bitmap.getHeight()>=up.getY2())smileY=up.getY2()-bitmap.getHeight();
                        System.out.println(smileY);
                        System.out.println(towardPointX + "hj");
                        System.out.println(towardPointY);
                        smileY -= 10;
                    }
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }

            }
        }
    }
}
