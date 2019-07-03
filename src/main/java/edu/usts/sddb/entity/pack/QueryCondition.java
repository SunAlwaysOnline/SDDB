package edu.usts.sddb.entity.pack;

import java.util.List;

/**
 * @author sun 前台的查询条件封装类 filters:{"groupOp":"AND","rules":[ {"field":"st_id",
 *         "op":"eq","data":"15"}, {"field":"st_edu_len","op":"cn","data":"12"}
 *         ] }
 */
public class QueryCondition {
	private String groupOp;
	private List<Rules> rules;

	public QueryCondition() {
		super();
	}

	public QueryCondition(String groupOp, List<Rules> rules) {
		super();
		this.groupOp = groupOp;
		this.rules = rules;
	}

	public String getGroupOp() {
		return groupOp;
	}

	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}

	public List<Rules> getRules() {
		return rules;
	}

	public void setRules(List<Rules> rules) {
		this.rules = rules;
	}

	@Override
	public String toString() {
		return "QueryCondition [groupOp=" + groupOp + ", rules=" + rules + "]";
	}

}
