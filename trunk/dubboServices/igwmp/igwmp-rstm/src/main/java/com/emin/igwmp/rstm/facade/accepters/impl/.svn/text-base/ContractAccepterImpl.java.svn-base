package com.emin.igwmp.rstm.facade.accepters.impl;
 
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult; 
import com.emin.base.service.Condition; 
import com.emin.igwmp.rstm.domain.RestaurantPrivateInfo;
import com.emin.igwmp.rstm.facade.accepters.ContractAccepter;
import com.emin.igwmp.rstm.service.RestaurantPrivateInfoService;
import com.emin.igwmp.rstm.util.CommonUtil;

@Service(version="0.0.1")
@Component("contractAccepter")
public class ContractAccepterImpl implements ContractAccepter{
	
	@Resource
	RestaurantPrivateInfoService restaurantPrivateInfoService;
	
	/**
     * 根据条件分页查询合同列表
     * @param pageRequest
     * @param conditions
     * @return
     */
	public PagedResult<RestaurantPrivateInfo> queryContractByCondition(PageRequest pageRequest , List<Condition> conditions) {
		return restaurantPrivateInfoService.loadRestaurantPrivateInfoByCondition(pageRequest, conditions);
	}

	/**.
	 * 保存合同信息
	 * @param id
	 */
	public void saveContract(RestaurantPrivateInfo restaurantPrivateInfo) {
		String rgstNum = restaurantPrivateInfo.getBusinessRgstNum();
		if(rgstNum == null){
			
		}
		restaurantPrivateInfoService.saveOrUpdate(restaurantPrivateInfo);
	}
	
	 /**
     * 根据ID删除合同信息表
     *
     * @param id
     */
    public void removeContract(Long id) {
    	restaurantPrivateInfoService.deleteById(id);
	}

	@Override
	public RestaurantPrivateInfo queryContractById(Long id) {		
		return restaurantPrivateInfoService.findById(id);
	}

	@Override
	public String getRgstNumBySignedFlag(boolean isSigned) {
		PageRequest pageRequest = new PageRequest();
		pageRequest.setOffset(0);
		pageRequest.setLimit(5);
//		SortKey sortkey = SortKey.desc("createTime");
//		pageRequest.setOrderBy(new SortKey[]{sortkey});
		PagedResult<RestaurantPrivateInfo> privateInfos = restaurantPrivateInfoService.getPage(pageRequest);
		String contractNum = "";
		if(privateInfos.getResultList()!=null&&privateInfos.getResultList().size()>0){
			RestaurantPrivateInfo privateInfo = privateInfos.getResultList().get(0);
			contractNum = privateInfo.getContractNum();	
		}
		return CommonUtil.gernerateRgstNumByPreNum(isSigned, contractNum);
	}

}
