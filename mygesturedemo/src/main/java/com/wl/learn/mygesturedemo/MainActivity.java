package com.wl.learn.mygesturedemo;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, View.OnTouchListener {


    private static final String TAG = MainActivity.class.getSimpleName();
    private GestureDetectorCompat detector;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = ((TextView) (TextView) findViewById(R.id.textView));
        textView.setOnTouchListener(this);
        detector = new GestureDetectorCompat(this, this);
        detector.setOnDoubleTapListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //要拿到该事件的绝对坐标,再将事件交给手势处理
        MotionEvent obtain = MotionEvent.obtain(event.getDownTime(), event.getEventTime(), event.getAction(), event.getRawX(), event.getRawY(), event.getMetaState());
        return detector.onTouchEvent(obtain);
    }

    /**
     * @param e
     * @return 这里返回值为true 才会执行后面的方法
     */
    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG, "onDown() called with: e = [" + e + "]");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG, "onShowPress() called with: e = [" + e + "]");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, "onSingleTapUp() called with: e = [" + e + "]");
        //这里要返回true
        return false;
    }

    /**
     * @param e1
     * @param e2
     * @param distanceX 位移是前者减后者,反的
     * @param distanceY
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "onScroll() called with: e1 = [" + e1 + "], e2 = [" + e2 + "], distanceX = [" + distanceX + "], distanceY = [" + distanceY + "]");
        //跟随手指滑动
        ViewCompat.setTranslationX(textView, ViewCompat.getTranslationX(textView) - distanceX);
        ViewCompat.setTranslationY(textView, ViewCompat.getTranslationY(textView) - distanceY);
        return false;
    }

    /**
     * 按住,600毫秒
     *
     * @param e
     */
    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG, "onLongPress() called with: e = [" + e + "]");
    }

    /**
     * 一秒中之内产生的位移 , 快速移动抬起后 和listview惯性滑动相似
     *
     * @param e1
     * @param e2
     * @param velocityX 速度
     * @param velocityY
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG, "onFling() called with: e1 = [" + e1 + "], e2 = [" + e2 + "], velocityX = [" + velocityX + "], velocityY = [" + velocityY + "]");
        return false;
    }

    /**
     * 确认单击
     *
     * @param e
     * @return
     */
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    /**
     * 双击操作
     *
     * @param e
     * @return
     */
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        //放大操作
        ViewCompat.setScaleX(textView, ViewCompat.getScaleX(textView) + 1);
        ViewCompat.setScaleY(textView, ViewCompat.getScaleY(textView) + 1);
        if (ViewCompat.getScaleX(textView) > 5) {
            ViewCompat.setScaleX(textView, 1);
            ViewCompat.setScaleY(textView, 1);
        }
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }


}
