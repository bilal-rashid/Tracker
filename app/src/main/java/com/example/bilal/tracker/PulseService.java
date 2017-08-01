package com.example.bilal.tracker;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Bilal on 8/24/2016.
 */
public class PulseService extends IntentService {


    public PulseService() {
        super(PulseService.class.getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(this, "Pulse starting", Toast.LENGTH_SHORT).show(); //This happens!
        //mVibrator= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //mVibrator.vibrate(3000);
        //return START_STICKY;
        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    protected void onHandleIntent(Intent intent) {

        //Toast.makeText(this, "Pulse start", Toast.LENGTH_SHORT).show(); //This happens!

        this.stopSelf();
    }
}