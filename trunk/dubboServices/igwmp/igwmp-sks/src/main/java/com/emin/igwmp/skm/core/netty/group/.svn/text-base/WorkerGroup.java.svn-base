package com.emin.igwmp.skm.core.netty.group;

import org.springframework.stereotype.Service;

import io.netty.channel.nio.NioEventLoopGroup;

@Service("workerGroup")
public class WorkerGroup extends NioEventLoopGroup {

    /** 业务出现线程大小 */
    private static final int BIZTHREADSIZE = 30;
    
    public WorkerGroup(){
	super(BIZTHREADSIZE);
    }
}
