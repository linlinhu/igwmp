package laiebei.terminal.common.utilcode;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/8/26.
 */
public class SuUtils {

    private static Process process;
    /**
     * 结束进程,执行操作调用即可
     */
    public static void kill(String packageName) {
        initProcess();
        killProcess(packageName);
        close();
    }

    public static void start(String packageName , String activityName){
        initProcess();
        startProcess(packageName,activityName);
        close();
    }

    /**
     * 初始化进程
     */
    private static void initProcess() {
        if (process == null)
            try {
                process = Runtime.getRuntime().exec("su");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * 结束进程
     */
    private static void killProcess(String packageName) {
        OutputStream out = process.getOutputStream();
        String cmd = "am force-stop " + packageName + " \n";
        try {
            out.write(cmd.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启进程
     * */
    private static void startProcess(String packageName,String activityName){
        OutputStream out = process.getOutputStream();
        String cmd = "am start -n " + packageName + "/" + activityName +  " \n";
        try {
            out.write(cmd.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭输出流
     */
    private static void close() {
        if (process != null)
            try {
                process.getOutputStream().close();
                process = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
