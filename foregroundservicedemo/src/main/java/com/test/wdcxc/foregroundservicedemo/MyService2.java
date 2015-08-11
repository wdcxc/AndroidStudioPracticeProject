package com.test.wdcxc.foregroundservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService2 extends Service {
    public MyService2() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("mtag", "ser2!!!");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
