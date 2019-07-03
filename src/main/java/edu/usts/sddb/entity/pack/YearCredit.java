package edu.usts.sddb.entity.pack;

public class YearCredit {
	private String year;
	private double credit_gain;
	private double credit_ungain;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public double getCredit_gain() {
		return credit_gain;
	}

	public void setCredit_gain(double credit_gain) {
		this.credit_gain = credit_gain;
	}

	public double getCredit_ungain() {
		return credit_ungain;
	}

	public void setCredit_ungain(double credit_ungain) {
		this.credit_ungain = credit_ungain;
	}

	@Override
	public String toString() {
		return "YearCredit [year=" + year + ", credit_gain=" + credit_gain + ", credit_ungain=" + credit_ungain + "]";
	}

}
