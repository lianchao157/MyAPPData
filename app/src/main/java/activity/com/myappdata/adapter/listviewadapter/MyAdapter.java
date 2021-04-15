package activity.com.myappdata.adapter.listviewadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.com.myappdata.R;

/***
 * 适配item
 */
public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private LinearLayout linearLayout = null;
    private LayoutInflater inflater;
    private List<String> list = new ArrayList<String>();
    private TextView tex;
    private final int VIEW_TYPE = 3;
    private final int TYPE_1 = 0;
    private final int TYPE_2 = 1;
    private final int TYPE_3 = 2;

    public MyAdapter(Context context, List<String> list) {
        // TODO Auto-generated constructor stub
        this.mContext = context;
        this.list = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        // TODO 自动生成的方法存根
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO 自动生成的方法存根
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO 自动生成的方法存根
        return position;
    }


    //每个convert view都会调用此方法，获得当前所需要的view样式
    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        int viewtype = position % 6;
        if (viewtype == 0)
            return TYPE_1;
        else if (viewtype < 3)
            return TYPE_2;
        else if (viewtype < 6)
            return TYPE_3;
        else
            return TYPE_1;
    }

    //返回样式的数量
    @Override
    public int getViewTypeCount() {
        // TODO Auto-generated method stub
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder1 holder1 = null;
        viewHolder2 holder2 = null;
        viewHolder3 holder3 = null;
        int type = getItemViewType(position);

        // 无convertView，需要new出各个控件
        if (convertView == null) {
            Log.e("convertView = ", "###convertView为空###");
            // 按当前所需的样式，确定new的布局
            switch (type) {
                case TYPE_1:
                    convertView = inflater.inflate(R.layout.list_item_type3, parent, false);
                    holder1 = new viewHolder1();
                    holder1.textView = (TextView) convertView.findViewById(R.id.tv_list1);
                    holder1.checkBox = (CheckBox) convertView.findViewById(R.id.check);
                    Log.e("convertView = ", "布局样式一");
                    convertView.setTag(holder1);
                    break;
                case TYPE_2:
                    convertView = inflater.inflate(R.layout.list_item_type1, parent, false);
                    holder2 = new viewHolder2();
                    holder2.textView = (TextView) convertView.findViewById(R.id.tv_list11);
                    Log.e("convertView = ", "布局样式二");
                    convertView.setTag(holder2);
                    break;
                case TYPE_3:
                    convertView = inflater.inflate(R.layout.list_item_type2, parent, false);
                    holder3 = new viewHolder3();
                    holder3.textView = (TextView) convertView.findViewById(R.id.tv_zzl);
                    holder3.p_w_picpathView = (ImageView) convertView.findViewById(R.id.iv_list1);
                    Log.e("convertView = ", "布局样式三");
                    convertView.setTag(holder3);
                    break;
            }
        } else {
            // 有convertView，按样式，取得不用的布局
            switch (type) {
                case TYPE_1:
                    holder1 = (viewHolder1) convertView.getTag();
                    Log.e("convertView= ", "布局样式一");
                    break;
                case TYPE_2:
                    holder2 = (viewHolder2) convertView.getTag();
                    Log.e("convertView= ", "布局样式二");
                    break;
                case TYPE_3:
                    holder3 = (viewHolder3) convertView.getTag();
                    Log.e("convertView= ", "布局样式三");
                    break;
            }
        }

        // 设置资源
        switch (type) {
            case TYPE_1:
                holder1.textView.setText(Integer.toString(position));
                holder1.checkBox.setChecked(true);
                break;
            case TYPE_2:
                holder2.textView.setText(Integer.toString(position));
                break;
            case TYPE_3:
                holder3.textView.setText(Integer.toString(position));
                holder3.p_w_picpathView.setBackgroundResource(R.drawable.ic_username);
                break;
        }

        return convertView;
    }

    // 各个布局的控件资源
    class viewHolder1 {
        CheckBox checkBox;
        TextView textView;
    }

    class viewHolder2 {
        TextView textView;
    }

    class viewHolder3 {
        ImageView p_w_picpathView;
        TextView textView;
    }

}
