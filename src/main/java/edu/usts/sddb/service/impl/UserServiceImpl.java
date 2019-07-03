package edu.usts.sddb.service.impl;

import edu.usts.sddb.constant.FileConstant;
import edu.usts.sddb.dao.*;
import edu.usts.sddb.entity.*;
import edu.usts.sddb.entity.pack.State;
import edu.usts.sddb.entity.pack.StudentScore;
import edu.usts.sddb.entity.pack.StudentProfile;
import edu.usts.sddb.entity.pack.TeacherProfile;
import edu.usts.sddb.service.FileService;
import edu.usts.sddb.service.StudentService;
import edu.usts.sddb.service.UserService;
import edu.usts.sddb.service.VolunteerService;
import edu.usts.sddb.util.DataEncrypt;
import edu.usts.sddb.util.DataFormatUtil;
import edu.usts.sddb.util.Task.ViewPool;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("userService")
public class UserServiceImpl implements UserService {


    @Autowired
    @Qualifier("userDao")
    UserDao userDao;

    @Autowired
    @Qualifier("teacherDao")
    TeacherDao teacherDao;

    @Autowired
    @Qualifier("studentDao")
    StudentDao studentDao;

    @Autowired
    VolunteerService volunteerService;

    @Autowired
    PersonAwardServiceImpl personAwardService;

    @Autowired
    StudentService studentService;

    @Autowired
    FileService fileService;

    @Override
    public User getUserById(String us_id) {
        return userDao.findByUserId(us_id);
    }

    //教师版，只处理教师的登录
    public State dealLogin(User user) {
        State state = null;
        Subject currentUser = SecurityUtils.getSubject();
        User u = userDao.findByUserId(user.getUs_id());

        //不存在此用户
        if (null == u) {
            state = new State(false, "此用户名不存在！");
            return state;
        }

        if (u.getUs_type().equals("student")) {
            state = new State(false, "请进入学生入口进行登录，当前系统为教师版!");
            return state;
        }

        //存在此用户
        //进行登录验证
        UsernamePasswordToken token =
                new UsernamePasswordToken(user.getUs_id(), user.getUs_password());
        //token.setRememberMe(true);
        try {
            currentUser.login(token);
        } catch (Exception e) {
            state = new State(false, "用户名或密码错误!");
            return state;
        }

        String us_name = u.getUs_name();

        //没有异常，将用户名设置进消息中
        state = new State(true, us_name);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("用户ID:" + u.getUs_id() + " 姓名:" + u.getUs_name() + " 于" + df.format(new Date()) + " 登录教师端系统");
        return state;

    }

    //教师版，只处理教师的注册
    @Override
    public State dealRegister(User user) {
        User u = new User();
        State state = null;
        String id = user.getUs_id();

        //根据id查找是否存在此教师
        Teacher teacher = teacherDao.findById(id);
        if (teacher != null) {
            state = new State(true, "教师注册成功");
            u.setUs_id(id);
            u.setUs_name(teacher.getTe_name());
            String encrypt = DataEncrypt.getMd5Str(user.getUs_password(), id);
            u.setUs_password(encrypt);
            u.setUs_type("teacher");
            int i = userDao.addUser(u);
            if (i > 0) {
                return state;
            }
        }
        state = new State(false, "输入的工号不存在，已有的教师表中不存在该工号，请联系管理员添加。");
        return state;
    }

    //查询所有用户姓名与工号
    public List<User> findAll() {
        List<User> list = userDao.findAll();
        return list;
    }

    //获取用户基本信息
    @Override
    public User getUserInfo(String us_id) {
        User user = userDao.findByUserId(us_id);
        User resultUser = new User();
        resultUser.setUs_phone(user.getUs_phone());
        resultUser.setUs_qq(user.getUs_qq());
        resultUser.setUs_email(user.getUs_email());
        resultUser.setUs_intro(user.getUs_intro());
        resultUser.setUs_allow_detail(user.getUs_allow_detail());
        return resultUser;
    }

    @Override
    public State editUserInfo(User user) {
        //去除手机号码内的所有空格
        user.setUs_phone(user.getUs_phone().replaceAll(" ", ""));

        State state = null;
        int i = 0;
        try {
            i = userDao.edit(user);
        } catch (Exception e) {
            e.printStackTrace();
            state = new State(false, "用户信息更新失败！");
            return state;
        }
        if (i > 0) {
            state = new State(true, "用户信息更新成功!");
        } else {
            state = new State(false, "用户信息更新失败!");
        }

        return state;
    }

