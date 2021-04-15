package activity.com.myappdata.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.adapter.jingdongadapter.RecycleViewAdapter;
import activity.com.myappdata.entity.EntityReccleview;
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
public class RecycleviewAdapter extends RecyclerView.Adapter<RecycleviewAdapter.ViewHolder> {

    public RecycleviewAdapter() {
    }

    private static final String TAG = "FruitAdapter";
    private Context mContext;
    List<EntityReccleview> mfruitList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
        ProgressBar note_progressBar;
        LinearLayout linearLayout;
        TextView tv_sale;
        TextView tv_codemoney;

        public ViewHolder(View view) {
            super(view);
            linearLayout = (LinearLayout) view;
            fruitImage = (ImageView) view.findViewById(R.id.iv_phone);
            fruitName = (TextView) view.findViewById(R.id.tv_codemoney);
            tv_codemoney = (android.widget.TextView) view.findViewById(R.id.tv_codemoney);
            note_progressBar = (ProgressBar) view.findViewById(R.id.note_progressBar);
            tv_sale = (android.widget.TextView) view.findViewById(R.id.tv_sale);
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
    public RecycleviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_recyclee_title_item, parent, false);

        final RecycleviewAdapter.ViewHolder holder = new RecycleviewAdapter.ViewHolder(view);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EntityReccleview fruit = mfruitList.get(position);
        holder.fruitName.setText(mfruitList.get(position).getImagetextrae());
//        holder.fruitImage = (ImageView) view.findViewById(R.id.tv_money);
//        holder.fruitName = (TextView) view.findViewById(R.id.tv_codemoney);
//        note_progressBar = (ProgressBar) view.findViewById(R.id.note_progressBar);
        holder.tv_sale.setText("已经销售"+mfruitList.get(position).getStrrate()+"%");
        Glide.with(mContext)
                .load(mfruitList.get(position).getImagurl())
                .placeholder(R.mipmap.fushi)//默认图
                .error(R.mipmap.go_top)//发生错误的默认图
                .into(holder.fruitImage);
    }

    /***
     * 给车量赋值图标和文字
     *
     //     * @param holder
     //     * @param position
     */
//    @Override
//    public void onBindViewHolder(RecycleViewAdapter.ViewHolder holder, int position) {
//        PPShopMenuEntity fruit = mFruitList.get(position);
//        holder.fruitName.setText(mFruitList.get(position).getPpshopmenumname());
//        System.out.println("*****************" + mFruitList.get(position).getPpshopmenumimage());
//        Glide.with(mContext)
//                .load(mFruitList.get(position).getPpshopmenumimage())
//                .placeholder(R.drawable.alert_bg)//默认图
//                .error(R.drawable.icon_edit_32)//发生错误的默认图
//                .into(holder.fruitImage);
//    }
    @Override
    public int getItemCount() {
        return mfruitList.size();
    }
}
// fruitImage = (ImageView) view.findViewById(R.id.tv_money);
//         fruitName = (TextView) view.findViewById(R.id.tv_codemoney);
//         note_progressBar= (ProgressBar) view.findViewById(R.id.note_progressBar);
//         tv_sale= (android.widget.TextView) view.findViewById(R.id.tv_sale);