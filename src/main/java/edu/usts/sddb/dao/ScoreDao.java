package edu.usts.sddb.dao;

import java.util.List;

import edu.usts.sddb.entity.Advice;
import edu.usts.sddb.entity.assist.DBComment;
import org.apache.ibatis.annotations.Param;

import edu.usts.sddb.entity.Certificate;
import edu.usts.sddb.entity.Score;

public interface ScoreDao {

    //--------------------------增删改查开始--------------------------------------
    public List<Score> findByPage(int page, int rows);

    public int getSum();

    public int findByFiltersSum(String sql);

    public List<Score> findByFilters(String sql);


    public int add(Score score);

    public int del(String sc_id);

    public int edit(Score score);

    //得到字段注释
    public List<DBComment> findComment();

    //返回表中所有的数据
    public List<Score> findAll();

    //--------------------------增删改查结束--------------------------------------

    public List<String> findCourseByClass(String className);


    public List<String> findYearByStuId(String sc_student_id);


    // 通过学生ID和相应年份查找该年份学生成绩，不包括不计入平均绩点的学科任选课，与重修课
    public List<Score> findScore(String sc_student_id, String sc_edu_year);

    // 通过ID查找此学生所有成绩，不含重修与任选课
    public List<Score> findScore1(String id);

    //通过ID 和 学年查找此学生所有成绩（含重修和任选课）
    public List<Score> findAllScore(String id, String year);

    public List<Score> findRebuildScore(String id);

    public List<Score> findScoreByIDAndRemark(String id);

    public String findNameById(String id);

    public List<String> findCourseNameById(String id);

    public List<Double> getCreditSum(@Param("id") String id);

    public List<Score> findScoreOfAll(@Param("id") String id);

    public List<Double> findBobyById(String id);

    public List<Score> findScoreIncludeRebuild(String id);

    public List<String> findAllTerm();

    // 查询指定学年学期的补考人次
    public int findMakeUpTimes(String year, String term);

    // 查询指定学年学期的重修人次
    public int findRebuildTimes(String year, String term);


    //--------------------------大数据分析服务接口开始-------------------------------------

    //查询某个学生未通过的核心必修课，如果补考通过或重修通过，依然取出来，主要为课程预警提供服务
    public List<Score> getFailRequestCourse(String sc_student_id);

    //查询某个学生是否存在某个课程的考试记录
    public Score getCourse(String sc_student_id, String sc_course_name);

    //查询某个课程的学分
    public Score getCourseCredit(String sc_course_name);

    //查询一个学生所有的必修课成绩，不包含重修
    public List<Score> getAllRequireCourseById(String sc_student_id);

    //查询指定班的指定课程的平均绩点
    public double getAverageByClassAndCourse(String sc_class_name, String sc_course_name);

    //--------------------------大数据分析服务接口结束-------------------------------------

}
