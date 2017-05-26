<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="${basePath}/weixinResources/css/mui.min.css?d=3">
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}/weixinResources/css/user/take_wine.css?d=4" />
		<title>来一杯</title>
	</head>
	
	<body>
	<#setting datetime_format="yyyy-MM-dd HH:mm"/>
		<div class="mui-content">
			<div id="grid_tabbar" class="g-grid_tabbar"></div>
			<div id="slider" class="mui-slider">
				<div id="sliderSegmentedControl" class="mui-segmented-control">
					<a class="mui-control-item <#if status ==1>mui-active</#if>" href="#wait_take_wine">
						待取酒
						<badge class="badge" data-badge = "${orderInfo?size}"></badge>
					</a>
					<a class="mui-control-item <#if status !=1>mui-active</#if>" href="#complete_take_wine">
						已完成
					</a>
				</div>
				<div class="g-grid" id="grid"></div>
				
				<div id="grid_window_wait" class="g-grid_window">
					
					<div class="mui-card">
						<div class="mui-card-content">
							<div class="mui-card-header mui-card-media">
								<div class="mui-media-body">
									<span class="mui-pull-left">订单号:<span id="ordernumber"></span></span>
									<a class="mui-pull-right btn_close_wait"><img src="${basePath}/weixinResources/img/close.png"  /></a>
								</div>
							</div>							
							<div class="mui-card-content-inner">
								<div class="mui-clearfix m-underline">
									<img class="mui-pull-left" src="../img/wine_name.png">
									<div class="mui-pull-left m-grid_wine_info">
										<p class="u-wine_name" id ="productname"></p>
										<p class="u-wine_info u-wine_info_middle">购酒时间:<span id="creattime"></span></p>
										<p class="u-wine_info">购酒酒量:<span id="shouldtakeml"></span>两</p>
									</div>
								</div>
							</div>						
						</div>
						<div class="mui-card-content">
							<div class="mui-card-content-inner">
								<li class="mui-table-view-cell mui-collapse">
									<a class="mui-navigate-right m-btn_wait_take_wine" href="#">待打酒</a>
									<div class="mui-collapse-content m-wait_take_wine_info">
										<p>打酒码:<span id="takecode"></span></p>
										<p>有效期至:<span id="codeexipretime"></span></p>
										<p class="u-last_info">打酒酒量:<span id="actualtakeml"></span>两</p>
									</div>
								</li>
							</div>
						</div>
						<div class="g-wine_question">
							订单有问题联系客服 :<span>400-5660-1548</span>
							<button class="g-present_wine">赠酒</button>
						</div>
					</div>
				</div>
				<div id="grid_window_complete" class="g-grid_window">
					<div class="mui-card">
						<div class="mui-card-content">
							<div class="mui-card-header mui-card-media">
								<div class="mui-media-body">
									<span class="mui-pull-left">订单号:<span id="cordernumber"></span></span>
									<a class="mui-pull-right" id="close_btn_complete"><img src="${basePath}/weixinResources/img/close.png" /></a>
								</div>
							</div>
							<div class="mui-card-content-inner">

								<div class="mui-clearfix m-underline">
									<img class="mui-pull-left" src="" id="cproducturl">
									<div class="mui-pull-left m-grid_wine_info">
										<p class="u-wine_name"><span id="cproductname"></span></p>
										<p class="u-wine_info u-wine_info_middle">购酒时间:<span id="ccreatetime"></span></p>
										<p class="u-wine_info">打酒量:<span id="cshouldtakeml"></span>两</p>
									</div>
								</div>
							</div>
						</div>
						<div class="mui-card-content">
							<div class="mui-card-content-inner">
								<li class="mui-table-view-cell mui-collapse">
									<a class="mui-navigate-right m-btn_wait_take_wine" href="#">打酒记录</a>
									<div class="mui-collapse-content m-wait_take_wine_info">
										<p>取酒码:<span id="ctakecode"></span></p>
										<p>取酒地址:强生酒店</p>
										<p>取酒时间:<span id="cfinishtime"></span></p>
										<p>应出酒量:<span id="cshouldtakeml"></span>两</p>
										<p class="u-last_info">实出酒量:<span id="cactualTakeMl"></span>两</p>
									</div>
								</li>

							</div>
						</div>
						<div class="g-wine_question">
							订单有问题联系客服 :<span>400-5660-1548</span>
						</div>

					</div>
				</div>
				
				
				<div id="sliderProgressBar" class="mui-slider-progress-bar mui-col-xs-6"></div>
				<div class="mui-slider-group">
					<#if status ==1>
					<div id="wait_take_wine" class="mui-slider-item mui-control-content <#if status ==1>mui-active</#if>">
						<div id="scroll1" class="mui-scroll-wrapper">
							<div class="mui-scroll">
								<#if orderInfo?size =0 >
									<div style="text-align: center">
			                            <span style="color: #000;font-size: 1rem">暂无记录</span>
			                        </div>
								<#else>
									<#list orderInfo as order>	
									<div class="mui-card">
										<div class="mui-card-content">
											<div class="mui-card-header mui-card-media">
												<div class="mui-media-body">										
													<span id="buy_time">购酒时间：${order.createTime?number?number_to_datetime}</span>
												</div>
											</div>
																			
											<div class="mui-card-content-inner">										
												<a data-productname="${order.productName}" data-producturl="${order.product_url}" data-ordernumber="${order.orderNumber}" data-creattime="${order.createTime?number?number_to_datetime}" data-shouldtakeml="${order.shouldTakeMl}" data-takecode="${order.takeCode}" data-codeexipretime="${order.codeExipreTime}" data-actualtakeml="${order.actualTakeMl}" class="g-wine_info">
													<div class="mui-clearfix">
														<img class="mui-pull-left" src="${order.product_url}">
														<div class="mui-pull-left m-wine_info">
															
															<p class="u-wine_name">${order.productName}<span>${order.productDegree}度&nbsp;&nbsp;${order.productType}</span></p>
															<p class="u-wine_code">打酒码：<span class="u-color_yellow u-active">${order.takeCode}</span></br><span id="effective">(有效期至:${(order.codeExipreTime?number)?number_to_datetime})</span></p>
															<p class="u-wine_price">购酒酒量:${order.shouldTakeMl}两<span class="u-wine_price_right">共支付:<span class="u-color_yellow">${order.payedMoney}元</span></span>
															</p>
														</div>
													</div>
												</a>
																			
												<div class="m-get_wine_code">
													<button  class=<#if (order.codeExipreTime?number) < (.now?number)>"resetCode"</#if>  data-ordernumber = "${order.orderNumber}" data-shouldtakeml = "${order.shouldTakeMl}">重获打酒码</button>
												</div>
	
											</div>
											</#list>	
										</#if>
									</div>	
								</div>
							</div>	
							<#else>
							<div id="complete_take_wine" class="mui-slider-item mui-control-content <#if status !=1>mui-active</#if>">					
						<div id="scroll2" class="mui-scroll-wrapper">
							<div class="mui-card-content">
								<div class="mui-card-header mui-card-media">
									<div class="mui-media-body m-total">
										<span class="mui-pull-right">共计完成 <span class="u-num_total">${orderInfo?size}</span>单</span>
									</div>
								</div>
							</div>
							<div class="mui-scroll">
								<div class="mui-card g-complete">
									<div class="mui-card-content">
										<div class="mui-card-content-inner">
										<#if orderInfo?size =0 >
										 	<div style="text-align:center">
						                        <span style="color:#000;font-size: 1rem">暂无记录</span>
						                    </div>
										<#else>
										<#list orderInfo as order>
											<a class="g-complete_info" data-cproductname="${order.productName}" data-cproducturl="${order.product_url}" data-cordernumber="${order.orderNumber}" data-cfinishtime="${order.finishTime?number?number_to_datetime}" data-ccreatetime="${order.creatTime?number?number_to_datetime}" data-cshouldtakeml="${order.shouldTakeMl}" data-ctakecode="${order.takeCode}" data-ccodeexipretime="${order.codeExipreTime}" data-cactualtakeml="${order.actualTakeMl}">
											<div class="mui-clearfix">
												<img class="mui-pull-left" src="${order.product_url}">
												<div class="mui-pull-left m-complete_info">
													<p class="u-wine_name">${order.productName} </p>
													<p class="u-wine_complete">完成时间:${order.finishTime}</p>
													<p class="u-wine_price">购酒酒量:${order.shouldTakeMl}两
													<span class="u-wine_price_right">共支付:<span class="u-color_yellow">${order.payedMoney}元</span></span>
													</p>
												</div>
											</div>
											</a>
											</#list>
 										</#if>		
										</div>
									</div>
								</div>	
								
							</div>
						</div>
					</div>	
					</#if>	
				</div>
			</div>
								
			<form id="form" method="POST" action="${basePath}/order/takewine.do">
			    <input type="number" id="status" name="status">
			    <input type="test" id="openId" name="openId">
			    <input type="test" id="state" name="state">
			</form>
		</div>	
		
		
		<script src="${basePath}/weixinResources/js/mui.js"></script>
		<script src="${basePath}/weixinResources/js/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script>
	    	var status = ${status};
	    	if(status == 1){
	    		var badge = $(".badge").data().badge;
				$(".badge").html("("+badge+")");
	    	}

			mui.init({
				swipeBack: false
			});
		
			$(".resetCode").each(function(index){
				$(this).on("tap",function(){
					 var data = this.dataset;					 
					 for(var k in data){					 	
				 		if (k =="ordernumber") {
				 		var orderNo = data[k];
				 	}else {
				 		var shouldTakeMl = data[k];
				 	}
				 } 		
					 window.location.href ="${basePath}/order/rePorterCode.do?orderNumber="+orderNo+"&shouldTakeMl="+shouldTakeMl+"&state=${woaId}";		
				})
		
			})		
			
			mui('.mui-scroll-wrapper').scroll({
				indicators: false //是否显示滚动条
			});
			
			$("#sliderSegmentedControl .mui-control-item").each(function(index){
				$(this).on("tap",function(){
					if (index === 1) {
						document.getElementById("status").setAttribute("value", 2);	
						document.getElementById("openId").setAttribute("value", "${openId}");
						document.getElementById("state").setAttribute("value", "${woaId}");	
					}else if(index === 0){
						document.getElementById("status").setAttribute("value", 1);	
						document.getElementById("openId").setAttribute("value", "${openId}");	
						document.getElementById("state").setAttribute("value", "${woaId}");
					}
					document.getElementById("form").submit(); //提交表单	
				}) 		
		
			})	
			
			

			$('#wait_take_wine .mui-card-content-inner>a').each(function(index){				
				$(this).on("tap",function(){
				 var data = this.dataset;
				 for(var k in data){				 	
				 	if (k =="producturl") {				 		
				 		$("#"+k).attr("src",data[k]);
				 	}else {
				 		$("#"+k).html(data[k]);
				 	}
				 	
				 } 									
					$('#grid').show();
					$('#grid_window_wait').show();				
					$('#grid_tabbar').show();	
				})
			})
			

			$('.btn_close_wait').on('tap', function() {
				$('#grid').hide();
				$('#grid_window_wait').hide();
				$('#grid_tabbar').hide();
			})
			
			$('#complete_take_wine .mui-card-content-inner>a').each(function(index){				
				$(this).on("tap",function(){
				 var data = this.dataset;
				 for(var k in data){					 	
				 	if (k =="cproducturl") {
				 		
				 		$("#"+k).attr("src",data[k]);
				 	}else {
				 		$("#"+k).html(data[k]);
				 	}
				 } 										
					$('#grid').show();
					$('#grid_window_complete').show();
					$('#grid_tabbar').show();
				})
			})
			
			$('#close_btn_complete').on('tap', function() {
				$('#grid').hide();
				$('#grid_window_complete').hide();
				$('#grid_tabbar').hide();
			})

		</script>

		</body>

</html>