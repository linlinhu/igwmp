package com.emin.igwmp.rstm.facade.accepters.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.igwmp.rstm.facade.accepters.RedisAccepter;
import com.emin.igwmp.rstm.redis.RedisDao;

@Service(version="0.0.1")
@Component("redisAccepter")
public class RedisAccepterImpl implements RedisAccepter {
	
	@Resource
	RedisDao redisDao;
	
	public void sendMessage(String channel, Serializable message) {
		redisDao.sendMessage(channel, message);
	}

	public void put(String ObjKey, String key, String value) {
		redisDao.put(key, ObjKey, value);
	}

	public void exipre(String key) {
		redisDao.exipre(key);
	}

	public void delete(String ObjKey, String key) {
		redisDao.delete(key, ObjKey);
	}

	public String get(String ObjKey, String key) {
		return (String) redisDao.get(key, ObjKey);
	}
}
