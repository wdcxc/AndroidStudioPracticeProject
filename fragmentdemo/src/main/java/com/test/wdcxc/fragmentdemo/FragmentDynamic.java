package com.test.wdcxc.fragmentdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wdcxc on 2015/8/11.
 */
public class FragmentDynamic extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dynamic_layout,container,false);
        //return super.onCreateView(inflater, container, savedInstanceState);
    }


}
