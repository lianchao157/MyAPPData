package activity.com.myappdata.mvp.base.modelmvp.entity.videopack;

import java.io.Serializable;

/***
 * 实体类代码
 */
public class VideoListEntity implements Serializable {
    private String videolistid;
    private String videolistname;
    private String videolistimageurl;

    public String getVideolistid() {
        return videolistid;
    }

    public VideoListEntity(String videolistid, String videolistname, String videolistimageurl, String videorate) {
        this.videolistid = videolistid;
        this.videolistname = videolistname;
        this.videolistimageurl = videolistimageurl;
        this.videorate = videorate;
    }

    public VideoListEntity() {
    }

    public void setVideolistid(String videolistid) {
        this.videolistid = videolistid;
    }

    public String getVideolistname() {
        return videolistname;
    }

    public void setVideolistname(String videolistname) {
        this.videolistname = videolistname;
    }

    public String getVideolistimageurl() {
        return videolistimageurl;
    }

    public void setVideolistimageurl(String videolistimageurl) {
        this.videolistimageurl = videolistimageurl;
    }

    public String getVideorate() {
        return videorate;
    }

    public void setVideorate(String videorate) {
        this.videorate = videorate;
    }

    private String videorate;
    private String videoImage;

    public String getVideoImage() {
        return videoImage;
    }

    public void setVideoImage(String videoImage) {
        this.videoImage = videoImage;
    }

}
