package com.wdcxc.musicchannaldemo.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.wdcxc.musicchannaldemo.Bean.ChannalBean;
import com.wdcxc.musicchannaldemo.Bean.LocalBean;
import com.wdcxc.musicchannaldemo.model.IModel_Channal;
import com.wdcxc.musicchannaldemo.model.Model_Channal;
import com.wdcxc.musicchannaldemo.view.IView_Channal;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wdcxc on 2015/8/4.
 */
public class Presenter_Channal {
    private IView_Channal iv_chan;
    private IModel_Channal im_chan;

    public Presenter_Channal(IView_Channal iv_chan) {
        this.iv_chan = iv_chan;
        im_chan = new Model_Channal();
    }

    public void setContent(String position) {
        iv_chan.prepare();
        ChannalAsyncTask cat=new ChannalAsyncTask();
        Log.i("mtag", "pc_pre:setcontent@position:" + position);
        cat.execute(position);
    }

    public void playMp3(Context context, ChannalBean cb) {
        im_chan.playMp3(context,cb);
    }

    private class ChannalAsyncTask extends AsyncTask<String,Void,ArrayList<ChannalBean>>{

        @Override
        protected ArrayList<ChannalBean> doInBackground(String... params) {
            ArrayList<ChannalBean> al = im_chan.getContent(params[0]);
            return al;
        }

        @Override
        protected void onPostExecute(ArrayList<ChannalBean> channalBeans) {
            super.onPostExecute(channalBeans);
            iv_chan.show(channalBeans);
        }
    }
}
