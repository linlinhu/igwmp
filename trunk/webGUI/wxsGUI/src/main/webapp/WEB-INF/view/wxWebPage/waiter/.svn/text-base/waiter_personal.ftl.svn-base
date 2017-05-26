<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no" />
		<meta name="apple-mobile-web-app-status-bar-style" content="white" />
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/mui.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/waiter_common.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/waiter/waiter_personal.css" />
		<title>个人中心</title>
	</head>

	<body>
		<div class="mui-content">
			<div class="g-personal_title">
				<img src="${basePath}/weixinResources/img/icon2.jpg" />
				<div class="m-waiter_name">
					${servantInfo.name}
				</div>
				<div class="m-waiter_address">
				<#list servantInfo.restaurants as sr>
					<#if sr_index==0>
						${sr.name}
					</#if>
				</#list>	
				</div>
				<div class="m-waiter_integral">
					积分
					<span>${servantInfo.integral} </span>分 | 
					累计打酒
					<span>500 </span>斤
				</div>
			</div>
			<div class="g-personal_content">
				<div class="m-content1">
					<div class="m-content1_up item" data-tag="record">
						<span>代打记录</span>
					</div>
					<div class="m-content1_down item" data-tag="money">
						<span>我要提现</span>
					</div>
				</div>
				<div class="m-content2">
					<div class="m-content2_up item" data-tag="point">
						<span>积分详情</span>
					</div>
					<div class="m-content2_down item" data-tag="community">
						<span>打酒员社区</span>
					</div>
				</div>
			</div>
		</div>
		<script src="${basePath}/weixinResources/js/mui.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/weixinResources/js/EminCommon.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			//var servantId = ${servant.id}
			/*localStorage.cellphone=${cellphone};
			localStorage.open=${openId};*/
			var openId = '${openId}';
			alert(openId)
			mui(".g-personal_content").on("tap",".item",function(){
				var tag = this.dataset.tag;
				if(tag=="record"){
					var status = 1;
					alert("${woaId}")
					window.location.href = '${basePath}/wxservant/'+tag+'.htm?openId='+openId+"&vendoutStatus="+status+"&state=${woaId}";
				}
			})
		</script>
	</body>

</html>