package activity.com.myappdata.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import activity.com.myappdata.R;
import activity.com.myappdata.typeui.blackbackgrould.GrayFrameLayout;

/****
 * android   第一次打开页面进行   指导页面
 *
 * 灰色背景图
 *
 *
 * 总结代码  写了两个  自定义的view 一个是   在布局修改
 *
 * 灰色北京
 */
public class GuideFirstActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_first);
    }
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        try {
            if ("FrameLayout".equals(name)) {
                int count = attrs.getAttributeCount();
                for (int i = 0; i < count; i++) {
                    String attributeName = attrs.getAttributeName(i);
                    String attributeValue = attrs.getAttributeValue(i);
                    if (attributeName.equals("id")) {
                        int id = Integer.parseInt(attributeValue.substring(1));
                        String idVal = getResources().getResourceName(id);
                        if ("android:id/content".equals(idVal)) {
                            GrayFrameLayout grayFrameLayout = new GrayFrameLayout(context, attrs);
//                            grayFrameLayout.setWindow(getWindow());
                            return grayFrameLayout;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onCreateView(name, context, attrs);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {


        }
    }
}
