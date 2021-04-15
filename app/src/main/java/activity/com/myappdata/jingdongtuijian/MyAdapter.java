package activity.com.myappdata.jingdongtuijian;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.entity.Beandata;
import activity.com.myappdata.entity.DataBean;

public class MyAdapter extends RecyclerView.Adapter{
    private String url = "https://m.360buyimg.com/n0/jfs/t7441/10/64242474/419246/adb30a7d/598e95fbNd989ba0a.jpg!q70.jpg";

    Context context;
    List<Beandata> list;
    public MyAdapter(Context context, List<Beandata> list) {
        this.context=context;
        this.list=list;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(parent.getContext(), R.layout.item,null);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder= (ViewHolder) holder;
        viewHolder.tv1.setText(list.get(position).getData().getString());
        viewHolder.tv2.setText(list.get(position).getData().getTextstr());
        Glide.with(context).load(url).into(viewHolder.img);

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tv1,tv2;

        public ViewHolder(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.img);
            tv2= (TextView) itemView.findViewById(R.id.tv2);
            tv1= (TextView) itemView.findViewById(R.id.tv1);
        }
    }
}
