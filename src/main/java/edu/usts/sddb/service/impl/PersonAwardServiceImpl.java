package edu.usts.sddb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.usts.sddb.dao.CertificateDao;
import edu.usts.sddb.dao.CompetitionDao;
import edu.usts.sddb.dao.ExcellentStudentDao;
import edu.usts.sddb.dao.InnovationTrainDao;
import edu.usts.sddb.dao.PartyMemberDao;
import edu.usts.sddb.dao.PostGraduateDao;
import edu.usts.sddb.dao.PushExcellentDao;
import edu.usts.sddb.dao.ScholarshipDao;
import edu.usts.sddb.dao.StudentDao;
import edu.usts.sddb.entity.Certificate;
import edu.usts.sddb.entity.Competition;
import edu.usts.sddb.entity.ExcellentStudent;
import edu.usts.sddb.entity.InnovationTrain;
import edu.usts.sddb.entity.Award;
import edu.usts.sddb.entity.PartyMember;
import edu.usts.sddb.entity.PostGraduate;
import edu.usts.sddb.entity.PushExcellent;
import edu.usts.sddb.entity.Scholarship;
import edu.usts.sddb.entity.Student;
import edu.usts.sddb.service.PersonAwardService;
import edu.usts.sddb.util.CheckData;

//前期的想法是将个人所获得的所有奖项形成一个个人云图，因此取名叫PersonalCloud
//现在最准确叫法是个人获奖，应该为PersonAward
//最终已更正

@Service("personCloudService")
public class PersonAwardServiceImpl implements PersonAwardService {

    @Autowired
    @Qualifier("studentDao")
    StudentDao studentDao;

    @Autowired
    @Qualifier("certificateDao")
    CertificateDao certificateDao;

    @Autowired
    @Qualifier("competitionDao")
    CompetitionDao competitionDao;

    @Autowired
    @Qualifier("excellentStudentDao")
    ExcellentStudentDao excellentStudentDao;

    @Autowired
    @Qualifier("innovationTrainDao")
    InnovationTrainDao innovationTrainDao;

    @Autowired
    @Qualifier("postGraduateDao")
    PostGraduateDao postGraduateDao;

    @Autowired
    @Qualifier("scholarshipDao")
    ScholarshipDao scholarshipDao;

    @Autowired
    @Qualifier("partyMemberDao")
    PartyMemberDao partyMemberDao;

    @Autowired
    @Qualifier("pushExcellentDao")
    PushExcellentDao pushExcellentDao;

    /*
     * 此方法生成个人获奖的情况
     */
    public List<Award> generate(String id) {
        List<Award> list = new ArrayList<Award>();
        Student stu = studentDao.findById(id);
        String name = stu.getSt_name();

        list.addAll(certificate(id));
        list.addAll(competition(id, name));
        list.addAll(scholarship(id));
        list.addAll(excellentStu(id));
        list.addAll(innovationTrain(name));
        list.addAll(partyMember(name));
        list.addAll(pushExcellent(name));
        list.addAll(postGraduate(name));

        return list;
    }

    private List<Award> pushExcellent(String name) {
        List<Award> list = new ArrayList<Award>();
        List<PushExcellent> l = pushExcellentDao.findByName(name);
        for (PushExcellent c : l) {
            Award part = new Award();
            part.setName("已被推优");
            part.setYear(c.getPu_time());
            list.add(part);
        }
        return list;
    }

    private List<Award> partyMember(String name) {
        List<Award> list = new ArrayList<Award>();
        List<PartyMember> l = partyMemberDao.findByName(name);
        for (PartyMember c : l) {
            Award part = new Award();
            part.setName(c.getPa_state() + "党员");
            part.setYear(c.getPa_time());
            list.add(part);
        }
        return list;
    }

    private List<Award> scholarship(String id) {
        List<Award> list = new ArrayList<Award>();
        List<Scholarship> l = scholarshipDao.findById(id);

        for (Scholarship c : l) {
            Award part = new Award();
            String award = c.getSc_awards();
            if (award.trim().length() == 3) {
                award += "学金";
            }
            part.setName(award);
            part.setYear(c.getSc_year());
            list.add(part);
        }
        return list;
    }

    private List<Award> postGraduate(String name) {
        List<Award> list = new ArrayList<Award>();
        List<PostGraduate> l = postGraduateDao.findByname(name);

        for (PostGraduate c : l) {
            Award part = new Award();
            part.setName(c.getPo_new_school() + "研究生");
            part.setYear("毕业阶段");
            list.add(part);
        }
        return list;
    }

    private List<Award> innovationTrain(String name) {
        List<Award> list = new ArrayList<Award>();
        List<InnovationTrain> l = innovationTrainDao.findByName(name);

        for (InnovationTrain c : l) {
            Award part = new Award();
            part.setName(c.getIn_category() + c.getIn_name());
            part.setYear(c.getIn_year());
            list.add(part);
        }
        return list;
    }

    private List<Award> excellentStu(String id) {
        List<Award> list = new ArrayList<Award>();
        List<ExcellentStudent> l = excellentStudentDao.findById(id);

        for (ExcellentStudent c : l) {
            Award part = new Award();
            part.setName(c.getEx_honor());
            part.setYear(c.getEx_year());
            list.add(part);
        }
        return list;
    }

    private List<Award> competition(String id, String name) {
        List<Award> list = new ArrayList<Award>();
        List<Competition> l = competitionDao.findByName(name);
        // List<Competition> l1 = competitionDao.findByName(name);
        // l.addAll(l1);
        // List<Competition> realList = CheckData.checkCo(l);
        for (Competition c : l) {
            Award part = new Award();
            part.setName(c.getCo_name() + c.getCo_level() + c.getCo_awards());
            part.setYear(c.getCo_time_award());
            list.add(part);
        }
        return list;
    }

    private List<Award> certificate(String id) {
        List<Award> list = new ArrayList<Award>();
        List<Certificate> ll = certificateDao.findCeByCeid(id);
        List<Certificate> l = CheckData.checkCe(ll);
        for (Certificate c : l) {
            Award part = new Award();
            part.setName(c.getCe_name());
            part.setYear(c.getCe_time());
            list.add(part);
        }
        return list;
    }

}
