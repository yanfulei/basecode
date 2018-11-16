package top.lsmod.me.basecode.eventbus.bean;

import top.lsmod.me.basecode.base.BaseReqBean;

/**
 * Created by yanfulei on 2018/10/4
 * Email yanfulei1990@gmail.com
 */
public class TestInterfaceDemoBean extends BaseReqBean {
    private String code;
    private String q;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }
}
