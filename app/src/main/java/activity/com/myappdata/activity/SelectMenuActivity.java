package activity.com.myappdata.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.util.LunarDecorator;
import activity.com.myappdata.util.ToastUtil;
import activity.com.myappdata.util.linc.foldingmenu.FoldingLayout;
import activity.com.myappdata.util.linc.foldingmenu.OnFoldListener;
import activity.com.myappdata.util.networkutils.NetworkListener;
import activity.com.myappdata.util.networkutils.core.NetType;
import activity.com.myappdata.util.networkutils.core.Network;


public class SelectMenuActivity extends Activity implements View.OnClickListener {
//    private LinearLayout mTrafficLayout;
//    private RelativeLayout mTrafficBarLayout;
//    private FoldingLayout mTrafficFoldingLayout;
private static final String TAG = "SelectMenuActivity";

    private String TAG_ARROW = "service_arrow";
    private String TAG_ITEM = "service_item";

    private View mBottomView;
    private LinearLayout mTrafficLayout, mLifeLayout, mMedicalLayout, mLiveLayout, mPublicLayout;
    private RelativeLayout mTrafficBarLayout, mLifeBarLayout, mMedicalBarLayout, mLiveBarLayout, mPublicBarLayout;
    private FoldingLayout mTrafficFoldingLayout, mLifeFoldingLayout, mMedicalFoldingLayout, mLiveFoldingLayout, mPublicFoldingLayout;

    private final int FOLD_ANIMATION_DURATION = 1000;

    private int mNumberOfFolds = 3;
    private Button btnstopcar;//  停车查询
    private MaterialCalendarView calendarView;
    private Button busnowselect;//  公交时时查询

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menum_main_layout);
        mTrafficLayout = (LinearLayout) findViewById(R.id.traffic_layout);
        mTrafficBarLayout = (RelativeLayout) findViewById(R.id.traffic_bar_layout);
        mTrafficFoldingLayout = ((FoldingLayout) findViewById(R.id.traffic_item));
        btnstopcar = (Button) findViewById(R.id.btnstopcar);
        calendarView = (MaterialCalendarView) findViewById(R.id.calendar_calendarView);
        btnstopcar.setOnClickListener(this);
        ;
        final View mBottomView = findViewById(R.id.bottom_view);

        mTrafficBarLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                handleAnimation(v, mTrafficFoldingLayout, mTrafficLayout, mBottomView);
            }
        });
//   日历组件

//        calendarView.set
        CalendarDay date = CalendarDay.today();
        calendarView.setCurrentDate(date);
        calendarView.setShowOtherDates(MaterialCalendarView.SHOW_OTHER_MONTHS);
        calendarView
                .state()
                .edit()
                //设置一周的第一天是周日还是周一
                .setFirstDayOfWeek(Calendar.SUNDAY)
                //设置日期范围
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        //设置周的文本
        calendarView.setWeekDayLabels(new String[]{"日", "一", "二", "三", "四", "五", "六"});
        //设置年月的title
        calendarView.setTitleFormatter(new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay day) {
                StringBuffer buffer = new StringBuffer();
                int yearOne = day.getYear();
                int monthOne = day.getMonth() + 1;
                buffer.append(yearOne).append("年").append(monthOne).append("月");
                return buffer;
            }
        });

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                CalendarDay temp;
                List<LunarDecorator> lunarDecorators = new ArrayList<>();
                for (int i = 18; i <= 31; i++) {
                    temp = CalendarDay.from(date.getYear(), date.getMonth() - 1, i);
                    add(temp, lunarDecorators);
                }
                for (int i = 0; i <= 31; i++) {
                    temp = CalendarDay.from(date.getYear(), date.getMonth(), i);
                    add(temp, lunarDecorators);
                }
                for (int i = 0; i < 14; i++) {
                    temp = CalendarDay.from(date.getYear(), date.getMonth() + 1, i);
                    add(temp, lunarDecorators);
                }

                calendarView.addDecorators(lunarDecorators);
            }

            private void add(CalendarDay date, List<LunarDecorator> lunarDecorators) {
                LunarDecorator decorator = new LunarDecorator(date);
                lunarDecorators.add(decorator);
            }
        });

        busnowselect = (Button) findViewById(R.id.busnowselect);
        busnowselect.setOnClickListener(this);
        NetworkListener.getInstance().registerObserver(this);
    }

    private void handleAnimation(final View bar, final FoldingLayout foldingLayout, View parent, final View nextParent) {

        final ImageView arrow = (ImageView) parent.findViewById(R.id.traffic_arrow);

        foldingLayout.setFoldListener(new OnFoldListener() {

            @Override
            public void onStartFold(float foldFactor) {

//                bar.setClickable(true);
//                arrow.setBackgroundResource(R.drawable.service_arrow_up);
//                resetMarginToTop(foldingLayout, foldFactor, nextParent);
            }

            @Override
            public void onFoldingState(float foldFactor, float foldDrawHeight) {
//                bar.setClickable(false);
//                resetMarginToTop(foldingLayout, foldFactor, nextParent);
            }

            @Override
            public void onEndFold(float foldFactor) {

//                bar.setClickable(true);
//                arrow.setBackgroundResource(R.drawable.service_arrow_down);
//                resetMarginToTop(foldingLayout, foldFactor, nextParent);
            }
        });

        animateFold(foldingLayout, 1000);

    }

    @SuppressLint("NewApi")
    public void animateFold(FoldingLayout foldLayout, int duration) {
        float foldFactor = foldLayout.getFoldFactor();

        ObjectAnimator animator = ObjectAnimator.ofFloat(foldLayout, "foldFactor", foldFactor, foldFactor > 0 ? 0 : 1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(0);
        animator.setDuration(duration);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btnstopcar://  停车查询   测试页面
                Intent intent = new Intent(SelectMenuActivity.this, JingDongShouyeActivity.class);
                startActivity(intent);

                break;
            case R.id.busnowselect://  公交时时查询
                Intent intent1 = new Intent(SelectMenuActivity.this, ChangeTypeActivity.class);
                startActivity(intent1);


                break;
        }
    }
//    private void resetMarginToTop(View view, float foldFactor, View nextParent) {
//
//        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) nextParent.getLayoutParams();
//        lp.topMargin =(int)( - view.getMeasuredHeight() * foldFactor) + dp2px(MainActivity.this, 10);
//        nextParent.setLayoutParams(lp);
//    }
//    public final static int dp2px(Context context, float dpValue) {
//        float density = context.getResources().getDisplayMetrics().density;
//        return (int) (dpValue * density + 0.5f);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkListener.getInstance().unRegisterObserver(this);
    }

    @Network(netType = NetType.WIFI)
    public void onNetChanged(NetType netType) {
        Log.d(TAG, "onNetChanged: 网络发生改变" + netType.name());
        ToastUtil.makeText(SelectMenuActivity.this,"网络改变");
    }
}
