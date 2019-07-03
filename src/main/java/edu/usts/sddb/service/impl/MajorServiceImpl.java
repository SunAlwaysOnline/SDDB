package edu.usts.sddb.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.usts.sddb.entity.pack.ObjectQuery;
import edu.usts.sddb.entity.pack.QueryCondition;
import edu.usts.sddb.service.ExcelService;
import edu.usts.sddb.util.DataFormatUtil;
import edu.usts.sddb.util.Excel.ExcelData;
import edu.usts.sddb.util.Excel.ExcelUtil;
import edu.usts.sddb.util.ExceptionUtil;
import edu.usts.sddb.util.SqlJointUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.usts.sddb.dao.MajorDao;
import edu.usts.sddb.entity.Major;
import edu.usts.sddb.entity.pack.MajorQuery;
import edu.usts.sddb.service.MajorService;
import edu.usts.sddb.util.QueryUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service("majorService")
public class MajorServiceImpl implements MajorService {

    @Autowired
    @Qualifier("majorDao")
    MajorDao majorDao;

    @Autowired
    ExcelService excelService;

    public ObjectQuery findByPage(int page, int rows) {
        // 本次操作不是搜索，而是按条件进行查询
        // 查询全部
        // page 当前所处页数 rows 每页显示的条数
        List list = majorDao.findByPage((page - 1) * rows, rows);
        // 获得总记录数
        int records = majorDao.getSum();
        // 获得总页数
        int total = QueryUtil.getTotalPage(records, rows);
        // 第一个参数为当前页数，第二个为总页数，第三个参数为总记录数，第四个参数为模型对象
        ObjectQuery oq = new ObjectQuery(page, total, records, list);
        return oq;
    }


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

            String sql = SqlJointUtil.getSqlByFilters(queryCondition, (page - 1) * rows, rows, false, "t_major");

            List list = majorDao.findByFilters(sql);
            String getSumSql = SqlJointUtil.getSqlByFilters(queryCondition, (page - 1) * rows, rows, true, "t_major");
            int records = majorDao.findByFiltersSum(getSumSql);
            int total = QueryUtil.getTotalPage(records, rows);
            ObjectQuery oq = new ObjectQuery(page, total, records, list);
            return oq;

        }
    }

    @Override
    public String handle(String oper, Major major, String[] id) {
        major = DataFormatUtil.checkNull(major);
        // oper有三种操作 add,del,edit,
        switch (oper) {
            case "edit":
                // 按ma_id进行更改发展建议数据
                //由于major的id可能为空，把id[0]赋给major
                if (id != null) {
                    major.setMa_id(id[0]);
                }
                try {
                    int editAffectedRow = majorDao.edit(major);
                    if (editAffectedRow == 1) {
                        System.out.println("修改成功");
                        return "success";
                    }
                } catch (Exception e) {
                    return ExceptionUtil.HandleDataException(e);
                }
                break;

            case "del":
                // 会按ma_id来删除，考虑到存在多选，此时主键id是数组
                int count = 0;
                for (int i = 0; i < id.length; i++) {
                    majorDao.del(id[i]);
                    count++;
                }
                String str = count + "条成功删除" + (id.length - count) + "条删除失败";
                System.out.println(str);
                return str;

            case "add":
                // 新增发展建议对象
                try {
                    int addAffectedRow = majorDao.add(major);
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
            List<Object> list = ExcelData.getDataByExcel(is, "major");
            for (Object o : list) {
                Major major = (Major) o;
                count++;
                majorDao.add(major);
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
        List<Major> majorList = new ArrayList<>();

        List<String> headerList = excelService.getExcelHeader("major");

        //用户导出数据分四个情况
        //(1)用户只导出非查找而来的当前页数数据，此时_search为false,filters为null,page为一个大于0的数
        if (!_search && filters == null && page > 0) {
            List<Object> objectList = findByPage(page, rows).getRows();
            for (Object o : objectList) {
                Major major = (Major) o;
                majorList.add(major);
            }
        }
        //(2)用户导出非查找而来的所有页面的数据，此时_search为false,filters为null,但限定page=-1
        if (!_search && filters == null && page == -1) {
            majorList = majorDao.findAll();
        }

        //(3)用户导出查找而来的当前页面的数据，此时_search为true,filters不为null,page为一个大于0的数
        //(4)用户导出查找而来所有页面的数据，此时_search为true,filters不为null,但限定page=-1
        //这俩种可以合并，反正SqlJointUtil工具类中可以进行区分并产生不同返回结果
        if (_search && filters != null) {
            List<Object> objectList = findByPage(_search, filters, page, rows).getRows();
            for (Object o : objectList) {
                Major major = (Major) o;
                majorList.add(major);
            }
        }

        List<List<Object>> content = new ArrayList<List<Object>>();
        for (Major major : majorList) {
            List<Object> row = new ArrayList<>();

            row.add(major.getMa_id());
            row.add(major.getMa_name());
            row.add(major.getMa_eng());
            row.add(major.getMa_academy_id());
            row.add(major.getMa_category());
            row.add(major.getMa_edu_len());
            row.add(major.getMa_edu_degree());
            row.add(major.getMa_level());

            content.add(row);

        }
        workbook = ExcelUtil.exportContent(headerList, content);

        return workbook;
    }
}
