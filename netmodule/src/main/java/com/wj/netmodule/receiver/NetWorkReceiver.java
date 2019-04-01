package com.wj.netmodule.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.wj.netmodule.config.Constants;
import com.wj.netmodule.listener.NetWorkChangeObserver;
import com.wj.netmodule.type.NetType;
import com.wj.netmodule.utils.NetWorkUtil;

/**
 * Created by jiwang on 2019/3/27.
 */

public class NetWorkReceiver extends BroadcastReceiver {

    private NetType netType;
    private NetWorkChangeObserver listener;

    public NetWorkReceiver() {
        netType = NetType.NONE;
    }

    public void setListener(NetWorkChangeObserver listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent == null || null == intent.getAction()) {
            return;
        }
        if (intent.getAction().equalsIgnoreCase(Constants.ANDROID_NET_CHANGE_ACTION)) {
            Log.e(Constants.LOG_TAG, "网络发生改变");
            netType = NetWorkUtil.getNetType();
            if (NetWorkUtil.isNetWorkAvailable()) {
                Log.e(Constants.LOG_TAG, "网络可用");
                if (null != listener) {
                    listener.onConnect(netType);
                }
            } else {
                Log.e(Constants.LOG_TAG, "网络不可用");
                if (null != listener) {
                    listener.onDisConnect();
                }

            }
        }

    }




}
