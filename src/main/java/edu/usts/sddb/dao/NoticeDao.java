package edu.usts.sddb.dao;

import edu.usts.sddb.entity.Notice;

import java.util.List;

public interface NoticeDao {
    public List<Notice> findTopSome(int number);

    public Notice findOne(int no_id);

    public int addNotice(Notice notice);

    public int delNotice(int no_id);

    public int updateNotice(Notice notice);
}
