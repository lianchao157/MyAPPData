package activity.com.myappdata.adapter.videoadapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.bean.video.VideoEntity;
import activity.com.myappdata.entity.EntityReccleview;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayerStandard;

/***
 * 适配器
 */
public class RecycleviewviewdeoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_VIDEO = 0;  //视频类型
    private static final int TYPE_IMAGE = 1;    //图片类型
    //————————————————
//    原文链接：https://blog.csdn.net/asjqkkkk/article/details/78489245
    private static final String TAG = "FruitAdapter";
    public RecycleviewviewdeoAdapter(Context mContext, List<VideoEntity> mfruitList) {
        this.mContext = mContext;
        this.mfruitList = mfruitList;
    }

    private Context mContext;
    List<VideoEntity> mfruitList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView rv_vide;
        ProgressBar note_progressBar;
        LinearLayout linearLayout;
        TextView tv_sale;
        TextView tv_codemoney;
        //        JzvdStd videoplayer;
        JZVideoPlayerStandard myJzvdStd;

        public ViewHolder(View view) {
            super(view);
            linearLayout = (LinearLayout) view;
            rv_vide = (TextView) view.findViewById(R.id.rv_vide);
            myJzvdStd = (JZVideoPlayerStandard) view.findViewById(R.id.videoplayer);
        }
    }

    public RecycleviewviewdeoAdapter(List<VideoEntity> fruitList) {
        mfruitList = fruitList;
    }

    ImageView mImageView;
    TextView mTextView, tv_codemoney;
    ProgressBar note_progressBar;
    TextView TextView, tv_sale;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext != null) {
            this.mContext = parent.getContext();
        }
        View view;
        if (viewType == TYPE_VIDEO) {
            view = View.inflate(mContext, R.layout.rv_item_video_item, null);
            return new VideoViewHolder(view);
        } else if (viewType == TYPE_IMAGE) {
            view = View.inflate(mContext, R.layout.rv_item_image_item, null);
            return new ImageViewHolder(view);
        } else {
            view = View.inflate(mContext, R.layout.rv_item_text_item, null);
            return new TextViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? TYPE_VIDEO : TYPE_IMAGE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VideoEntity fruit = mfruitList.get(position);
        if (holder instanceof VideoViewHolder) {
            VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
//            bindCommonData(videoViewHolder.commonView, fruit);
            videoViewHolder.jcv_videoplayer.setUp("https://media.w3.org/2010/05/sintel/trailer.mp4", JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "饺子闭眼睛");

        } else if (holder instanceof ImageViewHolder) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
//            bindCommonData(imageViewHolder.commonView, fruit);
//            imageViewHolder.iv_image_icon.setImageIcon("");

//            imageViewHolder.iv_image_icon.setImageResource(R.drawable.service_life_icon);
            if (fruit.getName() != null) {
                Glide.with(mContext).load(fruit.getVideourl()).into(imageViewHolder.iv_image_icon);
            }
        } else if (holder instanceof TextViewHolder) {
            TextViewHolder textViewHolder = (TextViewHolder) holder;
//            bindCommonData(textViewHolder.commonView, fruit);
            textViewHolder.rv_text.setText(fruit.getText());
        }
    }

//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//    }

//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        VideoEntity fruit = mfruitList.get(position);
//        holder.rv_vide.setText(mfruitList.get(position).getName());
//
//
//        holder.myJzvdStd.setUp("https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4"
//                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "饺子闭眼睛");
////        holder.myJzvdStd.thumbImageView.setImageIcon("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
//        holder.myJzvdStd.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");

    //        Glide.with(mContext)
