package com.example.chasen.myapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyView extends View {

    private static final String TAG = "Chasen";
    Paint paint;

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        Log.d(TAG,"2个参数");
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1.0f);
        paint.setStyle(Paint.Style.STROKE);
    }

    public MyView(Context context) {
        super(context);
        Log.d(TAG,"1个参数");

    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG,"3个参数");

    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int width = MeasureSpec.getSize(widthMeasureSpec);
//        Log.d(TAG,"width"+width);
//        if(widthMode == MeasureSpec.AT_MOST){
//            Log.d(TAG,"mode AT_MOST");
//            setMeasuredDimension(400,400);
//        }else if(widthMode == MeasureSpec.EXACTLY){
//            Log.d(TAG,"mode EXACTLY");
//        }else if(widthMode == MeasureSpec.UNSPECIFIED){
//            Log.d(TAG,"mode UNSPECIFIED");
//        }
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
        canvas.drawCircle(200f,200f,200f, paint);
    }
}
