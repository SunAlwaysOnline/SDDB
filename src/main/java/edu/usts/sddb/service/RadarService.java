package edu.usts.sddb.service;

import java.util.List;

import edu.usts.sddb.entity.Advice;
import edu.usts.sddb.entity.PartOfRadar;

public interface RadarService {
    public List<PartOfRadar> generaterRadar(String id);

    // 由具体分数获取建议
    public List<Advice> getAdvice(int study, int sport, int sciense, int volunteer);
}
