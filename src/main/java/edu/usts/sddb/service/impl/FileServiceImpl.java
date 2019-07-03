package edu.usts.sddb.service.impl;

import edu.usts.sddb.constant.FileConstant;
import edu.usts.sddb.dao.*;
import edu.usts.sddb.entity.SDDBFile;
import edu.usts.sddb.entity.pack.State;
import edu.usts.sddb.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Autowired
    @Qualifier("majorDao")
    MajorDao majorDao;

    @Autowired
    @Qualifier("fileDao")
    FileDao fileDao;


    @Autowired
    @Qualifier("classroomDao")
    ClassroomDao classroomDao;

    @Autowired
    @Qualifier("scoreDao")
    ScoreDao scoreDao;

    @Autowired
    UserDao userDao;


    @Override
    public List<String> findMajor(String id) {
        return majorDao.findMajorByAcademyId(id);
    }

    @Override
    public List<String> findClass(String ci_grade, String cl_major) {
        //classroomDao.
        return null;
    }

    @Override
    public List<String> findCourse(String className) {
        return scoreDao.findCourseByClass(className);
    }


    @Override
    public State dealUpload(SDDBFile file, MultipartFile[] files) {
        file.setFi_is_checked(false);
        State state = new State();
        //由file的属性创建对应文件夹（若无）
        String basePath = FileConstant.FILE_UPLOAD_URL + File.separator;

        String path = basePath +
                file.getFi_grade().trim() + File.separator +
                file.getFi_major() + File.separator +
                file.getFi_course() + File.separator;


        File file1 = new File(path);
        file1.mkdirs();


        for (int i = 0; i < files.length; i++) {
            MultipartFile temp = files[i];
            //设置原文件名
            String originalName = temp.getOriginalFilename();
            file.setFi_original_name(originalName);

            String modifiedName = originalName.substring(0, originalName.lastIndexOf("."));
            modifiedName += "(" + new Date().getTime() + new Random().nextInt(100000) + ")" + originalName.substring(originalName.lastIndexOf("."));

            //设置修改后的名称
            file.setFi_modified_name(modifiedName);
            try {
                //设置上传文件存储路径
                file.setFi_path(path + modifiedName);
                temp.transferTo(new File(path + modifiedName));
                //向数据库插入File对象
                int count = fileDao.insertFile(file);
                if (count > 0) {
                    System.out.println("count>0");
                }

            } catch (IOException e) {
                e.printStackTrace();
                state.setSuccess(false);
                System.out.println("文件上传失败");
                state.setInfo("文件上传失败");
                return state;
            }

        }
        state.setSuccess(true);
        state.setInfo("文件上传成功");
        System.out.println("文件上传成功");
        return state;
    }

    @Override
    public List<Map<String, Object>> getCountByFile(SDDBFile file) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (file.getFi_major() != null) {
            List<String> courses = fileDao.distinctCourse(file);
            for (String course : courses) {
                Map<String, Object> temp = new HashMap<>();
                temp.put("name", course);
                file.setFi_course(course);
                int count = fileDao.findCountBySelect(file);
                temp.put("count", count);
                list.add(temp);
            }
            return list;
        } else if (file.getFi_grade() != null) {
            List<String> majors = fileDao.distinctMajor(file);
            for (String major : majors) {
                Map<String, Object> temp = new HashMap<>();
                temp.put("name", major);
                file.setFi_major(major);
                int count = fileDao.findCountBySelect(file);
                temp.put("count", count);
                list.add(temp);
            }
            return list;
        } else {
            List<String> grades = fileDao.distinctGrade(file);
            for (String grade : grades) {
                Map<String, Object> temp = new HashMap<>();
                temp.put("name", grade);
                file.setFi_grade(grade);
                int count = fileDao.findCountBySelect(file);
                temp.put("count", count);
                list.add(temp);
            }
            return list;
        }

    }

    @Override
    public List<SDDBFile> getFileByFile(SDDBFile file) {
        return fileDao.findFile(file);
    }

    @Override
    public SDDBFile getFileById(int fi_id) {
        SDDBFile sddbFile = fileDao.findFileById(fi_id);
        String fi_user_name = userDao.findByUserId(sddbFile.getFi_user_id()).getUs_name();
        sddbFile.setFi_user_name(fi_user_name);
        return sddbFile;
    }

    //查找该用户上传的文件数
    @Override
    public int findCountByUserId(String fi_user_id) {
        int i = 0;
        try {
            i = fileDao.findCountByUserId(fi_user_id);
        } catch (Exception e) {
        }
        return i;
    }

    @Override
    public void download(int fi_id, HttpServletResponse response) throws IOException {
        State state = new State();
        int i = fileDao.editDownloadCount(fi_id);
        SDDBFile sddbFile = fileDao.findFileById(fi_id);
        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(sddbFile.getFi_path())));
        String fileName = URLEncoder.encode(sddbFile.getFi_original_name(), "utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while ((len = bis.read()) != -1) {
            out.write(len);
            out.flush();
        }
        out.close();
    }
}
