package com.zzxx.bookstore.util;

import java.util.List;

public class PageBean<T> {
    private List<T> list;
    private Integer currentPage;
    private Integer totalCount;
    private Integer totalPage;
    private Integer pageSize;

    public PageBean(List<T> list, Integer currentPage, Integer totalCount, Integer totalPage, Integer pageSize) {
        this.list = list;
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
    }

    public PageBean() {
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
