package com.emin.platform.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.telnet.TelnetClient;

public class WindowsTelnetClient  
{  
	/** Telnet服务器返回的字符集 */  
    private static final String SRC_CHARSET = "ISO8859-1";  
  
    /** 转换后的字符集 */  
    private static final String DEST_CHARSET = "GBK"; 
    
    private static FTPClient ftpClient = new FTPClient();
    
    private static String encoding = System.getProperty("file.encoding");
    /** 
     * 终端类型。包括以下类型：VT102、VT100、VT220、WYSE50、WYSE60、XTERM、SCOANSI、ANSI、LINUX、 
     * VSHELL几种。经测试，对于Windows的Telnet服务器，只有VT100、ANSI类型会造成中文乱码 
     */  
    private static final String TERM_TYPE = "VT220";  
  
    private TelnetClient client = new TelnetClient(TERM_TYPE);// Telnet客户端  
    private InputStream input; // Telnet输入流，用于获取Telnet服务器的返回信息  
    private OutputStream output; // Telnet输出流，用于向服务器发送命令  
    private String hostname; // IP地址或主机名  
    private int port = 23; // 端口。默认为23  
    private String username; // 用户名  
    private String password; // 密码  
    private String prompt; // 命令提示符，用于判断是否读取到了返回信息的结尾  
  
    /** 
     * 创建Telnet客户端，用于连接Windows的Telnet服务器。使用默认端口：23 
     *  
     * @param hostname 
     *            - IP地址，或主机名 
     * @param username 
     *            - 用户名 
     * @param password 
     *            - 密码 
     */  
    public WindowsTelnetClient(String hostname, String username, String password) {  
        this.hostname = hostname;  
        this.username = username;  
        this.password = password;  
    }  
  
    /** 
     * 创建Telnet客户端，用于连接Windows的Telnet服务器 
     *  
     * @param hostname 
     *            - IP地址，或主机名 
     * @param port 
     *            - 端口 
     * @param username 
     *            - 用户名 
     * @param password 
     *            - 密码 
     */  
    public WindowsTelnetClient(String hostname, int port, String username, String password) {  
        this.hostname = hostname;  
        this.port = port;  
        this.username = username;  
        this.password = password;  
    }  
  
    /** 
     * 连接到Telnet服务器 
     *  
     * @return - Telnet服务器的返回信息。截止到password： 
     * @throws SocketException 
     * @throws IOException 
     */  
    public String connect() throws SocketException, IOException {  
        client.connect(hostname, port);  
        input = client.getInputStream();  
        output = client.getOutputStream();  
        // 因为不知道服务器返回的是Login： 还是 login： ，所以忽略l  
        String loginOutput = readTo("ogin: ");  
        output.write((username + "\r\n").getBytes());  
        output.flush();  
        // 因为不知道服务器返回的是Password： 还是 password： ，所以忽略p  
        String passwordOutput = readTo("assword: ");  
        output.write((password + "\r\n").getBytes());  
        output.flush();  
        String promptOutput = readTo(">");  
        // 取倒数4位字符作为提示符，因为提示符最短为4位，如：C:\>  
        prompt = promptOutput.substring(promptOutput.length() - 4);  
        return loginOutput + passwordOutput + password + promptOutput;  
    }  
  
    /** 
     * 向Telnet服务器发送命令 
     *  
     * @param command 
     *            - 命令 
     * @return - 执行命令后，在命令行输出的信息 
     * @throws IOException 
     */  
    public String sendCommand(String command) throws IOException {  
        output.write(command.getBytes());  
        output.write('\r');  
        output.write('\n');  
        output.flush();  
        return readToPrompt();  
    }  
  
    /** 
     * 断开连接 
     *  
     * @return - 断开连接的命令 
     */  
  
    public String disconnect() {  
        try {  
            input.close();  
            output.close();  
            client.disconnect();  
        } catch (Exception e) {  
        }  
  
        return "exit";  
    }  
  
