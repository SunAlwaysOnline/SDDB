package edu.usts.sddb.service.impl;

import edu.usts.sddb.dao.NoticeDao;
import edu.usts.sddb.dao.UserDao;
import edu.usts.sddb.entity.Notice;
import edu.usts.sddb.entity.pack.State;
import edu.usts.sddb.service.NoticeService;
import edu.usts.sddb.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    @Qualifier("noticeDao")
    NoticeDao noticeDao;

    @Autowired
    UserDao userDao;


    @Override
    public List<Notice> findSome(int number) {
        List<Notice> notices = noticeDao.findTopSome(number);
        for (Notice n : notices) {
            String name = userDao.findByUserId(n.getNo_user_id()).getUs_name();
            n.setNo_user_name(name);
        }
        return notices;
    }

    @Override
    public Notice findOne(int no_id) {
        return noticeDao.findOne(no_id);
    }

    @Override
    public State handleNotice(String oper, Notice notice) {
        State state = new State();
        switch (oper) {
            case "add":
                try {
                    int i = noticeDao.addNotice(notice);
                    if (i == 1) {
                        state.setSuccess(true);
                        state.setInfo("添加成功");
                        return state;
                    }
                } catch (Exception e) {
                    state.setSuccess(false);
                    state.setInfo(ExceptionUtil.HandleDataException(e));
                    return state;
                }

            case "edit":
                //更改公告时，不更改第一发布人
                try {
                    int i = noticeDao.updateNotice(notice);
                    if (i == 1) {
                        state.setSuccess(true);
                        state.setInfo("修改成功");
                        return state;
                    }
                } catch (Exception e) {
                    state.setSuccess(false);
                    state.setInfo(ExceptionUtil.HandleDataException(e));
                    return state;
                }
            case "del":
                try {
                    int i = noticeDao.delNotice(notice.getNo_id());
                    if (i == 1) {
                        state.setSuccess(true);
                        state.setInfo("删除成功");
                        return state;
                    }
                } catch (Exception e) {
                    state.setSuccess(false);
                    state.setInfo(ExceptionUtil.HandleDataException(e));
                }
        }
        return state;
    }

//    public String setAttach(String attach) {
//        String str[] = attach.split(";");
//        String newStr = "";
//        for (int i = 0; i < str.length; i++) {
//            //由于Linux下和Windows下文件分隔符不同，此处规定数据库里存入^-^代替/或者\
//            String separator = "^-^";
//            String pre = str[i].substring(str[i].lastIndexOf("^-^") + separator.length());
//            newStr += pre + ":" + str[i].replace("^-^", File.separator) + ";";
//        }
//        return newStr;
//    }

//    public static void main(String[] args) {
//        String str = "f:^-^a^-^abc.doc;d:^-^b^-^qwe.xsl";
//        String newStr = new NoticeServiceImpl().setAttach(str);
//        System.out.println(newStr);
//        //System.out.println(File.separator);
//    }
}
