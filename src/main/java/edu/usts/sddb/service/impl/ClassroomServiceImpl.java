package edu.usts.sddb.service.impl;

import java.io.InputStream;
import java.util.*;

import edu.usts.sddb.dao.StudentDao;
import edu.usts.sddb.entity.PartOfRadar;
import edu.usts.sddb.entity.Student;
import edu.usts.sddb.entity.pack.*;
import edu.usts.sddb.service.ExcelService;
import edu.usts.sddb.service.RadarService;
import edu.usts.sddb.util.DataFormatUtil;
import edu.usts.sddb.util.Excel.ExcelData;
import edu.usts.sddb.util.Excel.ExcelUtil;
import edu.usts.sddb.util.ExceptionUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.usts.sddb.dao.ClassroomDao;
import edu.usts.sddb.entity.Classroom;
import edu.usts.sddb.entity.Major;
import edu.usts.sddb.service.ClassroomService;
import edu.usts.sddb.util.QueryUtil;
import edu.usts.sddb.util.SqlJointUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Service("classroomService")
public class ClassroomServiceImpl implements ClassroomService {
    @Autowired
    @Qualifier("classroomDao")
    ClassroomDao classroomDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    RadarService radarService;

    @Autowired
    ExcelService excelService;

    // 查询所有的班级
    public List<Classroom> findAll() {
        return classroomDao.findAll();
    }


    @Override
    public List<ClassMemberScore> getClassMemberScore(String class_name) {
        List<Student> studentList = studentDao.findStudentsByClassroom(class_name);
        List<ClassMemberScore> scoreList = new ArrayList<>();
        for (Student student : studentList) {
            ClassMemberScore classMemberScore = new ClassMemberScore();
            classMemberScore.setSt_id(student.getSt_id());
            classMemberScore.setSt_name(student.getSt_name());
            classMemberScore.setSt_sex(student.getSt_sex());
            List<PartOfRadar> radarList = radarService.generaterRadar(student.getSt_id());
            classMemberScore.setScorePart(radarList.get(0).getRadarScore());
            classMemberScore.setBodyPart(radarList.get(1).getRadarScore());
            classMemberScore.setScientificPart(radarList.get(2).getRadarScore());
            classMemberScore.setVolunteerPart(radarList.get(3).getRadarScore());
            int all = 0;
            for (int i = 0; i < 4; i++) {
                all += radarList.get(i).getRadarScore();
            }
            classMemberScore.setAll(all);
            scoreList.add(classMemberScore);
        }

        return scoreList;
    }

    //获得指定班级的四维分数状况
    @Override
    public ClassroomState getOneClassroomState(String class_name) {
        ClassroomState classroomState = new ClassroomState();
        List<ClassMemberScore> scoreList = getClassMemberScore(class_name);

        int num = 0;
        int score = 0;
        int body = 0;
        int scientific = 0;
        int volunteer = 0;

        for (ClassMemberScore memberScore : scoreList) {
            num++;
            score += memberScore.getScorePart();
            body += memberScore.getBodyPart();
            scientific += memberScore.getScientificPart();
            volunteer += memberScore.getVolunteerPart();
        }

        //班级表中的某个班级没有学生
        if (num == 0) {
            return null;
        }

        classroomState.setName(class_name);
        classroomState.setNum(num);
        classroomState.setScore(score / num);
        classroomState.setBody(body / num);
        classroomState.setScientific(scientific / num);
        classroomState.setVolunteer(volunteer / num);
        classroomState.setAll(score / num + body / num + scientific / num + volunteer / num);

        return classroomState;
    }

    //获得所有班级的四维分数状况
    @Override
    public List<ClassroomState> getAllClassroomState() {
        List<Classroom> classroomList = findAll();
        List<ClassroomState> stateList = new ArrayList<>();

        for (Classroom classroom : classroomList) {
            ClassroomState classroomState = getOneClassroomState(classroom.getCl_name());
            if (null == classroomState) {
                continue;
            }
            stateList.add(classroomState);
        }
        return stateList;
    }

