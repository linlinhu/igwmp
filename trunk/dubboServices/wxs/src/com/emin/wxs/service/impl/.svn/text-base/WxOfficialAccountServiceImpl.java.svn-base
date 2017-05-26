/**
 * 
 */
package com.emin.wxs.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.exception.EminException;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.wxs.domain.WxOfficialAccount;
import com.emin.wxs.exception.WXSExceptionCode;
import com.emin.wxs.service.WxOfficialAccountService;

/**
 * @author jim
 *
 */
@Service("wxOfficialAccountService")
public class WxOfficialAccountServiceImpl extends UndeleteableServiceImpl<WxOfficialAccount> implements WxOfficialAccountService{

	@Override
	public void saveOrUpdate(WxOfficialAccount wxOfficialAccount) throws EminException{
		this.beforeSaveOrUpdate(wxOfficialAccount);
		super.saveOrUpdate(wxOfficialAccount);
	}
	private void beforeSaveOrUpdate(WxOfficialAccount wxOfficialAccount){
		if(StringUtils.isBlank(wxOfficialAccount.getAppId())
				||StringUtils.isBlank(wxOfficialAccount.getAppSecret())
				||StringUtils.isBlank(wxOfficialAccount.getCompanyCode())
				||StringUtils.isBlank(wxOfficialAccount.getCompanyName())
				||StringUtils.isBlank(wxOfficialAccount.getToken())
				||wxOfficialAccount.getType()==0){
			throw new EminException(WXSExceptionCode.WOA_PARAMTERS_INVALID);
		}
	}
	@Override
	public List<WxOfficialAccount> loadWxOfficialAccountsByCompany(Long companyId){
		PreFilter companyFilter = PreFilters.eq(WxOfficialAccount.PROP_COMPANY_ID, companyId);
		return this.findByPreFilter(companyFilter,getStatusFilter());
	}
	@Override
	public WxOfficialAccount loadByCompanyCode(String companyCode){
		PreFilter codeFilter = PreFilters.eq(WxOfficialAccount.PROP_COMPANY_CODE, companyCode);
		return this.findUniqueByPreFilter(codeFilter);
	}
	
}
