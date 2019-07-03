package edu.usts.sddb.entity.bPack;

import java.util.List;

/**
 * 成绩位置预测的封装类
 */
public class BScorePosition {
    private List<ExistCourse> existCourseList;
    private List<ForecastCourse> forecastCourseList;

    public List<ExistCourse> getExistCourseList() {
        return existCourseList;
    }

    public void setExistCourseList(List<ExistCourse> existCourseList) {
        this.existCourseList = existCourseList;
    }

    public List<ForecastCourse> getForecastCourseList() {
        return forecastCourseList;
    }

    public void setForecastCourseList(List<ForecastCourse> forecastCourseList) {
        this.forecastCourseList = forecastCourseList;
    }

    @Override
    public String toString() {
        return "BScorePosition{" +
                "existCourseList=" + existCourseList +
                ", forecastCourseList=" + forecastCourseList +
                '}';
    }
}


