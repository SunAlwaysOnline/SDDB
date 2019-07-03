package edu.usts.sddb.dao;

import java.util.List;

import edu.usts.sddb.entity.Advice;
import edu.usts.sddb.entity.Certificate;
import edu.usts.sddb.entity.assist.DBComment;

public interface CertificateDao {

    public List<Certificate> findAll();

    public List<Certificate> findCeByCeid(String ce_student_id);


    public List<Certificate> findByPage(int page, int rows);

    public int getSum();

    public int findByFiltersSum(String sql);

    public List<Certificate> findByFilters(String sql);


    public int add(Certificate certificate);

    public int del(String ce_id);

    public int edit(Certificate certificate);

    /**
     * 返回表中的注释，用于导出excel时，显示第一列的字段
     */
    public List<DBComment> findComment();

}
