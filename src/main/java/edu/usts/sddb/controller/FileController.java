package edu.usts.sddb.controller;

import edu.usts.sddb.entity.SDDBFile;
import edu.usts.sddb.entity.pack.State;
import edu.usts.sddb.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    @Qualifier("fileService")
    FileService fileService;

    @ResponseBody
    @RequestMapping("/findClass.do")
    public List<String> courseList(String sc_class_name) {
        List list = fileService.findCourse(sc_class_name);
        return list;
    }


    @ResponseBody
    @RequestMapping("/fileUpload.do")
    public State upload(SDDBFile file, @RequestParam("files") MultipartFile[] files) {
        State state = fileService.dealUpload(file, files);
        return state;
    }


    //返回下载之前的文件夹信息包括文件夹名称与文件数量
    @RequestMapping("/preDown.do")
    @ResponseBody
    public List<Map<String, Object>> preDownLoad(SDDBFile file) {
        List<Map<String, Object>> map = fileService.getCountByFile(file);
        return map;
    }


    @RequestMapping("/getFile.do")
    @ResponseBody
    public List<SDDBFile> getFile(SDDBFile file) {
        return fileService.getFileByFile(file);
    }


    @RequestMapping("/getFileById.do")
    @ResponseBody
    public SDDBFile getFileById(int fi_id) {
        return fileService.getFileById(fi_id);
    }

    @RequestMapping("/download/{fi_id}")
    @ResponseBody
    public void download(@PathVariable int fi_id, HttpServletResponse response) {
        try {
            fileService.download(fi_id, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
