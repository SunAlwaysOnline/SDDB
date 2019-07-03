package edu.usts.sddb.dao;


import edu.usts.sddb.entity.Volunteer;
import edu.usts.sddb.entity.assist.DBComment;

import java.util.List;

public interface VolunteerDao {

    public List<Volunteer> findAll();

    public double findTimeById(String id);

    public List<Volunteer> getClassRank(String id);

    public List<Volunteer>getGradeRank(String id);

    public List<Volunteer> getAcademyRank(String id);

    public int add(Volunteer volunteer);

    public int del(String v_student_id);

    public int edit(Volunteer volunteer);

    /**
     * 返回表中的注释，用于导出excel时，显示第一列的字段
     */
    public List<DBComment> findComment();

    public int getVolunteerSum();

    /**
     * @param pre  从第pre+1个记录开始
     * @param rows 每页显示的行数，即取多少条
     */
    public List<Volunteer> findVolunteerByPage(int pre, int rows);

    /**
     * @param sql 较为复杂的一条查询语句，依据多条件查找得出
     */
    public List<Volunteer> findByFilters(String sql);

    /**
     * @param sql 较为复杂的一条查询语句，查询满足多条件查询的记录总数
     */
    public int findByFiltersSum(String sql);


}
