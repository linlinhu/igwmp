
package com.emin.platform.util;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class CookieUtil {

 public static void addCookie(HttpServletResponse response,String name,String value,int maxAge){
  try {
   Cookie cookie=new Cookie(name,URLEncoder.encode(value, "utf-8"));
   cookie.setMaxAge(maxAge);
   cookie.setPath("/");
   //cookie.setDomain(".emin-tech.com");
   response.addHeader("P3P", "CP=\"NON DSP COR CURa ADMa DEVa TAIa PSAa PSDa IVAa IVDa CONa HISa TELa OTPa OUR UNRa IND UNI COM NAV INT DEM CNT PRE LOC\"");
   response.addCookie(cookie);

  } catch (UnsupportedEncodingException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }
 public static String getValue(HttpServletRequest request,String name){
  String value=null;
  Cookie[] cookies=request.getCookies();
  if(cookies!=null){
   for(Cookie cookie:cookies){
    if(name.equals(cookie.getName())){
     try {
      value=URLDecoder.decode(cookie.getValue(),"utf-8");
     } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }
     break;
    }
   }
  }
  return value;
 }
 public static void delCookie(HttpServletResponse response,String name){
  Cookie cookie=new Cookie(name,"");
  cookie.setMaxAge(0);
  response.addCookie(cookie);
 }
}
