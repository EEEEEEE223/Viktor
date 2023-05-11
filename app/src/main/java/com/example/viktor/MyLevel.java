package com.example.viktor;

import static com.example.viktor.DrawThread.generate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class MyLevel {
    int level;
    int seconds;
    int enemy;
    ArrayList<MyEntity> enemys=new ArrayList<>();
    int strongenemy;
    int longrangeenemy;
    //CountDownTimer upCountDownTimer=new CountDownTimer(seconds,1000) {
        //@Override
        //public void onTick(long l) {
            //time--;
        //}

        //@Override
        //public void onFinish(){
        //}
    //};

    public MyLevel(int level) {
        this.level=level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getEnemy() {
        return enemy;
    }

    public void setEnemy(int enemy, Bitmap angsmile) {
        enemys.clear();

        for (int i = 0; i < enemy; i++) {
            enemys.add(new MyEntity(100, 3, angsmile, generate(-100,1000) , generate(-500,-2500)));
        }
        this.enemy = enemy;
    }

    public int getStrongenemy() {
        return strongenemy;
    }

    public void setStrongenemy(int strongenemy) {
        this.strongenemy = strongenemy;
    }

    public int getLongrangeenemy() {
        return longrangeenemy;
    }

    public void setLongrangeenemy(int longrangeenemy) {
        this.longrangeenemy = longrangeenemy;
    }
}
