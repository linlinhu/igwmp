package com.emin.wxs.vo;

import com.emin.platform.domain.Organize;

public class OrganizeVO {
	private Long id;

	private Long pid = 0l;//父级组织ID 默认为0
	
	private String name; 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static OrganizeVO organizeToVO(Organize org){
		
		if(org!=null){
			OrganizeVO vo = new OrganizeVO();
			vo.setId(org.getId());
			vo.setPid(org.getPid());
			vo.setName(org.getName());
			return vo;
		}
		return null;
		
	}
	public  Organize convertToOrganize(Organize org){
		Organize o = org;
		if(o==null){
			o = new Organize();
		}

		o.setCompanyId(1l);
		o.setId(this.getId());
		o.setPid(this.getPid());
		o.setName(this.getName());
		return o;
	}
}
