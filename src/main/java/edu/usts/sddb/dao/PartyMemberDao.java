/**
 *
 */
package edu.usts.sddb.dao;

import java.util.List;

import edu.usts.sddb.entity.Advice;
import edu.usts.sddb.entity.PartyMember;
import edu.usts.sddb.entity.assist.DBComment;

/**
 * @author 张琳
 */
public interface PartyMemberDao {

    public List<PartyMember> findAll();

    public List<PartyMember> findByName(String pa_student_name);


    public List<PartyMember> findByPage(int page, int rows);

    public int getSum();

    public int findByFiltersSum(String sql);

    public List<PartyMember> findByFilters(String sql);


    public int add(PartyMember partyMember);

    public int del(String pa_id);

    public int edit(PartyMember partyMember);

    /**
     * 返回表中的注释，用于导出excel时，显示第一列的字段
     */
    public List<DBComment> findComment();
}
