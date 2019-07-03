package edu.usts.sddb.service;

import java.util.List;


import edu.usts.sddb.entity.Award;

public interface PersonAwardService {
	public List<Award> generate(String id);
}
