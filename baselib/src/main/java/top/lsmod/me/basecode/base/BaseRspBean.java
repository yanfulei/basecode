package top.lsmod.me.basecode.base;

import java.io.Serializable;

/**
 * Author:yanfulei
 * Date:2018/11/6
 * Email:yanfulei1990@gmail.com
 **/
public class BaseRspBean implements Serializable {
    public Boolean Success;
    public String Message;

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean success) {
        Success = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
