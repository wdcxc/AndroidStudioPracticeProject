package com.wdcxc.musicchannaldemo.model;

import android.os.AsyncTask;

import com.wdcxc.musicchannaldemo.Bean.LocalBean;
import com.wdcxc.musicchannaldemo.MainActivity;
import com.wdcxc.musicchannaldemo.utils.JsonParser;
import com.wdcxc.musicchannaldemo.utils.NetConn;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by wdcxc on 2015/8/4.
 */
public class Model_Local implements IModel_Local {

    private static final String LOCAL_NETADDRr = "http://course.gdou.com/qingting/home/getlocal";

    @Override
    public ArrayList<LocalBean> getContent() {
        ArrayList<LocalBean> al = null;
        NetConn nc = new NetConn(LOCAL_NETADDRr);
        InputStream is = nc.getInputStream();
        if (null != is)
            al = new JsonParser().localParse(is);
        nc.closeNetConn();
        return al;
    }
}
