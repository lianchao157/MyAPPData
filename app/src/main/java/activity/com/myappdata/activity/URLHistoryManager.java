package activity.com.myappdata.activity;

import android.content.Context;

import java.util.ArrayList;

import activity.com.myappdata.bean.URLHistoryItem;

class URLHistoryManager {
    public ArrayList<String> getHistorySites() {
        return HistorySites;
    }

    public URLHistoryManager() {
    }
    public URLHistoryManager(Context context) {
        mContenx=context;
    }
    public URLHistoryManager(ArrayList<String> historySites) {
        HistorySites = historySites;
    }

    public void setHistorySites(ArrayList<String> historySites) {
        HistorySites = historySites;
    }

    private ArrayList<String> HistorySites;

    public ArrayList<URLHistoryItem> getHistorySites1() {
        return HistorySites1;
    }

    public void setHistorySites1(ArrayList<URLHistoryItem> historySites1) {
        HistorySites1 = historySites1;
    }

    private ArrayList<URLHistoryItem> HistorySites1;
    private  Context mContenx;
}
