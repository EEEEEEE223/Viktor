package com.example.viktor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class GameView extends View {
    private int viewWidth;
    private int viewHeight;
    private int time = 30;
    private int hp = 10;

    public GameView(Context context) {
        super(context);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        viewWidth = w;
        viewHeight = h;
    }
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        canvas.drawARGB(250, 127, 199, 255);
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setTextSize(56.0f);
        p.setColor(Color.WHITE);
        canvas.drawText(time+"", viewWidth - 100, 70, p);
        canvas.drawText(hp+"", viewWidth - 100, 60, p);
    }


}
