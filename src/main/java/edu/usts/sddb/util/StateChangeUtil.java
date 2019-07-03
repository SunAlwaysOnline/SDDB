package edu.usts.sddb.util;

import java.util.ArrayList;
import java.util.List;

public class StateChangeUtil {

    //将int数组转化为list
    public static List<Integer> arrayToList(int[] index) {
        List<Integer> list = new ArrayList<>();
        for (int temp : index) {
            list.add(temp);
        }
        return list;

    }
}
