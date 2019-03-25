package com.yehowah.myviewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.view.View;

public class PathView extends View {
    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public PathView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PathView(Context context,  AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //region --范围
    private void drawRegion(Canvas canvas,Region rgn,Paint paint){
        RegionIterator iterator = new RegionIterator(rgn);
        Rect rect = new Rect();
        while(iterator.next(rect)){
            canvas.drawRect(rect,paint);//画矩形
        }
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);


        //画一个三角形
        Path path = new Path();
        path.moveTo(10,10);//设置起始点
        path.lineTo(10,100);//第一条直线的终点，也是第二条直线的起点
        path.lineTo(300,100);//画第二条直线
        path.close();//闭环

        canvas.drawPath(path,paint);//直线路径

        path.moveTo(10,120);//起始点
        RectF rectF= new RectF(100,120,200,220);//矩形
        path.arcTo(rectF,0,270,true);//360度，将不会画,true将不会联系上面的起始点
        canvas.drawPath(path,paint);//逆时针，从最右边开始画



        paint.setStyle(Paint.Style.FILL);
        Region region = new Region(new Rect(10,250,260,450));





    }
}
