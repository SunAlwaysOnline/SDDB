package edu.usts.sddb.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import edu.usts.sddb.entity.pack.ObjectQuery;
import edu.usts.sddb.entity.pack.StudentScore;
import edu.usts.sddb.service.ExcelService;
import edu.usts.sddb.util.Excel.ExcelUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;

import edu.usts.sddb.entity.Student;
import edu.usts.sddb.entity.pack.QueryCondition;
import edu.usts.sddb.entity.pack.StudentQuery;
import edu.usts.sddb.service.StudentService;
import edu.usts.sddb.util.QueryUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    ExcelService excelService;

    @RequestMapping("/findsome")
    @ResponseBody
    public ObjectQuery findByPage(Boolean _search, String filters, int page, int rows)
            throws UnsupportedEncodingException {
        if (filters != null) {
            // 转码
            //filters = new String(filters.getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(filters);
        }
        return studentService.findByPage(_search, filters, page, rows);

    }

    @RequestMapping("/handle")
    @ResponseBody
    public String handle(String oper, Student student, String id[])
            throws UnsupportedEncodingException {
        String temp = studentService.handle(oper, student, id);
        // 对传回的中文进行编码
        return URLEncoder.encode(temp, "UTF-8");
    }

    @RequestMapping("/findStuByClass.do")
    @ResponseBody
    public List<Student> findStudentsByClassroom(String st_class) {
        return studentService.findStudents(st_class);

    }

    @RequestMapping("/findStuById")
    @ResponseBody
    public Student findStuById(String st_id) {
        return studentService.findStuById(st_id);
    }

    @RequestMapping("/score.do")
    @ResponseBody
    public List<StudentScore> studentScores(String st_id) {
        return studentService.findAllScoreById(st_id);
    }

    //-----------------------------------导入导出---------------------------------------------------------

    //先下载模版文件，再上传模版文件进行导入
    @RequestMapping("/downloadTemplate")
    @ResponseBody
    public void downloadTemplate(HttpServletResponse response) {
        HSSFWorkbook workbook = excelService.getTemplate("student");
        ExcelUtil.downloadExcel(response, workbook, "student.xls");
    }

    //导入excel
    @RequestMapping("/importExcel")
    @ResponseBody
    public String importExcel(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        String message = studentService.importExcel(request, response);
        return URLEncoder.encode(message, "UTF-8");
    }


    @RequestMapping("/exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletResponse response, Boolean _search, String filters, int page, int rows) {
        HSSFWorkbook workbook = studentService.export(_search, filters, page, rows);
        ExcelUtil.downloadExcel(response, workbook, "student.xls");
    }

}
