package edu.usts.sddb.entity.pack;

/**
 * 用来封装用户个人主页资料的类
 */
public class StudentProfile {
    /**
     * 用户学号
     */
    private String p_id;

    /**
     * 姓名
     */
    private String p_name;

    /**
     * 性别
     */
    private String p_sex;

    /**
     * 学院
     */
    private String p_academy;

    /**
     * 年级
     */
    private String p_grade;

    /**
     * 专业
     */
    private String p_major;

    /**
     * 班级
     */
    private String p_classroom;

    /**
     * 主页浏览次数
     */
    private Integer p_view;
    /**
     * 已有学年的平均绩点
     */
    private Double p_gpa;

    /**
     * 绩点在本专业的排名
     */
    private String p_rank;

    /**
     * 获奖次数
     */
    private Integer p_award_number;

    /**
     * 志愿服务小时
     */
    private Double p_volunteer_time;

    /**
     * 贡献资料数
     */
    private Integer p_file_count;

    /**
     * 自我简介
     */
    private String p_intro;

    /**
     * QQ号
     */
    private String p_qq;

    /**
     * 邮箱
     */
    private String p_email;

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_sex() {
        return p_sex;
    }

    public void setP_sex(String p_sex) {
        this.p_sex = p_sex;
    }

    public String getP_academy() {
        return p_academy;
    }

    public void setP_academy(String p_academy) {
        this.p_academy = p_academy;
    }

    public String getP_grade() {
        return p_grade;
    }

    public void setP_grade(String p_grade) {
        this.p_grade = p_grade;
    }

    public String getP_major() {
        return p_major;
    }

    public void setP_major(String p_major) {
        this.p_major = p_major;
    }

    public String getP_classroom() {
        return p_classroom;
    }

    public void setP_classroom(String p_classroom) {
        this.p_classroom = p_classroom;
    }

    public Integer getP_view() {
        return p_view;
    }

    public void setP_view(Integer p_view) {
        this.p_view = p_view;
    }

    public Double getP_gpa() {
        return p_gpa;
    }

    public void setP_gpa(Double p_gpa) {
        this.p_gpa = p_gpa;
    }

    public String getP_rank() {
        return p_rank;
    }

    public void setP_rank(String p_rank) {
        this.p_rank = p_rank;
    }

    public Integer getP_award_number() {
        return p_award_number;
    }

    public void setP_award_number(Integer p_award_number) {
        this.p_award_number = p_award_number;
    }

    public Double getP_volunteer_time() {
        return p_volunteer_time;
    }

    public void setP_volunteer_time(Double p_volunteer_time) {
        this.p_volunteer_time = p_volunteer_time;
    }

    public Integer getP_file_count() {
        return p_file_count;
    }

    public void setP_file_count(Integer p_file_count) {
        this.p_file_count = p_file_count;
    }

    public String getP_intro() {
        return p_intro;
    }

    public void setP_intro(String p_intro) {
        this.p_intro = p_intro;
    }

    public String getP_qq() {
        return p_qq;
    }

    public void setP_qq(String p_qq) {
        this.p_qq = p_qq;
    }

    public String getP_email() {
        return p_email;
    }

    public void setP_email(String p_email) {
        this.p_email = p_email;
    }

    @Override
    public String toString() {
        return "StudentProfile{" +
                "p_id='" + p_id + '\'' +
                ", p_name='" + p_name + '\'' +
                ", p_sex='" + p_sex + '\'' +
                ", p_academy='" + p_academy + '\'' +
                ", p_grade='" + p_grade + '\'' +
                ", p_major='" + p_major + '\'' +
                ", p_classroom='" + p_classroom + '\'' +
                ", p_view=" + p_view +
                ", p_gpa=" + p_gpa +
                ", p_rank='" + p_rank + '\'' +
                ", p_award_number=" + p_award_number +
                ", p_volunteer_time=" + p_volunteer_time +
                ", p_file_count=" + p_file_count +
                ", p_intro='" + p_intro + '\'' +
                ", p_qq='" + p_qq + '\'' +
                ", p_email='" + p_email + '\'' +
                '}';
    }
}


