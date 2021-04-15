package activity.com.myappdata.mvp.base.uimvp.adpter_mvp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.mvp.base.modelmvp.entity.Province;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.InnerHolder> {


    public PlaceAdapter() {
    }

    public PlaceAdapter(List<Province> mData) {
        this.mData = mData;
    }

    // 存放数据
    private List<Province> mData = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        return new InnerHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Province province = mData.get(position);
        // 绑定数据
        holder.setData(province);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private final TextView content;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.home_place_content);
        }


        public void setData(Province province) {
            //TODO： 设置数据
            content.setText(province.getName());
        }
    }

    // 传递数据
    public void setmData(List<Province> contents) {
        mData.clear();
        mData.addAll(contents);
        notifyDataSetChanged();
    }
}
