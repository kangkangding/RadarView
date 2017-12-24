package com.example.dingkangkang.radarview;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class MainActivity extends AppCompatActivity {

    private RadarView radarView;
    private double x=0;

    private int width=400,height=400;//布局设置的尺寸是dp，代码中设置的是像素px，所以需要转换

    private int[] count=new int[10],count2=new int[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radarView = (RadarView) findViewById(R.id.rv_rader);

        width = dp2px(400);
        height = dp2px(400);

        CountDownTimer timer = new CountDownTimer(600000,50) {
            @Override
            public void onTick(long millisUntilFinished) {


//                圆点坐标：(x0,y0)
//                半径：r
//                角度：a0
//
//                则圆上任一点为：（x1,y1）
//                x1   =   x0   +   r   *   cos(ao   *   3.14   /180   )
//                y1   =   y0   +   r   *   sin(ao   *   3.14   /180   )

                for(int i=0;i<10;i++){
                    count[i]= (int) (width/2+width/2*cos((x-1-i)*3.14/180));
                    count2[i]= (int) (width/2+width/2*sin((x-1-i)*3.14/180));
                }

                radarView.setLocation2(count,count2);
                radarView.setLocation((width/2+width/2*cos(x*3.14/180)),(height/2+width/2*sin(x*3.14/180)));
                Log.d("1111",(width/2+width/4*cos(x*3.14/180))+"::::::"+(height/2+width/4*sin(x*3.14/180)));

                if(x>=360){
                    x=0;
                }
                x=x+6;
            }

            @Override
            public void onFinish() {

            }
        }.start();


    }


    protected int dp2px(int dp){
        return  (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,dp,
                getResources().getDisplayMetrics());
    }
}
