package com.test.wdcxc.fragmentdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentStatic.OnTouchListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 动态添加Fragment
         */
        //实例化自定义的Fragment子类
        FragmentDynamic fd=new FragmentDynamic();
        //传递参数
        fd.setArguments(new Bundle());
        //获得FragmentManager
        FragmentManager fm=getFragmentManager();
        //获得FragmentTransaction
        FragmentTransaction ft=fm.beginTransaction();
        //添加Fragment子类对象
        ft.add(R.id.main_framelayout, fd, "fragment_dynamic");
        /*//添加动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);*/
        //push进backstack,以便可以回退到前一个状态
        ft.addToBackStack(null);
        //最后提交
        ft.commit();

        //使FragmentTransaction中commit的操作立即执行，必须为Ui线程才可执行此操作
        boolean haspendinginent = fm.executePendingTransactions();

        /**
         * 获得Activity中的Fragment
         */
        FragmentStatic fs= (FragmentStatic) fm.findFragmentById(R.id.fragment_static);
        fd= (FragmentDynamic) fm.findFragmentByTag("fragment_dynamic");


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
    public void onTouchListener() {
        Toast.makeText(this,"you have touched the static generate fragment", Toast.LENGTH_SHORT).show();
    }
}
