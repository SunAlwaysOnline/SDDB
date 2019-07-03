package edu.usts.sddb.entity.pack;

import java.util.List;

import edu.usts.sddb.entity.Major;

public class MajorQuery {
	// 当前所处页数
	private int page;
	// 总共页数
	private int total;
	// 总记录数
	private int records;
	// 模型对象
	private List<Major> rows;

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

	public List<Major> getRows() {
		return rows;
	}

	public void setRows(List<Major> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "MajorQuery [page=" + page + ", total=" + total + ", records=" + records + ", rows=" + rows + "]";
	}

}
