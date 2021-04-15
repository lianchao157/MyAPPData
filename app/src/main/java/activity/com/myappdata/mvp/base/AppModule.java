package activity.com.myappdata.mvp.base;

import android.app.Application;
//
//import javax.inject.Singleton;
//
import activity.com.myappdata.application.MyApplication;
//import dagger.Module;
//import dagger.Provides;

//@Module
public class AppModule {


    private MyApplication application;

    public AppModule(MyApplication application){
        this.application=application;
    }

//    @Provides
//    @Singleton
    public Application provideApplication(){
        return application;
    }

}
