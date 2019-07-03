package edu.usts.sddb.service.impl;

import edu.usts.sddb.dao.CompetitionDao;
import edu.usts.sddb.dao.ScholarshipDao;
import edu.usts.sddb.dao.ScoreDao;
import edu.usts.sddb.dao.StudentDao;
import edu.usts.sddb.entity.Competition;
import edu.usts.sddb.entity.GPAOfStudent;
import edu.usts.sddb.entity.Scholarship;
import edu.usts.sddb.entity.Score;
import edu.usts.sddb.entity.bPack.*;
import edu.usts.sddb.service.BForecastService;
import edu.usts.sddb.service.ScoreService;
import edu.usts.sddb.util.DataFormatUtil;
import edu.usts.sddb.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service("bForecastService")
public class BForecastServiceImpl implements BForecastService {

    @Autowired
    ScoreDao scoreDao;

    @Autowired
    ScoreService scoreService;

    @Autowired
    ScholarshipDao scholarshipDao;

    @Autowired
    CompetitionDao competitionDao;

    @Autowired
    StudentDao studentDao;

    //--------------------------------------课程通过预警开始-------------------------------------

    //@Override
    public BCourseFailAndWarn getFailAndWarn(BCourseDependenceMax dependence) {
        BCourseFailAndWarn bCourseDependence = new BCourseFailAndWarn();

        //设置未通过的核心必修课程
        List<FailRequestCourse> failList = new ArrayList<>();
        List<Score> scoreList = scoreDao.getFailRequestCourse(dependence.getSc_student_id());
        //将数据塞入实体类中
        for (Score score : scoreList) {
            FailRequestCourse fail = new FailRequestCourse();
            fail.setSc_edu_year(score.getSc_edu_year());
            fail.setSc_edu_term(score.getSc_edu_term());
            fail.setSc_course_name(score.getSc_course_name());
            fail.setSc_score(score.getSc_score());
            failList.add(fail);
        }

        bCourseDependence.setFail(failList);


        //设置课程预警
        List<WarnCourse> warnList = new ArrayList<>();

        //比如a->b,c->b,d->e
        //某同学d科目挂科,则查询e科目是否有记录,如有记录,则舍弃;若没有记录,则直接用d的成绩推测e的成绩
        //某同学a科目挂科,则查询b科目是否有记录，如有记录，则舍弃;
        //若没有记录，则查询所有指向b的科目，发现有a和c,确保a和c都存在记录的情况下，b的成绩=1/2(a+c);
        //若c记录不存在，则b的成绩=a

        //取出挂科记录
        for (int i = 0; i < failList.size(); i++) {
            FailRequestCourse course = failList.get(i);
            //System.out.println("挂科成绩有" + course.getSc_course_name());

            //由BCourseDependenceMax取出course影响的课程名
            String targetCourse = "";
            for (int j = 0; j < dependence.getDependence().getEdges().size(); j++) {
                if (dependence.getDependence().getEdges().get(j).getSourceID().equals(course.getSc_course_name())) {
                    targetCourse = dependence.getDependence().getEdges().get(j).getTargetID();
                    //System.out.println("影响的课程名" + targetCourse);
                    break;
                }
            }

            //如果targetCourse的考试记录已经存在，则不对targetCourse做预测
            Score targetScore = scoreDao.getCourse(dependence.getSc_student_id(), targetCourse);
            if (null != targetScore) {
                //System.out.println("存在成绩" + targetCourse);
                continue;
            }
            //System.out.println("不存在成绩");

            //取出影响targetCourse所有的课程名
            List<String> sourceCourse = new ArrayList<>();
            for (int m = 0; m < dependence.getDependence().getEdges().size(); m++) {
                if (dependence.getDependence().getEdges().get(m).getTargetID().equals(targetCourse)) {
                    sourceCourse.add(dependence.getDependence().getEdges().get(m).getSourceID());
                    //System.out.println("影响" + targetCourse + "的课程为" + dependence.getDependence().getEdges().get(m).getSourceID());
                }
            }

            //抽取sourceCourse有考试记录的课程
            List<String> trueSourceCource = new ArrayList<>();
            for (int n = 0; n < sourceCourse.size(); n++) {
                Score temp = scoreDao.getCourse(dependence.getSc_student_id(), sourceCourse.get(n));
                if (null != temp) {
                    trueSourceCource.add(sourceCourse.get(n));
                    //System.out.println("有成绩的有" + sourceCourse.get(n));
                }
            }

            //查询trueSourceCource中课程的成绩,按相同的比例赋值给targetCourse
            int sum = 0;
            for (int k = 0; k < trueSourceCource.size(); k++) {
                Score score = scoreDao.getCourse(dependence.getSc_student_id(), trueSourceCource.get(k));
                try {
                    int sc_score = Integer.parseInt(score.getSc_score());
                    sum += sc_score;
                } catch (NumberFormatException e) {
                    //说明是非数字，
                    if (score.getSc_score().equals("不及格")) {
                        sum += 30;
                    } else if (score.getSc_score().equals("及格")) {
                        sum += 65;
                    } else {
                        sum += 25;
                    }

                }
            }

            //没有任何课程影响 挂科影响的目标课程   即此挂科课程->null
            if (trueSourceCource.size() == 0) {
                continue;
            }
            //得到最终的分数
            int average = sum / trueSourceCource.size();

            //取出级别
            String[] level = new String[]{
                    "最高(0-10)", "极高(10-20)", "高(20-30)", "较高(30-40)",
                    "中等(40-50)", "一般(50-60)", "低(60-70)", "较低(70-80)",
                    "极低(80-90)", "最低(90-100)", "不可能(100)"};

            //最终级别
            String finalLevel = level[average / 10];

            WarnCourse warnCourse = new WarnCourse();
            warnCourse.setSc_course_name(targetCourse);

            //查询课程学分
            Score temp = scoreDao.getCourseCredit(targetCourse);
            String credit = "";
            if (null != temp) {
                credit = temp.getSc_course_credit() + "";
            } else {
                credit = "暂无";
            }
            warnCourse.setSc_course_credit(credit);
            warnCourse.setLevel(finalLevel);
            warnList.add(warnCourse);

        }

        //对预警课程去重
        List<WarnCourse> finalWarnList = new ArrayList<>();
        for (int z = 0; z < warnList.size(); z++) {
            boolean flag = false;
            for (int x = 0; x < finalWarnList.size(); x++) {
                if (warnList.get(z).getSc_course_name().equals(finalWarnList.get(x).getSc_course_name())) {
                    flag = true;
                }
            }
            if (!flag) {
                finalWarnList.add(warnList.get(z));
            }
        }

        bCourseDependence.setWarn(finalWarnList);
        return bCourseDependence;
    }


