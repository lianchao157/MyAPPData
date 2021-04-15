package activity.com.myappdata.jingdongtuijian;

import java.util.List;

import activity.com.myappdata.entity.Beandata;

public class MyDataPresenter  implements DataPresenter {

    private final MyDataMoudle myDataMoudle;
    DataView dataView;

    public  MyDataPresenter(DataView dataView){
        this.dataView = dataView;
        myDataMoudle = new MyDataMoudle();
    }

    @Override
    public void success(List<Beandata> list) {
        dataView.toBackHome(list);
    }

    @Override
    public void eroor() {

    }

    public void netWork(String url) {

        myDataMoudle.getData(url,this);


    }
}
