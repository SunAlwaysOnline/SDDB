package edu.usts.sddb.service;

import edu.usts.sddb.entity.Advice;
import edu.usts.sddb.entity.Student;
import edu.usts.sddb.entity.assist.DBComment;
import edu.usts.sddb.entity.pack.ObjectQuery;
import edu.usts.sddb.entity.pack.StudentQuery;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AdviceService {
    public ObjectQuery findByPage(Boolean _search, String filters, int page, int rows);

    public String handle(String oper, Advice advice, String id[]);

    public List<Advice> findAdvicesByPage(int start, int rows);

    public HSSFWorkbook export(Boolean _search, String filters, int page, int rows);

    public List<DBComment> findComment();

    public String importExcel(HttpServletRequest request, HttpServletResponse response);
}
