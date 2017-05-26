package com.emin.igwmp.rs.facade.accepters;

import java.util.List;
 
import com.emin.igwmp.rs.domain.Region; 

public interface RegionAccepter {

	void deleteRegion(Long regionId);

	List<Region> loadRegionTree(Long pid,String match);

	void changeParent(Long pid, Long[] regionIds);

	
	List<Region> loadAllRegion();

	

}
