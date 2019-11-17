package com.sc.act.api.commons.web.base;

import java.util.List;


public class PageResponse<T> {

    //总记录数
    private long total;
    //结果集
    private List<T> list;

    public PageResponse() {
        super();
    }

    public PageResponse(List<T> list) {
        this.list = list;
    }

    public PageResponse(long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}