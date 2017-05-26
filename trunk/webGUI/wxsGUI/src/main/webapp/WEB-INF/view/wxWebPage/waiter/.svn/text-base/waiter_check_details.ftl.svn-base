
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
<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/waiter/waiter_check_details.css" />
<title>查看收益</title>
<style type="text/css">
</style>
</head>

<body>
	<!--<header class="mui-bar mui-bar-nav g-header_bg">
			<a class="mui-action-back mui-icon mui-icon-closeempty mui-pull-left"></a>
			<a class="mui-icon mui-icon-more mui-pull-right"></a>
			<h4 class="mui-pull-left">查看收益</h4>
		</header>-->
	<div class="mui-content">
		<div class="mui-text-center">
			<ul class="mui-pagination">
				<li><a class="m-active" href="javascript:;">当天</a></li>
				<li><a href="javascript:;">近一周</a></li>
				<li><a href="javascript:;">近一月</a></li>
				<li><a href="javascript:;">近三月</a></li>
			</ul>
		</div>
		<div class="m-date_range mui-text-left">
			<div class="u-date_start">
				<span>起始日期</span><span id="date_start" class="mui-pull-right"></span>
			</div>
			<div class="u-date_end">
				<span>截止日期</span><span id="date_end" class="mui-pull-right"></span>
			</div>
		</div>

		<button class="u-btn_sure mui-btn-danger" id="sure_btn"
			onclick="btn_sure()">确 认</button>

	</div>

	<script src="${basePath}/weixinResources/js/mui.js" type="text/javascript" charset="utf-8"></script>
	<script src="${basePath}/weixinResources/js/EminCommon.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="${basePath}/weixinResources/js/jquery-2.1.4.min.js" type="text/javascript"
		charset="utf-8"></script>

	<script type="text/javascript">
			var myDate = new Date();
			var date_today = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate();

			Emin_getId("date_start").innerHTML = date_today;
			Emin_getId("date_end").innerHTML = date_today;
			//myDate.setMonth(myDate.getMonth() - 3);
			//alert(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate())

			$('.mui-pagination').find("li").each(function(index, ele) {
				$(this).on("tap", function() {
					$(this).siblings().children().removeClass("m-active");
					$(this).children().addClass("m-active");
					if($(this).index() == 1) {
						myDate = new Date();
						myDate.setDate(myDate.getDate() - 7);
						var date_last_weekend = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate();
						Emin_getId("date_start").innerHTML = date_last_weekend;
					}
					if($(this).index() == 2) {
						myDate = new Date();
						myDate.setMonth(myDate.getMonth() - 1);
						var date_last_month = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate();
						Emin_getId("date_start").innerHTML = date_last_month;
					}
					if($(this).index() == 3) {
						myDate = new Date();
						myDate.setMonth(myDate.getMonth() - 3);
						var date_last_three_month = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate();
						Emin_getId("date_start").innerHTML = date_last_three_month;
					}
					if($(this).index() == 0) {
						myDate = new Date();
						var date_today = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate();						
						Emin_getId("date_start").innerHTML = date_today;
					}
				})
			})
			
			mui('.mui-content').on('tap', '#sure_btn', function(e) {
			var endTime = Emin_getId("date_start").innerHTML;	
			var timestamp = Date.parse(new Date(endTime+" 00:00:00"));
			console.log(timestamp)
			window.location.href="${basePath}/servant/pointsearch.htm?cellphone=13800000001&startDate="+timestamp;
		})
			
		</script>
</body>

</html>