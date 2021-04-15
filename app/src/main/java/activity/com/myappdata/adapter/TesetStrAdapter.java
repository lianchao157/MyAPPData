package activity.com.myappdata.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import activity.com.myappdata.R;

public class TesetStrAdapter extends BaseAdapter {
    private List<String> appList;
    private Context mContext;
    private LayoutInflater inflater;

    public TesetStrAdapter(List<String> appList, Context mContext) {
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
        HListViewAdaptertr hHListViewAdaptertr = null;
        if (hHListViewAdaptertr == null) {
            hHListViewAdaptertr = new HListViewAdaptertr();
            convertView = inflater.inflate(R.layout.item_type1, null, false);
            TextView id = (TextView) convertView.findViewById(R.id.item_type1_text);
            hHListViewAdaptertr.id = id;
            convertView.setTag(convertView);
        } else {
            hHListViewAdaptertr = (HListViewAdaptertr) convertView.getTag();
            convertView.setTag(convertView);
        }
        return convertView;

    }

    class HListViewAdaptertr {
        public TextView id;
    }

    public void refersh(List<String> appList) {
        this.appList = appList;
        System.out.print("更新");
        notifyDataSetChanged();
    }
}
