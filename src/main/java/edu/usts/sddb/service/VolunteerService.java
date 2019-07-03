package edu.usts.sddb.service;

import edu.usts.sddb.entity.Volunteer;
import edu.usts.sddb.entity.assist.DBComment;
import edu.usts.sddb.entity.pack.ObjectQuery;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface VolunteerService {


    public Double findTimeById(String id);

    public Map<String,Double> getVolunteerRank(String id);

    //----------------------------------数据管理---------------------------------------------------------

    public ObjectQuery findByPage(Boolean _search, String filters, int page, int rows);

    public String handle(String oper, Volunteer volunteer, String id[]);

    public List<Volunteer> findVolunteerByPage(int start, int rows);

    //------------------------导入导出-----------------------------

    public HSSFWorkbook export(Boolean _search, String filters, int page, int rows);

    public String importExcel(HttpServletRequest request, HttpServletResponse response);
}
