package activity.com.myappdata.application;

import android.app.Application;
import android.content.Context;

import activity.com.myappdata.mvp.base.AppModule;
import activity.com.myappdata.mvp.base.modelmvp.AppServiceModule;
import activity.com.myappdata.mvp.base.modelmvp.entity.UserInfoByLogin;
import activity.com.myappdata.server.CrashHandler;
import activity.com.myappdata.util.networkutils.NetworkListener;

public class MyApplication  extends Application{
    private String mylabel ;
    public String getLabel(){
        return mylabel;
    }
    public void setLabel(String s){
        this.mylabel = s;
    }
    CrashHandler handler = null;


//    mvp模式下
//    private AppComponent appComponent;

public  static  UserInfoByLogin userInfoByLogin;


    public static MyApplication get(Context context){
        return (MyApplication)context.getApplicationContext();
    }


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        setLabel("Welcome!"); //初始化全局变量
        NetworkListener.getInstance().init(this);
        CrashHandler crashLog = CrashHandler.getInstance();
        crashLog.init(getApplicationContext());


        userInfoByLogin=new UserInfoByLogin();
//        mvp

//        appComponent=DaggerAppComponent.builder()
//                .appModule(new AppModule(this))
//                .apiServiceModule(new ApiServiceModule())
//                .appServiceModule(new AppServiceModule())
//                .build();

    }
//
//    public AppComponent getAppComponent() {
//        return appComponent;
//    }


    /**
     * 登陆代码  判断是否登陆
     * @return
     */
//    public  String    getUserlogininf(){
//
//        return null;
//    }
}