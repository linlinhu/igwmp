package laiebei.terminal.skm.accepter;

import laiebei.terminal.skm.listener.OnResponseListener;

/**
 * Created by Administrator on 2017/4/17.
 */

public interface MasterAccepter {
    /**
     * 获取机器与饭店关联信息
     * @param ipcCode 机器ID
     * @param listener 返回监听
     *
     * */
    public void RelationInfo(String ipcCode,OnResponseListener listener);

    /**
     * 获取机器配置通道信息
     * @param ipcCode 机器ID
     * @param listener 返回监听
     *
     * */
    public void WinesInfo(String ipcCode,OnResponseListener listener);

    /**
     * 购买支付
     * @param ipcCode 机器ID
     * @param productId 产品标识
     * @param value 购买计量
     * @param listener 返回监听
     * */
    public void PayMent(String ipcCode,Long productId,Long value,double price,int payType,OnResponseListener listener);

    /**
     * 上传取酒码取酒
     * @param ipcCode 机器识别ID
     * @param productId 产品ID
     * @param code 取酒码
     * @param listener 返回监听
     * */
    public void TakeCodeValidate(String ipcCode, Long productId, String code,OnResponseListener listener);

    /**
     * 上传出酒订单
     * @param ipcCode 机器识别ID
     * @param orderId 订单号
     * @param channel 出酒通道号
     * @param value 出酒量
     * @param isSuccess 是否出酒成功
     * @param listener 返回监听
     * */
    public void ReportOrder(String ipcCode,String orderId,
                            int channel,int value,boolean isSuccess,OnResponseListener listener);

    public void getReplaces(String ipcCode, String phone,OnResponseListener listener);

    public void getQrcode(String ipcCode, String publicUtl,OnResponseListener listener);
}
