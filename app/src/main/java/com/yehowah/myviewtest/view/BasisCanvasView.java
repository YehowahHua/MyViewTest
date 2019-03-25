package com.yehowah.myviewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class BasisCanvasView extends View {
    public BasisCanvasView(Context context) {
        super(context);
    }

    public BasisCanvasView(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public BasisCanvasView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BasisCanvasView(Context context,  AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画布背景设置
//        canvas.drawColor(0xffffffff);//0xAARRGGBB A-Alpha透明度
//        canvas.drawARGB(255,255,0,255);
//        canvas.drawRGB(255,0,255);//默认a-255,透明度255  紫色


        Paint paint = new Paint(); //这是测试，真实项目中不能在onDraw中创建变量，容易出现GC回收问题
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(50);
        //画直线
//        canvas.drawLine(100,100,200,200, paint);


        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10);
        //画直线
//        canvas.drawLine(400,100,500,200, paint);
        float[] pts = {10,10,100,100,200,200,400,400};
//        canvas.drawLines(pts,paint);//只画出2条线，(10,10)->(100,100),(200,200)->(400,400)
        canvas.drawLines(pts,2,4,paint);//从pts取出4个数，从第3个数开始

        //画点
        paint.setStrokeWidth(15);
        canvas.drawPoint(300,300,paint);


        //矩形
        Rect rect=new Rect(10,10,200,200);//这是测试，真实项目中不能在onDraw中创建变量，容易出现GC回收问题
//        RectF rectF=new RectF(10f,10f,200f,200f);//可输入float
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect,paint);
    }
}
