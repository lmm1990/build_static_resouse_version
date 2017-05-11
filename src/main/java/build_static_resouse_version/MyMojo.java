package build_static_resouse_version;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import build_static_resouse_version.model.FileInfo;
import build_static_resouse_version.utils.Cryptography.MD532;
import build_static_resouse_version.utils.FileUtil;
import build_static_resouse_version.utils.LogUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Goal which touches a timestamp file.
 *
 * @goal bulid_static
 *
 * @phase compile
 */
public class MyMojo
    extends AbstractMojo
{
    /**
     * 原始
     * @parameter expression="${basedir}\\src\\main\\webapp"
     * */
    private String sourceRoot;

    /**
     * 目标目录
     * @parameter expression="${project.build.directory}\\${project.build.finalName}"
     * */
    private String targetRoot;

    /**
     * @parameter
     * */
    private String[] suffixs = new String[0];

    public void execute()
        throws MojoExecutionException
    {
        sourceRoot = sourceRoot.replace("\\","/");
        targetRoot = targetRoot.replace("\\","/");
        LogUtil.init(getLog());
        run(sourceRoot,targetRoot,suffixs);
    }

    public static void main(String[] args) {

//        Pattern pattern = Pattern.compile("('/[A-Za-z]+|\"/[A-Za-z]+)[a-zA-Z0-9/_.-]+\\.(js|css)(\\?|)");
//        Matcher m = pattern.matcher("var defaults = require('/assets/js/artDialog/src/dialog-config.js');");
//        while (m.find()) {
//            System.out.println(m.group());
//        }
    }

    static Map<String, FileInfo> fileList = new HashMap<>();

    public static void run(String sourceRoot,String targetRoot,String[] suffixList){
        if(suffixList==null || suffixList.length < 1){
            LogUtil.info("error");
            return;
        }
        LogUtil.info(String.format("webapp path:%s",sourceRoot));
        LogUtil.info(String.format("target path:%s",targetRoot));
        LogUtil.info(String.format("suffixList:\n%s",String.join("\n",suffixList)));
        long timeStart = new Date().getTime();
        HashSet<File> sourceFileList = FileUtil.getFileList(sourceRoot,new HashSet<>(Arrays.asList(suffixList)));
        JSONObject fileMap = new JSONObject();
        for (File item :sourceFileList){
            String path = item.getPath().replace("\\","/").replace(sourceRoot,"");
            if(path.equals("/assets/js/file_map.js")){
                continue;
            }
            fileList.put(path,new FileInfo(){{
                setFileType(FileUtil.getExtension(item.getName()));
                setFileVersion(MD532.encrypt(item));
                setRelativelyFilePath(path);
                setFileName(item.getName());
                setFile(item);
            }});
            fileMap.put(path,String.format("%s?v=%s",path,fileList.get(path).getFileVersion()));
            LogUtil.info(String.format("find type:%s file:%s md5:%s",fileList.get(path).getFileType(),path,fileList.get(path).getFileVersion()));
        }
        FileUtil.writeAllText(String.format("%s/assets/js/file_map.js",targetRoot),String.format("var file_map=%s",fileMap.toJSONString()));
        LogUtil.info("begin add version");
        Pattern pattern2 = Pattern.compile("('/[A-Za-z]+|\"/[A-Za-z]+)[a-zA-Z0-9/_.-]+\\.(js|css)(\\?|)(v=|)");
        Pattern pattern = Pattern.compile("('/[A-Za-z]+|\"/[A-Za-z]+).*?\\.(js|css)\\?v=[0-9a-z]+");
        long version = System.currentTimeMillis();
        String startFlog;
        for(String path :fileList.keySet()){
            Matcher matcher = pattern.matcher(FileUtil.readAllText(fileList.get(path).getFile()));
            StringBuffer content = new StringBuffer();

            String item;
            while (matcher.find()) {
                item = matcher.group();
                startFlog = item.startsWith("'")?"'":"\"";
                item = replaceContent(item,version);
                matcher.appendReplacement(content,String.format("%s%s",startFlog,item));
            }
            matcher.appendTail(content);

            matcher = pattern2.matcher(content.toString());
            content = new StringBuffer();
            while (matcher.find()) {
                item = matcher.group();
                if(item.contains("v=")){
                    continue;
                }
                startFlog = item.startsWith("'")?"'":"\"";
                item = replaceContent(item,version);
                matcher.appendReplacement(content,String.format("%s%s",startFlog,item));
            }
            matcher.appendTail(content);
            FileUtil.writeAllText(String.format("%s%s",targetRoot,path),content.toString());
        }
        LogUtil.info(String.format("===============  Total time [%d millisecond]===========================",new Date().getTime() - timeStart));
        LogUtil.info("========================================================================");
    }

    private static String replaceContent(String item,long version){
        item = item.substring(1);
        int paramIndex =item.indexOf("?");
        if(paramIndex>-1) {
            item = item.substring(0,paramIndex);
        }
        return String.format("%s%sv=%s%s", item, item.indexOf("?") > -1 ? "&" : "?", fileList.containsKey(item) ? fileList.get(item).getFileVersion() : version,paramIndex>-1?"&":"");
    }
}
