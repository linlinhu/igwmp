package com.emin.wxs.util;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 
 * 日期 : 2015-07-08<br>
 * 作者 : michael<br>
 * 功能 : 日期工具类<br>
 */
public class DateUtil {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	static GregorianCalendar gc = new GregorianCalendar();

	/**
	 * 
	 * 功能说明 : 将页面yyyy年MM月dd日 转换为yyyy-MM-dd <br>
	 * 或 将yyyy年MM月dd日 HH:mm:ss转换为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param cnStr
	 * @return
	 */
	public static String cnDate2EnDate(String cnStr) {
		if (cnStr == null || cnStr.trim().length() == 0) {
			return cnStr;
		}
		try {
			if (cnStr.trim().length() <= 11) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
				return sdf.format(df.parse(cnStr));
			} else {
				SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
				return sdf2.format(df.parse(cnStr));
			}
		} catch (Exception e) {
		}
		return cnStr;
	}

	/**
	 * 
	 * 功能说明 : 将页面yyyy-MM-dd转换为yyyy年MM月dd日 <br>
	 * 或 将yyyy-MM-dd HH:mm:ss转换为yyyy年MM月dd日 HH:mm:ss
	 * 
	 * 
	 * @param cnStr
	 * @return
	 */
	public static String enDate2CnDate(String enStr) {
		if (enStr == null || enStr.trim().length() == 0) {
			return enStr;
		}
		try {
			if (enStr.trim().length() <= 10) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
				return df.format(sdf.parse(enStr));
			} else {
				SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
				return df.format(sdf2.parse(enStr));
			}
		} catch (Exception e) {
		}
		return enStr;
	}

	/**
	 * 
	 * getSysDate 作用: 得到系统时间
	 * 
	 * @return java.util.Date
	 */
	public static Date getSysDate() {
		return new Date();
	}

	/**
	 * 
	 * getSysCalendar 作用: 得到系统时间
	 * 
	 * @return java.util.Calendar
	 */
	public static Calendar getSysCalendar() {
		Calendar currCalendar = Calendar.getInstance();
		return currCalendar;
	}

	/**
	 * 取得当前月的字符串形式yyyy-MM
	 */
	public static String getCurrMonth() {
		return (new SimpleDateFormat("yyyy-MM")).format(new Date());

	}

	/**
	 * 取得当前时间的字符串形式yyyy-MM-dd
	 */

	public static String getCurrDate() {
		return (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
	}

	/**
	 * 取得当前时间的字符串形式yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrTime() {
		return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
	}

	/**
	 * 字符串转换成日期
	 * 
	 */
	public static Date parseDate(String s) {
		Date d = null;

		try {
			if (s.length() <= 10) {
				d = sdf.parse(s);
			} else {
				d = sdf2.parse(s);
			}

		} catch (Exception e) {
		}

		return d;
	}

	/**
	 * 字符串转换成Calendar
	 */
	public static Calendar parseCalendar(String s) {
		Calendar c = null;
		Date d = parseDate(s);

		if (d != null) {
			c = Calendar.getInstance();
			c.setTime(d);
		}
		return c;
	}

	/**
	 * 字符串转换成日期
	 */
	public static Date parseDateTime(String s) {
		Date d = null;

		try {
			d = sdf2.parse(s);

		} catch (Exception e) {
		}

		return d;
	}

	public static String format(Date d, String format) {
		if (d == null) {
			return null;
		}
		SimpleDateFormat sdformat = new SimpleDateFormat(format);
		return sdformat.format(d);
	}

	/*
	 * 转换日期和时间为指定格式的字符串 用例：format(new Now(), "yyyy-MM-dd") format(new Now(),
	 * "yyyy-MM") format(new Now(), "yyyy-MM-dd HH:mm:ss")
	 */
	public static String format(Calendar c, String format) {
		return format(c == null ? (Date) null : c.getTime(), format);
	}
	
	/**
	 * 
	 * Description :  返回 yyyy-MM-dd HH:mm:ss格式
	 * @param c
	 * @return
	 */
	public static String formatTime(Calendar c) {
		return format(c == null ? (Date) null : c.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * 
	 * 功能说明 : 自定义格式
	 * 
	 * @param s
	 * @param format
	 * @return
	 */
	public static String format(String s, String format) {
		Date d = parseDate(s);

		if (d == null) {
			return null;
		}

		return format(d, format);
	}

	public static String format(String s) {
		return format(s, "yyyy-MM-dd");
	}

	
	////////////////////////////////////////

	/**
	 * 把数据组成字符串
	 */
	public static String paseToString(String[] str,int start,int end){
		StringBuffer sub = new StringBuffer("");
		for (int i = start; i <= end; i++) {
			sub.append(str[i]);
		}
		return sub.toString();
	}
	
	/**
	 * 16进制转换成10进制
	 */
	public static int paseInteger(String str){
		int t = 0;
		if (null != str && !"".equals(str)) {
			t = new BigInteger(str, 16).intValue();
		}
		return t;
	}
	public static long paseLong(String str){
		long t = 0;
		if (null != str && !"".equals(str)) {
			t = new BigInteger(str, 16).longValue();
		}
		return t;
	}
	
	/**
	 * 根据设备的时间基准获取时间
	 */
	public static String parseString(Integer second) {
		String str = "2000-1-1 00:00:00";
		Calendar date = parseCalendar(str);
		date.add(Calendar.SECOND, second);
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 把数据组成字符串
	 */
	public static String paseToString2(String[] str,int start,int end){
		StringBuffer sub = new StringBuffer("");
		for (int i = start; i <= end; i++) {
			sub.append(str[i]);
		}
		return sub.toString();
	}
	
	public static String parseStringToSpaceString(String reqStr) {
		List<String> strlist = new ArrayList<String>();
		StringBuffer sub = new StringBuffer(reqStr);
		for (int i = 0; i < reqStr.length(); i++) {
			strlist.add(String.valueOf(sub.charAt(i)));
			if (i > 0 && i%2 == 1) {
				strlist.add(" ");
			}
		}
		StringBuffer s = new StringBuffer("");
		for (String string : strlist) {
			s.append(string);
		}
		return s.toString();
		
	}
	
	public static Date getMonthStartDay(){
		Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        return c.getTime();
	}
	public static Date getMonthEndDay(){
		Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        return ca.getTime();
	}
	
	
}
