package com.emin.igwmp.skm.util;

/**
 * Created by Administrator on 2017/4/18.
 */
public class LegalUtils {

    private LegalUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isSelling(double price,int remainder , int warning){
        if(remainder <= 0){
            return false;
        }
        if(remainder < warning || (price - 0.001 < 0.01)){
            return false;
        }
        return true;
    }

}
