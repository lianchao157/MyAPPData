package activity.com.myappdata.activity;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;

import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;

import activity.com.myappdata.R;
import activity.com.myappdata.adapter.TesetStrAdapter;
import me.next.slidebottompanel.SlideBottomPanel;

/***\
 * android   销量价格 排序价格   代码类
 * https://blog.csdn.net/u013475663/article/details/51791532
 *
 */
public class xiaoliangjiagepaixuActivity extends AppCompatActivity implements View.OnClickListener {
    private SlideBottomPanel sbp;
    private RadioButton rbBank;
    private RadioButton rbRange;
    private RadioButton rbMoney;
    private RadioButton rbTime;
    private ImageView arrow1;
    private ImageView arrow2;
    private ImageView arrow3;
    private ImageView arrow4;
    private ListView lvCreditSort;
    private TesetStrAdapter tsadapter;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoliangjiagepaixu);
        initView();
        initTit();
        setListen();
        initData();
    }

    private void initData() {
        lvCreditSort.setAdapter(new ArrayAdapter<>(xiaoliangjiagepaixuActivity.this, R.layout.list_item, getData()));
    }

    private ArrayList<String> getData() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add("Item " + i);
        }
        return list;
    }

    private void setListen() {
        rbMoney.setOnClickListener(this);
        rbTime.setOnClickListener(this);
        rbRange.setOnClickListener(this);
        rbBank.setOnClickListener(this);
//        sbp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isHidden){
//                    clearArrow();
//                    clearTextColor();
//                }
//            }
//        });

//        sbp.setOnStateChangeListener(new SlideBottomPanel.OnStateChangeListener() {
//            @Override
//            public void Hidden(boolean isHidden) {
//                if (isHidden) {
//                    clearArrow();
//                    clearTextColor();
//                }
//            }
//        });

    }

    private void initView() {
        lvCreditSort = (ListView) findViewById(R.id.lv_credit_sort);
        sbp = (SlideBottomPanel) findViewById(R.id.sbp);
        rbBank = (RadioButton) findViewById(R.id.rb_bank);
        rbMoney = (RadioButton) findViewById(R.id.rb_money);
        rbRange = (RadioButton) findViewById(R.id.rb_range);
        rbTime = (RadioButton) findViewById(R.id.rb_time);
        arrow1 = (ImageView) findViewById(R.id.arrow1);
        arrow2 = (ImageView) findViewById(R.id.arrow2);
        arrow3 = (ImageView) findViewById(R.id.arrow3);
        arrow4 = (ImageView) findViewById(R.id.arrow4);

    }

    private void initTit() {
        rbBank.setText("机构类型");
        rbRange.setText("综合排序");
        rbMoney.setText("金额范围");
        rbTime.setText("贷款期限");
    }

    public void clearTextColor() {
        rbTime.setTextColor(getResources().getColor(R.color.black));
        rbMoney.setTextColor(getResources().getColor(R.color.black));
        rbRange.setTextColor(getResources().getColor(R.color.black));
        rbBank.setTextColor(getResources().getColor(R.color.black));
    }

    public void clearArrow() {
        arrow1.setImageResource(R.mipmap.donw);
        arrow2.setImageResource(R.mipmap.donw);
        arrow3.setImageResource(R.mipmap.donw);
        arrow4.setImageResource(R.mipmap.donw);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_bank:
                clearArrow();
                clearTextColor();
                rbBank.setTextColor(getResources().getColor(R.color.blue));
                arrow1.setImageResource(R.mipmap.donw);
//                sbp.reOpen();
//                lvCreditSort.setAdapter(new ArrayAdapter<>(xiaoliangjiagepaixuActivity.this, R.layout.list_item, getData()));
                tsadapter=new TesetStrAdapter(getData(),xiaoliangjiagepaixuActivity.this);
                lvCreditSort.setAdapter(tsadapter);
//                sbp.reOpen();
                break;
            case R.id.rb_money:
                clearArrow();
                clearTextColor();
                rbMoney.setTextColor(getResources().getColor(R.color.blue));
                arrow3.setImageResource(R.mipmap.donw);
                tsadapter=new TesetStrAdapter(getData(),xiaoliangjiagepaixuActivity.this);
                lvCreditSort.setAdapter(tsadapter);
//                sbp.reOpen();
                break;
            case R.id.rb_range:
                clearArrow();
                clearTextColor();
                rbRange.setTextColor(getResources().getColor(R.color.blue));
                arrow2.setImageResource(R.mipmap.donw);
//                sbp.reOpen();
                break;
            case R.id.rb_time:
                clearArrow();
                clearTextColor();
                rbTime.setTextColor(getResources().getColor(R.color.blue));
                arrow4.setImageResource(R.mipmap.donw);
//                sbp.reOpen();
                break;
            default:
                break;
        }

    }


//    private void reOpenPanel() {
//        final View mPanel = findViewWithTag(TAG_PANEL);
//        if (isPanelShowing()) {
//            if (isAnimating) {
//                mPanel.clearAnimation();
//                isAnimating = false;
//                reOpen();
//                return;
//            }
//            final int t = (int) (mMeasureHeight - mTitleHeightNoDisplay);
//            ValueAnimator animator = ValueAnimator.ofFloat(
//                    ViewHelper.getY(mPanel), mMeasureHeight - mTitleHeightNoDisplay);
//            animator.setInterpolator(mCloseAnimationInterpolator);
//            animator.setTarget(mPanel);
//            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//                    float value = (float) animation.getAnimatedValue();
//                    ViewHelper.setY(mPanel, value);
//                    if (mDarkFrameLayout != null && mIsFade && value < t) {
//                        mDarkFrameLayout.fade((int) ((1 - value / t) * DarkFrameLayout.MAX_ALPHA));
//                    }
//                }
//            });
//            animator.addListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animation) {
//                    isAnimating = true;
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    isAnimating = false;
//                    isPanelShowing = false;
//                    showPanelTitle(mPanel);
//                    displayPanel();
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animation) {
//                    isAnimating = false;
//                    isPanelShowing = false;
//                    showPanelTitle(mPanel);
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animation) {
//                }
//            });
//            animator.setDuration(50);
//            animator.start();
//        } else {
//            displayPanel();
//        }
//    }


    @Override
    public void onBackPressed() {
        if (sbp.isPanelShowing()) {
            sbp.hide();
            return;
        }
        super.onBackPressed();
    }

}
