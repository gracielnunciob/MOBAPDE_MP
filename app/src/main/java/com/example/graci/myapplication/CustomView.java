package com.example.graci.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {

    Paint paint;
    Rect rect;
    Paint textPaint;

    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new Paint();
        rect = new Rect();
    }

    protected void onDraw(Canvas canvas){
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getWidth(), getHeight()-20, paint);

        textPaint.setARGB(200, 254, 0, 0);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(300);
        canvas.drawText("Hello", canvas.getWidth(), canvas.getHeight()-20, textPaint);


    }
}
