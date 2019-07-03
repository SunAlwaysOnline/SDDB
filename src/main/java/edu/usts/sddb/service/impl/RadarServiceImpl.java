package edu.usts.sddb.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import edu.usts.sddb.dao.*;
import edu.usts.sddb.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.usts.sddb.service.RadarService;
import edu.usts.sddb.service.ScoreService;
import edu.usts.sddb.util.RadarUtil;

@Service("radarService")
public class RadarServiceImpl implements RadarService {

    @Autowired
    @Qualifier("competitionDao")
    CompetitionDao competitionDao;

    @Autowired
    @Qualifier("innovationTrainDao")
    InnovationTrainDao innovationTrainDao;

    @Autowired
    @Qualifier("scoreService")
    ScoreService scoreService;

    @Autowired
    @Qualifier("studentDao")
    StudentDao studentDao;

    @Autowired
    @Qualifier("adviceDao")
    AdviceDao adviceDao;

    @Autowired
    VolunteerDao volunteerDao;

    public List<Advice> getAdvice(int study, int sport, int sciense,int volunteer) {
        List<Advice> list = new ArrayList<Advice>();
        Advice a_study = adviceDao.findAdvice("学习", RadarUtil.getLevelByScore(study));
        list.add(a_study);
        Advice a_sport = adviceDao.findAdvice("体育", RadarUtil.getLevelByScore(sport));
        list.add(a_sport);
        Advice a_science = adviceDao.findAdvice("科研", RadarUtil.getLevelByScore(sciense));
        list.add(a_science);
        Advice a_volunteer=adviceDao.findAdvice("志愿",RadarUtil.getLevelByScore(volunteer));
        list.add(a_volunteer);
        return list;
    }

    public List<PartOfRadar> generaterRadar(String id) {
        // 声明放入各个维度的list
        List<PartOfRadar> list = new ArrayList<PartOfRadar>();
        // 生成学习成绩雷达图部分，并加入list
        PartOfRadar scorePart = generateRadarScore(id);
        list.add(scorePart);

        // 生成身体素质雷达图部分，并加入list
        PartOfRadar bodyPart = generateRadarBody(id);
        // System.out.println(bodyPart);
        list.add(bodyPart);

        // 生成科研情况雷达图部分，并加入list
        PartOfRadar scientificPart = generateRadarScience(id);
        list.add(scientificPart);

        //生成志愿服务雷达图部分
        PartOfRadar volunteerPart = generateRadarVolunteer(id);
        list.add(volunteerPart);

        // 返回list
        return list;
    }

    private PartOfRadar generateRadarScience(String id) {
        PartOfRadar por = new PartOfRadar();
        por.setRadarName("科研情况");
        Student stu = studentDao.findById(id);
        String name = stu.getSt_name();
        // 计算竞赛子量化成绩a
        int a = 0;
        List<Competition> list = competitionDao.findByName(name);

        int score1[][] = {{50, 40, 20, 20}, {28, 20, 10, 5}, {20, 10, 8, 3}};
        int score2[][] = {{30, 20, 16, 10, 8}, {30, 20, 16, 10, 10}};
        for (Competition c : list) {
            a += getScore(c, score1);
        }
        // 计算科研立项子量化成绩b
        int b = 0;
        List<InnovationTrain> l = innovationTrainDao.findByName(name);
        for (InnovationTrain i : l) {
            b += getScore(i, score2);
        }
        por.setRadarScore(a + b);
        if (a + b > 100) {
            por.setRadarScore(100);
        }
        // System.out.println(name + " a: " + a + "b: " + b);
        return por;
    }

