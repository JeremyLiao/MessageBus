package com.lhl.messagebus;

import java.lang.reflect.Method;

/**
 * Created by hailiangliao on 2016/10/19.
 */

class MessageActionInfo {
    private Object receiver;
    private String actionName;
    private Method method;

    public MessageActionInfo(String actionName, Object receiver, Method method) {
        this.receiver = receiver;
        this.actionName = actionName;
        this.method = method;
    }

    public Object getReceiver() {
        return receiver;
    }

    public void setReceiver(Object receiver) {
        this.receiver = receiver;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
