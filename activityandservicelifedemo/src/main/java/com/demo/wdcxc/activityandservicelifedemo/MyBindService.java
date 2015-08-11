package com.demo.wdcxc.activityandservicelifedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.View;



public class MyBindService extends Service {

    private int i;

    public MyBindService() {
    }

    @Override
    public void onCreate() {
        i=0;
        super.onCreate();
        Log.i("mtag", "bser:oncreate@i:"+i++);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("mtag","bser:onstartcommand@i:"+i++);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.i("mtag","bser:onbind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("mtag","bser:onunbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("mtag","bser:ondestroy");
    }
}
