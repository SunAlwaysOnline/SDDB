package edu.usts.sddb.service.impl;

import edu.usts.sddb.dao.DormDao;
import edu.usts.sddb.dao.StudentDao;
import edu.usts.sddb.entity.Dorm;
import edu.usts.sddb.entity.PartOfRadar;
import edu.usts.sddb.entity.Student;
import edu.usts.sddb.entity.pack.ClassroomState;
import edu.usts.sddb.entity.pack.DormMemberScore;
import edu.usts.sddb.entity.pack.DormState;
import edu.usts.sddb.service.DormService;
import edu.usts.sddb.service.RadarService;
import edu.usts.sddb.service.StudentService;
import edu.usts.sddb.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DormServiceImpl implements DormService {

    @Autowired
    DormDao dormDao;

    @Autowired
    RadarService radarService;

    @Autowired
    StudentDao studentDao;

    @Override
    public List<String> findDistinctDorm() {
        return dormDao.findDistinctDorm();
    }

    @Override
    public List<Dorm> findByDormName(String d_name) {
        return dormDao.findByDormName(d_name);
    }


    //根据宿舍名生成宿舍成员情况图
    @Override
    public List<DormMemberScore> getDormMemberScore(String d_name) {
        List<Dorm> dormList = findByDormName(d_name);
        List<DormMemberScore> scoreList = new ArrayList<>();
        for (Dorm dorm : dormList) {
            DormMemberScore memberScore = new DormMemberScore();
            memberScore.setD_name(dorm.getD_name());
            memberScore.setD_no(dorm.getD_no());
            memberScore.setD_st_id(dorm.getD_st_id());

            Student student = studentDao.findById(dorm.getD_st_id());

            //从宿舍表中获取到学生id可能不存在于学生表中，很奇怪...
            if (null == student) {
                continue;
            }

            memberScore.setD_st_sex(student.getSt_sex());
            memberScore.setD_st_name(dorm.getD_st_name());
            memberScore.setD_st_class(dorm.getD_st_class());
            List<PartOfRadar> radarList = radarService.generaterRadar(dorm.getD_st_id());
            memberScore.setScorePart(radarList.get(0).getRadarScore());
            memberScore.setBodyPart(radarList.get(1).getRadarScore());
            memberScore.setScientificPart(radarList.get(2).getRadarScore());
            memberScore.setVolunteerPart(radarList.get(3).getRadarScore());
            int all = 0;
            for (int i = 0; i < 4; i++) {
                all += radarList.get(i).getRadarScore();
            }
            memberScore.setAll(all);
            scoreList.add(memberScore);
        }
        return scoreList;
    }

    //获取指定宿舍分数状况
    @Override
    public DormState getOneDormState(String d_name) {

        DormState dormState = new DormState();
        List<DormMemberScore> scoreList = getDormMemberScore(d_name);

        int num = 0;
        int score = 0;
        int body = 0;
        int scientific = 0;
        int volunteer = 0;

        for (DormMemberScore memberScore : scoreList) {
            num++;
            score += memberScore.getScorePart();
            body += memberScore.getBodyPart();
            scientific += memberScore.getScientificPart();
            volunteer += memberScore.getVolunteerPart();
        }

        //此宿舍中的学生不存在于学生表中
        if (scoreList.size() == 0) {
            return null;
        }
        dormState.setName(d_name);
        dormState.setNum(num);
        dormState.setScore(score / num);
        dormState.setBody(body / num);
        dormState.setScientific(scientific / num);
        dormState.setVolunteer(volunteer / num);
        dormState.setAll(score / num + body / num + scientific / num + volunteer / num);

        return dormState;
    }

    //获取全体宿舍分数状况
    //这个步骤耗时严重
    @Override
    public List<DormState> getAllDormState() {
        List<String> dormList = findDistinctDorm();
        List<DormState> dormStateList = new ArrayList<>();

        for (String name : dormList) {
            DormState dormState = getOneDormState(name);
            if (null == dormState) {
                continue;
            }
            dormStateList.add(dormState);
        }
        return dormStateList;
    }


    //获取指定宿舍的平均分与总分排名
    @Override
    public Map<String, String> getDormRank(String d_name) {
        Map<String, String> map = new LinkedHashMap<>();

        int score = 0;
        int body = 0;
        int scientific = 0;
        int volunteer = 0;
        int all = 0;

        List<DormState> dormStateList = getAllDormState();

        //根据学习成绩排名
        Collections.sort(dormStateList, new Comparator<DormState>() {
            @Override
            public int compare(DormState o1, DormState o2) {
                return o2.getScore() - o1.getScore();
            }
        });

        for (int i = 0; i < dormStateList.size(); i++) {
            if (d_name.equals(dormStateList.get(i).getName())) {
                score = i + 1;
                break;
            }
        }

        //根据体育排名
        Collections.sort(dormStateList, new Comparator<DormState>() {
            @Override
            public int compare(DormState o1, DormState o2) {
                return o2.getBody() - o1.getBody();
            }
        });

        for (int i = 0; i < dormStateList.size(); i++) {
            if (d_name.equals(dormStateList.get(i).getName())) {
                body = i + 1;
                break;
            }
        }

        //根据科研排名
        Collections.sort(dormStateList, new Comparator<DormState>() {
            @Override
            public int compare(DormState o1, DormState o2) {
                return o2.getScientific() - o1.getScientific();
            }
        });

        for (int i = 0; i < dormStateList.size(); i++) {
            if (d_name.equals(dormStateList.get(i).getName())) {
                scientific = i + 1;
                break;
            }
        }

        //根据志愿排名
        Collections.sort(dormStateList, new Comparator<DormState>() {
            @Override
            public int compare(DormState o1, DormState o2) {
                return o2.getVolunteer() - o1.getVolunteer();
            }
        });

        for (int i = 0; i < dormStateList.size(); i++) {
            if (d_name.equals(dormStateList.get(i).getName())) {
                volunteer = i + 1;
                break;
            }
        }

        //根据总评平均排名
        Collections.sort(dormStateList, new Comparator<DormState>() {
            @Override
            public int compare(DormState o1, DormState o2) {
                return o2.getAll() - o1.getAll();
            }
        });

        for (int i = 0; i < dormStateList.size(); i++) {
            if (d_name.equals(dormStateList.get(i).getName())) {
                all = i + 1;
                break;
            }
        }

        map.put("score", score + "/" + dormStateList.size());
        map.put("body", body + "/" + dormStateList.size());
        map.put("scientific", scientific + "/" + dormStateList.size());
        map.put("volunteer", volunteer + "/" + dormStateList.size());
        map.put("all", all + "/" + dormStateList.size());

        return map;
    }


}
