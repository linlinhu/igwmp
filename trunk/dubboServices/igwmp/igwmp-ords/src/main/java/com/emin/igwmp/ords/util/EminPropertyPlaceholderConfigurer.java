package com.emin.igwmp.ords.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EminPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{

	private static Map<String, Object> ctxPropertiesMap;//存放所有通过spring加载的properties配置信息  
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		
		super.processProperties(beanFactoryToProcess, props);
		//load properties to ctxPropertiesMap  
		if(ctxPropertiesMap==null){
			ctxPropertiesMap = new HashMap<String, Object>();  
		}
         
        for (Object key : props.keySet()) {  
            String keyStr = key.toString();  
            String value = props.getProperty(keyStr);  
            ctxPropertiesMap.put(keyStr, value);  
        }  
	}
	 //static method for accessing context properties  
    public static String getContextProperty(String name) {  
        return (String) ctxPropertiesMap.get(name);  
    }  
	
}
