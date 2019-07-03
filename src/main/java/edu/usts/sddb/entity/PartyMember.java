package edu.usts.sddb.entity;

/**
 * 与数据库t_party_member对应
 */
public class PartyMember {

    /**
     * 自增主键
     */
    private Integer pa_id;
    /**
     * 学生学号(数据库中允许为空)
     */
    private String pa_student_id;
    /**
     * 学生姓名
     */
    private String pa_student_name;
    /**
     * 支部名称
     */
    private String pa_branch_name;

    /**
     * 学生性别
     */
    private String pa_student_sex;
    /**
     * 入党时间
     */
    private String pa_time;
    /**
     * 状态
     */
    private String pa_state;
    /**
     * 年级
     */
    private String pa_grade;
    /**
     * 班级
     */
    private String pa_class_name;
    /**
     * 支部书记
     */
    private String pa_branch_secretary;

    public Integer getPa_id() {
        return pa_id;
    }

    public void setPa_id(Integer pa_id) {
        this.pa_id = pa_id;
    }

    public String getPa_student_id() {
        return pa_student_id;
    }

    public void setPa_student_id(String pa_student_id) {
        this.pa_student_id = pa_student_id;
    }

    public String getPa_student_name() {
        return pa_student_name;
    }

    public void setPa_student_name(String pa_student_name) {
        this.pa_student_name = pa_student_name;
    }

    public String getPa_branch_name() {
        return pa_branch_name;
    }

    public void setPa_branch_name(String pa_branch_name) {
        this.pa_branch_name = pa_branch_name;
    }

    public String getPa_student_sex() {
        return pa_student_sex;
    }

    public void setPa_student_sex(String pa_student_sex) {
        this.pa_student_sex = pa_student_sex;
    }

    public String getPa_time() {
        return pa_time;
    }

    public void setPa_time(String pa_time) {
        this.pa_time = pa_time;
    }

    public String getPa_state() {
        return pa_state;
    }

    public void setPa_state(String pa_state) {
        this.pa_state = pa_state;
    }

    public String getPa_grade() {
        return pa_grade;
    }

    public void setPa_grade(String pa_grade) {
        this.pa_grade = pa_grade;
    }

    public String getPa_class_name() {
        return pa_class_name;
    }

    public void setPa_class_name(String pa_class_name) {
        this.pa_class_name = pa_class_name;
    }

    public String getPa_branch_secretary() {
        return pa_branch_secretary;
    }

    public void setPa_branch_secretary(String pa_branch_secretary) {
        this.pa_branch_secretary = pa_branch_secretary;
    }

    @Override
    public String toString() {
        return "PartyMember{" +
                "pa_id=" + pa_id +
                ", pa_student_id='" + pa_student_id + '\'' +
                ", pa_student_name='" + pa_student_name + '\'' +
                ", pa_branch_name='" + pa_branch_name + '\'' +
                ", pa_student_sex='" + pa_student_sex + '\'' +
                ", pa_time='" + pa_time + '\'' +
                ", pa_state='" + pa_state + '\'' +
                ", pa_grate='" + pa_grade + '\'' +
                ", pa_class_name='" + pa_class_name + '\'' +
                ", pa_branch_secretary='" + pa_branch_secretary + '\'' +
                '}';
    }
}
