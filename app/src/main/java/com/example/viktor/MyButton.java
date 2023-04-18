package com.example.viktor;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class MyButton {
    private int x;
    private int y;
    private Bitmap bitmap;
    private Canvas canvas;
    public MyButton(int x, int y, Bitmap bitmap){
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
    }
    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
    public int getX() {
        return x; //canvas.getWidth()-x;
    }
    public int getX2() {
        return canvas.getWidth()-x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y; //canvas.getWidth()-y;
    }
    public int getY2() {
        return canvas.getHeight()-y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
