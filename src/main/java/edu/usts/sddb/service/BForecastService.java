package edu.usts.sddb.service;

import edu.usts.sddb.entity.bPack.BCourseDependenceMax;
import edu.usts.sddb.entity.bPack.BCourseFailAndWarn;
import edu.usts.sddb.entity.bPack.BScorePosition;

import java.util.HashMap;

public interface BForecastService {

    //获取未通过的必修课程与课程预警
    //由学号获得未通过的必修课程
    //由课程依赖关系与未通过的必修课程获得课程预警
    public BCourseFailAndWarn getFailAndWarn(BCourseDependenceMax dependence);

    //为外部考研录取与入党预测接口提供数据
    public HashMap<String, String> getBPostGraduateOrPartyMember(String st_id);

    //接受成绩位置预测的txt并解析生成该生的成绩位置情况预测
    public BScorePosition getScorePosition(String st_id, String result);
}
