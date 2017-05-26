package com.emin.igwmp.skm.core.netty.group;

import org.springframework.stereotype.Service;

import io.netty.channel.nio.NioEventLoopGroup;

@Service("bossGroup")
public class BossGroup extends NioEventLoopGroup {

    /** 用于分配处理业务线程的线程组个数 */
    private static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2; // 默认
    
    public BossGroup(){
	super(BIZGROUPSIZE);
    }
    
}
