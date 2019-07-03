package edu.usts.sddb.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ExcelService {
    //获得模版对象
    public HSSFWorkbook getTemplate(String name);

    //获取导出excel的头部
    public List<String> getExcelHeader(String name);


}
