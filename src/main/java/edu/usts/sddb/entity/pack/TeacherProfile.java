package edu.usts.sddb.entity.pack;

/**
 * 用来封装用户个人主页资料的类
 */
public class TeacherProfile {
    /**
     * 用户工号
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
     * 部门
     */
    private String p_department;

    /**
     * 学历
     */
    private String p_edu_background;

    /**
     * 职称
     */
    private String p_pro_title;

    /**
     * 政治面貌
     */
    private String p_politics_status;

    /**
     * 主页浏览次数
     */
    private Integer p_view;

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

    public String getP_department() {
        return p_department;
    }

    public void setP_department(String p_department) {
        this.p_department = p_department;
    }

    public String getP_edu_background() {
        return p_edu_background;
    }

    public void setP_edu_background(String p_edu_background) {
        this.p_edu_background = p_edu_background;
    }

    public String getP_pro_title() {
        return p_pro_title;
    }

    public void setP_pro_title(String p_pro_title) {
        this.p_pro_title = p_pro_title;
    }

    public String getP_politics_status() {
        return p_politics_status;
    }

    public void setP_politics_status(String p_politics_status) {
        this.p_politics_status = p_politics_status;
    }

    public Integer getP_view() {
        return p_view;
    }

    public void setP_view(Integer p_view) {
        this.p_view = p_view;
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
        return "TeacherProfile{" +
                "p_id='" + p_id + '\'' +
                ", p_name='" + p_name + '\'' +
                ", p_sex='" + p_sex + '\'' +
                ", p_department='" + p_department + '\'' +
                ", p_edu_background='" + p_edu_background + '\'' +
                ", p_pro_title='" + p_pro_title + '\'' +
                ", p_politics_status='" + p_politics_status + '\'' +
                ", p_view=" + p_view +
                ", p_file_count=" + p_file_count +
                ", p_intro='" + p_intro + '\'' +
                ", p_qq='" + p_qq + '\'' +
                ", p_email='" + p_email + '\'' +
                '}';
    }
}


