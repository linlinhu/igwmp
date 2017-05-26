package com.emin.igwmp.skm.util;

import com.emin.igwmp.skm.core.MConfig;
import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2017/3/17.
 */
public class LogUtils extends Logger{

    private static org.apache.log4j.Logger log = Logger
            .getLogger(LogUtils.class);


    protected LogUtils(String name) {
        super(name);
    }

    public static void setLevel(){
        log.setLevel(MConfig.DEBUG_LEVEL);
    }


    public static void D(Object message){
        if(MConfig.DEBUG_SW){
            log.debug(message);
        }
    }

    public static void W(Object message){
        if(MConfig.DEBUG_SW){
            log.warn(message);
        }
    }

    public static void I(Object message){
        if(MConfig.DEBUG_SW){
            log.info(message);
        }
    }

    public static void E(Object message){
        if(MConfig.DEBUG_SW){
            log.error(message);
        }
    }



}
