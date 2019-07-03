package edu.usts.sddb.entity.pack;

import java.util.List;

import edu.usts.sddb.entity.Student;

/**
 * @author sun 每一个需要分页显示的对象,都需要转化成这样的格式,变量名page,total,records,rows不可变
 */
public class StudentQuery {
	// 当前所处页数
	private int page;
	// 总共页数
	private int total;
	// 总记录数
	private int records;
	// 模型对象
	private List<Student> rows;

	public StudentQuery(int page, int total, int records, List<Student> rows) {
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

	public List<Student> getRows() {
		return rows;
	}

	public void setRows(List<Student> rows) {
		this.rows = rows;
	}

}