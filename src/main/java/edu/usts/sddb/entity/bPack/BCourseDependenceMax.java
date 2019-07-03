package edu.usts.sddb.entity.bPack;

//包含学号
public class BCourseDependenceMax {

    //学号
    private String sc_student_id;

    //大数据分析中课程预警生成的json文件对应的实体类
    private BCourseDependenceJson dependence;

    public String getSc_student_id() {
        return sc_student_id;
    }

    public void setSc_student_id(String sc_student_id) {
        this.sc_student_id = sc_student_id;
    }

    public BCourseDependenceJson getDependence() {
        return dependence;
    }

    public void setDependence(BCourseDependenceJson dependence) {
        this.dependence = dependence;
    }

    @Override
    public String toString() {
        return "BCourseDependenceMax{" +
                "sc_student_id='" + sc_student_id + '\'' +
                ", dependence=" + dependence +
                '}';
    }
}

