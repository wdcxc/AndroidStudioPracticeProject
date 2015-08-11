package com.wdcxc.musicchannaldemo.utils;

import android.database.DefaultDatabaseErrorHandler;

import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.InputStream;
import java.math.MathContext;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ConcurrentModificationException;

/**
 * Created by wdcxc on 2015/8/4.
 */
public class NetConn {
    private static String netaddr;
    private InputStream is=null;
    private HttpURLConnection mhuc=null;

    public NetConn(String netaddr) {
        this.netaddr = netaddr;
    }

    public InputStream getInputStream() {
        try {
            URL murl = new URL(netaddr);
            mhuc = (HttpURLConnection) murl.openConnection();
            mhuc.setRequestMethod("GET");
            mhuc.setReadTimeout(3000);
            mhuc.setConnectTimeout(5000);
            mhuc.setDoInput(true);
            is = mhuc.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

    public void closeNetConn()
    {
        if(is!=null)
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        if(mhuc!=null)
            mhuc.disconnect();

    }

}