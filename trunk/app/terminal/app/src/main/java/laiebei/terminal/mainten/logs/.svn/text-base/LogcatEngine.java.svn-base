package laiebei.terminal.mainten.logs;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import laiebei.terminal.application.Config;
import laiebei.terminal.common.utilcode.DeviceUtils;
import laiebei.terminal.common.utilcode.EmptyUtils;
import laiebei.terminal.common.utilcode.RegexUtils;

/**
 * Created by Administrator on 2016/8/31.
 */
public class LogcatEngine {
    public static final String LOG_TAG = "LogcatLog";
    public static LogcatEngine INSTANCE = new LogcatEngine();;
    private static String PATH_LOGCAT;
    private LogDumper mLogDumper = null;
    private int mPId;
    private List<String> logListArray = new ArrayList<String>();
    private final long OverdueTime = 14*24*60*60*1000;//一周

    private SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMdd-HH-mm");
    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String LogImportRegex = LOG_TAG;

    /**
     * 启动log记录引擎
     * */
    public static void startLogcat(Context mcontext) {
        String folderPath = null;

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {// save in SD card first
            folderPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Config.NOTE;
        } else {// If the SD card does not exist, save in the directory of application.

            folderPath = mcontext.getFilesDir().getAbsolutePath() + File.separator + Config.NOTE;
        }

        INSTANCE.start(folderPath);
    }

    /**
     * 停止log记录引擎
     * */
    public static void stopLogcat() {
        INSTANCE.stop();
    }

    /**
     * 添加关键日志记录TAG
     * */
    public void addImportTag(String Tag){
        if(EmptyUtils.isEmpty(Tag)){
            return;
        }
        LogImportRegex += "|" + Tag;
    }



    private LogcatEngine() {
        mPId = android.os.Process.myPid();
    }

    private void setFolderPath(String folderPath) {

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        if (!folder.isDirectory())
            throw new IllegalArgumentException("The logcat folder path is not a directory: " + folderPath);

        PATH_LOGCAT = folderPath.endsWith("/") ? folderPath : folderPath + "/";
    }


    public void start(String saveDirectoy) {

        setFolderPath(saveDirectoy);

        if (mLogDumper == null)
            mLogDumper = new LogDumper(String.valueOf(mPId), PATH_LOGCAT);
        mLogDumper.start();
    }

    public void stop() {
        if (mLogDumper != null) {
            mLogDumper.stopLogs();
            mLogDumper = null;
        }
    }

    /*
    * 删除7天前过期的文件
    * */
    private void deleteOverdueFile(){
        File fp = new File(PATH_LOGCAT);
        if(fp.exists()&&fp.isDirectory()){
            logListArray.clear();
            getDirectoryAllFile(fp);
        }

        for (String logPath : logListArray) {
            File mfp = new File(logPath);
            Date date = new Date(mfp.lastModified());
            Date CurrentDate = new Date();
//            Log.d("aleyds", "FileName:" + mfp.getAbsolutePath()  + "  Last  data:" + date.getTime() + "  CurDate:" + CurrentDate.getTime());
            if(TimeCompare(date, CurrentDate)){
                mfp.delete();
            }
        }
    }

    private boolean TimeCompare(Date time, Date Current){
        if(Current.getTime() - time.getTime() >= OverdueTime){
            return true;
        }else{
            return false;
        }
    }

    private  void getDirectoryAllFile(File directory){
        File mDirectoryFiles[] = directory.listFiles();
        if(mDirectoryFiles != null){
            for (File f:mDirectoryFiles) {
                if (f.isDirectory()){
                    getDirectoryAllFile(f);
                }else{
                    logListArray.add(f.getAbsolutePath());
                }
            }
        }
    }

    private void killAllLogcat(){
        String cmd = "ps | grep logcat";
        try {
            String line = "";
            Process myprocess = Runtime.getRuntime().exec(cmd);
            BufferedReader reader  = new BufferedReader(new InputStreamReader(myprocess.getInputStream()), 1024);
            while((reader != null) &&(line = reader.readLine()) != null){
                String[] myStrs = line.split(" ");
                for(String Str : myStrs){
                    if((Str != null)&& RegexUtils.isPureDigital(Str)){
                        String killcmd = "kill " + Integer.parseInt(Str);
                        Runtime.getRuntime().exec(killcmd);
                        break;
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private class LogDumper extends Thread {

        //公共变量
        private String mPID;//进程PID
        private boolean mRunning = true;//线程运行标志

        private FileOutputStream importLogOut = null;//关键log

        //所有日志变量
        private Process alllogcatProc;//
        private BufferedReader allmReader = null;
        private String allCmds = null;//所有log
        private FileOutputStream allLogOut = null;//所有log

        public LogDumper(String pid, String dir) {
            mPID = pid;
            LogcatInit(dir,pid);
        }

        private void LogcatInit(String dir,String pid){
            killAllLogcat();//清除由于多次打开APK  导致logcat进程驻留问题

            deleteOverdueFile();//删除过期文件

            allCmds = "logcat *:e *:w *:d | grep \"(" + pid + ")\"";//日志规则
            try {
                //关键日志文件输入流
                importLogOut = new FileOutputStream(new File(dir, Config.IMPORT_NOTE  + DeviceUtils.getSerialID() + "-"+  simpleDateFormat1.format(new Date()) + ".log"), true);
                //全部日志文件输入流
                allLogOut = new FileOutputStream(new File(dir, Config.ALL_NOTE  + DeviceUtils.getSerialID() + "-"+  simpleDateFormat1.format(new Date()) + ".log"), true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        private void LogcatWrite(String line)throws IOException {
            if(!EmptyUtils.isEmpty(line) && line.contains(mPID)){
                //记录关键日志
                if(isImportLogcat(line) && importLogOut != null){
                    importLogOut.write((simpleDateFormat2.format(new Date()) + "  " + line + "\n").getBytes());
                }
                //记录全日志
                if(allLogOut != null){
                    allLogOut.write((simpleDateFormat2.format(new Date()) + "  " + line + "\n").getBytes());
                }
            }
        }

        private void releaseFileStream(){
            if (importLogOut != null) {
                try {
                    importLogOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                importLogOut = null;
            }
            //释放全日志资源
            if (alllogcatProc != null) {
                alllogcatProc.destroy();
                alllogcatProc = null;
            }
            if (allmReader != null) {
                try {
                    allmReader.close();
                    allmReader = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (allLogOut != null) {
                try {
                    allLogOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                allLogOut = null;
            }
        }

    public void stopLogs() {
        mRunning = false;
    }

    private  boolean isImportLogcat(String LineStr){
        Pattern p = Pattern.compile(LogImportRegex);
        Matcher m = p.matcher(LineStr);
        return m.find();
    }

    @Override
    public void run() {
        try {
            alllogcatProc = Runtime.getRuntime().exec(allCmds);
            allmReader = new BufferedReader(new InputStreamReader(alllogcatProc.getInputStream()), 1024);
            String lineAll = null;
            while (mRunning ) {
                if (!mRunning) {
                    break;
                }
                if((allmReader != null) &&(lineAll = allmReader.readLine()) != null){
                    LogcatWrite(lineAll);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            releaseFileStream();
        }

    }

}
}
