package edu.usts.sddb.entity.bPack;

/**
 * 预测的课程位置
 */
public class ForecastCourse {
    private String forecastCourse;
    private String forecastPosition;
    private double forecastGpa;
    private double confidence;

    public String getForecastCourse() {
        return forecastCourse;
    }

    public void setForecastCourse(String forecastCourse) {
        this.forecastCourse = forecastCourse;
    }

    public String getForecastPosition() {
        return forecastPosition;
    }

    public void setForecastPosition(String forecastPosition) {
        this.forecastPosition = forecastPosition;
    }

    public double getForecastGpa() {
        return forecastGpa;
    }

    public void setForecastGpa(double forecastGpa) {
        this.forecastGpa = forecastGpa;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    @Override
    public String toString() {
        return "ForecastCourse{" +
                "forecastCourse='" + forecastCourse + '\'' +
                ", forecastPosition='" + forecastPosition + '\'' +
                ", forecastGpa=" + forecastGpa +
                ", confidence=" + confidence +
                '}';
    }
}