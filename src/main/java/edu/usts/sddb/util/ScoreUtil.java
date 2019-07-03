package edu.usts.sddb.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import edu.usts.sddb.entity.Score;

/**
 * @author sun 成绩表的工具类
 */
public class ScoreUtil {

    /**
     * author sun
     *
     * @param sList 某个学生在某个年度的所有成绩
     * @return 当年的gpa
     */
    public static double getGpaInYear(List<Score> sList) {
        // 平均绩点
        double gpa = 0.0;
        // 相应绩点乘以学分的和
        double creditMultiplygrade = 0;
        // 总学分
        double sumCredit = 0;
        for (Score s : sList) {
            if(null==s.getSc_gpa()){
                s.setSc_gpa(0.0);
            }
            creditMultiplygrade += s.getSc_course_credit() * s.getSc_gpa();
            sumCredit += s.getSc_course_credit();

        }
        gpa = creditMultiplygrade / sumCredit;
        // 保留四位小数
        DecimalFormat format = new DecimalFormat("#.0000");
        try {
            gpa = Double.parseDouble(format.format(gpa));
        } catch (NumberFormatException e) {
            //按照学号未查到成绩，暂定gpa为0
            gpa = 0.0;
        }
        return gpa;

    }

    /**
     * @param start_term 开始学期
     * @param end_term   结束学期
     * @return 返回开始学期是否小于等于结束学期
     */
    public static Boolean joinExecute(String start_term, String end_term) {
        String str1 = start_term.replaceAll("-", "");
        String str2 = end_term.replaceAll("-", "");
        return Integer.parseInt(str2) >= Integer.parseInt(str1);
    }

    /**
     * author sun
     *
     * @param start_term 开始学期
     * @param end_term   结束学期
     * @return 位于开始学期与结束学期之间的学期
     */
    public static List<String> getBetweenTerms(String start_term, String end_term) {
        List<String> list = new ArrayList<String>();
        int start_year = Integer.parseInt(start_term.split("-")[0]);
        int end_year = Integer.parseInt(end_term.split("-")[0]);

        for (int i = start_year; i <= end_year; i++) {
            String temp_term = i + "-" + (i + 1);
            for (int j = 1; j <= 2; j++) {
                String term = temp_term + "-" + j;
                list.add(term);
            }
        }
        if (start_term.endsWith("2")) {
            list.remove(0);
        }
        if (end_term.endsWith("1")) {
            list.remove(list.size() - 1);
        }
        return list;
    }


}
