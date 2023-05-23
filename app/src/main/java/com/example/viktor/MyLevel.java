package com.example.viktor;

import static com.example.viktor.DrawThread.generate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

public class MyLevel {
    int level;
    int seconds;
    int enemy;
    ArrayList<MyEntity> enemys=new ArrayList<>();
    int strongenemy;
    int longrangeenemy;
    Canvas canvas;
    Bitmap bitmap;

    public MyLevel(int level,Bitmap bitmap) {
       setLevel(level);
       this.bitmap=bitmap;
    }

    public int getLevel() {
        return level;
    }
    public void setEnemy(int enemy, Bitmap angsmile) {
        if(canvas ==null){
            return;
        }
        enemys.clear();

        for (int i = 0; i < enemy; i++) {
            int x=generate(-100,1000);
            int y = generate(-500, -2500);
            MyEntity myEntity=new MyEntity(25, 3, angsmile, x , y);
            enemys.add( myEntity);
            myEntity.draw(canvas);

        }
        this.enemy = enemy;
    }
    public void setLevel(int level) {
        
        this.level = level;
        if(level==0) {
            setEnemy(4, bitmap);
        }else if(level==1){
            setEnemy(6,bitmap);
        }else if(level==2){
            setEnemy(8,bitmap);
        }else if(level==3){
            setEnemy(10,bitmap);
        }else if(level>=4&&level<=5){
            setEnemy(15,bitmap);
            setLongrangeenemy(3);
        }else if(level==6){
            setEnemy(20,bitmap);
        }else if(level==7&&level==8){
            setEnemy(14,bitmap);
            setLongrangeenemy(10);
        }else if(level>=9&&level<=12){
            setEnemy(14,bitmap);
            setLongrangeenemy(20);
            setStrongenemy(10);
        }
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

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public int getDeadEnemy() {
        int count=0;
        for (MyEntity entity : enemys){
            if (entity.isDelete()==true){
                count+=1;
            }
        }
        return count;
    }

}
