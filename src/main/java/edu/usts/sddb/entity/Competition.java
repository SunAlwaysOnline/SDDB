package edu.usts.sddb.entity;

/**
 * 与数据库t_competitipn对应
 *
 * @author 张琳
 */
public class Competition {

    /**
     * 自增主键
     */
    private Integer co_id;
    /**
     * 学号
     */
    private String co_student_id;
    /**
     * 姓名
     */
    private String co_student_name;
    /**
     * 专业
     */
    private String co_major_name;
    /**
     * 竞赛名称
     */
    private String co_name;
    /**
     * 级别
     */
    private String co_level;
    /**
     * 奖项
     */
    private String co_awards;
    /**
     * 主办单位
     */
    private String co_sponsor;
    /**
     * 获奖日期
     */
    private String co_time_award;
    /**
     * 指导教师
     */
    private String co_teachers;

    public Integer getCo_id() {
        return co_id;
    }

    public void setCo_id(Integer co_id) {
        this.co_id = co_id;
    }

    public String getCo_student_id() {
        return co_student_id;
    }

    public void setCo_student_id(String co_student_id) {
        this.co_student_id = co_student_id;
    }

    public String getCo_student_name() {
        return co_student_name;
    }

    public void setCo_student_name(String co_student_name) {
        this.co_student_name = co_student_name;
    }

    public String getCo_major_name() {
        return co_major_name;
    }

    public void setCo_major_name(String co_major_name) {
        this.co_major_name = co_major_name;
    }

    public String getCo_name() {
        return co_name;
    }

    public void setCo_name(String co_name) {
        this.co_name = co_name;
    }

    public String getCo_level() {
        return co_level;
    }

    public void setCo_level(String co_level) {
        this.co_level = co_level;
    }

    public String getCo_awards() {
        return co_awards;
    }

    public void setCo_awards(String co_awards) {
        this.co_awards = co_awards;
    }

    public String getCo_sponsor() {
        return co_sponsor;
    }

    public void setCo_sponsor(String co_sponsor) {
        this.co_sponsor = co_sponsor;
    }

    public String getCo_time_award() {
        return co_time_award;
    }

    public void setCo_time_award(String co_time_award) {
        this.co_time_award = co_time_award;
    }

    public String getCo_teachers() {
        return co_teachers;
    }

    public void setCo_teachers(String co_teachers) {
        this.co_teachers = co_teachers;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "co_id=" + co_id +
                ", co_student_id='" + co_student_id + '\'' +
                ", co_student_name='" + co_student_name + '\'' +
                ", co_major_name='" + co_major_name + '\'' +
                ", co_name='" + co_name + '\'' +
                ", co_level='" + co_level + '\'' +
                ", co_awards='" + co_awards + '\'' +
                ", co_sponsor='" + co_sponsor + '\'' +
                ", co_time_award='" + co_time_award + '\'' +
                ", co_teachers='" + co_teachers + '\'' +
                '}';
    }
}
