package com.emin.wxs.service;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.wxs.domain.Fans;
import com.emin.wxs.domain.FansItem;

public interface FansService extends UndeleteableService<Fans>{
   void  saveOrUpdateFans(Fans fans);
   PagedResult<Fans> queryUserByCondition(PageRequest pRequest,List<Condition> conditions);
   
   void saveAllFans(Long woaId);
   
   void saveInitFans(Long woaId);
   
   void updateUserByOpenId(Long woaId);
   
   FansItem loadByOpenId(String openId, Long woaId);
   
   void syncWoaFansInfo();
   
   FansItem loadByOpenId(String openId);
   
   void saveFansItem(FansItem item) throws EminException;
   
   Fans loadByUnionId(String unionId);
   
}
