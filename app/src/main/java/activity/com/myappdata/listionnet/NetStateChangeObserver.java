package activity.com.myappdata.listionnet;

public interface NetStateChangeObserver {
    void onNetDisconnected();
    void onNetConnected(NetworkType networkType);
}
