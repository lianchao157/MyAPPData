package activity.com.myappdata.adapter.fuzaadapter;

import android.support.v7.widget.GridLayoutManager;

public class SectionedSpanSizeLookup  extends GridLayoutManager.SpanSizeLookup{
    protected SectionedRecyclerViewAdapter<?, ?, ?, ?, ?> adapter = null;
    protected GridLayoutManager layoutManager = null;

    public SectionedSpanSizeLookup(SectionedRecyclerViewAdapter<?, ?, ?, ?, ?> adapter, GridLayoutManager layoutManager) {
        this.adapter = adapter;
        this.layoutManager = layoutManager;
    }

    @Override
    public int getSpanSize(int position) {
        if (adapter.hasHeader()) {//列表顶部有header
            if (position == 0) {
                return layoutManager.getSpanCount();
            } else if (position + 1 < adapter.getItemCount()) {
                if (adapter.isSectionHeaderPosition(position -1) || adapter.isSectionFooterPosition(position -1)) {
                    return layoutManager.getSpanCount();
                } else {
                    return 1;
                }
            } else {
                return layoutManager.getSpanCount();
            }
        } else {//列表顶部没有header
            if (position + 1 < adapter.getItemCount()) {
                if (adapter.isSectionHeaderPosition(position) || adapter.isSectionFooterPosition(position)) {
                    return layoutManager.getSpanCount();
                } else {
                    return 1;
                }
            } else {
                return layoutManager.getSpanCount();
            }
        }
    }
}
