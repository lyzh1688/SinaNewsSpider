package com.octopus.crawler;

import com.lz.octopus.common.result.IResultDetail;

/**
 * Created by kfzx-liuyz1 on 2016/11/11.
 */
public class SinaNewsResultDetail implements IResultDetail {
    private String source;
    private String time;
    private String title;
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "SinaNewsResultDetail [title=" + this.title + ", source=" + this.source + ", time=" + this.time
                + ", href=" + this.href + "]";
    }
}
