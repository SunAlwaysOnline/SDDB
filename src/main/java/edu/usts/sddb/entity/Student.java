package edu.usts.sddb.entity;

public class Student {
    private String st_id;
    private String st_name;
    private String st_sex;
    private String st_major;
    private String st_class;
    private String st_grade;

    public String getSt_id() {
        return st_id;
    }

    public void setSt_id(String st_id) {
        this.st_id = st_id;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public String getSt_sex() {
        return st_sex;
    }

    public void setSt_sex(String st_sex) {
        this.st_sex = st_sex;
    }

    public String getSt_major() {
        return st_major;
    }

    public void setSt_major(String st_major) {
        this.st_major = st_major;
    }

    public String getSt_class() {
        return st_class;
    }

    public void setSt_class(String st_class) {
        this.st_class = st_class;
    }

    public String getSt_grade() {
        return st_grade;
    }

    public void setSt_grade(String st_grade) {
        this.st_grade = st_grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "st_id='" + st_id + '\'' +
                ", st_name='" + st_name + '\'' +
                ", st_sex='" + st_sex + '\'' +
                ", st_major='" + st_major + '\'' +
                ", st_class='" + st_class + '\'' +
                ", st_grade='" + st_grade + '\'' +
                '}';
    }
}
