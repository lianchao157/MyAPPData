package activity.com.myappdata.bean.video;

import java.io.Serializable;

public class VideoEntity implements Serializable {
    public VideoEntity(String name, String videourl) {
        this.name = name;
        this.videourl = videourl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideourl() {
        return videourl;
    }

    @Override
    public String toString() {
        return "VideoEntity{" +
                "name='" + name + '\'' +
                ", videourl='" + videourl + '\'' +
                '}';
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public VideoEntity(String name, String videourl, String image, String text) {
        this.name = name;
        this.videourl = videourl;
        this.image = image;
        this.text = text;
    }

    private String name;
    private String videourl;
    private String image;
    private String text;
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
