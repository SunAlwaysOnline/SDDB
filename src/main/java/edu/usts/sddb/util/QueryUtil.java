package edu.usts.sddb.util;

public class QueryUtil {
    /**
     * author sun
     *
     * @param records 总记录数
     * @param rows    每页显示行数
     * @return 总页数
     */
    public static int getTotalPage(int records, int rows) {
        return records % rows == 0 ? records / rows : records / rows + 1;
    }

}
