package com.wdcxc.musicchannaldemo.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wdcxc.musicchannaldemo.Bean.ChannalBean;
import com.wdcxc.musicchannaldemo.R;
import com.wdcxc.musicchannaldemo.presenter.ChannalBaseAdapter;
import com.wdcxc.musicchannaldemo.presenter.Presenter_Channal;

import java.util.ArrayList;

/**
 * Created by wdcxc on 2015/8/4.
 */
public class ChannalActivity extends Activity implements IView_Channal {

    private ListView lv;
    private String position;
    private ProgressDialog pd;
    private Presenter_Channal pc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channal_listview);

        lv = (ListView) findViewById(R.id.lv_channal);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("bundle");
        position = b.getString("position");

        pc = new Presenter_Channal(this);
        pc.setContent(position);
    }

    @Override
    public void prepare() {
        pd = new ProgressDialog(this);
        pd.setTitle("请稍候");
        pd.setMessage("正在加载数据。。。");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    public void show(final ArrayList<ChannalBean> al) {

        if (null == al) {
            Toast.makeText(this,"对不起,数据加载失败",Toast.LENGTH_SHORT).show();
        } else {
            ChannalBaseAdapter cba = new ChannalBaseAdapter(al, this);
            lv.setAdapter(cba);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    pc.playMp3(ChannalActivity.this, al.get(position));
                }
            });
        }
        pd.dismiss();
    }
}
