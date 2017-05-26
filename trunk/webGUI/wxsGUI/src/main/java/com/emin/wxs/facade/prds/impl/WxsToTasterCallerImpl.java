package com.emin.wxs.facade.prds.impl;
	 
import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult; 
import com.emin.base.service.Condition;
import com.emin.igwmp.prds.domain.WineTaster;
import com.emin.igwmp.prds.facade.accepters.WineTasterAccepter;
import com.emin.wxs.facade.prds.WxsToTasterCaller;
 
import org.springframework.stereotype.Component;
 
import java.util.List;
	 

	/** 
	 * 品酒大师
	 * @author Administrator
	 *
	 */
	@Component("wxsToTasterCaller")
	public class WxsToTasterCallerImpl implements WxsToTasterCaller {
		
		@Reference(version="0.0.1")
		private WineTasterAccepter wineTasterAccepter;
 
		
		//查找大师
		@Override
		public PagedResult<WineTaster> loadPagedTastersByCondition(PageRequest req, List<Condition> conditions){
			PagedResult<WineTaster> Tasters = wineTasterAccepter.loadPagedWineTastersByCondition(req, conditions);
			return Tasters;
		}

			
		@Override
		public void saveOrUpdateTaster(WineTaster taster) {
			wineTasterAccepter.saveOrUpdate(taster);
			
		}

		@Override
		public void deleteTaster(Long id) {
			// TODO Auto-generated method stub
			
		}
}
