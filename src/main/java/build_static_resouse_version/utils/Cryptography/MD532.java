package build_static_resouse_version.utils.Cryptography;

import build_static_resouse_version.utils.FileUtil;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lmm on 2015/11/30.
 */
public class MD532 {
    /**
     * MD532加密<br />
     * Author:刘明明<br />
     * CreateTime:2015年11月30日11:51:15
     *
     * @param str 带加密的字符串
     * @return string 加密后的值
     */
    public static String encrypt(String str) {
        return encrypt(str.getBytes());
    }

    /**
     * MD532加密<br />
     * Author:刘明明<br />
     * CreateTime:2015年12月4日11:15:47
     *
     * @param file 文件
     * @return string 加密后的值
     */
    public static String encrypt(File file) {
        return encrypt(FileUtil.read(file));
    }

    /**
     * MD532加密<br />
     * Author:刘明明<br />
     * CreateTime:2015年12月4日11:15:47
     *
     * @param byteList 带加密的字节数据
     * @return string 加密后的值
     */
    public static String encrypt(byte[] byteList) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(byteList);
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}