package edu.usts.sddb.service;


import edu.usts.sddb.entity.Scholarship;
import edu.usts.sddb.entity.pack.ObjectQuery;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface ScholarshipService {
    public ObjectQuery findByPage(Boolean _search, String filters, int page, int rows);

    public String handle(String oper, Scholarship scholarship, String id[]);

    public HSSFWorkbook export(Boolean _search, String filters, int page, int rows);

    public String importExcel(HttpServletRequest request, HttpServletResponse response);
}
