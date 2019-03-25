package com.yehowah.myviewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
//book 自定义控件
public class BasisView extends View {


    public BasisView(Context context) {
        super(context);
    }

    public BasisView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BasisView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BasisView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置画笔的基本属性
        Paint paint = new Paint();
//        paint.setColor(0xFFFF0000);//红色
        paint.setColor(Color.RED);          //设置画笔颜色
//        paint.setStyle(Paint.Style.STROKE); //设置填充样式 stroke 描边
        paint.setStyle(Paint.Style.FILL); //设置填充样式 fill 填充
        paint.setStrokeWidth(50);           //设置画笔宽度
        canvas.drawCircle(190,200,150, paint);//半径150px

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(0x7EFFFF00);//黄色
        canvas.drawCircle(590,200,100, paint);




    }
}
