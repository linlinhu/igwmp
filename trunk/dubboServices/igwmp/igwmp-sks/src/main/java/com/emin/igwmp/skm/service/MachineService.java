package com.emin.igwmp.skm.service;

import com.emin.igwmp.ms.domain.MachineChannel;

/**
 * Created by Administrator on 2017/4/24.
 */
public interface MachineService {

    public MachineChannel getChannelForProduct(String ipcCode, Long productId) throws Exception;
    public boolean updateChannel(String ipcCode, MachineChannel channel)throws Exception;
}
