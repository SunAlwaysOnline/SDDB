package edu.usts.sddb.dao;


import edu.usts.sddb.entity.Duty;
import edu.usts.sddb.entity.assist.DBComment;

import java.util.List;

public interface DutyDao {

    public List<Duty> findAll();

    public int getSum();

    public List<Duty> findDutyById(String du_student_id);

    public int add(Duty duty);

    public int del(String du_id);

    public int edit(Duty duty);

    /**
     * 返回表中的注释，用于导出excel时，显示第一列的字段
     */
    public List<DBComment> findComment();



    /**
     * @param pre  从第pre+1个记录开始
     * @param rows 每页显示的行数，即取多少条
     */
    public List<Duty> findByPage(int pre, int rows);

    /**
     * @param sql 较为复杂的一条查询语句，依据多条件查找得出
     */
    public List<Duty> findByFilters(String sql);

    /**
     * @param sql 较为复杂的一条查询语句，查询满足多条件查询的记录总数
     */
    public int findByFiltersSum(String sql);


}
