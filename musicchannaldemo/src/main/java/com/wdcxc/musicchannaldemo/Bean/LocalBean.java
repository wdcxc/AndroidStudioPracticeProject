package com.wdcxc.musicchannaldemo.Bean;

/**
 * Created by wdcxc on 2015/8/4.
 */
public class LocalBean {

    private String localid;
    private String localname;

    public LocalBean(String localid,String localname)
    {
        this.localid=localid;
        this.localname=localname;
    }

    public String getLocalid() {
        return localid;
    }

    public void setLocalid(String localid) {
        this.localid = localid;
    }

    public String getLocalname() {
        return localname;
    }

    public void setLocalname(String localname) {
        this.localname = localname;
    }
}
