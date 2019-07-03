package edu.usts.sddb.controller;


import edu.usts.sddb.entity.Volunteer;
import edu.usts.sddb.entity.pack.ObjectQuery;
import edu.usts.sddb.service.ExcelService;
import edu.usts.sddb.service.VolunteerService;
import edu.usts.sddb.util.Excel.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

@Controller
@RequestMapping("/volunteer")
public class VolunteerController {

    @Autowired
    VolunteerService volunteerService;
    @Autowired
    ExcelService excelService;


    @RequestMapping("/getVolunteerRank")
    @ResponseBody
    public Map<String, Double> getVolunteerRank(String id) {
        return volunteerService.getVolunteerRank(id);
    }

    //----------------------------------数据管理---------------------------------------------------------

    @RequestMapping("/findsome.do")
    @ResponseBody
    public ObjectQuery findVolunteerByPage(Boolean _search, String filters, int page, int rows) {
        if (filters != null) {
            // 转码
            // filters = new String(filters.getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(filters);
        }
        return volunteerService.findByPage(_search, filters, page, rows);

    }

    @RequestMapping("/handleVolunteer.do")
    @ResponseBody
    public String handleVolunteer(String oper, Volunteer volunteer, String id[])
            throws UnsupportedEncodingException {
        String temp = volunteerService.handle(oper, volunteer, id);
        // 对传回的中文进行编码
        return URLEncoder.encode(temp, "UTF-8");
    }

    //-----------------------------------导入导出---------------------------------------------------------

    //先下载模版文件，再上传模版文件进行导入
    @RequestMapping("/downloadTemplate")
    @ResponseBody
    public void downloadTemplate(HttpServletResponse response) {
        HSSFWorkbook workbook = excelService.getTemplate("volunteer");
        ExcelUtil.downloadExcel(response, workbook, "volunteer.xls");
    }

    //导入excel
    @RequestMapping("/importExcel.do")
    @ResponseBody
    public String importExcel(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        String message = volunteerService.importExcel(request, response);
        return URLEncoder.encode(message, "UTF-8");
    }


    @RequestMapping("/exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletResponse response, Boolean _search, String filters, int page, int rows) {
        HSSFWorkbook workbook = volunteerService.export(_search, filters, page, rows);
        ExcelUtil.downloadExcel(response, workbook, "volunteer.xls");
    }

}
