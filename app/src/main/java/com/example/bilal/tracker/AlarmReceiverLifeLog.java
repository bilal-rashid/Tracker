package com.example.bilal.tracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Bilal on 8/24/2016.
 */
public class AlarmReceiverLifeLog extends BroadcastReceiver {

    private static final String TAG = "LL24";
    static Context context;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.v(TAG, "Alarm for LifeLog...");

        Intent ll24Service = new Intent(context, PulseService.class);
        context.startService(ll24Service);
    }
}