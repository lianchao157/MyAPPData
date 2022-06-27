package activity.com.myappdata.mvp.base.view.showmainactivity.searchview;


import java.io.Serializable;
/****
 * 获取的app 中主页中的菜单数据    实体类本不应该放在这
 */
public class HotMenums implements Serializable {
    public int getHotmenumsid() {
        return hotmenumsid;
    }

    public void setHotmenumsid(int hotmenumsid) {
        this.hotmenumsid = hotmenumsid;
    }

    public String getHotmenumsname() {
        return hotmenumsname;
    }

    public void setHotmenumsname(String hotmenumsname) {
        this.hotmenumsname = hotmenumsname;
    }

    public String getHotmenumsimage() {
        return hotmenumsimage;
    }

    public void setHotmenumsimage(String hotmenumsimage) {
        this.hotmenumsimage = hotmenumsimage;
    }

    public String getHotmenumstype() {
        return hotmenumstype;
    }

    public void setHotmenumstype(String hotmenumstype) {
        this.hotmenumstype = hotmenumstype;
    }

    public String getHotmenumsotherid() {
        return hotmenumsotherid;
    }

    public void setHotmenumsotherid(String hotmenumsotherid) {
        this.hotmenumsotherid = hotmenumsotherid;
    }

    public HotMenums() {
    }

    public HotMenums(int hotmenumsid, String hotmenumsname, String hotmenumsimage, String hotmenumstype, String hotmenumsotherid) {
        this.hotmenumsid = hotmenumsid;

        this.hotmenumsname = hotmenumsname;
        this.hotmenumsimage = hotmenumsimage;
        this.hotmenumstype = hotmenumstype;
        this.hotmenumsotherid = hotmenumsotherid;
    }

    private int hotmenumsid;//  菜单id
    private String hotmenumsname;//    名称
    private String hotmenumsimage;//  图片
    private String hotmenumstype;// 分类标识
    private String  hotmenumsotherid;
}
