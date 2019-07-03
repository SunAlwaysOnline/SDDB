package edu.usts.sddb.service;

import java.util.List;
import java.util.Map;

import edu.usts.sddb.entity.Advice;
import edu.usts.sddb.entity.GPAOfStudent;
import edu.usts.sddb.entity.Score;
import edu.usts.sddb.entity.User;
import edu.usts.sddb.entity.assist.DBComment;
import edu.usts.sddb.entity.pack.ClassUngain;
import edu.usts.sddb.entity.pack.CreditInfo;
import edu.usts.sddb.entity.pack.ObjectQuery;
import edu.usts.sddb.entity.pack.ScorePack;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangfeng
 */
public interface ScoreService {

    public List<GPAOfStudent> getAllGPA(String id);

    public List<Score> getAllScore(String id);

    public List<Double> getBodyScore(String id);

    public CreditInfo creadit(String id);

    /**
     * 求出此年级在本学习学分的积欠情况
     *
     * @param year 年级加学期
     * @return
     */
    public List<ClassUngain> ungainScore(int year, String term);

    /**
     * 从年级的角度计算学分学分拖欠人数比例
     *
     * @param grade
     * @param term
     * @return
     */
    public Map<String, Double> gradeRate(int grade, String term);

    /**
     * author sun
     *
     * @param start_term 开始学期
     * @param end_term   结束学期
     * @return 开始学期与结束学期之间学期内的补考人次
     */
    public Map<String, Integer> findMakeUpTimes(String start_term, String end_term);

    /**
     * author sun
     *
     * @param start_term 开始学期
     * @param end_term   结束学期
     * @return 开始学期与结束学期之间学期内的重修人次
     */
    public Map<String, Integer> findRebuildTimes(String start_term, String end_term);

    // 由学号与学年如（2015-2016）查询该生在该学年的平均绩点排名
    public int rankOfMajor(String id, String year);

    //获取该同学的成绩分布 如优：3门课，良：10门课.....
    public Map<String,Integer> getScoreDistribution(String st_id);

    //----------------------------------数据管理---------------------------------------------------------

    public ObjectQuery findByPage(Boolean _search, String filters, int page, int rows);

    public String handle(String oper, Score score, String id[]);

    //------------------------导入导出-----------------------------

    public HSSFWorkbook export(Boolean _search, String filters, int page, int rows);

    public String importExcel(HttpServletRequest request, HttpServletResponse response);
}
