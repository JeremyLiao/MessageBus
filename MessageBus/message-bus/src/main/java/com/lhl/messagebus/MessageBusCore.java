package com.lhl.messagebus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hailiangliao on 2016/10/19.
 */

class MessageBusCore {

    private static final String TAG = MessageBusCore.class.getSimpleName();

    private Context context;
    private Map<String, MessageActionInfo> actionInfoMap = new HashMap<>();

    public MessageBusCore(Context context) {
        this.context = context;
    }

    public void register(Object subscriber) {
        Method[] methods = subscriber.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(BroadcastAction.class)) {
                BroadcastAction action = method.getAnnotation(BroadcastAction.class);
                String actionName = action.action();
                actionInfoMap.put(actionName, new MessageActionInfo(actionName, subscriber, method));
            }
        }
        if (actionInfoMap.size() > 0) {
            IntentFilter filter = new IntentFilter();
            for (String action : actionInfoMap.keySet()) {
                filter.addAction(action);
            }
            context.registerReceiver(receiver, filter);
            Log.d(TAG, "registerReceiver");
        }
    }

    public void unregister() {
        if (actionInfoMap.size() > 0) {
            context.unregisterReceiver(receiver);
            Log.d(TAG, "unregisterReceiver");
        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (actionInfoMap.containsKey(action)) {
                try {
                    MessageActionInfo actionInfo = actionInfoMap.get(action);
                    actionInfo.getMethod().setAccessible(true);
                    actionInfo.getMethod().invoke(actionInfo.getReceiver(), intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
