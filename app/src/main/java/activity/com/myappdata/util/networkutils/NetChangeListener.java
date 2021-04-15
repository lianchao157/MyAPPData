package activity.com.myappdata.util.networkutils;

import activity.com.myappdata.util.networkutils.core.NetType;

public interface NetChangeListener {
    /**
     * 已连接
     * @param netType NetType
     */
    void onConnect(NetType netType);

    /**
     * 连接断开
     */
    void onDisConnect();
}
