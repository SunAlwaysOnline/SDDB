package edu.usts.sddb.util;

import java.util.List;

import edu.usts.sddb.entity.pack.QueryCondition;
import edu.usts.sddb.entity.pack.Rules;

/**
 * @author sun 解决复杂的sql拼接，暂时没找到MyBatis动态sql的解决方案
 */
public class SqlJointUtil {
    public static String getSqlByFilters(QueryCondition filters, int pre, int rows, boolean isGetSum,
                                         String tableName) {
        String sqlStr = "";
        if (!isGetSum) {
            sqlStr = "select * from " + tableName + " where ";
        } else {
            sqlStr = "select count(*) from " + tableName + " where ";
        }


        // 先获得groupOp,即是and还是or
        String groupOp = filters.getGroupOp();

        // 遍历filters.rules获得查询条件
        List<Rules> rules = filters.getRules();
        for (int i = 0; i < rules.size(); i++) {
            // 获得查询变量
            String field = rules.get(i).getField();
            // 获得操作
            String op = rules.get(i).getOp();
            // 获得查询的值
            String data = rules.get(i).getData();

            if (i == 0) {
                if (getOp(op).equals("=")) {
                    sqlStr += field + " " + getOp(op) + " '" + data + "' ";
                } else {
                    sqlStr += field + " " + getOp(op) + " '%" + data + "%' ";
                }
            } else {
                if (getOp(op).equals("=")) {
                    sqlStr += groupOp + " " + field + " " + getOp(op) + " '" + data + "' ";
                } else {
                    sqlStr += groupOp + " " + field + " " + getOp(op) + " '%" + data + "%' ";

                }
            }
        }

        //小于0代表不使用分页技术，直接查找全部，导出此次查询的所有数据
        //而此时需要使用分页技术，此时导出当前页数据，而非全部数据
        if ((!isGetSum) && pre != -2 * rows) {
            sqlStr += "limit " + pre + "," + rows;
        }
        return sqlStr;
    }

    public static String getOp(String tempOp) {
        String op = "";
        switch (tempOp) {
            case "eq":
                op = "=";
                break;

            case "cn":
                op = "like";
                break;
        }
        return op;

    }

}
