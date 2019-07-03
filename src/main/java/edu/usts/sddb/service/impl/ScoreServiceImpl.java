package edu.usts.sddb.service.impl;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.usts.sddb.entity.pack.*;
import edu.usts.sddb.service.ExcelService;
import edu.usts.sddb.util.*;
import edu.usts.sddb.util.Excel.ExcelData;
import edu.usts.sddb.util.Excel.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import edu.usts.sddb.dao.ClassroomDao;
import edu.usts.sddb.dao.ScoreDao;
import edu.usts.sddb.dao.StudentDao;
import edu.usts.sddb.entity.GPAOfStudent;
import edu.usts.sddb.entity.Score;
import edu.usts.sddb.entity.Student;
import edu.usts.sddb.service.ScoreService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service("scoreService")
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    @Qualifier("scoreDao")
    ScoreDao scoreDao;

    @Autowired
    @Qualifier("classroomDao")
    ClassroomDao classroomDao;

    @Autowired
    @Qualifier("studentDao")
    StudentDao studentDao;

    @Autowired
    ExcelService excelService;

    public Map<String, Double> gradeRate(int grade, String term) {
        Map<String, Double> map = new LinkedHashMap();
        List<String> term_list = new ArrayList<String>();
        String str = grade + "-" + (grade + 1) + "-1";
        int now_grade = grade;
        while (ScoreUtil.joinExecute(str, term)) {
            term_list.add(str);
            if (str.endsWith("1")) {
                str = str.substring(0, str.length() - 1) + "2";
            } else {
                now_grade++;
                str = now_grade + "-" + (now_grade + 1) + "-1";
            }
        }

        // 利用dao查询出所有学期
        // List<String> terms = scoreDao.findAllTerm();

        // 根据传入的term字段确定此年级要加入计算的学期
        // List<String> exeTerms = relatedTerms(term, terms);

        // 遍历要计算的学期
        for (String tempTerm : term_list) {
            int sum = 0;
            int sum_ungain = 0;
            List<ClassUngain> list = ungainScore(grade, tempTerm);
            for (ClassUngain cu : list) {
                sum += cu.getStuNum();
                sum_ungain += cu.getUngainStuNum();
            }
            double temp_d = DataFormatUtil.doubleFormat((double) sum_ungain / sum, 3);
            map.put(tempTerm, temp_d);
        }
        return map;
    }

    // // terms为所有学期，term为终止的学期
    // private List<String> relatedTerms(String term, List<String> terms) {
    // List<String> exeTerms = new ArrayList<String>();
    // for (String temp : terms) {
    // if (joinExecute(temp, term)) {
    // exeTerms.add(temp);
    // }
    // }
    // return exeTerms;
    // }

    /*
     * (non-Javadoc)
     *
     * @see edu.usts.sddb.service.ScoreService#ungainScore(int,
     * java.lang.String) year：年级 term：学期
     */
    public List<ClassUngain> ungainScore(int year, String term) {
        List<ClassUngain> list = new ArrayList<ClassUngain>();
        // 通过年级year得到专业majors
        List<String> majors = classroomDao.findMajorByYear(year);
        // 遍历专业，的到每个专业人数
        for (String major : majors) {
            ClassUngain cu = new ClassUngain();
            // 设置封装类专业属性
            cu.setClassName(major);

            List<String> stus = studentDao.findStuIdByMajorAndYear(major, year);
            int num = stus.size();

            // 此时可能出现有这个专业，但人数为0的情况
            if (num == 0) {
                // 跳出本次循环
                continue;
            }
            // 设置封装类学生人数属性

            cu.setStuNum(num);

            // sum标志学分拖欠学生人数
            int sum = 0;
            // 遍历学生 查询此学生是否在此学期前（包含该学期）有学分积欠
            for (String id : stus) {
                double credit = ungainCredit(id, term);
                if (credit > 0.0) {
                    sum++;
                }
            }
            cu.setUngainStuNum(sum);

            double temp = DataFormatUtil.doubleFormat((double) sum / num, 3);

            cu.setUngainRate(temp);
            list.add(cu);
        }

        return list;
    }

    private double ungainCredit(String id, String term) {
        double ungain_credit = 0.0;
        // 该学生通过的的成绩
        List<Score> scores = scoreDao.findScore1(id);
        // 该学生重修科目的成绩
        List<Score> scores_rebuild = scoreDao.findRebuildScore(id);

        for (Score s : scores) {
            // 判断此成绩是否该加入计算
            boolean b = ScoreUtil.joinExecute(s.getSc_edu_year() + s.getSc_edu_term(), term);
            if (b) {
                if (null == s.getSc_gpa()) {
                    s.setSc_gpa(0.0);
                }
                if (s.getSc_gpa() < 1.0) {
                    ungain_credit += s.getSc_course_credit();
                    for (Score s1 : scores_rebuild) {
                        if (null == s1.getSc_gpa()) {
                            s1.setSc_gpa(0.0);
                        }
                        if (s1.getSc_course_name().equals(s.getSc_course_name())
                                && ScoreUtil.joinExecute(s1.getSc_edu_year() + s1.getSc_edu_term(), term)
                                && s1.getSc_gpa() >= 1.0) {
                            ungain_credit -= s1.getSc_course_credit();
                            break;
                        }
                    }
                }
            }
        }
        return ungain_credit;
    }

    /*
     * 得到一个学生在已经存在的年度内的gpa
     *
     * @see edu.usts.sddb.service.ScoreService#getAllGPA()
     */
    public List<GPAOfStudent> getAllGPA(String id) {
        List<GPAOfStudent> gpaOfStudentList = new ArrayList<GPAOfStudent>();
        List<String> yearList = scoreDao.findYearByStuId(id);
        GPAOfStudent gpaOfStudent = null;
        for (String year : yearList) {
            List<Score> scoreList = scoreDao.findScore(id, year);
            double gpa = ScoreUtil.getGpaInYear(scoreList);
            gpaOfStudent = new GPAOfStudent(year, gpa);
            gpaOfStudentList.add(gpaOfStudent);
        }
        return gpaOfStudentList;
    }

    public CreditInfo creadit(String id) {
        CreditInfo info = new CreditInfo();

        // 设置学生姓名
        String stu_name = scoreDao.findNameById(id);
        info.setStu_name(stu_name);
        // 设置学生学分情况
        List<YearCredit> yearCredits = new ArrayList<YearCredit>();
        List<String> years = scoreDao.findYearByStuId(id);

        // 此list中存放重修成绩
        List<Score> rebuildList = scoreDao.findRebuildScore(id);

        for (String year : years) {
            List<Score> scores = scoreDao.findScore(id, year);// 已去除重修的课程
            YearCredit yc = new YearCredit();
            yc.setYear(year);
            for (Score s : scores) {
                if (null == s.getSc_gpa()) {
                    s.setSc_gpa(0.00);
                }
                if (s.getSc_gpa() >= 1.0) { // 判断绩点大于1.0
                    yc.setCredit_gain(s.getSc_course_credit() + yc.getCredit_gain());
                } else {
                    yc.setCredit_ungain(s.getSc_course_credit() + yc.getCredit_ungain());
                    for (Score rebuildScore : rebuildList) {
                        if (s.getSc_course_name().equals(rebuildScore.getSc_course_name())) {
                            //重修考试依然旷考
                            if (null == rebuildScore.getSc_gpa()) {
                                rebuildScore.setSc_gpa(0.0);
                            }
                            if (rebuildScore.getSc_gpa() >= 1.0) {
                                // 说明重修通过，增加本学年获得的学分，减少本学年未获得的学分
                                System.out.println(rebuildScore.toString());
                                yc.setCredit_gain(rebuildScore.getSc_course_credit() + yc.getCredit_gain());
                                yc.setCredit_ungain(yc.getCredit_ungain() - rebuildScore.getSc_course_credit());
                                break;
                            }
                        }
                    }
                }
            }
            yearCredits.add(yc);
        }
        info.setYearCredits(yearCredits);

        // 设置总学分获得率
        // 遍历yearCredits以获得gain与ungain学分
        double gain = 0.0;
        double ungain = 0.0;
        for (YearCredit yCredit : yearCredits) {
            gain += yCredit.getCredit_gain();
            ungain += yCredit.getCredit_ungain();
        }
        double unformatRate = gain / (gain + ungain);
        // 保留四位小数
        DecimalFormat ft = new DecimalFormat("#.0000");
        double rate = Double.parseDouble(ft.format(unformatRate));
        // 断点
        info.setRate(rate * 100);
        return info;
    }

    /*
     * 通过学生ID查找该学生成绩，不包括不计入平均绩点的学科任选课，与重修课
     *
     * @see edu.usts.sddb.service.ScoreService#getAllScore(java.lang.String)
     */
    public List<Score> getAllScore(String id) {
        List<String> years = scoreDao.findYearByStuId(id);
        List<Score> scores = new ArrayList<Score>();
        for (String year : years) {
            scores.addAll(scoreDao.findScore(id, year));
        }
        return scores;
    }

    public List<Double> getBodyScore(String id) {
        List<Double> l = scoreDao.findBobyById(id);

        return l;

    }

    /*
     * 查询开始学期与结束学期之间学期内的补考人次
     *
     * @see edu.usts.sddb.service.ScoreService#findMakeUpTimes(java.lang.String,
     * java.lang.String)
     */
    public Map<String, Integer> findMakeUpTimes(String start_term, String end_term) {
        Map<String, Integer> map = new LinkedHashMap();
        List<String> list = ScoreUtil.getBetweenTerms(start_term, end_term);
        for (String str : list) {
            String year = str.substring(0, str.length() - 2);
            String term = str.substring(str.length() - 1, str.length());
            int num = scoreDao.findMakeUpTimes(year, term);
            map.put(str, num);

        }

        return map;
    }

    /*
     * 查询开始学期与结束学期之间学期内的重修人次
     *
     * @see edu.usts.sddb.service.ScoreService#findMakeUpTimes(java.lang.String,
     * java.lang.String)
     */
    public Map<String, Integer> findRebuildTimes(String start_term, String end_term) {
        Map<String, Integer> map = new LinkedHashMap();
        List<String> list = ScoreUtil.getBetweenTerms(start_term, end_term);
        for (String str : list) {
            String year = str.substring(0, str.length() - 2);
            String term = str.substring(str.length() - 1, str.length());
            int num = scoreDao.findRebuildTimes(year, term);
            map.put(str, num);

        }

        return map;
    }

    /*
     * 由学号与学年如（2015-2016）查询该生在该学年的平均绩点排名
     */
    public int rankOfMajor(String id, String year) {
        Student stu = studentDao.findById(id);
        String stuClass = stu.getSt_class();
        List<Student> stus = studentDao.findStudentsByClassroom(stuClass);
        List<Double> gpas = new ArrayList<Double>();
        // 记录当前学生的绩点
        double gpa = ScoreUtil.getGpaInYear(scoreDao.findScore(id, year));
        for (Student s : stus) {
            String stu_id = s.getSt_id();
            List<Score> scores = scoreDao.findScore(stu_id, year);
            gpas.add(ScoreUtil.getGpaInYear(scores));
        }
        Collections.sort(gpas);// 排序后为升序
        // 当存在绩点相同时，此方法会返回并列成绩的中的第一名
        return gpas.size() - (gpas.lastIndexOf(gpa));
    }

    /**
     * @param st_id 学号
     * @return 成绩分布
     */
    @Override
    public Map<String, Integer> getScoreDistribution(String st_id) {
        List<Score> scoreList = scoreDao.findScoreIncludeRebuild(st_id);
        //分别代表优(5.0~4.0);良(4.0~3.0);中(3.0~2.0);及格(2.0~1.0);不及格（0.0或null),其中null表示旷考或缺课
        Integer i_1 = 0, i_2 = 0, i_3 = 0, i_4 = 0, i_5 = 0;
        for (Score score : scoreList) {
            Double s = score.getSc_gpa();
            if (s == null) {
                i_5++;
            } else if (s >= 4.0) {
                i_1++;
            } else if (s >= 3.0) {
                i_2++;
            } else if (s >= 2.0) {
                i_3++;
            } else if (s >= 1.0) {
                i_4++;
            } else {
                i_5++;
            }
        }
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("优秀", i_1);
        map.put("良好", i_2);
        map.put("中等", i_3);
        map.put("及格", i_4);
        map.put("不及格", i_5);
        return map;
    }


    //----------------------------------数据管理---------------------------------------------------------

    public ObjectQuery findByPage(int page, int rows) {
        // 本次操作不是搜索，而是按条件进行查询
        // 查询全部
        // page 当前所处页数 rows 每页显示的条数
        List list = scoreDao.findByPage((page - 1) * rows, rows);
        // 获得总记录数
        int records = scoreDao.getSum();
        // 获得总页数
        int total = QueryUtil.getTotalPage(records, rows);
        // 第一个参数为当前页数，第二个为总页数，第三个参数为总记录数，第四个参数为模型对象
        ObjectQuery oq = new ObjectQuery(page, total, records, list);
        return oq;
    }


    @Override
    public ObjectQuery findByPage(Boolean _search, String filters, int page, int rows) {
        if (!_search) {
            return findByPage(page, rows);
        } else {

            // 按filters中的条件查找
            QueryCondition queryCondition = null;
            try {
                queryCondition = new ObjectMapper().readValue(filters, QueryCondition.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String sql = SqlJointUtil.getSqlByFilters(queryCondition, (page - 1) * rows, rows, false, "t_score");

            List list = scoreDao.findByFilters(sql);
            String getSumSql = SqlJointUtil.getSqlByFilters(queryCondition, (page - 1) * rows, rows, true, "t_score");
            int records = scoreDao.findByFiltersSum(getSumSql);
            int total = QueryUtil.getTotalPage(records, rows);
            ObjectQuery oq = new ObjectQuery(page, total, records, list);
            return oq;
        }
    }

    @Override
    public String handle(String oper, Score score, String[] id) {
        score = DataFormatUtil.checkNull(score);
        // oper有三种操作 add,del,edit,
        switch (oper) {
            case "edit":
                // 按sc_id进行更改成绩数据
                //由于score的id可能为空，把id[0]赋给score
                if (id != null) {
                    score.setSc_id(Integer.parseInt(id[0]));
                }
                try {
                    int editAffectedRow = scoreDao.edit(score);
                    if (editAffectedRow == 1) {
                        System.out.println("修改成功");
                        return "success";
                    }
                } catch (Exception e) {
                    return ExceptionUtil.HandleDataException(e);
                }
                break;

            case "del":
                // 会按st_id来删除，考虑到存在多选，此时主键id是数组
                int count = 0;
                for (int i = 0; i < id.length; i++) {
                    scoreDao.del(id[i]);
                    count++;
                }
                String str = count + "条成功删除" + (id.length - count) + "条删除失败";
                System.out.println(str);
                return str;

            case "add":
                // 新增发展建议对象
                try {
                    int addAffectedRow = scoreDao.add(score);
                    if (addAffectedRow == 1) {
                        System.out.println("插入成功");
                        return "success";
                    }
                } catch (Exception e) {
                    return ExceptionUtil.HandleDataException(e);
                }
        }
        return "success";
    }


    //-----------------------------------导入导出---------------------------------------------------------

    @Override
    public String importExcel(HttpServletRequest request, HttpServletResponse response) {
        String message;
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multiRequest.getFile("upfile");
        if (file.isEmpty()) {
            return "上传的文件不存在！";
        }
        InputStream is = null;
        int count = 0;
        try {
            is = file.getInputStream();
            List<Object> list = ExcelData.getDataByExcel(is, "score");
            for (Object o : list) {
                Score score = (Score) o;
                count++;
                scoreDao.add(score);
            }
            message = "上传成功的数目为" + count + "上传失败的数目为" + (list.size() - count);
        } catch (Exception e) {
            e.printStackTrace();
            message = "上传失败:第" + (count + 1) + "行存在不符合规定的数据...";
        }

        return message;
    }

    /**
     * 用户导出数据
     *
     * @param _search 是否是查找的，注意查找需要用到多条件查找，条件字符串已经放入到filters中
     * @param filters 条件字符串
     * @param page    当前页数
     * @param rows    显示条数
     * @return
     */
    public HSSFWorkbook export(Boolean _search, String filters, int page, int rows) {
        HSSFWorkbook workbook = null;
        List<Score> scoreList = new ArrayList<>();

        List<String> headerList = excelService.getExcelHeader("score");

        //用户导出数据分四个情况
        //(1)用户只导出非查找而来的当前页数数据，此时_search为false,filters为null,page为一个大于0的数
        if (!_search && filters == null && page > 0) {
            List<Object> objectList = findByPage(page, rows).getRows();
            for (Object o : objectList) {
                Score score = (Score) o;
                scoreList.add(score);
            }
        }
        //(2)用户导出非查找而来的所有页面的数据，此时_search为false,filters为null,但限定page=-1
        if (!_search && filters == null && page == -1) {
            scoreList = scoreDao.findAll();
        }

        //(3)用户导出查找而来的当前页面的数据，此时_search为true,filters不为null,page为一个大于0的数
        //(4)用户导出查找而来所有页面的数据，此时_search为true,filters不为null,但限定page=-1
        //这俩种可以合并，反正SqlJointUtil工具类中可以进行区分并产生不同返回结果
        if (_search && filters != null) {
            List<Object> objectList = findByPage(_search, filters, page, rows).getRows();
            for (Object o : objectList) {
                Score score = (Score) o;
                scoreList.add(score);
            }
        }

        List<List<Object>> content = new ArrayList<List<Object>>();
        for (Object ob : scoreList) {
            List<Object> row = new ArrayList<>();
            Score score = (Score) ob;
            row.add(score.getSc_student_id());
            row.add(score.getSc_student_name());
            row.add(score.getSc_course_name());
            row.add(score.getSc_course_credit());
            row.add(score.getSc_course_nature());
            row.add(score.getSc_score());
            row.add(score.getSc_remark());
            row.add(score.getSc_gpa());
            row.add(score.getSc_make_up_score());
            row.add(score.getSc_rebuild_score());
            row.add(score.getSc_class_name());
            row.add(score.getSc_course_belong());
            row.add(score.getSc_rebuild_mark());
            row.add(score.getSc_select_course_id());
            row.add(score.getSc_edu_year());
            row.add(score.getSc_edu_term());
            content.add(row);


        }
        workbook = ExcelUtil.exportContent(headerList, content);

        return workbook;
    }

}
