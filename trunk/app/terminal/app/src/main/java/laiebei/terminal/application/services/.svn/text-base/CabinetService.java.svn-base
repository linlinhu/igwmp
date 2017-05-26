package laiebei.terminal.application.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcloud.android.annotation.Nullable;

import java.util.List;

import laiebei.terminal.common.utilcode.DeviceUtils;
import laiebei.terminal.common.utilcode.EmptyUtils;
import laiebei.terminal.dbm.DaoLite;
import laiebei.terminal.dbm.domain.VentoutOrder;
import laiebei.terminal.exceptions.RunException;
import laiebei.terminal.skm.accepter.impl.MasterAccepterImpl;
import laiebei.terminal.skm.listener.OnResponseListener;
import laiebei.terminal.skm.msmanage.vo.response.OrderVO;
import laiebei.terminal.trade.wine.facade.accepter.impl.CabinetAccepterImpl;
import laiebei.terminal.trade.wine.listener.OnCabinetListener;
import laiebei.terminal.trade.wine.vo.StatusVO;
import laiebei.terminal.trade.wine.vo.bean.StatusBean;
import timber.log.Timber;

/**
 * Created by Administrator on 2017/4/18.
 */

public class CabinetService extends Service {

    public final static String PARAM_KEY = "cabinet.key";

    //Service To H5
    public final static String ACTION_CABINET_ORDER_H5 = "cabinet.order.h5";
    public final static String ACTION_CABINET_VENDOUT_H5 = "vendout.vendout.h5";
    //Cabinet To Service
    public final static String ACTION_CABINET_ORDER = "cabinet.order";

    //Message ID
    private final int MESSAGE_REPORT = 0;

    private OnResponseListener  listener;

    public void setListener(OnResponseListener listener){
        this.listener = listener;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ReceiverInit();
        OrderInitTask();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**************************广播定义  start**********************************/
    private void sendReceiver(String action, String param){
        Intent intent = new Intent();
        intent.setAction(action);
        intent.putExtra(PARAM_KEY,param);
        sendBroadcast(intent);
    }

    private void ReceiverInit(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_CABINET_ORDER);
        registerReceiver(receiver,filter);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Bundle bundle = intent.getExtras();
            if(action.equalsIgnoreCase(ACTION_CABINET_ORDER)){
                if(EmptyUtils.isNotEmpty(bundle)){
                    String param = bundle.getString(PARAM_KEY);
                    Timber.d("酒柜服务获取到订单服务" + param);
                    sendReceiver(ACTION_CABINET_ORDER_H5,param);
                    OrderVO vo  = JSON.parseObject(param,OrderVO.class);
                    JSONObject json =  new CabinetAccepterImpl().vendout(vo.getRows().getOrderId(), vo.getRows().getProductId(),vo.getRows().getChannel(), vo.getRows().getValue(), new OnCabinetListener() {
                        @Override
                        public void OnResult(String result) {
                            sendReceiver(ACTION_CABINET_VENDOUT_H5,result);
                            vendoutResult(result);
                        }

                        @Override
                        public void OnFail(String result) {
                            sendReceiver(ACTION_CABINET_VENDOUT_H5,result);
                            vendoutResult(result);
                        }
                    });
                    sendReceiver(ACTION_CABINET_VENDOUT_H5,json.toJSONString());
                }
            }
        }
    };
    /**************************广播定义  end**********************************/

    /**************************订单上报   stat********************************/

    private void vendoutResult(String message){
        StatusVO vo = JSON.parseObject(message,StatusVO.class);
        DaoLite.INSTANCE.saveOrUpdateOrder(vo.getRows().getOrderId(),vo.getRows().getProductId(),vo.getRows().getChannel(),
                vo.getRows().getValue(),vo.getRows().getStatus(),false);
        if(vo.getRows().getStatus() == 4){//订单完成
            sendHandler(MESSAGE_REPORT,vo);
        }
    }

    private void sendHandler(int wath, Object obj){
        Message msg = reportHandler.obtainMessage();
        if(EmptyUtils.isEmpty(msg)){
            msg = new Message();
        }
        msg.what = wath;
        msg.obj = obj;
        reportHandler.sendMessage(msg);
    }

    /**
     * 上报错误，延时上报
     * */
    private void ReportAgainDelay(final Object obj){
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                sendHandler(MESSAGE_REPORT, obj);
            }
        }.start();
    }

    private Handler reportHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE_REPORT:
                    final StatusVO vo = (StatusVO) msg.obj;
                    try {
                        new MasterAccepterImpl().ReportOrder(DeviceUtils.getSerialID(), vo.getRows().getOrderId(),
                                vo.getRows().getChannel(), vo.getRows().getActutalValue(), (vo.getRows().getStatus() != 4), new OnResponseListener() {
									@Override
									public void OnResult(String row) {
                                        JSONObject json = JSON.parseObject(row);
                                        if(json.getBoolean("success")){
                                            DaoLite.INSTANCE.saveOrUpdateOrder(vo.getRows().getOrderId(),vo.getRows().getProductId(),
                                                    vo.getRows().getChannel(), vo.getRows().getValue(),vo.getRows().getStatus(),true);
                                        }else{
                                            ReportAgainDelay(vo);
                                        }
									}

									@Override
									public void OnFail(String error) {
                                        ReportAgainDelay(vo);
									}
								});
                    } catch (RunException e) {
                        e.printStackTrace();
                        ReportAgainDelay(vo);
                    }
                    break;
                default:
                    break;
            }

        }
    };

    /**************************订单上报   end********************************/
    //开机任务，发现有为上报成功的订单进行重新上报
    private void OrderInitTask(){
        List<VentoutOrder> lists = DaoLite.INSTANCE.queryOrderList(false);
        if(EmptyUtils.isNotEmpty(lists)){
            for (VentoutOrder order : lists){
                StatusVO vo = new StatusVO();
                StatusBean bean = new StatusBean();
                vo.setSuccess(true);
                bean.setOrderId(order.getOrderId());
                bean.setChannel(order.getChannel());
                bean.setValue(order.getValue());
                bean.setActutalValue(order.getValue());
                bean.setStatus(order.getStatus());
                bean.setResultCode(order.getResultCode());
                vo.setRows(bean);
                sendHandler(MESSAGE_REPORT,vo);
            }
        }
    }

}
