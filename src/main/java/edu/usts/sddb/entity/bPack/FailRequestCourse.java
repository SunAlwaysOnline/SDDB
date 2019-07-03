package edu.usts.sddb.entity.bPack;

//未通过的核心必修课基本类
public class FailRequestCourse {

    //学年
    private String sc_edu_year;

    //学期
    private String sc_edu_term;

    //课程名
    private String sc_course_name;

    //成绩
    private String sc_score;

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

    public String getSc_course_name() {
        return sc_course_name;
    }

    public void setSc_course_name(String sc_course_name) {
        this.sc_course_name = sc_course_name;
    }

    public String getSc_score() {
        return sc_score;
    }

    public void setSc_score(String sc_score) {
        this.sc_score = sc_score;
    }
}
