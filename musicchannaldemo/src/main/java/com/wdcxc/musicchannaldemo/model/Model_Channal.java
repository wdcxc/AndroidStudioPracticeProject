package com.wdcxc.musicchannaldemo.model;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

import com.wdcxc.musicchannaldemo.Bean.ChannalBean;
import com.wdcxc.musicchannaldemo.service.MediaService;
import com.wdcxc.musicchannaldemo.utils.JsonParser;
import com.wdcxc.musicchannaldemo.utils.NetConn;

import java.io.InputStream;
import java.net.BindException;
import java.net.Inet4Address;
import java.util.ArrayList;

/**
 * Created by wdcxc on 2015/8/4.
 */
public class Model_Channal implements IModel_Channal {
    private static final String CHANNAL_BASIC_NETADDR=
            "http://course.gdou.com/qingting/home/getchannel?channelID=";
    private boolean isplay=false;
    private boolean ispause=false;
    private MediaService ms=null;
    private String curplaymp3=null;
    private Intent msi=null;

    private ServiceConnection sc=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //ms= ((MediaService.MediaBinder) service).getMediaService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
           ms=null;
        }
    };

    @Override
    public void playMp3(Context context,ChannalBean cb) {
        msi=new Intent(context,MediaService.class);
        Bundle b=new Bundle();
        b.putParcelable("cb", cb);
        b.putString("src","act");
//        if(isplay) {
//            if(!curplaymp3.equals(cb.getMp3url()))
//            {
//                Log.i("mtag", "mc_mod:playMp3:startnew");
//                b.putString("cmd", "playnewone");
//            }
//            else{
//                b.putString("cmd","pause");
//            }
//        }
//        else
//        {
//            Log.i("mtag", "mc_mod:playMp3:start");
//            if(ispause)
//                b.putString("cmd", "resume");
//            else
//                b.putString("cmd","play");
//            ispause=!ispause;
//        }
        msi.putExtra("bundle", b);
        context.startService(msi);
//        curplaymp3=cb.getMp3url();
        isplay=!isplay;
//        Log.i("mtag:","mc_mod:playmp3");
//        if(null==ms) {
//            Log.i("mtag:", "mc_mod:playmp3@ms:" + ms);
//            Intent i = new Intent(context, MediaService.class);
//            Bundle b=new Bundle();
//            b.putString("mp3url",mp3url);
//            i.putExtra("bundle",b);
//            context.bindService(i, sc, Service.BIND_AUTO_CREATE);
//        }
//        else{
//                if (!isplay) {
//                    if (curplaymp3 == null)
//                        ms.playMp3ByUrl(mp3url);
//                    else if (!mp3url.equals(curplaymp3)) {
//                        ms.replayAnotherMp3(mp3url);
//                    } else
//                        ms.resumePlayMp3();
//                } else
//                    ms.pausePlayMp3();
//            }
//        isplay=!isplay;
//        curplaymp3=mp3url;
    }

    @Override
    public ArrayList<ChannalBean> getContent(String position) {
        String channal_addr=CHANNAL_BASIC_NETADDR+position;
        ArrayList<ChannalBean> al=null;
        Log.i("mtag","mc_mod:getcontent@channal_addr:"+channal_addr);
        NetConn nc=new NetConn(channal_addr);
        InputStream is=nc.getInputStream();
        if(null!=is)
        al=new JsonParser().channalParse(is);
        nc.closeNetConn();
        return al;
    }
}
