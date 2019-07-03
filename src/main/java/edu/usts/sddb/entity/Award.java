package edu.usts.sddb.entity;

import java.util.List;

public class Award {

	/**
	 * 获奖名称
	 */
	String name;

	/**
	 * 获奖年份
	 */
	String year;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "PartOfCloud [name=" + name + ", year=" + year + "]";
	}

	
}
