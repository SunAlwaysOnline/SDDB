package edu.usts.sddb.dao;

import edu.usts.sddb.entity.Advice;
import edu.usts.sddb.entity.Student;
import edu.usts.sddb.entity.assist.DBComment;

import java.util.List;

public interface AdviceDao {
    // 由建议种类与种类等级获取建议
    public Advice findAdvice(String ad_category, String ad_level);

    public List<Advice> findAllAdvice();

    public List<Advice> findAdvicesByPage(int page, int rows);

    //得到字段注释
    public List<DBComment> findComment();

    public int getAdviceSum();

    public int findByFiltersSum(String sql);

    public List<Advice> findByFilters(String sql);


    public int add(Advice advice);

    public int del(String ad_id);

    public int edit(Advice advice);

}
