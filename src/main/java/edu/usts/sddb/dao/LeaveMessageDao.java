package edu.usts.sddb.dao;


import edu.usts.sddb.entity.LeaveMessage;

import java.util.List;

public interface LeaveMessageDao {

    public int add(LeaveMessage leaveMessage);

    public List<LeaveMessage> findSome(String id);

    //抽取p1与p2之间的留言记录
    public List<LeaveMessage> findRecord(String p1, String p2);

    //将p1发给p2的留言记录设置为p2已读
    public int setRead(String p1, String p2);

}
