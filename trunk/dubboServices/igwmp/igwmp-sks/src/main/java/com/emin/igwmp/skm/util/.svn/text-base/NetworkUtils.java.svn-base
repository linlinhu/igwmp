package com.emin.igwmp.skm.util;

import java.net.InetAddress;

/**
 * Created by Administrator on 2017/4/22.
 */
public class NetworkUtils {
    private NetworkUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String getLocalIP(){
        InetAddress ia=null;
        try {
            ia=ia.getLocalHost();
            String localip = ia.getHostAddress();
            return localip;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }
}
