package com.example.mynotifier.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.example.mynotifier.R;
import com.example.mynotifier.config.ConfigManager;
import com.example.mynotifier.serviceHelpers.ServiceManager;
import com.example.mynotifier.services.MyService;

public class SettingsActivity extends AppCompatActivity {
    private static final String TAG = "SettingsActivity reee";
    private ConfigManager config;
    private SwitchCompat serviceSwitch;
    private SwitchCompat darkModeSwitch;
    private EditText serverAddressEditText;
    private EditText serverPortEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        config = new ConfigManager(this);

        findViews();
        writeCurrentSettings();
        attachSettingViewListeners();

        setTitleBar();
    }

    private void findViews(){
        serviceSwitch = findViewById(R.id.setting_service_enable_switch);
        darkModeSwitch = findViewById(R.id.setting_dark_mode_enable_switch);
        serverAddressEditText = findViewById(R.id.setting_server_address_edittext);
        serverPortEditText = findViewById(R.id.setting_server_port_edittext);
    }

    private void writeCurrentSettings(){
        serviceSwitch.setChecked(config.getIsServiceEnabled());
        darkModeSwitch.setChecked(config.getIsDarkMode());
        serverAddressEditText.setText(config.getServerAddress());
        serverPortEditText.setText(String.valueOf(config.getServerPort()));
    }

    private void attachSettingViewListeners(){
        attachServiceSwitchListeners();
        attachDarkModeSwitchListeners();
        attachServerAddressEditTextListeners();
        attachServerPortEditTextListeners();
    }

    private void attachServiceSwitchListeners(){
        Context context = this;

        serviceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                config.setIsServiceEnabled(b);

                try {
                    if (b){
                        ServiceManager.startIfNotRunning(context, MyService.class);
                    }
                    else{
                        ServiceManager.stopIfRunning(context, MyService.class);
                    }
                }
                catch (Exception e){
                    Toast.makeText(context, "Failed to save service setting!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void attachDarkModeSwitchListeners(){
        Context context = this;

        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                config.setIsDarkMode(b);

                try {
                    if (b){
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    }
                    else{
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }
                }
                catch (Exception e){
                    Toast.makeText(context, "Failed to save dark mode setting!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void attachServerAddressEditTextListeners(){
        Context context = this;

        serverAddressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    config.setServerAddress(editable.toString());
                }
                catch (Exception e){
                    Toast.makeText(context, "Failed to save server address!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void attachServerPortEditTextListeners(){
        Context context = this;

        serverPortEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    int port = Integer.parseInt(editable.toString());
                    config.setServerPort(port);
                }
                catch (Exception e){
                    Toast.makeText(context, "Failed to save server port!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setTitleBar(){
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null){
            actionBar.setTitle(R.string.settings_activity_title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()){
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}