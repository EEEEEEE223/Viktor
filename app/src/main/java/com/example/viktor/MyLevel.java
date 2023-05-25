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
    ArrayList<MyEntity> enemys1=new ArrayList<>();
    ArrayList<MyEntity> enemys2=new ArrayList<>();
    int strongenemy;
    int boss;
    Canvas canvas;
    Bitmap bitmap;
    Bitmap bitmap1;
    Bitmap bitmap2;

    public MyLevel(int level,Bitmap bitmap,Bitmap bitmap1,Bitmap bitmap2) {
       setLevel(level);
       this.bitmap=bitmap;
       this.bitmap1=bitmap1;
       this.bitmap2=bitmap2;
    }

    public int getLevel() {
        return level;
    }
    public void setEnemy(int enemy, Bitmap angsmile,int strongenemy, Bitmap angsmile1,int boss,Bitmap angsmile2) {
        if(canvas ==null){
            return;
        }
        clear();
        for (int i = 0; i < enemy; i++) {
            int x=generate(-100,1000);
            int y = generate(-500, -2500);
            MyEntity myEntity=new MyEntity(50, 3, angsmile, x , y,80);
            enemys.add( myEntity);
            myEntity.draw(canvas);

        }
        for (int i = 0; i < strongenemy; i++) {
            int x=generate(-100,1000);
            int y = generate(-500, -2500);
            MyEntity myEntity=new MyEntity(125, 1, angsmile1, x , y,200);
            enemys1.add( myEntity);
            myEntity.draw(canvas);

        }
        for (int i = 0; i < boss; i++) {
            int x=generate(-100,1000);
            int y = generate(-500, -2500);
            MyEntity myEntity=new MyEntity(1500, 2, angsmile2, x , y,450);
            enemys2.add( myEntity);
            myEntity.draw(canvas);

        }
        this.enemy = enemy;
        this.strongenemy=strongenemy;
        this.boss=boss;
    }


    public void setLevel(int level) {
        
        this.level = level;
        if(level==1) {
            setEnemy(4, bitmap,0,bitmap1,0,bitmap2);
        }else if(level==2){
            setEnemy(6,bitmap,0,bitmap1,0,bitmap2);
        }else if(level==3){
            setEnemy(8,bitmap,0,bitmap1,0,bitmap2);
        }else if(level==4){
            setEnemy(10,bitmap,3,bitmap1,0,bitmap2);
        }else if(level>=5&&level<=6){
            setEnemy(15,bitmap,0,bitmap1,0,bitmap2);
        }else if(level==7){
            setEnemy(20,bitmap,5,bitmap1,0,bitmap2);
        }else if(level>=8&&level<=9){
            setEnemy(14,bitmap,10,bitmap1,0,bitmap2);
        }else if(level>=10&&level<=13){
            setEnemy(14,bitmap,15,bitmap1,0,bitmap2);
        }else if(level==14){
            setEnemy(0,bitmap,0,bitmap1,1,bitmap2);
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

    public int getBoss() {
        return boss;
    }

    public void setBoss(int boss) {
        this.boss = boss;
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
    public int getDeadStrongEnemy() {
        int count=0;
        for (MyEntity entity1 : enemys1){
            if (entity1.isDelete()==true){
                count+=1;
            }
        }
        return count;
    }
    public int getDeadBoss() {
        int count=0;
        for (MyEntity entity2 : enemys2){
            if (entity2.isDelete()==true){
                count+=1;
            }
        }
        return count;
    }
    public void  clear(){
        enemys.clear();
        enemys1.clear();
        enemys2.clear();
    }
}
