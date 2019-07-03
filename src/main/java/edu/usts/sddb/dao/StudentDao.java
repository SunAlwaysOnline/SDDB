package edu.usts.sddb.dao;

import java.util.List;

import edu.usts.sddb.entity.Student;
import edu.usts.sddb.entity.assist.DBComment;
import edu.usts.sddb.entity.pack.StudentQuery;

public interface StudentDao {

    public List<Student> findAll();

    public int getSum();

    public int add(Student student);

    public int del(String st_id);

    public int edit(Student student);

    public List<Student> findByPage(int page, int rows);

    public List<Student> findByFilters(String sqlStr);

    public int findByFiltersSum(String sqlStr);

    public Student findById(String id);

    public List<Student> findStudentsByClassroom(String st_class);

    public List<String> findStuIdByMajorAndYear(String major, int grade);

    /**
     * 返回表中的注释，用于导出excel时，显示第一列的字段
     */
    public List<DBComment> findComment();

}
