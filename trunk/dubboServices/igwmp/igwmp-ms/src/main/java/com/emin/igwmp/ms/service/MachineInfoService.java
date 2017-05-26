package com.emin.igwmp.ms.service;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.igwmp.ms.domain.MachineInfo;

import java.util.List;

/**
 * User: shimingliang
 * Date: 2017/3/9
 * Time: 14:03
 * 机器基本信息接口
 */
public interface MachineInfoService extends UndeleteableService<MachineInfo> {

    /**
     * 分页查询机器信息
     * @param pageRequest
     * @param conditions
     * @return
     */
    public PagedResult<MachineInfo> queryMachineInfoForList(PageRequest pageRequest, List<Condition> conditions);
    /**
     * 查询机器信息
     * @param conditions
     * @return
     */
    public List<MachineInfo> queryMachineInfoForList(List<Condition> conditions);

    /**
     * 添加或更新机器基本信息表
     *
     * @param machineInfo
     */
    public long addOrUpdateMachineInfo(MachineInfo machineInfo);

    /**
     * 根据ID删除机器基本信息表
     *
     * @param id
     */
    public void removeMachineInfo(Long id);


    /**
     * 机器与餐厅 绑定与解绑
     * @param machineInfo  机器
     */
    public void cancelOrBind(MachineInfo machineInfo);

}
