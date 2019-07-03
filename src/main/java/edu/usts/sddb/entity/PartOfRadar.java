package edu.usts.sddb.entity;

public class PartOfRadar {
	private String radarName;
	private int radarScore;

	public String getRadarName() {
		return radarName;
	}

	public void setRadarName(String radarName) {
		this.radarName = radarName;
	}

	public int getRadarScore() {
		return radarScore;
	}

	public void setRadarScore(int radarScore) {
		this.radarScore = radarScore;
	}

	@Override
	public String toString() {
		return "PartOfRadar [radarName=" + radarName + ", radarScore=" + radarScore + "]";
	}

}
