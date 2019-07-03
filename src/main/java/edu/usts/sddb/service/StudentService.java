package edu.usts.sddb.service;

import java.util.List;

import edu.usts.sddb.entity.Student;
import edu.usts.sddb.entity.assist.DBComment;
import edu.usts.sddb.entity.pack.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface StudentService {

    public ObjectQuery findByPage(Boolean _search, String filters, int page, int rows);

    public String handle(String oper, Student student, String id[]);

    public List<Student> findStudents(String st_class);

    public List<StudentScore> findAllScoreById(String id);

    public Student findStuById(String st_id);


    //---------------导入导出------------------------

    public HSSFWorkbook export(Boolean _search, String filters, int page, int rows);

    public String importExcel(HttpServletRequest request, HttpServletResponse response);

}
