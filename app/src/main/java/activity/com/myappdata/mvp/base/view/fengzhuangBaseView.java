package activity.com.myappdata.mvp.base.view;

/***
 * 封装下的baseview
 * ：这个baseView没有做太多的处理，你可以添加一些属于你自己的公共业务逻辑
 */
public interface  fengzhuangBaseView<T> {

    //显示进度框
    void showDialog();

    //隐藏进度框
    void dismissDialog();
}
