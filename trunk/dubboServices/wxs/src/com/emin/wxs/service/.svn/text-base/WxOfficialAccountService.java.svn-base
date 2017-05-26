/**
 * 
 */
package com.emin.wxs.service;

import java.util.List;

import com.emin.base.service.UndeleteableService;
import com.emin.wxs.domain.WxOfficialAccount;

/**
 * @author jim
 *
 */
public interface WxOfficialAccountService extends UndeleteableService<WxOfficialAccount>{

	/**
	 * 查询公司所有公众号
	 * @param companyId
	 * @return
	 */
	List<WxOfficialAccount> loadWxOfficialAccountsByCompany(Long companyId);

	/**根据微信号查询公众号
	 * @param companyCode
	 * @return
	 */
	WxOfficialAccount loadByCompanyCode(String companyCode);
	
}
