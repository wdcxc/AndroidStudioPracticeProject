package com.demo.wdcxc.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.apache.http.entity.InputStreamEntity;

import java.security.cert.PolicyNode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bt_noti_nor;
    private Button bt_noti_prg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_noti_nor= (Button) findViewById(R.id.bt_noti_nor);
        bt_noti_prg= (Button) findViewById(R.id.bt_noti_prg);

        bt_noti_nor.setOnClickListener(this);
        bt_noti_prg.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        Notification.Builder nb=new Notification.Builder(this);
        nb.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("title")
                .setContentText("text");
        NotificationManager nm= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        switch(v.getId())
        {
            case R.id.bt_noti_nor:
            {
                Intent i_reg_act=new Intent(this,RegularActivity.class);
                TaskStackBuilder tsb=TaskStackBuilder.create(this);
                tsb.addParentStack(RegularActivity.class);
                tsb.addNextIntent(i_reg_act);
                PendingIntent pi_reg_act=tsb.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);

                Intent i_spe_act=new Intent(this,SpecialActivity.class);
                i_spe_act.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pi_spe_act= PendingIntent.getActivity(this,2,i_spe_act,PendingIntent.FLAG_UPDATE_CURRENT);

                nb.setContentIntent(pi_reg_act);
                nb.setAutoCancel(true);

                nb.addAction(0,"reg_act",pi_reg_act);
                nb.addAction(0,"spe_act",pi_spe_act);

                nm.notify(1,nb.build());
                break;
            }
            case R.id.bt_noti_prg:
            {
                nb.setProgress(100, 80, false);
                nm.notify(0,nb.build());
                break;
            }
        }
    }
}
