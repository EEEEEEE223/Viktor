package com.example.viktor;

import android.os.CountDownTimer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Test210223 {

    public static void main(String[] args) {
        final int[] time = {30};
        int seconds=30;
        CountDownTimer upCountDownTimer=new CountDownTimer(seconds,1000) {
            @Override
            public void onTick(long l) {
                time[0]--;
            }

            @Override
            public void onFinish(){
            }
        };
        System.out.println(time[0]);
    }
}