package com.emin.igwmp.rstm.facade.accepters;

import java.io.Serializable;

/**
 * redis服务
 * 
 * @author zhaoqt
 *
 */
public interface RedisAccepter {

	public void sendMessage(String channel, Serializable message);

	public void put(String ObjKey, String key, String value);

	public void exipre(String key);

	public void delete(String ObjKey, String key);

	public String get(String ObjKey, String key);
}
