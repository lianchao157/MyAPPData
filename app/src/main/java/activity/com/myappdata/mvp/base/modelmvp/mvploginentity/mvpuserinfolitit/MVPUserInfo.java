package activity.com.myappdata.mvp.base.modelmvp.mvploginentity.mvpuserinfolitit;

import activity.com.myappdata.mvp.base.modelmvp.entity.UserInfo;

/**
 * 分页模式配合
 * GetUserInfoByMVPInteractor
 * 和mvp下findfragment
 */
public class MVPUserInfo {
    public MVPUserInfo(int code, String msg, MVPUserinfoData data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    private String msg;

    public MVPUserinfoData getData() {
        return data;
    }

    public void setData(MVPUserinfoData data) {
        this.data = data;
    }

    private MVPUserinfoData data;
}
