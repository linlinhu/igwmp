

/*
 * 判断用户是否是服务员,如果是，择自动登录
 * 传入参数
 * openId
 * state(woaId)
 * url：验证成功后的跳转地址
 */
function isServant(openId,url,basePath){
	mui.ajax({
		url:basePath+"/wxservant/isValid.do",
		data:{
			openId:openId
		},
		dataType:"json",
		success:function(data){
			if(data.success){
				window.location.href=url;
			}else{
				mui.toast(data.message);
			}
		}
	})
}
