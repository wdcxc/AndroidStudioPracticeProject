package com.wdcxc.musicchannaldemo.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.session.PlaybackState;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.wdcxc.musicchannaldemo.Bean.ChannalBean;
import com.wdcxc.musicchannaldemo.Bean.LocalBean;
import com.wdcxc.musicchannaldemo.R;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ServiceConfigurationError;

/**
 * Created by wdcxc on 2015/8/5.
 */
public class MediaService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.i("mtag", "ms_ser:onprepared");
        mp.start();
    }

    private MediaPlayer mp = null;
    private boolean isplay = false;
    private Notification.Builder notib = null;
    private ChannalBean cb = null;
    private int playtimes;

    @Override
    public void onCreate() {
        super.onCreate();
        mp = new MediaPlayer();
        mp.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        notib = new Notification.Builder(this);
        playtimes = 0;
        Log.i("mtag", "ms_ser:oncreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bundle b = intent.getBundleExtra("bundle");
        cb = (ChannalBean) b.getParcelable("cb");
        String src = b.getString("src");
        if ("act".equals(src)) {
            if (playtimes == 0)
                playMp3ByUrl(cb.getMp3url());
            else {
                mp.stop();
                mp.reset();
                playMp3ByUrl(cb.getMp3url());
            }
            isplay = true;
            startForegroundService("pause");

        } else if ("noti".equals(src)) {
            String cmd = b.getString("cmd");
            if ("exit".equals(cmd)) {
                Log.i("mtag","ms_ser:onstartcommand:exit");
                stopForeground(true);
                if (isplay)
                    mp.stop();
                this.stopSelf();
            } else if ("auto".equals(cmd)) {
                if (isplay) {
                    mp.pause();
                    startForegroundService("play");
                } else {
                    mp.start();
                    startForegroundService("pause");
                }
                isplay = !isplay;
            }
        }
        playtimes++;

//        if("play".equals(cmd))
//        {
//            playMp3ByUrl(cb.getMp3url());
//        }
//        else if("pause".equals(cmd))
//        {
//            mp.pause();
//        }
//        else if("playnewone".equals(cmd))
//        {
//            mp.stop();
//            mp.reset();
//            playMp3ByUrl(cb.getMp3url());
//        }
//        else if("resume".equals(cmd))
//        {
//            mp.start();
//        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void playMp3ByUrl(String mp3url) {
        try {
            Log.i("mtag", "ms_ser:playmp3byurl@mp3url:" + mp3url);
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setDataSource(mp3url);
            mp.setOnPreparedListener(this);
            mp.prepareAsync();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startForegroundService(String btucontent) {
        Intent i_pause = new Intent(this, MediaService.class);
        Bundle b_pause = new Bundle();
        b_pause.putString("cmd", "auto");
        b_pause.putString("src", "noti");
        b_pause.putParcelable("cb", cb);
        i_pause.putExtra("bundle", b_pause);
        PendingIntent pi_pause = PendingIntent.getService(this, 1, i_pause, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent i_exit = new Intent(this, MediaService.class);
        Bundle b_exit = new Bundle();
        b_exit.putString("src", "noti");
        b_exit.putString("cmd", "exit");
        b_exit.putParcelable("cb", cb);
        i_exit.putExtra("bundle", b_exit);
        PendingIntent pi_exit = PendingIntent.getService(this, 2, i_exit, PendingIntent.FLAG_UPDATE_CURRENT);

        notib = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(cb.getChanid())
                .setContentText(cb.getChantitle())
                .addAction(0, btucontent, pi_pause)
                .addAction(0, "exit", pi_exit);
        startForeground(1, notib.build());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        this.stopSelf();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        mp.release();
        mp = null;
        return false;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //return new MediaBinder();
        return null;
    }

//    public class MediaBinder extends Binder {
//        public MediaService getMediaService()
//        {
//            return MediaService.this;
//        }
//    }


}
