package activity.com.myappdata.MVPEaily.present;

import activity.com.myappdata.MVPEaily.model.Model;
import activity.com.myappdata.MVPEaily.view.IView;

/**
 * Presenter层接口---控制Model层的数据操作及调用View层的UI操作来完成“中间人”工作.
 * 用于model和view的相关方法的调用
 */
public class Presenter implements
        IPresenter, Model.LoadDataCallback {

    private final IView mView;
    private final Model mModel;

    public Presenter(IView view) {
        mView = view;
        mModel = new Model();
    }

    @Override
    public void loadData() {
        mView.showLoadingProgress("加载数据中");
        mModel.getData(Presenter.this);
    }

    @Override
    public void success(String data) {

        mView.showData(data);
    }

    @Override
    public void failure() {

    }
}
