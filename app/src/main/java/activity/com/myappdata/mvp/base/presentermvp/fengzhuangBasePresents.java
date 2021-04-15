package activity.com.myappdata.mvp.base.presentermvp;

import java.lang.ref.WeakReference;

/***
 * BasePresent：我们这里每次将我们的View层作为泛型传入到我们的Presenter层中，并且作为弱引用将其持有，定义初始化数据的方法，并且做绑定和解绑处理，定义一个抽象的销毁方法
 */
public abstract  class fengzhuangBasePresents <T> {

    /**
     * 持有UI接口的弱引用
     */
    protected WeakReference mViewRef;

    /**
     * 获取数据方法
     */
    public abstract void fetch();

    /**
     * 绑定的方法
     * 在onCreate()中调用
     *
     * @param view
     */
    public void attachView(T view) {
        mViewRef = new WeakReference(view);
    }

    /**
     * 解绑
     * 在onDestroy方法中调用，防止内存泄漏
     */
    public void detach() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }

        onDestroy();
    }

    //释放资源处理
    public abstract void onDestroy();

}
