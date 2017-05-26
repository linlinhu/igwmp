package com.emin.igwmp.tws.redis;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;
@Component("redisDao")
public class RedisDao {

	private static final Logger logger = LoggerFactory.getLogger(RedisDao.class);
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

   
    public void sendMessage(String channel, Serializable message) {
        redisTemplate.convertAndSend(channel, message);
    }

   
	public void put(String key,JSONObject json) {  
    	
        redisTemplate.opsForValue().set(key, json.toString());
    }  
  
    public void expire(String key,Long time,TimeUnit timeUnit){
    	Boolean success = redisTemplate.expire(key, time, timeUnit);
    	logger.info("key:"+key+" 过期设置"+(success?"成功":"失败"));
    }
    public void cancelExpire(String key){
    	Boolean success = redisTemplate.persist(key);    
    	logger.info("key:"+key+" 取消过期设置"+(success?"成功":"失败"));
    }
    
    public void delete(String key) {  
        redisTemplate.opsForValue().getOperations().delete(key);
    }  
  
    public String get(String key) {    	
        return (String) redisTemplate.opsForValue().get(key);
    }  
 
    public boolean contains(String key){
    	return redisTemplate.opsForValue().getOperations().hasKey(key);
    }
    public Set<String> query(String pattern){
    	return redisTemplate.keys(pattern);
    }
}

