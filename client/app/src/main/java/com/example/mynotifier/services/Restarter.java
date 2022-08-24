package com.example.mynotifier.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Restarter extends BroadcastReceiver {
    private static final String TAG = "Restarter reeeeeeee";

    public void onReceive(Context context, Intent intent) {
        ServiceStarter.startIfNotRunning(context, MyService.class);
    }
}