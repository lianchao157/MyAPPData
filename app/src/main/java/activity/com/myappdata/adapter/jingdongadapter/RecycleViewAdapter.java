package activity.com.myappdata.adapter.jingdongadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import activity.com.myappdata.R;


/**
 * Created by Administrator on 2018/6/8.
 */


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    public RecycleViewAdapter() {
    }

    private static final String TAG = "FruitAdapter";
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
        LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            linearLayout = (LinearLayout) view;
//            fruitImage = (ImageView) view.findViewById(recycleview_itemiv);
//            fruitName = (TextView) view.findViewById(R.id.recycleview_itemtv);
        }
    }

//    public RecycleViewAdapter(List<PPShopMenuEntity> fruitList) {
//        mFruitList = fruitList;
//    }

    ImageView mImageView;
    TextView mTextView;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycleview_item, parent, false);

        final ViewHolder holder = new ViewHolder(view);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
            }
        });
        return holder;
    }

    /***
     * 给车量赋值图标和文字
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        PPShopMenuEntity fruit = mFruitList.get(position);
//        holder.fruitName.setText(mFruitList.get(position).getPpshopmenumname());
//        System.out.println("*****************" + mFruitList.get(position).getPpshopmenumimage());
//        Glide.with(mContext)
//                .load(mFruitList.get(position).getPpshopmenumimage())
//                .placeholder(R.drawable.alert_bg)//默认图
//                .error(R.drawable.icon_edit_32)//发生错误的默认图
//                .into(holder.fruitImage);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}

