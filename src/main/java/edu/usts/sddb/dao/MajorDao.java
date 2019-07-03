package edu.usts.sddb.dao;

import java.util.List;

import edu.usts.sddb.entity.Advice;
import edu.usts.sddb.entity.Major;
import edu.usts.sddb.entity.assist.DBComment;

public interface MajorDao {
    public List<Major> findAll();

    public List<Major> findByPage(int page, int rows);

    public int getSum();

    public int findByFiltersSum(String sql);

    public List<Major> findByFilters(String sql);


    public int add(Major major);

    public int del(String ad_id);

    public int edit(Major major);

    public List<String> findMajorByAcademyId(String ma_academy_id);


    /**
     * 返回表中的注释，用于导出excel时，显示第一列的字段
     */
    public List<DBComment> findComment();
}
