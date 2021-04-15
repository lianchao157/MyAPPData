package activity.com.myappdata.activity;

import android.util.Log;

import activity.com.myappdata.application.MyApplication;

public class MyRunnable implements Runnable{
    private final static String TAG = "My Runnable ===> ";

    MyApplication myApplication = new MyApplication();

    @Override
    public void run() {
        // TODO Auto-generated method stub
        Log.d(TAG, "run");
//        myApplication.WriterLog();
    }
}


