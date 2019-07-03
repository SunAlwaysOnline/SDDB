package edu.usts.sddb.entity;

public class Teacher {
	private String te_id;
	private String te_name;
	private String te_sex;
	private String te_birth;
	private String te_department;
	private String te_phone;
	private String te_email;
	private String te_edu_background;
	private String te_edu_degree;
	private String te_pro_title;
	private String te_politics_status;

	public String getTe_id() {
		return te_id;
	}

	public void setTe_id(String te_id) {
		this.te_id = te_id;
	}

	public String getTe_name() {
		return te_name;
	}

	public void setTe_name(String te_name) {
		this.te_name = te_name;
	}

	public String getTe_sex() {
		return te_sex;
	}

	public void setTe_sex(String te_sex) {
		this.te_sex = te_sex;
	}

	public String getTe_birth() {
		return te_birth;
	}

	public void setTe_birth(String te_birth) {
		this.te_birth = te_birth;
	}

	public String getTe_department() {
		return te_department;
	}

	public void setTe_department(String te_department) {
		this.te_department = te_department;
	}

	public String getTe_phone() {
		return te_phone;
	}

	public void setTe_phone(String te_phone) {
		this.te_phone = te_phone;
	}

	public String getTe_email() {
		return te_email;
	}

	public void setTe_email(String te_email) {
		this.te_email = te_email;
	}

	public String getTe_edu_background() {
		return te_edu_background;
	}

	public void setTe_edu_background(String te_edu_background) {
		this.te_edu_background = te_edu_background;
	}

	public String getTe_edu_degree() {
		return te_edu_degree;
	}

	public void setTe_edu_degree(String te_edu_degree) {
		this.te_edu_degree = te_edu_degree;
	}

	public String getTe_pro_title() {
		return te_pro_title;
	}

	public void setTe_pro_title(String te_pro_title) {
		this.te_pro_title = te_pro_title;
	}

	public String getTe_politics_status() {
		return te_politics_status;
	}

	public void setTe_politics_status(String te_politics_status) {
		this.te_politics_status = te_politics_status;
	}

	@Override
	public String toString() {
		return "Teacher [te_id=" + te_id + ", te_name=" + te_name + ", te_sex=" + te_sex + ", te_birth=" + te_birth
				+ ", te_department=" + te_department + ", te_phone=" + te_phone + ", te_email=" + te_email
				+ ", te_edu_background=" + te_edu_background + ", te_edu_degree=" + te_edu_degree + ", te_pro_title="
				+ te_pro_title + ", te_politics_status=" + te_politics_status + "]";
	}

}
