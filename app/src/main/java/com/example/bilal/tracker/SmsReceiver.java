package com.example.bilal.tracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Bilal on 10/9/2016.
 */
public class SmsReceiver extends BroadcastReceiver {
    private String TAG = SmsReceiver.class.getSimpleName();

    public SmsReceiver() {
    }
    public void deleteSMS(Context ctx, String message, String number) {
        try {
            Uri uriSms = Uri.parse("content://sms");
            Cursor c = ctx.getContentResolver().query(uriSms,
                    new String[] { "_id", "thread_id", "address",
                            "person", "date", "body" }, null, null, null);

            Log.i(TAG, "c count......"+c.getCount());
            if (c != null && c.moveToFirst()) {
                do {
                    long id = c.getLong(0);
                    long threadId = c.getLong(1);
                    String address = c.getString(2);
                    String body = c.getString(5);
                    String date = c.getString(3);
                    Log.e("log>>>", "0>" + c.getString(0) + "1>" + c.getString(1)   + "2>" + c.getString(2) + "<-1>"  + c.getString(3) + "4>" + c.getString(4)+ "5>" + c.getString(5));
//                    Log.e("log>>>", "date" + c.getString(0));

//                    if (body.contains(getResources().getText(R.string.invite_text).toString()) && address.equals(number)) {
                    if (message.contains(body) && address.equals(number)) {
                        // mLogger.logInfo("Deleting SMS with id: " + threadId);
                        int rows = ctx.getContentResolver().delete(Uri.parse("content://sms/" + id), "date=?",new String[] { c.getString(4) });
                        Log.e("log>>>", "Delete success......... rows: "+rows);
                        Log.e("log>>>", "Delete success......... body: "+body);
                    }
                } while (c.moveToNext());
            }

        } catch (Exception e) {
            Log.e("log>>>", e.toString());
            Log.e("log>>>", e.getMessage());
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the data (SMS data) bound to intent
        Bundle bundle = intent.getExtras();

        SmsMessage[] msgs = null;

        String str = "";

        if (bundle != null) {
            // Retrieve the SMS Messages received
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];

            String number="";//+msgs[0].getOriginatingAddress();
            // For every SMS message received
            for (int i=0; i < msgs.length; i++) {
                // Convert Object array
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                // Sender's phone number
                str += "SMS from " + msgs[i].getOriginatingAddress() + " : ";
                number=msgs[i].getOriginatingAddress();
                // Fetch the text message
                str += msgs[i].getMessageBody().toString();
                // Newline <img draggable="false" class="emoji" alt="🙂" src="https://s.w.org/images/core/emoji/72x72/1f642.png">
                str += "\n";
            }
            Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
           // abortBroadcast();
          //  deleteSMS(context,str,number);

            // Display the entire SMS Message
         //   Log.d(TAG, str);
        }
    }
}