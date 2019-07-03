package edu.usts.sddb.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.usts.sddb.entity.*;
import edu.usts.sddb.entity.pack.ObjectQuery;
import edu.usts.sddb.service.ExcelService;
import edu.usts.sddb.util.Excel.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.usts.sddb.entity.pack.ClassUngain;
import edu.usts.sddb.entity.pack.CreditInfo;
import edu.usts.sddb.service.ClassroomService;
import edu.usts.sddb.service.ScoreService;

@Controller
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    @Qualifier("scoreService")
    ScoreService scoreService;

    @Autowired
    @Qualifier("classroomService")
    ClassroomService classroomService;

    @Autowired
    ExcelService excelService;

    @RequestMapping("/sturank.do")
    @ResponseBody
    public int stuRank(String id, String year) {
        return scoreService.rankOfMajor(id, year);
    }

    @RequestMapping("/gpa.do")
    @ResponseBody
    public List<GPAOfStudent> getGPA(String id) {
        List<GPAOfStudent> gpaOfStudentList = scoreService.getAllGPA(id);
        return gpaOfStudentList;
    }

    // 得到班级表的所有数据
    @RequestMapping("/getClassroom.do")
    @ResponseBody
    public List<Classroom> getAllClassroom() {
        return classroomService.findAll();

    }

    @RequestMapping("/credit.do")
    @ResponseBody
    public CreditInfo credit(String id) {
        CreditInfo info = scoreService.creadit(id);
        return info;
    }

    @RequestMapping("/majorungain.do")
    @ResponseBody
    public List<ClassUngain> majorUngain(int year, String term) {
        List<ClassUngain> list = scoreService.ungainScore(year, term);
        return list;
    }

    @RequestMapping("/graderate.do")
    @ResponseBody
    public Map<String, Double> gradeRate(int grade, String term) {
        Map<String, Double> map = scoreService.gradeRate(grade, term);
        return map;
    }

    @RequestMapping("/getMakeUpTimes.do")
    @ResponseBody
    public Map<String, Integer> getMakeUpTimes(String start_term, String end_term) {
        Map<String, Integer> map = scoreService.findMakeUpTimes(start_term, end_term);
        return map;
    }

    @RequestMapping("/getReBuildTimes.do")
    @ResponseBody
    public Map<String, Integer> getReBuildTimes(String start_term, String end_term) {
        Map<String, Integer> map = scoreService.findRebuildTimes(start_term, end_term);
        return map;
    }

    @RequestMapping("/getScoreDistribution")
    @ResponseBody
    public Map<String,Integer> getScoreDistribution(String id) {
        return scoreService.getScoreDistribution(id);
    }

    //----------------------------------数据管理---------------------------------------------------------
    @RequestMapping("/findsome")
    @ResponseBody
    public ObjectQuery findAdvicesByPage(Boolean _search, String filters, int page, int rows)
            throws UnsupportedEncodingException {
        if (filters != null) {
            // 转码
            // filters = new String(filters.getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(filters);
        }
        return scoreService.findByPage(_search, filters, page, rows);

    }

    @RequestMapping("/handle")
    @ResponseBody
    public String handleAdvice(String oper, Score score, String id[])
            throws UnsupportedEncodingException {
        String temp = scoreService.handle(oper, score, id);
        // 对传回的中文进行编码
        return URLEncoder.encode(temp, "UTF-8");
    }


    //-----------------------------------导入导出---------------------------------------------------------

    //先下载模版文件，再上传模版文件进行导入
    @RequestMapping("/downloadTemplate")
    @ResponseBody
    public void downloadTemplate(HttpServletResponse response) {
        HSSFWorkbook workbook = excelService.getTemplate("score");
        ExcelUtil.downloadExcel(response, workbook, "score.xls");
    }

    //导入excel
    @RequestMapping("/importExcel")
    @ResponseBody
    public String importExcel(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        String message = scoreService.importExcel(request, response);
        return URLEncoder.encode(message, "UTF-8");
    }


    @RequestMapping("/exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletResponse response, Boolean _search, String filters, int page, int rows) {
        HSSFWorkbook workbook = scoreService.export(_search, filters, page, rows);
        ExcelUtil.downloadExcel(response, workbook, "score.xls");
    }

}
