package com.emin.platform.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.dao.SortKey;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.base.util.CommonsUtil;
import com.emin.platform.domain.OrgMember;
import com.emin.platform.domain.Organize;
import com.emin.platform.domain.Person;
import com.emin.platform.service.OrganizeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("organizeService")
public class OrganizeServiceImpl extends UndeleteableServiceImpl<Organize> implements OrganizeService {
	
	/**
	 * 增加或更新一个组织
	 * 
	 * @author rocky
	 * @param organize
	 * @return
	 */
	@Override
	public void saveOrUpdateOrganize(Organize organize) {
		this.beforeSaveOrUpdate(organize);
		super.saveOrUpdate(organize);
	}

	/**
	 * 增加或更新之前对一个组织的校验
	 * 
	 * @author rocky
	 * @param organize
	 * @return
	 */
	private void beforeSaveOrUpdate(Organize organize) {
		if (!CommonsUtil.isNotEmpty(organize.getName()) || null == organize.getPid()
			|| null == organize.getCompanyId()) {
			throw new RuntimeException("信息不完整");
		}
		// 判断name重复
		if (findByName(organize.getName(), organize.getId(),organize.getCompanyId()) != null) {
			throw new RuntimeException("部门:"+organize.getName()+" 与存在");
		}
	}

	/**
	 * 按照查询条件查询组织
	 * 
	 * @author rocky
	 * @param name
	 * @param id
	 * @param companyId
	 * @return Organize
	 */
	public Organize findByName(String name, Long id, Long companyId) {
		PreFilter nameFilter = PreFilters.eq(Organize.PROP_NAME, name);
		PreFilter companyIdFilter = PreFilters.eq(Organize.PROP_COMPANY_ID,companyId);
		
		if (null == id) {
			return this.findUniqueByPreFilter(nameFilter, companyIdFilter);
		} else {
			PreFilter notIdFilter = PreFilters.notEq(Organize.PROP_ID, id);
			return this.findUniqueByPreFilter(nameFilter, notIdFilter,companyIdFilter);
		}

	}

	public PagedResult<Organize> getPagedForUserByConditions(
			PageRequest pageRequest, List<Condition> conditions) {
		List<PreFilter> filters = new ArrayList<PreFilter>();
		if (conditions != null && conditions.size() > 0) {
			for (Condition condition : conditions) {
				filters.add(Conditions.convertToPropertyFilter(condition));
			}

		}
		PreFilter[] preFilters = new PreFilter[filters.size()];
		preFilters = filters.toArray(preFilters);
		// this.getEntityDao().
		return this.getPage(pageRequest, preFilters);
	}

	/**
	 * 查询所有组织
	 * 
	 * @author rocky
	 * @param sortKeys
	 * @param @param conditions
	 * @param @return
	 * @return List<Organize>
	 */
	@Override
	public List<Organize> loadByCompanyId(Long companyId) {
		return this.findByPreFilter(PreFilters.eq(Organize.PROP_COMPANY_ID,
				companyId));
	}

	/**
	 * 按照查询条件分页查询组织
	 * 
	 * @author rocky
	 * @param organize
	 * @return PagedResult<Organize>
	 */
	public PagedResult<Organize> loadOrganizeByCondition(
			PageRequest pageRequestData, List<Condition> conditions) {
		List<PreFilter> filters = new ArrayList<PreFilter>(conditions.size());
		if (null != conditions && conditions.size() > 0) {
			for (Condition condition : conditions) {
				filters.add(Conditions.convertToPropertyFilter(condition));
			}
		}
		pageRequestData.setOrderBy(new SortKey[] { SortKey.DEFAULT });
		PreFilter[] preFilters = new PreFilter[filters.size()];
		preFilters = filters.toArray(preFilters);
		return this.getPage(pageRequestData, preFilters);
	}




	@Override
	public JSONArray loadCompanyOrgTree(Long pid, Long companyId) {
		JSONArray nodes = new JSONArray();
		if (pid == null || pid == 0) {
			List<Organize> organizes = this.findByPreFilter(PreFilters.eq(Organize.PROP_COMPANY_ID, companyId),PreFilters.or(PreFilters.eq(Organize.PROP_PID, Organize.ORG_TOP_PID),PreFilters.isNull(Organize.PROP_PID)),PreFilters.eq(Organize.PROP_STATUS, Organize.STATUS_VALID));
			for (Organize organize : organizes) {
				JSONObject jo = new JSONObject();
				jo.put("text", organize.getName());
				jo.put("id", organize.getId());
				jo.put("pid", organize.getPid());
				jo.put("companyid", organize.getCompanyId());
				nodes.add(jo);
			}
		} else {
			List<Organize> organizes = this.findByPreFilter(PreFilters.eq(Organize.PROP_COMPANY_ID, companyId),PreFilters.eq(Organize.PROP_PID, pid),PreFilters.eq(Organize.PROP_STATUS, Organize.STATUS_VALID));
			for (Organize organize : organizes) {
				JSONObject jo = new JSONObject();
				jo.put("text", organize.getName());
				jo.put("id", organize.getId());
				jo.put("pid", organize.getPid());
				jo.put("companyid", organize.getCompanyId());
				nodes.add(jo);
			}
		}
		return nodes;
	}
	@Override
	public PagedResult<Person> loadMembersByOrgId(PageRequest pageRequest,Long orgId,String match){
		if(pageRequest==null || orgId==null){
			throw new RuntimeException("查询条件不完整");
		}
		PreFilter orgFilter = PreFilters.eq(OrgMember.PROP_ORGID, orgId);
		PreFilter matchFilter = null;
		if(StringUtils.isNotBlank(match)){
			PreFilter nameFilter = PreFilters.like(OrgMember.PROP_PERSONNAME, "%"+match+"%");
			PreFilter nzmFilter = PreFilters.like(OrgMember.PROP_PERSONNZM, "%"+match+"%");
			PreFilter mobileFilter = PreFilters.like(OrgMember.PROP_PERSOMMOBILE, "%"+match+"%");
			matchFilter = PreFilters.or(nameFilter,nzmFilter,mobileFilter);
		}
		PagedResult<OrgMember> members = this.getEntityDao().getPage(OrgMember.class, pageRequest, orgFilter,matchFilter,getStatusFilter());
		List<Person> persons = new ArrayList<Person>();
		for(OrgMember m:members.getResultList()){
			persons.add(m.getPerson());
		}
		PagedResult<Person> p = new PagedResult<Person>(persons, members.getNextOffset(), members.getTotalCount());
		return p;
	}
	
	

