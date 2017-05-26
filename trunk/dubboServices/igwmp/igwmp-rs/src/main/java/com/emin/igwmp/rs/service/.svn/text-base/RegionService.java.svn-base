package com.emin.igwmp.rs.service;

import java.util.List;

import com.emin.base.service.UndeleteableService;
import com.emin.igwmp.rs.domain.Region; 

public interface RegionService extends UndeleteableService<Region>{

	void deleteRegion(Long regionId);

	List<Region> loadRegionTree(Long pid,String match);

	void changeParent(Long pid, Long[] regionIds);

	
	List<Region> loadAllRegion();

	

}
