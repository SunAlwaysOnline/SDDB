package edu.usts.sddb.controller;

import edu.usts.sddb.entity.Notice;
import edu.usts.sddb.entity.pack.State;
import edu.usts.sddb.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    @Qualifier("noticeService")
    NoticeService noticeService;

    @ResponseBody
    @RequestMapping("/findSome.do")
    public List<Notice> findNotice(int number) {
        List<Notice> list = noticeService.findSome(number);
        return list;
    }

    @RequestMapping("/findOne.do")
    @ResponseBody
    public Notice findOne(int no_id) {
        return noticeService.findOne(no_id);
    }

    @RequestMapping("/handleNotice")
    @ResponseBody
    public State handleNotice(String oper, Notice notice) {
        return noticeService.handleNotice(oper, notice);
    }
}
