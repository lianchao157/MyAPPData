package activity.com.myappdata.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.adapter.jingdongadapter.RecycleViewAdapter;

/**
 * Created by lianchao on 2021/1/7.
 */

public class HomePowerFragment extends Fragment {
    public static HomePowerFragment newInstance(String text) {
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        HomePowerFragment TabFragment = new HomePowerFragment();
        TabFragment.setArguments(bundle);
        return TabFragment;
    }

    public static Fragment newInstance() {
        HomePowerFragment fragment = new HomePowerFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_homepower, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecycleViewAdapter());
        return rootView;
    }

    class MyAdapter extends FragmentPagerAdapter {
        private List<String> list;

        public MyAdapter(FragmentManager fm, List<String> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return HomePowerFragment.newInstance(list.get(position));
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }
    }
}
