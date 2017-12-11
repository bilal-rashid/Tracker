package com.example.bilal.tracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Telephony;
import android.util.Log;

/**
 * Created by Bilal on 8/24/2016.
 */
public class AlarmReceiverLifeLog extends BroadcastReceiver {

    private static final String TAG = "LL24";
    static Context context;

    @Override
    public void onReceive(Context context, Intent intent) {

//        Log.v(TAG, "Alarm for LifeLog...");

//        Intent ll24Service = new Intent(context, PulseService.class);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Telephony.Sms.Intents.DATA_SMS_RECEIVED_ACTION);
        filter.addDataScheme("sms");
//        context.registerReceiver(new notificationReceiver(),filter);
    }
}