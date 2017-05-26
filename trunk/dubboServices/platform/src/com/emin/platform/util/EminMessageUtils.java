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
package com.emin.platform.util;

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

	public static final String EMIN_HOME = "EMIN_HOME";
	public static final String MESSAGE_CONFIG = "message.properties";
	private  static Logger logger = Logger.getLogger(EminMessageUtils.class);
	private  static Properties prop;
	
	private  static void loadSysEnvs(){
		if(prop == null){
			 prop = new Properties();
		}
		
		FileInputStream fis = null;
		InputStreamReader fileReader = null;
		try {
	
			String configFile = System.getenv(EMIN_HOME)+File.separator+"rms"+File.separator+"conf"+File.separator+MESSAGE_CONFIG;
			logger.info("System Config:"+configFile);
			fis = new FileInputStream( configFile);
			fileReader = new InputStreamReader(fis, "UTF-8");
			prop.load(fileReader);
			fis.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			
			if(fileReader != null){					
				
				try {
					fileReader.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getMessage(String messageKey,Object...arguments){
		loadSysEnvs();
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
		loadSysEnvs();
		String messageKey = "work_desc_"+resCode.toLowerCase();
		String message = prop.getProperty(messageKey);
		message = message == null?"":message;
		return message;
	}
	
	public static String getDebtMessageGnerationDefaultCron(){
		loadSysEnvs();
		String cron = prop.getProperty("debt_message_default_cron");
		return cron;
	}
}
