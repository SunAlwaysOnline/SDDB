package edu.usts.sddb.util;

public class DataFilter {
    /**
     * @param str 前台传入的参数
     * @return 经过危险参数检验后，是否通过
     * @throws Exception
     */
    public static boolean isPass(String str) throws Exception {
        String[] danger = {"select", "insert", "update", "delete", "drop", "create", "--"};
        String[] param = str.split(" ");
        for (String d : danger) {
            for (String p : param) {
                //忽略大小写进行检验
                if (d.equalsIgnoreCase(p)) {
                    return false;
                }
            }
        }
        return true;
    }
}
