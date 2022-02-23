package com.webbrains.backgroundservice.screens;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.webbrains.backgroundservice.R;
import com.webbrains.backgroundservice.service.BackgroundService;
import com.webbrains.backgroundservice.utility.Utility;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartServiceTap(View view) {
        Intent playerIntent = new Intent(MainActivity.this, BackgroundService.class);
        Log.e("isMyServiceRunning", Utility.isMyServiceRunning(MainActivity.this, BackgroundService.class) + "");
        if (!Utility.isMyServiceRunning(MainActivity.this, BackgroundService.class)) { // IMPORTANT
            startService(playerIntent);
        }
        bindService(playerIntent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // We've bound to LocalService, cast the IBinder and get LocalService instance
                BackgroundService.LocalBinder binder = (BackgroundService.LocalBinder) service;
                BackgroundService BackgroundService = binder.getService();

                Log.e("onServiceConnected", "yes");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        }, Context.BIND_AUTO_CREATE);
    }

    public void onSendBroadcastTap(View view) {
        Intent i1 = new Intent(Utility.BROADCAST_RECEIVER);
        i1.putExtra("data", "789");
        sendBroadcast(i1);
    }
}