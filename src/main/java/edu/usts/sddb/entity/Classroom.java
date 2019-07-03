package edu.usts.sddb.entity;

public class Classroom {
    private String cl_id;
    private String cl_name;
    private int cl_person_num;
    private String cl_major_id;
    private int cl_edu_len;
    private int cl_grade;
    private String cl_instructor_name;
    private String cl_instructor_phone;
    private String cl_edu_form;
    private String cl_major;

    public String getCl_id() {
        return cl_id;
    }

    public void setCl_id(String cl_id) {
        this.cl_id = cl_id;
    }

    public String getCl_name() {
        return cl_name;
    }

    public void setCl_name(String cl_name) {
        this.cl_name = cl_name;
    }

    public int getCl_person_num() {
        return cl_person_num;
    }

    public void setCl_person_num(int cl_person_num) {
        this.cl_person_num = cl_person_num;
    }

    public String getCl_major_id() {
        return cl_major_id;
    }

    public void setCl_major_id(String cl_major_id) {
        this.cl_major_id = cl_major_id;
    }

    public int getCl_edu_len() {
        return cl_edu_len;
    }

    public void setCl_edu_len(int cl_edu_len) {
        this.cl_edu_len = cl_edu_len;
    }

    public int getCl_grade() {
        return cl_grade;
    }

    public void setCl_grade(int cl_grade) {
        this.cl_grade = cl_grade;
    }

    public String getCl_instructor_name() {
        return cl_instructor_name;
    }

    public void setCl_instructor_name(String cl_instructor_name) {
        this.cl_instructor_name = cl_instructor_name;
    }

    public String getCl_instructor_phone() {
        return cl_instructor_phone;
    }

    public void setCl_instructor_phone(String cl_instructor_phone) {
        this.cl_instructor_phone = cl_instructor_phone;
    }

    public String getCl_edu_form() {
        return cl_edu_form;
    }

    public void setCl_edu_form(String cl_edu_form) {
        this.cl_edu_form = cl_edu_form;
    }

    public String getCl_major() {
        return cl_major;
    }

    public void setCl_major(String cl_major) {
        this.cl_major = cl_major;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "cl_id='" + cl_id + '\'' +
                ", cl_name='" + cl_name + '\'' +
                ", cl_person_num=" + cl_person_num +
                ", cl_major_id='" + cl_major_id + '\'' +
                ", cl_edu_len=" + cl_edu_len +
                ", cl_grade=" + cl_grade +
                ", cl_instructor_name='" + cl_instructor_name + '\'' +
                ", cl_instructor_phone='" + cl_instructor_phone + '\'' +
                ", cl_edu_form='" + cl_edu_form + '\'' +
                ", cl_major='" + cl_major + '\'' +
                '}';
    }
}
