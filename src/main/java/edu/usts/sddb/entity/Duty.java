package edu.usts.sddb.entity;

/**
 * 职务表
 */
public class Duty {
    /**
     * 自增序号、非主键
     */
    private Integer du_id;

    /**
     * 学号
     */
    private String du_student_id;

    /**
     * 姓名
     */
    private String du_student_name;

    /**
     * 任职时间
     */
    private String du_time;

    /**
     * 职务名称
     */
    private String du_name;

    public Integer getDu_id() {
        return du_id;
    }

    public void setDu_id(Integer du_id) {
        this.du_id = du_id;
    }

    public String getDu_student_id() {
        return du_student_id;
    }

    public void setDu_student_id(String du_student_id) {
        this.du_student_id = du_student_id;
    }

    public String getDu_student_name() {
        return du_student_name;
    }

    public void setDu_student_name(String du_student_name) {
        this.du_student_name = du_student_name;
    }

    public String getDu_time() {
        return du_time;
    }

    public void setDu_time(String du_time) {
        this.du_time = du_time;
    }

    public String getDu_name() {
        return du_name;
    }

    public void setDu_name(String du_name) {
        this.du_name = du_name;
    }
}
