package activity.com.myappdata.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lianchao on 2020/12/29.
 */

public abstract  class BaseFragment extends Fragment {
    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        findViewById(view);
        setViewData(view);
        setClickEvent(view);
    }

    /**
     * 设置布局
     *
     * @return
     */
    public abstract int setLayoutId();

    /**
     * findViewById
     */
    public abstract void findViewById(View view);

    /**
     * setViewData
     */
    public abstract void setViewData(View view);

    /**
     * setClickEvent
     */
    public abstract void setClickEvent(View view);

}
