<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-status-bar-style" content="white">
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/mui.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/user/personal_order.css" />
		<title>个人中心</title>
	</head>

	<body>
		<div class="mui-content">
			<div class='wave_box'>
				<div class="head_portrait">
					<img src="${fans.headimgurl}" id="test" />
					<p class="user_name">${fans.nickname}</p>
					<div class="mui-clearfix">
						<div class="user_ware_num ">
							<span class="u-left_part">
							<span class="u-num">250</span>两<span class="u-wine_count">累计酒量</span></span>
							<a class="u-right_part">查看记录</a>
						</div>
					</div>
				</div>
				<div class='wave -one'></div>
				<div class='wave -two'></div>
				<div class="wave_bottom">
					<div class="m-my_order" id="my_order">
						<img src="${basePath}/weixinResources/img/order_01.png" />
						<p>我的订单</p>
					</div>
					<div class="m-contact" id="contact">
						<img src="${basePath}/weixinResources/img/contact.png" />
						<p>联系客服</p>
					</div>
				</div>
			</div>

		</div>

		<script src="${basePath}/weixinResources/js/mui.js" type="text/javascript" charset="utf-8"></script>
		<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		
			var openId = "${openId}";
			
			mui('.wave_bottom').on('tap','#my_order', function(e) {
				window.location.href ="${basePath}/order/takewine.do?status=1&openId="+openId+"&state=${woaId}";					
			});

			mui('.user_ware_num').on('tap','.u-right_part', function(e) {
				window.location.href ="${basePath}/weixin/record.htm?status=2&openId="+openId+"&state=${woaId}";	
			});
			
			
			mui('.wave_bottom').on('tap','#contact', function(e) {
				window.location.href ='${basePath}/weixin/contact.htm';
			});
		</script>
	</body>

</html>