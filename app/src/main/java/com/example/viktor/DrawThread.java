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
    private volatile boolean running2 = false;
    private Paint backgroundPaint = new Paint();
    private Paint endpaint=new Paint();
    private Paint dest=new Paint();
    private Bitmap bitmap;
    private Bitmap angsmile;
    private Bitmap but;
    private Bitmap reset;
    private MyButton up;
    private MyButton down;
    private MyButton left;
    private MyButton right;
    private MyEntity enemy;
    private int towardPointX;
    private int towardPointY;
    private MyLevel myLevel;
    private boolean start =true;
    private boolean die =false;
    {
        backgroundPaint.setColor(Color.WHITE);
        backgroundPaint.setStyle(Paint.Style.FILL);
        endpaint.setColor(Color.RED);
        endpaint.setTextSize((float) 100);
        dest.setColor(Color.BLACK);
        dest.setTextSize(75);
    }

    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.smile);
        this.surfaceHolder = surfaceHolder;
        but = BitmapFactory.decodeResource(context.getResources(), R.drawable.but);
        angsmile = BitmapFactory.decodeResource(context.getResources(), R.drawable.angsmile);
        reset=BitmapFactory.decodeResource(context.getResources(), R.drawable.reset);
    }

    public void requestStop() {
        running = false;
    }

    public void setTowardPoint(int x, int y) {
        start = true;
        towardPointX = x;
        towardPointY = y;
    }
    public int generate(int min , int max){
        return (int)Math.floor(Math.random() * (max - min - 1 ) + min);
    }

    @Override
    public void run() {
        int smileXP= 12;
        int smileX = 0;
        int smileY = 0;
        up = new MyButton(200, 500, but);
        down = new MyButton(200, 500, but);
        right = new MyButton(400, 350, but);
        left = new MyButton(0, 350, but);
        enemy = new MyEntity(100, 3, angsmile, 0,0);//randomNumber.generate(-100,1000) , randomNumber.generate(-500,-2500));
        myLevel=new MyLevel(1);
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            up.setCanvas(canvas);
            down.setCanvas(canvas);
            right.setCanvas(canvas);
            left.setCanvas(canvas);
            enemy.setCanvas(canvas);
            int h = canvas.getHeight();
            int w = canvas.getWidth();
            System.out.println(generate(-100,1000));
            System.out.println(generate(-500,-2500));
            if (canvas != null) {
                try {
                    Rect msrs = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                    Rect mdestinatoin = new Rect(smileX, smileY, smileX + 150, smileY + 150);
                    Rect distdestinatoin=new Rect(smileX-200,smileY-200,smileX+350,smileY+350);
                    Rect src = new Rect(up.getBitmap().getWidth() / 2, up.getBitmap().getHeight() / 2, up.getBitmap().getWidth(), up.getBitmap().getHeight());
                    Rect src2 = new Rect(0, down.getBitmap().getHeight() / 2, down.getBitmap().getWidth() / 2, down.getBitmap().getHeight());
                    Rect src3 = new Rect(0, 0, left.getBitmap().getWidth() / 2, left.getBitmap().getHeight() / 2);
                    Rect src4 = new Rect(right.getBitmap().getWidth() / 2, 0, right.getBitmap().getWidth(), right.getBitmap().getHeight() / 2);
                    Rect src5 = new Rect(0, 0, enemy.getBitmap().getWidth(), enemy.getBitmap().getHeight());
                    Rect srcreset=new Rect(0,0,reset.getWidth(),reset.getHeight());
                    Rect destination = new Rect(up.getX(), up.getY2(), up.getX() + 200, up.getY2() + 200);
                    Rect destination2 = new Rect(down.getX(), h - 200, down.getX() + 200, h);
                    Rect destination3 = new Rect(left.getX(), left.getY2(), left.getX() + 200, left.getY2() + 200);
                    Rect destination4 = new Rect(right.getX(), right.getY2(), right.getX() + 200, right.getY2() + 200);
                    Rect destination5 = new Rect(enemy.getEntytyX(), enemy.getEntytyY(), enemy.getEntytyX() + 80, enemy.getEntytyY() + 80);
                    Rect destinationres=new Rect(w /2-100, h /2-300, w /2+100, h /2-100);
                    if(myLevel.getLevel()<=4){
                        myLevel.setEnemy(4);
                    }else if(myLevel.getLevel()>=5&&myLevel.getLevel()<=8){
                        myLevel.setEnemy(10);
                        myLevel.setLongrangeenemy(3);
                    }else if(myLevel.getLevel()>=9&&myLevel.getLevel()<=12){
                        myLevel.setEnemy(0);
                        myLevel.setLongrangeenemy(7);
                        myLevel.setStrongenemy(10);
                    }
                    if(start) {
                        if(myLevel.getEnemy()>=0){
                            canvas.drawBitmap(enemy.getBitmap(), src5, destination5, new Paint());
                            myLevel.setEnemy(myLevel.getEnemy()-1);
                        }
                        canvas.drawRect(0, 0, w, h, backgroundPaint);
                        canvas.drawBitmap(up.getBitmap(), src, destination, new Paint());
                        canvas.drawBitmap(down.getBitmap(), src2, destination2, new Paint());
                        canvas.drawBitmap(left.getBitmap(), src3, destination3, new Paint());
                        canvas.drawBitmap(right.getBitmap(), src4, destination4, new Paint());
                        canvas.drawBitmap(bitmap, msrs, mdestinatoin, new Paint());
                        canvas.drawText("XP-" + smileXP, up.getX2() - 200, up.getY2() + 200, dest);
                    }
                    if(die){
                        canvas.drawBitmap(reset,srcreset,destinationres,new Paint());
                        canvas.drawRect(0, 0, w, h, new Paint());
                        canvas.drawText("YOU DIE", (float) w / 2 - 150, (float) h / 2, endpaint);
                    }
                    if (destination4.contains(towardPointX, towardPointY)) {
                        if (smileX + 200 <= w)
                            smileX += 10;
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
                            enemy.setEntytyX(enemy.getEntytyX() + 2);
                        }
                        if (enemy.getEntytyX() > smileX) {
                            enemy.setEntytyX(enemy.getEntytyX() - 2);
                        }
                        if (enemy.getEntytyY() < smileY) {
                            enemy.setEntytyY(enemy.getEntytyY() + 3);
                        }
                        if (enemy.getEntytyY() > smileY) {
                            enemy.setEntytyY(enemy.getEntytyY() - 3);
                        }
                        if (destination5.intersect(mdestinatoin)) {
                            smileXP--;
                        }
                        if (smileXP<=0){
                            start=false;
                            die=true;
                        }
                        if(destinationres.contains(towardPointX,towardPointY)){
                            die=false;
                            smileX=0;
                            smileY=0;
                            smileXP=12;
                            enemy.setEntytyY(enemy.getFirstentytyY());
                            enemy.setEntytyX(enemy.getFirstentytyX());
                            enemy.setHp(enemy.getFirstHp());
                            start=true;
                        }
                        if (distdestinatoin.contains(destination5)) {
                            enemy.setHp(enemy.getHp() - 1);
                        }
                    }
                    if(enemy.getHp()<=0){
                        enemy.setEntytyY(-10000);
                    }
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
