package edu.usts.sddb.controller;

import edu.usts.sddb.entity.bPack.BCourseFailAndWarn;
import edu.usts.sddb.entity.bPack.BCourseDependenceMax;
import edu.usts.sddb.entity.bPack.BScorePosition;
import edu.usts.sddb.service.BForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;


//B是big data的缩写，本控制器主要为大数据分析提供服务
@Controller
@RequestMapping("/forecast")
public class BForecastController {

    @Autowired
    BForecastService bForecastService;


    @RequestMapping("/getFailAndWarn")
    @ResponseBody
    public BCourseFailAndWarn getFailAndWarn(@RequestBody BCourseDependenceMax dependence) {
        return bForecastService.getFailAndWarn(dependence);
    }

    @RequestMapping("/getBPostGraduateOrPartyMember")
    @ResponseBody
    public HashMap<String, String> getBPostGraduateOrPartyMember(String st_id) {
        return bForecastService.getBPostGraduateOrPartyMember(st_id);
    }

    @RequestMapping("/getScorePosition")
    @ResponseBody
    public BScorePosition getScorePosition(String st_id, String result) {
        return bForecastService.getScorePosition(st_id, result);

    }
}
