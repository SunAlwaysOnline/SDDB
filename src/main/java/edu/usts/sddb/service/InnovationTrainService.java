package edu.usts.sddb.service;


import edu.usts.sddb.entity.InnovationTrain;
import edu.usts.sddb.entity.Scholarship;
import edu.usts.sddb.entity.pack.ObjectQuery;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface InnovationTrainService {
    public ObjectQuery findByPage(Boolean _search, String filters, int page, int rows);

    public String handle(String oper, InnovationTrain innovationTrain, String id[]);


    //---------------导入导出------------------------

    public HSSFWorkbook export(Boolean _search, String filters, int page, int rows);

    public String importExcel(HttpServletRequest request, HttpServletResponse response);
}
