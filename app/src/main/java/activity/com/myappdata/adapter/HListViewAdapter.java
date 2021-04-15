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
import activity.com.myappdata.bean.ApprovalData;

/**
 * Created by Administrator on 2018/7/10.
 */

public class HListViewAdapter extends BaseAdapter {
    private List<ApprovalData> appList;
    private Context mContext;
    private LayoutInflater inflater;

    public HListViewAdapter(List<ApprovalData> appList, Context mContext) {
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
            convertView = inflater.inflate(R.layout.homelistview_item, null, false);
//            TextView intercomid = (TextView) convertView.findViewById(R.id.tv_home_Approval_info);
            TextView orgname = (TextView) convertView.findViewById(R.id.tv_home_Approval_info1);
            TextView outtime = (TextView) convertView.findViewById(R.id.tv_home_Approval_info3);
            TextView apptextok = (TextView) convertView.findViewById(R.id.approtv_ok);
//            hHListViewAdaptertr.intercomid = intercomid;
            hHListViewAdaptertr.orgname = orgname;
            hHListViewAdaptertr.outtime = outtime;
            hHListViewAdaptertr.apptextok = apptextok;
            convertView.setTag(convertView);

        } else {
            hHListViewAdaptertr = (HListViewAdaptertr) convertView.getTag();
            convertView.setTag(convertView);
        }
        hHListViewAdaptertr.orgname.setText(appList.get(position).getUsername());
//        hHListViewAdaptertr.intercomid.setText(appList.get(position).getReason());
        hHListViewAdaptertr.outtime.setText(appList.get(position).getUsingtime());
        if (appList.get(position).getAuditstate() == null) {
            hHListViewAdaptertr.apptextok.setText("未审批");
            hHListViewAdaptertr.apptextok.setTextColor(Color.BLACK);
        } else {
            if (("0").equals(appList.get(position).getAuditstate())) {  //没通过
                hHListViewAdaptertr.apptextok.setText("拒绝审批");
                hHListViewAdaptertr.apptextok.setTextColor(Color.RED);
            } else if (appList.get(position).getAuditstate().equals("1")) {
                hHListViewAdaptertr.apptextok.setText("审批完成");
                hHListViewAdaptertr.apptextok.setTextColor(Color.GREEN);
            }
        }
        System.out.print("更新！！！！！！");
        return convertView;

    }

    class HListViewAdaptertr {
        public TextView id, apptextok, apptextno;
        public TextView intercomid;
        public TextView orgid;
        public TextView orgname;
        public TextView vehicleid;
        public TextView outtime;
        public TextView intime;
        public TextView approtv_jujue;
        /**
         * "id": "48f567460cf94e478e9cb7c66c01fe1d",
         "intercomid": "DJ0001",
         "orgid": "d4c5b5eacc7443e7b5eda8b431447e03",
         "orgname": "天津市河西区小白楼分公司天津市河西区小白楼分公司",
         "vehicleid": "津AT8599",
         "outtime": "2018-07-09 10:34:34",
         "intime": "2018-07-09 10:34:37"
         *
         *
         *
         *
         */
    }

    public void refersh(List<ApprovalData> appList) {
        this.appList = appList;
        System.out.print("更新");
        notifyDataSetChanged();
    }

}
