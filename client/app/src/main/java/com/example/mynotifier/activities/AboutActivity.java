package com.example.mynotifier.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotifier.R;
import com.example.mynotifier.services.MyService;
import com.example.mynotifier.services.ServiceStarter;

public class AboutActivity extends AppCompatActivity {
    private static final String TAG = "AboutActivity reeeeee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitleBar();
    }

    private void setTitleBar(){
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null){
            actionBar.setTitle(R.string.about_activity_title);
        }
    }
}