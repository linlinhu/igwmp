package com.emin.igwmp.rstm.facade.accepters;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.rstm.domain.RestaurantAdminInfo;
import com.emin.igwmp.rstm.domain.RestaurantPrivateInfo;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;


/**
 * 调用合同相关信息
 * @author zhaoqt
 *
 */
public interface ContractAccepter {
	
	/**
     * 根据条件分页查询合同列表
     * @param pageRequest
     * @param conditions
     * @return
     */
	public PagedResult<RestaurantPrivateInfo> queryContractByCondition(PageRequest pageRequest , List<Condition> conditions);

	/**
	 * 保存合同信息
	 * @param id
	 */
	void saveContract(RestaurantPrivateInfo id);
	
	 /**
     * 根据ID删除合同信息表
     *
     * @param id
     */
    void removeContract(Long id);

    RestaurantPrivateInfo queryContractById(Long id);
    
    String getRgstNumBySignedFlag(boolean isSigned);
}
