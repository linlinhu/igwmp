package com.emin.wxs.util;

import com.emin.base.util.ThreadLocalUtil;



public class WxsThreadLocalUtil extends ThreadLocalUtil {
	
	/**
     * 当前用户ID
     */
    private static final ThreadLocal<Long> personLocal = new ThreadLocal<Long>();  
   
   
    
    public static void setPersonId(Long personId){
    	personLocal.set(personId);
    }
    public static Long getPersonId(){
      
    	return personLocal.get();
    }
   
  
    
    
}
