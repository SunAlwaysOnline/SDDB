package edu.usts.sddb.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.usts.sddb.entity.Certificate;
import edu.usts.sddb.entity.Competition;

public class CheckData {

	/**
	 * author sun
	 * 证书表去重
	 * 若不存在则返回true,否则返回false
	 * @param list
	 * @param ceName
	 */
	public static boolean checkCeRemoveSame(List<String> list, String ceName) {
		if (!list.contains(ceName)) {
			list.add(ceName);
			return true;
		}
		return false;
	}

	/**
	 * 证书表数据验证
	 * 
	 * @param list
	 * @return
	 */
	public static List<Certificate> checkCe(List<Certificate> list) {
		List<Certificate> l = new ArrayList<Certificate>();
		//每次判断证书名称是否已经存在
		List<String> ceNameList = new ArrayList<String>();
		for (Certificate c : list) {
			try {
				int score = Integer.parseInt(c.getCe_score());
				if (score >= 425&&checkCeRemoveSame(ceNameList, c.getCe_name())) {
					l.add(c);
				}
			} catch (NumberFormatException e) {
				// System.out.println("成绩无法转为数字");
				if (c.getCe_score().equals("合格")&&checkCeRemoveSame(ceNameList, c.getCe_name())) {
					l.add(c);
				} else if (c.getCe_score().equals("及格")&&checkCeRemoveSame(ceNameList, c.getCe_name())) {
					l.add(c);
				} else if (c.getCe_score().equals("良好")&&checkCeRemoveSame(ceNameList, c.getCe_name())) {
					l.add(c);
				} else if (c.getCe_score().equals("优秀")&&checkCeRemoveSame(ceNameList, c.getCe_name())) {
					l.add(c);
				}
			} catch (Exception e) {
				// System.out.println("证书数据格式化出现异常！");
			}

		}
		return l;
	}
}
