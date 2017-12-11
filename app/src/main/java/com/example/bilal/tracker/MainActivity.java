package com.example.bilal.tracker;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        Log.d("Applbilal12","Activity started");
//        Calendar calendar = Calendar.getInstance();
//
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        Intent ll24 = new Intent(this, AlarmReceiverLifeLog.class);
//        PendingIntent recurringLl24 = PendingIntent.getBroadcast(this, 0, ll24, PendingIntent.FLAG_CANCEL_CURRENT);
//        AlarmManager alarms = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        alarms.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 30, recurringLl24);





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(REQUEST_CODE == requestCode)
        {
            if(requestCode == Activity.RESULT_OK)
            {
                // done with activate to Device Admin
            }
            else
            {
                // cancle it.
            }
        }
    }

}