//                .load(mfruitList.get(position).getImagurl())
//                .placeholder(R.mipmap.fushi)//默认图
//                .error(R.mipmap.go_top)//发生错误的默认图
//                .into(holder.fruitImage);
//    }
    class VideoViewHolder extends RecyclerView.ViewHolder {
        CommonView commonView = new CommonView();
        //        JZVideoPlayerStandard jcv_videoplayer;
        @BindView(R.id.videoplayer)
        JZVideoPlayerStandard jcv_videoplayer;

        public VideoViewHolder(View itemView) {
            super(itemView);
            //在这里实例化特有的
//            jcv_videoplayer = (JZVideoPlayerStandard) itemView.findViewById(R.id.videoplayer);
//            initCommonView(itemView, commonView);
//            if (null != jcv_videoplayer) {
//                Log.e(TAG, "null");
//            } else {
//                Log.e(TAG, "bu null");
//            }
            ButterKnife.bind(this, itemView);
        }

    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rv_image)
        ImageView iv_image_icon;
//        CommonView commonView = new CommonView();
//        ImageView iv_image_icon;

        public ImageViewHolder(View itemView) {
            super(itemView);
//            iv_image_icon = (ImageView) itemView.findViewById(R.id.rv_image);
//            if (null != iv_image_icon.getDrawable()) {
//                Log.e(TAG, "null");
//            } else {
//                Log.e(TAG, "bu null");
//            }
//            initCommonView(itemView, commonView);
            ButterKnife.bind(this, itemView);
        }
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        //        CommonView commonView = new CommonView();
//        TextView rv_text;
        @BindView(R.id.rv_text)
        TextView rv_text;

        public TextViewHolder(View itemView) {
            super(itemView);
//            rv_text = (TextView) itemView.findViewById(R.id.rv_text);
//            rv_text.setText("1111");
//            initCommonView(itemView, commonView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemCount() {
        return mfruitList.size();
    }


//————————————————
//        版权声明：本文为CSDN博主「asjqkkkk」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/asjqkkkk/article/details/78489245

    private void bindCommonData(CommonView view, VideoEntity data) {
        if (data.getVideourl() != null) {
            Glide.with(mContext).load(data.getName())
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .skipMemoryCache(false)
//                    .error(R.drawable.service_life_icon)
                    .into(view.iv_headpic);

        }
        if (data.getName() != null) {
            view.tv_name.setText(data.getName());
        }
//        view.tv_time_refresh.setText(data.getCreate_time());
//        view.tv_shenhe_ding_number.setText(data.getLove());
//        view.tv_shenhe_cai_number.setText(data.getHate());
//        view.tv_context.setText(data.getText());
    }

//
//————————————————
//    版权声明：本文为CSDN博主「asjqkkkk」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/asjqkkkk/article/details/78489245


    //公共视图  3步骤
    static class CommonView {
        //用户信息
        ImageView iv_headpic;
        TextView tv_name;
        TextView tv_time_refresh;
        ImageView iv_right_more;

        //顶和赞
        TextView tv_shenhe_ding_number;
        TextView tv_shenhe_cai_number;

//        //下载栏
//        LinearLayout ll_download;

        //中间公共部分 -所有的都有
        TextView tv_context;
        JZVideoPlayerStandard videoplayer;
    }

    private void initCommonView(View itemView, CommonView commonView) {
        commonView.videoplayer = (JZVideoPlayerStandard) itemView.findViewById(R.id.videoplayer);
        commonView.iv_headpic = (ImageView) itemView.findViewById(R.id.rv_image);
        commonView.tv_name = (TextView) itemView.findViewById(R.id.rv_text);
//        commonView.tv_time_refresh = (TextView) itemView.findViewById(R.id.tv_time_refresh);
//        commonView.iv_right_more = (ImageView) itemView.findViewById(R.id.iv_right_more);
//
//        commonView.tv_shenhe_ding_number = (TextView) itemView.findViewById(R.id.tv_shenhe_ding_number);
//        commonView.tv_shenhe_cai_number = (TextView) itemView.findViewById(R.id.tv_shenhe_cai_number);

//        commonView.ll_download = (LinearLayout) itemView.findViewById(R.id.ll_download);
    }


}