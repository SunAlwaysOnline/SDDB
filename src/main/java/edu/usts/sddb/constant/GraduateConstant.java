package edu.usts.sddb.constant;

/**
 * 毕业资格常量类
 * <p>
 * 一．毕业资格审核功能
 * 设置模块判断学生的毕业资格：
 * 1．所有必修课和限选课全部合格：判断所有素质必修课、一般必修课、核心必修课、通识必修课、综合必修课、
 * 通识限选课、方向限选课是否全部及格（同一门课程出现多条记录取最高成绩）。
 * 2．学科任选课是否达到要求：
 * 电子、通信、建筑智能专业：总学分修满10学分；
 * 计算机、嵌入式专业：总学分修满13学分；
 * 电气专业：总学分修满11学分；
 * 计算机（专转本）专业：总学分修满8学分；
 * 电气（3+2）专业：总学分修满9学分；
 * 3．跨学科任选课是否达到要求：
 * 总学分修满5学分，且艺术类不低于2学分、体育类不低于1学分，其他不低于2学分。
 * 4．课外学分是否达到要求（暂缓实现）：
 * 总学分修满4学分；
 * 5．体侧达标（暂缓实现）：
 * 四年平均成绩达到50分以上；
 * 6．特殊情况：
 * 2017年9月以后受到作弊处分（留校察看）的学生直接取消毕业资格；
 * 同时满足上述所有条件（4和5除外），判断所有学生的毕业资格，导出未能毕业学生的名单和原因
 * （参考字段：专业、班级、学号、姓名、不能毕业的原因（必修课缺多少学分、缺几门课程；限选课缺多少学分、
 * 缺几门课程；学科任选课缺多少学分；跨学科任选课缺多少学分；特殊情况等））
 */
public class GraduateConstant {

    //------学科任选课的学分要求-------------------------
    //电子、通信、建筑智能专业：总学分修满10学分
    //电子
    public static final int SUBJECT_ELECTIVE_COURSE_ELECTRON = 10;
    //通信
    public static final int SUBJECT_ELECTIVE_COURSE_COMMUNICATION = 10;
    //建筑智能
    public static final int SUBJECT_ELECTIVE_COURSE_BUILDING_INTELLIGENCE = 10;

    //计算机、嵌入式专业：总学分修满13学分
    //计算机
    public static final int SUBJECT_ELECTIVE_COURSE_COMPUTER = 13;
    //嵌入式
    public static final int SUBJECT_ELECTIVE_COURSE_COMPUTER_EMBEDDED = 13;

    ///电气专业：总学分修满11学分
    public static final int SUBJECT_ELECTIVE_COURSE_ELECTRIC = 11;

    //计算机（专转本）专业：总学分修满8学分
    public static final int SUBJECT_ELECTIVE_COURSE_COMPUTER_Z = 8;

    //电气（3+2）专业：总学分修满9学分
    public static final int SUBJECT_ELECTIVE_COURSE_ELECTRIC_F = 8;
    //------学科任选课的学分要求结束-------------------------

    //------跨学科任选课的学分要求开始-----------------------
    public static final int CROSS_SUBJECT_ELECTIVE_COURSE_ALL=5;



    //跨学科任选课学分要求


    //课外学分(暂缓实现)

    //体测要求(暂缓实现)

    //作弊处分(直接取消毕业资格)

}
