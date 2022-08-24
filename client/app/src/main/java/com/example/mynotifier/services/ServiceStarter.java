package com.example.mynotifier.services;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class ServiceStarter {
    private static final String TAG = "ServiceStarter reeeeeee";

    public static boolean isRunning(Context context, Class<?> cls){
        // Get activity manager
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        // Loop over all services
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)){
            // If service class matches the one passed in arg, then it is running
            if (service.service.getClassName().equals(cls.getName())){
                return true;
            }
        }

        // If no services match, service is not running
        return false;
    }

    public static void startIfNotRunning(Context context, Class<?> cls){
        // Check if service is already running
        if (!isRunning(context, cls)){
            // Service intent
            Intent service = new Intent(context, cls);

            // Android Oreo requires starting as foreground
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Start service in foreground
                context.startForegroundService(service);
                Log.d(TAG, "Started using startForegroundService!");
            } else {
                // Start service in background
                context.startService(service);
                Log.d(TAG, "Started using startService!");
            }
        }
    }
}
