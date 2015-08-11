package com.wdcxc.musicchannaldemo.presenter;

import android.app.ActivityManager;
import android.content.Context;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.nfc.NfcEvent;
import android.os.AsyncTask;
import android.support.v7.internal.app.WindowDecorActionBar;
import android.text.Html;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wdcxc.musicchannaldemo.Bean.ChannalBean;
import com.wdcxc.musicchannaldemo.R;
import com.wdcxc.musicchannaldemo.utils.JsonParser;
import com.wdcxc.musicchannaldemo.utils.NetConn;

import java.io.InputStream;
import java.io.PipedInputStream;
import java.util.ArrayList;

/**
 * Created by wdcxc on 2015/8/5.
 */
public class ChannalBaseAdapter extends BaseAdapter {

    private ArrayList<ChannalBean> al_channal;
    private Context context;
    private LruCache<String,Bitmap> imgcache=null;

    public ChannalBaseAdapter(ArrayList<ChannalBean> al,Context context)
    {
        this.al_channal=al;
        this.context=context;
        ActivityManager am= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        int cachesize=1024*1024/4*am.getMemoryClass();
        imgcache=new LruCache<>(cachesize);
    }


    @Override
    public int getCount() {
        return al_channal.size();
    }

    @Override
    public Object getItem(int position) {
        return al_channal.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.channal_item_layout,null);
            vh=new ViewHolder();
            vh.iv= (ImageView) convertView.findViewById(R.id.imgv_channal);
            vh.tv=(TextView)convertView.findViewById(R.id.tv_channal);
            convertView.setTag(vh);
        }
        else
        {
            vh= (ViewHolder) convertView.getTag();
        }

        String imgurl=al_channal.get(position).getImgurl();
        if(null==vh.iv.getTag()||!imgurl.equals(vh.iv.getTag()))
        {
            if(null!=imgcache.get(imgurl)) {
                vh.iv.setImageBitmap(imgcache.get(imgurl));
                vh.iv.setTag(imgurl);
            }
            else {
                vh.iv.setImageResource(R.mipmap.ic_launcher);
                vh.iv.setTag(imgurl);
                new LoadImage(vh.iv, imgurl).execute(imgurl);
            }
        }

        vh.tv.setText( al_channal.get(position).getChantitle());
        return convertView;
    }

    private class LoadImage extends AsyncTask<String,Void,Bitmap>{

        private ImageView iv;
        private String imgurl;

        public LoadImage(ImageView iv,String imgurl)
        {
            this.iv=iv;
            this.imgurl=imgurl;
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            NetConn nc=new NetConn(params[0]);
            InputStream is=nc.getInputStream();
            Bitmap b=BitmapFactory.decodeStream(is);
            nc.closeNetConn();
            return b;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (null != bitmap) {
                imgcache.put(imgurl,bitmap);
                if (null == iv.getTag() || imgurl.equals(iv.getTag())) {
                    iv.setImageBitmap(bitmap);
                }
            }
        }
    }

    private class ViewHolder
    {
        TextView tv;
        ImageView iv;
    }

}
