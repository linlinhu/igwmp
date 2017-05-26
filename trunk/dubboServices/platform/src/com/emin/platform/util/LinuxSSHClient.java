package com.emin.platform.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class LinuxSSHClient {
	
	private static Log log = LogFactory.getLog(LinuxSSHClient.class);
	
	private static Session connect(String host, Integer port, String user, String password) throws JSchException{
		Session session = null;
		try {
			JSch jsch = new JSch();
			if(port != null){
				session = jsch.getSession(user, host, port.intValue());
			}else{
				session = jsch.getSession(user, host);
			}
			session.setPassword(password);
			//设置第一次登陆的时候提示，可选值:(ask | yes | no)
			session.setConfig("StrictHostKeyChecking", "no");
			//30秒连接超时
			session.connect(30000);
		} catch (JSchException e) {
			e.printStackTrace();
			System.out.println("SFTPUitl 获取连接发生错误");
			throw e;
		}
		return session;
	}
	/**
	 * 远程 执行命令并返回结果调用过程 是同步的（执行完才会返回）
	 * @param host	主机名
	 * @param user	用户名
	 * @param psw	密码
	 * @param port	端口
	 * @param command	命令
	 * @return
	 */
	public static String exec(String host,String user,String psw,int port,String command){
		String result="";
		Session session =null;
		ChannelExec openChannel =null;
		try {
			
			session = connect(host,port,user,psw);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");			
			openChannel = (ChannelExec) session.openChannel("exec");
			openChannel.setCommand(command);
			//int exitStatus = openChannel.getExitStatus();
			//System.out.println(exitStatus);
			openChannel.connect();  
			
            InputStream in = openChannel.getInputStream();
            if(in.available()==0){
            	in = openChannel.getExtInputStream();
            }
            if(in.available()==0){
            	in = openChannel.getErrStream();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));  
            String buf = null;
            while ((buf = reader.readLine()) != null) {
            	result+= new String(buf.getBytes("gbk"),"UTF-8")+"\r\n";  
            }  
		} catch (JSchException | IOException e) {
			result+=e.getMessage();
		}finally{
			if(openChannel!=null&&!openChannel.isClosed()){
				openChannel.disconnect();
			}
			if(session!=null&&session.isConnected()){
				session.disconnect();
			}
		}
		return result;
	}
	/**
	 * sftp上传文件(夹)
	 * @param directory
	 * @param uploadFile
	 * @param sftp
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public static void upload(String host,String user,String psw,int port,String directory, String uploadFile) throws Exception{
		System.out.println("sftp upload file [directory] : "+directory);
		System.out.println("sftp upload file [uploadFile] : "+ uploadFile);
		File file = new File(uploadFile);
		ChannelSftp  sftp =null;
		Session session =null;
		if(file.exists()){
			
			session = connect(host,port,user,psw);
			Channel channel = session.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			//这里有点投机取巧，因为ChannelSftp无法去判读远程linux主机的文件路径,无奈之举
			try {
				Vector content = sftp.ls(directory);
				if(content == null){
					sftp.mkdir(directory);
				}
			} catch (SftpException e) {
				sftp.mkdir(directory);
			}
			//进入目标路径
			sftp.cd(directory);
			if(file.isFile()){
				InputStream ins = new FileInputStream(file);
				//中文名称的
				sftp.put(ins, new String(file.getName().getBytes("UTF-8"),"ISO-8859-1"));
				//sftp.setFilenameEncoding("UTF-8");
			}else{
				File[] files = file.listFiles();
				for (File file2 : files) {
					String dir = file2.getAbsolutePath();
					if(file2.isDirectory()){
						String str = dir.substring(dir.lastIndexOf(File.separator));
						directory = Paths.get(directory + str).toString();
					}
					upload(host,user,psw,port,directory,dir);
				}
			}
		}
		if(sftp != null)sftp.disconnect();
		if(session != null)session.disconnect();
	}
	
	/**
	 * sftp下载文件（夹）
	 * @param directory 下载文件上级目录
	 * @param srcFile 下载文件完全路径
	 * @param saveFile 保存文件路径
	 * @param sftp ChannelSftp
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("rawtypes")
	public static void download(String host,String user,String psw,int port,String directory,String srcFile, String saveFile) throws UnsupportedEncodingException {
		Vector conts = null;
		ChannelSftp  sftp =null;
		Session session =null;
		
		try{
			session = connect(host,port,user,psw);
			Channel channel = session.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			conts = sftp.ls(srcFile);
		} catch (SftpException e) {
			e.printStackTrace();
			log.debug("ChannelSftp sftp罗列文件发生错误",e);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file = new File(saveFile);
		if(!file.exists()) file.mkdir();
		//文件
		if(srcFile.indexOf(".") > -1){
			try {
				sftp.get(srcFile, saveFile);
			} catch (SftpException e) {
				e.printStackTrace();
				log.debug("ChannelSftp sftp下载文件发生错误",e);
			}
		}else{
		//文件夹(路径)
			for (Iterator iterator = conts.iterator(); iterator.hasNext();) {
				LsEntry obj =  (LsEntry) iterator.next();
				String filename = new String(obj.getFilename().getBytes(),"UTF-8");
				if(!(filename.indexOf(".") > -1)){
					
					directory = Paths.get(directory + System.getProperty("file.separator") + filename).normalize().toString();
					srcFile = directory;
					saveFile = Paths.get(saveFile + System.getProperty("file.separator") + filename).toString();
				}else{
					//扫描到文件名为".."这样的直接跳过
					String[] arrs = filename.split("\\.");
					if((arrs.length > 0) && (arrs[0].length() > 0)){
						srcFile = Paths.get(directory + System.getProperty("file.separator") + filename).toString();
					}else{
						continue;
					}
				}
				download(host,user,psw,port,directory, srcFile, saveFile);
			}
		}
		if(sftp != null)sftp.disconnect();
		if(session != null)session.disconnect();
	}
	
	
	public static void main(String args[]){
		
	    try {
			//upload("192.168.0.89", "root", "root", 22, "/mnt/test", "G:/platform-exec_lib");
			String exec = exec("192.168.0.89", "root", "root", 22, "nohup java -jar /mnt/test/platform-exec.jar > platform.out 2>&1 &");
			System.out.println(exec);	
	    	//download("192.168.0.89", "root", "root", 22, "","log.txt","E:/");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
