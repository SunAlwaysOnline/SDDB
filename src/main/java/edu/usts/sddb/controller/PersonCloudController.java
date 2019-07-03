package edu.usts.sddb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.usts.sddb.entity.Award;
import edu.usts.sddb.service.PersonAwardService;

@Controller
@RequestMapping("/data")
public class PersonCloudController {

    @Autowired
    @Qualifier("personCloudService")
    PersonAwardService personCloudService;

    @RequestMapping("/cloud.do")
    @ResponseBody
    public List<Award> cloud(String id) {
        List<Award> list = personCloudService.generate(id);
        return list;
    }


}
