package edu.usts.sddb.controller;

import edu.usts.sddb.entity.Dorm;
import edu.usts.sddb.entity.pack.DormMemberScore;
import edu.usts.sddb.entity.pack.DormState;
import edu.usts.sddb.service.DormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dorm")
public class DormController {

    @Autowired
    DormService dormService;

    @RequestMapping("/findDistinctDorm")
    @ResponseBody
    public List<String> findDistinctDorm() {
        return dormService.findDistinctDorm();
    }

    @RequestMapping("/getDormMemberScore")
    @ResponseBody
    public List<DormMemberScore> getDormMemberScore(String d_name) {
        return dormService.getDormMemberScore(d_name);
    }

    //获取指定宿舍分数状况
    @RequestMapping("/getOneDormState")
    @ResponseBody
    public DormState getOneDormState(String d_name) {
        return dormService.getOneDormState(d_name);
    }

    //获取全体宿舍分数状况
    @RequestMapping("/getAllDormState")
    @ResponseBody
    public List<DormState> getAllDormState() {
        return dormService.getAllDormState();
    }

    //获取指定宿舍的平均分与总分排名
    @RequestMapping("/getDormRank")
    @ResponseBody
    public Map<String, String> getDormRank(String d_name) {
        return dormService.getDormRank(d_name);
    }

}
