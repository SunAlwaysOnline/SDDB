package edu.usts.sddb.controller;

import edu.usts.sddb.entity.PartOfRadar;
import edu.usts.sddb.entity.User;
import edu.usts.sddb.entity.pack.State;
import edu.usts.sddb.entity.pack.StudentProfile;
import edu.usts.sddb.entity.pack.TeacherProfile;
import edu.usts.sddb.service.RadarService;
import edu.usts.sddb.service.UserService;
import edu.usts.sddb.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    UserService userService;

    @Autowired
    RadarService radarService;


    @RequestMapping("/login.do")
    @ResponseBody
    public State dealLogin(String us_id, String us_password) {
        User user = new User();
        user.setUs_id(us_id);
        user.setUs_password(us_password);
        State state = userService.dealLogin(user);
        return state;

    }


    @RequestMapping("/register.do")
    @ResponseBody
    public State dealRegister(String us_id, String us_password) {

        User user = new User();
        user.setUs_id(us_id);
        user.setUs_password(us_password);
        State state = null;
        try {
            state = userService.dealRegister(user);
        } catch (DuplicateKeyException e) {
            state = new State();
            state.setSuccess(false);
            state.setInfo("该用户已存在");
        }
        return state;
    }

    @RequestMapping("/resetPassword")
    @ResponseBody
    public State resetPassword(User user) {
        return userService.resetPassword(user);
    }

    @RequestMapping("/getUserById")
    @ResponseBody
    public Map<String, String> getNameByUser(String us_id) {
        Map<String, String> map = new HashMap<>();
        User user = userService.getUserById(us_id);
        map.put("name", user.getUs_name());
        map.put("type", user.getUs_type());
        return map;
    }


    @RequestMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo(String us_id) {
        return userService.getUserInfo(us_id);
    }

    @RequestMapping("/editUserInfo")
    @ResponseBody
    public State editUserInfo(User user) {
        return userService.editUserInfo(user);
    }

    @RequestMapping("/uploadPhoto")
    @ResponseBody
    public State uploadPhoto(String us_id, @RequestParam("us_photo") CommonsMultipartFile photo) {
        return userService.uploadPhoto(us_id, photo);
    }

    //获取个人头像，当不存在个人头像时，response中不返回数据，前端会自动使用默认头像
    @RequestMapping("/getPhoto/{us_id}")
    @ResponseBody
    public void getPhoto(HttpServletResponse response, @PathVariable String us_id) {
        try {
            userService.getPhoto(response, us_id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //因为获得(绩点与绩点排名）的时间太长，因此将个人主页数据进行拆分
    @RequestMapping("/getStudentProfileNoGpa")
    @ResponseBody
    public StudentProfile getStudentProfileNoGpa(String us_id) {
        return userService.getStudentProfileNoGpa(us_id);
    }

    @RequestMapping("/getStudentProfileHasGpa")
    @ResponseBody
    public StudentProfile getStudentProfileHasGpa(String us_id) {
        return userService.getStudentProfileHasGpa(us_id);
    }

    @RequestMapping("/getTeacherProfile")
    @ResponseBody
    public TeacherProfile getTeacherProfile(String us_id) {
        return userService.getTeacherProfile(us_id);
    }


    @RequestMapping("/addVisitNumber")
    @ResponseBody
    public void addVisitNumber(String from, String to) {
        userService.addVisitNumber(from, to);
    }


    //将综合素质分数降序输出
    @RequestMapping("/getUserQuality")
    @ResponseBody
    public List<PartOfRadar> getUserQuality(String us_id) {
        List<PartOfRadar> partOfRadarList = radarService.generaterRadar(us_id);
        Collections.sort(partOfRadarList, new Comparator<PartOfRadar>() {
            @Override
            public int compare(PartOfRadar o1, PartOfRadar o2) {
                return o2.getRadarScore() - o1.getRadarScore();
            }
        });

        return partOfRadarList;
    }

    @RequestMapping("/getAllowDetail")
    @ResponseBody
    public State getAllowDetail(String from, String to) {
        return userService.getAllowDetail(from, to);
    }

    @RequestMapping("/editAllowDetail")
    @ResponseBody
    public State editAllowDetail(String us_id, boolean isAllow) {
        return userService.editAllowDetail(us_id, isAllow);
    }


}
