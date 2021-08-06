package activity.com.myappdata.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.bean.ApprovalData;
import activity.com.myappdata.bean.BlankFragmentEntity;

/**
 * Created by Administrator on 2018/7/10.
 */

public class BlankFragmetistViewAdapter extends BaseAdapter {
    private List<BlankFragmentEntity> appList;
    private Context mContext;
    private LayoutInflater inflater;

    public BlankFragmetistViewAdapter(List<BlankFragmentEntity> appList, Context mContext) {
        this.appList = appList;
        this.mContext = mContext;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return appList.size();

    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BlankFragmetistViewHolder BlankFragmetistViewHolder = null;
        if (BlankFragmetistViewHolder == null) {
            BlankFragmetistViewHolder = new BlankFragmetistViewHolder();
            convertView = inflater.inflate(R.layout.blanfragment_list_item, null, false);
            ImageView intercomid = (ImageView) convertView.findViewById(R.id.blanfragemt_item_iv);
            TextView orgname = (TextView) convertView.findViewById(R.id.tv_info);
            TextView outtime = (TextView) convertView.findViewById(R.id.tv_monyinfo);

            ImageView blanfragemt_item_iv2 = (ImageView) convertView.findViewById(R.id.blanfragemt_item_iv2);
            TextView orgname2 = (TextView) convertView.findViewById(R.id.tv_info2);
            TextView outtime2 = (TextView) convertView.findViewById(R.id.tv_monyinfo2);
            TextView tv_xaingsi2 = (TextView) convertView.findViewById(R.id.tv_xaingsi2);
            BlankFragmetistViewHolder.intercomid = intercomid;
            BlankFragmetistViewHolder.outtime = outtime;
            BlankFragmetistViewHolder.orgname = orgname;

            BlankFragmetistViewHolder.orgname2 = blanfragemt_item_iv2;
            BlankFragmetistViewHolder.outtime2 = orgname2;
            BlankFragmetistViewHolder.tv_xaingsi2 = tv_xaingsi2;
            convertView.setTag(convertView);

        } else {
            BlankFragmetistViewHolder = (BlankFragmetistViewHolder) convertView.getTag();
            convertView.setTag(convertView);
        }

//        ImageView  需要加入

        Glide.with(mContext)
                .load(appList.get(position).getImagestr())
                .into(BlankFragmetistViewHolder.intercomid);
        BlankFragmetistViewHolder.orgname.setText(appList.get(position).getImagestr());
        BlankFragmetistViewHolder.outtime.setText(appList.get(position).getMoney());

        BlankFragmetistViewHolder.tv_xaingsi2.setText(appList.get(position).getImagestr());
        BlankFragmetistViewHolder.outtime2.setText(appList.get(position).getMoney());
        Glide.with(mContext)
                .load(appList.get(position).getImagestr())
                .into(BlankFragmetistViewHolder.orgname2);
        return convertView;

    }

    class BlankFragmetistViewHolder {
        public TextView id, apptextok, apptextno;
        public ImageView intercomid;
        public TextView orgid;
        public TextView orgname;
        public TextView vehicleid;
        public TextView outtime;
        public TextView intime;
        public TextView approtv_jujue;




        public ImageView orgname2;
        public TextView outtime2 ;
        public TextView tv_xaingsi2 ;
    }

//    public void refersh(List<ApprovalData> appList) {
//        this.appList = appList;
//        System.out.print("更新");
//        notifyDataSetChanged();
//    }

}
