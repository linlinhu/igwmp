package com.emin.igwmp.skm.exception;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.emin.igwmp.skm.util.SocketLocalUtils;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Administrator on 2017/3/16.
 */
public class RunException extends RuntimeException {
    private Logger logger = LoggerFactory.getLogger(RunException.class);
    private static final long serialVersionUID = 1L;
    public static final String PROPERTIES_FILE_NAME = "exception";
    public static final String EXCEPTION_KEY_PREFIX = "exception.code.";
    private final String code;
    private final Object[] arguments;

    public RunException(String code, Object... arguments) {
        this.code = code;
        this.arguments = arguments;
    }

    public RunException(Throwable e, String code, Object... arguments) {
        super(e);
        this.code = code;
        this.arguments = arguments;
    }

    public String getLocalizedMessage() {
        Locale locale = SocketLocalUtils.getLocale();
        if(locale == null) {
            locale = Locale.CHINA;
        }

        try {
            ResourceBundle t = ResourceBundle.getBundle("exception", locale);
            String message = t.getString("exception.code." + this.code);
            return this.arguments != null && this.arguments.length != 0? MessageFormat.format(message, this.arguments):message;
        } catch (Throwable var4) {
            var4.printStackTrace();
            return "";
        }
    }

    public String getCode() {
        return this.code;
    }

    public Object[] getArguments() {
        return this.arguments != null? Arrays.copyOf(this.arguments, this.arguments.length):null;
    }

    public void printStackTrace() {
        StringBuffer msg = new StringBuffer();
        StackTraceElement[] var5;
        int var4 = (var5 = this.getStackTrace()).length;

        for(int var3 = 0; var3 < var4; ++var3) {
            StackTraceElement ele = var5[var3];
            msg.append("FileName:" + ele.getFileName() + ",ClassName:" + ele.getClassName() + ",MethodName:" + ele.getMethodName() + ",LineNumber:" + ele.getLineNumber() + ";");
        }

        this.logger.warn(this.getLocalizedMessage());
        this.logger.warn(msg.toString());
    }
}
