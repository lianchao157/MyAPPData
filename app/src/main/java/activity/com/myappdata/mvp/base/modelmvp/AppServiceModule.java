package activity.com.myappdata.mvp.base.modelmvp;

import activity.com.myappdata.mvp.base.modelmvp.entity.City;
//import dagger.Provides;

public class AppServiceModule {


    City provideUser() {
        City user = new City();
        user.setId(1);
        user.setName("hello world");
        return user;
    }

}
