package edu.usts.sddb.service;

import edu.usts.sddb.entity.Dorm;
import edu.usts.sddb.entity.pack.DormMemberScore;
import edu.usts.sddb.entity.pack.DormState;

import java.util.List;
import java.util.Map;

public interface DormService {

    //获得去重的宿舍名
    public List<String> findDistinctDorm();

    //根据宿舍名查找宿舍成员
    public List<Dorm> findByDormName(String d_name);

    //根据宿舍名生成宿舍成员情况图
    public List<DormMemberScore> getDormMemberScore(String d_name);

    //获取指定宿舍分数状况
    public DormState getOneDormState(String d_name);

    //获取全体宿舍分数状况
    public List<DormState> getAllDormState();

    //获取指定宿舍的平均分与总分排名
    public Map<String, String> getDormRank(String d_name);
}
