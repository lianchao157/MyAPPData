package activity.com.myappdata.fragment.fragmentqiantao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import activity.com.myappdata.R;

public class FragmentRight extends Fragment {

    private View view_fragment;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreateView(inflater, container, savedInstanceState);
        view_fragment = inflater.inflate(R.layout.fragment_right, null);
        return view_fragment;
    }
//
//        ————————————————
//        版权声明：本文为CSDN博主「尼古拉斯阳」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/linder_qzy/article/details/50739541
}