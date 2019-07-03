package edu.usts.sddb.dao;

import java.util.List;

import edu.usts.sddb.entity.Advice;
import edu.usts.sddb.entity.PostGraduate;
import edu.usts.sddb.entity.assist.DBComment;

public interface PostGraduateDao {

    public List<PostGraduate> findAll();

    public List<PostGraduate> findByname(String name);

    public List<PostGraduate> findByPage(int page, int rows);

    public int getSum();

    public int findByFiltersSum(String sql);

    public List<PostGraduate> findByFilters(String sql);


    public int add(PostGraduate postGraduate);

    public int del(String ad_id);

    public int edit(PostGraduate postGraduate);


    /**
     * 返回表中的注释，用于导出excel时，显示第一列的字段
     */
    public List<DBComment> findComment();
}
