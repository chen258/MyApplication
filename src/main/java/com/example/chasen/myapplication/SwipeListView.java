package com.example.chasen.myapplication;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import static android.content.ContentValues.TAG;

/**
 * Created by chasen on 2017/5/25.
 */

public class SwipeListView extends ListView {
    int position=0;
    View itemView;
    int oldX;
    int firstDownX;
    int screenWidth;
    int delViewWidth;
    Rect rect = new Rect();
    Point point = new Point();
    ViewGroup.LayoutParams layoutParams;
    public SwipeListView(Context context) {
        super(context);
    }

    public SwipeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager windowManager = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        screenWidth = windowManager.getDefaultDisplay().getWidth();
        delViewWidth = screenWidth/2;
    }

    public SwipeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,"touch down");
                oldX = x;
                firstDownX =x;
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,"touch up");
                itemView = getChildAt(position-getFirstVisiblePosition());
                if(itemView==null){
                    Log.d(TAG,"当前view为空");
                    return false;
                }
                itemView.getGlobalVisibleRect(rect,point);
                if(firstDownX-x>=delViewWidth/2&&firstDownX-x<delViewWidth){
                    //也满足滑动需求，自动滑动到添加view的宽度位置
                    Log.d(TAG,"还需移动距离:"+(delViewWidth-(firstDownX-x)));
                    itemView.scrollBy(delViewWidth-(firstDownX-x),0);
                    itemView.setTag(true);
                }
                if(firstDownX-x<delViewWidth/2&&firstDownX-x>0){
                    //不满足滑动要求，退回原位置
                    Log.d(TAG,"不满足滑动 ，还需移动距离:"+(x-firstDownX));
                    itemView.scrollBy(x-firstDownX,0);
                    itemView.setTag(false);
                }
                break;
            case MotionEvent.ACTION_MOVE:

                itemView = getChildAt(position-getFirstVisiblePosition());
                if(itemView==null){
                    Log.d(TAG,"当前view为空");
                    return false;
                }
                Rect rect = new Rect();
                Point point = new Point();
                itemView.getGlobalVisibleRect(rect,point);
                Log.d(TAG,"itemView.getX"+point.x);
//                if(firstDownX-x>delViewWidth){
//                    //滑动距离过大||还需要添加一些判断
//                    return super.onTouchEvent(ev);
//                }
                if(point.x<-delViewWidth/2 ){
                    Log.d(TAG,"itemView.getX"+point.x + "del:"+(-delViewWidth/2)+"禁止再往左滑动");
                    if(oldX-x>0)
                    return super.onTouchEvent(ev);
                }
                if(point.x>0){
                    Log.d(TAG,"禁止再往右滑动");
                    if(oldX-x<0) return super.onTouchEvent(ev);
                }
                Log.d(TAG,"firstX:"+firstDownX+"x:"+x);
                itemView.scrollBy(oldX-x,0); //使用scrollBy移动内容
                //然后另外一个删除Button的view移动过来
                oldX = x;
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,"down");
                //找到当前按下的item的position
                position = pointToPosition(x,y);

                Log.d(TAG,"当前点击的position"+position+"第一个po"+getFirstVisiblePosition());
                if(position == AdapterView.INVALID_POSITION){
                    Log.d(TAG,"点击position无效");
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,"up");
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 根据点击坐标判断当前点击的item号
     * @param x
     * @param y
     * @return
     */
//    public int pointToPosition(float x,float y){
//
//    }
}
