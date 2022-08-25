package com.example.mynotifier.config;

import android.content.Context;
import android.content.SharedPreferences;

public class ConfigManager {
    private static final String TAG = "ConfigManager reeeeeee";
    private static final String SHARED_PREFERENCES_NAME = "config";
    private static final int SHARED_PREFERENCES_MODE = Context.MODE_PRIVATE;

    private static final String IS_SERVICE_ENABLED_KEY = "service_enabled";
    private static final String IS_DARK_MODE_KEY = "dark_mode";
    private static final String SERVER_ADDRESS_KEY = "server_address";
    private static final String SERVER_PORT_KEY = "server_port";

    private static final boolean DEFAULT_IS_SERVICE_ENABLED = false;
    private static final boolean DEFAULT_IS_DARK_MODE = false;
    private static final String DEFAULT_SERVER_ADDRESS = "http://www.example.com";
    private static final int DEFAULT_SERVER_PORT = 1234;

    private final Context context;

    public ConfigManager(Context context){
        this.context = context;
    }

    private SharedPreferences getSharedPreferences(){
        return this.context.getSharedPreferences(SHARED_PREFERENCES_NAME, SHARED_PREFERENCES_MODE);
    }

    public boolean getIsServiceEnabled(){
        return this.getSharedPreferences().getBoolean(IS_SERVICE_ENABLED_KEY, DEFAULT_IS_SERVICE_ENABLED);
    }

    public void setIsServiceEnabled(boolean enabled){
        this.getSharedPreferences().edit().putBoolean(IS_SERVICE_ENABLED_KEY, enabled).apply();
    }

    public boolean getIsDarkMode(){
        return this.getSharedPreferences().getBoolean(IS_DARK_MODE_KEY, DEFAULT_IS_DARK_MODE);
    }

    public void setIsDarkMode(boolean isDarkMode){
        this.getSharedPreferences().edit().putBoolean(IS_DARK_MODE_KEY, isDarkMode).apply();
    }

    public String getServerAddress(){
        return this.getSharedPreferences().getString(SERVER_ADDRESS_KEY, DEFAULT_SERVER_ADDRESS);
    }

    public void setServerAddress(String address){
        this.getSharedPreferences().edit().putString(SERVER_ADDRESS_KEY, address).apply();
    }

    public int getServerPort(){
        return this.getSharedPreferences().getInt(SERVER_PORT_KEY, DEFAULT_SERVER_PORT);
    }

    public void setServerPort(int port){
        this.getSharedPreferences().edit().putInt(SERVER_PORT_KEY, port).apply();
    }
}
