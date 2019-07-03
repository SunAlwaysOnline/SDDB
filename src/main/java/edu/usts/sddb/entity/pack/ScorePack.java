package edu.usts.sddb.entity.pack;

import java.util.List;

public class ScorePack {
	private String year;
	private double credit_gain;
	private double credit_ungain;
	private double rate;
	private List<CourseFail> list;

	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public double getCredit_gain() {
		return credit_gain;
	}

	public void setCredit_gain(double credit_gain) {
		this.credit_gain = credit_gain;
	}

	public double getCredit_ungain() {
		return credit_ungain;
	}

	public void setCredit_ungain(double credit_ungain) {
		this.credit_ungain = credit_ungain;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public List<CourseFail> getList() {
		return list;
	}

	public void setList(List<CourseFail> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "ScorePack [year=" + year + ", credit_gain=" + credit_gain + ", credit_ungain=" + credit_ungain
				+ ", rate=" + rate + ", list=" + list + "]";
	}

	

}