    //--------------------------------------课程通过预警结束----------------------------------------
    //--------------------------------------研录取与入党预测开始-------------------------------------

    @Override
    public HashMap<String, String> getBPostGraduateOrPartyMember(String st_id) {
        HashMap<String, String> map = new HashMap<>();
        //获取绩点
        List<GPAOfStudent> list = scoreService.getAllGPA(st_id);
        double gpa = 0.0;
        for (GPAOfStudent temp : list) {
            gpa += temp.getValue();
        }
        gpa = DataFormatUtil.doubleFormat(gpa / list.size(), 1);
        String gpaStr = "";
        if (gpa > 3.0) {
            gpaStr = "A";
        } else if (gpa > 2.0) {
            gpaStr = "B";
        } else {
            gpaStr = "C";
        }
        map.put("gpa", gpaStr);

        //获取奖学金
        List<Scholarship> scholarshipList = scholarshipDao.findById(st_id);
        String scholarship = "";
        for (Scholarship temp : scholarshipList) {
            scholarship += temp.getSc_awards() + ",";
        }
        map.put("scholarship", scholarship);

        //获取竞赛成绩

        List<Competition> competitionList = competitionDao.findById(st_id);
        String competition = "";
        for (Competition temp : competitionList) {
            competition += temp.getCo_level() + ":" + temp.getCo_awards() + ",";
        }
        if (competitionList.size() != 0) {
            competition = competition.substring(0, competition.length() - 1);
        }

        map.put("competition", competition);

        return map;
    }

