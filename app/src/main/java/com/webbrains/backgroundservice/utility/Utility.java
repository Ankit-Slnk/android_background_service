package com.webbrains.backgroundservice.utility;

import android.app.ActivityManager;
import android.content.Context;

public class Utility {

    public static final String BROADCAST_RECEIVER = "BROADCAST_RECEIVER";

    public static boolean isMyServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
