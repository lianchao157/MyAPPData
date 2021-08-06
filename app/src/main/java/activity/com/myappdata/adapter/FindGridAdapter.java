package activity.com.myappdata.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.squareup.picasso.Picasso;

import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.bean.FindFragmetnMenuTitleEntity;
import activity.com.myappdata.bean.GoodsConfigBean;

/**
 * 顶部菜单适配器  fragment的适配器代码
 */
public class FindGridAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<FindFragmetnMenuTitleEntity> data;
    private Context mContext;

    public FindGridAdapter(Context context, List<FindFragmetnMenuTitleEntity> data) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.mContext = context;
    }

    public void setData(List<FindFragmetnMenuTitleEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int position) {
        return this.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.findgrdidadapter_item, null);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        FindFragmetnMenuTitleEntity config = data.get(position);
//        Picasso.with(mContext).load(config.getPp_menum_textimage()).placeholder(R.drawable.icon_msg).into(holder.imageview_config_key);
        holder.tv_menu_name.setText(config.getPp_menum_texttitle() + "");
        return convertView;
    }

    class MyViewHolder {
        ImageView imageview_config_key;
        TextView tv_menu_name;

        public MyViewHolder(View rootview) {
            imageview_config_key = (ImageView) rootview.findViewById(R.id.imageview_config_key);
            tv_menu_name = (TextView) rootview.findViewById(R.id.tv_menu_name);
        }
    }
}
