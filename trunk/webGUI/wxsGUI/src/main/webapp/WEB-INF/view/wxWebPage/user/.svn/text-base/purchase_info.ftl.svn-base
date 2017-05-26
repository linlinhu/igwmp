<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-status-bar-style" content="white">
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/mui.css" />
		<!--<link rel="stylesheet" type="text/css" href="css/mui.min.css"/>-->
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/common_btn.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/user/purchase_info.css" />
		<title>确认购酒信息</title>
		<style type="text/css">

		</style>
	</head>

	<body>
		<!--<header class="mui-bar mui-bar-nav g-header_bg">
			<a class="mui-action-back mui-icon mui-icon-closeempty mui-pull-left"></a>
			<a class="mui-icon mui-icon-more mui-pull-right"></a>
			<h4 class="mui-pull-left">确认购酒信息</h4>
		</header>-->
		<div class="mui-content">
			<div class="mui-card">
				<div class="mui-card-header mui-card-media">
					<div class="mui-media-body">
						<span>所选酒品：</span>
					</div>
				</div>
				<div class="mui-card-content">
					<div class="mui-card-content-inner mui-clearfix">
						<img id="product_url" src="../img/wine.png" />
						<div class="mui-clearfix first">
							<p class="u-wine_left mui-pull-left">所选酒品</p>
							<p class="u-wine_right mui-pull-right" id="product_name">习酒一品金樽</p>
						</div>
						<div class="mui-clearfix">
							<p class="u-wine_num_left mui-pull-left">打酒数量</p>
							<p class="u-wine_right mui-pull-right" id="quentity">3两</p>
						</div>
						<div class="mui-clearfix first">
							<p class="u-wine_price_left mui-pull-left">酒价</p>
							<p class="u-wine_price_right mui-pull-right money " id="price">64元</p>
						</div>
					</div>
				</div>
				<div class="mui-card-footer">
					<div class="mui-card-link">应付</div>
					<div class="mui-card-link money amount">64元</div>
				</div>
			</div>
		</div>
		<div class="mui-bar mui-bar-tab g-nav_btn">
			<div class="mui-pull-left m-part_left">
				<span>应付：</span>
				<span class="money" id="amount">64元</span>
			</div>
			<div class="mui-pull-right m-part_right">
				<button type="button" id="sure_btn" onclick="prepay()">微信支付</button>
			</div>
		</div>
		

		<script src="${basePath}/weixinResources/js/mui.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/weixinResources/js/EminCommon.js" type="text/javascript" charset="utf-8"></script>
		<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/weixinResources/js/pingpp.js?a=2" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/weixinResources/js/EminCommonPayment.js?d=2" type="text/javascript" charset="utf-8"></script>
		
		<script type="text/javascript">		
		
		window.onerror=function(a,b,c){
		
		}
		var tempData = JSON.parse(localStorage.getItem('tempDetailData'));
		if(tempData) {
			var quentity = tempData.quentity;
			var product_name = tempData.product_name;
			var price = tempData.price;
			var product_url = tempData.product_url;
			Emin_getId('quentity').innerHTML = quentity;
			Emin_getId('product_name').innerHTML = product_name;
			Emin_getId('price').innerHTML = price;
			Emin_getId('product_url').src = product_url;
			var priceNo = price.split("￥")[1];
			var money = parseFloat(price)*parseInt(quentity);
			Emin_getOnlyDom('.amount').innerHTML = money + '元';
			Emin_getId('amount').innerHTML = money + '元';
		}	
			var quentity_unit = parseInt(Emin_getId('quentity').innerHTML)*50;
			
			var price_unit = parseFloat(Emin_getId('price').innerHTML) /500;
			var amount = parseFloat(price_unit * quentity_unit);
			var orderNumber ='${orderNumber}';
			function prepay(){		
			//	window.location.href = '${basePath}/order/porterCode.do?orderNumber='+orderNumber+'&shouldTakeMl='+quentity_unit+'&state=${woaId}';
				wap_pay("wx_pub","${orderNumber}",1,"${openId}",function(result,err,paymentNumber){
					if(result=="success"){
						
						//支付成功
						window.location.href = '${basePath}/order/porterCode.do?orderNumber='+orderNumber+'&shouldTakeMl='+quentity_unit+'&state=${woaId}';
		//				wap_pay_complete(paymentNumber);
						
					}else{
						alert("支付失败");
					}		
				})  
				
			}
		
			
			
		</script>
	</body>

</html>