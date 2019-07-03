package edu.usts.sddb.entity;

/**
 * 与数据库t_post_graduate表对应
 */
public class PostGraduate {
    private Integer po_id;
    /**
     * 姓名
     */
    private String po_student_name;
    /**
     * 政治面貌
     */
    private String po_politics_status;
    /**
     * 统考分数
     */
    private int po_exam_score;
    /**
     * 本科专业
     */
    private String po_old_major;
    /**
     * 录取院校
     */
    private String po_new_school;
    /**
     * 录取专业
     */
    private String po_new_major;

    public Integer getPo_id() {
        return po_id;
    }

    public void setPo_id(Integer po_id) {
        this.po_id = po_id;
    }

    public String getPo_student_name() {
        return po_student_name;
    }

    public void setPo_student_name(String po_student_name) {
        this.po_student_name = po_student_name;
    }

    public String getPo_politics_status() {
        return po_politics_status;
    }

    public void setPo_politics_status(String po_politics_status) {
        this.po_politics_status = po_politics_status;
    }

    public int getPo_exam_score() {
        return po_exam_score;
    }

    public void setPo_exam_score(int po_exam_score) {
        this.po_exam_score = po_exam_score;
    }

    public String getPo_old_major() {
        return po_old_major;
    }

    public void setPo_old_major(String po_old_major) {
        this.po_old_major = po_old_major;
    }

    public String getPo_new_school() {
        return po_new_school;
    }

    public void setPo_new_school(String po_new_school) {
        this.po_new_school = po_new_school;
    }

    public String getPo_new_major() {
        return po_new_major;
    }

    public void setPo_new_major(String po_new_major) {
        this.po_new_major = po_new_major;
    }

    @Override
    public String toString() {
        return "PostGraduate{" +
                "po_id=" + po_id +
                ", po_student_name='" + po_student_name + '\'' +
                ", po_politics_status='" + po_politics_status + '\'' +
                ", po_exam_score=" + po_exam_score +
                ", po_old_major='" + po_old_major + '\'' +
                ", po_new_school='" + po_new_school + '\'' +
                ", po_new_major='" + po_new_major + '\'' +
                '}';
    }
}
