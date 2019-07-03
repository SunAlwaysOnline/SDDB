package edu.usts.sddb.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import edu.usts.sddb.entity.pack.ClassMemberScore;
import edu.usts.sddb.entity.pack.ClassroomState;
import edu.usts.sddb.entity.pack.ObjectQuery;
import edu.usts.sddb.service.ExcelService;
import edu.usts.sddb.util.Excel.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.usts.sddb.entity.Classroom;
import edu.usts.sddb.entity.pack.ClassroomQuery;
import edu.usts.sddb.service.ClassroomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/classroom")
public class ClassroomController {

    @Autowired
    @Qualifier("classroomService")
    ClassroomService classroomService;

    @Autowired
    ExcelService excelService;

    @RequestMapping("/findsome.do")
    @ResponseBody
    public ObjectQuery findClassroomByPage(Boolean _search, String filters, int page, int rows)
            throws UnsupportedEncodingException {
        if (filters != null) {
            // 转码
            filters = new String(filters.getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(filters);
        }
        return classroomService.findByPage(_search, filters, page, rows);
    }

    @RequestMapping("/handleClassroom.do")
    @ResponseBody
    public String handleClassroom(String oper, Classroom classroom, String id[])
            throws UnsupportedEncodingException {
        String temp = classroomService.handleClassroom(oper, classroom, id);
        return URLEncoder.encode(temp, "UTF-8");
    }

    ////根据班级名称获得班级内成员的雷达图情况
    @RequestMapping("/getClassMemberScore")
    @ResponseBody
    public List<ClassMemberScore> getClassMemberScore(String class_name) {
        return classroomService.getClassMemberScore(class_name);
    }


    //获得指定班级的四维分数状况
    @RequestMapping("/getOneClassroomState")
    @ResponseBody
    public ClassroomState getOneClassrommState(String class_name) {
        return classroomService.getOneClassroomState(class_name);
    }

    //获得所有班级的四维分数状况
    @RequestMapping("/getAllClassroomState")
    @ResponseBody
    public List<ClassroomState> getAllClassroomState() {
        return classroomService.getAllClassroomState();
    }

    //获得指定班级的四维平均分的排名
    @RequestMapping("/getClassroomRank")
    @ResponseBody
    public Map<String, String> getClassroomRank(String class_name) {
        return classroomService.getClassroomRank(class_name);
    }

    //-----------------------------------导入导出---------------------------------------------------------

    //先下载模版文件，再上传模版文件进行导入
    @RequestMapping("/downloadTemplate")
    @ResponseBody
    public void downloadTemplate(HttpServletResponse response) {
        HSSFWorkbook workbook = excelService.getTemplate("classroom");
        ExcelUtil.downloadExcel(response, workbook, "classroom.xls");
    }

    //导入excel
    @RequestMapping("/importExcel")
    @ResponseBody
    public String importExcel(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        String message = classroomService.importExcel(request, response);
        return URLEncoder.encode(message, "UTF-8");
    }


    @RequestMapping("/exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletResponse response, Boolean _search, String filters, int page, int rows) {
        HSSFWorkbook workbook = classroomService.export(_search, filters, page, rows);
        ExcelUtil.downloadExcel(response, workbook, "classroom.xls");
    }

}
