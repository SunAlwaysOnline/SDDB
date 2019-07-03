package edu.usts.sddb.dao;

import edu.usts.sddb.entity.SDDBFile;

import java.util.List;

public interface FileDao {
    public int insertFile(SDDBFile file);

    public int findCountBySelect(SDDBFile file);

    public List<String> distinctGrade(SDDBFile file);

    public List<String> distinctMajor(SDDBFile file);

    public List<String> distinctCourse(SDDBFile file);


    public List<SDDBFile> findFile(SDDBFile file);

    public SDDBFile findFileById(int fi_id);


    public int editDownloadCount(int fi_id);

    public int findCountByUserId(String fi_user_id);


}
