package com.wdcxc.musicchannaldemo.utils;

import android.app.job.JobInfo;
import android.content.ClipData;
import android.util.Log;

import com.wdcxc.musicchannaldemo.Bean.ChannalBean;
import com.wdcxc.musicchannaldemo.Bean.LocalBean;
import com.wdcxc.musicchannaldemo.view.ChannalActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wdcxc on 2015/8/4.
 */
public class JsonParser {

    private String isToString(InputStream is) {
        if (null == is)
            Log.i("mtag", "jp_utl:istostring@is:" + is);

        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        StringBuilder sb=new StringBuilder();
        String s="";
        try {
            while((s=br.readLine())!=null)
            {
                sb.append((s));
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public ArrayList<LocalBean> localParse(InputStream is)
    {
        String s = isToString(is);
        ArrayList<LocalBean> al=new ArrayList<LocalBean>();
        try {
            JSONArray ja=new JSONArray(s);
            for(int i=0;i<ja.length();i++)
            {
                JSONObject jo= ja.getJSONObject(i);
                String localid=jo.getString("localID");
                String localname=jo.getString("localName");
                LocalBean lb=new LocalBean(localid,localname);
                al.add(lb);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return al;
    }

    public ArrayList<ChannalBean> channalParse(InputStream is)
    {
        String s=isToString(is);
        ArrayList<ChannalBean> al=new ArrayList<ChannalBean>();
        try {
            JSONArray ja=new JSONArray(s);
            for(int i=0;i<ja.length();i++){
                JSONObject jo=ja.getJSONObject(i);
                String chanid=jo.getString("chanID");
                String chantitle=jo.getString("chanTitle");
                String chanhls=jo.getString("chanHLS");
                String chanmp3url=jo.getString("chanHttp");
                String chanimgurl=jo.getString("chanImg");
                ChannalBean cb=new ChannalBean(chanid,chanhls,chanimgurl,chanmp3url,chantitle);
                al.add(cb);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return al;
    }

}
