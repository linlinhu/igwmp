<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-status-bar-style" content="white">
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/mui.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/common_tableview.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/common_btn.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/user/detail.css" />
		<title>订单详情</title>
		<style type="text/css">
			.hengxian {
				position: absolute;
				height: 1px;
				width: 200px;
				/*background:red;*/
				top: 4vh;
				background: -webkit-linear-gradient(left, #000, #eee,#000);
				/* Safari 5.1 - 6.0 */
				background: -o-linear-gradient(right, #000, #eee,#000);
				/* Opera 11.1 - 12.0 */
				background: -moz-linear-gradient(right, #000, #eee,#000);
				/* Firefox 3.6 - 15 */
				background: linear-gradient(to right, #000, #eee,#000);
			}
			.shuxian {
				position: absolute;
				width: 1px;
				height: 80px;		
				top: 1vh;		
				left: 9vw;
				background: -webkit-linear-gradient(top, #000, #eee,#000);
				/* Safari 5.1 - 6.0 */
				background: -o-linear-gradient(bottom, #000, #eee,#000);
				/* Opera 11.1 - 12.0 */
				background: -moz-linear-gradient(bottom, #000, #eee,#000);
				/* Firefox 3.6 - 15 */
				background: linear-gradient(to bottom, #000, #eee,#000);
			}
		</style>

	</head>

	<body>		
		<div class="mui-scroll-wrapper">
			<div class="mui-scroll">
				<div class="mui-content">				
					<div class="g-head_img">
						<img id="product_url" src="${productItem.product_url}" />
						<div class="hengxian"></div>
						<div class="shuxian"></div>
						<div class="m-head_type">
							<p>香型：<span id="productType">${productItem.productType}</span></p>
							<p>酒精度：<span id="productDegree">${productItem.productDegree}</span></p>
						</div>
					</div>
					<ul class="mui-table-view">
						<li class="mui-table-view-cell">
							<div class="mui-clearfix m-constance_up">
								<p class="u-product_info mui-pull-left" id="product_name">${productItem.productName}</p>
								<p class="u-product_info mui-pull-right"><span id="price">${productItem.discountedPrice}</span><span class="real_price">门市价：${productItem.realPrice}</span></p>
							</div>
							<div class="mui-clearfix">
								<p class="u-product_num mui-pull-right">已卖出：<span>36斤</span></p>
							</div>
							<div class="mui-clearfix m-constance_down">
								<p class="u-address mui-pull-left">产地</p>
								<p class="u-address mui-pull-right">${productItem.productAddress}</p>
							</div>
						</li>
					
					</ul>	
									
				</div>
				<nav class="g-mui_foot mui-bar mui-bar-tab">
					<button type="button" class="u-next_btn" id="sure_btn" onclick="receiveCode()">领取打酒码<span id ="receive">（${productItem.takeCode}）</span></button>					
				</nav>
				
			</div>

		</div>
		<script src="${basePath}/weixinResources/js/mui.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/weixinResources/js/EminCommon.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/weixinResources/js/detail.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">	
			
			mui('.mui-scroll-wrapper').scroll({
				deceleration: 0.0005 //flick 减速系数，系数越大，滚动速度越慢，滚动距离越小，默认值0.0006
			});
		
			function receiveCode(){
			   	mui.ajax({
    				url:"${basePath}/order/getGivingOrder.do",
    				data:{
    					orderNumber:"${productItem.orderNumber}",
    					receiverId:"${productItem.vendeeId}"
    				},
    				success:function(data){
    					
    					alert(data.message);
    				}
				})  	
			}	
			

			
		</script>
	</body>

</html>