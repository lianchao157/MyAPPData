package activity.com.myappdata.widgets;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import activity.com.myappdata.R;
import activity.com.myappdata.util.ToastUtil;

/****
 * 悬浮球代码
 */
public class FloatingViewService extends Service {
    private WindowManager mWindowManager;
    private View mFloatingView;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mFloatingView = LayoutInflater.from(this).inflate(R.layout.layout_floating_view, null);
        //设置WindowManger布局参数以及相关属性
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        //初始化位置
        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 10;
        params.y = 100;
        //获取WindowManager对象
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mFloatingView, params);
        //关闭FloatingView
        ImageView closeBtn = (ImageView) mFloatingView.findViewById(R.id.close_btn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSelf();
            }
        });
        //录制按钮
        ImageView screenBtn = (ImageView) mFloatingView.findViewById(R.id.screen_btn);
        screenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(FloatingViewService.this, "点击录制", Toast.LENGTH_LONG).show();
                ToastUtil.makeText(FloatingViewService.this,"点击录制");
            }
        });

        //FloatingView的拖动事件
        mFloatingView.findViewById(R.id.floating_container).setOnTouchListener(new View.OnTouchListener() {
            //获取X坐标
            private int startX;
            //获取Y坐标
            private int startY;
            //初始化X的touch坐标
            private float startTouchX;
            //初始化Y的touch坐标
            private float startTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = params.x;
                        startY = params.y;
                        startTouchX = event.getRawX();
                        startTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        params.x = startX + (int) (event.getRawX() - startTouchX);
                        params.y = startY + (int) (event.getRawY() - startTouchY);
                        //更新View的位置
                        mWindowManager.updateViewLayout(mFloatingView, params);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //移除FloatingView
        if (mFloatingView != null) mWindowManager.removeView(mFloatingView);
    }
}
