package com.emin.igwmp.ms.service.impl;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.igwmp.ms.domain.MachineInfo;
import com.emin.igwmp.ms.exception.ExceptionCode; 
import com.emin.igwmp.ms.service.MachineInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: shimingliang
 * Date: 2017/3/10
 * Time: 11:03
 */
@Service("machineInfoService")
public class MachineInfoServiceImpl extends UndeleteableServiceImpl<MachineInfo> implements MachineInfoService {

    private Logger logger = LoggerFactory.getLogger(this.clazz);

    
    /**
     * 分页查询机器基本表信息
     *
     * @param pageRequest
     * @param conditions  查询条件
     * @return
     */
    public PagedResult<MachineInfo> queryMachineInfoForList(PageRequest pageRequest, List<Condition> conditions) {
        logger.info("分页查询机器基本信息");
        if (pageRequest == null) {
            logger.info("分页查询机器基本表信息参数异常 pageRequest===" + pageRequest);
            throw new EminException(ExceptionCode.T_PARAMTERS_INVALID);
        }
        List<PreFilter> filterList = new ArrayList<PreFilter>();
        filterList.add(getStatusFilter());
        if (conditions != null) {
            conditions.add(new Condition("status", Condition.ConditionOperator.EQ, Condition.ConditionType.CHARACTER, 1)); //逻辑状态位
            conditions.add(new Condition("channelList.status", Condition.ConditionOperator.EQ, Condition.ConditionType.CHARACTER, 1)); //逻辑状态位
            for (Condition condition : conditions) {
                PreFilter preFilterRes = null;
                PreFilter preFilterIpc = null;
                PreFilter preFilterCode = null;
                PreFilter preFilterOr = null;
                if ("keyword".equals(condition.getPropertyName())) {
                    preFilterIpc = PreFilters.like("ipcCode", "%" + condition.getValues()[0].toString() + "%");
                    preFilterRes = PreFilters.like("restaurantName", "%" + condition.getValues()[0].toString() + "%");
                    preFilterCode = PreFilters.like("code", "%" + condition.getValues()[0].toString() + "%");
                    preFilterOr = PreFilters.or(preFilterCode, preFilterIpc, preFilterRes);
                    filterList.add(preFilterOr);
                } else {
                    PreFilter filter = Conditions.convertToPropertyFilter(condition);
                    filterList.add(filter);
                }
            }
        }
        PreFilter[] filters = new PreFilter[filterList.size()];
        filters = filterList.toArray(filters);
        return this.getPage(pageRequest, filters);
    }

    /**
     * 查询机器基本表信息
     * @param conditions  查询条件
     * @return
     */
    public List<MachineInfo> queryMachineInfoForList(List<Condition> conditions) {
        logger.info("查询机器基本信息");
        List<PreFilter> filterList = new ArrayList<PreFilter>();
        filterList.add(getStatusFilter());
        if (conditions != null) {
            conditions.add(new Condition("status", Condition.ConditionOperator.EQ, Condition.ConditionType.CHARACTER, 1)); //逻辑状态位
            conditions.add(new Condition("channelList.status", Condition.ConditionOperator.EQ, Condition.ConditionType.CHARACTER, 1)); //逻辑状态位
            for (Condition condition : conditions) {
                PreFilter preFilterRes = null;
                PreFilter preFilterIpc = null;
                PreFilter preFilterCode = null;
                PreFilter preFilterOr = null;
                if ("keyword".equals(condition.getPropertyName())) {
                    preFilterIpc = PreFilters.like("ipcCode", "%" + condition.getValues()[0].toString() + "%");
                    preFilterRes = PreFilters.like("restaurantName", "%" + condition.getValues()[0].toString() + "%");
                    preFilterCode = PreFilters.like("code", "%" + condition.getValues()[0].toString() + "%");
                    preFilterOr = PreFilters.or(preFilterCode, preFilterIpc, preFilterRes);
                    filterList.add(preFilterOr);
                } else {
                    PreFilter filter = Conditions.convertToPropertyFilter(condition);
                    filterList.add(filter);
                }
            }
        }
        PreFilter[] filters = new PreFilter[filterList.size()];
        filters = filterList.toArray(filters);
        return this.findByPreFilter(filters);
    }

    /**
     * 添加或更新机器基本表信息
     *
     * @param machineInfo
     */
    public long addOrUpdateMachineInfo(MachineInfo machineInfo) {
        logger.info("添加或更新机器基本表信息");
        this.saveOrUpdate(machineInfo);
        return machineInfo.getId();
    }

    /**
     * 根据ID删除机器基本表信息
     *
     * @param id
     */
    public void removeMachineInfo(Long id) {
        logger.info("根据ID删除机器基本表信息===" + id);
        if (id == null) {
            logger.info("根据ID删除机器基本表信息参数异常 id===" + id);
            throw new EminException(ExceptionCode.T_PARAMTERS_INVALID);
        }

        this.deleteById(id);
    }

    /**
     * 机器与餐厅 绑定与解绑
     *
     * @param machineInfo
     */
    public void cancelOrBind(MachineInfo machineInfo) {
        logger.info("机器与餐厅 绑定与解绑===" + machineInfo.toString());
        this.saveOrUpdate(machineInfo);
    }
}
