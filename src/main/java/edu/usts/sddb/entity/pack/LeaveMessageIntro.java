package edu.usts.sddb.entity.pack;

import edu.usts.sddb.entity.LeaveMessage;

/**
 * 留言具体类
 * 即留言消息类加上留言作者、留言目标的姓名
 */
public class LeaveMessageIntro {
    private String author_name;
    private String target_name;
    private LeaveMessage leaveMessage;

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getTarget_name() {
        return target_name;
    }

    public void setTarget_name(String target_name) {
        this.target_name = target_name;
    }

    public LeaveMessage getLeaveMessage() {
        return leaveMessage;
    }

    public void setLeaveMessage(LeaveMessage leaveMessage) {
        this.leaveMessage = leaveMessage;
    }
}
