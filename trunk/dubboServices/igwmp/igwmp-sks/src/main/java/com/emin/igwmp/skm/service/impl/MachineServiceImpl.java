package com.emin.igwmp.skm.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.service.Condition;
import com.emin.igwmp.ms.domain.MachineChannel;
import com.emin.igwmp.ms.domain.MachineInfo;
import com.emin.igwmp.ms.facade.accepters.MachineInfoAccepter;
import com.emin.igwmp.pcs.domain.ProductPrice;
import com.emin.igwmp.prds.domain.Images;
import com.emin.igwmp.prds.domain.Product;
import com.emin.igwmp.prds.facade.accepters.ProductAccepter;
import com.emin.igwmp.rp.facade.accepters.ProductReportAccepter;
import com.emin.igwmp.skm.exception.ErrorCode;
import com.emin.igwmp.skm.facade.callers.vo.bean.WinesBean;
import com.emin.igwmp.skm.service.MachineService;
import com.emin.igwmp.skm.util.EmptyUtils;
import com.emin.igwmp.skm.util.LegalUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */
@Service("machineService")
public class MachineServiceImpl implements MachineService {

    @Reference(version="0.0.1")
    private MachineInfoAccepter machineInfoAccepter;

    @Reference(version="0.0.1")
    private ProductAccepter productAccepter;//产品信息

    @Override
    public MachineChannel getChannelForProduct(String ipcCode, Long productId){

        List<Condition> conditions = new ArrayList<Condition>();
        List<MachineInfo> result = null;
        conditions.add(new Condition("ipcCode", Condition.ConditionOperator.EQ, Condition.ConditionType.CHARACTER,ipcCode));

        try{
            result = machineInfoAccepter.queryMachineInfoForList(conditions);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        if (EmptyUtils.isEmpty(result) || EmptyUtils.isEmpty(result.get(0).getChannelList())) {
            return null;
        }
        for (MachineChannel channel: result.get(0).getChannelList()) {
            if(channel.getLiquorInfoId() == productId){
                return channel;
            }
        }
        return null;
    }

    @Override
    public boolean updateChannel(String ipcCode, MachineChannel newChannel) throws Exception {
        List<Condition> conditions = new ArrayList<Condition>();
        List<MachineInfo> result = null;
        conditions.add(new Condition("ipcCode", Condition.ConditionOperator.EQ, Condition.ConditionType.CHARACTER,ipcCode));
        result = machineInfoAccepter.queryMachineInfoForList(conditions);
        if (EmptyUtils.isEmpty(result) || EmptyUtils.isEmpty(result.get(0).getChannelList())) {
            return false;
        }
        for (int i = 0; i < result.get(0).getChannelList().size(); i++) {
            //可能存在一个产品多个通道
            if(result.get(0).getChannelList().get(i).getLiquorInfoId() == newChannel.getLiquorInfoId()
                    && result.get(0).getChannelList().get(i).getChannelNo() == newChannel.getChannelNo()){
                result.get(0).getChannelList().set(i,newChannel);
                machineInfoAccepter.updateMachineInfo(result.get(0));
                return true;
            }
        }
        return true;
    }


}
