package com.wj.netmodule.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import com.wj.netmodule.NetWorkManager;
import com.wj.netmodule.type.NetType;

/**
 * Created by jiwang on 2019/3/27.
 */

public class NetWorkUtil {


    public static boolean isNetWorkAvailable() {
        ConnectivityManager ctm = (ConnectivityManager) NetWorkManager.getDefault().getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (ctm == null) {
            return false;
        }
        // 返回所有网络信息
        NetworkInfo[] infos = ctm.getAllNetworkInfo();
        if (infos != null) {
            for (NetworkInfo info : infos) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 获取当前网络类型
     */
    public static NetType getNetType() {
        ConnectivityManager ctm = (ConnectivityManager) NetWorkManager.getDefault().getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (ctm == null) {
            return NetType.NONE;
        }
        NetworkInfo infos = ctm.getActiveNetworkInfo();
        if (infos == null) {
            return NetType.NONE;
        }

        int type = infos.getType();
        if (type == ConnectivityManager.TYPE_MOBILE) {
            if (infos.getExtraInfo().toLowerCase().equals("cmmnet")) {
                return NetType.CMNET;
            } else {
                return NetType.CMWAP;
            }
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            return NetType.WIFI;
        }
        return NetType.NONE;
    }


    public static void openSetting(Context context, int requesetCode) {

    }
}
