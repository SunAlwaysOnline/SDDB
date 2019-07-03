package edu.usts.sddb.entity.pack;

public class CourseFail {
	private String course_name;
	private double credit;
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	@Override
	public String toString() {
		return "CourseFail [course_name=" + course_name + ", credit=" + credit + "]";
	}
	
	
}
