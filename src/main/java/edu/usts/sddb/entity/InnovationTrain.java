package edu.usts.sddb.entity;

/**
 * 与数据库t_innovation_train对应
 */
public class InnovationTrain {

    /**
     * 自增主键
     */
    private Integer in_id;
    /**
     * 年份
     */
    private String in_year;
    /**
     * 项目名称
     */
    private String in_name;
    /**
     * 项目类别
     */
    private String in_category;
    /**
     * 学生负责人
     */
    private String in_students;
    /**
     * 指导教师
     */
    private String in_teachers;

    public Integer getIn_id() {
        return in_id;
    }

    public void setIn_id(Integer in_id) {
        this.in_id = in_id;
    }

    public String getIn_year() {
        return in_year;
    }

    public void setIn_year(String in_year) {
        this.in_year = in_year;
    }

    public String getIn_name() {
        return in_name;
    }

    public void setIn_name(String in_name) {
        this.in_name = in_name;
    }

    public String getIn_category() {
        return in_category;
    }

    public void setIn_category(String in_category) {
        this.in_category = in_category;
    }

    public String getIn_students() {
        return in_students;
    }

    public void setIn_students(String in_students) {
        this.in_students = in_students;
    }

    public String getIn_teachers() {
        return in_teachers;
    }

    public void setIn_teachers(String in_teachers) {
        this.in_teachers = in_teachers;
    }

    @Override
    public String toString() {
        return "InnovationTrain{" +
                "in_id=" + in_id +
                ", in_year='" + in_year + '\'' +
                ", in_name='" + in_name + '\'' +
                ", in_category='" + in_category + '\'' +
                ", in_students='" + in_students + '\'' +
                ", in_teachers='" + in_teachers + '\'' +
                '}';
    }
}
