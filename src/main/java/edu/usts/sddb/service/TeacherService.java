package edu.usts.sddb.service;

import edu.usts.sddb.entity.Teacher;
import edu.usts.sddb.entity.pack.MajorQuery;
import edu.usts.sddb.entity.pack.ObjectQuery;
import edu.usts.sddb.entity.pack.TeacherQuery;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TeacherService {
	public ObjectQuery findByPage(int page, int rows);

	public String handleTeacher(String oper, Teacher teacher, String id[]);
	
	public ObjectQuery findByPage(Boolean _search, String filters, int page, int rows);


	//---------------导入导出------------------------

	public HSSFWorkbook export(Boolean _search, String filters, int page, int rows);

	public String importExcel(HttpServletRequest request, HttpServletResponse response);
}
