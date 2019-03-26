package com.yehowah.myviewtest.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

//静态注册 电池电量低于10%就会出现
//android 8.0之后无法通过静态获取到这个广播，只能通过动态获取
public class BatteryLowReceiver extends BroadcastReceiver {
    private static final String TAG = "BatteryLowReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: action--"+intent);
    }
}
