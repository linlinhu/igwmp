<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="${basePath}/weixinResources/css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/user/take_wine_record.css" />
		<title>来一杯</title>
		<style>
			.mui-scroll-wrapper {
				margin-top: 6vh;
			}
		</style>
		
	</head>

	<body>
		<div class="mui-content">
			<div class="stastics_date">
				统计至：
			</div>
			<div id="scroll1" class="mui-scroll-wrapper">
				<div class="mui-scroll">
				<#list productRecord as pr>	
				<div class="mui-card">
						<div class="mui-card-content">
							<div class="mui-card-content-inner">
								<div class="g-wine_info">
									<div class="mui-clearfix">
										<img class="mui-pull-left" src="../img/wine_name.png">
										<div class="mui-pull-left m-wine_info">
											<p class="u-wine_name">${pr.productName}</p>
											<p class="u-wine_price">${pr.productDegree}度&nbsp;&nbsp;${pr.productType}<span class="u-wine_price_right">累计酒量:<span class="u-color_yellow">${pr.outAmount}</span></span>
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				</#list>
				</div>
			</div>
		</div>

		<script src="${basePath}/weixinResources/js/mui.min.js"></script>
		<script src="${basePath}/weixinResources/js/EminCommon.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/weixinResources/js/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script>
			//alert(screen.height)
			mui('.mui-scroll-wrapper').scroll({
				deceleration: 0.0005 //flick 减速系数，系数越大，滚动速度越慢，滚动距离越小，默认值0.0006
			});

			var myDate = new Date();			
			var weekday = new Array(7)
			weekday[0] = "星期天"
			weekday[1] = "星期一"
			weekday[2] = "星期二"
			weekday[3] = "星期三"
			weekday[4] = "星期四"
			weekday[5] = "星期五"
			weekday[6] = "星期六"

			var date_today = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate() + " "+weekday[myDate.getDay()];
			Emin_getOnlyDom(".stastics_date").innerHTML += date_today;
		</script>
	</body>

</html>