    //获得指定班级的四维平均分的排名
    @Override
    public Map<String, String> getClassroomRank(String class_name) {
        Map<String, String> map = new LinkedHashMap<>();
        int score = 0;
        int body = 0;
        int scientific = 0;
        int volunteer = 0;
        int all = 0;

        List<ClassroomState> stateList = getAllClassroomState();

        //根据学习成绩排名
        Collections.sort(stateList, new Comparator<ClassroomState>() {
            @Override
            public int compare(ClassroomState o1, ClassroomState o2) {
                return o2.getScore() - o1.getScore();
            }
        });

        for (int i = 0; i < stateList.size(); i++) {
            if (class_name.equals(stateList.get(i).getName())) {
                score = i + 1;
                break;
            }
        }

        //根据体育排名
        Collections.sort(stateList, new Comparator<ClassroomState>() {
            @Override
            public int compare(ClassroomState o1, ClassroomState o2) {
                return o2.getBody() - o1.getBody();
            }
        });

        for (int i = 0; i < stateList.size(); i++) {
            if (class_name.equals(stateList.get(i).getName())) {
                body = i + 1;
                break;
            }
        }

        //根据科研排名
        Collections.sort(stateList, new Comparator<ClassroomState>() {
            @Override
            public int compare(ClassroomState o1, ClassroomState o2) {
                return o2.getScientific() - o1.getScientific();
            }
        });

        for (int i = 0; i < stateList.size(); i++) {
            if (class_name.equals(stateList.get(i).getName())) {
                scientific = i + 1;
                break;
            }
        }

        //根据志愿排名
        Collections.sort(stateList, new Comparator<ClassroomState>() {
            @Override
            public int compare(ClassroomState o1, ClassroomState o2) {
                return o2.getVolunteer() - o1.getVolunteer();
            }
        });

        for (int i = 0; i < stateList.size(); i++) {
            if (class_name.equals(stateList.get(i).getName())) {
                volunteer = i + 1;
                break;
            }
        }

        //根据总评平均排名
        Collections.sort(stateList, new Comparator<ClassroomState>() {
            @Override
            public int compare(ClassroomState o1, ClassroomState o2) {
                return o2.getAll() - o1.getAll();
            }
        });

        for (int i = 0; i < stateList.size(); i++) {
            if (class_name.equals(stateList.get(i).getName())) {
                all = i + 1;
                break;
            }
        }

        map.put("score", score + "/" + stateList.size());
        map.put("body", body + "/" + stateList.size());
        map.put("scientific", scientific + "/" + stateList.size());
        map.put("volunteer", volunteer + "/" + stateList.size());
        map.put("all", all + "/" + stateList.size());

        return map;
    }

