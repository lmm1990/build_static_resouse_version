package build_static_resouse_version.model;

import java.io.File;

/**  
* @Package 文件信息
*	 com.iqarr.maven.plugin.model
* @ClassName: 
*	 FileInfo
* @since 
*	  V1.0
* @author 
*		zhangyong   
* @version 
*		V1.0      
*/
public class FileInfo {

    /**相对文件路径  去除了文件webroot 和全部前缀路径 **/
    private String relativelyFilePath;
    
    /** 文件版本号**/
    private String fileVersion;
    
    /**文件类型 **/
    private String fileType;
    /** 文件名称**/
    private String fileName;
    
    /** 文件对象**/
    private File file;
    
    /**输出最终文件名　**/
    private String finalFileName;
    /**
     * 获取 相对文件路径 
     * @return relativelyFilePath
     */
    public String getRelativelyFilePath() {
        return relativelyFilePath;
    }

    /**
     * 设置 相对文件路径
     * @param relativelyFilePath 相对文件路径
     */
    public void setRelativelyFilePath(String relativelyFilePath) {
        this.relativelyFilePath = relativelyFilePath;
    }

    /**
     * 获取 文件版本号 
     * @return fileVersion
     */
    public String getFileVersion() {
        return fileVersion;
    }

    /**
     * 设置 文件版本号
     * @param fileVersion 文件版本号
     */
    public void setFileVersion(String fileVersion) {
        this.fileVersion = fileVersion;
    }

    /**
     * 获取 文件类型 
     * @return fileType
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置 文件类型
     * @param fileType 文件类型
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * 获取 文件名称 
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置 文件名称
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取 文件对象 
     * @return file
     */
    public File getFile() {
        return file;
    }

    /**
     * 设置 文件对象
     * @param file 文件对象
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * 获取 输出文件对象 
     * @return finalFileName
     */
    public String getFinalFileName() {
        return finalFileName;
    }

    /**
     * 设置 输出文件对象
     * @param finalFileName 输出文件对象
     */
    public void setFinalFileName(String finalFileName) {
        this.finalFileName = finalFileName;
    }


	@Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        
        if( !(obj instanceof FileInfo)){
            return false;
        }
        FileInfo ob=(FileInfo)obj;
        
        if(!fileType.equals(ob.fileType)){
            return false;
        }
        
        if(!relativelyFilePath.equals(ob.relativelyFilePath)){
            return false;
        }
        
        if(!fileVersion.equals(ob.fileVersion)){
            return false;
        }
        return true;
    }

	/*
	* <p>Title: toString</p>  
	* <p>Description: </p>  
	* @return  
	* @see java.lang.Object#toString()  
	*/
	
	@Override
	public String toString() {
		return "FileInfo [relativelyFilePath=" + relativelyFilePath + ", fileVersion=" + fileVersion + ", fileType="
        + fileType + ", fileName=" + fileName + ", file=" + file + ", finalFileName=" + finalFileName+ "]";
	}
}