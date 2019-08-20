package com.yehowah.myviewtest.ui;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yehowah.myviewtest.R;
//动态注册广播
public class ReceiverTestActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "ReceiverTestActivity";

    private final String CUSTOM_ACTION = "com.yehowah.custom.action";
    private Button customReceiverBt;
    private ProgressBar mBatteryLevel;
    private TextView barryId;
    private TextView mBatteryHealth;
    private TextView mBatteryStatus;

    private BroadcastReceiver mBatteryChangedReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_test);
        //1.添加ACTION广播 过滤
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        //2.new 一个新的广播
        mBatteryChangedReceiver = new BatteryChangedReceiver();
        //3.注册广播
        registerReceiver(mBatteryChangedReceiver,intentFilter);

        //自定义广播

        customReceiverBt = (Button) findViewById(R.id.customReceiverBt);
        customReceiverBt.setOnClickListener(this);

    }


    private void initView(){
        barryId = (TextView) findViewById(R.id.barryId);
        mBatteryStatus = (TextView) findViewById(R.id.tv_status);
        mBatteryHealth = (TextView) findViewById(R.id.tv_health);
        mBatteryLevel = (ProgressBar) findViewById(R.id.proBarId);
        mBatteryLevel.setMax(100);

    }


    class BatteryChangedReceiver extends BroadcastReceiver{
        int health;
        int level;
        int status;
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "onReceive: action--"+intent.getAction());
            if (action == Intent.ACTION_BATTERY_CHANGED){
                Log.d(TAG, "onReceive: get battery changed");
                health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH,BatteryManager.BATTERY_HEALTH_GOOD);
                level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,BatteryManager.BATTERY_STATUS_DISCHARGING);
                Log.i(TAG, "onReceive: health--"+health+",level--"+level+",status--"+status);
                updateBatteryView(health,status,level);
            }
        }

        private void updateBatteryView(int health, int status, int level) {
            barryId.setText("当前电量: "+level);
            //更新电池电量
            mBatteryLevel.setProgress(level);
            if (status == BatteryManager.BATTERY_STATUS_CHARGING){
                mBatteryStatus.setText("电池状态：充电中");
            }else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING){
                mBatteryStatus.setText("电池状态：未充电");
            }
            if (health == BatteryManager.BATTERY_HEALTH_GOOD){
                mBatteryHealth.setText("电池健康程度："+" Good");
            }else if (health == BatteryManager.BATTERY_HEALTH_UNKNOWN){
                mBatteryHealth.setText("电池健康程度："+" unknown");
            }

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBatteryChangedReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        
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
