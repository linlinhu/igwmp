package com.emin.igwmp.skm.core.pond;

import io.netty.channel.ChannelHandlerContext;

import java.net.SocketAddress;

/**
 * Created by Administrator on 2017/3/23.
 */
public class PondEntity {

    /**
     * device : 设备识别码--工控机产品序列号
     * creatTime : 链接创建时间
     * updateTime : 修改时间
     * IP : 链接设备ID
     * channel:通信通道
     */

    private String device;
    private Long creatTime;
    private Long updateTime;
    private SocketAddress IP;
    private String session;
    private ChannelHandlerContext channel;//通信通道

    public String getDevice() {
        return device;
    }

    public PondEntity setDevice(String device) {
        this.device = device;
        return this;
    }

    public Long getCreatTime() {
        return creatTime;
    }

    public PondEntity setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
        return this;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public PondEntity setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public SocketAddress getIP() {
        return IP;
    }

    public PondEntity setIP(SocketAddress IP) {
        this.IP = IP;
        return this;
    }

    public String getSession() {
        return session;
    }

    public PondEntity setSession(String session) {
        this.session = session;
        return this;
    }

    public ChannelHandlerContext getChannel() {
        return channel;
    }

    public PondEntity setChannel(ChannelHandlerContext channel) {
        this.channel = channel;
        return this;
    }
}
