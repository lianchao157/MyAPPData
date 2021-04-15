package activity.com.myappdata.adapter.tableadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import activity.com.myappdata.fragment.BlankFragment;

/**
 * Created by lianchao on 2021/1/7.
 */

public class MyAdapter  extends FragmentPagerAdapter {
    private List<String> list;
    private  List<Fragment>  listfragmet;
    public MyAdapter(FragmentManager fm, List<String> list, List<Fragment>  listfragmet) {
        super(fm);
        this.list = list;
        this.listfragmet=listfragmet;
    }
    @Override
    public Fragment getItem(int position) {
//        return BlankFragment.newInstance(list.get(position));
        return   listfragmet.get(position);

    }
    @Override
    public int getCount() {
        return listfragmet.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
