<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-status-bar-style" content="white">
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/mui.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/waiter/waiter_porter_code.css?d=1" />
		<title>确认购酒信息</title>
	</head>

	<body>

		<div class="mui-content">	
			<div class="g-buy_suc">
				购酒成功
			</div>
			<div class="mui-card g-porter_code_title">
			<#setting datetime_format="yyyy-MM-dd HH:mm"/>
				<div class="mui-card-content">
					<div class="mui-card-content-inner">
						<p class="u-porter_code">${takeWineRecordVO.takeCode}</p>
						<p class="u-date_valid">有效期至：${(takeWineRecordVO.codeExpireTime?number)?number_to_datetime}</p>
						<p class="u-introduce">(若取酒码过期请重新获取取酒码。)</p>
					</div>
				</div>
			</div>
			
			<div class="mui-card second">
				<div class="mui-card-content">
					<div class="mui-card-content-inner">
						<li class="mui-table-view-cell">
							<div class="u-waiter">请服务员打酒</div>
							
							<div class="mui-button-row m-code" id="code">
								
							</div>

						</li>
					</div>
				</div>
			</div>
		</div>

		<script src="${basePath}/weixinResources/js/mui.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/weixinResources/js/jquery-2.1.4.min.js"></script>
		<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="${basePath}/weixinResources/js/jquery.qrcode.min.js"></script>
		<script type="text/javascript">
			
			var takeCode = '${takeWineRecordVO.takeCode}';
			jQuery(function(){
				jQuery('#code').qrcode("${basePath}/wxservant/takewine.htm?takeCode="+takeCode+"&openId=${openId}");
			}) 
			
			var conf = ${jsAPIConf};			
			conf.jsApiList=["checkJsApi","onMenuShareQQ","onMenuShareTimeline","onMenuShareAppMessage","onMenuShareWeibo","scanQRCode"];
			wx.config(conf);
			
			wx.ready(function(){

				wx.onMenuShareAppMessage({
    				title: '分享打酒码', // 分享标题
    				
    				desc: '来一杯兄弟', // 分享描述
    				link: '${urlLink}', // 分享链接
    				imgUrl: '', // 分享图标
    				type: '', // 分享类型,music、video或link，不填默认为link
    				dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
    				success: function () { 
        				mui.ajax({
        				url:"${basePath}/order/giveOrder.do",
        				data:{
        					orderNumber:"${orderNumber}"       					
        				},
        				success:function(data){
        						alert(data.success)
        					}
        				})     				
    				},
    				cancel: function (data) { 
    					mui.ajax({
	        				url:"${basePath}/order/cancelGiveOrder.do",
	        				data:{
	        					orderNumber:"${orderNumber}"
	        				},
	        				success:function(data){
	        					alert(data.message)
	        				}
        				})	
    				}
				});
			
			});

		</script>
	</body>

</html>