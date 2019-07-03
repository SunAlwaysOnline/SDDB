package edu.usts.sddb.dao;

import java.util.List;

import edu.usts.sddb.entity.Scholarship;
import edu.usts.sddb.entity.assist.DBComment;

public interface ScholarshipDao {
    public List<Scholarship> findAll();

    public List<Scholarship> findByName(String name);

    public List<Scholarship> findById(String id);

    public List<Scholarship> findScholarshipByPage(int page, int rows);

    public int getScholarshipSum();

    public int findByFiltersSum(String sql);

    public List<Scholarship> findByFilters(String sql);

    public int add(Scholarship scholarship);

    public int del(String sc_id);

    public int edit(Scholarship scholarship);

    /**
     * 返回表中的注释，用于导出excel时，显示第一列的字段
     */
    public List<DBComment> findComment();
}
