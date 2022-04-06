package activity.com.myappdata.fragment.typeallkind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.activity.FuZaBuJuActivity;
import activity.com.myappdata.activity.GuideFirstActivity;
import activity.com.myappdata.activity.JingDongShouyeActivity;
import activity.com.myappdata.activity.MainActivity;
import activity.com.myappdata.activity.ManyRecycleviewTitleActivity;
import activity.com.myappdata.activity.SearchInfoActivity;
import activity.com.myappdata.activity.SelectMenuActivity;
import activity.com.myappdata.activity.TestActivity;
import activity.com.myappdata.activity.TwoLevelMenumActivity;
import activity.com.myappdata.activity.VideoActivity;
import activity.com.myappdata.activity.searchbyjindong.SearchByJongDongActivity;
import activity.com.myappdata.activity.video.HotMenums;
import activity.com.myappdata.adapter.Pro_type_adapter;
import activity.com.myappdata.entity.typeallkindentity.Type;
import activity.com.myappdata.mvp.base.uimvp.activity.MVP_LoginActivity;
import activity.com.myappdata.mvp.base.uimvp.activity.MainMvpActivity;
import activity.com.myappdata.retorfitutils.HttpEngine;
import activity.com.myappdata.util.ToastUtil;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Fragment_pro_type02 extends Fragment {

    public static String TAG = "Fragment_pro_type02";
    private ArrayList<Type> list;
    private ImageView hint_img;
    private GridView listView;
    private Pro_type_adapter adapter;
    private Type type;
    private ProgressBar progressBar;
    private String typename;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pro_type02, null);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        hint_img = (ImageView) view.findViewById(R.id.hint_img);
        listView = (GridView) view.findViewById(R.id.listView);
        typename = getArguments().getString("typename");
        ((TextView) view.findViewById(R.id.toptype)).setText(typename);
        GetTypeList();
        adapter = new Pro_type_adapter(Fragment_pro_type02.this.getActivity(), list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                ToastUtil.makeText(Fragment_pro_type02.this.getActivity(), "您选中" + arg3);

                if (arg3 == 0) {
//                    startActivity (Fragment_pro_type.this.getActivity(), JingDongShouyeActivity.class);
                    startActivity(new Intent(Fragment_pro_type02.this.getActivity(), JingDongShouyeActivity.class));
                } else if (arg3 == 1) {
// 京东的搜索框
                    startActivity(new Intent(Fragment_pro_type02.this.getActivity(), SearchByJongDongActivity.class));

                }


            }
        });

        return view;
    }

    private void startActivity(Activity activity, Class<JingDongShouyeActivity> jingDongShouyeActivityClass) {
        Intent i = new Intent(Fragment_pro_type02.this.getActivity(), JingDongShouyeActivity.class);
        startActivity(i);
    }


    private void GetTypeList() {

        HttpEngine.GetHotMenumOnclick(typename, new Observer<List<HotMenums>>() {
            @Override
            public void onSubscribe(Disposable d) {

                Log.i(TAG, "" + d);
            }

            @Override
            public void onNext(List<HotMenums> hotMenums) {
//                Log.i("onNext==222=", hotMenums.getHotmenumsimage() + "---" + hotMenums.getHotmenumsname());
                List<String> liststrmenum = new ArrayList<String>();
                for (int i = 0; i < hotMenums.size(); i++) {
                    Log.i("onNext==222=", hotMenums.get(i).getHotmenumsname());
                    liststrmenum.add(hotMenums.get(i).getHotmenumsname());
                }

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {

            }

        });


        list = new ArrayList<Type>();
        for (int i = 0; i < 1; i++) {
            type = new Type(i, typename + i, "");

            list.add(type);
//             type  添加的是最左侧的数据
            Type type1 = new Type(1, "测试1" + 1, R.mipmap.fushi + "");
            list.add(type1);
        }

        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onResume() {
        super.onResume();


    }
}
