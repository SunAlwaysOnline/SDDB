package edu.usts.sddb.dao;

import java.util.List;

import edu.usts.sddb.entity.Advice;
import edu.usts.sddb.entity.assist.DBComment;
import org.apache.ibatis.annotations.Param;

import edu.usts.sddb.entity.Competition;

public interface CompetitionDao {
    public List<Competition> findAll();

    public List<Competition> findByName(String name);

    public List<Competition> findById(String id);

    public List<Competition> findByPage(int page, int rows);

    public int getSum();

    public int findByFiltersSum(String sql);

    public List<Competition> findByFilters(String sql);


    public int add(Competition competition);

    public int del(String co_id);

    public int edit(Competition competition);

    /**
     * 返回表中的注释，用于导出excel时，显示第一列的字段
     */
    public List<DBComment> findComment();
}
