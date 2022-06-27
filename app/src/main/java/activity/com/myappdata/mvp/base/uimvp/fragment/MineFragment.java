package activity.com.myappdata.mvp.base.uimvp.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.mvp.base.basemvp.BaseFragment;
import activity.com.myappdata.mvp.base.modelmvp.entity.Province;
import activity.com.myappdata.mvp.base.modelmvp.mvploginentity.mvpuserinfolitit.UserinfoBywebData;
import activity.com.myappdata.mvp.base.presentermvp.IPlacePresenter;
import activity.com.myappdata.mvp.base.presentermvp.IPlacePresenterImpl.PlacePresenterImpl;
import activity.com.myappdata.mvp.base.uimvp.activity.CropImageActivity;
import activity.com.myappdata.mvp.base.uimvp.activity.GoodInfoTypeSelectActivity;
import activity.com.myappdata.mvp.base.uimvp.activity.GoodsLineShowActivity;
import activity.com.myappdata.mvp.base.uimvp.adpter_mvp.PlaceAdapter;
import activity.com.myappdata.mvp.base.uimvp.adpter_mvp.UserInfoAdapter;
import activity.com.myappdata.mvp.base.utilsmvp.LogUtils;
import activity.com.myappdata.mvp.base.view.IProvinceCallbask;
import activity.com.myappdata.util.DialogUtil;
import activity.com.myappdata.util.ToastUtil;
import activity.com.myappdata.util.intentaction.IntentUtil;

/**
 * https://zhuanlan.zhihu.com/p/45701880
 * <p>
 * <p>
 * 自己的fragment
 * <p>
 * 图片裁剪和上传服务端
 */
public class MineFragment extends BaseFragment implements IProvinceCallbask, View.OnClickListener {

    private static final String TAG = "MineFragment";
    private TextView tv_mine;
    private LinearLayout lin_tv_mine;//  商品选择类型的布局
    private Button my_logoutbtn;//  登出系统
    private LinearLayout setpowerlin; //设置预留
    private ImageView img_head;// 顶部的图片
    private static final String FILE_PATH = "/sdcard/syscamera.jpg";// 照片路径
    public  static   int OPEN_CANMER=99;

    private  ImageView  img_setting;//  设置图片  目的图片剪裁

    @Override
    protected int getLayoutInflaterResId() {
        return R.layout.fragment_item_goods_config_info;
    }

    @Override
    protected void initView(View rootView) {

        tv_mine = (TextView) rootView.findViewById(R.id.tv_mine);//  mvp  测试代码
        tv_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MineFragment.this.getActivity(), GoodsLineShowActivity.class);
                startActivity(i);
            }
        });
        // 创建布局管理器
        lin_tv_mine = (LinearLayout) rootView.findViewById(R.id.lin_tv_mine);

        lin_tv_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MineFragment.this.getActivity(), GoodInfoTypeSelectActivity.class);
                startActivity(i);
            }
        });
        my_logoutbtn = (Button) rootView.findViewById(R.id.my_logout);
        my_logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MineFragment.this.getActivity().finish();
            }
        });
        setpowerlin = (LinearLayout) rootView.findViewById(R.id.setpowerlin);
        setpowerlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MineFragment.this.getActivity(), GoodInfoTypeSelectActivity.class);
                startActivity(i);
            }
        });
//        顶部图片裁剪
        img_head = (ImageView) rootView.findViewById(R.id.c1);
        img_head.setOnClickListener(this);
        img_setting =(ImageView) rootView.findViewById(R.id.img_setting);
        img_setting.setOnClickListener(this);
    }

    @Override
    protected void initPresenter() {
        // TODO: 创建 PlacePresenter 对象
    }

    @Override
    protected void loadData() {
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void loadedDatabyuser(List<UserinfoBywebData> provinceList) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_head:
                //加载本地drawable文件
                Drawable mybird1 = getResources().getDrawable(R.drawable.default_head);
                img_head.setImageDrawable(mybird1);
                break;
            case R.id.c1:
                DialogUtil.showCentreDialogbyBoom(MineFragment.this.getActivity());
                DialogUtil dialogUtil = new DialogUtil();
                dialogUtil.buttonClickEvent(new DialogUtil.DialogButtonClick() {
                    @Override
                    public void cilckComfirmButton(View view) {
                        if (Build.VERSION.SDK_INT >= 23) {
                            int checkCallPhonePermission = ContextCompat.checkSelfPermission(MineFragment.this.getActivity(), Manifest.permission.CAMERA);
                            if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                                ActivityCompat.requestPermissions(MineFragment.this.getActivity(),new String[]{Manifest.permission.CAMERA},99);
                                return;
                            }else{
                                showCamera();
                            }
                        } else {
                            showCamera();
                        }
//                        原文链接：https://blog.csdn.net/dubo_csdn/article/details/81743212

                    }

                    @Override
                    public void cilckCancleButton(View view) {
                        Intent intent = new Intent();
                        // 指定开启系统相机的Action
                        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        startActivityForResult(intent, 1);
                    }
                });

                break;
            case R.id.img_setting://  图片裁剪
                IntentUtil.showIntent(MineFragment.this.getActivity(), CropImageActivity.class);
                break;
        }
    }

    private void showCamera() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, 2);

    }

    /***
     * 打开相册的方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            // 从相册返回的数据
//            Log.e(this.getClass().getName(), "Result:" + data.toString());
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                img_head.setImageURI(uri);
                Log.e(this.getClass().getName(), "Uri:" + String.valueOf(uri));
            }
        } else if (requestCode == 0) {
            File file = new File(FILE_PATH);
            Uri uri = Uri.fromFile(file);
            img_head.setImageURI(uri);
        } else if (requestCode == 1) {
//            Log.i(TAG, "默认content地址：" + data.getData());
            if(null==data||null==data.getData()){
                ToastUtil.makeText(MineFragment.this.getContext(),"你还没有选择图片哦");
            }else{
                img_head.setImageURI(data.getData());
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 99:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showCamera();
                } else {
                    Toast.makeText(MineFragment.this.getActivity(), "相机权限禁用了。请务必开启相机权", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
