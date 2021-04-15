package activity.com.myappdata.adapter.jingdongadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lianchao on 2020/9/11.
 */

public class GuideFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragments;
    public void setmFragments(List<Fragment> fragments) {
        if (fragments == null) {
            mFragments = new ArrayList<>();
        } else {
            mFragments = fragments;
        }
    }

    public GuideFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
