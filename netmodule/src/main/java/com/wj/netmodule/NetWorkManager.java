package com.wj.netmodule;

import android.app.Application;
import android.content.IntentFilter;

import com.wj.netmodule.config.Constants;
import com.wj.netmodule.listener.NetWorkChangeObserver;
import com.wj.netmodule.receiver.NetWorkReceiver;

/**
 * Created by jiwang on 2019/3/27.
 */

public class NetWorkManager {

    public static volatile NetWorkManager instance;
    private Application application;
    private NetWorkReceiver receiver;

    public NetWorkManager() {
        receiver = new NetWorkReceiver();
    }

    public static NetWorkManager getDefault() {
        if (instance == null) {
            synchronized (NetWorkManager.class) {
                if (instance == null) {
                    instance = new NetWorkManager();
                }
            }
        }
        return instance;
    }

    public void  setListener(NetWorkChangeObserver listener){
        receiver.setListener(listener);
    }

    /**
     * 初始化，动态广播注册
     * @param application
     */
    public  void init(Application application){
        this.application=application;
        if (null==application) {
            return;
        }
        IntentFilter filter=new IntentFilter();
        filter.addAction(Constants.ANDROID_NET_CHANGE_ACTION);
        application.registerReceiver(receiver,filter);
    }

    public Application getApplication() {

        if (application==null){
            throw new RuntimeException("application is null");
        }

        return application;
    }
}
