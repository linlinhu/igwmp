package com.emin.igwmp.rstm.redis;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
@Component("redisDao")
public class RedisDao {

	private static final Logger logger = LoggerFactory.getLogger(RedisDao.class);
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

   
    public void sendMessage(String channel, Serializable message) {
        redisTemplate.convertAndSend(channel, message);
    }

    public void put(String ObjKey,String key,String value) {  
        redisTemplate.opsForHash().put(key, ObjKey, value);  
    }  
  
    public void exipre(String key){
    	Boolean success = redisTemplate.expire(key, 10l, TimeUnit.SECONDS);
    	logger.info(success.toString());
    }
    
    public void delete(String ObjKey,String key) {  
    	
        redisTemplate.opsForHash().delete(key,ObjKey);  
    }  
  
    public String get(String ObjKey,String key) {  
        return (String) redisTemplate.opsForHash().get(key,ObjKey);  
    }  

   
}
