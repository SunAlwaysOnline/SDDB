package edu.usts.sddb.entity;

/**
 * 与数据库t_user对应，用户登录表
 *
 * @author wangfeng
 */
public class User {
    /**
     * 登录ID
     */
    private String us_id;
    /**
     * 登录密码
     */
    private String us_password;
    /**
     * 用户姓名
     */
    private String us_name;
    /**
     * 用户类型
     */
    private String us_type;

    /**
     * 自我简介
     */
    private String us_intro;

    /**
     * 主页浏览次数
     */
    private Integer us_view;

    /**
     * 手机号码
     */
    private String us_phone;

    /**
     * QQ号码
     */
    private String us_qq;

    /**
     * 邮箱
     */
    private String us_email;

    /**
     * 是否允许被同学查看详细信息
     */
    private boolean us_allow_detail;

    public String getUs_id() {
        return us_id;
    }

    public void setUs_id(String us_id) {
        this.us_id = us_id;
    }

    public String getUs_password() {
        return us_password;
    }

    public void setUs_password(String us_password) {
        this.us_password = us_password;
    }

    public String getUs_name() {
        return us_name;
    }

    public void setUs_name(String us_name) {
        this.us_name = us_name;
    }

    public String getUs_type() {
        return us_type;
    }

    public void setUs_type(String us_type) {
        this.us_type = us_type;
    }

    public String getUs_intro() {
        return us_intro;
    }

    public void setUs_intro(String us_intro) {
        this.us_intro = us_intro;
    }

    public Integer getUs_view() {
        return us_view;
    }

    public void setUs_view(Integer us_view) {
        this.us_view = us_view;
    }

    public String getUs_phone() {
        return us_phone;
    }

    public void setUs_phone(String us_phone) {
        this.us_phone = us_phone;
    }

    public String getUs_qq() {
        return us_qq;
    }

    public void setUs_qq(String us_qq) {
        this.us_qq = us_qq;
    }

    public String getUs_email() {
        return us_email;
    }

    public void setUs_email(String us_email) {
        this.us_email = us_email;
    }

    public boolean getUs_allow_detail() {
        return us_allow_detail;
    }

    public void setUs_allow_detail(boolean us_allow_detail) {
        this.us_allow_detail = us_allow_detail;
    }

    @Override
    public String toString() {
        return "User{" +
                "us_id='" + us_id + '\'' +
                ", us_password='" + us_password + '\'' +
                ", us_name='" + us_name + '\'' +
                ", us_type='" + us_type + '\'' +
                ", us_intro='" + us_intro + '\'' +
                ", us_view=" + us_view +
                ", us_phone='" + us_phone + '\'' +
                ", us_qq='" + us_qq + '\'' +
                ", us_email='" + us_email + '\'' +
                ", us_allow_detail=" + us_allow_detail +
                '}';
    }
}
