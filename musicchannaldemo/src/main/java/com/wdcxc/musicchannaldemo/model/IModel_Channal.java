package com.wdcxc.musicchannaldemo.model;

import android.content.Context;

import com.wdcxc.musicchannaldemo.Bean.ChannalBean;

import java.util.ArrayList;

/**
 * Created by wdcxc on 2015/8/4.
 */

public interface IModel_Channal{

    public ArrayList<ChannalBean> getContent(String position);

    public void playMp3(Context context, ChannalBean cb);
}
