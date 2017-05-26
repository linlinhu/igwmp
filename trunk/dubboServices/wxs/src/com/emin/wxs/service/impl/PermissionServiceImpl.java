package com.emin.wxs.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PreFilters;
import com.emin.base.dao.SortKey;
import com.emin.base.exception.EminException;
import com.emin.base.service.CRUDServiceImpl;
import com.emin.base.util.CommonsUtil;
import com.emin.platform.facade.accepters.OrgAccepter;
import com.emin.wxs.domain.Menu;
import com.emin.wxs.domain.Operation;
import com.emin.wxs.domain.OrgOperation;
import com.emin.wxs.exception.WXSExceptionCode;
import com.emin.wxs.service.PermissionService;
@Service("permissionService")
public class PermissionServiceImpl extends CRUDServiceImpl<OrgOperation> implements PermissionService{

	@Reference(version="0.0.1")
	private OrgAccepter orgAccepter;
	
	@Transactional
	@Override
	public void savePermission(Long orgId,Long[] operationIds) throws EminException{
		if(orgId==null || operationIds==null || operationIds.length==0){
			throw new EminException(WXSExceptionCode.WOA_PARAMTERS_INVALID);
		}
		this.getEntityDao().deleteBySQL("delete from public.org_operation where org_id ="+orgId);
		for (int i = 0; i < operationIds.length; i++) {
			OrgOperation oot = new OrgOperation();
			oot.setOperationId(operationIds[i]);
			oot.setOrgId(orgId);
			this.save(oot);
		}
	}
	
	@Override
	public List<Operation> loadAllOperations(){
		return this.getEntityDao().findAll(Operation.class,SortKey.asc(Operation.PROP_MENU_ID));
	}
	
	@Override
	public List<Menu> getAuthorizedMenuForPerson(Long personId){
		if(personId==1l){			
			return this.getEntityDao().findByPreFilter(Menu.class,new SortKey[]{SortKey.asc("index")},PreFilters.ge(Menu.PROP_STATUS, 0));
		}
		Long[] orgIds = orgAccepter.getPersonOrgIds(personId);
		if(orgIds.length==0){
			return new ArrayList<Menu>();
		}
		List<BigInteger> list = this.getEntityDao().findBySQL("select menu_id from public.operation where id in (select operation_id from public.org_operation where org_id in ("+CommonsUtil.longArrToString(orgIds)+"))");
		if(list==null || list.size()==0){
			return new ArrayList<Menu>();
		}
		Long[] menuIds = new Long[list.size()];
		for (int i = 0; i < list.size(); i++) {
			menuIds[i] = list.get(i).longValue();
		}
		return this.getEntityDao().findByIds(Menu.class, menuIds);		
	}
	@Override
	public List<Operation> getMenuOperationsForPerson(Long personId,Long menuId){
		if(personId==1l){
			return this.getEntityDao().findByPreFilter(Operation.class,PreFilters.eq(Operation.PROP_MENU_ID, menuId));
		}
		Long[] orgIds = orgAccepter.getPersonOrgIds(personId);
		if(orgIds.length==0){
			return new ArrayList<Operation>();
		}
		List<BigInteger> list = this.getEntityDao().findBySQL("select operation_id from public.org_operation where org_id in ("+CommonsUtil.longArrToString(orgIds)+")");
		if(list==null || list.size()==0){
			return new ArrayList<Operation>();
		}
		Long[] operationIds = new Long[list.size()];
		for (int i = 0; i < list.size(); i++) {
			operationIds[i] = list.get(i).longValue();
		}
		List<Operation> operations = this.getEntityDao().findByPreFilter(Operation.class, PreFilters.in(Operation.PROP_ID, (Object[])operationIds),PreFilters.eq(Operation.PROP_MENU_ID, menuId));
		System.out.println(operations.size());
		return operations;
	}
	@Override
	public List<Operation> getMenuOperationsForPerson(Long personId,String menuCode){
		System.out.println("1111");
		List<Operation> operations = new ArrayList<Operation>();
		if(personId==1l){
			try {
				operations = this.getEntityDao().findByPreFilter(Operation.class,PreFilters.eq("menu.code", menuCode));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println(operations.size());
			return operations;
		}
		
		Long[] orgIds = orgAccepter.getPersonOrgIds(personId);
		if(orgIds.length==0){
			return operations;
		}
		List<BigInteger> list = this.getEntityDao().findBySQL("select operation_id from public.org_operation where org_id in ("+CommonsUtil.longArrToString(orgIds)+")");
		if(list==null || list.size()==0){
			return operations;
		}
		Long[] operationIds = new Long[list.size()];
		for (int i = 0; i < list.size(); i++) {
			operationIds[i] = list.get(i).longValue();
		}
		operations = this.getEntityDao().findByPreFilter(Operation.class, PreFilters.in(Operation.PROP_ID, (Object[])operationIds),PreFilters.eq("menu.code", menuCode));
		System.out.println(operations.size());
		return operations;
	}
	@Override
	public List<Operation> getAllOperationsForOrg(Long orgId){
		
		List<BigInteger> list = this.getEntityDao().findBySQL("select operation_id from public.org_operation where org_id = "+orgId);
		if(list==null || list.size()==0){
			return new ArrayList<Operation>();
		}
		Long[] operationIds = new Long[list.size()];
		for (int i = 0; i < list.size(); i++) {
			operationIds[i] = list.get(i).longValue();
		}
		return this.getEntityDao().findByIds(Operation.class, operationIds);
	}
	
}
