package edu.usts.sddb.controller;

import edu.usts.sddb.entity.pack.State;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/file1")
public class TestFileUploadDownloadController {
    @ResponseBody
    @RequestMapping("/upload")
    public State upload(@RequestParam("file") CommonsMultipartFile file){
        State state = new State();
        // 获得原始文件名
        String fileName = file.getOriginalFilename();
        System.out.println("原始文件名:" + fileName);


        String path = "D:\\uploadFile";

        try {
            file.transferTo(new File(path+File.separator+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return state;
    }


    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String path = "D:\\uploadFile";
        File file = new File(path);
        File[] files = file.listFiles();
        String name = files[0].getName();//随机获取一个文件，实际中按需编写代码
        name = URLEncoder.encode(name,"utf-8");
        System.out.println("文件的名字："+name);
        response.addHeader("content-disposition", "attachment;filename="+name);
        try {
            FileUtils.copyFile(files[0], response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
