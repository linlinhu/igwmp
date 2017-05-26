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
<link rel="stylesheet" type="text/css"
href="${basePath}/weixinResources/css/waiter/waiter_earnings_details.css" />
<title>收益详情</title>

</head>

<body>
	<div class="mui-content">
		<div class="mui-scroll-wrapper">
			<div class="mui-scroll">
				<#list servantVenoutList.resultList as svl>
					<div class="mui-clearfix">
						<div class="g-earnings_time mui-pull-left">18:22</div>
						<div class="g-earnings_content mui-pull-left">
							<div class="m-wine_name">
								${svl.wineName}
							</div>
							<div class="m-wine_price">
								<span class="u-wine_quanlity">${svl.vendoutCapacity}两</span><span
									class="u-wine_price">${svl.gainIntegral}分</span>
							</div>
						</div>
					</div>
				</#list>
			</div>
		</div>
	</div>
</body>
<script src="${basePath}/weixinResources/js/mui.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
		mui('.mui-scroll-wrapper').scroll({
			deceleration: 0.0005 //flick 减速系数，系数越大，滚动速度越慢，滚动距离越小，默认值0.0006
		});
	</script>
</html>