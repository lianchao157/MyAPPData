package activity.com.myappdata.jingdongtuijian;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import activity.com.myappdata.entity.Beandata;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyDataMoudle  implements DataMoudle {
    @Override
    public void getData(String url, final DataPresenter dataPresenter) {

        OkhtttpUtils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String json = response.body().string();

                Gson gson = new Gson();

                Beandata beandata = gson.fromJson(json, Beandata.class);

                List<Beandata> list= (List<Beandata>) beandata.getData();

                dataPresenter.success(list);

            }
        });
    }
}
