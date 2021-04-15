package activity.com.myappdata.bean;

/**
 * Created by Administrator on 2018/7/10.
 */

public class ApprovalData {
    private String id;
    private String auditstate;
    private String audittime;
    private String username;
    private String apptime;
    private String usingtime;
    private String vehicletype;
    private String orgname;
    private String omileage;
    private String imileage;
    private String vehicleno;
    private String vehicleid;
    private String reason;
    private String auditflow;

    @Override
    public String toString() {
        return "ApprovalData{" +
                "id='" + id + '\'' +
                ", auditstate='" + auditstate + '\'' +
                ", audittime='" + audittime + '\'' +
                ", username='" + username + '\'' +
                ", apptime='" + apptime + '\'' +
                ", usingtime='" + usingtime + '\'' +
                ", vehicletype='" + vehicletype + '\'' +
                ", orgname='" + orgname + '\'' +
                ", omileage='" + omileage + '\'' +
                ", imileage='" + imileage + '\'' +
                ", vehicleno='" + vehicleno + '\'' +
                ", vehicleid='" + vehicleid + '\'' +
                ", reason='" + reason + '\'' +
                ", auditflow='" + auditflow + '\'' +
                ", auditcom='" + auditcom + '\'' +
                '}';
    }



    public String getAuditcom() {
        return auditcom;
    }

    public void setAuditcom(String auditcom) {
        this.auditcom = auditcom;
    }

    private String auditcom;// 不过的原因

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuditstate() {
        return auditstate;
    }

    public void setAuditstate(String auditstate) {
        this.auditstate = auditstate;
    }

    public String getAudittime() {
        return audittime;
    }

    public void setAudittime(String audittime) {
        this.audittime = audittime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApptime() {
        return apptime;
    }

    public void setApptime(String apptime) {
        this.apptime = apptime;
    }

    public String getUsingtime() {
        return usingtime;
    }

    public void setUsingtime(String usingtime) {
        this.usingtime = usingtime;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getOmileage() {
        return omileage;
    }

    public void setOmileage(String omileage) {
        this.omileage = omileage;
    }

    public String getImileage() {
        return imileage;
    }

    public void setImileage(String imileage) {
        this.imileage = imileage;
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAuditflow() {
        return auditflow;
    }

    public void setAuditflow(String auditflow) {
        this.auditflow = auditflow;
    }

    public ApprovalData(String id, String auditstate, String audittime, String username, String apptime, String usingtime, String vehicletype, String orgname, String omileage, String imileage, String vehicleno, String vehicleid, String reason, String auditflow) {
        this.id = id;
        this.auditstate = auditstate;
        this.audittime = audittime;
        this.username = username;
        this.apptime = apptime;
        this.usingtime = usingtime;
        this.vehicletype = vehicletype;
        this.orgname = orgname;
        this.omileage = omileage;
        this.imileage = imileage;
        this.vehicleno = vehicleno;
        this.vehicleid = vehicleid;
        this.reason = reason;
        this.auditflow = auditflow;
    }
}
