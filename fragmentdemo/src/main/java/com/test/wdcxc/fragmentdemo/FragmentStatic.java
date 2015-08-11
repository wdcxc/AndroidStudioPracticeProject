package com.test.wdcxc.fragmentdemo;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wdcxc on 15/8/11.
 */
public class FragmentStatic extends Fragment {
    private OnTouchListener ontouchlistener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ontouchlistener= (OnTouchListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ontouchlistener.onTouchListener();
        View v = inflater.inflate(R.layout.fragment_static_layout, container, false);
        return v;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public interface OnTouchListener
    {
        public void onTouchListener();
    }

}
