package edu.usts.sddb.entity.bPack;

import java.util.List;

public class ScorePosition {
    /**
     * 前置课程名
     */
    private List<String> preCourse;
    /**
     * 结果课程名
     */
    private String resultCourse;

    /**
     * 置信度
     */
    private double confidence;

    /**
     * 支持度总数
     */
    private int support;

    public List<String> getPreCourse() {
        return preCourse;
    }

    public void setPreCourse(List<String> preCourse) {
        this.preCourse = preCourse;
    }

    public String getResultCourse() {
        return resultCourse;
    }

    public void setResultCourse(String resultCourse) {
        this.resultCourse = resultCourse;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public int getSupport() {
        return support;
    }

    public void setSupport(int support) {
        this.support = support;
    }


    public String listToString(List<String> list) {
        String start = "preCourse[";
        String end = "]";
        String str = start;
        for (String temp : list) {
            str += temp + ",";
        }
        str = str.substring(0, str.length() - 1);
        str += end;
        return str;
    }

    @Override
    public String toString() {
        return "ScorePosition{" +
                "preCourse=" + listToString(preCourse) +
                ", resultCourse='" + resultCourse + '\'' +
                ", confidence=" + confidence +
                ", support=" + support +
                '}';
    }


}
