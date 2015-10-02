package com.ishare.mall.crawler.model.jqgrid;

public class JQGridPageRequest {

    private int rows;
    private int page;
    //private boolean _search;
    private long nd;
    private String sord = "asc";//排序：asc，desc
    private String sidx = "id";//排序字段

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getNd() {
        return nd;
    }

    public void setNd(long nd) {
        this.nd = nd;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }
}
