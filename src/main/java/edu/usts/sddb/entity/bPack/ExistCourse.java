package edu.usts.sddb.entity.bPack;

/**
 * 已考过的课程的位置情况
 */
public class ExistCourse {
    private String existCourse;
    private double gpa;
    private double avgGpa;
    private String existPosition;

    public String getExistCourse() {
        return existCourse;
    }

    public void setExistCourse(String existCourse) {
        this.existCourse = existCourse;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getAvgGpa() {
        return avgGpa;
    }

    public void setAvgGpa(double avgGpa) {
        this.avgGpa = avgGpa;
    }

    public String getExistPosition() {
        return existPosition;
    }

    public void setExistPosition(String existPosition) {
        this.existPosition = existPosition;
    }

    @Override
    public String toString() {
        return "ExistCourse{" +
                "existCourse='" + existCourse + '\'' +
                ", gpa=" + gpa +
                ", avgGpa=" + avgGpa +
                ", existPostion='" + existPosition + '\'' +
                '}';
    }
}