package edu.usts.sddb.entity;

import java.sql.Timestamp;

public class Notice {

    private int no_id;
    private String no_user_id;

    //作者姓名，数据库中未设计
    private String no_user_name;
    private String no_title;
    private String no_content;
    private String no_attach;
    private Timestamp no_create_time;

    public int getNo_id() {
        return no_id;
    }

    public void setNo_id(int no_id) {
        this.no_id = no_id;
    }

    public String getNo_user_id() {
        return no_user_id;
    }

    public void setNo_user_id(String no_user_id) {
        this.no_user_id = no_user_id;
    }

    public String getNo_user_name() {
        return no_user_name;
    }

    public void setNo_user_name(String no_user_name) {
        this.no_user_name = no_user_name;
    }

    public String getNo_title() {
        return no_title;
    }

    public void setNo_title(String no_title) {
        this.no_title = no_title;
    }

    public String getNo_content() {
        return no_content;
    }

    public void setNo_content(String no_content) {
        this.no_content = no_content;
    }

    public String getNo_attach() {
        return no_attach;
    }

    public void setNo_attach(String no_attach) {
        this.no_attach = no_attach;
    }

    public Timestamp getNo_create_time() {
        return no_create_time;
    }

    public void setNo_create_time(Timestamp no_create_time) {
        this.no_create_time = no_create_time;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "no_id=" + no_id +
                ", no_user_id='" + no_user_id + '\'' +
                ", no_user_name='" + no_user_name + '\'' +
                ", no_title='" + no_title + '\'' +
                ", no_content='" + no_content + '\'' +
                ", no_attach='" + no_attach + '\'' +
                ", no_create_time=" + no_create_time +
                '}';
    }
}
