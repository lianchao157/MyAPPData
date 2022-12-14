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
     * ????????????????????? ??????????????????
     **/
    public void switchContent(Fragment to) {
        try {
            if (mContent != to) {
                FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction();
                if (!to.isAdded()) { // ??????????????????add???
                    transaction.hide(mContent)
                            .add(R.id.rl_left_fragment_container, to)
                            .commitAllowingStateLoss(); // ???????????????fragment???add????????????Activity???
                } else {
                    transaction.hide(mContent).show(to)
                            .commitAllowingStateLoss(); // ???????????????fragment??????????????????
                }
                mContent = to;
            }
            mTabs[currentTabIndex].setSelected(false);
            // ?????????tab??????????????????
            mTabs[index].setSelected(true);
            currentTabIndex = index;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
//????????????????????????????????????????????????
//    ????????????????????????CSDN???????????????????????????????????????????????????CC 4.0 BY-SA???????????????????????????????????????????????????????????????
//    ???????????????https://blog.csdn.net/linder_qzy/article/details/50739541
