package com.emin.igwmp.ms.service.impl;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.igwmp.ms.domain.MachineChannel;
import com.emin.igwmp.ms.exception.ExceptionCode;
import com.emin.igwmp.ms.service.MachineChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * User: shimingliang
 * Date: 2017/3/10
 * Time: 11:03
 */
@Service("machineChannelService")
@Transactional
public class MachineChannelServiceImpl extends UndeleteableServiceImpl<MachineChannel> implements MachineChannelService {

    private Logger logger = LoggerFactory.getLogger(this.clazz);

    /**
     * 分页查询机器通道基本表信息
     *
     * @param pageRequest
     * @param conditions  查询条件
     * @return
     */
    public PagedResult<MachineChannel> queryMachineChannelForList(PageRequest pageRequest, List<Condition> conditions) {
        logger.info("分页查询机器通道基本信息");
        if (pageRequest == null) {
            logger.info("分页查询机器通道基本表信息参数异常 pageRequest===" + pageRequest);
            throw new EminException(ExceptionCode.T_PARAMTERS_INVALID);
        }
        List<PreFilter> filterList = new ArrayList<PreFilter>();
        filterList.add(getStatusFilter());
        if (conditions != null) {
            for (Condition condition : conditions) {

                PreFilter filter = Conditions.convertToPropertyFilter(condition);
                filterList.add(filter);
            }
        }
        PreFilter[] filters = new PreFilter[filterList.size()];
        filters = filterList.toArray(filters);
        return this.getPage(pageRequest, filters);
    }


    /**
     * 添加或更新机器通道基本表信息
     *
     * @param machineChannel
     */
    public void addOrUpdateMachineChannel(MachineChannel machineChannel) {
        logger.info("添加或更新机器通道基本表信息");
        if (machineChannel == null) {
            logger.info("添加或更新机器通道基本表信息参数异常 VO===" + machineChannel);
            throw new EminException(ExceptionCode.T_PARAMTERS_INVALID);
        }
        super.saveOrUpdate(machineChannel);
    }

    /**
     * 根据ID删除机器通道基本表信息
     *
     * @param id
     */
    public void removeMachineChannel(Long id) {
        logger.info("根据ID删除机器通道基本表信息===" + id);
        if (id == null) {
            logger.info("根据ID删除机器通道基本表信息参数异常 id===" + id);
            throw new EminException(ExceptionCode.T_PARAMTERS_INVALID);
        }

        this.deleteById(id);
    }
}