package top.lsmod.me.basecode.eventbus.bean;

import android.app.Activity;

/**
 * Created by yanfulei on 2018/10/4
 * Email yanfulei1990@gmail.com
 */
public class BaseNetWorkEbReqBean {
    private Class cla;
    private String url;
    private String json;
    private String httpType;
    private boolean isAuto;
    private int interfaceId;
    private Activity activity;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public int getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(int interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getUrl() {
        return url;
    }

    public boolean isAuto() {
        return isAuto;
    }

    public void setAuto(boolean auto) {
        isAuto = auto;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpType() {
        return httpType.toLowerCase();
    }

    public void setHttpType(String httpType) {
        this.httpType = httpType;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Class getCla() {
        return cla;
    }

    public void setCla(Class cla) {
        this.cla = cla;
    }
}
