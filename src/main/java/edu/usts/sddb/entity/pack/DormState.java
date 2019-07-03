package edu.usts.sddb.entity.pack;

/**
 * 宿舍分数状况
 */
public class DormState {
    /**
     * 宿舍名
     */
    private String name;

    /**
     * 宿舍人数
     */
    private Integer num;

    /**
     * 学习平均分
     */
    private Integer score;

    /**
     * 体育平均分
     */
    private Integer body;

    /**
     * 科研平均分
     */
    private Integer scientific;

    /**
     * 志愿平均分
     */
    private Integer volunteer;

    /**
     * 总评平均分
     */
    private Integer all;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getBody() {
        return body;
    }

    public void setBody(Integer body) {
        this.body = body;
    }

    public Integer getScientific() {
        return scientific;
    }

    public void setScientific(Integer scientific) {
        this.scientific = scientific;
    }

    public Integer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Integer volunteer) {
        this.volunteer = volunteer;
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }
}
