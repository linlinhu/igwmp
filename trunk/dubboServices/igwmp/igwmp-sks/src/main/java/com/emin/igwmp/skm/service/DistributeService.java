package com.emin.igwmp.skm.service;

import com.emin.igwmp.skm.core.msmanage.entity.ChiefEntity;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Administrator on 2017/3/26.
 */
public interface DistributeService {

    public void Distribute(ChannelHandlerContext ctx,ChiefEntity entity);
}
