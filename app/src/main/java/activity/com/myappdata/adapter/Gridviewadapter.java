package activity.com.myappdata.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.entity.GridvieMenumEntity;

/**
 * Created by lianchao on 2020/12/24.
 */

public class Gridviewadapter   extends BaseAdapter {
    public Gridviewadapter(LayoutInflater mInflater, List<GridvieMenumEntity> listGridItems) {
        this.mInflater = mInflater;
        this.listGridItems = listGridItems;
    }

    private LayoutInflater mInflater;
    private List<GridvieMenumEntity> listGridItems;

//    public Gridviewadapter(int[] title, int[] image, Context context) {
//        super();
//        listGridItems = new ArrayList<GridvieMenumEntity>();
//        mInflater = LayoutInflater.from(context);
//        for (int i = 0; i < image.length; i++) {
//            GridvieMenumEntity pItem = new GridvieMenumEntity(title[i], image[i]);
//            listGridItems.add(pItem);
//        }
//    }
    public Gridviewadapter( List<GridvieMenumEntity> listGridItems, Context context) {
        super();
    }

    public int getCount() {
        if (null != listGridItems) {
            return listGridItems.size();
        } else {
            return 0;
        }

    }

    public Object getItem(int position) {
        return listGridItems.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout_itemgradview, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.tvshow);
            viewHolder.image = (ImageView) convertView
                    .findViewById(R.id.ivshow);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(listGridItems.get(position).getTextstr());
//        viewHolder.image.setImageResource(listGridItems.get(position)
//                .getImagstr());
        return convertView;
    }

    private  class  ViewHolder{

        private  TextView title;
        private  ImageView image;
    }
}
