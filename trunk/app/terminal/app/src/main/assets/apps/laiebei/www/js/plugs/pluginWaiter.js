
/**
 * 说明:主要业务回调接口
 * 调用方式:注册订单监听
 * 接口说明:
 * author: aleyds 2017-4-21
**/
document.addEventListener("plusready",function(){
	
//
//  //原生android包
    var Context = plus.android.importClass("android.content.Context");
    var IntentFilter = plus.android.importClass('android.content.IntentFilter');
    var Intent = plus.android.importClass('android.content.Intent');
    
//  // android原生对象
    var mainActivity = null;
    var receiver = null;
//  // 业务广播标识
    var PARAM_KEY = "cabinet.key";
    var ACTION_CABINET_WAITER = "cabinet.waiter.h5";//出酒订单
//
    var pluginWaiter = {
        create : function(callback){
            var _callback = typeof callback !== 'function' ? null : function(args) {
                callback(args);
            };
            _callback("true");
        },
        createJob : createJob, // 延迟 n 毫秒执行
        cancelJob : cancelJob
    };
//
    /**
     * 初始化,获取MainActivity,创建广播接收器
     */
    function initReceiver(callback){
        var _callback = typeof callback !== 'function' ? null : function(args) {
            callback(args);
        };
        mainActivity = plus.android.runtimeMainActivity();
        // 广播接收
        receiver = plus.android.implements('io.dcloud.android.content.BroadcastReceiver', {
            onReceive: function(context, intent) { //实现onReceiver回调函数
                plus.android.importClass(intent); //通过intent实例引入intent类，方便以后的‘.’操作
                var broadcast = intent.getAction();
                var bundle = intent.getExtras();
                plus.android.importClass(bundle);
                if(broadcast == ACTION_CABINET_WAITER){
                	var param = bundle.getString(PARAM_KEY);
                    _callback(param);
                }
           }});
    }
//  
    function cancelJob(){
    	if(receiver != null){
    		if(mainActivity == null){
            	mainActivity = plus.android.runtimeMainActivity();
        	}
    		mainActivity.unregisterReceiver(receiver); //取消监听
    		receiver = null;
    	}
    }
    function createJob(callback) {
		cancelJob();
        initReceiver(callback);
        if(mainActivity == null){
            mainActivity = plus.android.runtimeMainActivity();
        }
        // --- 注册监听 start ---
        var filter = new IntentFilter();
        filter.addAction(ACTION_CABINET_WAITER);
        mainActivity.registerReceiver(receiver,filter);
        // --- 注册监听 end ---

    }
//
    window.plus.waiter = pluginWaiter;
}, true);