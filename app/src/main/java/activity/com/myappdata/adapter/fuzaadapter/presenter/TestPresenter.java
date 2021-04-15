package activity.com.myappdata.adapter.fuzaadapter.presenter;


import java.util.List;

import activity.com.myappdata.adapter.fuzaadapter.base.Module;
import activity.com.myappdata.adapter.fuzaadapter.utils.DatasUtil;
import activity.com.myappdata.entity.TestEntity;

/**
 * package: com.easyandroid.sectionadapter.mvp.presenter.TestPresenter
 * author: gyc
 * description:
 * time: create at 2017/7/8 9:53
 */

public class TestPresenter implements Module.Presenter{

    private Module.View view;

    public TestPresenter(Module.View view) {
        this.view = view;
    }

    @Override
    public void loadData(int loadType) {
        List<TestEntity.BodyBean.EListBean> datas = DatasUtil.createDatas();
        if(view!=null){
            view.updateList(loadType, datas);
        }
    }
}
