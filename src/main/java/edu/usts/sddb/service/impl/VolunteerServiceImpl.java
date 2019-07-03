package edu.usts.sddb.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.usts.sddb.dao.VolunteerDao;
import edu.usts.sddb.entity.Advice;
import edu.usts.sddb.entity.Volunteer;
import edu.usts.sddb.entity.assist.DBComment;
import edu.usts.sddb.entity.pack.ObjectQuery;
import edu.usts.sddb.entity.pack.QueryCondition;

import edu.usts.sddb.service.ExcelService;
import edu.usts.sddb.service.VolunteerService;
import edu.usts.sddb.util.*;
import edu.usts.sddb.util.Excel.ExcelData;
import edu.usts.sddb.util.Excel.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

@Service("volunteerService")
public class VolunteerServiceImpl implements VolunteerService {

    @Autowired
    VolunteerDao volunteerDao;

    @Autowired
    ExcelService excelService;


    //由学号获取志愿时长
    @Override
    public Double findTimeById(String id) {
        Double time = null;
        try {
            time = volunteerDao.findTimeById(id);
            //找不到该同学的支援数据，则默认为0
        } catch (Exception e) {
            time = 0.0;
        }

        return time;
    }

    //获取志愿时长在班级、年级、学院内的排名
    @Override
    public Map<String, Double> getVolunteerRank(String id) {
        Map<String, Double> map = new LinkedHashMap<>();
        Double all_time = findTimeById(id);
        if (null == all_time) {
            all_time = 0.0;
        }
        map.put("all_time", all_time);

        //获得此学号所在班级的所有人的志愿数据，有可能志愿数据中压根就没有这个人的数据
        //那么使用排名除以总人数时，会出现分母为0，即结果为Infinity(无穷大)
        //因此产生数据格式转换错误，当产生这样的异常时，直接使得排名为前100%
        List<Volunteer> class_list = volunteerDao.getClassRank(id);

        int i = 0;
        for (; i < class_list.size(); i++) {
            if (class_list.get(i).getV_student_id().equals(id)) {
                break;
            }
        }

        Double class_rank = (double) i / class_list.size();

        try {
            class_rank = DataFormatUtil.doubleFormat(class_rank * 100, 1);
        } catch (NumberFormatException e) {
            class_rank = 100.0;
        }
        map.put("class_rank", class_rank);

        List<Volunteer> grade_list = volunteerDao.getGradeRank(id);
        int m = 0;
        for (; m < grade_list.size(); m++) {
            if (grade_list.get(m).getV_student_id().equals(id)) {
                break;
            }
        }
        Double grade_rank = (double) m / grade_list.size();

        try {
            grade_rank = DataFormatUtil.doubleFormat(grade_rank * 100, 1);
        } catch (NumberFormatException e) {
            grade_rank = 100.0;
        }
        map.put("grade_rank", grade_rank);

        List<Volunteer> academy_list = volunteerDao.getAcademyRank(id);
        int n = 0;
        for (; n < academy_list.size(); n++) {
            if (academy_list.get(n).getV_student_id().equals(id)) {
                break;
            }
        }
        Double academy_rank = (double) n / academy_list.size();

        try {
            academy_rank = DataFormatUtil.doubleFormat(academy_rank * 100, 1);
        } catch (NumberFormatException e) {
            academy_rank = 100.0;
        }
        map.put("academy_rank", academy_rank);

        return map;
    }


    //----------------------------------数据管理---------------------------------------------------------

    public ObjectQuery findByPage(int page, int rows) {
        // 本次操作不是直接显示，而是按多条件进行查找
        // 查询全部
        // page 当前所处页数 rows 每页显示的条数
        List list = volunteerDao.findVolunteerByPage((page - 1) * rows, rows);
        // 获得总记录数
        int records = volunteerDao.getVolunteerSum();
        // 获得总页数
        int total = QueryUtil.getTotalPage(records, rows);
        // 第一个参数为当前页数，第二个为总页数，第三个参数为总记录数，第四个参数为模型对象
        ObjectQuery oq = new ObjectQuery(page, total, records, list);
        return oq;
    }


