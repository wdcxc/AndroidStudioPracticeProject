package com.wdcxc.musicchannaldemo.view;

import com.wdcxc.musicchannaldemo.Bean.ChannalBean;

import java.util.ArrayList;

/**
 * Created by wdcxc on 2015/8/4.
 */
public interface IView_Channal {

    public void prepare();

    public void show(ArrayList<ChannalBean> al);
}
