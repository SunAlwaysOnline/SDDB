package edu.usts.sddb.controller;


import edu.usts.sddb.entity.LeaveMessage;
import edu.usts.sddb.entity.User;
import edu.usts.sddb.entity.pack.LeaveMessageIntro;
import edu.usts.sddb.service.LeaveMessageService;
import edu.usts.sddb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/leaveMessage")
public class LeaveMessageController {

    @Autowired
    UserService userService;

    @Autowired
    LeaveMessageService leaveMessageService;

    @RequestMapping("/getTarget")
    @ResponseBody
    public List<User> getTarget() {
        return userService.findAll();
    }

    @RequestMapping("/leave")
    @ResponseBody
    public Boolean leave(LeaveMessage leaveMessage) {
        try {
            leaveMessageService.leave(leaveMessage);
            //留言成功后，通知前端可显示此次留言
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //获取有关id的留言记录
    @RequestMapping("/getMessage")
    @ResponseBody
    public List<LeaveMessageIntro> getMessage(String id, int number) {
        return leaveMessageService.findSome(id,number);
    }

    //获取p1与p2的留言记录
    @RequestMapping("getRecord")
    @ResponseBody
    public List<LeaveMessageIntro> getRecord(String p1, String p2) {
        return leaveMessageService.findRecord(p1, p2);
    }

    //将p1发给p2的留言记录设置为p2已读
    @RequestMapping("setRead")
    @ResponseBody
    public Boolean setRead(String p1, String p2) {
        try {
            leaveMessageService.setRead(p1, p2);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
