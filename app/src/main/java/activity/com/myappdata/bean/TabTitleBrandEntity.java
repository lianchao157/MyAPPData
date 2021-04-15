package activity.com.myappdata.bean;

import java.io.Serializable;

/****
 * Tab  下title  brad  品牌的实体类
 */
public class TabTitleBrandEntity implements Serializable {
    public TabTitleBrandEntity(String tabtitltImage, String tabtitleStr) {
        TabtitltImage = tabtitltImage;
        TabtitleStr = tabtitleStr;
    }

    public String getTabtitltImage() {
        return TabtitltImage;
    }

    public void setTabtitltImage(String tabtitltImage) {
        TabtitltImage = tabtitltImage;
    }

    public String getTabtitleStr() {
        return TabtitleStr;
    }

    public void setTabtitleStr(String tabtitleStr) {
        TabtitleStr = tabtitleStr;
    }

    @Override
    public String toString() {
        return "TabTitleBrandEntity{" +
                "TabtitltImage='" + TabtitltImage + '\'' +
                ", TabtitleStr='" + TabtitleStr + '\'' +
                '}';
    }

    private String TabtitltImage;

    public TabTitleBrandEntity() {
    }

    private String TabtitleStr;


}
