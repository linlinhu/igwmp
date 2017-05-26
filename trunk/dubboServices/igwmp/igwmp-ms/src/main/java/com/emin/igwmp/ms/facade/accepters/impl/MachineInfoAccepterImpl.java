package com.emin.igwmp.ms.facade.accepters.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.igwmp.ms.domain.MachineChannel;
import com.emin.igwmp.ms.domain.MachineControl;
import com.emin.igwmp.ms.domain.MachineInfo;
import com.emin.igwmp.ms.domain.MachineInfoVo;
import com.emin.igwmp.ms.exception.ExceptionCode;
import com.emin.igwmp.ms.facade.accepters.MachineInfoAccepter;
import com.emin.igwmp.ms.service.MachineChannelService;
import com.emin.igwmp.ms.service.MachineControlService;
import com.emin.igwmp.ms.service.MachineInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: shimingliang
 * Date: 2017/3/10
 * Time: 11:03
 */
@Component
@Service
public class MachineInfoAccepterImpl implements MachineInfoAccepter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MachineInfoService machineInfoService;
    @Autowired
    private MachineChannelService machineChannelService;
    @Autowired
    private MachineControlService machineControlService;

    /**
     * 分页查询机器基本表信息
     *
     * @param pageRequest
     * @param conditions  查询条件
     * @return
     */
    public PagedResult<MachineInfo> queryMachineInfoForList(PageRequest pageRequest, List<Condition> conditions) throws EminException {
        logger.info("分页查询机器基本信息-ACCEPTER");
        if (pageRequest == null) {
            logger.info("分页查询机器基本表信息参数异常-ACCEPTER pageRequest===" + pageRequest);
            throw new EminException(ExceptionCode.T_PARAMTERS_INVALID);
        }

        return machineInfoService.queryMachineInfoForList(pageRequest, conditions);
    }

    /**
     * 查询机器基本表信息
     * @param conditions 查询条件
     * @return
     */
    public List<MachineInfo> queryMachineInfoForList(List<Condition> conditions) throws EminException {
        logger.info("分页查询机器基本信息-ACCEPTER");
        return machineInfoService.queryMachineInfoForList(conditions);
    }


    /**
     * 添加或更新机器基本表信息
     *
     * @param infoVo
     */
    public void addOrUpdateMachineInfo(MachineInfoVo infoVo) throws EminException {
        logger.info("添加或更新机器基本表信息-ACCEPTER");
        if (infoVo == null || infoVo.getId() == null || infoVo.getChannelList() == null) {
            logger.info("添加或更新机器基本表信息参数异常-ACCEPTER infoVo===" + infoVo);
            throw new EminException(ExceptionCode.T_PARAMTERS_INVALID);
        }
        //设备信息
        MachineInfo machineInfo = machineInfoService.findById(infoVo.getId());
        if (machineInfo == null || machineInfo.getMachineControl() == null) {
            logger.info("添加或更新机器基本表信息,根据Id未查询到机器信息或机器版本信息 -ACCEPTER machineInfo===" + machineInfo);
            throw new EminException(ExceptionCode.T_SERVICE_EXCEPTION);
        }
        machineInfo.setCode(infoVo.getCode());
        machineInfo.setIpcCode(infoVo.getIpcCode());
        machineInfo.setSimCode(infoVo.getSimCode());
        machineInfo.setChannelSum(infoVo.getChannelSum());


        //查询关联通道
        List<MachineChannel> channelList = getChannelList(infoVo.getId());
        if (channelList == null) {
            logger.info("添加或更新机器基本表信息,根据机器Id未查询到关联通道信息 -ACCEPTER machineInfo===" + machineInfo);
            throw new EminException(ExceptionCode.T_SERVICE_EXCEPTION);
        }

        if (infoVo.getChannelList().size() > 0) {
            for (int j = 0; j < channelList.size(); j++) {
                channelList.get(j).setStatus(-1);
                channelList.get(j).setMachineInfoId(null);
                for (int k = 0; k < infoVo.getChannelList().size(); k++) {
                    if (channelList.get(j).getId() == infoVo.getChannelList().get(k).getId()) {
                        channelList.get(j).setAlarmValue(infoVo.getAlarmValue());
                        channelList.get(j).setWarningValue(infoVo.getWarningValue());
                        channelList.get(j).setLiquorInfoId(infoVo.getChannelList().get(k).getLiquorInfoId());
                        channelList.get(j).setLiquorName(infoVo.getChannelList().get(k).getLiquorName());
                        channelList.get(j).setAllowance(infoVo.getChannelList().get(k).getAllowance());
                        channelList.get(j).setSort(infoVo.getChannelList().get(k).getSort());
                        channelList.get(j).setStatus(1);
                        channelList.get(j).setMachineInfoId(infoVo.getId());
                    }
                }
            }

            for (int k = 0; k < infoVo.getChannelList().size(); k++) {
                if (infoVo.getChannelList().get(k).getId() == null) {

                    MachineChannel machineChannel = new MachineChannel();
                    machineChannel.setSort(infoVo.getChannelList().get(k).getSort());
                    machineChannel.setAlarmValue(infoVo.getAlarmValue());
                    machineChannel.setWarningValue(infoVo.getWarningValue());
                    machineChannel.setLiquorInfoId(infoVo.getChannelList().get(k).getLiquorInfoId());
                    machineChannel.setLiquorName(infoVo.getChannelList().get(k).getLiquorName());
                    machineChannel.setAllowance(infoVo.getChannelList().get(k).getAllowance());
                    machineChannel.setStatus(1);
                    machineChannel.setChannelNo(infoVo.getChannelList().get(k).getChannelNo());
                    machineChannel.setCreateTime(new Date().getTime());
                    machineChannel.setLastModifyTime(new Date().getTime());
                    machineChannel.setMachineInfoId(infoVo.getId());

                    channelList.add(machineChannel);
                }
            }
        }

        machineInfo.setChannelList(channelList);

        if (infoVo.getRestaurantId() != null && !StringUtils.isBlank(infoVo.getRestaurantName())) {

            //设备状态控制表信息
            MachineControl machineControl = machineControlService.findById(machineInfo.getMachineControl().getId());
            if (machineControl == null) {
                logger.info("添加或更新机器基本表信息,根据Id未查询到机器版本信息 -ACCEPTER machineControl===" + machineControl);
                throw new EminException(ExceptionCode.T_SERVICE_EXCEPTION);
            }

            machineInfo.setRestaurantId(infoVo.getRestaurantId());
            machineInfo.setRestaurantName(infoVo.getRestaurantName());
            machineControl.setBindStatus(1); //绑定
            machineControl.setBindTime(new Date().getTime());
            machineInfo.setMachineControl(machineControl);
        }

        machineInfoService.addOrUpdateMachineInfo(machineInfo);
    }

    /**
     * 装机，拆机，移机
     *
     * @param machine
     */
    public long updateMachineInfo(MachineInfo machine) {
        logger.info("装机，拆机，移机-ACCEPTER");
        if (machine == null) {
            logger.info("装机，拆机，移机-ACCEPTER infoVo===" + machine);
            throw new EminException(ExceptionCode.T_PARAMTERS_INVALID);
        }
        //设备信息
        MachineInfo machineInfo = new MachineInfo();
        if (machine.getId() != null) {  //更新
            machineInfo = machineInfoService.findById(machine.getId());
            if (machineInfo == null || machineInfo.getMachineControl() == null) {
                logger.info("装机，拆机，移机,根据Id未查询到机器信息或机器版本信息 -ACCEPTER machineInfo===" + machineInfo);
                throw new EminException(ExceptionCode.T_SERVICE_EXCEPTION);
            }

            if (!StringUtils.isBlank(machine.getCode())) {
                machineInfo.setCode(machine.getCode());
            }
            if (!StringUtils.isBlank(machine.getIpcCode())) {
                machineInfo.setIpcCode(machine.getIpcCode());
            }
            if (!StringUtils.isBlank(machine.getSimCode())) {
                machineInfo.setSimCode(machine.getSimCode());
            }

            machineInfo.setChannelSum(machine.getChannelSum());
            if (machine.getRestaurantId() != null && !StringUtils.isBlank(machine.getRestaurantName())) {
                machineInfo.setRestaurantId(machine.getRestaurantId());
                machineInfo.setRestaurantName(machine.getRestaurantName());
            }
            //查询关联通道
            List<MachineChannel> channelList = getChannelList(machine.getId());
            //表示需要更新通道
            if (machine.getChannelList() != null && machine.getChannelList().size() > 0) {
                //删除原来的
                for (int j = 0; j < channelList.size(); j++) {
                    machineChannelService.deleteById(channelList.get(j).getId());
                }
                //设置新的
                for (int i = 0; i < machine.getChannelList().size(); i++) {
                    machine.getChannelList().get(i).setMachineInfoId(machineInfo.getId());
                    machine.getChannelList().get(i).setStatus(1);
                    machine.getChannelList().get(i).setCreateTime(new Date().getTime());
                    machine.getChannelList().get(i).setLastModifyTime(new Date().getTime());
                }
                machineInfo.setChannelList(machine.getChannelList());
            } else {
                if (channelList != null) {
                    machineInfo.setChannelList(channelList);
                }
            }
            machineInfoService.addOrUpdateMachineInfo(machineInfo);
            return machineInfo.getId();
        } else { //新增

            //版本
            MachineControl machineControl = new MachineControl();
            machineControl.setCreateTime(new Date().getTime());
            machineControl.setLastModifyTime(new Date().getTime());
            if (machine.getMachineControl() != null) {
                machineControl.setRunType(machine.getMachineControl().getRunType());
            }
            machineControl.setStatus(1);
            if (machine.getRestaurantId() != null && !StringUtils.isBlank(machine.getRestaurantName())) {
                machineControl.setBindTime(new Date().getTime());
                machineControl.setBindStatus(1);
            }

            machine.setMachineControl(machineControl);

            //通道
            if (machine.getChannelList() != null && machine.getChannelList().size() > 0) {
                for (int i = 0; i < machine.getChannelList().size(); i++) {
                    machine.getChannelList().get(i).setMachineInfoId(machine.getId());
                    machine.getChannelList().get(i).setStatus(1);
                    machine.getChannelList().get(i).setCreateTime(new Date().getTime());
                    machine.getChannelList().get(i).setLastModifyTime(new Date().getTime());
                }
            }

            machine.setStatus(1);
            machine.setCreateTime(new Date().getTime());
            machine.setLastModifyTime(new Date().getTime());

            return machineInfoService.addOrUpdateMachineInfo(machine);
        }
    }

    /**
     * 根据ID删除机器基本表信息
     *
     * @param id
     */
    public void removeMachineInfo(Long id) throws EminException {
        logger.info("根据ID删除机器基本表信息-ACCEPTER ===" + id);
        if (id == null) {
            logger.info("根据ID删除机器基本表信息参数异常-ACCEPTER id===" + id);
            throw new EminException(ExceptionCode.T_PARAMTERS_INVALID);
        }
        machineInfoService.disable(id);  //逻辑删除
    }

    /**
     * 机器与餐厅 解绑
     *
     * @param ids 机器id
     */
    public void cancelBind(Long[] ids) {
        logger.info("根据ID与餐厅 解绑-ACCEPTER ===" + ids);
        if (ids == null || ids.length == 0) {
            logger.info("根据ID与餐厅 解绑参数异常-ACCEPTER id===" + ids);
            throw new EminException(ExceptionCode.T_PARAMTERS_INVALID);
        }

        for (int i = 0; i < ids.length; i++) {
            MachineInfo machineInfo = machineInfoService.findById(ids[0]);
            if (machineInfo == null) {
                continue;
            }
            machineInfo.setRestaurantId(-1L);
            machineInfo.setRestaurantName("");
            machineInfo.setChannelList(getChannelList(machineInfo.getId()));
            //设备状态控制表信息
            if (machineInfo.getMachineControl() == null) {
                logger.info("机器与餐厅 绑定-ACCEPTER,根据Id未查询到机器版本信息 -ACCEPTER machineControl===" + machineInfo.getMachineControl());
                throw new EminException(ExceptionCode.T_SERVICE_EXCEPTION);
            }
            MachineControl machineControl = machineControlService.findById(machineInfo.getMachineControl().getId());
            if (machineControl == null) {
                logger.info("添加或更新机器基本表信息,根据Id未查询到机器版本信息 -ACCEPTER machineControl===" + machineControl);
                throw new EminException(ExceptionCode.T_SERVICE_EXCEPTION);
            }
            machineControl.setBindStatus(0); //取消绑定
            machineControlService.saveOrUpdate(machineControl);
            machineInfoService.cancelOrBind(machineInfo);
        }
    }


    /**
     * 机器与餐厅 绑定
     *
     * @param machineInfo 机器
     */
    public void bindMachine(MachineInfo machineInfo) {
        if (machineInfo == null || machineInfo.getId() == null || machineInfo.getRestaurantId() == null ||
                StringUtils.isBlank(machineInfo.getRestaurantName())) {
            logger.info("机器与餐厅 绑定参数异常-ACCEPTER machineInfo===" + machineInfo);
            throw new EminException(ExceptionCode.T_PARAMTERS_INVALID);
        }
        logger.info("机器与餐厅 绑定-ACCEPTER ===" + machineInfo);

        MachineInfo machine = machineInfoService.findById(machineInfo.getId());
        if (machine.getRestaurantId() == machineInfo.getRestaurantId() && machineInfo.getRestaurantName().equals(machine.getRestaurantName()))
            return;
        machine.setRestaurantId(machineInfo.getRestaurantId());
        machine.setRestaurantName(machineInfo.getRestaurantName());
        machine.setChannelList(getChannelList(machineInfo.getId()));
        //设备状态控制表信息
        if (machine.getMachineControl() == null) {
            logger.info("机器与餐厅 绑定-ACCEPTER,根据Id未查询到机器版本信息 -ACCEPTER machineControl===" + machine.getMachineControl());
            throw new EminException(ExceptionCode.T_SERVICE_EXCEPTION);
        }
        MachineControl machineControl = machineControlService.findById(machine.getMachineControl().getId());
        if (machineControl == null) {
            logger.info("添加或更新机器基本表信息,根据Id未查询到机器版本信息 -ACCEPTER machineControl===" + machineControl);
            throw new EminException(ExceptionCode.T_SERVICE_EXCEPTION);
        }
        machineControl.setBindStatus(1); //绑定
        machineControl.setBindTime(new Date().getTime());
        machineControlService.saveOrUpdate(machineControl);
        machineInfoService.cancelOrBind(machine);
    }


    /**
     * 查询通道信息
     *
     * @param machineInfoId
     * @return
     */
    private List<MachineChannel> getChannelList(Long machineInfoId) {
        if (machineInfoId == null) {
            return null;
        }
        java.util.List<MachineChannel> channelList = new ArrayList<MachineChannel>();

        PreFilter filters1 = null;
        PreFilter filters2 = null;
        filters1 = PreFilters.eq("machineInfoId", machineInfoId);
        filters2 = PreFilters.eq("status", 1);
        channelList = machineChannelService.findByPreFilter(PreFilters.and(filters1, filters2));
        return channelList;
    }
}
