package edu.usts.sddb.entity.pack;

public class Rules {
	private String field;
	private String op;
	private String data;

	public Rules() {
		super();
	}

	public Rules(String field, String op, String data) {
		super();
		this.field = field;
		this.op = op;
		this.data = data;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Rules [field=" + field + ", op=" + op + ", data=" + data + "]";
	}

}
