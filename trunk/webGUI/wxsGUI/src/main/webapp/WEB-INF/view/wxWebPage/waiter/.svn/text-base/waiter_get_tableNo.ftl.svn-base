<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-status-bar-style" content="white">
<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/mui.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/waiter_common.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/waiter/waiter_swipe_code.css" />
 
<title>扫码付款</title>
</head>
<body>
	<!--<header class="mui-bar mui-bar-nav g-header_bg">
			<a class="mui-action-back mui-icon mui-icon-closeempty mui-pull-left"></a>
			<a class="mui-icon mui-icon-more mui-pull-right"></a>
			<h4 class="mui-pull-left">扫码付款</h4>
		</header>-->
	<div class="g-grid" id="grid"></div>
	<div class="g-grid_window" id="grid_window">
		<div class="g-grid_ramadhin">
			<div class="m-icon_close mui-pull-right" onclick="cancel()"></div>
			<div class="m-grid_write_no">请到酒柜输入打酒码取酒</div>
			<div class="m-grid_write_no">打酒码:  <span id="takeCode"></span></div>
			<div class="m-grid_table_num">桌号备注: <span id="window_tableNo"><span>号</div>
			<div class="m-pay">
				支付人: <span class="u-payer mui-pull-right"><img id="userImg" src="" /><span id="userName"></span></span>
			</div>
		</div>
	</div>
	<div class="mui-content">
		<button class="u-btn_sure mui-btn-danger" onclick="btn_sure()" id="sure_btn">确认</button>
	</div>
	<div class="g-ramadhin">
		<div class="m-write_no">填写桌号或备注</div>
		<div class="m-num mui-pull-left">
			<input id="tableNumber" type="number"/>
		</div>
		<div class="mui-pull-left m-remark">
			<span>选填</span>
		</div>

	</div>
	<script src="${basePath}/weixinResources/js/mui.js" type="text/javascript" charset="utf-8"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript" charset="utf-8"></script>
	<script src="${basePath}/weixinResources/js/EminCommon.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
			var orderId ='${orderId}';
			var success = '${success}';
			if(success){
				mui.ajax({
					url:"${basePath}/wxservant/getOrderInfo.do",
					data:{
						orderId:orderId
					},
					success:function(data){
						document.querySelector("#takeCode").innerHTML=data.takeCode;
						document.querySelector("#userName").innerHTML=data.nickname;
						document.querySelector("#userImg").src=data.headimgurl;
					}
				})
			}else{
				WeixinJSBridge.invoke('closeWindow',{},function(res){
				   mui.toast("${message}")
				});
			}
	 		
	
		 		

		function btn_sure() {
			var tableNo = document.querySelector("#tableNumber").value;
			alert(tableNo+":::::::::::::::::"+orderId)
			//如果有桌号，保存桌号到记录
			if(tableNo!=null || tableNo!=undefined){
				mui.ajax({
					url:"${basePath}/wxservant/saveTable.do",
					data:{
						tableNumber:tableNo,
						id:orderId
					},
					success:function(){
						mui.toast("请到酒柜输入打酒码取酒");
						Emin_getId("grid").style.display = "block";
						Emin_getId("grid_window").style.display = "block";
						document.querySelector("#window_tableNo").innerHTML=tableNo;
						return;
					}
				})
			}
			Emin_getId("grid").style.display = "block";
			Emin_getId("grid_window").style.display = "block";
			document.querySelector("#window_tableNo").innerHTML="无";
			
		}

		function cancel() {
			Emin_getId("grid").style.display = "none";
			Emin_getId("grid_window").style.display = "none";
			window.location.href="${basePath}/wxservant/record.htm?openId="+openId
		}
	</script>
</body>

</html>