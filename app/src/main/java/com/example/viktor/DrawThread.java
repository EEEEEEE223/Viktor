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
    private int towardPointX;
    private int towardPointY;
    private MyLevel myLevel;
    private boolean start =true;
    private boolean die =false;
    int smileXP= 12;
    int smileX = 0;
    int smileY = 0;
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
    public  static int generate(int min , int max){
        return (int)Math.floor(Math.random() * (max - min - 1 ) + min);
    }

    @Override
    public void run() {
        up = new MyButton(200, 500, but);
        down = new MyButton(200, 500, but);
        right = new MyButton(400, 350, but);
        left = new MyButton(0, 350, but);
        myLevel=new MyLevel(1);
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            up.setCanvas(canvas);
            down.setCanvas(canvas);
            right.setCanvas(canvas);
            left.setCanvas(canvas);
            int h = canvas.getHeight();
            int w = canvas.getWidth();
            if (canvas != null) {
                try {
                    Rect msrs = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                    Rect mdestinatoin = new Rect(smileX, smileY, smileX + 150, smileY + 150);
                    Rect distdestinatoin=new Rect(smileX-200,smileY-200,smileX+350,smileY+350);
                    Rect src = new Rect(up.getBitmap().getWidth() / 2, up.getBitmap().getHeight() / 2, up.getBitmap().getWidth(), up.getBitmap().getHeight());
                    Rect src2 = new Rect(0, down.getBitmap().getHeight() / 2, down.getBitmap().getWidth() / 2, down.getBitmap().getHeight());
                    Rect src3 = new Rect(0, 0, left.getBitmap().getWidth() / 2, left.getBitmap().getHeight() / 2);
                    Rect src4 = new Rect(right.getBitmap().getWidth() / 2, 0, right.getBitmap().getWidth(), right.getBitmap().getHeight() / 2);
                    Rect srcreset=new Rect(0,0,reset.getWidth(),reset.getHeight());
                    Rect destination = new Rect(up.getX(), up.getY2(), up.getX() + 200, up.getY2() + 200);
                    Rect destination2 = new Rect(down.getX(), h - 200, down.getX() + 200, h);
                    Rect destination3 = new Rect(left.getX(), left.getY2(), left.getX() + 200, left.getY2() + 200);
                    Rect destination4 = new Rect(right.getX(), right.getY2(), right.getX() + 200, right.getY2() + 200);
                    Rect destinationres=new Rect(w /2-100, h /2-300, w /2+100, h /2-100);
                    if(myLevel.getLevel()<=4){
                        myLevel.setSeconds(15*3000);
                        myLevel.setEnemy(4,angsmile);
                    }else if(myLevel.getLevel()>=5&&myLevel.getLevel()<=8){
                        myLevel.setSeconds(20*3000);
                        myLevel.setEnemy(10,angsmile);
                        myLevel.setLongrangeenemy(3);
                    }else if(myLevel.getLevel()>=9&&myLevel.getLevel()<=12){
                        myLevel.setSeconds(25*3000);
                        myLevel.setEnemy(0,angsmile);
                        myLevel.setLongrangeenemy(7);
                        myLevel.setStrongenemy(10);
                    }
                    myLevel.setSeconds(myLevel.getSeconds()-1);
                    if(start) {
                        canvas.drawRect(0, 0, w, h, backgroundPaint);
                        for (MyEntity enemy:myLevel.enemys) {
                            enemy.setCanvas(canvas);
                            enemy.draw();
                        }
                        canvas.drawBitmap(up.getBitmap(), src, destination, new Paint());
                        canvas.drawBitmap(down.getBitmap(), src2, destination2, new Paint());
                        canvas.drawBitmap(left.getBitmap(), src3, destination3, new Paint());
                        canvas.drawBitmap(right.getBitmap(), src4, destination4, new Paint());
                        canvas.drawBitmap(bitmap, msrs, mdestinatoin, new Paint());
                        canvas.drawText("XP-" + smileXP, up.getX2() - 200, up.getY2() + 200, dest);
                        canvas.drawText("Time-" + myLevel.getSeconds()/3000, up.getX2() - 200, up.getY2() + 400, dest);
                        System.out.println(myLevel.getSeconds());
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
                    enemymoves(canvas, mdestinatoin, distdestinatoin, destinationres);
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    private void enemymoves(Canvas canvas, Rect mdestinatoin, Rect distdestinatoin, Rect destinationres) {
        for (MyEntity enemy:myLevel.enemys) {
            enemy.setCanvas(canvas);
            enemy.draw();
            if (!mdestinatoin.contains(enemy.getDestination5())) {
                if (enemy.getEntytyX() < smileX) {
                    enemy.setEntytyX(enemy.getEntytyX() + 20);
                }
                if (enemy.getEntytyX() > smileX) {
                    enemy.setEntytyX(enemy.getEntytyX() - 20);
                }
                if (enemy.getEntytyY() < smileY) {
                    enemy.setEntytyY(enemy.getEntytyY() + 30);
                }
                if (enemy.getEntytyY() > smileY) {
                    enemy.setEntytyY(enemy.getEntytyY() - 30);
                }
                if (enemy.getDestination5().intersect(mdestinatoin)) {
                    smileXP--;
                }
                if (smileXP <= 0) {
                    start = false;
                    die = true;
                }
                if (destinationres.contains(towardPointX, towardPointY)) {
                    die = false;
                    smileX = 0;
                    smileY = 0;
                    smileXP = 12;
                    enemy.setEntytyY(enemy.getFirstentytyY());
                    enemy.setEntytyX(enemy.getFirstentytyX());
                    enemy.setHp(enemy.getFirstHp());
                    start = true;
                }
                if (distdestinatoin.contains(enemy.getDestination5())) {
                    enemy.setHp(enemy.getHp() - 1);
                }
            }
            if (enemy.getHp() <= 0) {
                enemy.setEntytyY(-10000);
            }
        }
    }
}