    @Override
    public State uploadPhoto(String us_id, @RequestParam("photo") CommonsMultipartFile photo) {
        State state = null;
        //后缀名
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf("."));
        //遍历是否存在该用户的头像
        //比如需要使用15200141115.jpg覆盖15200141115.png
        //列出c:/sddbfile/photo中所有的文件
        String[] fileNameList = new File(FileConstant.PHOTO_UPLOAD_URL).list();
        if (null != fileNameList && fileNameList.length != 0) {
            for (String fileName : fileNameList) {
                String fileNameNoSuffix = fileName.substring(0, fileName.lastIndexOf("."));
                if (us_id.equals(fileNameNoSuffix)) {
                    new File(FileConstant.PHOTO_UPLOAD_URL + "/" + fileName).delete();
                    break;
                }
            }
        }


        File file = new File("C:/sddbfile/photo/" + us_id + suffix);
        try {
            photo.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            state = new State(false, "头像上传失败!");
        }
        state = new State(true, "头像上传成功!");

        return state;
    }

    @Override
    public void getPhoto(HttpServletResponse response, String us_id) throws IOException {
        //创建头像存放的文件夹
        File dir = new File(FileConstant.PHOTO_UPLOAD_URL);
        dir.mkdirs();
        //真实文件名
        String trueFileName = "";

        //列出c:/sddbfile/photo中所有的文件
        String[] fileNameList = new File(FileConstant.PHOTO_UPLOAD_URL).list();
        if (fileNameList == null || fileNameList.length == 0) {
            return;
        } else {
            for (String fileName : fileNameList) {
                String fileNameNoSuffix = fileName.substring(0, fileName.lastIndexOf("."));
                if (us_id.equals(fileNameNoSuffix)) {
                    trueFileName = fileName;
                    break;
                }
            }
        }


        FileInputStream fis = null;
        try {
            fis = new FileInputStream(FileConstant.PHOTO_UPLOAD_URL + "/" + trueFileName);
        } catch (FileNotFoundException e) {
            System.out.println(FileConstant.PHOTO_UPLOAD_URL + "/" + us_id + "头像未找到");
            return;
        }

        OutputStream ops = response.getOutputStream();
        int count = 0;
        byte[] buffer = new byte[1024];
        while ((count = fis.read(buffer)) != -1) {
            ops.write(buffer, 0, count);
            ops.flush();
        }
        fis.close();
        ops.close();

    }

    @Override
    public State resetPassword(User user) {
        //将密码进行MD5加密
        String us_password = DataEncrypt.getMd5Str(user.getUs_password(), user.getUs_id());
        user.setUs_password(us_password);
        State state = null;
        int i = 0;
        try {
            i = userDao.resetPassword(user);
        } catch (Exception e) {
            state = new State(false, "重置失败");
        }
        if (i > 0) {
            state = new State(true, "重置成功");
        } else {
            state = new State(false, "重置失败");
        }

        return state;
    }


    /**
     * 获取用户(学生)个人主页资料
     * 无gpa
     */
    @Override
    public StudentProfile getStudentProfileNoGpa(String us_id) {
        StudentProfile userProfile = new StudentProfile();
        userProfile.setP_id(us_id);
        Student student = studentDao.findById(us_id);
        User user = userDao.findByUserId(us_id);
        if (null == user) {
            user = new User();

            user.setUs_view(0);
            user.setUs_intro("暂时还没有简介...");
            user.setUs_phone("...");
            user.setUs_qq("...");
            user.setUs_email("...");
        }

        userProfile.setP_name(student.getSt_name());
        userProfile.setP_sex(student.getSt_sex());
        userProfile.setP_academy("电子与信息工程学院");
        userProfile.setP_grade(student.getSt_grade());
        userProfile.setP_major(student.getSt_major());
        userProfile.setP_classroom(student.getSt_class());


        int p_view = user.getUs_view();
        userProfile.setP_view(p_view);


        List<Award> awardList = personAwardService.generate(us_id);
        userProfile.setP_award_number(awardList.size());


        Double time = volunteerService.findTimeById(us_id);
        userProfile.setP_volunteer_time(time);

        int p_file_count = fileService.findCountByUserId(us_id);
        userProfile.setP_file_count(p_file_count);

        String p_intro = user.getUs_intro();
        userProfile.setP_intro(p_intro);

        String p_qq = user.getUs_qq();
        userProfile.setP_qq(p_qq);

        String p_email = user.getUs_email();
        userProfile.setP_email(p_email);

        return userProfile;
    }

    /**
     * 获取用户(学生)个人主页资料
     * 有gpa
     */
    @Override
    public StudentProfile getStudentProfileHasGpa(String us_id) {
        StudentProfile userProfile = new StudentProfile();

        Student student = studentDao.findById(us_id);

        List<StudentScore> studentScoreList = studentService.findAllScoreById(us_id);
        Double p_gpa = 0.0;
        for (int i = 0; i < studentScoreList.size(); i++) {
            p_gpa += studentScoreList.get(i).getGpa();
        }
        p_gpa = p_gpa / studentScoreList.size();
        p_gpa = DataFormatUtil.doubleFormat(p_gpa, 2);

        userProfile.setP_gpa(p_gpa);

        //查找该班级内的所有学生
        String stuClass = student.getSt_class();
        List<Student> studentList = studentDao.findStudentsByClassroom(stuClass);
        List<Double> gpaList = new ArrayList<>();
        Double gpa = 0.0;
        for (int j = 0; j < studentList.size(); j++) {
            List<StudentScore> allStudentScoreList = studentService.findAllScoreById(studentList.get(j).getSt_id());
            gpa = 0.0;
            for (int m = 0; m < allStudentScoreList.size(); m++) {
                gpa += allStudentScoreList.get(m).getGpa();
            }
            gpa = gpa / allStudentScoreList.size();
            gpaList.add(DataFormatUtil.doubleFormat(gpa, 2));
        }

        //对gpa进行排序
        Collections.sort(gpaList, new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                if (o2 > o1) {
                    return 1;
                } else if (o2.equals(o1)) {
                    return 0;
                }
                return -1;
            }
        });

        int rank = 0;
        for (rank = 0; rank < gpaList.size(); rank++) {
            if (p_gpa.equals(gpaList.get(rank))) {
                break;
            }
        }

        userProfile.setP_rank((rank + 1) + "/" + gpaList.size());

        return userProfile;
    }

    @Override
    public TeacherProfile getTeacherProfile(String us_id) {
        TeacherProfile userProfile = new TeacherProfile();
        userProfile.setP_id(us_id);
        Teacher teacher = teacherDao.findById(us_id);
        User user = userDao.findByUserId(us_id);
        if (null == user) {
            user = new User();

            user.setUs_view(0);
            user.setUs_intro("暂时还没有简介...");
            user.setUs_phone("...");
            user.setUs_qq("...");
            user.setUs_email("...");
        }

        userProfile.setP_name(teacher.getTe_name());
        userProfile.setP_sex(teacher.getTe_sex());
        userProfile.setP_department(teacher.getTe_department());
        userProfile.setP_edu_background(teacher.getTe_edu_background());
        userProfile.setP_pro_title(teacher.getTe_pro_title());
        userProfile.setP_politics_status(teacher.getTe_politics_status());

        int p_view = user.getUs_view();
        userProfile.setP_view(p_view);

        int p_file_count = fileService.findCountByUserId(us_id);
        userProfile.setP_file_count(p_file_count);

        String p_intro = user.getUs_intro();
        userProfile.setP_intro(p_intro);

        String p_qq = user.getUs_qq();
        userProfile.setP_qq(p_qq);

        String p_email = user.getUs_email();
        userProfile.setP_email(p_email);

        return userProfile;
    }

    /**
     * 增加访问人数
     */
    @Override
    public void addVisitNumber(String from, String to) {
        if (!ViewPool.contain(from, to)) {
            ViewPool.add(from, to);
            userDao.addVisitNumber(to);
        }
    }

    @Override
    public State getAllowDetail(String from, String to) {
        State state = null;
        //查看from用户的种类
        String type = userDao.findByUserId(from).getUs_type();
        if (type.equals("teacher")) {
            state = new State(true, "教师有权限查看任何一个学生的详细信息");

        } else {
            //学生查看学生，查询to用户是否允许
            //先看to学生是否注册，如未注册，则默认允许查看
            User toUser = userDao.findByUserId(to);
            if (null == toUser) {
                state = new State(true, "目标学生未注册，则默认允许");
                return state;
            }
            boolean allowDetail = userDao.findByUserId(to).getUs_allow_detail();
            if (allowDetail) {
                state = new State(true, "此学生允许同学查看详细信息");
            } else {
                state = new State(false, "此学生不允许同学查看详细信息");
            }
        }
        return state;
    }

    @Override
    public State editAllowDetail(String us_id, boolean isAllow) {
        State state = null;
        User user = new User();
        user.setUs_id(us_id);
        user.setUs_allow_detail(isAllow);
        int i = 0;
        try {
            i = userDao.editAllowDetail(user);
        } catch (Exception e) {
            e.printStackTrace();
            state = new State(false, "权限设置更新失败!");
            return state;
        }
        if (i > 0) {
            state = new State(true, "权限设置更新成功!");
        } else {
            state = new State(false, "权限设置更新失败!");
        }
        return state;

    }


}
