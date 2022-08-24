package com.example.mynotifier.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mynotifier.R;
import com.example.mynotifier.services.MyService;
import com.example.mynotifier.services.ServiceStarter;

public class HistoryActivity extends AppCompatActivity {
    private static final String TAG = "HistoryActivity reeeeee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ServiceStarter.startIfNotRunning(this, MyService.class);
    }
}