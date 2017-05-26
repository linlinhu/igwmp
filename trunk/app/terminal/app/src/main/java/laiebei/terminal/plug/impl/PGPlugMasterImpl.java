package laiebei.terminal.plug.impl;

import org.json.JSONArray;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.StandardFeature;
import io.dcloud.common.util.JSUtil;
import laiebei.terminal.common.utilcode.DeviceUtils;
import laiebei.terminal.plug.PGPlugMaster;
import laiebei.terminal.plug.vo.VOTest;
import laiebei.terminal.skm.accepter.impl.MasterAccepterImpl;
import laiebei.terminal.skm.listener.OnResponseListener;
import timber.log.Timber;

/**
 * Created by Administrator on 2017/4/17.
 */

public class PGPlugMasterImpl extends StandardFeature implements PGPlugMaster {
    @Override
    public void Relation(final IWebview pWebview, JSONArray array) {
        final String CallBackID = array.optString(0);
        final JSONArray resultJson = new JSONArray();
        try {
            new MasterAccepterImpl().RelationInfo(DeviceUtils.getSerialID(), new OnResponseListener() {
                @Override
                public void OnResult(String row) {
                    resultJson.put(row);

                    JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.OK, false);
                }

                @Override
                public void OnFail(String error) {
                    resultJson.put(error);
                    JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.ERROR, false);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            resultJson.put("网络异常");
            JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.ERROR, false);
        }

    }

    @Override
    public void Wines(final IWebview pWebview, JSONArray array) {
        final String CallBackID = array.optString(0);
        JSONArray resultJson = new JSONArray();
        resultJson.put(VOTest.getWinesInfo());

        new MasterAccepterImpl().WinesInfo(DeviceUtils.getSerialID(), new OnResponseListener() {
            @Override
            public void OnResult(String row) {
                JSONArray resultJson = new JSONArray();
                resultJson.put(row);
                JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.OK, false);
            }

            @Override
            public void OnFail(String error) {
                JSONArray resultJson = new JSONArray();
                resultJson.put(error);
                JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.ERROR, false);
            }
        });
    }

    @Override
    public void Tasting(final IWebview pWebview, JSONArray array) {
        final String CallBackID = array.optString(0);
        long wineId = array.optLong(1);
        Timber.d("品酒  ID" + wineId);
        new MasterAccepterImpl().PayMent(DeviceUtils.getSerialID(), wineId, 25L, 0.01,1,new OnResponseListener() {
            @Override
            public void OnResult(String row) {
                JSONArray resultJson = new JSONArray();
                resultJson.put(row);
                JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.OK, false);
            }

            @Override
            public void OnFail(String error) {
                JSONArray resultJson = new JSONArray();
                resultJson.put(error);
                JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.ERROR, false);
            }
        });

    }

    @Override
    public void Buy(final IWebview pWebview, JSONArray array) {
        final String CallBackID = array.optString(0);
        long productId = array.optLong(1);
        long quantity = array.optLong(2);
        double price = array.optDouble(3);
        Timber.d("买酒产品ID:" + productId +  "  购买量:" + quantity  + " 单价:" +price);
        new MasterAccepterImpl().PayMent(DeviceUtils.getSerialID(), productId, quantity*50l, price,0,new OnResponseListener() {
            @Override
            public void OnResult(String row) {
                JSONArray resultJson = new JSONArray();
                resultJson.put(row);
                JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.OK, false);
            }

            @Override
            public void OnFail(String error) {
                JSONArray resultJson = new JSONArray();
                resultJson.put(error);
                JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.ERROR, false);
            }
        });
        //测试数据
//        resultJson.put(VOTest.getPay());
//        JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.OK, false);
    }

    @Override
    public void Take(final IWebview pWebview, JSONArray array) {
        final String CallBackID = array.optString(0);
        String takeCode = array.optString(1);
        Timber.d(  "取酒码:" + takeCode);

        new MasterAccepterImpl().TakeCodeValidate(DeviceUtils.getSerialID(), 0l, takeCode, new OnResponseListener() {
            @Override
            public void OnResult(String row) {
                JSONArray resultJson = new JSONArray();
                resultJson.put(row);

                JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.OK, false);
            }

            @Override
            public void OnFail(String error) {
                JSONArray resultJson = new JSONArray();
                resultJson.put(error);
                JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.ERROR, false);
            }
        });
    }

    @Override
    public void Replaces(final IWebview pWebview, JSONArray array) {
        final String CallBackID = array.optString(0);
        String waiterID = array.optString(1);
        Timber.d("代打酒  服务员号" +  waiterID);

//        JSONArray resultJson = new JSONArray();
//        resultJson.put(VOTest.getReplaces());
//        JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.OK, false);
        new MasterAccepterImpl().getReplaces(DeviceUtils.getSerialID(),waiterID,new OnResponseListener(){
            @Override
            public void OnResult(String row) {
                JSONArray resultJson = new JSONArray();
                resultJson.put(row);
                Timber.d(  "上传给H5:" + row);
                JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.OK, false);
            }

            @Override
            public void OnFail(String error) {
                JSONArray resultJson = new JSONArray();
                resultJson.put(error);
                JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.ERROR, false);
            }
        });
    }
}
