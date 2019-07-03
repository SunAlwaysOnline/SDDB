package edu.usts.sddb.controller;


import edu.usts.sddb.service.ExcelService;
import edu.usts.sddb.util.Excel.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    ExcelService excelService;

    //先下载模版文件，再上传模版文件进行导入
    @RequestMapping("/downloadTemplate/{name}")
    @ResponseBody
    public void downloadTemplate(HttpServletResponse response, @PathVariable String name) {
        HSSFWorkbook workbook = excelService.getTemplate(name);
        ExcelUtil.downloadExcel(response, workbook, name + ".xls");
    }


}
