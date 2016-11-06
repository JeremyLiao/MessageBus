package com.lhl.messagebus;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hailiangliao on 2016/10/19.
 */

public class MessageBus {

    private static final String TAG = MessageBus.class.getSimpleName();
    private static MessageBus defaultMessageBus = null;

    public static MessageBus getDefault() {
        if (defaultMessageBus == null) {
            synchronized (MessageBus.class) {
                if (defaultMessageBus == null) {
                    defaultMessageBus = new MessageBus();
                }
            }
        }
        return defaultMessageBus;
    }

    private Map<Object, MessageBusCore> coreMap = new HashMap<>();

    private MessageBus() {
    }

    public void register(Context context, Object subscriber) {
        MessageBusCore core = new MessageBusCore(context);
        core.register(subscriber);
        coreMap.put(subscriber, core);
        Log.d(TAG, "Put subscriber in CoreMap: " + subscriber);
    }

    public void unregister(Object subscriber) {
        if (coreMap.containsKey(subscriber)) {
            coreMap.get(subscriber).unregister();
            coreMap.remove(subscriber);
            Log.d(TAG, "Remove subscriber from CoreMap: " + subscriber);
        }
    }

    public void post(Context context, Intent intent) {
        context.sendBroadcast(intent);
    }
}
