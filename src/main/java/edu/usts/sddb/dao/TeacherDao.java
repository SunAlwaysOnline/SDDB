package edu.usts.sddb.dao;

import java.util.List;

import edu.usts.sddb.entity.Major;
import edu.usts.sddb.entity.Student;
import edu.usts.sddb.entity.Teacher;
import edu.usts.sddb.entity.assist.DBComment;
import edu.usts.sddb.entity.pack.ObjectQuery;

public interface TeacherDao {
    public List<Teacher> findAll();

    public int getSum();

    public int add(Teacher teacher);

    public int del(String te_id);

    public int edit(Teacher teacher);

    public List<Object> findByPage(int page, int rows);


    public List<Object> findByFilters(String sqlStr);

    public int findByFiltersSum(String sqlStr);

    public Teacher findById(String id);

    /**
     * 返回表中的注释，用于导出excel时，显示第一列的字段
     */
    public List<DBComment> findComment();
}
