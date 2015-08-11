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
         * ��̬���Fragment
         */
        //ʵ�����Զ����Fragment����
        FragmentDynamic fd=new FragmentDynamic();
        //���ݲ���
        fd.setArguments(new Bundle());
        //���FragmentManager
        FragmentManager fm=getFragmentManager();
        //���FragmentTransaction
        FragmentTransaction ft=fm.beginTransaction();
        //���Fragment�������
        ft.add(R.id.main_framelayout, fd, "fragment_dynamic");
        /*//��Ӷ���
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);*/
        //push��backstack,�Ա���Ի��˵�ǰһ��״̬
        ft.addToBackStack(null);
        //����ύ
        ft.commit();

        //ʹFragmentTransaction��commit�Ĳ�������ִ�У�����ΪUi�̲߳ſ�ִ�д˲���
        boolean haspendinginent = fm.executePendingTransactions();

        /**
         * ���Activity�е�Fragment
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
