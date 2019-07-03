package edu.usts.sddb.util.Task;


import java.util.ArrayList;
import java.util.List;

/**
 * 用来存储访问用户主页的访问池
 * 每隔12个小时清空ip池
 * 目的：12个小时内使用相同的ip访问某个用户主页，浏览次数不会增加
 * String:访问者from+"-"+被访问者to的形式
 */
public class ViewPool {
    //ip池
    public static List<String> viewList;

    static {
        viewList = new ArrayList<>();
    }

    public static void add(String from, String to) {
        viewList.add(from + "-" + to);
    }

    public static boolean contain(String from, String to) {
        return viewList.contains(from + "-" + to);
    }

    public static void clear() {
        viewList.clear();
    }
}
