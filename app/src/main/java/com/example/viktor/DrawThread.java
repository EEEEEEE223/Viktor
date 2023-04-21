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
    private Bitmap angsmile;
    private Bitmap but;
    private MyButton up;
    private MyButton down;
    private MyButton left;
    private MyButton right;
    private MyEntity enemy;
    private int towardPointX;
    private int towardPointY;
    private boolean start = false;

    {
        backgroundPaint.setColor(Color.WHITE);
        backgroundPaint.setStyle(Paint.Style.FILL);
    }

    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.smile);
        this.surfaceHolder = surfaceHolder;
        but = BitmapFactory.decodeResource(context.getResources(), R.drawable.but);
        angsmile = BitmapFactory.decodeResource(context.getResources(), R.drawable.angsmile);
    }

    public void requestStop() {
        running = false;
    }

    public void setTowardPoint(int x, int y) {
        start = true;
        towardPointX = x;
        towardPointY = y;
    }

    @Override
    public void run() {
        int smileX = 0;
        int smileY = 0;
        up = new MyButton(200, 500, but);
        down = new MyButton(200, 500, but);
        right = new MyButton(400, 350, but);
        left = new MyButton(0, 350, but);
        enemy = new MyEntity(10, 3, angsmile, 0, 0);
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            up.setCanvas(canvas);
            down.setCanvas(canvas);
            right.setCanvas(canvas);
            left.setCanvas(canvas);
            enemy.setCanvas(canvas);
            if (canvas != null) {
                try {
                    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
                    //canvas.drawBitmap(bitmap, smileX, smileY, backgroundPaint);
                    Rect msrs = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                    Rect mdestinatoin = new Rect(smileX, smileY, smileX + 200, smileY + 200);
                    Rect src = new Rect(up.getBitmap().getWidth() / 2, up.getBitmap().getHeight() / 2, up.getBitmap().getWidth(), up.getBitmap().getHeight());
                    Rect src2 = new Rect(0, down.getBitmap().getHeight() / 2, down.getBitmap().getWidth() / 2, down.getBitmap().getHeight());
                    Rect src3 = new Rect(0, 0, left.getBitmap().getWidth() / 2, left.getBitmap().getHeight() / 2);
                    Rect src4 = new Rect(right.getBitmap().getWidth() / 2, 0, right.getBitmap().getWidth(), right.getBitmap().getHeight() / 2);
                    Rect src5 = new Rect(0, 0, enemy.getBitmap().getWidth(), enemy.getBitmap().getHeight());
                    Rect destination = new Rect(up.getX(), up.getY2(), up.getX() + 200, up.getY2() + 200);
                    Rect destination2 = new Rect(down.getX(), canvas.getHeight() - 200, down.getX() + 200, canvas.getHeight());
                    Rect destination3 = new Rect(left.getX(), left.getY2(), left.getX() + 200, left.getY2() + 200);
                    Rect destination4 = new Rect(right.getX(), right.getY2(), right.getX() + 200, right.getY2() + 200);
                    Rect destination5 = new Rect(enemy.getEntytyX(), enemy.getEntytyY(), enemy.getEntytyX() + 150, enemy.getEntytyY() + 150);
                    canvas.drawBitmap(bitmap, msrs, mdestinatoin, new Paint());
                    canvas.drawBitmap(up.getBitmap(), src, destination, new Paint());
                    canvas.drawBitmap(down.getBitmap(), src2, destination2, new Paint());
                    canvas.drawBitmap(left.getBitmap(), src3, destination3, new Paint());
                    canvas.drawBitmap(right.getBitmap(), src4, destination4, new Paint());
                    canvas.drawBitmap(enemy.getBitmap(), src5, destination5, new Paint());
                    if (destination4.contains(towardPointX, towardPointY)) {
                        if (smileX + 200 <= canvas.getWidth())
                            smileX += 10;
                        System.out.println(2);
                    } else if (destination3.contains(towardPointX, towardPointY)) {
                        if (smileX <= 0) smileX = 0;
                        smileX -= 10;
                    } else if (destination2.contains(towardPointX, towardPointY)) {
                        if (up.getY2() - 50 >= smileY + 200)
                            smileY += 10;
                    } else if (destination.contains(towardPointX, towardPointY)) {
                        if (smileY <= 0) smileY = 0;
                        smileY -= 10;
                    }
                    if (!mdestinatoin.contains(destination5)) {
                        if (enemy.getEntytyX() < smileX) {
                            enemy.setEntytyX(enemy.getEntytyX() + 4);
                        }
                        if (enemy.getEntytyX() > smileX) {
                            enemy.setEntytyX(enemy.getEntytyX() - 4);
                        }
                        if (enemy.getEntytyY() < smileY) {
                            enemy.setEntytyY(enemy.getEntytyY() + 6);
                        }
                        if (enemy.getEntytyY() > smileY) {
                            enemy.setEntytyY(enemy.getEntytyY() - 6);
                        }
                    }
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }

            }
        }
    }
}