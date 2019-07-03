package edu.usts.sddb.service;

import edu.usts.sddb.entity.User;
import edu.usts.sddb.entity.pack.State;
import edu.usts.sddb.entity.pack.StudentProfile;
import edu.usts.sddb.entity.pack.TeacherProfile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    public User getUserById(String us_id);

    public State dealLogin(User user);

    public List<User> findAll();

    public State dealRegister(User user);

    public User getUserInfo(String us_id);

    public State editUserInfo(User user);

    public State uploadPhoto(String us_id, CommonsMultipartFile photo);

    public void getPhoto(HttpServletResponse response, String us_id) throws FileNotFoundException, IOException;

    public State resetPassword(User user);

    /**
     * 获取用户(学生)个人主页资料
     * 无gpa
     */
    public StudentProfile getStudentProfileNoGpa(String us_id);

    /**
     * 获取用户(学生)个人主页资料
     * 有gpa
     */
    public StudentProfile getStudentProfileHasGpa(String us_id);

    /**
     * 获取用户(教师)个人主页资料
     */
    public TeacherProfile getTeacherProfile(String us_id);


    /**
     * 增加访问人数,from用户查看to用户主页
     */
    public void addVisitNumber(String from, String to);

    /**
     * 查询允许查看详细的权限,from用户是否有权限查看to用户的详细信息
     */
    public State getAllowDetail(String from, String to);

    /**
     * 修改查看权限
     */
    public State editAllowDetail(String us_id, boolean isAllow);
}
