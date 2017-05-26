/*
 * UI升级 Wgt 
 * 
 * 
 * */
function installCimplete(url, path){
	console.log("通知监控程序，安装完成");
	plus.devices.updateComplete(function(){
		
	},function(){
		
	},url,path);
}

// 更新应用资源
function installWgt(url , path){
    plus.nativeUI.showWaiting("正在安装更新，请稍后...");
    console.log("installWgt 正在安装更新，请稍后...");
    plus.runtime.install(path,{},function(){
        plus.nativeUI.closeWaiting();
        console.log("installWgt 安装更新成功");
        installCimplete(url,path);
        mui.toast('程序更新完成,即将重启',{ duration:'long', type:'div' });
        plus.runtime.restart();
//      plus.nativeUI.alert("程序更新完成！",function(){
//          plus.runtime.restart();
//      });
    },function(e){
        plus.nativeUI.closeWaiting();
        installCimplete(url,path);
        console.log("installWgt 安装wgt文件失败["+e.code+"]："+e.message);
//      plus.nativeUI.alert("安装wgt文件失败["+e.code+"]："+e.message);
    });
}
