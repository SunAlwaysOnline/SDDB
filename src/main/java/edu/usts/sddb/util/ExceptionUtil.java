package edu.usts.sddb.util;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

public class ExceptionUtil {

    /**
     * 处理数据增删改的异常
     *
     * @param e 异常
     * @return 提示用户的信息
     */
    public static String HandleDataException(Exception e) {
        e.printStackTrace();
        if (e instanceof DuplicateKeyException) {
            //主键重复异常
            String key = e.getMessage().split("for key ")[1].split("\n")[0];
            return "第 " + key + "列数据与已有的数据重复,请先修改再试.";
        } else if (e instanceof DataIntegrityViolationException) {
            //数据违反完整性约束
            if (e.getMessage().contains("Out of range value")) {
                //[1]空值
                String column = e.getMessage().split("column '")[1].split("' at")[0];
                return column + "列的值不能为空,请输入数据后再试.";
            } else if (e.getMessage().contains("cannot be null")) {
                String column = e.getMessage().split("Column '")[1].split("' cannot")[0];
                return column + "列的值不能为空,请输入数据后再试.";
            } else if (e.getMessage().contains("Data too long")) {
                //[2]数据过长
                String column = e.getMessage().split("column '")[1].split("' at")[0];
                return column + "列数据长度过长，请减少数据长度再试.";
            } else if (e.getMessage().contains("FOREIGN KEY")) {
                //[3]外键依赖
                String column = e.getMessage().split("FOREIGN KEY \\(`")[1].split("`\\) REFERENCES")[0];
                return column + "列数据不存在，请输入一个已经存在的值.";
            }
        }
        return "success";
    }
}
