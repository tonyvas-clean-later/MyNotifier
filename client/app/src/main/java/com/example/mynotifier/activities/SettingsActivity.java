package com.example.mynotifier.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mynotifier.R;
import com.example.mynotifier.services.MyService;
import com.example.mynotifier.services.ServiceStarter;

public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "SettingsActivity reeeee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ServiceStarter.startIfNotRunning(this, MyService.class);
    }
}