package com.example.viktor;

import android.os.CountDownTimer;

public class MyLevel {
    int level;
    int seconds;
    int time=seconds;
    int enemy;
    int strongenemy;
    int longrangeenemy;
    CountDownTimer upCountDownTimer=new CountDownTimer(seconds,1000) {
        @Override
        public void onTick(long l) {
            time--;
        }

        @Override
        public void onFinish(){
        }
    };

    public MyLevel(int level) {
        this.level=level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getEnemy() {
        return enemy;
    }

    public void setEnemy(int enemy) {
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
