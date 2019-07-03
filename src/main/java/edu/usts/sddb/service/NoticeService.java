package edu.usts.sddb.service;

import edu.usts.sddb.entity.Notice;
import edu.usts.sddb.entity.pack.State;

import java.util.List;

public interface NoticeService {
    public List<Notice> findSome(int number);

    public Notice findOne(int no_id);

    public State handleNotice(String oper, Notice notice);
}
