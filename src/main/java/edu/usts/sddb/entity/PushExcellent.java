package edu.usts.sddb.entity;

/**
 * 与数据库t_push_excellent对应，推优表
 */
public class PushExcellent {

    /**
     * 自增主键
     */
    private Integer pu_id;
    /**
     * 学号
     */
    private String pu_student_id;
    /**
     * 姓名
     */
    private String pu_student_name;
    /**
     * 班级
     */
    private String pu_class_name;
    /**
     * 推优时间
     */
    private String pu_time;
    /**
     * 担任职务
     */
    private String pu_hold_duty;
    /**
     * 奖学金
     */
    private String pu_scholarship;
    /**
     * 是否挂科
     */
    private String pu_is_fail_exam;

    public Integer getPu_id() {
        return pu_id;
    }

    public void setPu_id(Integer pu_id) {
        this.pu_id = pu_id;
    }

    public String getPu_student_id() {
        return pu_student_id;
    }

    public void setPu_student_id(String pu_student_id) {
        this.pu_student_id = pu_student_id;
    }

    public String getPu_student_name() {
        return pu_student_name;
    }

    public void setPu_student_name(String pu_student_name) {
        this.pu_student_name = pu_student_name;
    }

    public String getPu_class_name() {
        return pu_class_name;
    }

    public void setPu_class_name(String pu_class_name) {
        this.pu_class_name = pu_class_name;
    }

    public String getPu_time() {
        return pu_time;
    }

    public void setPu_time(String pu_time) {
        this.pu_time = pu_time;
    }

    public String getPu_hold_duty() {
        return pu_hold_duty;
    }

    public void setPu_hold_duty(String pu_hold_duty) {
        this.pu_hold_duty = pu_hold_duty;
    }

    public String getPu_scholarship() {
        return pu_scholarship;
    }

    public void setPu_scholarship(String pu_scholarship) {
        this.pu_scholarship = pu_scholarship;
    }

    public String getPu_is_fail_exam() {
        return pu_is_fail_exam;
    }

    public void setPu_is_fail_exam(String pu_is_fail_exam) {
        this.pu_is_fail_exam = pu_is_fail_exam;
    }

    @Override
    public String toString() {
        return "PushExcellent{" +
                "pu_id=" + pu_id +
                ", pu_student_id='" + pu_student_id + '\'' +
                ", pu_student_name='" + pu_student_name + '\'' +
                ", pu_class_name='" + pu_class_name + '\'' +
                ", pu_time='" + pu_time + '\'' +
                ", pu_hold_duty='" + pu_hold_duty + '\'' +
                ", pu_scholarship='" + pu_scholarship + '\'' +
                ", pu_is_fail_exam='" + pu_is_fail_exam + '\'' +
                '}';
    }
}