    @Override
    public ObjectQuery findByPage(Boolean _search, String filters, int page, int rows) {
        //此次操作非多条件查找
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

            String sql = SqlJointUtil.getSqlByFilters(queryCondition, (page - 1) * rows, rows, false, "t_volunteer");

            List list = volunteerDao.findByFilters(sql);
            String getSumSql = SqlJointUtil.getSqlByFilters(queryCondition, (page - 1) * rows, rows, true, "t_volunteer");
            int records = volunteerDao.findByFiltersSum(getSumSql);
            int total = QueryUtil.getTotalPage(records, rows);
            ObjectQuery oq = new ObjectQuery(page, total, records, list);
            return oq;
        }
    }

    @Override
    public String handle(String oper, Volunteer volunteer, String[] id) {
        volunteer = DataFormatUtil.checkNull(volunteer);
        // oper有三种操作 add,del,edit,
        switch (oper) {
            case "edit":
                // 按v_student_id进行更改志愿服务数据
                //由于volunteer的id可能为空，把id[0]赋给volunteer
                if (id != null) {
                    volunteer.setV_student_id(id[0]);
                }
                try {
                    int editAffectedRow = volunteerDao.edit(volunteer);
                    if (editAffectedRow == 1) {
                        System.out.println("修改成功");
                        return "success";
                    }
                } catch (Exception e) {
                    return ExceptionUtil.HandleDataException(e);
                }
                break;

            case "del":
                // 会按v_student_id来删除，考虑到存在多选，此时主键id是数组
                int count = 0;
                for (int i = 0; i < id.length; i++) {
                    volunteerDao.del(id[i]);
                    count++;
                }
                String str = count + "条成功删除" + (id.length - count) + "条删除失败";
                System.out.println(str);
                return str;

            case "add":
                // 新增发展建议对象
                try {
                    int addAffectedRow = volunteerDao.add(volunteer);
                    if (addAffectedRow == 1) {
                        System.out.println("插入成功");
                        return "success";
                    }
                } catch (Exception e) {
                    return ExceptionUtil.HandleDataException(e);
                }
        }
        return "success";
    }

    @Override
    public List<Volunteer> findVolunteerByPage(int start, int row) {
        return volunteerDao.findVolunteerByPage(start, row);
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
        try {
            is = file.getInputStream();
            List<Object> list = ExcelData.getDataByExcel(is, "volunteer");
            int count = 0;
            for (Object o : list) {
                Volunteer volunteer = (Volunteer) o;
                volunteerDao.add(volunteer);
                count++;
            }
            message = "上传成功的数目为" + count + "上传失败的数目为" + (list.size() - count);
        } catch (Exception e) {
            e.printStackTrace();
            message = "上传失败:" + e.getMessage();
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
        List<Volunteer> volunteerList = new ArrayList<>();

        List<String> headerList = excelService.getExcelHeader("volunteer");

        //用户导出数据分四个情况
        //(1)用户只导出非查找而来的当前页数数据，此时_search为false,filters为null,page为一个大于0的数
        if (!_search && filters == null && page > 0) {
            List<Object> objectList = findByPage(page, rows).getRows();
            for (Object o : objectList) {
                Volunteer volunteer = (Volunteer) o;
                volunteerList.add(volunteer);
            }
        }
        //(2)用户导出非查找而来的所有页面的数据，此时_search为false,filters为null,但限定page=-1
        if (!_search && filters == null && page == -1) {
            volunteerList = volunteerDao.findAll();
        }

        //(3)用户导出查找而来的当前页面的数据，此时_search为true,filters不为null,page为一个大于0的数
        //(4)用户导出查找而来所有页面的数据，此时_search为true,filters不为null,但限定page=-1
        //这俩种可以合并，反正SqlJointUtil工具类中可以进行区分并产生不同返回结果
        if (_search && filters != null) {
            List<Object> objectList = findByPage(_search, filters, page, rows).getRows();
            for (Object o : objectList) {
                Volunteer volunteer = (Volunteer) o;
                volunteerList.add(volunteer);
            }
        }

        List<List<Object>> content = new ArrayList<List<Object>>();
        for (Object ob : volunteerList) {
            List<Object> row = new ArrayList<>();
            Volunteer volunteer = (Volunteer) ob;
            row.add(volunteer.getV_student_id());
            row.add(volunteer.getV_student_name());
            row.add(volunteer.getV_time());
            content.add(row);

        }
        workbook = ExcelUtil.exportContent(headerList, content);

        return workbook;
    }
}
