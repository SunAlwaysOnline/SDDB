package edu.usts.sddb.dao;

import java.util.List;

import edu.usts.sddb.entity.Certificate;
import edu.usts.sddb.entity.Classroom;
import edu.usts.sddb.entity.Major;
import edu.usts.sddb.entity.Teacher;
import edu.usts.sddb.entity.assist.DBComment;

public interface ClassroomDao {
    public List<Classroom> findAll();

    public List<String> findMajorByYear(int year);


    public int getSum();

    public int add(Classroom classroom);

    public int del(String cl_id);

    public int edit(Classroom classroom);

    public List<Object> findByPage(int page, int rows);

    public List<Object> findByFilters(String sqlStr);

    public int findByFiltersSum(String sqlStr);

    public List<String> findClassByGradeAndMajor(String grade, String major);


    /**
     * 返回表中的注释，用于导出excel时，显示第一列的字段
     */
    public List<DBComment> findComment();

}
