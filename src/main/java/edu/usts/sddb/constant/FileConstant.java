package edu.usts.sddb.constant;

public class FileConstant {
    //文件上传基地址
    public static final String FILE_UPLOAD_URL = "c:/sddbfile";

    //头像的保存地址
    public static final String PHOTO_UPLOAD_URL = FILE_UPLOAD_URL + "/photo";

    //默认头像地址
    public String DEFAULT_PHOTO_URL;

//    public static String getDefault_photo_url(Class<?> c) {
//        return c.getClassLoader().getResource("/").getPath().replaceFirst("/", "")
//                .replace("WEB-INF/classes/", "") + "assets/images/avatars/user.jpg";
//    }
}
