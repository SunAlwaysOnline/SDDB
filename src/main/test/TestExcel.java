
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;

public class TestExcel {
/*    @Test
    public void write() throws IOException {
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("helloWord");
        XSSFRow row = sheet.createRow(2);
        XSSFCell cell = row.createCell(2, Cell.CELL_TYPE_STRING);
        cell.setCellValue("a");
        FileOutputStream fos = new FileOutputStream(new File("g:\\test.xlsx"));
        book.write(fos);
        book.close();
    }

    @Test
    public void read() throws IOException {
        FileInputStream fis = new FileInputStream(new File("g:\\test.xlsx"));
        XSSFWorkbook book = new XSSFWorkbook(fis);
        XSSFSheet sheet = book.getSheetAt(0);
        XSSFRow row = sheet.getRow(2);
        XSSFCell cell = row.getCell(2);
        String value = cell.getStringCellValue();
        System.out.println(value);
        fis.close();
        book.close();
    }

    @Test
    public void getNum() throws IOException {
        FileInputStream fis = new FileInputStream(new File("g:\\test.xlsx"));
        XSSFWorkbook book = new XSSFWorkbook(fis);
        XSSFSheet sheet = book.getSheetAt(0);
        int firstRowNum = sheet.getFirstRowNum();
        System.out.println("firstRowNum:" + firstRowNum);
        int lastRowNum = sheet.getLastRowNum();
        System.out.println("lastRowNum:" + lastRowNum);
        Row row = sheet.getRow(lastRowNum);
        int firstCellNum = row.getFirstCellNum();
        System.out.println("firstCellNum:" + firstCellNum);
        int lastCellNum = row.getLastCellNum();
        System.out.println("lastCellNum:" + lastCellNum);
    }

    @Test
    public void get() throws IOException {
        FileInputStream fis = new FileInputStream(new File("g:\\test.xlsx"));
        XSSFWorkbook book = new XSSFWorkbook(fis);
        XSSFSheet sheet = book.getSheetAt(0);
        XSSFRow row = sheet.getRow(2);
        XSSFCell cell = row.getCell(2);
        cell.setCellValue(String.valueOf(null));
    }*/

}
