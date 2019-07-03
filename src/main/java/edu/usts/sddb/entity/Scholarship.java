package edu.usts.sddb.entity;

/**
 * 与数据库t_scholarship对应，奖学金表
 *
 * @author 张琳
 */
public class Scholarship {

    /**
     * 自增主键
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
     * 专业
     */
    private String sc_student_major;
    /**
     * 年度
     */
    private String sc_year;
    /**
     * 奖项
     */
    private String sc_awards;
    /**
     * 奖金
     */
    private int sc_money;

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

    public String getSc_student_major() {
        return sc_student_major;
    }

    public void setSc_student_major(String sc_student_major) {
        this.sc_student_major = sc_student_major;
    }

    public String getSc_year() {
        return sc_year;
    }

    public void setSc_year(String sc_year) {
        this.sc_year = sc_year;
    }

    public String getSc_awards() {
        return sc_awards;
    }

    public void setSc_awards(String sc_awards) {
        this.sc_awards = sc_awards;
    }

    public int getSc_money() {
        return sc_money;
    }

    public void setSc_money(int sc_money) {
        this.sc_money = sc_money;
    }

    @Override
    public String toString() {
        return "Scholarship{" +
                "sc_id=" + sc_id +
                ", sc_student_id='" + sc_student_id + '\'' +
                ", sc_student_name='" + sc_student_name + '\'' +
                ", sc_student_major='" + sc_student_major + '\'' +
                ", sc_year='" + sc_year + '\'' +
                ", sc_awards='" + sc_awards + '\'' +
                ", sc_money=" + sc_money +
                '}';
    }
}
