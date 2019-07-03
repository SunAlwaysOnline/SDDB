package edu.usts.sddb.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DataFormatUtil {
    public static Double doubleFormat(double d, int i) {

        String str = "#.";
        for (int j = 0; j < i; j++) {
            str += "0";
        }
        DecimalFormat format = new DecimalFormat(str);
        String format_rate = format.format(d);
        Double temp = Double.valueOf(format_rate);
        return temp;
    }

    //将get为""时,set其为null
    public static <T> T checkNull(Object object) {
        //原对象转成泛型类
        T t_old = (T) object;
        //System.out.println(t_old);
        try {
            Method[] methods = t_old.getClass().getMethods();
            //遍历所有方法，找出set方法并执行，找出返回值为空字符串的放入list中
            List<String> setFuns = new ArrayList<String>();
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                if (method.getName().startsWith("get")) {
                    if (method.invoke(t_old) == "") {
                        setFuns.add("set" + method.getName().substring(3));
                    }
                }
            }
            //遍历setFuns集合，执行空字符串变null操作
            for (String setFun : setFuns) {
                Method method = t_old.getClass().getMethod(setFun, String.class);
                method.invoke(t_old, new Object[]{null});
            }
        } catch (Exception e) {
            System.out.println("对象转换异常");
            e.printStackTrace();
        }
        return t_old;
    }

    //get为null时,set其为""
    public static <T> T checkEmpty(Object object) {
        //原对象转成泛型类
        T t_old = (T) object;
        //System.out.println(t_old);
        try {
            Method[] methods = t_old.getClass().getMethods();
            //遍历所有方法，找出set方法并执行，找出返回值为null的放入list中
            List<String> setFuns = new ArrayList<String>();
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                if (method.getName().startsWith("get")) {
                    if (method.invoke(t_old) == null) {
                        setFuns.add("set" + method.getName().substring(3));
                    }
                }
            }
            //遍历setFuns集合，执行空字符串变null操作
            for (String setFun : setFuns) {
                Method method = t_old.getClass().getMethod(setFun, String.class);
                method.invoke(t_old, new Object[]{""});
            }
        } catch (Exception e) {
            System.out.println("对象转换异常");
            e.printStackTrace();
        }
        return t_old;
    }
}
