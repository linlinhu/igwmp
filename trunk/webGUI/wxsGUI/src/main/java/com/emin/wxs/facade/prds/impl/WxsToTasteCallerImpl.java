package com.emin.wxs.facade.prds.impl;
	 
import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult; 
import com.emin.base.service.Condition;
import com.emin.igwmp.prds.domain.Taste;
import com.emin.igwmp.prds.facade.accepters.TasteAccepter;
import com.emin.igwmp.prds.facade.accepters.WineTasterAccepter;
import com.emin.wxs.facade.prds.WxsToTasteCaller;
 
import org.springframework.stereotype.Component;
 
import java.util.List;
	 

	/** 
	 * 品酒大师
	 * @author Administrator
	 *
	 */
	@Component("wxsToTasteCaller")
	public class WxsToTasteCallerImpl implements WxsToTasteCaller {
		
		@Reference(version="0.0.1")
		private TasteAccepter tasteAccepter;
		
		//查找大师
		@Override
		public PagedResult<Taste> loadPagedTastesByCondition(PageRequest req, List<Condition> conditions){
			PagedResult<Taste> Tastes = tasteAccepter.loadPagedTastesByCondition(req, conditions);
			return Tastes;
		}
			
		@Override
		public void deleteTaste(Long id) {
			tasteAccepter.deleteTaste(id);
			
		}

		@Override
		public void saveOrUpdateTaste(Taste taste) {
			/*if(null==taste.getId()||taste.getId()<=0){
				taste.setCreateTime(System.currentTimeMillis());
			}
			taste.setLastModifyTime(System.currentTimeMillis());
			taste.setStatus(Taste.STATUS_VALID);*/
			tasteAccepter.saveOrUpdate(taste);
		}

}
