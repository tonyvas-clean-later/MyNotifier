package com.example.mynotifier.activities;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotifier.R;

public class AboutActivity extends AppCompatActivity {
    private static final String TAG = "AboutActivity reee";

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
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}