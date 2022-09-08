package activity.com.myappdata.util.traffictransfer;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.BusStep;
import com.amap.api.services.route.RouteBusLineItem;
import com.amap.api.services.route.RouteRailwayItem;

import java.util.List;

import activity.com.myappdata.BusRouteDetailActivity;
import activity.com.myappdata.R;

import static activity.com.myappdata.util.traffictransfer.AMapUtil.getFriendlyLength;
import static activity.com.myappdata.util.traffictransfer.AMapUtil.getFriendlyTime;
import static activity.com.myappdata.util.traffictransfer.AMapUtil.getSimpleBusLineName;

public
class BusResultListAdapter  extends BaseAdapter {
    private Context mContext;
    private List<BusPath> mBusPathList;
    private BusRouteResult mBusRouteResult;

    public BusResultListAdapter(Context context, BusRouteResult busrouteresult) {
        mContext = context;
        mBusRouteResult = busrouteresult;
        mBusPathList = busrouteresult.getPaths();
    }

    @Override
    public int getCount() {
        return mBusPathList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBusPathList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_bus_result, null);
            holder.title = (TextView) convertView.findViewById(R.id.bus_path_title);
            holder.des = (TextView) convertView.findViewById(R.id.bus_path_des);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final BusPath item = mBusPathList.get(position);
        holder.title.setText(AMapUtil.getBusPathTitle(item));
        holder.des.setText(AMapUtil.getBusPathDes(item));

        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext.getApplicationContext(),
                        BusRouteDetailActivity.class);
                intent.putExtra("bus_path", item);
                intent.putExtra("bus_result", mBusRouteResult);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);

            }
        });

        return convertView;
    }

    private class ViewHolder {
        TextView title;
        TextView des;
    }

    public static String getBusPathTitle(BusPath busPath) {
        if (busPath == null) {
            return String.valueOf("");
        }
        List<BusStep> busSetps = busPath.getSteps();
        if (busSetps == null) {
            return String.valueOf("");
        }
        StringBuffer sb = new StringBuffer();
        for (BusStep busStep : busSetps) {
            StringBuffer title = new StringBuffer();
            if (busStep.getBusLines().size() > 0) {
                for (RouteBusLineItem busline : busStep.getBusLines()) {
                    if (busline == null) {
                        continue;
                    }

                    String buslineName = getSimpleBusLineName(busline.getBusLineName());
                    title.append(buslineName);
                    title.append(" / ");
                }
//					RouteBusLineItem busline = busStep.getBusLines().get(0);

                sb.append(title.substring(0, title.length() - 3));
                sb.append(" > ");
            }
            if (busStep.getRailway() != null) {
                RouteRailwayItem railway = busStep.getRailway();
                sb.append(railway.getTrip()+"("+railway.getDeparturestop().getName()
                        +" - "+railway.getArrivalstop().getName()+")");
                sb.append(" > ");
            }
        }
        return sb.substring(0, sb.length() - 3);
    }
    public static String getBusPathDes(BusPath busPath) {
        if (busPath == null) {
            return String.valueOf("");
        }
        long second = busPath.getDuration();
        String time = getFriendlyTime((int) second);
        float subDistance = busPath.getDistance();
        String subDis = getFriendlyLength((int) subDistance);
        float walkDistance = busPath.getWalkDistance();
        String walkDis = getFriendlyLength((int) walkDistance);
        return String.valueOf(time + " | " + subDis + " | 步行" + walkDis);
    }

}
