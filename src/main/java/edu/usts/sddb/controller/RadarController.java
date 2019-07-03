package edu.usts.sddb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.usts.sddb.entity.Advice;
import edu.usts.sddb.entity.PartOfRadar;
import edu.usts.sddb.service.RadarService;

@Controller
@RequestMapping("/radar")
public class RadarController {
    @Autowired
    @Qualifier("radarService")
    RadarService radarService;

    @RequestMapping("/person.do")
    @ResponseBody
    public List<PartOfRadar> generate(String id) {
        List<PartOfRadar> radar = radarService.generaterRadar(id);
        return radar;
    }

    @RequestMapping("/advice.do")
    @ResponseBody
    public List<Advice> getAdvice(int study, int sport, int sciense, int volunteer) {
        List<Advice> advice = radarService.getAdvice(study, sport, sciense, volunteer);
        return advice;
    }

}
