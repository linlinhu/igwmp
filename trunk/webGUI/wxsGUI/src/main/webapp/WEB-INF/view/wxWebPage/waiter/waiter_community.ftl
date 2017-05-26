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
<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/waiter/waiter_community.css" />
<title>服务员社区</title>
<style type="text/css">
</style>
</head>

<body>
	<!--<header class="mui-bar mui-bar-nav g-header_bg">
			<a class="mui-action-back mui-icon mui-icon-closeempty mui-pull-left"></a>
			<a class="mui-icon mui-icon-more mui-pull-right"></a>
			<h4 class="mui-pull-left">服务员社区</h4>
		</header>-->

	<div class="mui-content">
		<div class="mui-scroll-wrapper">
			<div class="mui-scroll">
				<#list servantRankList?sort_by('integral')?reverse as sr>
					<#if sr_index==0>
					<div class="m-no1_content">
						<div class="no1_mark">
							<span>NO.${sr_index+1}</span>
						</div>
						<span class="waiter_champion">${sr.name}</span> <img class="crown"
							src="${basePath}/weixinResources/img/crown.png" /> <img class="campion_name"
							src="${basePath}/weixinResources/img/campion_name.png" /> <img class="icon"
							src="${sr.url}" />
					</div>
	
					<div class="m-no1_introduce">
						<p class="u-title">来自 : ${sr.restaurantName}</p>
						<p>
							<span class="u-wine_quality">${sr.amout}两</span><span class="u-wine_price">${sr.integral}分</span>
						</p>
					</div>
					<#else>
						<div class="g-waiter_content mui-clearfix">
						<div class="m-waiter_icon mui-pull-left">
							<span class="u-waiter_no">NO.${sr_index+1}</span> 
							<img class="u-waiter_icon" src="${sr.url}" />
						</div>
						<div class="m-waiter_introduce mui-pull-left">
							<p class="u-title">${sr.name}</p>
							<p>来自:${sr.restaurantName}</p>
							<p>
								<span class="u-wine_quality">24两</span><span class="u-wine_price"> ${sr.integral}分</span>
							</p>
						</div>
					</div>
					</#if>	
				</#list>
			</div>
		</div>

	</div>

	<script src="${basePath}/weixinResources/js/mui.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
			mui('.mui-scroll-wrapper').scroll({
				deceleration: 0.0005 //flick 减速系数，系数越大，滚动速度越慢，滚动距离越小，默认值0.0006
			});
			mui('.mui-bar-nav').on('tap','.mui-icon-more', function(e) {
				//				alert(this.innerHTML);

				mui.openWindow({

					url: 'waiter_earnings_details.html',

					id: 'waiter_earnings_details.html'

				});
			});
		</script>
</body>

</html>