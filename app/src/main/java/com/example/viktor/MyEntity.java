package com.example.viktor;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class MyEntity {
    int hp;
    int damage;
    int radX;
    int radY;
    private Bitmap bitmap;
    private Canvas canvas;
    int entytyX=0;
    int entytyY=0;

    public MyEntity(int hp, int damage, int radX, int radY, Bitmap bitmap, Canvas canvas) {
        this.hp = hp;
        this.damage = damage;
        this.radX = radX;
        this.radY = radY;
        this.bitmap = bitmap;
        this.canvas = canvas;
    }
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRadX() {
        return radX;
    }

    public void setRadX(int radX) {
        this.radX = radX;
    }

    public int getRadY() {
        return radY;
    }

    public void setRadY(int radY) {
        this.radY = radY;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public int getEntytyX() {
        return entytyX;
    }

    public void setEntytyX(int entytyX) {
        this.entytyX = entytyX;
    }

    public int getEntytyY() {
        return entytyY;
    }

    public void setEntytyY(int entytyY) {
        this.entytyY = entytyY;
    }
}
