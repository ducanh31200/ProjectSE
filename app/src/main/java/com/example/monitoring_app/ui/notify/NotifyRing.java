package com.example.monitoring_app.ui.notify;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.monitoring_app.R;


public class NotifyRing extends Service {
    MediaPlayer mediaPlayer;
    Context context;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public NotifyRing() {

    }
    public NotifyRing(Context con)
    {
        context = con;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer = MediaPlayer.create(context, R.raw.nhacchuong);
        mediaPlayer.start();
        return START_NOT_STICKY;
    }
}
