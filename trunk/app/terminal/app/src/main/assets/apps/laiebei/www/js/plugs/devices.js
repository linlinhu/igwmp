/**
 * 说明:主要业务回调接口
 * 调用方式:plus.devices.接口名(成功function,失败function,参数列表)
 * 接口说明:
 * 1、getPackageInfo 获取设备信息
 * 2、getIpcCode  获取设备序列号
 * 3、getTrafficInfo 获取设备使用流量情况
 * 4、vendout 通知出酒(运维使用)
 * 5、clearCache 清除设备缓存(运维使用)
 * 6、deleteCache (根据模块与类型清除缓存，运维使用)
 * author: aleyds 2017-4-21
**/
document.addEventListener("plusready",function(){


	var _BARCODE = 'devices',
		B = window.plus.bridge;
	var getCallID = function(successCallback, errorCallback){
		var success = typeof successCallback !== 'function' ? null : function(args) 
			{
				successCallback(args);
			},
			fail = typeof errorCallback !== 'function' ? null : function(code) 
			{
				errorCallback(code);
			};
		return B.callbackId(success, fail);
	};
	var devices = {
		getPackageInfo:function(successCallback, errorCallback){
			callbackID = getCallID(successCallback, errorCallback);
			return B.exec(_BARCODE, "getPackageInfo", [callbackID]);
		},
		getIpcCode:function(successCallback, errorCallback){
			callbackID = getCallID(successCallback, errorCallback);
			return B.exec(_BARCODE, "getIpcCode", [callbackID]);
		},
		vendout:function(successCallback, errorCallback,orderId,channel,value){
			callbackID = getCallID(successCallback, errorCallback);
			return B.exec(_BARCODE, "vendout", [callbackID,orderId,channel,value]);
		},
		getQrcode:function(successCallback, errorCallback,url){
			callbackID = getCallID(successCallback, errorCallback);
			return B.exec(_BARCODE, "getQrcode", [callbackID,url]);
		},
		clearCache:function(successCallback, errorCallback){
			callbackID = getCallID(successCallback, errorCallback);
			return B.exec(_BARCODE, "clearCache", [callbackID]);
		},
		validateApp:function(successCallback, errorCallback){
			callbackID = getCallID(successCallback, errorCallback);
			return B.exec(_BARCODE, "validateApp", [callbackID]);
		},
		validteUI:function(successCallback, errorCallback,version){
			callbackID = getCallID(successCallback, errorCallback);
			return B.exec(_BARCODE, "validteUI", [callbackID,version]);
		},
		updateComplete:function(successCallback, errorCallback,url,path){
			callbackID = getCallID(successCallback, errorCallback);
			return B.exec(_BARCODE, "updateComplete", [callbackID,url,path]);
		},
		saveVer:function(successCallback, errorCallback,uiVer){
			callbackID = getCallID(successCallback, errorCallback);
			return B.exec(_BARCODE, "saveVer", [callbackID,uiVer]);
		},
		trafficRecord:function(successCallback, errorCallback,type,value){
			callbackID = getCallID(successCallback, errorCallback);
			return B.exec(_BARCODE, "trafficRecord", [callbackID,type,value]);
		}
	};
	window.plus.devices = devices;
},true);