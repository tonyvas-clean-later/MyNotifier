package com.example.mynotifier.serviceHelpers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.mynotifier.config.ConfigManager;
import com.example.mynotifier.services.MyService;

public class StartOnBoot extends BroadcastReceiver {
    private static final String TAG = "StartOnBoot reee";

    public void onReceive(Context context, Intent intent) {
        // Check if service is enabled
        if (new ConfigManager(context).getIsServiceEnabled()){
            // Start service if it is
            ServiceManager.startIfNotRunning(context, MyService.class);
            Log.d(TAG, "MyService started on boot!");
        }
        else{
            Log.d(TAG, "MyService is disabled, not starting on boot!");
        }
    }
}
