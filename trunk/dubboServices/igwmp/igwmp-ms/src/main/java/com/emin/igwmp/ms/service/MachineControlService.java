package com.emin.igwmp.ms.service;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.igwmp.ms.domain.MachineControl;

import java.util.List;

/**
 * User: shimingliang
 * Date: 2017/3/9
 * Time: 14:03
 * 机器状态控制基本信息接口
 */
public interface MachineControlService extends UndeleteableService<MachineControl> {

    /**
     * 分页查询机器状态控制信息
     * @param pageRequest
     * @param conditions
     * @return
     */
    public PagedResult<MachineControl> queryMachineControlForList(PageRequest pageRequest, List<Condition> conditions);

    /**
     * 添加或更新机器状态控制基本信息表
     *
     * @param machineControl
     */
    public void addOrUpdateMachineControl(MachineControl machineControl);

    /**
     * 根据ID删除机器状态控制基本信息表
     *
     * @param id
     */
    public void removeMachineControl(Long id);

}
