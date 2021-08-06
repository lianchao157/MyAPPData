package activity.com.myappdata.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.adapter.videoadapter.RecycleviewviewdeoAdapter;
import activity.com.myappdata.entity.EntityReccleview;
import butterknife.BindView;
import butterknife.ButterKnife;
// <ImageView
//         android:id="@+id/iv_phone"
//         android:layout_width="wrap_content"
//         android:layout_height="wrap_content"
//         android:layout_gravity="center"
//         android:background="@mipmap/shangping3" />
//
//         <TextView
//         android:id="@+id/tv_money"
//         android:layout_width="wrap_content"
//         android:layout_height="wrap_content"
//         android:layout_gravity="center"
//         android:text="￥300"
//         android:textColor="@color/red"
//         android:textSize="30px" />
//
//         <TextView
//         android:id="@+id/tv_codemoney"

/****
 * findfragment  的适配器代码
 * 需要修改  条目太紧凑
 */
public class RecycleviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private int typeimage = 1;
    private int typenews = 2;


    public RecycleviewAdapter(Context mContext, List<EntityReccleview> mfruitList) {
        this.mContext = mContext;
        this.mfruitList = mfruitList;
    }

    private static final String TAG = "FruitAdapter";
    private Context mContext;
    List<EntityReccleview> mfruitList;

    //    static class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView fruitImage;
//        TextView fruitName;
//        ProgressBar note_progressBar;
//        LinearLayout linearLayout;
//        TextView tv_sale;
//        TextView tv_codemoney;
//
//        public ViewHolder(View view) {
//            super(view);
//            fruitImage = (ImageView) view.findViewById(R.id.iv_phone);
//            fruitName = (TextView) view.findViewById(R.id.tv_codemoney);
//            fruitName.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
//            tv_codemoney = (android.widget.TextView) view.findViewById(R.id.tv_codemoney);
//            note_progressBar = (ProgressBar) view.findViewById(R.id.note_progressBar);
//            tv_sale = (android.widget.TextView) view.findViewById(R.id.tv_sale);
//        }
//    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView rv_vide;
        ProgressBar note_progressBar;
        LinearLayout linearLayout;
        TextView tv_sale;
        TextView tv_codemoney;

        public ViewHolder(View view) {
            super(view);
            linearLayout = (LinearLayout) view;
            rv_vide = (TextView) view.findViewById(R.id.rv_vide);
        }
    }

    public RecycleviewAdapter(List<EntityReccleview> fruitList) {
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
        if (viewType == typeimage) {
            view = View.inflate(mContext, R.layout.layout_recyclee_title_item, null);
            return new ViewHolder1(view);
        } else if (typenews == viewType) {//  新代码  图片  和   价格
            view = View.inflate(mContext, R.layout.layout_recycle_iamgeinfo, null);
            return new ViewHolder2(view);
        } else {
//            view = View.inflate(mContext, R.layout.rv_item_text_item, null);
            return new ViewHolder2(null);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? typeimage : typenews;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        EntityReccleview fruit = mfruitList.get(position);
        if (holder instanceof ViewHolder1) {
            ViewHolder1 videoViewHolder = (ViewHolder1) holder;
            videoViewHolder.fruitName.setText(mfruitList.get(position).getImagetextrae());
            videoViewHolder.note_progressBar.setProgress((9 + position));
            videoViewHolder.note_progressBar.setVisibility(View.VISIBLE);
            videoViewHolder.note_progressBar.setIndeterminate(false);
            videoViewHolder.tv_sale.setText("已经销售" + mfruitList.get(position).getStrrate() + "%");
            Glide.with(mContext)
                    .load(mfruitList.get(position).getImagurl())
//                    .placeholder(R.mipmap.fushi)//默认图
//                    .error(R.mipmap.shangping3)//发生错误的默认图
                    .into(videoViewHolder.fruitImage);

        } else if (holder instanceof ViewHolder2) {
            ViewHolder2 v2 = (ViewHolder2) holder;
            Glide.with(mContext)
                    .load(mfruitList.get(position).getImagurl())
//                    .placeholder(R.mipmap.fushi)//默认图
//                    .error(R.mipmap.shangping3)//发生错误的默认图
                    .into(v2.iv_shoptitle);
            v2.tv_shoptitlemoney.setText(mfruitList.get(position).getStrrate());
        }
    }

    @Override
    public int getItemCount() {
        return mfruitList.size();
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_phone)
        ImageView fruitImage;
        @BindView(R.id.tv_codemoney)
        TextView fruitName;
        @BindView(R.id.note_progressBar)
        ProgressBar note_progressBar;
        @BindView(R.id.tv_sale)
        TextView tv_sale;

        public ViewHolder1(View itemView) {
            super(itemView);
            //在这里实例化特有的
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_shoptitle)
        ImageView iv_shoptitle;
        @BindView(R.id.tv_shoptitlemoney)
        TextView tv_shoptitlemoney;
        @BindView(R.id.tv_shoptitlemoney2)
        TextView tv_shoptitlemoney2;

        public ViewHolder2(View itemView) {
            super(itemView);
            //在这里实例化特有的
            ButterKnife.bind(this, itemView);
        }
    }
}
