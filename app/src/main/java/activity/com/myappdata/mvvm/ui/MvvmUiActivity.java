package activity.com.myappdata.mvvm.ui;

import android.app.Activity;
//import android.databinding.DataBindingUtil;
//import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import activity.com.myappdata.R;
import activity.com.myappdata.api.retorfitpack.entity.Data;
import activity.com.myappdata.api.retorfitpack.entity.Root;

/****
 *
 * 创建 一个mvvm 的框架显示activity
 * 具体来说，就是针对每个Activity或者Fragment的布局，在编译阶段，会生成一个ViewDataBinding类的对象，
 * 该对象持有Activity要展示的数据和布局中的各个view的引用。同时还有如下优势：将数据分解到各个view、在UI线程上更新数据、
 * 监控数据的变化，实时更新，这样一来，你要展示的数据已经和展示它的布局紧紧绑定在了一起。我认为这才是DataBinding真正的魅力所在。
 ————————————————
 版权声明：本文为CSDN博主「william~」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 原文链接：https://blog.csdn.net/u014602228/article/details/99571722
 */
public class MvvmUiActivity extends Activity {
    //    private ActivityMvvmMainBinding binding;
    Root root = new Root();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm_ui);
//        setContentView(R.layout.activity_mvvm_ui);

        Data user = new Data("Chintan Soni", "+91 9876543210", "3333", "33444");
//        binding.setContact(user);
    }
}
