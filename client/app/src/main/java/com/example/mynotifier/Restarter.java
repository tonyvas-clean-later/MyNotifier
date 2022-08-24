package com.example.mynotifier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class Restarter extends BroadcastReceiver {
    private static final String TAG = "Restarter reeeeeeee";

    public void onReceive(Context context, Intent intent) {
        // Service intent
        Intent service = new Intent(context, MyService.class);

        // Android Oreo requires starting as foreground
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(service);
            Log.d(TAG, "Restarted using startForegroundService!");
        } else {
            context.startService(service);
            Log.d(TAG, "Restarted using startService!");
        }
    }
}