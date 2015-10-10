package com.ishare.mall.crawler.model.jqgrid;

import org.springframework.data.domain.Page;

import java.util.List;

public class JQGridPageResponse<T> {
    private long total;
    private int page;
    private long records;
    private List<T> rows;

    public JQGridPageResponse(Page<T> _page) {
        this.total = _page.getTotalPages();
        this.page = _page.getNumber() + 1;
        this.records = _page.getTotalElements();
        this.rows = _page.getContent();
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
