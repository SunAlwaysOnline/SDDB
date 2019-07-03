package edu.usts.sddb.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import edu.usts.sddb.entity.pack.ObjectQuery;
import edu.usts.sddb.service.ExcelService;
import edu.usts.sddb.util.Excel.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.usts.sddb.entity.Teacher;
import edu.usts.sddb.entity.pack.TeacherQuery;
import edu.usts.sddb.service.TeacherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    @Qualifier("teacherService")
    TeacherService teacherService;

    @Autowired
    ExcelService excelService;

    @RequestMapping("/findsome.do")
    @ResponseBody
    public ObjectQuery findTeachersByPage(Boolean _search, String filters, int page, int rows)
            throws UnsupportedEncodingException {
        if (filters != null) {
            // 转码
            //filters = new String(filters.getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(filters);
        }
        return teacherService.findByPage(_search, filters, page, rows);
    }

    @RequestMapping("/handleTeacher.do")
    @ResponseBody
    public String handleTeacher(String oper, Teacher teacher, String id[])
            throws UnsupportedEncodingException {
        String temp = teacherService.handleTeacher(oper, teacher, id);
        return URLEncoder.encode(temp, "UTF-8");
    }

    //-----------------------------------导入导出---------------------------------------------------------

    //先下载模版文件，再上传模版文件进行导入
    @RequestMapping("/downloadTemplate")
    @ResponseBody
    public void downloadTemplate(HttpServletResponse response) {
        HSSFWorkbook workbook = excelService.getTemplate("teacher");
        ExcelUtil.downloadExcel(response, workbook, "teacher.xls");
    }

    //导入excel
    @RequestMapping("/importExcel")
    @ResponseBody
    public String importExcel(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        String message = teacherService.importExcel(request, response);
        return URLEncoder.encode(message, "UTF-8");
    }


    @RequestMapping("/exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletResponse response, Boolean _search, String filters, int page, int rows) {
        HSSFWorkbook workbook = teacherService.export(_search, filters, page, rows);
        ExcelUtil.downloadExcel(response, workbook, "teacher.xls");
    }


}
