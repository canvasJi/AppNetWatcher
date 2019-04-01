package com.wj.netmodule.listener;

import com.wj.netmodule.type.NetType;

/**
 * Created by jiwang on 2019/3/27.
 * 网络回掉监听
 */

public interface NetWorkChangeObserver {

    // 网络连接
    void onConnect(NetType netType);

    //网络断开
    void onDisConnect();
}
