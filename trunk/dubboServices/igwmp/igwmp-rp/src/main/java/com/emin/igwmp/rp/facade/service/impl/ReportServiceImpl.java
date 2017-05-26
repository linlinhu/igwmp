package com.emin.igwmp.rp.facade.service.impl;
 
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service; 
import com.emin.base.exception.EminException;  
import com.emin.igwmp.rp.dao.CoreDao;
import com.emin.igwmp.rp.facade.service.ReportService;
 
@Service("reportService")
public class ReportServiceImpl implements ReportService {
 
	CoreDao coreDao;
 
 
	@Override
	public List<Map<String, Object>> findDataListBySql(String sql, Object... pamrs) throws EminException {
		List<Map<String, Object>> list = coreDao.query(sql, pamrs);		
		return list;
	}
 
 
 

}
