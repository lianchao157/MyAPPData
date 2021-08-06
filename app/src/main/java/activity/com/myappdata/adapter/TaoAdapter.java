package activity.com.myappdata.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.entity.TaoAdapterMenum;


/**
 * Created by lianchao on 2021/1/8.
 * <p>
 * recycleview  适配器代码
 */
public class TaoAdapter extends RecyclerView.Adapter<activity.com.myappdata.adapter.TaoAdapter.ViewHolder> {

    private static final String TAG = "FruitAdapter";
    private Context mContext;
    List<TaoAdapterMenum> mFruitList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
        LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            linearLayout = (LinearLayout) view;
            fruitImage = (ImageView) view.findViewById(R.id.bf_recycleview_itemiv);
            fruitName = (TextView) view.findViewById(R.id.bf_recycleview_itemtv);
        }
    }

    public TaoAdapter(List<TaoAdapterMenum> fruitList) {
        mFruitList = fruitList;
    }

    ImageView mImageView;
    TextView mTextView;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.blankfragment_recycleview_item, parent, false);

        final ViewHolder holder = new ViewHolder(view);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
            }
        });
        int parentHeight = parent.getHeight();
        parent.getWidth();
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = (parentHeight / 2);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaoAdapterMenum fruit = mFruitList.get(position);
        holder.fruitName.setText(fruit.getStrname());
        Glide.with(mContext)
                .load(fruit.getStriamge())
//                .placeholder(R.drawable.gray_radius)//默认图
//                .error(R.drawable.selector_list_item)//发生错误的默认图
                .into(holder.fruitImage);
    }

//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        TaoAdapterMenum fruit = mFruitList.get(position);
//        holder.fruitName.setText(mFruitList.get(position).getStrname());
//        Glide.with(mContext)
//                .load(mFruitList.get(position))
//                .placeholder(R.drawable.gray_radius)//默认图
//                .error(R.drawable.selector_list_item)//发生错误的默认图
//                .into(holder.fruitImage);
//    }

    @Override
    public int getItemCount() {
        {
            return mFruitList.size();
        }
    }
}
