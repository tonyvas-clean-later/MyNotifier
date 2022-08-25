package com.example.mynotifier.serviceHelpers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.mynotifier.services.MyService;

public class StartOnBoot extends BroadcastReceiver {
    private static final String TAG = "StartOnBoot reeeeeeee";

    public void onReceive(Context context, Intent intent) {
        ServiceManager.startIfNotRunning(context, MyService.class);
    }
}
