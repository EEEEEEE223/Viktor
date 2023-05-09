package com.example.viktor;

public class MyLevel {
    int level;
    int seconds;
    int enemy;
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
