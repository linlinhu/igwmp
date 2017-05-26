<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="${basePath}/weixinResources/css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/user/index.css" />
		<title>首页</title>

	</head>

	<body>
		<div class="mui-scroll-wrapper">
			<div class="mui-scroll">
				
				<div class="mui-content">
					<img class="m-index_banner" src="${basePath}/weixinResources/img/index_banner.png" />					
					
    				<#list product.resultList as p>   				
					<a data-id = "${p.id}">						
						<img class="m-index_wine" src="${p.productImgUrl}" />
						<div class="first">
							<p class="u-wine_name ">${p.productName}</p>
							<p class="u-wine_introduce">	
								${p.productDesc}													
							</p>
						</div>
						<div class="mui-clearfix second">
							<p class="u-wine_price mui-pull-left">￥${p.discountedPrice}<span>门市价:￥${p.realPrice}</span></p>
							<p class="u-wine_num mui-pull-right">已卖出 :<span> ${p.saleVolume}</span></p>
						</div>
					</a>
					</#list>
					<a>						
						<img class="m-index_wine" src="${basePath}/weixinResources/img/wine.png" />
						<div class="first">
							<p class="u-wine_name ">习酒特曲</p>
							<p class="u-wine_introduce">
								远离世俗，远离了轻薄，远离了娇柔的妩媚，那屡自赤水河谷飘来 远离世俗，远离了轻薄，远离了娇柔的妩媚，那屡自赤水河谷飘来 远离世俗，远离了轻薄，远离了娇柔的妩媚，那屡自赤水河谷飘来
							</p>
						</div>
						<div class="mui-clearfix second">
							<p class="u-wine_price mui-pull-left">￥22<span>门市价:￥32</span></p>
							<p class="u-wine_num mui-pull-right">已卖出 :<span> 36斤</span></p>
						</div>
					</a>
					
				</div>
			</div>
		</div>
	</body>
	<script src="${basePath}/weixinResources/js/mui.min.js"></script>
	<script src="${basePath}/weixinResources/js/jquery-2.1.4.min.js"></script>
	<script src="${basePath}/weixinResources/js/EminCommon.js"></script>
	<script>
		
		
		mui('.mui-scroll-wrapper').scroll({
			deceleration: 0.0005 //flick 减速系数，系数越大，滚动速度越慢，滚动距离越小，默认值0.0006
		});
		mui.plusReady(function() {
			plus.screen.lockOrientation("portrait-primary");
		});
		mui('.mui-content').on('tap', 'a', function(e) {
			var id = this.dataset.id;
			
			window.location.href ='${basePath}/weixin/detail.htm?id='+id+"&state=${woaId}";
		});
		
		
		
	</script>

</html>