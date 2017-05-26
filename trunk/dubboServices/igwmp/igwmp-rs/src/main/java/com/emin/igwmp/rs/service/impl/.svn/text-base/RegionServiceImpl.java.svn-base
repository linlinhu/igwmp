package com.emin.igwmp.rs.service.impl;

import java.util.List;
 
import org.springframework.stereotype.Service;

import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.exception.BaseExCode;
import com.emin.base.exception.EminException;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.base.util.CommonsUtil;
import com.emin.igwmp.rs.domain.Region;
import com.emin.igwmp.rs.service.RegionService;

@Service("regionService")
public class RegionServiceImpl extends UndeleteableServiceImpl<Region> implements RegionService{


	
	@Override
	public void saveOrUpdate(Region region){
		
		this.beforeSaveOrUpdate(region);
		super.saveOrUpdate(region);
		
	}
	private void beforeSaveOrUpdate(Region region){
		if(region.getName()==null){
			throw new EminException(BaseExCode.BIZ_ILLEGAL_PARAMETER);
		}
		if(region.getPid()==null){
			region.setPid(0l);
		}
		
		
		if (region.getId() == null) {
			//如果是新建 名字是否已经存在
			PreFilter nameFilter = PreFilters.eq(Region.PROP_NAME, region.getName());	
			PreFilter statusFilter = PreFilters.eq(Region.PROP_STATUS, Region.STATUS_VALID);
			int count = this.getCountByPreFilter(nameFilter,statusFilter);
			if (count > 0) {
				throw new EminException(BaseExCode.BIZ_NAME_EXISTS, region.getName());
			}
		}else{
			PreFilter nameFilter = PreFilters.eq(Region.PROP_NAME, region.getName());	
			PreFilter statusFilter = PreFilters.eq(Region.PROP_STATUS, Region.STATUS_VALID);
			PreFilter idFilter = PreFilters.notEq(Region.PROP_ID,region.getId());
			int count = this.getCountByPreFilter(nameFilter,statusFilter,idFilter);
			if (count > 0) {
				throw new EminException(BaseExCode.BIZ_NAME_EXISTS, region.getName());
			}
		}
		
	}
	@Override
	public void deleteRegion(Long regionId){
		Region region = this.findById(regionId);
		
		int count = this.getCountByPreFilter(PreFilters.eq(Region.PROP_PID, region.getId()),PreFilters.eq(Region.PROP_STATUS, Region.STATUS_VALID));
		if(count>0){
				throw new RuntimeException("该区域下包含子区域");
		}
		
		this.deleteById(regionId);
	}
	@Override
	public List<Region> loadRegionTree(Long pid,String match){
		PreFilter matchFilter = null;
		PreFilter pidFilter = PreFilters.eq(Region.PROP_PID, pid);
		if(match!=null && !match.equals("")){
			matchFilter = PreFilters.or(PreFilters.like(Region.PROP_NAME,"%"+match+"%"),PreFilters.like(Region.PROP_NZM,"%"+match+"%"));
			pidFilter = null;
		}
		return this.findByPreFilter(PreFilters.eq(Region.PROP_STATUS, Region.STATUS_VALID),pidFilter,matchFilter);
		
	}
	@Override
	public void changeParent(Long pid,Long[] regionIds){
		this.getEntityDao().updateByHQL("update Region set pid = ?,lastModifyTime=? where id in ("+CommonsUtil.longArrToString(regionIds)+")", pid,System.currentTimeMillis());
	}
	
		
	@Override
	public List<Region> loadAllRegion() {
		return this.findByPreFilter(PreFilters.eq(Region.PROP_STATUS, Region.STATUS_VALID));
	}
	
	
	
	
	
}
