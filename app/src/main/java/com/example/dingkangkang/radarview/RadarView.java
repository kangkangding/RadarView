package com.example.dingkangkang.radarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by dingkangkang on 2017/12/23.
 */

public class RadarView extends View{
    private int height=0;
    private int width=0;
    private double xheight=4;
    private double xwidth=4;

    private Paint p;

    private int[] count,count2;

    public  void setLocation2(int[] counts,int[] count2s){
        count=counts;
        count2 = count2s;
    }

    public void setLocation(double x, double y){
        xwidth=x;
        xheight=y;
    }
    public RadarView(Context context) {
        super(context);
        height = getHeight();
        width = getWidth();

        p = new Paint();
        p.setColor(Color.BLUE);
        p.setStrokeWidth(3);
        p.setAntiAlias(true);

        p.setStyle(Paint.Style.FILL);
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        height = getHeight();
        width = getWidth();

        p = new Paint();
        p.setColor(Color.BLUE);
        p.setStrokeWidth(3);
        p.setAntiAlias(true);

        p.setStyle(Paint.Style.FILL);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        height = getHeight();
        width = getWidth();

        p = new Paint();
        p.setColor(Color.BLUE);
        p.setStrokeWidth(3);

        p.setAntiAlias(true);

        p.setStyle(Paint.Style.FILL);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        height = getHeight();
        width = getWidth();

        p = new Paint();
        p.setColor(Color.BLUE);
        p.setStrokeWidth(3);
        p.setAntiAlias(true);

        p.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        p.setColor(Color.GREEN);
        for(int i=0;i<128;i=i+3){
            for (int j=0;j<128;j=j+3){
                canvas.drawCircle(width*i/128,height*j/128,width/256,p);
            }

        }


        p.setStyle(Paint.Style.STROKE);

        p.setColor(Color.BLUE);
        canvas.drawCircle(width/2,height/2,width/2,p);
        canvas.drawCircle(width/2,height/2,width*3/8,p);
        canvas.drawCircle(width/2,height/2,width/4,p);
        canvas.drawCircle(width/2,height/2,width/8,p);
        canvas.drawCircle(width/2,height/2,width/16,p);



        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.RED);
        canvas.drawCircle(width/2,height/2,width/64,p);

        canvas.drawLine(width/2,height/2,(float) xwidth,(float)xheight,p);

        for(int i=0;i<count.length;i++){
            p.setColor(Color.argb((10-i)+(10-i)*10,255,0,0));
            canvas.drawLine(width/2,height/2,(float) count[i],(float)count2[i],p);
        }


        Log.d("2222",(float) xwidth+":::::"+(float)xheight);

        postInvalidateDelayed(10);



    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

    }



}
