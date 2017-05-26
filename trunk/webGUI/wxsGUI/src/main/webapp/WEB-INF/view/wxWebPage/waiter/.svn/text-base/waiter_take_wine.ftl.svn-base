<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<link rel="stylesheet" href="${basePath}/weixinResources/css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/waiter/waiter_take_wine.css" />
		<title>打酒记录</title> 
	</head>

	<body>
		<div class="btn_scan" id="scanQRCode0">
			<img src="${basePath}/weixinResources/img/scan.png" />
		</div>
		<div class="mui-content">
			<div id="slider" class="mui-slider">
				<div id="sliderSegmentedControl" class=" mui-segmented-control">
					<a class="mui-control-item mui-active" data-status=0>
						待取酒
					</a>
					<a class="mui-control-item" data-status=2>
						取酒完成
					</a> 
				</div>
				<div id="sliderProgressBar" class="mui-slider-progress-bar mui-col-xs-6"></div>
				<div class="mui-slider-group">
					<div id="wait_take_wine" class="mui-slider-item mui-control-content mui-active">
						<div id="scroll1" class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<#list recordList.resultList as list>
									<div class="mui-card <#if list_index==0>g-first</#if>">
										<div class="mui-card-content">
											<div class="mui-card-content-inner">
												<div class="mui-clearfix">
													<img class="mui-pull-left" src="${basePath}/weixinResources/img/wine_name.png">
													<div class="mui-pull-left m-wine_info">
														<p class="u-wine_name">${list.wineName}${list_index}</p>
														<p class="u-wait_wine_info">购酒时间 ：${list.createTime}</br>
														购酒酒量 :<span class="u-color_red">${list.vendoutCapacity}两</span></br>
														支付人 : ${list.vendeeInfo.name}</br>
														备注 : <span class="u-color_red">${list.tableNum}号桌</span></p>
													</div>
												</div>
												<#if list.vendoutStatus ==0></#if>
												<div class="m-get_wine_code">
													<p>
														<span>打酒码:${list.tableNum}</span></br>
														<span>有效期:${list.codeExpireTime}</span>
													</p>
												</div>
											</div>
										</div>
									</div>
								</#list>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
		<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.1.0.js" ></script>
		<script src="${basePath}/weixinResources/js/mui.min.js"></script>
		<script>
			mui.init({
				swipeBack: false
			});
			mui('.mui-scroll-wrapper').scroll({
				deceleration: 0.0005 //flick 减速系数，系数越大，滚动速度越慢，滚动距离越小，默认值0.0006
			});
			var basepath ="${basePath}";
		    var conf = ${jsAPIConf};
		    var openId='${openId}';
		    var basepath_Oath = basepath.replace(":80",""); 
			
		  	conf.jsApiList=["onMenuShareTimeline","onMenuShareAppMessage","hideMenuItems","scanQRCode"];
			wx.config(conf);
			document.getElementById("scanQRCode0").onclick=scan;
			function scan(){
				wx.scanQRCode({
				    needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
				    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
				    success: function (res) {
						var url = res.resultStr;
						var codeInfo = url.split("=")[1]
						var toUrl = basepath_Oath+"/wxservant/takewine.htm?takeCode="+codeInfo+"&openId="+openId;
						mui.ajax({
				        	url:basepath+"/woa/oauth.do",
				        	data:{
				        		url:toUrl,
				        		woaId:${woaId}
				        	},
				        	success:function(data){
				        		window.location.replace(data)
				        	},error:function(xhr,type,errorThrown){
				        		alert(type)
				        	}
				        });
				    }
				});
			}
			
			mui("#sliderSegmentedControl").on("tap",".mui-control-item",function(){
				var status = this.dataset.status;
				window.location.href=basepath+'/wxservant/record.htm?openId='+openId+"&vendoutStatus="+status+"&state=${woaId}";
			})


		</script>
	</body>
</html>