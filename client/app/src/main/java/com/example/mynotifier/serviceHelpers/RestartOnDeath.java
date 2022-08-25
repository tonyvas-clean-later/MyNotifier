package com.example.mynotifier.serviceHelpers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.mynotifier.services.MyService;

public class RestartOnDeath extends BroadcastReceiver {
    private static final String TAG = "RestartOnDeath reeeeeeee";

    public void onReceive(Context context, Intent intent) {
        ServiceManager.startIfNotRunning(context, MyService.class);
    }
}