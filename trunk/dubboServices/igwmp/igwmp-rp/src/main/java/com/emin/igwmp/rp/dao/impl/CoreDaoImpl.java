package com.emin.igwmp.rp.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.emin.igwmp.rp.dao.CoreDao;

public class CoreDaoImpl implements CoreDao{
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Override
	public int cud(String sql,Object ...args){
		return jdbcTemplate.update(sql,args);
	}
	@Override
	public List<Map<String, Object>> query(String sql,Object ...args){
		
		return jdbcTemplate.queryForList(sql,args);
	}
	@Override
	public Map<String, Object> queryUnique(String sql,Object ...args){
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	@Override
	public void batchCud(String sql,List<Object[]> args){
		jdbcTemplate.batchUpdate(sql,args);
	}
	
}
