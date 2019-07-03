package edu.usts.sddb.util;

import edu.usts.sddb.entity.bPack.ScorePosition;

import java.util.ArrayList;
import java.util.List;


public class ResultUtil {
    public static List<ScorePosition> scorePositionList = new ArrayList<>();

    //将result/rules.txt中的数据解析出来
    public static List<ScorePosition> getDataFromResult(String result) {
        String[] line = result.split("\n");
        ScorePosition scorePosition = null;

        for (String str : line) {
            scorePosition = new ScorePosition();
            String[] item = str.split("->");
            String[] preCourses = item[0].split(",");
            List<String> preCourseList = new ArrayList<>();

            for (String preCourse : preCourses) {
                preCourseList.add(preCourse);
                scorePosition.setPreCourse(preCourseList);
            }

            String resultCourse = item[1].split(":")[0];
            scorePosition.setResultCourse(resultCourse);

            String confidenceUnformat = item[1].split(":")[1].split(",")[0];
            double confidence = Double.parseDouble(confidenceUnformat.trim());
            scorePosition.setConfidence(confidence);

            String supportUnformat = item[1].split(":")[1].split(",")[1];
            int support = Integer.parseInt(supportUnformat.trim());
            scorePosition.setSupport(support);

            scorePositionList.add(scorePosition);

        }
        return scorePositionList;
    }

    //判断self的gpa与avg的gpa的关系,返回self在班级中的位置
    public static String getPosition(double avg, double self) {
        double dis = self - avg;
        if (Math.abs(dis) <= 1.0) {
            return "中游";
        }
        if ((Math.abs(dis) > 1.0) && Math.abs(dis) < 1.8) {
            if (dis > 0) {
                return "中上游";
            } else {
                return "中下游";
            }
        }
        if (Math.abs(dis) >= 1.8) {
            if (dis > 0) {
                return "上游";
            } else {
                return "下游";
            }
        }
        return "";
    }

    public static String getPositionByPrePositon(List<String> list) {
        if (list.size() == 0) {
            return "缺少数据，无法预测!";
        }
        String result = "中游";
        int score = 0;
        for (String position : list) {
            if (position.equals("上游")) {
                score += 100;
            } else if (position.equals("中上游")) {
                score += 75;
            } else if (position.equals("中游")) {
                score += 50;
            } else if (position.equals("中下游")) {
                score += 25;
            } else if (position.equals("下游")) {
                score += 0;
            }
        }
        score = score / list.size();
        if (score > 75) {
            return "上游";
        } else if (score > 60) {
            return "中上游";
        } else if (score > 45) {
            return "中游";
        } else if (score > 30) {
            return "中下游";
        } else {
            return "下游";
        }
    }
}
