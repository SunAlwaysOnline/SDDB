package edu.usts.sddb.entity.pack;

public class ClassUngain {
	private String className;
	private int stuNum;
	private int ungainStuNum;
	private double ungainRate;
	
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getStuNum() {
		return stuNum;
	}
	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}
	public int getUngainStuNum() {
		return ungainStuNum;
	}
	public void setUngainStuNum(int ungainStuNum) {
		this.ungainStuNum = ungainStuNum;
	}
	public double getUngainRate() {
		return ungainRate;
	}
	public void setUngainRate(double ungainRate) {
		this.ungainRate = ungainRate;
	}
	@Override
	public String toString() {
		return "ClassUngain [className=" + className + ", stuNum=" + stuNum + ", ungainStuNum=" + ungainStuNum
				+ ", ungainRate=" + ungainRate + "]";
	}
	
	
}
