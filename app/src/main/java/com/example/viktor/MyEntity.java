package com.example.viktor;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.google.android.material.datepicker.RangeDateSelector;

import java.util.Random;

public class MyEntity {
    int hp;
    int damage;
    int radX;
    int radY;
    private Bitmap bitmap;
    private Canvas canvas;
    int entytyX=0;
    int entytyY=0;
    int firstentytyX=entytyX;
    int firstentytyY=entytyY;
    int firstHp=hp;
    private Rect destination5;
    private Rect src5;

    public MyEntity(int hp, int damage, Bitmap bitmap, int entytyX, int entytyY) {
        this.hp = hp;
        this.damage = damage;
        this.bitmap = bitmap;
        this.entytyX = entytyX;
        this.entytyY = entytyY;
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
    public  void draw(){
        src5 = new Rect(0, 0, this.getBitmap().getWidth(), this.getBitmap().getHeight());
        destination5 = new Rect(this.getEntytyX(), this.getEntytyY(), this.getEntytyX() + 80, this.getEntytyY() + 80);
        this.canvas.drawBitmap(this.bitmap,src5,destination5,new Paint());
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

    public int getFirstentytyX() {return firstentytyX;}

    public void setFirstentytyX(int firstentytyX) {
        this.firstentytyX = firstentytyX;
    }

    public int getFirstentytyY() {
        return firstentytyY;
    }

    public void setFirstentytyY(int firstentytyY) {
        this.firstentytyY = firstentytyY;
    }

    public int getFirstHp() {return firstHp;}

    public void setFirstHp(int firstHp) {this.firstHp = firstHp;}

    public Rect getDestination5() {
        return destination5;
    }

    public void setDestination5(Rect destination5) {
        this.destination5 = destination5;
    }

    public Rect getSrc5() {
        return src5;
    }

    public void setSrc5(Rect src5) {
        this.src5 = src5;
    }
}
