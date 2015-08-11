package com.wdcxc.musicchannaldemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.wdcxc.musicchannaldemo.Bean.LocalBean;
import com.wdcxc.musicchannaldemo.presenter.Presenter_Local;
import com.wdcxc.musicchannaldemo.view.IView_Local;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView_Local{

    private ListView lv;
    private SimpleAdapter sa;
    private  ArrayList<Map<String,String>> alm_local;
    private ProgressDialog pd;
    public Presenter_Local pl=new Presenter_Local(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_listview);
        lv=(ListView)findViewById(R.id.lv_local);
        Log.i("mtag","main_act:oncreate");
        pl.setContent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void prepare() {
        Log.i("mtag","main_act:start");
        pd=new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setTitle("请稍后");
        pd.setMessage("正在加载数据。。。");
        pd.setCancelable(false);
        pd.show();
        Log.i("mtag", "main_act:start@pd:"+pd.toString());
    }

    @Override
    public void show(final ArrayList<Map<String,String>> alm_local) {
        Log.i("mtag","main_act:show");
        if(null==alm_local)
            Toast.makeText(this,"对不起,数据加载失败",Toast.LENGTH_SHORT).show();
        else {
            sa = new SimpleAdapter(this, alm_local, R.layout.local_item_layout,
                    new String[]{"local_id", "local_name"}, new int[]{R.id.li_tv_id, R.id.li_tv_name});
            lv.setAdapter(sa);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Map<String, String> m = alm_local.get(position);
                    String localid = m.get("local_id");
                    pl.jumpToItem(localid);
                }
            });
        }
        pd.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //pl.release(this);
    }
}
