package activity.com.myappdata.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import activity.com.myappdata.R;
import activity.com.myappdata.adapter.fuzaadapter.SectionedRecyclerViewAdapter;
import activity.com.myappdata.adapter.fuzaadapter.holder.FooterHolder;
import activity.com.myappdata.adapter.fuzaadapter.holder.TestSectionBodyHolder;
import activity.com.myappdata.adapter.fuzaadapter.holder.TestSectionFooterHolder;
import activity.com.myappdata.adapter.fuzaadapter.holder.TestSectionHeaderHolder;
import activity.com.myappdata.adapter.fuzaadapter.utils.DisplayUtil;
import activity.com.myappdata.adapter.fuzaadapter.utils.ListUtil;
import activity.com.myappdata.mvp.base.modelmvp.mvploginentity.mvpuserinfolitit.MVPUserInfo;
import activity.com.myappdata.mvp.base.modelmvp.mvploginentity.mvpuserinfolitit.UserinfoBywebData;

public class UserinfiByMPVAdapter  extends RecyclerView.Adapter<UserinfiByMPVAdapter.ViewHolder> implements View.OnClickListener{

    private static final String TAG = "DailyReportAdapter";
    private Context mContext;
    private List<UserinfoBywebData> details;

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (0){
//            case 1:
//                intent = new Intent(mContext, CarWarningActivity.class);
//                break;
//            case 2:
//                intent = new Intent(mContext, GunWaringActivity.class);
//                break;
//            case 3:
//                intent = new Intent(mContext, WalkWarningActivity.class);
//                break;
        }
//        if (intent != null) {
//            if(v.getId() == R.id.not_deal_warning){
//                intent.putExtra("dealState", 0);
//            } else if(v.getId() == R.id.has_deal_warning) {
//                intent.putExtra("dealState", 1);
//            }
//            mContext.startActivity(intent);
//        }

    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView orgName;
        private TextView outCount;
        private TextView inCount;
        private TextView notInCount;
        private TextView warningCount;
        private LinearLayout notDealWarning;
        private TextView notDealWarningCount;
        private LinearLayout hasDealWarning;
        private TextView hasDealWarningCount;

        public ViewHolder(View view) {
            super(view);
            orgName = (TextView) view.findViewById(R.id.org_name);
            outCount = (TextView) view.findViewById(R.id.out_count);
            inCount = (TextView) view.findViewById(R.id.in_count);
            notInCount = (TextView) view.findViewById(R.id.not_in_count);
            warningCount = (TextView) view.findViewById(R.id.warning_count);
            notDealWarning = (LinearLayout) view.findViewById(R.id.not_deal_warning);
            notDealWarningCount = (TextView) view.findViewById(R.id.not_deal_warning_count);
            hasDealWarning = (LinearLayout) view.findViewById(R.id.has_deal_warning);
            hasDealWarningCount = (TextView) view.findViewById(R.id.has_deal_warning_count);
        }
    }

    public UserinfiByMPVAdapter(List<UserinfoBywebData> reportDetails) {
        this.details = reportDetails;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.daily_end_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserinfoBywebData detail = details.get(position);
        holder.orgName.setText(detail.getUsername());
        holder.inCount.setText(detail.getUsertel() + "");
        holder.outCount.setText(detail.getUsertest() + "");
        holder.notInCount.setText(detail.getUsertype() + "");

        holder.hasDealWarning.setOnClickListener(this);
        holder.notDealWarning.setOnClickListener(this);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return details.size();
    }


}

