package edu.usts.sddb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.usts.sddb.entity.GPAOfStudent;
import edu.usts.sddb.service.ScoreService;

@Controller
@RequestMapping("/test")
public class TestScoreController {

    @Autowired
    @Qualifier("scoreService")
    ScoreService scoreService;

    @RequestMapping("/a.do")
    @ResponseBody
    public List<GPAOfStudent> getGPA(String id) {
        List<GPAOfStudent> gpaOfStudentList = scoreService.getAllGPA(id);
        return gpaOfStudentList;
    }

}
