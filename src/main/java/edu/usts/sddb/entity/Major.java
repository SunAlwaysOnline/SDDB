package edu.usts.sddb.entity;

public class Major {
    private String ma_id;
    private String ma_name;
    private String ma_eng;
    private int ma_academy_id;
    private String ma_category;
    private int ma_edu_len;
    private String ma_edu_degree;
    private String ma_level;

    public String getMa_id() {
        return ma_id;
    }

    public void setMa_id(String ma_id) {
        this.ma_id = ma_id;
    }

    public String getMa_name() {
        return ma_name;
    }

    public void setMa_name(String ma_name) {
        this.ma_name = ma_name;
    }

    public String getMa_eng() {
        return ma_eng;
    }

    public void setMa_eng(String ma_eng) {
        this.ma_eng = ma_eng;
    }

    public int getMa_academy_id() {
        return ma_academy_id;
    }

    public void setMa_academy_id(int ma_academy_id) {
        this.ma_academy_id = ma_academy_id;
    }

    public String getMa_category() {
        return ma_category;
    }

    public void setMa_category(String ma_category) {
        this.ma_category = ma_category;
    }

    public int getMa_edu_len() {
        return ma_edu_len;
    }

    public void setMa_edu_len(int ma_edu_len) {
        this.ma_edu_len = ma_edu_len;
    }

    public String getMa_edu_degree() {
        return ma_edu_degree;
    }

    public void setMa_edu_degree(String ma_edu_degree) {
        this.ma_edu_degree = ma_edu_degree;
    }

    public String getMa_level() {
        return ma_level;
    }

    public void setMa_level(String ma_level) {
        this.ma_level = ma_level;
    }

    @Override
    public String toString() {
        return "Major{" +
                "ma_id='" + ma_id + '\'' +
                ", ma_name='" + ma_name + '\'' +
                ", ma_eng='" + ma_eng + '\'' +
                ", ma_academy_id=" + ma_academy_id +
                ", ma_category='" + ma_category + '\'' +
                ", ma_edu_len=" + ma_edu_len +
                ", ma_edu_degree='" + ma_edu_degree + '\'' +
                ", ma_level='" + ma_level + '\'' +
                '}';
    }
}
