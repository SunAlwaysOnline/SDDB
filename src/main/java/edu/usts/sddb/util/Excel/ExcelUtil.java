package edu.usts.sddb.util.Excel;


import edu.usts.sddb.constant.ExcelConstant;
import edu.usts.sddb.entity.Advice;
import org.apache.http.HttpResponse;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Excel的工具类
 */
public class ExcelUtil {
    //2007版本以下的
    public static String EXCEL2007L = ".xls";
    //2007版本以上的
    public static String EXCEL2007H = ".xlsx";


    /**
     * 导出Excel
     *
     * @return 低版本的Excel
     */
    public static HSSFWorkbook exportContent(List<String> headerList, List<List<Object>> contentList) {
        //创建工作表
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作簿sheet,sheet名为默认值
        HSSFSheet sheet = workbook.createSheet();
        //创建列头
        createSheetHeader(sheet, headerList);
        //创建内容
        createSheetContent(sheet, contentList);
        //调整列宽
        setColumnSize(sheet);
        return workbook;
    }


    /**
     * 导出步骤第一步之创建列头
     *
     * @param sheet      工作簿
     * @param headerList 列头的list
     */
    public static void createSheetHeader(HSSFSheet sheet, List<String> headerList) {
        //在第一行创建列头行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headerList.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(headerList.get(i));
        }
    }

    /**
     * 导出步骤第二步之创建内容
     *
     * @param sheet       工作簿
     * @param contentList 每一行数据的集合
     */
    public static void createSheetContent(HSSFSheet sheet, List<List<Object>> contentList) {
        //遍历最外层list,得到行数
        for (int i = 0; i < contentList.size(); i++) {
            //第一行已经被列头占用，因此这里的i+1
            HSSFRow row = sheet.createRow(i + 1);
            //遍历内层list，得到每行的单元格数

            for (int j = 0; j < contentList.get(i).size(); j++) {
                //创建单元格
                HSSFCell cell = row.createCell(j);
                Object temp = contentList.get(i).get(j);
                //为空时，则不输出到excel中，否则excel中直接显示null
                if (null == temp) {
                    continue;
                }
                cell.setCellValue(String.valueOf(temp));
            }
        }
    }

    ////////////////以上是导出步骤，以下是导入步骤

    /**
     * @param is       输入流
     * @param fileName 文件名
     * @return
     * @throws Exception
     */
    //根据上传文件的后缀，自适应上传文件的版本
    public static Workbook getVerionWorkBook(InputStream is, String fileName) throws Exception {
        Workbook workbook = null;
        String fileVersion = fileName.substring(fileName.lastIndexOf("."));
        if (fileName.equals(EXCEL2007L)) {
            workbook = new HSSFWorkbook(is);
        } else if (fileName.equals(EXCEL2007H)) {
            workbook = new XSSFWorkbook(is);
        } else {
            throw new Exception("Upload file error");
        }
        return workbook;

    }

    /**
     * @param cell 单元格
     * @return 单元格经过格式化的值
     */
    public static Object getFormatCellValue(Cell cell) {
        Object value = null;
        if (null == cell) {
            return value;
        }
        switch (cell.getCellType()) {
            //数值型
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //如果是date类型则 ，获取该cell的date值
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    value = format.format(date);

                } else {// 纯数字
                    BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                    value = big.toString();
                }
                break;
            //字符串类型
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            // 布尔类型
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            default:
                value = cell.getStringCellValue();
        }
        return value;
    }


    //设置列的宽度
    public static void setColumnSize(HSSFSheet sheet) {
        HSSFRow row = sheet.getRow(0);
        for (int i = 0; i < row.getLastCellNum(); i++) {
            sheet.setColumnWidth(i, ExcelConstant.COLUMN_WIDTH);
        }
    }

    /**
     * 下载excel
     *
     * @param response
     * @param workbook HSSFWorkbook对象
     * @param fileName 文件名，包含后缀名
     */
    public static void downloadExcel(HttpServletResponse response, HSSFWorkbook workbook, String fileName) {
        System.out.println("已经进入到导出的程序");
        // 6.设置reponse参数
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        // 确保发送的当前文本格式
        response.setContentType("application/vnd.ms-excel");
        OutputStream ops = null;
        try {
            ops = response.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(ops);
            bos.flush();
            workbook.write(bos);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}