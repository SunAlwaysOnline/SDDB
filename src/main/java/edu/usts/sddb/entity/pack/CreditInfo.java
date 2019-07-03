package edu.usts.sddb.entity.pack;

import java.util.List;

public class CreditInfo {
	private String stu_name;
	private double rate;	
	private List<YearCredit> yearCredits;
	
	
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public List<YearCredit> getYearCredits() {
		return yearCredits;
	}
	public void setYearCredits(List<YearCredit> yearCredits) {
		this.yearCredits = yearCredits;
	}
	@Override
	public String toString() {
		return "CreditInfo [stu_name=" + stu_name + ", rate=" + rate + ", yearCredits=" + yearCredits + "]";
	}
	
	
	
}
