/**
 * 
 */
package com.emin.igwmp.fs.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;
import com.emin.base.util.EminEnvUtil;

/**
 * @author jim
 *
 */
public class FileUtil {
	public static final String DOT = ".";  
    public static final String SLASH_ONE = "/";  
    public static final String SLASH_TWO = "\\";  
  
    /** 
     * 获取没有扩展名的文件名 
     *  
     * @param fileName 
     * @return 
     */  
    public static String getWithoutExtension(String fileName) {  
        String ext = StringUtils.substring(fileName, 0,  
                StringUtils.lastIndexOf(fileName, DOT));  
        return StringUtils.trimToEmpty(ext);  
    }  
  
    /** 
     * 获取扩展名 
     *  
     * @param fileName 
     * @return 
     */  
    public static String getExtension(String fileName) {  
        if (StringUtils.INDEX_NOT_FOUND == StringUtils.indexOf(fileName, DOT))  
            return StringUtils.EMPTY;  
        String ext = StringUtils.substring(fileName,  
                StringUtils.lastIndexOf(fileName, DOT));  
        return StringUtils.trimToEmpty(ext);  
    }  
  
    /** 
     * 判断是否同为扩展名 
     *  
     * @param fileName 
     * @param ext 
     * @return 
     */  
    public static boolean isExtension(String fileName, String ext) {  
        return StringUtils.equalsIgnoreCase(getExtension(fileName), ext);  
    }  
  
    /** 
     * 判断是否存在扩展名 
     *  
     * @param fileName 
     * @return 
     */  
    public static boolean hasExtension(String fileName) {  
        return !isExtension(fileName, StringUtils.EMPTY);  
    }  
  
    /** 
     * 得到正确的扩展名 
     *  
     * @param ext 
     * @return 
     */  
    public static String trimExtension(String ext) {  
        return getExtension(DOT + ext);  
    }  
  
    /** 
     * 向path中填充扩展名(如果没有或不同的话) 
     *  
     * @param fileName 
     * @param ext 
     * @return 
     */  
    public static String fillExtension(String fileName, String ext) {  
        fileName = replacePath(fileName + DOT);  
        ext = trimExtension(ext);  
        if (!hasExtension(fileName)) {  
            return fileName + getExtension(ext);  
        }  
        if (!isExtension(fileName, ext)) {  
            return getWithoutExtension(fileName) + getExtension(ext);  
        }  
        return fileName;  
    }  
  
    /** 
     * 判断是否是文件PATH 
     *  
     * @param fileName 
     * @return 
     */  
    public static boolean isFile(String fileName) {  
        return hasExtension(fileName);  
    }  
  
    /** 
     * 判断是否是文件夹PATH 
     *  
     * @param fileName 
     * @return 
     */  
    public static boolean isFolder(String fileName) {  
        return !hasExtension(fileName);  
    }  
  
    public static String replacePath(String path) {  
        return StringUtils.replace(StringUtils.trimToEmpty(path), SLASH_ONE,  
                SLASH_TWO);  
    }  
  
    /** 
     * 链接PATH前处理 
     *  
     * @param path 
     * @return 
     */  
    public static String trimLeftPath(String path) {  
        if (isFile(path))  
            return path;  
        path = replacePath(path);  
        String top = StringUtils.left(path, 1);  
        if (StringUtils.equalsIgnoreCase(SLASH_TWO, top))  
            return StringUtils.substring(path, 1);  
        return path;  
    }  
  
    /** 
     * 链接PATH后处理 
     *  
     * @param path 
     * @return 
     */  
    public static String trimRightPath(String path) {  
        if (isFile(path))  
            return path;  
        path = replacePath(path);  
        String bottom = StringUtils.right(path, 1);  
        if (StringUtils.equalsIgnoreCase(SLASH_TWO, bottom))  
            return StringUtils.substring(path, 0, path.length() - 2);  
        return path + SLASH_TWO;  
    }  
  
    /** 
     * 链接PATH前后处理，得到准确的链接PATH 
     *  
     * @param path 
     * @return 
     */  
    public static String trimPath(String path) {  
        path = StringUtils.replace(StringUtils.trimToEmpty(path), SLASH_ONE,  
                SLASH_TWO);  
        path = trimLeftPath(path);  
        path = trimRightPath(path);  
        return path;  
    }  
  
    /** 
     * 通过数组完整链接PATH 
     *  
     * @param paths 
     * @return 
     */  
    public static String bulidFullPath(String... paths) {  
        StringBuffer sb = new StringBuffer();  
        for (String path : paths) {  
            sb.append(trimPath(path));  
        }  
        return sb.toString();  
    } 
    public static byte[] getBytes(File file){  
    	byte[] buffer = null;  
        try {              
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }
    public static String getFile(byte[] bfile,String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(System.getenv(EminEnvUtil.EMIN_HOME)+File.separator+"tmp");  
            if(!dir.exists()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(dir,fileName);
            if(!file.exists()){
            	 file.createNewFile();
            }
           
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);
            return file.getAbsolutePath();
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
        return null;
    }
    public static void main(String[] args) {
		String a = "D:\\1.txt";
		String b = getExtension(a);
		System.out.println(b);
	}
}
