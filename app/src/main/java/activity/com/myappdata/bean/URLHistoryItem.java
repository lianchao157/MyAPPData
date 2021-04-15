package activity.com.myappdata.bean;

import java.io.Serializable;

public class URLHistoryItem implements Serializable {
    public URLHistoryItem(String visit_site, String visit_detail_url) {
        this.visit_site = visit_site;
        this.visit_detail_url = visit_detail_url;
    }

    public String getVisit_site() {
        return visit_site;
    }

    public void setVisit_site(String visit_site) {
        this.visit_site = visit_site;
    }

    public String getVisit_detail_url() {
        return visit_detail_url;
    }

    @Override
    public String toString() {
        return "URLHistoryItem{" +
                "visit_site='" + visit_site + '\'' +
                ", visit_detail_url='" + visit_detail_url + '\'' +
                '}';
    }

    public void setVisit_detail_url(String visit_detail_url) {
        this.visit_detail_url = visit_detail_url;
    }

    private String visit_site;
    private String visit_detail_url;
}
