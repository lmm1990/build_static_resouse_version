package build_static_resouse_version.utils;

import build_static_resouse_version.MyMojo;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Package 文件工具
 *          com.iqarr.maven.plugin.utils
 * @ClassName:
 *             FileUtil
 * @since
 *        V1.0
 * @author
 *         zhangyong
 * @version
 *          V1.0
 */
public class FileUtil {
    
    /**
     * 
     * 遍历查找目标目录了中指定类型的文件，并保存在集合中
     * 
     * @param collected
     *            指定类型文件集合
     * @param file
     *            目标目录
     * @param includes
     *            文件后缀,不包含点
     */
    public static void collectFiles(List<File> collected, File file, Set<String> includes) {
        if (file.isFile()) {
            // 如果是文件，则判断是否为指定类型
            
            for (String include : includes) {
                if (file.getName().endsWith("." + include)) {
                    collected.add(file);
                    break;
                }
            }
        } else {
            // 如果是目录，则遍历子文件或者子目录，递归查找
        	if(null==file.listFiles ()){
        		return;
        	}
            for (File sub : file.listFiles()) {
                collectFiles(collected, sub, includes);
            }
        }
    }

    /**
     * 读取文件所有内容<br />
     * Author:刘明明<br />
     * CreateTime:2015年11月10日15:19:25
     *
     * @param fileName 文件名
     * @return String 文件内容
     */
    public static String readAllText(String fileName) {
        return readAllText(new File(fileName));
    }

    /**
     * 读取文件所有内容<br />
     * Author:刘明明<br />
     * CreateTime:2015年11月10日15:19:25
     *
     * @param file 文件
     * @return String 文件内容
     */
    public static String readAllText(File file) {
        if (!file.exists()) {
            return "";
        }
        StringBuilder str = new StringBuilder();
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                str.append(line);
                str.append("\r\n");
            }
        } catch (Exception ex) {
            return "";
        }
        return str.toString();
    }

    /**
     * 验证文件是否存在<br />
     * Author:刘明明<br />
     * CreateTime:2015年11月25日12:08:00
     *
     * @param fileName    文件名
     * @param isDirectory 是否是目录，默认值：false
     * @return Boolean
     */
    public static Boolean exists(String fileName, Boolean isDirectory) {
        if (isDirectory == null) {
            isDirectory = false;
        }
        if (isDirectory) {
            File file = new File(fileName);
            return file.exists() && file.isDirectory();
        }
        return new File(fileName).exists();
    }

    /**
     * 写文件<br />
     * Author:刘明明<br />
     * CreateTime:2015年11月26日18:28:27
     *
     * @param fileName 文件名
     * @return String 文件内容
     */
    public static boolean writeAllText(String fileName, String content) {
        notExistsDirectoryCreate(new File(fileName).getParent());
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"))) {
            writer.write(content);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 未找到目录则创建<br />
     * Author:刘明明<br />.
     * CreateTime:2016年1月20日13:05:18
     *
     * @param fileName 文件名
     */
    public static void notExistsDirectoryCreate(String fileName) {
        if (!exists(fileName, true)) {
            new File(fileName).mkdirs();
        }
    }
    
    /**
     * 
     * 写文件
     * 
     * @param file
     *            文件编码
     * @param strs
     * @throws IOException
     */
    public static void writeFile(final File file,final List<String> strs) throws IOException {
        
        OutputStream out = null;
        
        try {
            out = new FileOutputStream(file, false);
            for (String s : strs) {
                if (strs != null && !"".equals(s) && s.length() > 0) {   // && !"\r\n".equals(s)
                    out.write(s.getBytes(Charset.forName("utf-8")));
                    out.write("\n".getBytes(Charset.forName("utf-8")));
                }
            }
            
        } catch (IOException e) {
            throw e;
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
            
        }
        
    }
    
    /**
     * 
     * @Title:
     *         getSystemFileSeparator
     * @Description:
     *               获取系统文件分割符
     * @return
     */
    public static String getSystemFileSeparator() {
        
        return System.getProperties().getProperty("file.separator");
    }
    
    /**
     * 
     * 获取路径是否linux
     * 
     * @return
     */
    public static boolean getSystemFileSeparatorIslinux() {
        String property = System.getProperties().getProperty("file.separator");
        if ("/".equals(property)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 
     * 文件复制
     * 
     * @param source
     * @param to
     * @throws IOException
     */
    public static void fileChannelCopy(final File source, final File to) throws IOException {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(source);
            fo = new FileOutputStream(to);
            in = fi.getChannel();
            out = fo.getChannel();
            in.transferTo(0, in.size(), out);
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                throw e;
            }
        }
    }

    public static byte[] read(File file) {
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
            byte[] temp = new byte[1024];
            int size;
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
            in.close();
        } catch (Exception ex) {
            return new byte[0];
        }
        return out.toByteArray();
    }

    /**
     * 获得文件路径列表<br />
     * author:刘明明<br />
     * updateTime:2016年2月24日17:19:14
     *
     * @param folderPath 目录地址
     * @param suffixList 后缀列表
     */
    public static HashSet<File> getFileList(String folderPath, Set<String> suffixList) {
        HashSet<File> filePathList = new HashSet<>();
        File file = new File(folderPath);
        if (!file.exists()) {
            return filePathList;
        }
        getFileList(file, filePathList,suffixList);
        return filePathList;
    }

    /**
     * 获得文件路径列表<br />
     * author:刘明明<br />
     * updateTime:2016年2月24日17:19:14
     *
     * @param file         目录
     * @param filePathList 文件路径列表
     * @param suffixList 后缀列表
     */
    private static void getFileList(File file, HashSet<File> filePathList, Set<String> suffixList) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {// 判断是否文件夹
                getFileList(f, filePathList,suffixList);// 调用自身,查找子目录
            } else{
                //MyMojo.log.debug("f:"+f.getPath());
                if(suffixList.contains(getExtension(f.getName()))){
                    filePathList.add(f);
                }
            }
        }
    }

    /**
     * 获得文件后缀名，包含点<br />
     * Author:刘明明<br />
     * CreateTime:2015年12月4日10:52:57
     *
     * @param fileName 文件名
     * @return String 文件后缀名
     */
    public static String getExtension(String fileName) {
        if (fileName == null || fileName.length() < 1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
