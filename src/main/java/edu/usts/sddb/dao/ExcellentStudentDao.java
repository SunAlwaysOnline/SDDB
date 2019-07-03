package edu.usts.sddb.dao;

import java.util.List;

import edu.usts.sddb.entity.Advice;
import edu.usts.sddb.entity.ExcellentStudent;
import edu.usts.sddb.entity.assist.DBComment;

public interface ExcellentStudentDao {

    public List<ExcellentStudent> findAll();

    public List<ExcellentStudent> findById(String id);

    public List<ExcellentStudent> findByPage(int page, int rows);

    public int getSum();

    public int findByFiltersSum(String sql);

    public List<ExcellentStudent> findByFilters(String sql);


    public int add(ExcellentStudent excellentStudent);

    public int del(String ad_id);

    public int edit(ExcellentStudent excellentStudent);

    /**
     * 返回表中的注释，用于导出excel时，显示第一列的字段
     */
    public List<DBComment> findComment();
}
