package edu.usts.sddb.entity;

import java.sql.Timestamp;

/**
 * 留言实体类
 */
public class LeaveMessage {
    /**
     * 自增序号
     */
    private Integer le_id;

    /**
     * 留言人id
     */
    private String le_author_id;

    /**
     * 留言内容
     */
    private String le_content;

    /**
     * 留言时间
     */
    private Timestamp le_time;

    /**
     * 留言对象id
     */
    private String le_target_id;

    /**
     * 是否读取
     */
    private Boolean le_read;

    public Integer getLe_id() {
        return le_id;
    }

    public void setLe_id(Integer le_id) {
        this.le_id = le_id;
    }

    public String getLe_author_id() {
        return le_author_id;
    }

    public void setLe_author_id(String le_author_id) {
        this.le_author_id = le_author_id;
    }

    public String getLe_content() {
        return le_content;
    }

    public void setLe_content(String le_content) {
        this.le_content = le_content;
    }

    public Timestamp getLe_time() {
        return le_time;
    }

    public void setLe_time(Timestamp le_time) {
        this.le_time = le_time;
    }

    public String getLe_target_id() {
        return le_target_id;
    }

    public void setLe_target_id(String le_target_id) {
        this.le_target_id = le_target_id;
    }

    public Boolean getLe_read() {
        return le_read;
    }

    public void setLe_read(Boolean le_read) {
        this.le_read = le_read;
    }

    @Override
    public String toString() {
        return "LeaveMessage{" +
                "le_id=" + le_id +
                ", le_author_id='" + le_author_id + '\'' +
                ", le_content='" + le_content + '\'' +
                ", le_time=" + le_time +
                ", le_target_id='" + le_target_id + '\'' +
                ", le_read=" + le_read +
                '}';
    }
}
