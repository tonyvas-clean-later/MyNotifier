package com.example.mynotifier.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.mynotifier.R;
import com.example.mynotifier.config.ConfigManager;
import com.example.mynotifier.services.MyService;
import com.example.mynotifier.serviceHelpers.ServiceManager;

public class HistoryActivity extends AppCompatActivity {
    private static final String TAG = "HistoryActivity reee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitleBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // TODO - Fix these warning
        switch (item.getItemId()){
            case R.id.main_menu_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.main_menu_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setTitleBar(){
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null){
            actionBar.setTitle(R.string.history_activity_title);
        }
    }
}