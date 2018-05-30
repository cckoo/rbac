package cn.cckoo.yu.common.util;


import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class Md5 {
    /**
     * 生成md5
     * @param message
     * @return
     */
    public static String getMD5(String message) {
        String md5str;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md5str = Hex.bytesToHex(md.digest(message.getBytes()));
        } catch (Exception e) {
            return null;
        }
        return md5str;
    }

    /**
     * 生成md5
     * @param file
     * @return
     * @throws Exception
     */
    public static String getMD5(File file) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        try {
            byte[] buffer = new byte[1024];
            int length = -1;
            FileInputStream fileInputStream = new FileInputStream(file);
            while ((length = fileInputStream.read(buffer, 0, 1024)) != -1)
                md.update(buffer, 0, length);
        } catch (Exception e) { }
        return Hex.bytesToHex(md.digest());
    }
}
