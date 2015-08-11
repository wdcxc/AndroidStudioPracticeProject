package com.demo.wdcxc.activityandservicelifedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{


    private Button bt_jtact1;
    private Button bt_starts;
    private Button bt_bs;
    private Button bt_stops;
    private Button bt_ubs;
    private Button bt_startbser;
    private Button bt_stopbser;
    private ServiceConnection sc=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.i("mtag", "act2:oncreate");

        bt_jtact1= (Button) findViewById(R.id.bt_toact1);
        bt_starts=(Button)findViewById(R.id.bt_startser);
        bt_stops=(Button)findViewById(R.id.bt_stopser);
        bt_bs=(Button)findViewById(R.id.bt_bindser);
        bt_ubs=(Button)findViewById(R.id.bt_unbindser);
        bt_startbser=(Button)findViewById(R.id.bt_startbser);
        bt_stopbser=(Button)findViewById(R.id.bt_stopser);

    }

    @Override
    protected void onStart() {
        Log.i("mtag", "act2:onstart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i("mtag","act2:onresume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("mtag","act2:onpause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("mtag","act2:onstop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("mtag","act2:ondestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.i("mtag","act2:onrestart");
        super.onRestart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.i("mtag","act2:onsaveinstance");
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i("mtag","act2:onrestoreinstance");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.bt_toact1:
            {
                Intent i=new Intent(this,MainActivity.class);
                startActivity(i);
                break;
            }
            case R.id.bt_startser:
            {
                Intent i=new Intent(this,MyService.class);
                startService(i);
                break;
            }
            case R.id.bt_stopser:{
                Intent i=new Intent(this,MyService.class);
                stopService(i);
                break;
            }
            case R.id.bt_bindser:{
                Intent i=new Intent(this,MyBindService.class);
                bindService(i, sc, BIND_AUTO_CREATE);
                break;
            }
            case R.id.bt_unbindser:{
                Intent i=new Intent(this,MyBindService.class);
                unbindService(sc);
                break;
            }
            case R.id.bt_startbser:{
                Intent i=new Intent(this,MyBindService.class);
                startService(i);
                break;
            }
            case R.id.bt_stoptbser:{
                Intent i=new Intent(this,MyBindService.class);
                stopService(i);
                break;
            }
        }
    }
}
