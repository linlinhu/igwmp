/**
 * 说明:获取终端广告信息
 * 调用方式:plus.master.接口名(成功function,失败function,参数列表)
 * 接口说明:
 * author: aleyds 2017-4-21
**/
document.addEventListener("plusready",function(){
	var _BARCODE = 'adv',
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
	var adv = {
		getBootLogo:function(successCallback, errorCallback){
			callbackID = getCallID(successCallback, errorCallback);
			return B.exec(_BARCODE, "getBootLogo", [callbackID]);
		},
		getAwaitUrls:function(successCallback,errorCallback){
			callbackID = getCallID(successCallback, errorCallback);
			return B.exec(_BARCODE, "getAwaitUrls", [callbackID]);
		}
	};
	window.plus.adv = adv;
},true);