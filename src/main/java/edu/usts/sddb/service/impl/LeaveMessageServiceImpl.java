package edu.usts.sddb.service.impl;


import edu.usts.sddb.dao.LeaveMessageDao;
import edu.usts.sddb.dao.UserDao;
import edu.usts.sddb.entity.LeaveMessage;
import edu.usts.sddb.entity.pack.LeaveMessageIntro;
import edu.usts.sddb.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("leaveMessageService")
public class LeaveMessageServiceImpl implements LeaveMessageService {
    @Autowired
    LeaveMessageDao leaveMessageDao;

    @Autowired
    UserDao userDao;

    /**
     * 发表或回复一个留言
     */
    @Override
    public int leave(LeaveMessage leaveMessage) {
        return leaveMessageDao.add(leaveMessage);
    }

    /**
     * 取出一些有次id参与的留言记录
     */
    @Override
    public List<LeaveMessageIntro> findSome(String id, int number) {
        //用于直接返回的list
        List<LeaveMessageIntro> introList = new ArrayList<>();

        //用于返回所要求的留言记录,但此时还未形成最终的格式
        List<LeaveMessage> t_list = new ArrayList<>();

        //所有的有关此id的留言记录
        List<LeaveMessage> messageList = leaveMessageDao.findSome(id);

        //现在进行筛选，选择两个人留言记录中的最后一条留言
        //此list用于保存已经交流过的人的id
        List<String> idList = new ArrayList<>();


        for (int i = 0; i < messageList.size(); i++) {
            String author = messageList.get(i).getLe_author_id();
            String target = messageList.get(i).getLe_target_id();
            if (!author.equals(id)) {
                if (!idList.contains(author)) {
                    idList.add(author);
                    t_list.add(messageList.get(i));
                }
            } else {
                if (!idList.contains(target)) {
                    idList.add(target);
                    t_list.add(messageList.get(i));
                }
            }
        }

        //转换格式
        List<LeaveMessageIntro> preIntroList = new ArrayList<>();
        preIntroList = changeToIntro(t_list);

        //取出前number条
        int size = preIntroList.size() > number ? number : preIntroList.size();
        for (int k = 0; k < size; k++) {
            introList.add(preIntroList.get(k));
        }

        return introList;
    }

    /**
     * 抽取p1与p2的留言记录
     */
    @Override
    public List<LeaveMessageIntro> findRecord(String p1, String p2) {
        List<LeaveMessage> original = leaveMessageDao.findRecord(p1, p2);
        return changeToIntro(original);
    }


    /**
     * 将留言类转换为留言具体类，即需要留言人以及留言目标的姓名
     */
    public List<LeaveMessageIntro> changeToIntro(List<LeaveMessage> original) {
        List<LeaveMessageIntro> introList = new ArrayList<>();
        //转换格式
        for (int m = 0; m < original.size(); m++) {
            String author_name = userDao.findByUserId(original.get(m).getLe_author_id()).getUs_name();
            String target_name = userDao.findByUserId(original.get(m).getLe_target_id()).getUs_name();
            LeaveMessage leaveMessage = original.get(m);
            LeaveMessageIntro intro = new LeaveMessageIntro();
            intro.setAuthor_name(author_name);
            intro.setTarget_name(target_name);
            intro.setLeaveMessage(leaveMessage);
            introList.add(intro);
        }
        return introList;

    }


    /**
     * 将p1发给p2的留言记录设置为p2已读
     */
    @Override
    public int setRead(String p1, String p2) {
        return leaveMessageDao.setRead(p1, p2);
    }
}
