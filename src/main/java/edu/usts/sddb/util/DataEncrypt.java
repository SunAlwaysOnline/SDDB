package edu.usts.sddb.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class DataEncrypt {

    /**
     * @param source 待加密字符串
     * @param salt   盐
     * @return 加密后的字符串
     */
    public static String getMd5Str(String source, String salt) {
        //加密算法名称
        String algorithName = "MD5";
        //迭代次数
        int hashIterations = 2;
        //加盐
        ByteSource byteSource = ByteSource.Util.bytes(salt);
        return new SimpleHash(algorithName, source, byteSource, hashIterations).toString();
    }
}
