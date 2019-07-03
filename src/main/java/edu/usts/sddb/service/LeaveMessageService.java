package edu.usts.sddb.service;


import edu.usts.sddb.entity.LeaveMessage;
import edu.usts.sddb.entity.pack.LeaveMessageIntro;

import java.util.List;
import java.util.Map;


public interface LeaveMessageService {

    /**
     * 留言
     */
    public int leave(LeaveMessage leaveMessage);

    /**
     * 取出一些有此id参与的留言记录
     */
    public List<LeaveMessageIntro> findSome(String id,int number);

    /**
     * 取出p1与p2的留言记录
     */
    public List<LeaveMessageIntro> findRecord(String p1, String p2);

    /**
     * 将p1发给p2的留言记录设置为p2已读
     */
    public int setRead(String p1, String p2);


}
