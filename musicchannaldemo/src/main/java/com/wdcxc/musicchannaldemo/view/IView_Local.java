package com.wdcxc.musicchannaldemo.view;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by wdcxc on 2015/8/4.
 */
public interface IView_Local {
    public void prepare();
    public void show(ArrayList<Map<String,String>> alm_local);
}
