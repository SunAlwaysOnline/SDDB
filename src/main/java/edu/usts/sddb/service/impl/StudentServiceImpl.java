package edu.usts.sddb.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DataTruncation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.usts.sddb.dao.ScoreDao;
import edu.usts.sddb.entity.Duty;
import edu.usts.sddb.entity.Score;
import edu.usts.sddb.entity.pack.ObjectQuery;
import edu.usts.sddb.entity.pack.StudentScore;
import edu.usts.sddb.service.ExcelService;
import edu.usts.sddb.service.ScoreService;
import edu.usts.sddb.util.*;
import edu.usts.sddb.util.Excel.ExcelData;
import edu.usts.sddb.util.Excel.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.ConnectionFeatureNotAvailableException;
import com.mysql.jdbc.MysqlDataTruncation;

import edu.usts.sddb.dao.StudentDao;
import edu.usts.sddb.dao.UserDao;
import edu.usts.sddb.entity.Student;
import edu.usts.sddb.entity.pack.QueryCondition;
import edu.usts.sddb.entity.pack.StudentQuery;
import edu.usts.sddb.service.StudentService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    @Qualifier("studentDao")
    StudentDao studentDao;


    @Autowired
    @Qualifier("scoreDao")
    ScoreDao scoreDao;

    @Autowired
    @Qualifier("scoreService")
    ScoreService scoreService;

    @Autowired
    ExcelService excelService;


    public ObjectQuery findByPage(int page, int rows) {
        // 本次操作不是搜索，而是按条件进行查询
        // 查询全部
        // page 当前所处页数 rows 每页显示的条数
        List list = studentDao.findByPage((page - 1) * rows, rows);
        // 获得总记录数
        int records = studentDao.getSum();
        // 获得总页数
        int total = QueryUtil.getTotalPage(records, rows);
        // 第一个参数为当前页数，第二个为总页数，第三个参数为总记录数，第四个参数为模型对象
        ObjectQuery sq = new ObjectQuery(page, total, records, list);
        return sq;
    }

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

            String sql = SqlJointUtil.getSqlByFilters(queryCondition, (page - 1) * rows, rows, false, "t_student");

            List list = studentDao.findByFilters(sql);
            String getSumSql = SqlJointUtil.getSqlByFilters(queryCondition, (page - 1) * rows, rows, true, "t_student");
            int records = studentDao.findByFiltersSum(getSumSql);
            int total = QueryUtil.getTotalPage(records, rows);
            ObjectQuery sq = new ObjectQuery(page, total, records, list);
            return sq;

        }

    }

    public String handle(String oper, Student student, String id[]) {
        student = DataFormatUtil.checkNull(student);
        // oper有三种操作 add,del,edit,
        switch (oper) {
            case "edit":
                // 按st_id进行更改学生数据
                if (id != null) {
                    student.setSt_id(id[0]);
                }
                try {
                    int editAffectedRow = studentDao.edit(student);
                    if (editAffectedRow == 1) {
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
                    studentDao.del(id[i]);
                    count++;
                }
                String str = count + "条成功删除" + (id.length - count) + "条删除失败";
                System.out.println(str);
                return str;
            case "add":
                // 新增学生对象
                System.out.println(student.toString());
                try {
                    int addAffectedRow = studentDao.add(student);
                    if (addAffectedRow == 1) {
                        return "success";
                    }
                } catch (Exception e) {
                    return ExceptionUtil.HandleDataException(e);
                }
        }
        return "success";
    }


    // 由班级查询学生
    @Override
    public List<Student> findStudents(String st_class) {
        return studentDao.findStudentsByClassroom(st_class);
    }

    @Override
    public Student findStuById(String st_id) {
        return studentDao.findById(st_id);
    }

    @Override
    public List<StudentScore> findAllScoreById(String id) {
        List<StudentScore> list = new ArrayList<StudentScore>();
        //通过学生id 查出学年列表
        List<String> yearList = scoreDao.findYearByStuId(id);
        //遍历学年列表
        for (String year : yearList) {
            StudentScore score = new StudentScore();
            //设置学年
            score.setYear(year);
            //获得各科成绩并设置各科成绩
            List<Score> scoreList = scoreDao.findAllScore(id, year);
            score.setScores(scoreList);
            //通过各科成绩得到绩点 并设置
            double gpa = ScoreUtil.getGpaInYear(scoreList);
            score.setGpa(gpa);
            //获得专业排名并设置
            int rank = scoreService.rankOfMajor(id, year);
            score.setRank(rank);

            //加入到list中
            list.add(score);
        }
        return list;
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
            List<Object> list = ExcelData.getDataByExcel(is, "student");
            for (Object o : list) {
                Student student = (Student) o;
                count++;
                studentDao.add(student);
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
        List<Student> studentList = new ArrayList<>();

        List<String> headerList = excelService.getExcelHeader("student");

        //用户导出数据分四个情况
        //(1)用户只导出非查找而来的当前页数数据，此时_search为false,filters为null,page为一个大于0的数
        if (!_search && filters == null && page > 0) {
            List<Object> objectList = findByPage(page, rows).getRows();
            for (Object o : objectList) {
                Student student = (Student) o;
                studentList.add(student);
            }
        }
        //(2)用户导出非查找而来的所有页面的数据，此时_search为false,filters为null,但限定page=-1
        if (!_search && filters == null && page == -1) {
            studentList = studentDao.findAll();
        }

        //(3)用户导出查找而来的当前页面的数据，此时_search为true,filters不为null,page为一个大于0的数
        //(4)用户导出查找而来所有页面的数据，此时_search为true,filters不为null,但限定page=-1
        //这俩种可以合并，反正SqlJointUtil工具类中可以进行区分并产生不同返回结果
        if (_search && filters != null) {
            List<Object> objectList = findByPage(_search, filters, page, rows).getRows();
            for (Object o : objectList) {
                Student student = (Student) o;
                studentList.add(student);
            }
        }

        List<List<Object>> content = new ArrayList<List<Object>>();
        for (Student student : studentList) {
            List<Object> row = new ArrayList<>();
            row.add(student.getSt_id());
            row.add(student.getSt_name());
            row.add(student.getSt_sex());
            row.add(student.getSt_major());
            row.add(student.getSt_class());
            row.add(student.getSt_grade());
            content.add(row);

        }
        workbook = ExcelUtil.exportContent(headerList, content);

        return workbook;
    }


}