	@Override
	public void deleteOrganize(Long orgznizeId) {
		//删除部门前先查看部门是否存在有效岗位
		int count = this.getEntityDao().getCountByPreFilter(OrgMember.class, PreFilters.eq(OrgMember.PROP_ORGID, orgznizeId),getStatusFilter());
		if(count>0){
			throw new RuntimeException("请先将部门中的人员调岗后再移除");
		}
		this.deleteById(orgznizeId);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void addPersonToOrg(Long orgId,Long[] personIds){
		//先查询此部门是否存在空岗
		List<OrgMember> members = this.getEntityDao().findByPreFilter(OrgMember.class, PreFilters.eq(OrgMember.PROP_ORGID, orgId),PreFilters.eq(OrgMember.PROP_STATUS, 1),PreFilters.isNull(OrgMember.PROP_PERSONID));
		//如果有空岗则先填空岗
		if(members.size()>0){
			for(Long personId:personIds){
				if(members.size()>0){
					OrgMember m = members.get(0);
					m.setPersonId(personId);
					this.getEntityDao().update(members);
					//将此空岗从集合中移除
					members.remove(m);
				}else{
					//若果空岗已经填充完，还有用户的话 就新建岗位
					OrgMember orgMember = new OrgMember();
					orgMember.setOrgId(orgId);
					orgMember.setPersonId(personId);
					orgMember.setStatus(1);
					this.saveOrgMember(orgMember);
				}
			}
		}else{
			//没有空岗则新建
			for(Long personId:personIds){
				OrgMember orgMember = new OrgMember();
				orgMember.setOrgId(orgId);
				orgMember.setPersonId(personId);
				orgMember.setStatus(1);
				this.saveOrgMember(orgMember);
			}
		}
	}
	@Override
	public void removePersonFromOrg(Long orgId,Long[] personIds){
		if(orgId==null || personIds==null || personIds.length==0){
			return;
		}
		this.getEntityDao().updateByHQL("update OrgMember set status = -1,personId = null where orgId= ? and  personId in("+CommonsUtil.longArrToString(personIds)+")",orgId);
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void changePersonOrg(Long oldOrgId,Long newOrgId,Long[] personIds){
		if(oldOrgId==null || newOrgId==null || personIds==null || personIds.length==0){
			return;
		}
		//现将所选员工以前部门的岗位空出来
		this.getEntityDao().updateByHQL("update OrgMember set status = -1,personId = null where orgId= ? and  personId in("+CommonsUtil.longArrToString(personIds)+")",oldOrgId);
		//再将这些员工加入到新部门
		this.addPersonToOrg(newOrgId, personIds);
	}
	private void saveOrgMember(OrgMember orgMember){
		if(orgMember.getOrgId()==null || orgMember.getPersonId()==null){
			throw new RuntimeException("信息不完整");
		}
		this.getEntityDao().save(orgMember);
	}

	@Override
	public PagedResult<Person> loadUnallocatedPersons(PageRequest pageRequest){
		List<BigInteger> list = this.getEntityDao().findBySQL("select person_id from platform.orgmember where status = 1");
		PreFilter idsFilter = null;
		if(list!=null && list.size()>0){
			Long[] ids = new Long[list.size()];
			for (int i = 0; i < list.size(); i++) {
				ids[i] = list.get(i).longValue();
			}
			idsFilter = PreFilters.notIn(Person.PROP_ID, (Object[])ids);
		}
		return this.getEntityDao().getPage(Person.class, pageRequest, idsFilter,getStatusFilter());
	}
	@Override
	public Long[] getPersonOrgIds(Long personId){
		List<BigInteger> list = this.getEntityDao().findBySQL("select org_id from platform.orgmember where status = 1 and person_id = ? ",personId);
		if(list!=null && list.size()>0){
			Long[] ids = new Long[list.size()];
			for (int i = 0; i < list.size(); i++) {
				ids[i] = list.get(i).longValue();
			}
			return ids;
		}
		return new Long[0];
	}
}
