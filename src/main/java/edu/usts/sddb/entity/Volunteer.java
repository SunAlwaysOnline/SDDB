package edu.usts.sddb.entity;

/**
 * 与t_volunteer表对应
 * 志愿服务实体类
 */
public class Volunteer {
    private String v_student_id;
    private String v_student_name;
    private double v_time;

    public String getV_student_id() {
        return v_student_id;
    }

    public void setV_student_id(String v_student_id) {
        this.v_student_id = v_student_id;
    }

    public String getV_student_name() {
        return v_student_name;
    }

    public void setV_student_name(String v_student_name) {
        this.v_student_name = v_student_name;
    }

    public double getV_time() {
        return v_time;
    }

    public void setV_time(double v_time) {
        this.v_time = v_time;
    }
}
