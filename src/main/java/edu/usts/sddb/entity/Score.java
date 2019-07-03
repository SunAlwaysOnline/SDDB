package edu.usts.sddb.entity;

/**
 * 与数据库t_score对应，学生成绩表
 *
 * @author 张琳
 */
public class Score {

    /**
     * 自增序号，但非主键
     */
    private Integer sc_id;

    /**
     * 学号
     */
    private String sc_student_id;
    /**
     * 姓名
     */
    private String sc_student_name;
    /**
     * 课程名称
     */
    private String sc_course_name;
    /**
     * 课程学分
     */
    private double sc_course_credit;
    /**
     * 课程性质
     */
    private String sc_course_nature;
    /**
     * 总评成绩
     */
    private String sc_score;
    /**
     * 备注
     */
    private String sc_remark;
    /**
     * 绩点
     */
    private Double sc_gpa;
    /**
     * 补考成绩
     */
    private String sc_make_up_score;
    /**
     * 重修成绩
     */
    private String sc_rebuild_score;
    /**
     * 班级
     */
    private String sc_class_name;
    /**
     * 课程归属
     */
    private String sc_course_belong;
    /**
     * 重修标记
     */
    private Integer sc_rebuild_mark;
    /**
     * 选课课号
     */
    private String sc_select_course_id;
    /**
     * 学年
     */
    private String sc_edu_year;
    /**
     * 学期
     */
    private String sc_edu_term;

    public Integer getSc_id() {
        return sc_id;
    }

    public void setSc_id(Integer sc_id) {
        this.sc_id = sc_id;
    }

    public String getSc_student_id() {
        return sc_student_id;
    }

    public void setSc_student_id(String sc_student_id) {
        this.sc_student_id = sc_student_id;
    }

    public String getSc_student_name() {
        return sc_student_name;
    }

    public void setSc_student_name(String sc_student_name) {
        this.sc_student_name = sc_student_name;
    }

    public String getSc_course_name() {
        return sc_course_name;
    }

    public void setSc_course_name(String sc_course_name) {
        this.sc_course_name = sc_course_name;
    }

    public double getSc_course_credit() {
        return sc_course_credit;
    }

    public void setSc_course_credit(double sc_course_credit) {
        this.sc_course_credit = sc_course_credit;
    }

    public String getSc_course_nature() {
        return sc_course_nature;
    }

    public void setSc_course_nature(String sc_course_nature) {
        this.sc_course_nature = sc_course_nature;
    }

    public String getSc_score() {
        return sc_score;
    }

    public void setSc_score(String sc_score) {
        this.sc_score = sc_score;
    }

    public String getSc_remark() {
        return sc_remark;
    }

    public void setSc_remark(String sc_remark) {
        this.sc_remark = sc_remark;
    }

    public Double getSc_gpa() {
        return sc_gpa;
    }

    public void setSc_gpa(Double sc_gpa) {
        this.sc_gpa = sc_gpa;
    }

    public String getSc_make_up_score() {
        return sc_make_up_score;
    }

    public void setSc_make_up_score(String sc_make_up_score) {
        this.sc_make_up_score = sc_make_up_score;
    }

    public String getSc_rebuild_score() {
        return sc_rebuild_score;
    }

    public void setSc_rebuild_score(String sc_rebuild_score) {
        this.sc_rebuild_score = sc_rebuild_score;
    }

    public String getSc_class_name() {
        return sc_class_name;
    }

    public void setSc_class_name(String sc_class_name) {
        this.sc_class_name = sc_class_name;
    }

    public String getSc_course_belong() {
        return sc_course_belong;
    }

    public void setSc_course_belong(String sc_course_belong) {
        this.sc_course_belong = sc_course_belong;
    }

    public Integer getSc_rebuild_mark() {
        return sc_rebuild_mark;
    }

    public void setSc_rebuild_mark(Integer sc_rebuild_mark) {
        this.sc_rebuild_mark = sc_rebuild_mark;
    }

    public String getSc_select_course_id() {
        return sc_select_course_id;
    }

    public void setSc_select_course_id(String sc_select_course_id) {
        this.sc_select_course_id = sc_select_course_id;
    }

    public String getSc_edu_year() {
        return sc_edu_year;
    }

    public void setSc_edu_year(String sc_edu_year) {
        this.sc_edu_year = sc_edu_year;
    }

    public String getSc_edu_term() {
        return sc_edu_term;
    }

    public void setSc_edu_term(String sc_edu_term) {
        this.sc_edu_term = sc_edu_term;
    }

    @Override
    public String toString() {
        return "Score{" +
                "sc_id=" + sc_id +
                ", sc_student_id='" + sc_student_id + '\'' +
                ", sc_student_name='" + sc_student_name + '\'' +
                ", sc_course_name='" + sc_course_name + '\'' +
                ", sc_course_credit=" + sc_course_credit +
                ", sc_course_nature='" + sc_course_nature + '\'' +
                ", sc_score='" + sc_score + '\'' +
                ", sc_remark='" + sc_remark + '\'' +
                ", sc_gpa=" + sc_gpa +
                ", sc_make_up_score='" + sc_make_up_score + '\'' +
                ", sc_rebuild_score='" + sc_rebuild_score + '\'' +
                ", sc_class_name='" + sc_class_name + '\'' +
                ", sc_course_belong='" + sc_course_belong + '\'' +
                ", sc_rebuild_mark=" + sc_rebuild_mark +
                ", sc_select_course_id='" + sc_select_course_id + '\'' +
                ", sc_edu_year='" + sc_edu_year + '\'' +
                ", sc_edu_term='" + sc_edu_term + '\'' +
                '}';
    }
}
