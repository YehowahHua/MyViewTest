package com.yehowah.myviewtest.ui;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yehowah.myviewtest.R;
//动态注册广播
public class ReceiverTestActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "ReceiverTestActivity";

    private final String CUSTOM_ACTION = "com.yehowah.custom.action";
    private Button customReceiverBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_test);
        //1.添加ACTION广播 过滤
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        //2.new 一个新的广播
        //3.注册广播
        registerReceiver(mBatteryChangedReceiver,intentFilter);

        //自定义广播

        customReceiverBt = (Button) findViewById(R.id.customReceiverBt);
        customReceiverBt.setOnClickListener(this);

    }

    private BroadcastReceiver mBatteryChangedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: action--"+intent.getAction());
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBatteryChangedReceiver);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.customReceiverBt:
                Intent intent = new Intent();
                intent.setAction(CUSTOM_ACTION);
                //android 8.0之后需要指定  包名，类名
                intent.setComponent(new ComponentName("com.yehowah.myviewtest","com.yehowah.myviewtest.receiver.CustomReceiver"));
                Log.i(TAG, "onClick: 发送自定义广播");
                this.sendBroadcast(intent);//发送广播
                break;
        }

    }
}
