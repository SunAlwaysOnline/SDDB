package edu.usts.sddb.entity.pack;

public class DormMemberScore {
    /**
     * 宿舍名
     */
    private String d_name;
    /**
     * 床号
     */
    private String d_no;
    /**
     * 学号
     */
    private String d_st_id;
    /**
     * 姓名
     */
    private String d_st_name;
    /**
     * 性别
     */
    private String d_st_sex;
    /**
     * 班级
     */
    private String d_st_class;

    /**
     * 学习成绩
     */
    private int scorePart;
    /**
     * 身体素质
     */
    private int bodyPart;
    /**
     * 科研情况
     */
    private int scientificPart;
    /**
     * 志愿服务
     */
    private int volunteerPart;
    /**
     * 总评
     */
    private int all;

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_no() {
        return d_no;
    }

    public void setD_no(String d_no) {
        this.d_no = d_no;
    }

    public String getD_st_id() {
        return d_st_id;
    }

    public void setD_st_id(String d_st_id) {
        this.d_st_id = d_st_id;
    }

    public String getD_st_name() {
        return d_st_name;
    }

    public void setD_st_name(String d_st_name) {
        this.d_st_name = d_st_name;
    }

    public String getD_st_sex() {
        return d_st_sex;
    }

    public void setD_st_sex(String d_st_sex) {
        this.d_st_sex = d_st_sex;
    }

    public String getD_st_class() {
        return d_st_class;
    }

    public void setD_st_class(String d_st_class) {
        this.d_st_class = d_st_class;
    }

    public int getScorePart() {
        return scorePart;
    }

    public void setScorePart(int scorePart) {
        this.scorePart = scorePart;
    }

    public int getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(int bodyPart) {
        this.bodyPart = bodyPart;
    }

    public int getScientificPart() {
        return scientificPart;
    }

    public void setScientificPart(int scientificPart) {
        this.scientificPart = scientificPart;
    }

    public int getVolunteerPart() {
        return volunteerPart;
    }

    public void setVolunteerPart(int volunteerPart) {
        this.volunteerPart = volunteerPart;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }
}
