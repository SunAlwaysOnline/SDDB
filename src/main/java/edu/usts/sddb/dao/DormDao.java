package edu.usts.sddb.dao;

import edu.usts.sddb.entity.Dorm;

import java.util.List;

public interface DormDao {

    //获得去重的宿舍名
    public List<String> findDistinctDorm();

    //根据宿舍名查找宿舍成员
    public List<Dorm> findByDormName(String d_name);
}