    private int getScore(Object com, int[][] score) {
        if (score.length == 3) {
            Competition c = (Competition) com;
            if (c.getCo_level().contains("国家级")) {
                // return score[0][1];
                if (c.getCo_awards().contains("一等")) {
                    return score[0][0];
                } else if (c.getCo_awards().contains("二等")) {
                    return score[0][1];
                } else if (c.getCo_awards().contains("三等")) {
                    return score[0][2];
                } else {
                    return score[0][3];
                }
            } else if (c.getCo_level().contains("省级")) {
                if (c.getCo_awards().contains("一等")) {
                    return score[1][0];
                } else if (c.getCo_awards().contains("二等")) {
                    return score[1][1];
                } else if (c.getCo_awards().contains("三等")) {
                    return score[1][2];
                } else {
                    return score[1][3];
                }
            } else {
                if (c.getCo_awards().contains("一等")) {
                    return score[2][0];
                } else if (c.getCo_awards().contains("二等")) {
                    return score[2][1];
                } else if (c.getCo_awards().contains("三等")) {
                    return score[2][2];
                } else {
                    return score[2][3];
                }
            }
        }
        if (score.length == 2) {
            InnovationTrain it = (InnovationTrain) com;
            if (it.getIn_category().contains("创业")) {
                if (it.getIn_category().contains("实践")) {
                    return score[1][0];
                } else if (it.getIn_category().contains("训练")) {
                    if (it.getIn_category().contains("重点")) {
                        return score[1][1];
                    } else {
                        return score[1][2];
                    }
                }
            } else {
                if (it.getIn_category().contains("省")) {
                    if (it.getIn_category().contains("重点")) {
                        return score[0][0];
                    } else if (it.getIn_category().contains("一般")) {
                        return score[0][1];
                    } else if (it.getIn_category().contains("指导")) {
                        return score[0][2];
                    }
                } else if (it.getIn_category().contains("校级")) {
                    if (it.getIn_category().contains("指导")) {
                        return score[0][4];
                    } else {
                        return score[0][3];
                    }

                }
            }
        }
        return 0;
    }

    private PartOfRadar generateRadarBody(String id) {
        PartOfRadar por = new PartOfRadar();
        por.setRadarName("身体素质");
        List<Double> l = scoreService.getBodyScore(id);
        List<Integer> score = new ArrayList<Integer>();

        // 绩点与成绩的转化

        for (Double d : l) {
            if (null == d) {
                score.add(new Integer(0));
            } else if (d.toString().equals("1.5")) {
                score.add(new Integer(65));
            } else if (d.toString().equals("2.5")) {
                score.add(new Integer(75));
            } else if (d.toString().equals("3.5")) {
                score.add(new Integer(85));
            } else if (d.toString().equals("4.5")) {
                score.add(new Integer(95));
            } else {
                score.add(new Integer(0));
            }
        }
        double sum = 0;
        for (Integer i : score) {
            sum += i;
        }

        double averageScore = sum / l.size();
        DecimalFormat df = new DecimalFormat("######0");
        String strScore = df.format(averageScore);
        int bodyScore;
        try {
            bodyScore = Integer.parseInt(strScore);
        } catch (NumberFormatException e) {
            bodyScore = 0;
        }
        por.setRadarScore(bodyScore);
        return por;
    }

    //成绩量化指数
    private PartOfRadar generateRadarScore(String id) {
        PartOfRadar por = new PartOfRadar();
        por.setRadarName("学习成绩");
        List<GPAOfStudent> gpas = scoreService.getAllGPA(id);
        double sum = 0.0;
        for (GPAOfStudent gs : gpas) {
            sum += gs.getValue();
        }
        sum /= gpas.size();
        double averageGpa = sum / 4.5;
        double i = averageGpa * 100;
        DecimalFormat df = new DecimalFormat("######0");
        String strScore = df.format(i);
        int score;
        try {
            score = Integer.parseInt(strScore);
        } catch (NumberFormatException e) {
            score = 0;
        }
        por.setRadarScore(score);
        return por;

    }

    //志愿服务量化指数
    private PartOfRadar generateRadarVolunteer(String id) {
        PartOfRadar part = new PartOfRadar();
        part.setRadarName("志愿服务");
        double grade;
        try {
            //有可能不存在该学生的志愿服务数据，则默认时长为0
            grade = volunteerDao.findTimeById(id);
        } catch (Exception e) {
            grade = 0.00;
        }
        //默认比例为3倍
        grade = grade * 3;
        if (grade > 100) {
            grade = 100;
        }
        part.setRadarScore((int) grade);
        return part;

    }


}
