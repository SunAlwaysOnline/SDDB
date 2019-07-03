/**
 *
 */
package edu.usts.sddb.dao;

import java.util.List;

import edu.usts.sddb.entity.PushExcellent;
import edu.usts.sddb.entity.assist.DBComment;

/**
 * @author 张琳
 */
public interface PushExcellentDao {
    public List<PushExcellent> findAll();

    public List<PushExcellent> findByName(String pu_student_name);

    public List<PushExcellent> findByPage(int page, int rows);

    public int getSum();

    public int findByFiltersSum(String sql);

    public List<PushExcellent> findByFilters(String sql);

    public int add(PushExcellent pushExcellent);

    public int del(String pu_id);

    public int edit(PushExcellent pushExcellent);

    /**
     * 返回表中的注释，用于导出excel时，显示第一列的字段
     */
    public List<DBComment> findComment();

}
