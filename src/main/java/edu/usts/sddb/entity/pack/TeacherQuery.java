package edu.usts.sddb.entity.pack;

import java.util.List;

import edu.usts.sddb.entity.Student;
import edu.usts.sddb.entity.Teacher;

public class TeacherQuery {
	// 当前所处页数
	private int page;
	// 总共页数
	private int total;
	// 总记录数
	private int records;
	// 模型对象
	private List<Teacher> rows;

	public TeacherQuery(int page, int total, int records, List<Teacher> rows) {
		super();
		this.page = page;
		this.total = total;
		this.records = records;
		this.rows = rows;
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

	public List<Teacher> getRows() {
		return rows;
	}

	public void setRows(List<Teacher> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "TeacherQuery [page=" + page + ", total=" + total + ", records=" + records + ", rows=" + rows + "]";
	}

}
