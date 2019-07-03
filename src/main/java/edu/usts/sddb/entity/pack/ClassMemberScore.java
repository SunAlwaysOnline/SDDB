package edu.usts.sddb.entity.pack;

public class ClassMemberScore {

    /**
     * 学号
     */
    private String st_id;
    /**
     * 姓名
     */
    private String st_name;
    /**
     * 性别
     */
    private String st_sex;
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

    public String getSt_id() {
        return st_id;
    }

    public void setSt_id(String st_id) {
        this.st_id = st_id;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public String getSt_sex() {
        return st_sex;
    }

    public void setSt_sex(String st_sex) {
        this.st_sex = st_sex;
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
