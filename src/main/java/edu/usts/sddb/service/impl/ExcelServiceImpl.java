package edu.usts.sddb.service.impl;


import edu.usts.sddb.constant.ExcelConstant;
import edu.usts.sddb.dao.*;
import edu.usts.sddb.entity.assist.DBComment;
import edu.usts.sddb.service.ExcelService;

import edu.usts.sddb.util.Excel.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("excelService")
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    AdviceDao adviceDao;

    @Autowired
    VolunteerDao volunteerDao;

    @Autowired
    DutyDao dutyDao;

    @Autowired
    ScholarshipDao scholarshipDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    ScoreDao scoreDao;

    @Autowired
    ExcellentStudentDao excellentStudentDao;

    @Autowired
    CertificateDao certificateDao;

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    MajorDao majorDao;

    @Autowired
    ClassroomDao classroomDao;

    @Autowired
    PostGraduateDao postGraduateDao;

    @Autowired
    PartyMemberDao partyMemberDao;

    @Autowired
    PushExcellentDao pushExcellentDao;

    @Autowired
    CompetitionDao competitionDao;

    @Autowired
    InnovationTrainDao innovationTrainDao;


    //获取导入数据的模版
    @Override
    public HSSFWorkbook getTemplate(String name) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = sheet.createRow(0);

        List<String> header = getExcelHeader(name);
        for (int i = 0; i < header.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(header.get(i));
        }

        ExcelUtil.setColumnSize(sheet);

        return workbook;
    }

    public List<String> getExcelHeader(String name) {
        List<DBComment> list = new ArrayList<>();
        int[] index = new int[30];
        List<String> result = new ArrayList<>();
        switch (name) {
            case "advice":
                list = adviceDao.findComment();
                index = ExcelConstant.ADVICE_INDEX;
                break;
            case "volunteer":
                list = volunteerDao.findComment();
                index = ExcelConstant.VOLUNTEER_INDEX;
                break;
            case "duty":
                list = dutyDao.findComment();
                index = ExcelConstant.DUTY_INDEX;
                break;
            case "scholarship":
                list = scholarshipDao.findComment();
                index = ExcelConstant.SCHOLARSHIP_INDEX;
                break;
            case "student":
                list = studentDao.findComment();
                index = ExcelConstant.STUDENT_INDEX;
                break;
            case "score":
                list = scoreDao.findComment();
                index = ExcelConstant.SCORE_INDEX;
                break;
            case "excellentStudent":
                list = excellentStudentDao.findComment();
                index = ExcelConstant.EXCELLENT_STUDENT_INDEX;
                break;
            case "certificate":
                list = certificateDao.findComment();
                index = ExcelConstant.CERTIFICATE_INDEX;
                break;
            case "teacher":
                list = teacherDao.findComment();
                index = ExcelConstant.TEACHER_INDEX;
                break;
            case "major":
                list = majorDao.findComment();
                index = ExcelConstant.MAJOR_INDEX;
                break;
            case "classroom":
                list = classroomDao.findComment();
                index = ExcelConstant.CLASSROOM_INDEX;
                break;
            case "postGraduate":
                list = postGraduateDao.findComment();
                index = ExcelConstant.POST_GRADUATE;
                break;
            case "partyMember":
                list = partyMemberDao.findComment();
                index = ExcelConstant.PARTY_MEMBER;
                break;
            case "pushExcellent":
                list = pushExcellentDao.findComment();
                index = ExcelConstant.PUSH_EXCELEENT;
                break;
            case "competition":
                list = competitionDao.findComment();
                index = ExcelConstant.COMPETITION;
                break;
            case "innovationTrain":
                list = innovationTrainDao.findComment();
                index = ExcelConstant.INNOVATION_TRAIN;
                break;


        }
        for (int i = 0; i < index.length; i++) {
            result.add(list.get(index[i]).getComment());
        }
        return result;
    }


}
