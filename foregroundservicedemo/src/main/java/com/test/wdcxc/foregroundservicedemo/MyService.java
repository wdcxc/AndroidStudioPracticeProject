package com.test.wdcxc.foregroundservicedemo;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    private int i;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        i=0;
        Log.i("mtag", "ser:oncreate@i:" + i++);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("mtag", "ser:onstratcommand@i:" + i++);
        Log.i("mtag","!!!:"+intent.getStringExtra("cmd"));
        Intent i_stop=new Intent(this,MyService.class);
        i_stop.putExtra("cmd","stop");
        PendingIntent pi_stop=PendingIntent.getService(this,0,i_stop,PendingIntent.FLAG_UPDATE_CURRENT);

        Intent i_start=new Intent(this,MyService.class);
        i_start.putExtra("cmd","start");
        PendingIntent pi_start=PendingIntent.getService(this,1,i_start,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder notib=new Notification.Builder(this);
        notib.setSmallIcon(R.mipmap.ic_launcher);
        notib.setContentTitle("title");
        notib.setContentText("content");
        notib.addAction(0, "start", pi_start);
        notib.addAction(0, "stop", pi_stop);
        notib.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.music));
        //notib.setStyle(new Notification.MediaStyle().setShowActionsInCompactView(0,1));


        if("stop".equals(intent.getStringExtra("cmd"))) {
            Log.i("mtag","HHH");
            notib.setContentTitle("hahaha");

        }
        startForeground(1, notib.build());



        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("mtag","ser:ondestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
