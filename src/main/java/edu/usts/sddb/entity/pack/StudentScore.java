package edu.usts.sddb.entity.pack;

import edu.usts.sddb.entity.Score;

import java.util.List;

public class StudentScore {
    private String year;
    private int rank;
    private double gpa;
    private List<Score> scores;


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "StudentScore{" +
                "year='" + year + '\'' +
                ", rank=" + rank +
                ", gpa=" + gpa +
                ", scores=" + scores +
                '}';
    }
}
