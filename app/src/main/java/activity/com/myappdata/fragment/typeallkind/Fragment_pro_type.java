package activity.com.myappdata.fragment.typeallkind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

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
import activity.com.myappdata.activity.video.VideobyActivity;
import activity.com.myappdata.adapter.Pro_type_adapter;
import activity.com.myappdata.entity.typeallkindentity.Type;
import activity.com.myappdata.mvp.base.uimvp.activity.MVP_LoginActivity;
import activity.com.myappdata.mvp.base.uimvp.activity.MainMvpActivity;
import activity.com.myappdata.util.ToastUtil;

public class Fragment_pro_type extends Fragment {
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
        View view = inflater.inflate(R.layout.fragment_pro_type, null);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        hint_img = (ImageView) view.findViewById(R.id.hint_img);
        listView = (GridView) view.findViewById(R.id.listView);
        typename = getArguments().getString("typename");
        ((TextView) view.findViewById(R.id.toptype)).setText(typename);
        GetTypeList();
        adapter = new Pro_type_adapter(Fragment_pro_type.this.getActivity(), list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                ToastUtil.makeText(Fragment_pro_type.this.getActivity(), "您选中" + arg3);

                if (arg3 == 0) {
//                    startActivity (Fragment_pro_type.this.getActivity(), JingDongShouyeActivity.class);
                    startActivity(new Intent(Fragment_pro_type.this.getActivity(), JingDongShouyeActivity.class));
                } else if (arg3 == 1) {

                    startActivity(new Intent(Fragment_pro_type.this.getActivity(), SelectMenuActivity.class));
                } else if (arg3 == 2) {
                    startActivity(new Intent(Fragment_pro_type.this.getActivity(), MainMvpActivity.class));

                } else if (arg3 == 3) {//  知道页面b
                    startActivity(new Intent(Fragment_pro_type.this.getActivity(), GuideFirstActivity.class));
                } else if (arg3 == 4) {// 详细页面
                    startActivity(new Intent(Fragment_pro_type.this.getActivity(), MainActivity.class));
                } else if (arg3 == 5) {  //adapter  复杂布局

                    startActivity(new Intent(Fragment_pro_type.this.getActivity(), FuZaBuJuActivity.class));
                } else if (arg3 == 6) {  //adapter  复杂布局

                    startActivity(new Intent(Fragment_pro_type.this.getActivity(), VideoActivity.class));
                } else if (arg3 == 7) { ///  recycleview   下多样式布局
                    startActivity(new Intent(Fragment_pro_type.this.getActivity(), ManyRecycleviewTitleActivity.class));

                } else if (arg3 == 8) {
                    startActivity(new Intent(Fragment_pro_type.this.getActivity(), TestActivity.class));

                } else if (arg3 == 9) {

                    startActivity(new Intent(Fragment_pro_type.this.getActivity(), MVP_LoginActivity.class));//  MVp 模式代码

                } else if (arg3 == 10) {
                    startActivity(new Intent(Fragment_pro_type.this.getActivity(), TestActivity.class));//  listview  实现复杂布局
                } else if (arg3 == 11) {
                    startActivity(new Intent(Fragment_pro_type.this.getActivity(), TwoLevelMenumActivity.class));//  listview  实现复杂布局
                } else if (arg3 == 12) {
                    startActivity(new Intent(Fragment_pro_type.this.getActivity(), SearchInfoActivity.class));//  listview  实现复杂布局
                } else if (arg3 == 13) {
                    startActivity(new Intent(Fragment_pro_type.this.getActivity(), VideobyActivity.class));//  listview  实现复杂布局

                }

            }
        });

        return view;
    }

//    private void startActivity(FragmentActivity activity, Class<JingDongShouyeActivity> jingDongShouyeActivityClass) {
//        Intent  i=new Intent(Fragment_pro_type.this.getActivity(), JingDongShouyeActivity.class);
//        startActivity(i);
//    }


    private void GetTypeList() {
        list = new ArrayList<Type>();
        for (int i = 0; i < 1; i++) {
            type = new Type(i, typename + i, "");

            list.add(type);
//             type  添加的是最左侧的数据
            Type type1 = new Type(1, "电脑办公" + 1, R.mipmap.fushi + "");
            Type type2 = new Type(1, "mvp" + 1, R.mipmap.fushidi + "");
            Type type3 = new Type(1, "指导页面b" + 1, "");
            Type type4 = new Type(1, "详细页面" + 1, "");
            Type type5 = new Type(1, "复杂布局" + 1, "");
            Type type6 = new Type(1, "视频" + 1, "");
            Type type7 = new Type(1, "多样是布局" + 1, "");
            Type type8 = new Type(1, "错误日志测试和vide循环播放" + 1, "");
            Type type9 = new Type(1, "MVP代码" + 1, "");
            Type type10 = new Type(1, "listview复杂布局" + 1, "");


            Type type11 = new Type(1, "recycleview 嵌套", "嵌套");
            Type type12 = new Type(1, "toobar嵌套", "嵌套");

            Type type13 = new Type(1, "retorfit代码网络请求", "");
            list.add(type1);
            list.add(type2);
            list.add(type3);
            list.add(type4);
            list.add(type5);
            list.add(type6);
            list.add(type7);
            list.add(type8);
            list.add(type9);
            list.add(type10);
            list.add(type11);
            list.add(type12);
            list.add(type13);
        }

        progressBar.setVisibility(View.GONE);
    }
}
