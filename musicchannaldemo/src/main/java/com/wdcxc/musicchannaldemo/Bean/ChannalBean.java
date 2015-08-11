package com.wdcxc.musicchannaldemo.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wdcxc on 2015/8/4.
 */
public class ChannalBean implements Parcelable{
    private String chanid;
    private String chanhls;
    private String imgurl;
    private String mp3url;
    private String chantitle;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(chanid);
        dest.writeString(chanhls);
        dest.writeString(imgurl);
        dest.writeString(mp3url);
        dest.writeString(chantitle);
    }

    public static final Parcelable.Creator<ChannalBean> CREATOR=new Creator<ChannalBean>() {
        @Override
        public ChannalBean createFromParcel(Parcel source) {
            return new ChannalBean(source);
        }

        @Override
        public ChannalBean[] newArray(int size) {
            return new ChannalBean[size];
        }
    };

    public ChannalBean(Parcel source)
    {
        this.chanid=source.readString();
        this.chanhls=source.readString();
        this.imgurl=source.readString();
        this.mp3url=source.readString();
        this.chantitle=source.readString();
    }


    public ChannalBean(String chanid, String chanhls, String imgurl, String mp3url, String chantitle) {
        this.chanid = chanid;
        this.chanhls = chanhls;
        this.imgurl = imgurl;
        this.mp3url = mp3url;
        this.chantitle = chantitle;
    }

    public String getChantitle() {
        return chantitle;
    }

    public void setChantitle(String chantitle) {
        this.chantitle = chantitle;
    }

    public String getChanid() {
        return chanid;
    }

    public void setChanid(String chanid) {
        this.chanid = chanid;
    }

    public String getChanhls() {
        return chanhls;
    }

    public void setChanhls(String chanhls) {
        this.chanhls = chanhls;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getMp3url() {
        return mp3url;
    }

    public void setMp3url(String mp3url) {
        this.mp3url = mp3url;
    }


}
