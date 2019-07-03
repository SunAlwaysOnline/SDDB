package edu.usts.sddb.entity;

/**
 * 与t_certificate表对应，证书表
 *
 * @author 张琳
 */
public class Certificate {

    /**
     * 自增主键
     */
    private Integer ce_id;
    /**
     * 学号
     */
    private String ce_student_id;
    /**
     * 姓名
     */
    private String ce_student_name;
    /**
     * 学年
     */
    private String ce_edu_year;
    /**
     * 学期
     */
    private String ce_edu_term;

    /**
     * 等级考试名称
     */
    private String ce_name;
    /**
     * 考试日期
     */
    private String ce_time;
    /**
     * 成绩
     */
    private String ce_score;

    public Integer getCe_id() {
        return ce_id;
    }

    public void setCe_id(Integer ce_id) {
        this.ce_id = ce_id;
    }

    public String getCe_student_id() {
        return ce_student_id;
    }

    public void setCe_student_id(String ce_student_id) {
        this.ce_student_id = ce_student_id;
    }

    public String getCe_student_name() {
        return ce_student_name;
    }

    public void setCe_student_name(String ce_student_name) {
        this.ce_student_name = ce_student_name;
    }

    public String getCe_edu_year() {
        return ce_edu_year;
    }

    public void setCe_edu_year(String ce_edu_year) {
        this.ce_edu_year = ce_edu_year;
    }

    public String getCe_edu_term() {
        return ce_edu_term;
    }

    public void setCe_edu_term(String ce_edu_term) {
        this.ce_edu_term = ce_edu_term;
    }

    public String getCe_name() {
        return ce_name;
    }

    public void setCe_name(String ce_name) {
        this.ce_name = ce_name;
    }

    public String getCe_time() {
        return ce_time;
    }

    public void setCe_time(String ce_time) {
        this.ce_time = ce_time;
    }

    public String getCe_score() {
        return ce_score;
    }

    public void setCe_score(String ce_score) {
        this.ce_score = ce_score;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "ce_id=" + ce_id +
                ", ce_student_id='" + ce_student_id + '\'' +
                ", ce_student_name='" + ce_student_name + '\'' +
                ", ce_edu_year='" + ce_edu_year + '\'' +
                ", ce_edu_term='" + ce_edu_term + '\'' +
                ", ce_name='" + ce_name + '\'' +
                ", ce_time='" + ce_time + '\'' +
                ", ce_score='" + ce_score + '\'' +
                '}';
    }
}
