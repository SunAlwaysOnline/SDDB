package edu.usts.sddb.service;


import edu.usts.sddb.entity.Duty;
import edu.usts.sddb.entity.assist.DBComment;
import edu.usts.sddb.entity.pack.ObjectQuery;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DutyService {

    //由学号获取职务
    public List<Duty> findDutyById(String st_id);

    //----------------------------------数据管理---------------------------------------------------------

    public ObjectQuery findByPage(Boolean _search, String filters, int page, int rows);

    public String handle(String oper, Duty duty, String id[]);

    //------------------------导入导出-----------------------------

    public HSSFWorkbook export(Boolean _search, String filters, int page, int rows);

    public String importExcel(HttpServletRequest request, HttpServletResponse response);
}
