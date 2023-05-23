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
    private int l=1;
    private int smileXP= 12;
    private int smileX = 0;
    private int smileY = 0;
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
        myLevel=new MyLevel(l,angsmile);
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            up.setCanvas(canvas);
            down.setCanvas(canvas);
            right.setCanvas(canvas);
            left.setCanvas(canvas);

            if(myLevel.getCanvas()==null){
                myLevel.setCanvas(canvas);
                myLevel.setLevel(l);
            }
            myLevel.setCanvas(canvas);
            int h = canvas.getHeight();
            int w = canvas.getWidth();
            try {
                Rect msrs = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                Rect mdestinatoin = new Rect(smileX, smileY, smileX + 150, smileY + 150);
                Rect distdestinatoin=new Rect(smileX-200,smileY-200,smileX+350,smileY+350);
                Rect src = new Rect(up.getBitmap().getWidth() / 2, up.getBitmap().getHeight() / 2, up.getBitmap().getWidth(), up.getBitmap().getHeight());
                Rect src2 = new Rect(0, down.getBitmap().getHeight() / 2, down.getBitmap().getWidth() / 2, down.getBitmap().getHeight());
                Rect src3 = new Rect(0, 0, left.getBitmap().getWidth() / 2, left.getBitmap().getHeight() / 2);
                Rect src4 = new Rect(right.getBitmap().getWidth() / 2, 0, right.getBitmap().getWidth(), right.getBitmap().getHeight() / 2);
                Rect destination = new Rect(up.getX(), up.getY2(), up.getX() + 200, up.getY2() + 200);
                Rect destination2 = new Rect(down.getX(), h - 200, down.getX() + 200, h);
                Rect destination3 = new Rect(left.getX(), left.getY2(), left.getX() + 200, left.getY2() + 200);
                Rect destination4 = new Rect(right.getX(), right.getY2(), right.getX() + 200, right.getY2() + 200);
                if(die){
                    canvas.drawRect(0, 0, w, h, new Paint());
                    canvas.drawText("YOU DIE", (float) w / 2 - 150, (float) h / 2, endpaint);
                }
                if(start) {
                    spawninterface(canvas,w,h,src,src2,src3,src4,destination,destination2,destination3,destination4);
                    spawnsmile(canvas,mdestinatoin,msrs);
                    enemymoves(canvas, mdestinatoin, distdestinatoin);
                    System.out.println(myLevel.getEnemy());

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
                if(myLevel.getDeadEnemy()== myLevel.getEnemy()){
                    myLevel.setLevel(l+1);
                }
            } finally {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
    private void enemymoves(Canvas canvas, Rect mdestinatoin, Rect distdestinatoin) {

        for (MyEntity enemy:myLevel.enemys) {
            if(enemy.isDelete()){
                continue;
            }
            enemy.draw(canvas);
            if (!mdestinatoin.contains(enemy.getDestination())) {
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
                if (enemy.getDestination().intersect(mdestinatoin)) {
                    smileXP--;
                }
                if (smileXP <= 0) {
                    start = false;
                    die = true;
                }
                if (distdestinatoin.contains(enemy.getDestination())) {
                    enemy.setHp( enemy.getHp()-1);
                }
                if (enemy.getHp()<=0&&!enemy.isDelete()) {
                    enemy.setDelete(true);
                }
            }
        }
    }

    private void spawninterface(Canvas canvas,int w,int h,Rect src,Rect src2,Rect src3,
                                Rect src4,Rect destination,Rect destination2,Rect destination3,
                                Rect destination4){
        canvas.drawRect(0, 0, w, h, backgroundPaint);
        canvas.drawBitmap(up.getBitmap(), src, destination, new Paint());
        canvas.drawBitmap(down.getBitmap(), src2, destination2, new Paint());
        canvas.drawBitmap(left.getBitmap(), src3, destination3, new Paint());
        canvas.drawBitmap(right.getBitmap(), src4, destination4, new Paint());
        canvas.drawText("XP-" + smileXP, up.getX2() - 200, up.getY2() + 200, dest);
    }
    private void spawnsmile(Canvas canvas,Rect mdestinatoin,Rect msrs){
        canvas.drawBitmap(bitmap, msrs, mdestinatoin, new Paint());
    }
}
