package com.wdcxc.musicchannaldemo.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.wdcxc.musicchannaldemo.Bean.LocalBean;
import com.wdcxc.musicchannaldemo.MainActivity;
import com.wdcxc.musicchannaldemo.R;
import com.wdcxc.musicchannaldemo.model.IModel_Local;
import com.wdcxc.musicchannaldemo.model.Model_Local;
import com.wdcxc.musicchannaldemo.service.MediaService;
import com.wdcxc.musicchannaldemo.view.ChannalActivity;
import com.wdcxc.musicchannaldemo.view.IView_Local;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.SimpleTimeZone;

import static com.wdcxc.musicchannaldemo.R.layout.local_item_layout;

/**
 * Created by wdcxc on 2015/8/4.
 */
public class Presenter_Local{

    private IView_Local iv_local;
    private IModel_Local im_local;

    public Presenter_Local(IView_Local iv) {
        iv_local = iv;
        im_local = new Model_Local();
    }

    public void setContent()
    {
        Log.i("mtag","pl_pre:setcontent");
        iv_local.prepare();
        LocalAsyncTask la=new LocalAsyncTask();
        la.execute();
    }

    //获取simpleadapter所需的数据
    private ArrayList<Map<String, String>> getData(ArrayList<LocalBean> al) {
        ArrayList<Map<String, String>> alm_local = new ArrayList<Map<String, String>>();
        for (LocalBean lb : al) {
            Map<String, String> hm = new HashMap<String, String>();
            hm.put("local_id", lb.getLocalid());
            hm.put("local_name", lb.getLocalname());
            alm_local.add(hm);
        }
        return alm_local;
    }

    //跳转到ChannalActivity
    public void jumpToItem(String position) {
        Bundle b=new Bundle();
        b.putString("position",position);
        Intent i = new Intent((MainActivity) iv_local, ChannalActivity.class);
        i.putExtra("bundle",b);
        ((MainActivity) iv_local).startActivity(i);
    }

    public void release(Context context)
    {
        context.stopService(new Intent(context, MediaService.class));
    }


    private class LocalAsyncTask extends AsyncTask<String,Void,ArrayList<LocalBean>>
    {
        @Override
        protected ArrayList<LocalBean> doInBackground(String... params) {
            Log.i("mtag","pl_pre:localasynctask");
            ArrayList<LocalBean> al=im_local.getContent();
            Log.i("mtag","pl_pre:localasynctask@al:"+al.toString());
            return al;
        }

        @Override
        protected void onPostExecute(ArrayList<LocalBean> localBeans) {
            super.onPostExecute(localBeans);
            iv_local.show(getData(localBeans));
        }
    }
}



