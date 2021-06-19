package com.example.monitoring_app.ui.notify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

public class NotifyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Uri path = Uri.parse(RingtoneManager.EXTRA_RINGTONE_TYPE);
        Ringtone tone = RingtoneManager.getRingtone(context,path);
        tone.play();

        //NotifyRing notifyRing = new NotifyRing(context);
        //Intent myIntent = new Intent(context,NotifyRing.class);
        //context.startService(myIntent);

    }
}
