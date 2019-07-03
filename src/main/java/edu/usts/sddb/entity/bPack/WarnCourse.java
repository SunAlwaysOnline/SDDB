package edu.usts.sddb.entity.bPack;

//预警课程类
public class WarnCourse {

    //课程名
    private String sc_course_name;

    //课程学分
    private String sc_course_credit;

    //预警级别,由课程分数决定
    //0-"最高", "极高", "高", "较高", "中等", "一般", "低", "较低", "极低", "最低", "不可能"-100

    private String level;

    public String getSc_course_name() {
        return sc_course_name;
    }

    public void setSc_course_name(String sc_course_name) {
        this.sc_course_name = sc_course_name;
    }

    public String getSc_course_credit() {
        return sc_course_credit;
    }

    public void setSc_course_credit(String sc_course_credit) {
        this.sc_course_credit = sc_course_credit;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