    //--------------------------------------考研录取与入党预测结束-------------------------------------
    //--------------------------------------成绩位置预测开始------------------------------------------
    @Override
    public BScorePosition getScorePosition(String st_id, String result) {

        BScorePosition bScorePosition = new BScorePosition();
        List<ExistCourse> existCourseList = new ArrayList<>();
        List<ForecastCourse> forecastCourseList = new ArrayList<>();
        bScorePosition.setExistCourseList(existCourseList);
        bScorePosition.setForecastCourseList(forecastCourseList);

        List<ScorePosition> scorePositionList = ResultUtil.getDataFromResult(result);

        //---------------------------已考过的核心必修课成绩位置开始------------------------------------
        List<Score> requireScoreList = scoreDao.getAllRequireCourseById(st_id);
        String sc_class_name = studentDao.findById(st_id).getSt_class();
        List<String> requireCourseNameList = new ArrayList<>();
        ExistCourse existCourse = null;
        for (Score temp : requireScoreList) {
            existCourse = new ExistCourse();
            existCourse.setExistCourse(temp.getSc_course_name());
            requireCourseNameList.add(temp.getSc_course_name());

            if (null == temp.getSc_gpa()) {
                existCourse.setGpa(0.00);
            } else {
                existCourse.setGpa(temp.getSc_gpa());
            }

            double avg_gpa = scoreDao.getAverageByClassAndCourse(sc_class_name, temp.getSc_course_name());
            existCourse.setAvgGpa(DataFormatUtil.doubleFormat(avg_gpa, 2));
            existCourse.setExistPosition(ResultUtil.getPosition(avg_gpa, temp.getSc_gpa()));
            existCourseList.add(existCourse);
            System.out.println(existCourse);
        }
        //---------------------------已考过的核心必修课成绩位置结束------------------------------------
        //---------------------------未考过的核心必修课成绩位置开始------------------------------------
        //未考过的课程
        List<String> unExistCourseList = new ArrayList<>();
        //第一条匹配到的规则集合
        List<ScorePosition> scorePositionList1 = new ArrayList<>();
        //将结果课程加入到未考过的课程集合中
        for (ScorePosition temp : scorePositionList) {
            String course = temp.getResultCourse();
            if ((!unExistCourseList.contains(course)) && !requireCourseNameList.contains(course)) {
                unExistCourseList.add(course);
                scorePositionList1.add(temp);
                //System.out.println("未考过" + course);
            }
        }
        //遍历规则集合
        for (ScorePosition temp : scorePositionList1) {
            //System.out.println("规则有" + temp);
            // System.out.println("此规则的前置课程的长度为" + temp.getPreCourse().size());
            List<String> preCoursePosition = new ArrayList<>();
            double gpa = 0.0;
            ForecastCourse forecastCourse = null;

            for (String preCourse : temp.getPreCourse()) {
                //System.out.println("此规则中使用的前置课程为" + preCourse);
                forecastCourse = new ForecastCourse();

                for (ExistCourse existCourseTemp : existCourseList) {
                    //System.out.println("此时前置的课程为" + preCourse);
                    //System.out.println("此时已考的课程为" + existCourseTemp.getExistCourse());
                    if (preCourse.equals(existCourseTemp.getExistCourse())) {
                        //System.out.println("课程" + preCourse + "在已考中");
                        preCoursePosition.add(existCourseTemp.getExistPosition());
                        gpa += existCourseTemp.getGpa();
                        //System.out.println("预测课程:" + temp.getResultCourse());
                        //System.out.print("前置课程:" + existCourseTemp.getExistCourse() + "绩点:" + existCourse.getGpa());
                        break;
                    }
                }
            }
            String resultPosition = ResultUtil.getPositionByPrePositon(preCoursePosition);
            if (preCoursePosition.size() == 0) {
                gpa = 0.0;
                //该规则的前置课程都没有考过
                continue;
            } else {
                gpa = DataFormatUtil.doubleFormat(gpa / preCoursePosition.size(), 2);
            }
            //System.out.println("gpa:" + gpa);
            //System.out.println("preCoursePosition.size():" + preCoursePosition.size());
            forecastCourse.setForecastCourse(temp.getResultCourse());
            forecastCourse.setForecastGpa(gpa);
            forecastCourse.setForecastPosition(resultPosition);
            forecastCourse.setConfidence(temp.getConfidence());
            forecastCourseList.add(forecastCourse);

        }

        //---------------------------未考过的核心必修课成绩位置结束------------------------------------
        return bScorePosition;
    }
}