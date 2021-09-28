package activity.com.myappdata.activity;

import activity.com.myappdata.adapter.videoadapter.*;

import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.bean.video.VideoEntity;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerManager;
import cn.jzvd.JZVideoPlayerStandard;

/***
 * 视频播放和相应式布局
 *
 */
public class VideoActivity extends AppCompatActivity {

    private FlexboxLayout flexboxlayout;///  相应式布局
    private SensorManager sensorManager;
    private RecyclerView viderv;
    private Toolbar toobar;
    private RecycleviewviewdeoAdapter wdeoAdapter;
    private List<VideoEntity> liststr = new ArrayList<VideoEntity>();
    JZVideoPlayerStandard myJzvdStd;
    private JZVideoPlayer.JZAutoFullscreenListener jzAutoFullscreenListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initview();
        loadData();
    }

    private void loadData() {
    }

    private void initview() {
        findViewById(R.id.btn_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                cteateVideo();
                shwolittlewidos();
            }
        });

        myJzvdStd = (JZVideoPlayerStandard) findViewById(R.id.videoplayer1activity);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        jzAutoFullscreenListener = new JZVideoPlayer.JZAutoFullscreenListener();

        //将缩略图的scaleType设置为FIT_XY（图片全屏）
        myJzvdStd.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);

        //设置容器内播放器高,解决黑边（视频全屏）
        JZVideoPlayer.setVideoImageDisplayType(JZVideoPlayer.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT);


        //播放视频
        myJzvdStd.TOOL_BAR_EXIST = false;
        myJzvdStd.setUp("https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4"
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "饺子闭眼睛");
        flexboxlayout = (FlexboxLayout) findViewById(R.id.flexboxlayout);
        viderv = (RecyclerView) findViewById(R.id.viderv);
        toobar = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toobar);


        VideoEntity videoEntity = new VideoEntity("视频1", "http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4");
        VideoEntity videoEntity2 = new VideoEntity("视频2", "http://vjs.zencdn.net/v/oceans.mp4", "", "测试33333");
        VideoEntity videoEntity3 = new VideoEntity("视频3", "https://media.w3.org/2010/05/sintel/trailer.mp4", "", "测试111");
        liststr.add(videoEntity);
        liststr.add(videoEntity2);
        liststr.add(videoEntity3);
        wdeoAdapter = new RecycleviewviewdeoAdapter(VideoActivity.this, liststr);
        viderv.setAdapter(wdeoAdapter);
        viderv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        viderv.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
//                Jzvd jzvd = view.findViewById(R.id.videoplayer);
//                if (jzvd != null && jzvd.jzDataSource.containsTheUrl(JZMediaManager.getCurrentUrl())) {
//                    Jzvd currentJzvd = JzvdMgr.getCurrentJzvd();
//                    if (currentJzvd != null && currentJzvd.currentScreen != Jzvd.SCREEN_WINDOW_FULLSCREEN) {
//                        Jzvd.releaseAllVideos();
//                    }
//                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (myJzvdStd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    //    Home键退出界面暂停播放，返回界面继续播放
    @Override
    protected void onResume() {
        super.onResume();
        //home back
        myJzvdStd.goOnPlayOnResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        myJzvdStd.clearSavedProgress(this, null);
        //home back
        myJzvdStd.goOnPlayOnPause();

        if (null != myJzvdStd) {
            JZVideoPlayerStandard player = (JZVideoPlayerStandard) JZVideoPlayerManager.getCurrentJzvd();
            if (player != null && player.currentState == player.CURRENT_STATE_PREPARING) {
//                statepause = 2;
                myJzvdStd.releaseAllVideos();
            } else {
//                statepause = 0;
                myJzvdStd.clearSavedProgress(this, null);
                myJzvdStd.goOnPlayOnPause();
            }
        }
    }
// 开启全屏幕模式
    public  void  cteateVideo(){
        myJzvdStd.startFullscreen(this, VideoActivity.class, "http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4", "饺子辛苦了");
    }

//    开启小窗
    public void  shwolittlewidos(){
//        开启小窗播放视频
        myJzvdStd.startWindowTiny();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
