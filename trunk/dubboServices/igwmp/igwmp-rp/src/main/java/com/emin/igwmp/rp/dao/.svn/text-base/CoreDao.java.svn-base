package com.emin.igwmp.rp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public interface CoreDao {

	int cud(String sql,Object ...args);

	List<Map<String,Object>> query(String sql,Object ...args);

	Map<String,Object> queryUnique(String sql,Object ...args);

	JdbcTemplate getJdbcTemplate();

	void batchCud(String sql, List<Object[]> args);

}
