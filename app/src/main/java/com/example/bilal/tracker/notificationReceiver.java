package com.example.bilal.tracker;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by asimh on 25-Mar-16.
 */
public class notificationReceiver extends BroadcastReceiver {
    Context context;

    private String TAG = notificationReceiver.class.getSimpleName();

    public notificationReceiver(){
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the data (SMS data) bound to intent
        this.context=context;

        Bundle bundle = intent.getExtras();


        SmsMessage[] msgs = null;

        String message = "";


        if (bundle != null){
            // Retrieve the Binary SMS data

            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];


            // For every SMS message received (although multipart is not supported with binary)
            msgs[0] = SmsMessage.createFromPdu((byte[]) pdus[0]);




            // Return the User Data section minus the
            // User Data Header (UDH) (if there is any UDH at all)
           // message = msgs[0].getMessageBody();
            //Toast.makeText(context, "hahahahhha", Toast.LENGTH_LONG).show();

            FlagClass.number=msgs[0].getOriginatingAddress();



              Log.d("helloi",msgs[0].getMessageBody()+"   d");
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

                if (wifi.isWifiEnabled()) {
                    FlagClass.wifi = true;

                } else {
                    wifi.setWifiEnabled(true);
                    FlagClass.wifi = false;
                }
                if (!isMyServiceRunning(MyService.class)) {
                    Intent i = new Intent(context, MyService.class);
                    // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.startService(i);
                } else {
                    // context.stopService(new Intent(context, MyService.class));
                }
            }
            else {

               // showGPSDisabledAlertToUser();
            }

           /* if(message.contains("light"))
            {
                Camera mCamera = Camera.open();
                mCamera.startPreview();
                Camera.Parameters params = mCamera.getParameters();
                if(params.getFlashMode() != null){
                    params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                }
                mCamera.setParameters(params);
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mCamera.stopPreview();
                mCamera.release();

            }
            else if(message.contains("location"))
            {
                LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                {
                    WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
                    if(wifi.isWifiEnabled())
                    {
                        FlagClass.wifi=true;

                    }
                    else {
                        wifi.setWifiEnabled(true);
                        FlagClass.wifi=false;
                    }
                    if(!isMyServiceRunning(MyService.class)) {
                        Intent i=new Intent(context,MyService.class);
                        // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startService(i);
                    }
                    else
                    {
                        // context.stopService(new Intent(context, MyService.class));
                    }

                }
                else{

                }

            }
            else {
                if(!isMyServiceRunning(MyService.class)) {

                }
                else
                {
                     context.stopService(new Intent(context, MyService.class));
                }

            }*/









        }
    }
    private void showGPSDisabledAlertToUser(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context.getApplicationContext());
        builder.setMessage("Yout GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        context.getApplicationContext().startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    public String concat(String [] strArr)
    {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            strBuilder.append(strArr[i]);
        }
        return strBuilder.toString();
    }
}