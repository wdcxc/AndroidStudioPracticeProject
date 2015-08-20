package com.demo.wdcxc.mytopbardemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by wdcxc on 2015/8/20.
 */
public class MyTopBar extends RelativeLayout {

    public TextView tv_title;
    public Button btu_left;
    public Button btu_right;

    public LayoutParams tv_par,lbtu_par,rbtu_par;

    public OnMyTopBarListener onMyTopBarListener;

    public interface OnMyTopBarListener{
        public void onLeftClick();
        public void onRightClick();
    }

    public void setOnMyTopBarListener(OnMyTopBarListener onMyTopBarListener){
        this.onMyTopBarListener=onMyTopBarListener;
    }

    public MyTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.mytopbar);

        initTitle(typedArray,context);
        initLeftButton(typedArray,context);
        initRightButton(typedArray,context);

        setBackgroundColor(Color.BLUE);

        typedArray.recycle();

    }

    private void initTitle(TypedArray typedArray,Context context)
    {
        String tv_text = typedArray.getString(R.styleable.mytopbar_text);
        float tv_textsize = typedArray.getDimension(R.styleable.mytopbar_textsize, 1f);
        int tv_color = typedArray.getColor(R.styleable.mytopbar_textcolor, Color.RED);

        tv_title=new TextView(context);
        tv_title.setText(tv_text);
        tv_title.setTextColor(tv_color);
        tv_title.setTextSize(tv_textsize);
        tv_title.setGravity(Gravity.CENTER);

        tv_par=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tv_par.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(tv_title, tv_par);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initLeftButton(TypedArray typedArray, Context context)
    {
        String lbtu_text = typedArray.getString(R.styleable.mytopbar_lefttext);
        Drawable lbtu_bg = typedArray.getDrawable(R.styleable.mytopbar_leftbackground);
        int lbtu_textcolor = typedArray.getColor(R.styleable.mytopbar_lefttextcolor, Color.GREEN);

        btu_left=new Button(context);
        btu_left.setText(lbtu_text);
        btu_left.setTextColor(lbtu_textcolor);
        btu_left.setBackground(lbtu_bg);

        lbtu_par=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lbtu_par.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(btu_left, lbtu_par);

        btu_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyTopBarListener.onLeftClick();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initRightButton(TypedArray typedArray, Context context)
    {
        String rbtu_text = typedArray.getString(R.styleable.mytopbar_righttext);
        Drawable rbtu_bg = typedArray.getDrawable(R.styleable.mytopbar_rightbackground);
        int rbtu_textcolor = typedArray.getColor(R.styleable.mytopbar_righttextcolor, Color.GREEN);

        btu_right=new Button(context);
        btu_right.setText(rbtu_text);
        btu_right.setTextColor(rbtu_textcolor);
        btu_right.setBackground(rbtu_bg);

        rbtu_par=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rbtu_par.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(btu_right, rbtu_par);

        btu_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyTopBarListener.onRightClick();
            }
        });
    }

}
