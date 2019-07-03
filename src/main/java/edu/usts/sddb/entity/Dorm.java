package edu.usts.sddb.entity;

/**
 * 宿舍表
 */
public class Dorm {
    /**
     * 学号
     */
    private String d_st_id;
    /**
     * 姓名
     */
    private String d_st_name;
    /**
     * 班级
     */
    private String d_st_class;
    /**
     * 宿舍名称
     */
    private String d_name;

    /**
     * 床号
     */
    private String d_no;

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

    public String getD_st_class() {
        return d_st_class;
    }

    public void setD_st_class(String d_st_class) {
        this.d_st_class = d_st_class;
    }

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
}
