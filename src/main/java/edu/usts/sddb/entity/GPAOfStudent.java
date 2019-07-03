package edu.usts.sddb.entity;

/**
 * @author 戚春阳 个人平均绩点类
 */
public class GPAOfStudent {
	/**
	 * 年度
	 */
	private String year;
	/**
	 * 此年度的绩点
	 */
	private double value;

	public GPAOfStudent(String year, double value) {
		super();
		this.year = year;
		this.value = value;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "GPAOfStudent [year=" + year + ", value=" + value + "]";
	}

}
