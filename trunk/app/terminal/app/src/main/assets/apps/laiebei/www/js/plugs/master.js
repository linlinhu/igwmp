/**
 * 说明:主要业务回调接口
 * 调用方式:plus.master.接口名(成功function,失败function,参数列表)
 * 接口说明:
 * 1、Relation 获取机器与饭店关联信息
 * 2、Wines  获取机器配置酒品列表
 * 3、Tasting 品酒
 * 4、Buy 买酒
 * 5、Take 取酒码取酒
 * 6、获取待取酒列表
 * author: aleyds 2017-4-21
**/
document.addEventListener("plusready",function(){
	var _BARCODE = 'master',
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
	var master = {
		Relation:function(successCallback, errorCallback){
			callbackID = getCallID(successCallback, errorCallback);
			return B.exec(_BARCODE, "Relation", [callbackID]);
		},
		Wines:function(successCallback, errorCallback){
			callbackID = getCallID(successCallback, errorCallback);
			return B.exec(_BARCODE, "Wines", [callbackID]);
		},
		Tasting:function(successCallback, errorCallback,id,channel){
			callbackID = getCallID(successCallback, errorCallback);
			return B.exec(_BARCODE, "Tasting", [callbackID,id,channel]);
		},
		Buy:function(successCallback, errorCallback,id,quantity,price,channel){
			callbackID = getCallID(successCallback, errorCallback);
			return B.exec(_BARCODE, "Buy", [callbackID,id,quantity,price,channel]);
		},
		Take:function(successCallback, errorCallback,takeCode,phone){
			callbackID = getCallID(successCallback, errorCallback);
			return B.exec(_BARCODE, "Take", [callbackID,takeCode,phone]);
		},
		Replaces:function(successCallback, errorCallback, waiter){
			callbackID = getCallID(successCallback, errorCallback);
			return B.exec(_BARCODE, "Replaces", [callbackID,waiter]);
		}
	};
	window.plus.master = master;
},true);