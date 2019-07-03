package edu.usts.sddb.dao;

import java.util.List;

import edu.usts.sddb.entity.InnovationTrain;
import edu.usts.sddb.entity.assist.DBComment;

public interface InnovationTrainDao {

    public List<InnovationTrain> findAll();

    public List<InnovationTrain> findByName(String name);


    public List<InnovationTrain> findByPage(int page, int rows);

    public int getSum();

    public int findByFiltersSum(String sql);

    public List<InnovationTrain> findByFilters(String sql);

    public int add(InnovationTrain innovationTrain);

    public int del(String in_id);

    public int edit(InnovationTrain innovationTrain);

    /**
     * 返回表中的注释，用于导出excel时，显示第一列的字段
     */
    public List<DBComment> findComment();


}