    /** 
     * 读取后指定的字符处 
     *  
     * @param end 
     *            - 指定的字符 
     * @return - 从上次读取的位置，到<code>end</code>位置的输出内容 
     */  
    private String readTo(String end) {  
        StringBuffer sb = new StringBuffer();  
  
        char endChar = end.charAt(end.length() - 1);  
        char chr;  
        try {  
            while (true) {  
                chr = (char) input.read();  
                sb.append(chr);  
                if (chr == endChar && sb.toString().endsWith(end)) {  
                    return new String(sb.toString().getBytes(SRC_CHARSET), DEST_CHARSET); // 编码转换，解决中文乱码问题  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
        return "";  
    }  
  
    /** 
     * 读取后命令提示符 
     *  
     * @return - 从上次读取的位置，到命令提示符的输出内容 
     */  
    private String readToPrompt() {  
        return readTo(prompt);  
    }
    
    /**
     * Description: 向FTP服务器上传文件
     * 
     * @Version1.0
     * @param url
     *   FTP服务器hostname
     * @param port
     *   FTP服务器端口
     * @param username
     *   FTP登录账号
     * @param password
     *   FTP登录密码
     * @param path
     *   FTP服务器保存目录,如果是根目录则为“/”
     * @param filename
     *   上传到FTP服务器上的文件名
     * @param input
     *   本地文件输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String url, int port, String username,
      String password, String path, String filename, InputStream input) {
     boolean result = false;
    
     try {
      int reply;
      // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
      ftpClient.connect(url);
      // ftp.connect(url, port);// 连接FTP服务器
      // 登录
      ftpClient.login(username, password);
      ftpClient.setControlEncoding(DEST_CHARSET);
      // 检验是否连接成功
      reply = ftpClient.getReplyCode();
      if (!FTPReply.isPositiveCompletion(reply)) {
       System.out.println("连接失败");
       ftpClient.disconnect();
       return result;
      }
    
      // 转移工作目录至指定目录下
      boolean change = ftpClient.changeWorkingDirectory(path);
      ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
      if (change) {
       result = ftpClient.storeFile(new String(filename.getBytes(DEST_CHARSET),"iso-8859-1"), input);
       if (result) {
        System.out.println("上传成功!");
       }
      }
      input.close();
      ftpClient.logout();
     } catch (IOException e) {
      e.printStackTrace();
     } finally {
      if (ftpClient.isConnected()) {
       try {
        ftpClient.disconnect();
       } catch (IOException ioe) {
       }
      }
     }
     return result;
    }
    /**
     * Description: 从FTP服务器下载文件
     * 
     * @Version1.0
     * @param url
     *   FTP服务器hostname
     * @param port
     *   FTP服务器端口
     * @param username
     *   FTP登录账号
     * @param password
     *   FTP登录密码
     * @param remotePath
     *   FTP服务器上的相对路径
     * @param fileName
     *   要下载的文件名
     * @param localPath
     *   下载后保存到本地的路径
     * @return
     */
    public static boolean downFile(String url, int port, String username,
      String password, String remotePath, String fileName,
      String localPath) {
     boolean result = false;
     try {
      int reply;
      ftpClient.setControlEncoding(DEST_CHARSET);
       
      /*
       * 为了上传和下载中文文件，有些地方建议使用以下两句代替
       * new String(remotePath.getBytes(encoding),"iso-8859-1")转码。
       * 经过测试，通不过。
       */
   //   FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
   //   conf.setServerLanguageCode("zh");
    
      ftpClient.connect(url, port);
      // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
      ftpClient.login(username, password);// 登录
      // 设置文件传输类型为二进制
      ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
      // 获取ftp登录应答代码
      reply = ftpClient.getReplyCode();
      // 验证是否登陆成功
      if (!FTPReply.isPositiveCompletion(reply)) {
       ftpClient.disconnect();
       System.err.println("FTP server refused connection.");
       return result;
      }
      // 转移到FTP服务器目录至指定的目录下
      ftpClient.changeWorkingDirectory(new String(remotePath.getBytes(DEST_CHARSET),"iso-8859-1"));
      // 获取文件列表
      FTPFile[] fs = ftpClient.listFiles();
      for (FTPFile ff : fs) {
       if (ff.getName().equals(fileName)) {
        File localFile = new File(localPath + "/" + ff.getName());
        OutputStream is = new FileOutputStream(localFile);
        ftpClient.retrieveFile(ff.getName(), is);
        is.close();
       }
      }
    
      ftpClient.logout();
      result = true;
     } catch (IOException e) {
      e.printStackTrace();
     } finally {
      if (ftpClient.isConnected()) {
       try {
        ftpClient.disconnect();
       } catch (IOException ioe) {
       }
      }
     }
     return result;
    }
    public static void main(String[] args) throws Exception {  
        /*String hostname = "192.168.0.222"; // or:127.0.0.1  
        int port = 23;  
        String username = "Administrator";  
        String password = "EminServer2016";  
        WindowsTelnetClient client = new WindowsTelnetClient(hostname, port, username, password);  
        System.out.print(client.connect());  
        System.out.print(client.sendCommand("dir")); // 执行windows命令  
        System.out.print(client.disconnect());  */
    	
    	// FileInputStream in = new FileInputStream(new File("E:/1.mp4"));
    	// uploadFile("192.168.0.222", 21, "rd", "developer", "/","测试.mp4", in);
    	
    	downFile("192.168.0.222", 21, "rd", "developer", "/","测试.mp4", "E:/");
    }  
}
