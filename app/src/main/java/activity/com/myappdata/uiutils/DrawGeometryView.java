package activity.com.myappdata.uiutils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/***
 * 目标画一个椭圆形对 菜单
 */
public class DrawGeometryView  extends View {
    public DrawGeometryView(Context context) {
        super(context);
    }

    public DrawGeometryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawGeometryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 方法 说明 drawRect 绘制矩形 drawCircle 绘制圆形 drawOval 绘制椭圆 drawPath 绘制任意多边形
         * drawLine 绘制直线 drawPoin 绘制点
         */
        // 创建笔刷
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setStrokeWidth(4.0f);
        p.setTextSize(30f);

//        原文链接：https://blog.csdn.net/CrazyMo_/article/details/48931681
        RectF oval2 = new RectF(60, 400, 180, 520);// 设置个新的长方形，扫描测量
        canvas.drawArc(oval2, 180, 130, true, p);
        // 画弧，第一个参数是RectF：该类是第二个参数是角度的开始，第三个参数是多少度，第四个参数是真的时候画扇形，是假的时候画弧线
        //画椭圆，把oval改一下
        oval2.set(210,420,350,460);
        canvas.drawOval(oval2, p);
    }
}
