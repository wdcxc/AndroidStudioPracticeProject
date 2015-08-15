package com.demo.wdcxc.refreshablelistviewdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by wdcxc on 2015/8/15.
 */
public class RefreshableListView extends ListView implements AbsListView.OnScrollListener{

    private int lastvisibleitem;
    private int totalItemCount;
    private boolean isloading;
    private View footer=null;
    private OnListViewUpdate onlistviewupdate;

    public RefreshableListView(Context context) {
        super(context);
        initHeader(context);
        initFooter(context);
    }

    public RefreshableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeader(context);
        initFooter(context);
    }

    public RefreshableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHeader(context);
        initFooter(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RefreshableListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initHeader(context);
        initFooter(context);
    }

    //添加ListView头部
    private void initHeader(Context context)
    {
       LayoutInflater li=LayoutInflater.from(context);
        View header=li.inflate(R.layout.refreshablelistview_header_layout,null);
        this.addHeaderView(header);
    }

    //添加ListView尾部
    private void initFooter(Context context) {
        LayoutInflater li=LayoutInflater.from(context);
        footer=li.inflate(R.layout.refreshablelistview_footer_layout,null);
        this.addFooterView(footer);
        isloading=false;
        this.setOnScrollListener(this);
        footer.setVisibility(GONE);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(SCROLL_STATE_IDLE==scrollState&&totalItemCount==lastvisibleitem)
        {
            if(!isloading)
            {
                isloading=true;
                footer.setVisibility(VISIBLE);
                onlistviewupdate.updateData();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastvisibleitem=firstVisibleItem+visibleItemCount;
        this.totalItemCount=totalItemCount;
    }

    //将Activity对象传递进来
    public void setInterface(OnListViewUpdate olvu)
    {
        this.onlistviewupdate=olvu;
    }

    //用于通知Activity更新数据的接口
    public interface OnListViewUpdate{
        public void updateData();
    }

    //数据更新后隐藏footer
    public void hideFooter()
    {
        footer.setVisibility(GONE);
        isloading=false;
    }
}
