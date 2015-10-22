package com.tonto.hms.dao.support;

import java.util.List;

/**
 * 
 * @author TontoZhou
 *
 */
public class Page<E> {
    private int pageNum;
    private int pageSize;
    private int startRow;
    private int endRow;
    private long total;
    private int pages;
    private List<E> result;

    public Page(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.startRow = pageNum > 0 ? (pageNum - 1) * pageSize : 0;
        this.endRow = pageNum * pageSize;
    }

    public List<E> getResult() {
        return result;
    }

    public void setResult(List<E> result) {
        this.result = result;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
        pages= (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
        if(pages<pageNum)
        {
        	pageNum=pages;  
        	startRow = pageNum > 0 ? (pageNum - 1) * pageSize : 0;
        	endRow = pageNum * pageSize;
        }
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", startRow=" + startRow +
                ", endRow=" + endRow +
                ", total=" + total +
                ", pages=" + pages +
                '}';
    }

	public int getPages() {
		return pages;
	}
}