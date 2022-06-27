package activity.com.myappdata.mvp.base.uimvp.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

//import com.theartofdev.edmodo.cropper.CropImage;
//import com.theartofdev.edmodo.cropper.CropImageView;

import activity.com.myappdata.R;

/***
 * 图片裁剪  22.4.27  这个类中依赖导入后报错
 */
public class CropImageActivity extends Activity {

    private ImageView showcaijianphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);
//        initView();
    }

//    protected void initView() {
//        showcaijianphone = (ImageView) findViewById(R.id.showcaijianphone);
//        CropImage.activity()
//                .setGuidelines(CropImageView.Guidelines.ON) //开启选择器
//                .setActivityTitle("头像裁剪")
//                .setCropShape(CropImageView.CropShape.RECTANGLE)  //选择矩形裁剪
//                .start(CropImageActivity.this);
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplication(), "取消", Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode) {
//            case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE: {
//                CropImage.ActivityResult result = CropImage.getActivityResult(data);
//
//                if (resultCode == RESULT_OK) {
//                    final Uri resultUri = result.getUri();  //获取裁减后的图片的Uri
//                    showcaijianphone.setImageURI(resultUri);
////                    saveHeadPortrait(resultUri); //我自己写的处理图片的方法
//
//                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                    Log.d("PhotoActivity", "onActivityResult: Error");
//                    Exception exception = result.getError();
//                }
//                break;
//            }


        }
    }
}
//————————————————
//        版权声明：本文为CSDN博主「进击の程序猿！」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/qq_43529443/article/details/106629097