    //------------------------------------数据管理开始-------------------------------------------
    @Override
    public ObjectQuery findByPage(Boolean _search, String filters, int page, int rows) {
        if (!_search) {
            return findByPage(page, rows);
        } else {

            // 按filters中的条件查找
            QueryCondition queryCondition = null;
            try {
                queryCondition = new ObjectMapper().readValue(filters, QueryCondition.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String sql = SqlJointUtil.getSqlByFilters(queryCondition, (page - 1) * rows, rows, false, "t_class");
            System.out.println(sql);
            List<Object> list = classroomDao.findByFilters(sql);
            String getSumSql = SqlJointUtil.getSqlByFilters(queryCondition, (page - 1) * rows, rows, true, "t_class");
            int records = classroomDao.findByFiltersSum(getSumSql);
            int total = QueryUtil.getTotalPage(records, rows);
            ObjectQuery cq = new ObjectQuery(page, total, records, list);
            return cq;
        }
    }


    @Override
    public ObjectQuery findByPage(int page, int rows) {
        List<Object> list = classroomDao.findByPage((page - 1) * rows, rows);
        int records = classroomDao.getSum();
        int total = QueryUtil.getTotalPage(records, rows);
        ObjectQuery cq = new ObjectQuery(page, total, records, list);
        return cq;
    }

    @Override
    public String handleClassroom(String oper, Classroom classroom, String[] id) {
        classroom = DataFormatUtil.checkNull(classroom);
        switch (oper) {
            case "edit":
                // 按ma_id进行更改学生数据
                if (id != null) {
                    classroom.setCl_id((id[0]));
                }
                try {
                    int editAffectedRow = classroomDao.edit(classroom);
                    if (editAffectedRow == 1) {
                        return "success";
                    }
                } catch (Exception e) {
                    return ExceptionUtil.HandleDataException(e);
                }
                break;

            case "del":
                // 会按st_id来删除，考虑到存在多选，此时主键id是数组
                int count = 0;
                for (int i = 0; i < id.length; i++) {
                    classroomDao.del(id[i]);
                    count++;
                }
                String str = count + "条成功删除" + (id.length - count) + "条删除失败";
                System.out.println(str);
                return str;

            case "add":
                // 新增班级对象
                try {
                    int addAffectedRow = classroomDao.add(classroom);
                    if (addAffectedRow == 1) {
                        return "success";
                    }
                } catch (Exception e) {
                    return ExceptionUtil.HandleDataException(e);
                }
        }
        return "success";
    }


    //-----------------------------------导入导出---------------------------------------------------------

    @Override
    public String importExcel(HttpServletRequest request, HttpServletResponse response) {
        String message;
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multiRequest.getFile("upfile");
        if (file.isEmpty()) {
            return "上传的文件不存在！";
        }
        InputStream is = null;
        int count = 0;
        try {
            is = file.getInputStream();
            List<Object> list = ExcelData.getDataByExcel(is, "classroom");
            for (Object o : list) {
                Classroom classroom = (Classroom) o;
                count++;
                classroomDao.add(classroom);
            }
            message = "上传成功的数目为" + count + "上传失败的数目为" + (list.size() - count);
        } catch (Exception e) {
            e.printStackTrace();
            message = "上传失败:第" + (count + 1) + "行存在不符合规定的数据...";
        }

        return message;
    }

    /**
     * 用户导出数据
     *
     * @param _search 是否是查找的，注意查找需要用到多条件查找，条件字符串已经放入到filters中
     * @param filters 条件字符串
     * @param page    当前页数
     * @param rows    显示条数
     * @return
     */
    public HSSFWorkbook export(Boolean _search, String filters, int page, int rows) {
        HSSFWorkbook workbook = null;
        List<Classroom> classroomList = new ArrayList<>();

        List<String> headerList = excelService.getExcelHeader("classroom");

        //用户导出数据分四个情况
        //(1)用户只导出非查找而来的当前页数数据，此时_search为false,filters为null,page为一个大于0的数
        if (!_search && filters == null && page > 0) {
            List<Object> objectList = findByPage(page, rows).getRows();
            for (Object o : objectList) {
                Classroom classroom = (Classroom) o;
                classroomList.add(classroom);
            }
        }
        //(2)用户导出非查找而来的所有页面的数据，此时_search为false,filters为null,但限定page=-1
        if (!_search && filters == null && page == -1) {
            classroomList = classroomDao.findAll();
        }

        //(3)用户导出查找而来的当前页面的数据，此时_search为true,filters不为null,page为一个大于0的数
        //(4)用户导出查找而来所有页面的数据，此时_search为true,filters不为null,但限定page=-1
        //这俩种可以合并，反正SqlJointUtil工具类中可以进行区分并产生不同返回结果
        if (_search && filters != null) {
            List<Object> objectList = findByPage(_search, filters, page, rows).getRows();
            for (Object o : objectList) {
                Classroom classroom = (Classroom) o;
                classroomList.add(classroom);
            }
        }

        List<List<Object>> content = new ArrayList<List<Object>>();
        for (Classroom classroom : classroomList) {
            List<Object> row = new ArrayList<>();
            row.add(classroom.getCl_id());
            row.add(classroom.getCl_name());
            row.add(classroom.getCl_person_num());
            row.add(classroom.getCl_major_id());
            row.add(classroom.getCl_edu_len());
            row.add(classroom.getCl_grade());
            row.add(classroom.getCl_instructor_name());
            row.add(classroom.getCl_instructor_phone());
            row.add(classroom.getCl_edu_form());
            row.add(classroom.getCl_major());

            content.add(row);

        }
        workbook = ExcelUtil.exportContent(headerList, content);

        return workbook;
    }
}
