package com.emin.igwmp.rs.facade.accepters.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service; 
import com.emin.igwmp.rs.domain.Region;
import com.emin.igwmp.rs.facade.accepters.RegionAccepter;
import com.emin.igwmp.rs.service.RegionService;

@Component("regionAccepter")
@Service(version="0.0.1")
public class RegionAccepterImpl  implements RegionAccepter{

	@Autowired
	@Qualifier("regionService")
	private RegionService regionService;
	
	@Override
	public void deleteRegion(Long regionId) {

		regionService.deleteRegion(regionId);
	}

	@Override
	public List<Region> loadRegionTree(Long pid, String match) {

		return regionService.loadRegionTree(pid, match);
	}

	@Override
	public void changeParent(Long pid, Long[] regionIds) {
		regionService.changeParent(pid, regionIds);
		
	}

	@Override
	public List<Region> loadAllRegion() { 
		
		return regionService.loadAllRegion();
	}
 
	
}
