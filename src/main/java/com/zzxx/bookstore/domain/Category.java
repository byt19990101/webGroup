package com.zzxx.bookstore.domain;

import java.io.Serializable;

/**
 * 分类实体类
 */
public class Category implements Serializable {

    private int cid; // 分类id
    private String cname; // 分类名称
    private String tname; // 总分类名称

    public Category() {
    }

    public Category(int cid, String cname, String tname) {
        this.cid = cid;
        this.cname = cname;
        this.tname = tname;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", tname='" + tname + '\'' +
                '}';
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}
