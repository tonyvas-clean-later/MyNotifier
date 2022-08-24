package com.example.mynotifier.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Autostarter extends BroadcastReceiver {
    private static final String TAG = "Autostart reeeeeeee";

    public void onReceive(Context context, Intent intent) {
        ServiceStarter.startIfNotRunning(context, MyService.class);
    }
}
