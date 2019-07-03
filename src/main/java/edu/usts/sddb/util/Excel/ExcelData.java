package edu.usts.sddb.util.Excel;

import edu.usts.sddb.entity.*;
import org.apache.http.util.TextUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelData {

    public static List<Object> getDataByExcel(InputStream is, String name) throws Exception {
        List<Object> objectList = new ArrayList<>();
        HSSFWorkbook workbook = new HSSFWorkbook(is);
        int sheetSum = workbook.getNumberOfSheets();
        if (sheetSum > 1 || sheetSum == 0) {
            throw new Exception("请在第一个sheet页存放数据...");
        }
        HSSFSheet sheet = workbook.getSheetAt(0);
        //遍历sheet中的所有行,跳过第一行
        for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
            HSSFRow row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            switch (name) {
                case "advice":
                    objectList.add(getAdviceData(row));
                    break;
                case "volunteer":
                    objectList.add(getVolunteerData(row));
                    break;
                case "duty":
                    objectList.add(getDutyData(row));
                    break;
                case "scholarship":
                    objectList.add(getScholarshipData(row));
                    break;
                case "student":
                    objectList.add(getStudentData(row));
                    break;
                case "score":
                    objectList.add(getScoreData(row));
                    break;
                case "excellentStudent":
                    objectList.add(getExcellentStudentData(row));
                    break;
                case "certificate":
                    objectList.add(getCertificateData(row));
                    break;
                case "teacher":
                    objectList.add(getTeacherData(row));
                    break;
                case "major":
                    objectList.add(getMajorData(row));
                    break;
                case "classroom":
                    objectList.add(getClassroomData(row));
                    break;
                case "postGraduate":
                    objectList.add(getPostGraduateData(row));
                    break;
                case "partyMember":
                    objectList.add(getPartyMemberData(row));
                    break;
                case "pushExcellent":
                    objectList.add(getPushExcellentData(row));
                    break;
                case "competition":
                    objectList.add(getCompetitionData(row));
                    break;
                case "innovationTrain":
                    objectList.add(getInnovationTrainData(row));
                    break;

            }


        }
        return objectList;
    }

    public static Advice getAdviceData(HSSFRow row) {
        Advice advice = new Advice();
        String ad_category = (String) ExcelUtil.getFormatCellValue(row.getCell(0));
        advice.setAd_category(ad_category);
        String ad_level = (String) ExcelUtil.getFormatCellValue(row.getCell(1));
        advice.setAd_level(ad_level);
        String ad_suggestion = (String) ExcelUtil.getFormatCellValue(row.getCell(2));
        advice.setAd_suggestion(ad_suggestion);
        return advice;
    }

    public static Volunteer getVolunteerData(HSSFRow row) {
        Volunteer volunteer = new Volunteer();
        String v_student_id = (String) ExcelUtil.getFormatCellValue(row.getCell(0));
        volunteer.setV_student_id(v_student_id);
        String v_student_name = (String) ExcelUtil.getFormatCellValue(row.getCell(1));
        volunteer.setV_student_name(v_student_name);
        double v_time = Double.parseDouble((String) ExcelUtil.getFormatCellValue(row.getCell(2)));
        volunteer.setV_time(v_time);
        return volunteer;
    }

    public static Duty getDutyData(HSSFRow row) {
        Duty duty = new Duty();
        String du_student_id = (String) ExcelUtil.getFormatCellValue(row.getCell(0));
        duty.setDu_student_id(du_student_id);
        String du_student_name = (String) ExcelUtil.getFormatCellValue(row.getCell(1));
        duty.setDu_student_name(du_student_name);
        String du_time = (String) ExcelUtil.getFormatCellValue(row.getCell(2));
        duty.setDu_time(du_time);
        String du_name = (String) ExcelUtil.getFormatCellValue(row.getCell(3));
        duty.setDu_name(du_name);
        return duty;
    }

    public static Scholarship getScholarshipData(HSSFRow row) {
        Scholarship scholarship = new Scholarship();
        String sc_student_id = (String) ExcelUtil.getFormatCellValue(row.getCell(0));
        scholarship.setSc_student_id(sc_student_id);
        String sc_student_name = (String) ExcelUtil.getFormatCellValue(row.getCell(1));
        scholarship.setSc_student_name(sc_student_name);
        String sc_student_major = (String) ExcelUtil.getFormatCellValue(row.getCell(2));
        scholarship.setSc_student_major(sc_student_major);
        String sc_year = (String) ExcelUtil.getFormatCellValue(row.getCell(3));
        scholarship.setSc_year(sc_year);
        String sc_awards = (String) ExcelUtil.getFormatCellValue(row.getCell(4));
        scholarship.setSc_awards(sc_awards);
        int sc_money = Integer.parseInt((String) ExcelUtil.getFormatCellValue(row.getCell(5)));
        scholarship.setSc_money(sc_money);
        return scholarship;
    }

    public static Student getStudentData(HSSFRow row) {
        Student student = new Student();
        String st_id = (String) ExcelUtil.getFormatCellValue(row.getCell(0));
        student.setSt_id(st_id);
        String st_name = (String) ExcelUtil.getFormatCellValue(row.getCell(1));
        student.setSt_name(st_name);
        String st_sex = (String) ExcelUtil.getFormatCellValue(row.getCell(2));
        student.setSt_sex(st_sex);
        String st_major = (String) ExcelUtil.getFormatCellValue(row.getCell(3));
        student.setSt_major(st_major);
        String st_class = (String) ExcelUtil.getFormatCellValue(row.getCell(4));
        student.setSt_class(st_class);
        String grade = (String) ExcelUtil.getFormatCellValue(row.getCell(5));
        student.setSt_grade(grade);
        return student;
    }

    public static Score getScoreData(HSSFRow row) {
        Score score = new Score();
        String sc_student_id = (String) ExcelUtil.getFormatCellValue(row.getCell(0));
        score.setSc_student_id(sc_student_id);
        String sc_student_name = (String) ExcelUtil.getFormatCellValue(row.getCell(1));
        score.setSc_student_name(sc_student_name);
        String sc_course_name = (String) ExcelUtil.getFormatCellValue(row.getCell(2));
        score.setSc_course_name(sc_course_name);
        Double sc_course_credit = Double.parseDouble((String) ExcelUtil.getFormatCellValue(row.getCell(3)));
        score.setSc_course_credit(sc_course_credit);
        String sc_course_nature = (String) ExcelUtil.getFormatCellValue(row.getCell(4));
        score.setSc_course_nature(sc_course_nature);
        String sc_score = (String) ExcelUtil.getFormatCellValue(row.getCell(5));
        score.setSc_score(sc_score);
        String sc_remark = (String) ExcelUtil.getFormatCellValue(row.getCell(6));
        score.setSc_remark(sc_remark);
        String sc_gpa = (String) ExcelUtil.getFormatCellValue(row.getCell(7));
        if (TextUtils.isEmpty(sc_gpa)) {
            score.setSc_gpa(null);
        } else {
            score.setSc_gpa(Double.parseDouble(sc_gpa));
        }
        String sc_make_up_score = (String) ExcelUtil.getFormatCellValue(row.getCell(8));
        score.setSc_make_up_score(sc_make_up_score);
        String sc_rebuild_score = (String) ExcelUtil.getFormatCellValue(row.getCell(9));
        score.setSc_rebuild_score(sc_rebuild_score);
        String sc_class_name = (String) ExcelUtil.getFormatCellValue(row.getCell(10));
        score.setSc_class_name(sc_class_name);
        String sc_course_belong = (String) ExcelUtil.getFormatCellValue(row.getCell(11));
        score.setSc_course_belong(sc_course_belong);
        String sc_rebuild_mark = (String) ExcelUtil.getFormatCellValue(row.getCell(12));
        if (TextUtils.isEmpty(sc_rebuild_mark)) {
            score.setSc_rebuild_mark(null);
        } else {
            score.setSc_rebuild_mark(Integer.parseInt(sc_rebuild_mark));
        }
        String sc_select_course_id = (String) ExcelUtil.getFormatCellValue(row.getCell(13));
        score.setSc_select_course_id(sc_select_course_id);
        String sc_edu_year = (String) ExcelUtil.getFormatCellValue(row.getCell(14));
        score.setSc_edu_year(sc_edu_year);
        String sc_edu_term = (String) ExcelUtil.getFormatCellValue(row.getCell(15));
        score.setSc_edu_term(sc_edu_term);
        return score;

    }

    public static ExcellentStudent getExcellentStudentData(HSSFRow row) {
        ExcellentStudent excellentStudent = new ExcellentStudent();
        String ex_student_id = (String) ExcelUtil.getFormatCellValue(row.getCell(0));
        excellentStudent.setEx_student_id(ex_student_id);
        String ex_student_name = (String) ExcelUtil.getFormatCellValue(row.getCell(1));
        excellentStudent.setEx_student_name(ex_student_name);
        String ex_year = (String) ExcelUtil.getFormatCellValue(row.getCell(2));
        excellentStudent.setEx_year(ex_year);
        String ex_honor = (String) ExcelUtil.getFormatCellValue(row.getCell(3));
        excellentStudent.setEx_honor(ex_honor);
        return excellentStudent;
    }

    public static Certificate getCertificateData(HSSFRow row) {
        Certificate certificate = new Certificate();
        String ce_student_id = (String) ExcelUtil.getFormatCellValue(row.getCell(0));
        certificate.setCe_student_id(ce_student_id);
        String ce_student_name = (String) ExcelUtil.getFormatCellValue(row.getCell(1));
        certificate.setCe_student_name(ce_student_name);
        String ce_edu_year = (String) ExcelUtil.getFormatCellValue(row.getCell(2));
        certificate.setCe_edu_year(ce_edu_year);
        String ce_edu_term = (String) ExcelUtil.getFormatCellValue(row.getCell(3));
        certificate.setCe_edu_term(ce_edu_term);
        String ce_name = (String) ExcelUtil.getFormatCellValue(row.getCell(4));
        certificate.setCe_name(ce_name);
        String ce_time = (String) ExcelUtil.getFormatCellValue(row.getCell(5));
        certificate.setCe_time(ce_time);
        String ce_score = (String) ExcelUtil.getFormatCellValue(row.getCell(6));
        certificate.setCe_score(ce_score);
        return certificate;
    }

    public static Teacher getTeacherData(HSSFRow row) {
        Teacher teacher = new Teacher();
        String te_id = (String) ExcelUtil.getFormatCellValue(row.getCell(0));
        teacher.setTe_id(te_id);
        String te_name = (String) ExcelUtil.getFormatCellValue(row.getCell(1));
        teacher.setTe_name(te_name);
        String te_sex = (String) ExcelUtil.getFormatCellValue(row.getCell(2));
        teacher.setTe_sex(te_sex);
        String te_birth = (String) ExcelUtil.getFormatCellValue(row.getCell(3));
        teacher.setTe_birth(te_birth);
        String te_department = (String) ExcelUtil.getFormatCellValue(row.getCell(4));
        teacher.setTe_department(te_department);
        String te_phone = (String) ExcelUtil.getFormatCellValue(row.getCell(5));
        teacher.setTe_phone(te_phone);
        String te_email = (String) ExcelUtil.getFormatCellValue(row.getCell(6));
        teacher.setTe_email(te_email);
        String te_edu_background = (String) ExcelUtil.getFormatCellValue(row.getCell(7));
        teacher.setTe_edu_background(te_edu_background);
        String te_edu_degree = (String) ExcelUtil.getFormatCellValue(row.getCell(8));
        teacher.setTe_edu_degree(te_edu_degree);
        String te_pro_title = (String) ExcelUtil.getFormatCellValue(row.getCell(9));
        teacher.setTe_pro_title(te_pro_title);
        String te_politics_status = (String) ExcelUtil.getFormatCellValue(row.getCell(10));
        teacher.setTe_politics_status(te_politics_status);
        return teacher;

    }

    public static Major getMajorData(HSSFRow row) {
        Major major = new Major();
        String ma_id = (String) ExcelUtil.getFormatCellValue(row.getCell(0));
        major.setMa_id(ma_id);
        String ma_name = (String) ExcelUtil.getFormatCellValue(row.getCell(1));
        major.setMa_name(ma_name);
        String ma_eng = (String) ExcelUtil.getFormatCellValue(row.getCell(2));
        major.setMa_eng(ma_eng);
        int ma_academy_id = Integer.parseInt((String) ExcelUtil.getFormatCellValue(row.getCell(3)));
        major.setMa_academy_id(ma_academy_id);
        String ma_category = (String) ExcelUtil.getFormatCellValue(row.getCell(4));
        major.setMa_category(ma_category);
        int ma_edu_len = Integer.parseInt((String) ExcelUtil.getFormatCellValue(row.getCell(5)));
        major.setMa_edu_len(ma_edu_len);
        String ma_edu_degree = (String) ExcelUtil.getFormatCellValue(row.getCell(6));
        major.setMa_edu_degree(ma_edu_degree);
        String ma_level = (String) ExcelUtil.getFormatCellValue(row.getCell(7));
        major.setMa_level(ma_level);
        return major;
    }

    public static Classroom getClassroomData(HSSFRow row) {
        Classroom classroom = new Classroom();
        String cl_id = (String) ExcelUtil.getFormatCellValue(row.getCell(0));
        classroom.setCl_id(cl_id);
        String cl_name = (String) ExcelUtil.getFormatCellValue(row.getCell(1));
        classroom.setCl_name(cl_name);
        int cl_person_num = Integer.parseInt((String) ExcelUtil.getFormatCellValue(row.getCell(2)));
        classroom.setCl_person_num(cl_person_num);
        String cl_major_id = (String) ExcelUtil.getFormatCellValue(row.getCell(3));
        classroom.setCl_major_id(cl_major_id);
        int cl_edu_len = Integer.parseInt((String) ExcelUtil.getFormatCellValue(row.getCell(4)));
        classroom.setCl_edu_len(cl_edu_len);
        int cl_grade = Integer.parseInt((String) ExcelUtil.getFormatCellValue(row.getCell(5)));
        classroom.setCl_grade(cl_grade);
        String cl_instructor_name = (String) ExcelUtil.getFormatCellValue(row.getCell(6));
        classroom.setCl_instructor_name(cl_instructor_name);
        String cl_instructor_phone = (String) ExcelUtil.getFormatCellValue(row.getCell(7));
        classroom.setCl_instructor_phone(cl_instructor_phone);
        String cl_edu_form = (String) ExcelUtil.getFormatCellValue(row.getCell(8));
        classroom.setCl_edu_form(cl_edu_form);
        String cl_major = (String) ExcelUtil.getFormatCellValue(row.getCell(9));
        classroom.setCl_major(cl_major);
        return classroom;

    }

    public static PostGraduate getPostGraduateData(HSSFRow row) {
        PostGraduate postGraduate = new PostGraduate();
        String po_student_name = (String) ExcelUtil.getFormatCellValue(row.getCell(0));
        postGraduate.setPo_student_name(po_student_name);
        String po_politics_status = (String) ExcelUtil.getFormatCellValue(row.getCell(1));
        postGraduate.setPo_politics_status(po_politics_status);
        int po_exam_score = Integer.parseInt((String) ExcelUtil.getFormatCellValue(row.getCell(2)));
        postGraduate.setPo_exam_score(po_exam_score);
        String po_old_major = (String) ExcelUtil.getFormatCellValue(row.getCell(3));
        postGraduate.setPo_old_major(po_old_major);
        String po_new_school = (String) ExcelUtil.getFormatCellValue(row.getCell(4));
        postGraduate.setPo_new_school(po_new_school);
        String po_new_major = (String) ExcelUtil.getFormatCellValue(row.getCell(5));
        postGraduate.setPo_new_major(po_new_major);
        return postGraduate;
    }

    public static PartyMember getPartyMemberData(HSSFRow row) {
        PartyMember partyMember = new PartyMember();
        String pa_student_id = (String) ExcelUtil.getFormatCellValue(row.getCell(0));
        partyMember.setPa_student_id(pa_student_id);
        String pa_student_name = (String) ExcelUtil.getFormatCellValue(row.getCell(1));
        partyMember.setPa_student_name(pa_student_name);
        String pa_branch_name = (String) ExcelUtil.getFormatCellValue(row.getCell(2));
        partyMember.setPa_branch_name(pa_branch_name);
        String pa_student_sex = (String) ExcelUtil.getFormatCellValue(row.getCell(3));
        partyMember.setPa_student_sex(pa_student_sex);
        String pa_time = (String) ExcelUtil.getFormatCellValue(row.getCell(4));
        partyMember.setPa_time(pa_time);
        String pa_state = (String) ExcelUtil.getFormatCellValue(row.getCell(5));
        partyMember.setPa_state(pa_state);
        String pa_grade = (String) ExcelUtil.getFormatCellValue(row.getCell(6));
        partyMember.setPa_grade(pa_grade);
        String pa_class_name = (String) ExcelUtil.getFormatCellValue(row.getCell(7));
        partyMember.setPa_class_name(pa_class_name);
        String pa_branch_secretary = (String) ExcelUtil.getFormatCellValue(row.getCell(8));
        partyMember.setPa_branch_secretary(pa_branch_secretary);
        return partyMember;
    }

    public static PushExcellent getPushExcellentData(HSSFRow row) {
        PushExcellent pushExcellent = new PushExcellent();
        String pu_student_id = (String) ExcelUtil.getFormatCellValue(row.getCell(0));
        pushExcellent.setPu_student_id(pu_student_id);
        String pu_student_name = (String) ExcelUtil.getFormatCellValue(row.getCell(1));
        pushExcellent.setPu_student_name(pu_student_name);
        String pu_class_name = (String) ExcelUtil.getFormatCellValue(row.getCell(2));
        pushExcellent.setPu_class_name(pu_class_name);
        String pu_time = (String) ExcelUtil.getFormatCellValue(row.getCell(3));
        pushExcellent.setPu_time(pu_time);
        String pu_hold_duty = (String) ExcelUtil.getFormatCellValue(row.getCell(4));
        pushExcellent.setPu_hold_duty(pu_hold_duty);
        String pu_scholarship = (String) ExcelUtil.getFormatCellValue(row.getCell(5));
        pushExcellent.setPu_scholarship(pu_scholarship);
        String pu_is_fail_exam = (String) ExcelUtil.getFormatCellValue(row.getCell(6));
        pushExcellent.setPu_is_fail_exam(pu_is_fail_exam);
        return pushExcellent;
    }

    public static Competition getCompetitionData(HSSFRow row) {
        Competition competition = new Competition();
        String co_student_id = (String) ExcelUtil.getFormatCellValue(row.getCell(0));
        competition.setCo_student_id(co_student_id);
        String co_student_name = (String) ExcelUtil.getFormatCellValue(row.getCell(1));
        competition.setCo_student_name(co_student_name);
        String co_major_name = (String) ExcelUtil.getFormatCellValue(row.getCell(2));
        competition.setCo_major_name(co_major_name);
        String co_name = (String) ExcelUtil.getFormatCellValue(row.getCell(3));
        competition.setCo_name(co_name);
        String co_level = (String) ExcelUtil.getFormatCellValue(row.getCell(4));
        competition.setCo_level(co_level);
        String co_awards = (String) ExcelUtil.getFormatCellValue(row.getCell(5));
        competition.setCo_awards(co_awards);
        String co_sponsor = (String) ExcelUtil.getFormatCellValue(row.getCell(6));
        competition.setCo_sponsor(co_sponsor);
        String co_time_award = (String) ExcelUtil.getFormatCellValue(row.getCell(7));
        competition.setCo_time_award(co_time_award);
        String co_teachers = (String) ExcelUtil.getFormatCellValue(row.getCell(8));
        competition.setCo_teachers(co_teachers);
        return competition;
    }

    public static InnovationTrain getInnovationTrainData(HSSFRow row) {
        InnovationTrain innovationTrain = new InnovationTrain();
        String in_year = (String) ExcelUtil.getFormatCellValue(row.getCell(0));
        innovationTrain.setIn_year(in_year);
        String in_name = (String) ExcelUtil.getFormatCellValue(row.getCell(1));
        innovationTrain.setIn_name(in_name);
        String in_category = (String) ExcelUtil.getFormatCellValue(row.getCell(2));
        innovationTrain.setIn_category(in_category);
        String in_students = (String) ExcelUtil.getFormatCellValue(row.getCell(3));
        innovationTrain.setIn_students(in_students);
        String in_teachers = (String) ExcelUtil.getFormatCellValue(row.getCell(4));
        innovationTrain.setIn_teachers(in_teachers);

        return innovationTrain;
    }
}
