package activity.com.myappdata.fragment.fragmentqiantao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import activity.com.myappdata.R;

public class FragmentLeft extends Fragment implements View.OnClickListener {

    private View view_fragment;
    private InnerFragmentLeft innerFragmentLeft;
    private InnerFragmentRight innerFragmentRight;
    private Fragment mContent;
    private Button[] mTabs;
    private int currentTabIndex;
    private int index;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreateView(inflater, container, savedInstanceState);
        view_fragment = inflater.inflate(R.layout.fragment_left, null);
        initfindViewById();
        initFragment();
        return view_fragment;
    }

    private void initfindViewById() {
        mTabs = new Button[2];
        mTabs[0] = (Button) view_fragment
                .findViewById(R.id.btn_tab_clock_inner_left);
        mTabs[1] = (Button) view_fragment
                .findViewById(R.id.btn_tab_bell_inner_left);
        mTabs[0].setOnClickListener(this);
        mTabs[1].setOnClickListener(this);
    }

    private void initFragment() {
        innerFragmentLeft = new InnerFragmentLeft();
        innerFragmentRight = new InnerFragmentRight();
        FragmentTransaction beginTransaction = getFragmentManager()
                .beginTransaction();
        beginTransaction
                .add(R.id.rl_left_fragment_container, innerFragmentLeft);
        beginTransaction.commitAllowingStateLoss();
        mContent = innerFragmentLeft;
        currentTabIndex = 0;
        mTabs[currentTabIndex].setSelected(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tab_clock_inner_left:
                index = 0;
                if (null == innerFragmentLeft) {
                    innerFragmentLeft = new InnerFragmentLeft();
                }
                switchContent(innerFragmentLeft);
                break;
            case R.id.btn_tab_bell_inner_left:
                index = 1;
                if (null == innerFragmentRight) {
                    innerFragmentRight = new InnerFragmentRight();
                }
                switchContent(innerFragmentRight);
                break;
            default:
                break;
        }
    }

    /**
     * 修改显示的内容 不会重新加载
     **/
    public void switchContent(Fragment to) {
        try {
            if (mContent != to) {
                FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();
                if (!to.isAdded()) { // 先判断是否被add过
                    transaction.hide(mContent)
                            .add(R.id.rl_left_fragment_container, to)
                            .commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
                } else {
                    transaction.hide(mContent).show(to)
                            .commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
                }
                mContent = to;
            }
            mTabs[currentTabIndex].setSelected(false);
            // 把当前tab设为选中状态
            mTabs[index].setSelected(true);
            currentTabIndex = index;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
//————————————————
//    版权声明：本文为CSDN博主「尼古拉斯阳」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/linder_qzy/article/details/50739541
