package activity.com.myappdata.adapter.recycleviewandrecycleview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
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
import activity.com.myappdata.bean.BusinessEntity;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.MyViewHolder> {


    public interface OnRecyclerViewListener {

        // 点击商家图片
        void onBusinessImgClick(View view, int position);

        // 点击商家名称
        void onBusinessNameClick(View view, int position);
    }

    private OnRecyclerViewListener listener;

    private Context mContext;

    private List<Object> mData;

    public void setOnRecyclerViewListener(OnRecyclerViewListener mItemListener) {
        this.listener = mItemListener;

    }

    public BusinessAdapter(Context context, List<Object> datas) {
        this.mContext = context;
        this.mData = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_business, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final BusinessEntity entity = (BusinessEntity) mData.get(position);
        holder.businessName.setText(entity.getBusinessName());
        Glide
                .with(mContext)
                .load(entity.getBusinessImage())
                .into(holder.businessImg);


        // 关键代码
        //////////////////////////////////////////////////
        ProductAdapter productAdapter = new ProductAdapter(mContext, entity.getProducts());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.productList.setLayoutManager(linearLayoutManager);
        holder.productList.setAdapter(productAdapter);
        holder.productList.setVisibility(View.VISIBLE);
        /////////////////////////////////////////////////////

        // 是否点击收藏
        if (entity.isCollection()) {
            holder.collectionImg.setImageResource(R.mipmap.chaoshi);
        } else {
            holder.collectionImg.setImageResource(R.mipmap.chaoshidi);
        }

        holder.isCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (entity.isCollection()) {
                    ((BusinessEntity) mData.get(position)).setCollection(false);
                    holder.collectionImg.setImageResource(R.mipmap.chaoshi);
                } else {
                    ((BusinessEntity) mData.get(position)).setCollection(true);
                    holder.collectionImg.setImageResource(R.mipmap.chaoshidi);
                }
            }
        });

        if (listener != null) {
            // 商家图片点击事件
            holder.businessImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onBusinessImgClick(view, position);
                }
            });
            // 商家名称点击事件
            holder.businessName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onBusinessNameClick(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView businessImg;
        TextView businessName;
        LinearLayout isCollection;
        ImageView collectionImg;
        RecyclerView productList;

        public MyViewHolder(View itemView) {
            super(itemView);
            businessImg = (ImageView) itemView.findViewById(R.id.businessImg_Iv);
            businessName = (TextView) itemView.findViewById(R.id.productName_Tv);
            isCollection = (LinearLayout) itemView.findViewById(R.id.isCollection_Ll);
            collectionImg = (ImageView) itemView.findViewById(R.id.collection_Iv);
            productList = (RecyclerView) itemView.findViewById(R.id.productRv);
        }
    }

}
