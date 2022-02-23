package com.webbrains.backgroundservice.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.webbrains.backgroundservice.utility.Utility;

public class BackgroundService extends Service {

    private static final String tag = "BackgroundService";

    @Override
    public void onCreate() {
        Log.e(tag, "onCreate");

        registerBroadcastReceiver();

        super.onCreate();
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                Log.e("data", intent.getStringExtra("data"));
            }
            Log.e("broadcastReceiver", "12345");
        }
    };

    private void registerBroadcastReceiver() {
        Log.e(tag, "registerSearchDriverStatus");
        try {
            IntentFilter filter = new IntentFilter(Utility.BROADCAST_RECEIVER);
            registerReceiver(broadcastReceiver, filter);
        } catch (Exception e) {

        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(tag, "onStartCommand"); // 2
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(tag, "onBind");
        return new LocalBinder();
    }

    public class LocalBinder extends Binder {
        public BackgroundService getService() {
            // Return this instance of LocalService so clients can call public methods
            return BackgroundService.this;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(tag, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e(tag, "onUnbind");
        super.onDestroy();
    }
}
