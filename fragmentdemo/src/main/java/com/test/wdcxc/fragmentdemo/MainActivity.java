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
        //实例化自定义的Fagment子类对象
        FragmentDynamic fd=new FragmentDynamic();
        //传递构造参数
        fd.setArguments(new Bundle());
        //获取FragmentManager对象
        FragmentManager fm=getFragmentManager();
        //获取FragmentTransaction对象
        FragmentTransaction ft=fm.beginTransaction();
        //添加Fragment子类对象到Activity中
        ft.add(R.id.main_framelayout, fd, "fragment_dynamic");
        /*//添加动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);*/
        //把当前状态添加到backstack中，后面才可以返回到操作前状态
        ft.addToBackStack(null);
        //提交操作
        ft.commit();

        //FragmentTransaction提交的操作会等到UI thread有空才执行，如果想要立即执行提交的操作，可以用下面的语句
        boolean haspendinginent = fm.executePendingTransactions();

        /**
         *  获取Activity中的Fragment对象
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
