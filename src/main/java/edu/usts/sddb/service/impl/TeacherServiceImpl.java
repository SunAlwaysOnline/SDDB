package edu.usts.sddb.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.usts.sddb.entity.pack.ObjectQuery;
import edu.usts.sddb.service.ExcelService;
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

import edu.usts.sddb.dao.TeacherDao;
import edu.usts.sddb.entity.Major;
import edu.usts.sddb.entity.Teacher;
import edu.usts.sddb.entity.pack.MajorQuery;
import edu.usts.sddb.entity.pack.QueryCondition;
import edu.usts.sddb.entity.pack.TeacherQuery;
import edu.usts.sddb.service.TeacherService;
import edu.usts.sddb.util.QueryUtil;
import edu.usts.sddb.util.SqlJointUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    @Qualifier("teacherDao")
    TeacherDao teacherDao;

    @Autowired
    ExcelService excelService;

    @Override
    public ObjectQuery findByPage(Boolean _search, String filters, int page, int rows) {
        System.out.println("page=" + page);
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

            String sql = SqlJointUtil.getSqlByFilters(queryCondition, (page - 1) * rows, rows, false, "t_teacher");
            System.out.println(sql);
            List<Object> list = teacherDao.findByFilters(sql);
            String getSumSql = SqlJointUtil.getSqlByFilters(queryCondition, (page - 1) * rows, rows, true, "t_teacher");
            System.out.println(getSumSql);
            int records = teacherDao.findByFiltersSum(getSumSql);
            int total = QueryUtil.getTotalPage(records, rows);
            ObjectQuery tq = new ObjectQuery(page, total, records, list);
            return tq;
        }

    }

    @Override
    public ObjectQuery findByPage(int page, int rows) {
        List<Object> list = teacherDao.findByPage((page - 1) * rows, rows);
        int records = teacherDao.getSum();
        int total = QueryUtil.getTotalPage(records, rows);
        ObjectQuery tq = new ObjectQuery(page, total, records, list);
        return tq;
    }

    @Override
    public String handleTeacher(String oper, Teacher teacher, String[] id) {
        teacher = DataFormatUtil.checkNull(teacher);
        switch (oper) {
            case "edit":
                // 按te_id进行更改教师数据
                if (id != null) {
                    teacher.setTe_id(id[0]);
                }
                try {
                    int editAffectedRow = teacherDao.edit(teacher);
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
                    teacherDao.del(id[i]);
                    count++;
                }
                String str = count + "条成功删除" + (id.length - count) + "条删除失败";
                System.out.println(str);
                return str;

            case "add":
                try {
                    // 新增学生对象
                    int addAffectedRow = teacherDao.add(teacher);
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
            List<Object> list = ExcelData.getDataByExcel(is, "teacher");
            for (Object o : list) {
                Teacher teacher = (Teacher) o;
                count++;
                teacherDao.add(teacher);
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
        List<Teacher> teacherList = new ArrayList<>();

        List<String> headerList = excelService.getExcelHeader("teacher");

        //用户导出数据分四个情况
        //(1)用户只导出非查找而来的当前页数数据，此时_search为false,filters为null,page为一个大于0的数
        if (!_search && filters == null && page > 0) {
            List<Object> objectList = findByPage(page, rows).getRows();
            for (Object o : objectList) {
                Teacher teacher = (Teacher) o;
                teacherList.add(teacher);
            }
        }
        //(2)用户导出非查找而来的所有页面的数据，此时_search为false,filters为null,但限定page=-1
        if (!_search && filters == null && page == -1) {
            teacherList = teacherDao.findAll();
        }

        //(3)用户导出查找而来的当前页面的数据，此时_search为true,filters不为null,page为一个大于0的数
        //(4)用户导出查找而来所有页面的数据，此时_search为true,filters不为null,但限定page=-1
        //这俩种可以合并，反正SqlJointUtil工具类中可以进行区分并产生不同返回结果
        if (_search && filters != null) {
            List<Object> objectList = findByPage(_search, filters, page, rows).getRows();
            for (Object o : objectList) {
                Teacher teacher = (Teacher) o;
                teacherList.add(teacher);
            }
        }

        List<List<Object>> content = new ArrayList<List<Object>>();
        for (Teacher teacher : teacherList) {
            List<Object> row = new ArrayList<>();
            row.add(teacher.getTe_id());
            row.add(teacher.getTe_name());
            row.add(teacher.getTe_sex());
            row.add(teacher.getTe_birth());
            row.add(teacher.getTe_department());
            row.add(teacher.getTe_phone());
            row.add(teacher.getTe_email());
            row.add(teacher.getTe_edu_background());
            row.add(teacher.getTe_edu_degree());
            row.add(teacher.getTe_pro_title());
            row.add(teacher.getTe_politics_status());

            content.add(row);

        }
        workbook = ExcelUtil.exportContent(headerList, content);

        return workbook;
    }


}
