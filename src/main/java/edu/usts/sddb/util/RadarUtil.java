package edu.usts.sddb.util;

public class RadarUtil {

	/**
	 * author sun
	 *
	 * @param score 传入的分数
	 * @return 分数对应的建议等级
	 */
	public static String getLevelByScore(int score) {
		if(score>=75){
			return "优";
		}
		if(score>=60){
			return "良";
		}
		if(score>=45){
			return "中";
		}
		return "差";
	}
}
