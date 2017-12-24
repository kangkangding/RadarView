package com.example.dingkangkang.radarview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by dingkangkang on 2017/12/23.
 */

public class CircleView extends View{

    /**
     * 第一种颜色
     */
    private int mFirstColor;
    /**
     * 第二种颜色
     */
    private int mSecondColor;
    /**
     * 圆弧的宽度
     */
    private int mCircleWidth;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 圆弧的度数
     */
    private int mProgress;
    /**
     * 圆弧绘制的速度
     */
    private int mSpeed;
    /**
     * 是不是开始绘制下一个圆弧
     */
    private boolean isNext = false;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 获取自定义控件的一些值
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleView, defStyleAttr, 0);

        for (int i = 0; i < a.getIndexCount(); i++) {

            switch (a.getIndex(i)) {
                case R.styleable.CircleView_firstColor:
                    mFirstColor = a.getColor(a.getIndex(i), Color.WHITE);
                    break;
                case R.styleable.CircleView_secondColor:
                    mSecondColor = a.getColor(a.getIndex(i), Color.RED);
                    break;
                case R.styleable.CircleView_speed:
                    mSpeed = a.getInt(a.getIndex(i), 20);
                    break;
                case R.styleable.CircleView_circleWidth:
                    mCircleWidth = a.getDimensionPixelOffset(a.getIndex(i), (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();
        mPaint = new Paint();

        //绘图线程
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    mProgress++;
                    if (mProgress == 360) {
                        mProgress = 0;
                        if (!isNext) {
                            isNext = true;
                        } else {
                            isNext = false;
                        }
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(mSpeed); //通过传递过来的速度参数来决定线程休眠的时间从而达到绘制速度的快慢
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int center = getWidth() / 2;
        int radius = center - mCircleWidth / 2;
        mPaint.setStrokeWidth(mCircleWidth); // 设置圆环的宽度
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius); // 用于定义的圆弧的形状和大小的界限

        if (!isNext) {// 第一颜色的圈完整，第二颜色跑
            mPaint.setColor(mFirstColor); // 设置圆环的颜色
            canvas.drawCircle(center, center, radius, mPaint); // 画出圆环
            mPaint.setColor(mSecondColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        } else {
            mPaint.setColor(mSecondColor); // 设置圆环的颜色
            canvas.drawCircle(center, center, radius, mPaint); // 画出圆环
            mPaint.setColor(mFirstColor); // 设置圆环的颜色
            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        }
    }
}
