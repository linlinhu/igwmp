package com.emin.igwmp.ms.service;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.igwmp.ms.domain.MachineChannel;

import java.util.List;

/**
 * User: shimingliang
 * Date: 2017/3/9
 * Time: 14:03
 * 机器通道通道基本信息接口
 */
public interface MachineChannelService extends UndeleteableService<MachineChannel> {

    /**
     * 分页查询机器通道通道信息
     * @param pageRequest
     * @param conditions
     * @return
     */
    public PagedResult<MachineChannel> queryMachineChannelForList(PageRequest pageRequest, List<Condition> conditions);

    /**
     * 添加或更新机器通道基本信息表
     *
     * @param machineChannel
     */
    public void addOrUpdateMachineChannel(MachineChannel machineChannel);

    /**
     * 根据ID删除机器通道基本信息表
     *
     * @param id
     */
    public void removeMachineChannel(Long id);

}
