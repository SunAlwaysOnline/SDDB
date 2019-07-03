package edu.usts.sddb.service;

import edu.usts.sddb.entity.SDDBFile;
import edu.usts.sddb.entity.pack.State;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileService {
    public List<String> findMajor(String id);

    public List<String> findClass(String ci_grade, String cl_major);

    public List<String> findCourse(String className);

    public State dealUpload(SDDBFile file, MultipartFile[] files);

    public List<Map<String, Object>> getCountByFile(SDDBFile file);

    public List<SDDBFile> getFileByFile(SDDBFile file);

    public SDDBFile getFileById(int fi_id);

    public int findCountByUserId(String fi_user_id);

    public void download(int fi_id, HttpServletResponse response) throws IOException;
}
