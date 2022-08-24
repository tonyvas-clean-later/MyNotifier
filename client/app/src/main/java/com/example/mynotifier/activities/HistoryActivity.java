package com.example.mynotifier.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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
        setTitleBar();

        ServiceStarter.startIfNotRunning(this, MyService.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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