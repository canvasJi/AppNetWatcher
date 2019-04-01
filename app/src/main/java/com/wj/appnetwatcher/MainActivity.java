package com.wj.appnetwatcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.wj.netmodule.config.Constants;
import com.wj.netmodule.NetWorkManager;
import com.wj.netmodule.listener.NetWorkChangeObserver;
import com.wj.netmodule.type.NetType;

public class MainActivity extends AppCompatActivity implements NetWorkChangeObserver {

    private TextView tvNetStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNetStatus=findViewById(R.id.tvNetStatus);
        NetWorkManager.getDefault().init(getApplication());
        NetWorkManager.getDefault().setListener(this);
    }

    @Override
    public void onConnect(NetType netType) {
        Log.e(Constants.LOG_TAG, netType.name());
        tvNetStatus.setText("当前网络："+netType.name());
    }

    @Override
    public void onDisConnect() {

        tvNetStatus.setText("无网络");
    }
}
