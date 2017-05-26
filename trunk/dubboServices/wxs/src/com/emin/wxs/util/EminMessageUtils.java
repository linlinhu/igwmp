/**
 * --------------------------------------------
 * Copyright reserved by Emin Technology
 *
 * --------------------------------------------
 * PROJECT:	DMM_DAO
 * FILE:	EminMessageUtils.java
 * AUTHOR:	admin
 * DATE CREATION:	5 Aug 2013
 * DESCRIPTION:	Description
 *
 * ============================================
 *
 * VERSION CONTROL
 *
 * Number – Date – Author – SVN – Description
 * 01 – 5 Aug 2013 – My Name – xxx – Description
 * 
 */
package com.emin.wxs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.emin.base.util.CommonsUtil;

/**
 * @since 5 Aug 2013
 * @author Cathy.Hu
 */
public class EminMessageUtils {

	private static EminMessageUtils eu = null;
	public static final String EMIN_HOME = "EMIN_HOME";
	public static final String MESSAGE_CONFIG = "message.properties";
	public static final String APICLIENT_CERT = "apiclient_cert.p12";
	
	private  static Logger logger = Logger.getLogger(EminMessageUtils.class);
	private  static Properties prop;
	
	private EminMessageUtils(){
		if(prop == null) prop = new Properties();
		FileInputStream fis = null;
		InputStreamReader fileReader = null;
		try {
			String configFile = System.getenv(EMIN_HOME)+File.separator+"ewm"+File.separator+"conf"+File.separator+MESSAGE_CONFIG;
			logger.info("System Config:"+configFile);
			fis = new FileInputStream(configFile);
			fileReader = new InputStreamReader(fis, "UTF-8");
			prop.load(fileReader);
			fis.close();
		} catch (IOException e) {
			logger.error(e);
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
			if(fileReader != null){					
				try {
					fileReader.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
	}
	
	public static EminMessageUtils getInstance(){
		if(eu==null){
			synchronized (EminMessageUtils.class) {
				if(eu==null){
					eu = new EminMessageUtils();
				}
			}
		}
		return eu;
	}
	
	public static String getMessage(String messageKey,Object...arguments){
		String message = prop.getProperty(messageKey);
		if(arguments != null
				&& arguments.length > 0 && CommonsUtil.isNotEmpty(message)){
			message = MessageFormat.format(message, arguments);
		}
		if(message==null){
			message = "";
		}
		return message;
	}
	
	public static String getWorkDesc(String resCode){
		String messageKey = "work_desc_"+resCode.toLowerCase();
		String message = prop.getProperty(messageKey);
		message = message == null?"":message;
		return message;
	}
	
	public static String getDebtMessageGnerationDefaultCron(){
		String cron = prop.getProperty("debt_message_default_cron");
		return cron;
	}
	
	public static String getApiclientCert(){
		return System.getenv(EMIN_HOME)+File.separator+"ewm"+File.separator+"conf"+File.separator+APICLIENT_CERT;
	}
}
