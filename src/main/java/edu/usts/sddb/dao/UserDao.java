package edu.usts.sddb.dao;

import edu.usts.sddb.entity.User;

import java.util.List;


public interface UserDao {

    public User findByUserId(String us_id);

    public List<User> findAll();

    public int addUser(User user);

    public int resetPassword(User user);

    public void addVisitNumber(String us_id);

    public int edit(User user);

    public int editAllowDetail(User user);
}
