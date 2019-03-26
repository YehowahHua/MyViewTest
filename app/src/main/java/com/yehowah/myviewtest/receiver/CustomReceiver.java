package com.yehowah.myviewtest.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.yehowah.myviewtest.MainActivity;

public class CustomReceiver extends BroadcastReceiver {
    private static final String TAG = "CustomReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e(TAG, "onReceive: 收到自定义广播--"+action);

        Intent startIntent = new Intent();
        startIntent.setClass(context, MainActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(startIntent);
    }
}
