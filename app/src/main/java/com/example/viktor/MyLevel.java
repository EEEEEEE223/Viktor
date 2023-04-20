package com.example.viktor;

public class MyLevel {
    int level;
    int seconds=15;
    int time=seconds;
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
        if(level>0&&level<=4){
            enemy=15;
        }else if(level>=5&&level<=8){
            enemy=20;
            seconds=20;
            strongenemy=2;
        }else if(level>=9&&level<=12){
            enemy=20;
            seconds=25;
            strongenemy=4;
            longrangeenemy=2;
        }else if(level>=13&&level<=14){
            enemy=0;
            seconds=40;
            strongenemy=0;
            longrangeenemy=20;
        }else if(level==15){
            enemy=0;
            seconds=40;
            strongenemy=30;
            longrangeenemy=0;
        }
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
