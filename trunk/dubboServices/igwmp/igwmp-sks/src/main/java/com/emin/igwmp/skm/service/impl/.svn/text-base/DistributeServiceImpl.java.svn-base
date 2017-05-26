package com.emin.igwmp.skm.service.impl;

import com.alibaba.fastjson.JSON;
import com.emin.igwmp.skm.core.msmanage.assemble.AssembleMesageFail;
import com.emin.igwmp.skm.core.msmanage.assemble.AssembleSession;
import com.emin.igwmp.skm.core.msmanage.decode.Convert;
import com.emin.igwmp.skm.core.msmanage.distribute.*;
import com.emin.igwmp.skm.core.msmanage.entity.ChiefEntity;
import com.emin.igwmp.skm.core.msmanage.validate.ValidateEng;
import com.emin.igwmp.skm.core.netty.stream.nettyIO;
import com.emin.igwmp.skm.core.pond.PondAdapter;
import com.emin.igwmp.skm.core.pond.PondEntity;
import com.emin.igwmp.skm.exception.ErrorCode;
import com.emin.igwmp.skm.exception.ExceptionCode;
import com.emin.igwmp.skm.exception.RunException;
import com.emin.igwmp.skm.service.DistributeService;
import com.emin.igwmp.skm.util.LogUtils;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/26.
 */
@Service("distributeService")
public class DistributeServiceImpl implements DistributeService {

    @Resource(name = "distributeHeart")
    private DistributeHeart distributeHeart;

    @Resource(name = "distributeMachine")
    private DistributeMachine distributeMachine;//设备

    @Resource(name = "distributeProduct")
    private DistributeProduct distributeProduct;//产品

    @Resource(name = "distributeOrder")
    private DistributeOrder distributeOrder;//订单

    @Override
    public void Distribute(ChannelHandlerContext ctx, ChiefEntity entity) throws RunException {
        if(entity == null || ctx == null){
            throw new RunException(ExceptionCode.T_PARAMTERS_INVALID,"Distribute Service");
        }
        if(entity.getIdent().getType() == 0){
            distributeHeart.Distribute(entity);
        }else{
            if(entity.getHandler().getType() == 0){
                DistributeSession(ctx,entity);
            }else if(entity.getHandler().getType() == 1){
                if(!ValidateEng.validate(entity.getIdent().getDevice(),entity.getHandler())){
                    new AssembleMesageFail(ErrorCode.VERIFY_ERR).Assemble(entity.getIdent().getDevice(),null);
                }else{
                    LogUtils.D("Netty 业务层交互数据:" + JSON.toJSONString(entity));
                    switch (entity.getBody().getType()){
                        case 0://设备服务
                            distributeMachine.Distribute(entity);
                            break;
                        case 1://产品服务
                            distributeProduct.Distribute(entity);
                            break;
                        case 2://订单服务
                            distributeOrder.Distribute(entity);
                            break;
                        default:
                            break;
                    }
                }

            }
        }
    }

    private void DistributeSession(ChannelHandlerContext ctx, ChiefEntity entity){
        ChiefEntity session = new AssembleSession().Assemble(entity.getIdent().getDevice(),null);
        PondEntity pond = new PondEntity().setDevice(entity.getIdent().getDevice())
                .setCreatTime(new Date().getTime())
                .setUpdateTime(new Date().getTime())
                .setSession(session.getHandler().getSesscion())
                .setIP(ctx.channel().remoteAddress())
                .setChannel(ctx);
        try {
            PondAdapter.INSTANCE.addPond(pond);
        } catch (RunException e) {
            //已经存在先删除相同设备的连接池
            PondAdapter.INSTANCE.removePond(entity.getIdent().getDevice());
            PondAdapter.INSTANCE.addPond(pond);
            e.printStackTrace();
        }
        nettyIO.Write(entity.getIdent().getDevice(), Convert.Encode(session));
    }
}
