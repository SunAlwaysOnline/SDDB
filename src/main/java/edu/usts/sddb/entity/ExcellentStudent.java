package edu.usts.sddb.entity;

/**
 * 与数据库t_excellent_student对应
 *
 * @author 张琳
 */
public class ExcellentStudent {

    /**
     * 自增主键
     */
    private Integer ex_id;

    /**
     * 学号
     */
    private String ex_student_id;

    /**
     * 姓名
     */
    private String ex_student_name;

    /**
     * 年份
     */
    private String ex_year;

    /**
     * 荣誉
     */
    private String ex_honor;

    public Integer getEx_id() {
        return ex_id;
    }

    public void setEx_id(Integer ex_id) {
        this.ex_id = ex_id;
    }

    public String getEx_student_id() {
        return ex_student_id;
    }

    public void setEx_student_id(String ex_student_id) {
        this.ex_student_id = ex_student_id;
    }

    public String getEx_student_name() {
        return ex_student_name;
    }

    public void setEx_student_name(String ex_student_name) {
        this.ex_student_name = ex_student_name;
    }

    public String getEx_year() {
        return ex_year;
    }

    public void setEx_year(String ex_year) {
        this.ex_year = ex_year;
    }

    public String getEx_honor() {
        return ex_honor;
    }

    public void setEx_honor(String ex_honor) {
        this.ex_honor = ex_honor;
    }

    @Override
    public String toString() {
        return "ExcellentStudent{" +
                "ex_id=" + ex_id +
                ", ex_student_id='" + ex_student_id + '\'' +
                ", ex_student_name='" + ex_student_name + '\'' +
                ", ex_year='" + ex_year + '\'' +
                ", ex_honor='" + ex_honor + '\'' +
                '}';
    }
}
