package activity.com.myappdata.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/***
 * 顶部菜单代码
 * findfragment 的顶部菜单
 * 2021.4.22
 * spring bootfour
 */
public class FindFragmetnMenuTitleEntity {
    public FindFragmetnMenuTitleEntity(String pp_menum_textimage, String pp_menum_texttitle, String pp_menum_state) {
        this.pp_menum_textimage = pp_menum_textimage;
        this.pp_menum_texttitle = pp_menum_texttitle;
        this.pp_menum_state = pp_menum_state;
    }

    private String pp_menum_textimage;  // 图片
    private String pp_menum_texttitle;  //  文字
    private String pp_menum_state;       // 状态

    @Override
    public String toString() {
        return "PPMenumEntity{" +
                "pp_menum_textimage='" + pp_menum_textimage + '\'' +
                ", pp_menum_texttitle='" + pp_menum_texttitle + '\'' +
                ", pp_menum_state='" + pp_menum_state + '\'' +
                '}';
    }

    public FindFragmetnMenuTitleEntity() {
    }

    public String getPp_menum_textimage() {
        return pp_menum_textimage;
    }

    public void setPp_menum_textimage(String pp_menum_textimage) {
        this.pp_menum_textimage = pp_menum_textimage;
    }

    public String getPp_menum_texttitle() {
        return pp_menum_texttitle;
    }

    public void setPp_menum_texttitle(String pp_menum_texttitle) {
        this.pp_menum_texttitle = pp_menum_texttitle;
    }

    public String getPp_menum_state() {
        return pp_menum_state;
    }

    public void setPp_menum_state(String pp_menum_state) {
        this.pp_menum_state = pp_menum_state;
    }
}
