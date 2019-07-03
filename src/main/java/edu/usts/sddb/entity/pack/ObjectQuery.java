package edu.usts.sddb.entity.pack;


import java.util.List;

public class ObjectQuery {
    // 当前所处页数
    private int page;
    // 总共页数
    private int total;
    // 总记录数
    private int records;
    // 模型对象
    private List<Object> rows;

    public ObjectQuery() {
    }

    public ObjectQuery(int page, int total, int records, List<Object> rows) {
        this.page = page;
        this.total = total;
        this.records = records;
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "ObjectQuery{" +
                "page=" + page +
                ", total=" + total +
                ", records=" + records +
                ", rows=" + rows +
                '}';
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public List<Object> getRows() {
        return rows;
    }

    public void setRows(List<Object> rows) {
        this.rows = rows;
    }
}
