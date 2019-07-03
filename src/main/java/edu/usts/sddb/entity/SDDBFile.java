package edu.usts.sddb.entity;

import java.sql.Timestamp;

public class SDDBFile {
    private int fi_id;
    private String fi_user_id;

    //文件发布者的姓名，数据库并未设计
    private String fi_user_name;
    private String fi_original_name;
    private String fi_modified_name;
    private String fi_path;
    private String fi_info;
    private String fi_major;
    private String fi_grade;
    private String fi_course;
    private int fi_download_count;
    private Timestamp fi_time;
    private Boolean fi_is_checked;

    public int getFi_id() {
        return fi_id;
    }

    public void setFi_id(int fi_id) {
        this.fi_id = fi_id;
    }

    public String getFi_user_id() {
        return fi_user_id;
    }

    public void setFi_user_id(String fi_user_id) {
        this.fi_user_id = fi_user_id;
    }

    public String getFi_user_name() {
        return fi_user_name;
    }

    public void setFi_user_name(String fi_user_name) {
        this.fi_user_name = fi_user_name;
    }

    public String getFi_original_name() {
        return fi_original_name;
    }

    public void setFi_original_name(String fi_original_name) {
        this.fi_original_name = fi_original_name;
    }

    public String getFi_modified_name() {
        return fi_modified_name;
    }

    public void setFi_modified_name(String fi_modified_name) {
        this.fi_modified_name = fi_modified_name;
    }

    public String getFi_path() {
        return fi_path;
    }

    public void setFi_path(String fi_path) {
        this.fi_path = fi_path;
    }

    public String getFi_info() {
        return fi_info;
    }

    public void setFi_info(String fi_info) {
        this.fi_info = fi_info;
    }

    public String getFi_major() {
        return fi_major;
    }

    public void setFi_major(String fi_major) {
        this.fi_major = fi_major;
    }

    public String getFi_grade() {
        return fi_grade;
    }

    public void setFi_grade(String fi_grade) {
        this.fi_grade = fi_grade;
    }

    public String getFi_course() {
        return fi_course;
    }

    public void setFi_course(String fi_course) {
        this.fi_course = fi_course;
    }

    public int getFi_download_count() {
        return fi_download_count;
    }

    public void setFi_download_count(int fi_download_count) {
        this.fi_download_count = fi_download_count;
    }

    public Timestamp getFi_time() {
        return fi_time;
    }

    public void setFi_time(Timestamp fi_time) {
        this.fi_time = fi_time;
    }

    public Boolean getFi_is_checked() {
        return fi_is_checked;
    }

    public void setFi_is_checked(Boolean fi_is_checked) {
        this.fi_is_checked = fi_is_checked;
    }

    @Override
    public String toString() {
        return "SDDBFile{" +
                "fi_id=" + fi_id +
                ", fi_user_id='" + fi_user_id + '\'' +
                ", fi_user_name='" + fi_user_name + '\'' +
                ", fi_original_name='" + fi_original_name + '\'' +
                ", fi_modified_name='" + fi_modified_name + '\'' +
                ", fi_path='" + fi_path + '\'' +
                ", fi_info='" + fi_info + '\'' +
                ", fi_major='" + fi_major + '\'' +
                ", fi_grade='" + fi_grade + '\'' +
                ", fi_course='" + fi_course + '\'' +
                ", fi_download_count=" + fi_download_count +
                ", fi_time=" + fi_time +
                ", fi_is_checked=" + fi_is_checked +
                '}';
    }
}
