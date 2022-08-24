package com.example.mynotifier.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;

import com.example.mynotifier.R;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private static final String TAG = "MyService reeeeeeee";
    private static final int TIMER_DELAY = 0;
    private static final int TIMER_PERIOD = 1000;

    private Timer timer;
    private int timerRunCount;

    @Override
    public IBinder onBind(Intent intent) {
        // Return null to disable binding
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate :)");
        // Create persistent notification to run service without it getting destroyed after 5 sec
        startForeground(69, createForegroundNotification());
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy :(");
        super.onDestroy();

        // Stop whatever the service is currently doing
        this.stop();

        // Create and broadcast intent to restart service
        Intent bcIntent = new Intent();
        bcIntent.setAction(getResources().getString(R.string.restarter_action));
        bcIntent.setClass(this, Restarter.class);
        this.sendBroadcast(bcIntent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand!");

        // Start whatever this service needs to do
        this.start();

        // Return sticky so system will restart service if it kills it
        return Service.START_STICKY;
    }

    private void start(){
        startTimer();
    }

    private void stop(){
        stopTimer();
    }

    private Notification createForegroundNotification(){
        // Create different notification based on SDK version
        // Android Oreo and above require custom channel for foreground notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Get channel ID and name from string resources
            String channelId = getResources().getString(R.string.notification_channel_id);
            String channelName = getResources().getString(R.string.notification_channel_name);

            // Create a new channel
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_NONE);
            channel.setLightColor(Color.RED);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

            // Add the new channel
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            assert manager != null;
            manager.createNotificationChannel(channel);

            // Create the notification and return it
            return new NotificationCompat.Builder(this, channelId).setOngoing(true)
                    .setContentTitle("App is running in background")
                    .setPriority(NotificationManager.IMPORTANCE_MIN)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .build();
        }
        else{
            // Create default notification
            return new Notification();
        }
    }

    private void startTimer(){
        Log.d(TAG, "startTimer!");

        this.timer = new Timer();
        this.timerRunCount = 0;

        // Timer that logs to debug its iteration count every second
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timerRunCount++;
                Log.d(TAG, "Timer run " + timerRunCount + "!");
            }
        }, TIMER_DELAY, TIMER_PERIOD);
    }

    private void stopTimer(){
        // Cancels timer if it was started
        if (this.timer != null){
            timer.cancel();
            timer = null;
        }
    }
}
