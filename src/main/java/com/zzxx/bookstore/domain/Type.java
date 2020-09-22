package com.zzxx.bookstore.domain;

public class Type {
    private int tid;
    private String tname; // 总分类名称

    public Type(){

    }
    public Type(int tid, String tname) {
        this.tid = tid;
        this.tname = tname;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}
