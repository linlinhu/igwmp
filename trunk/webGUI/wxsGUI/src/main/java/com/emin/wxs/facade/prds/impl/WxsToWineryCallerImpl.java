package com.emin.wxs.facade.prds.impl;
 
import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult; 
import com.emin.base.service.Condition; 
import com.emin.igwmp.prds.domain.Winery;
import com.emin.igwmp.prds.facade.accepters.WineryAccepter;
import com.emin.wxs.facade.prds.WxsToWineryCaller;
 
import org.springframework.stereotype.Component;
 
import java.util.List;
 

/** 
 * 商品
 * @author Administrator
 *
 */
@Component("wxsToWineryCaller")
public class WxsToWineryCallerImpl implements WxsToWineryCaller {
	
	@Reference(version="0.0.1")
	private WineryAccepter wineryAccepter;

	@Override
	public void saveOrUpdateWinery(Winery winery) {
		wineryAccepter.saveOrUpdate(winery);
	}

	//查找商品
	@Override
	public PagedResult<Winery> loadPagedPCWinerysByCondition(PageRequest req, List<Condition> conditions){
		PagedResult<Winery> winerys = wineryAccepter.loadPagedWinerysByCondition(req, conditions);
		return winerys;
	}


	@Override
	public void deleteWinery(Long id) {
		wineryAccepter.deleteWinery(id);
		// TODO Auto-generated method stub
		
	} 
}
