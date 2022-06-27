package activity.com.myappdata.mvp.base.view.showmainactivity.showtviedeo;

import android.content.Context;


/**
 * 作者：MarkShuai
 * 时间：2017/12/19 15:44
 * 邮箱：MarkShuai@163.com
 * 意图：
 * 21212
 */
public class ShowVideoPresent<T> extends showVideoContract.Present<showVideoContract.View> {

    private Context mContext;
    private ShowVideoModel mModel;

    public ShowVideoPresent(Context mContext) {
        this.mContext = mContext;
        mModel = new ShowVideoModel();
    }

    @Override
    public void fetch() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    void showvideo(showVideoContract.View view) {
        mModel.showvideo(mContext);
        view.showvideo();
    }

}
