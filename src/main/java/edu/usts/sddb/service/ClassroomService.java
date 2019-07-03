package edu.usts.sddb.service;

import java.util.List;
import java.util.Map;

import edu.usts.sddb.entity.Classroom;
import edu.usts.sddb.entity.Major;
import edu.usts.sddb.entity.pack.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ClassroomService {
    public List<Classroom> findAll();

    public ObjectQuery findByPage(int page, int rows);

    public String handleClassroom(String oper, Classroom classroom, String id[]);

    public ObjectQuery findByPage(Boolean _search, String filters, int page, int rows);

    //根据班级名称获得班级内成员的雷达图情况
    public List<ClassMemberScore> getClassMemberScore(String class_name);

    //获得指定班级的四维分数状况(四维就是雷达图的四个数据)
    public ClassroomState getOneClassroomState(String class_name);

    //获得所有班级的四维分数状况(四维就是雷达图的四个数据)
    public List<ClassroomState> getAllClassroomState();

    //获得指定班级的四维平均分的排名
    public Map<String, String> getClassroomRank(String class_name);


    //---------------导入导出------------------------

    public HSSFWorkbook export(Boolean _search, String filters, int page, int rows);

    public String importExcel(HttpServletRequest request, HttpServletResponse response);

}
