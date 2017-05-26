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
				<#list productInfo.resultList as p>
				<div class="mui-content">				
					<div class="g-head_img">
					<!--<img src="../img/detail_blank.png" />-->
						<img id="product_url" src="${p.productImgUrl}" />
						<div class="hengxian"></div>
						<div class="shuxian"></div>
						<div class="m-head_type">
							<p>香型：<span id="productType">${p.productType}</span></p>
							<p>酒精度：<span id="productDegree">${p.productDegree}</span></p>
						</div>
						<div class="m-head_description">
							<span>${p.productDesc}id为:${p.id}</span>
						</div>
					</div>
					<ul class="mui-table-view">
						<li class="mui-table-view-cell">
							<div class="mui-clearfix m-constance_up">
								<p class="u-product_info mui-pull-left" id="product_name">${p.productName}</p>
								<p class="u-product_info mui-pull-right"><span id="price">${p.discountedPrice}</span><span class="real_price">门市价：${p.realPrice}</span></p>
							</div>
							<div class="mui-clearfix">
								<p class="u-product_num mui-pull-right">已卖出：<span>36斤</span></p>
							</div>
							<div class="mui-clearfix m-constance_down">
								<p class="u-address mui-pull-left">产地</p>
								<p class="u-address mui-pull-right">${p.productAddress}</p>
							</div>
						</li>

						<li class="mui-table-view-cell mui-collapse">
							<a class="u-waiter mui-navigate-right" href="#"><span>本品推荐调酒师介绍</span></a>
							<div class="mui-collapse-content">
								<div class="mui-button-row m-cheers mui-pull-left">
									<!--<img src="../img/cheers.png" />-->
									<img src="${p.expertImgUrl}" />
								</div>
								<div class="m-content mui-pull-right">
									<p class="u-content_name">${p.expertName}</p>
									<p class="u-content_introduce">${p.expertProfession}</p>
									<p class="u-content_introduce">国家一级白酒专家</p>
								</div>
							</div>
						</li>
						<li class="mui-table-view-cell">
							<p class="m-choice">请选择购酒数量</p>
							<p class="m-num_out"><span>2两</span><span>5两</span><span>1斤</span></p>
							<div class="mui-numbox" data-numbox-min='1' data-numbox-max='10'>
								<button class="mui-btn mui-btn-numbox-minus" type="button" id="minus">-</button>
								<div class="m-num_choice">
									<input id="true_num" class="mui-input-numbox" type="text" value="5" disabled="disabled" />
								</div>
								<button class="mui-btn mui-btn-numbox-plus" id="next" type="button">+</button>
							</div>
						</li>
					</ul>					
				</div>
				<nav class="g-mui_foot mui-bar mui-bar-tab">
					<button type="button" class="u-next_btn" id="sure_btn" data-productid = "${p.id}">结算<span id = "calculate">（26）</span></button>					
				</nav>
				</#list>
			</div>

		</div>
		<script src="${basePath}/weixinResources/js/mui.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/weixinResources/js/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/weixinResources/js/EminCommon.js" type="text/javascript" charset="utf-8"></script>
		<script src="${basePath}/weixinResources/js/detail.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">	
		
			var openId = "${openId}";
			
			mui('.mui-scroll-wrapper').scroll({
				deceleration: 0.0005 //flick 减速系数，系数越大，滚动速度越慢，滚动距离越小，默认值0.0006
			});
			
			var tempDetailData = {};
			
			var productId = Emin_getId('sure_btn').dataset.productid;	
			
			var price = parseFloat(Emin_getId('price').innerText);
			var amount = parseInt(Emin_getId('true_num').value) * price;
			var calculate = Emin_getId('calculate');
			calculate.innerHTML = "("+amount+")";
			var productInfo = {};
				/*获取json*/
			var productType = Emin_getId('productType').innerHTML;
			var productDegree = Emin_getId('productDegree').innerHTML;
			var product_name = Emin_getId('product_name').innerHTML;
			var product_url = Emin_getId('product_url').src;

			productInfo.productType = productType;
			productInfo.productDegree = productDegree;	
			productInfo.amount = amount;
			
			productInfo.productName = product_name;
			productInfo.product_url = product_url;
	
			mui('.mui-bar').on('tap', '#sure_btn', function(e) {
					tempDetailData.product_name = Emin_getId('product_name').innerHTML;
					tempDetailData.price = Emin_getId('price').innerText;
					tempDetailData.quentity = Emin_getId('true_num').value;
					tempDetailData.product_url = Emin_getId('product_url').src;
					localStorage.setItem('tempDetailData',JSON.stringify(tempDetailData));
					var count =  parseInt(Emin_getId('true_num').value);
					if(Emin_getId('true_num').value == '1斤') {
						count = 10;
					}
					productInfo.count =  count;
					amount = count * price;
					
					var obj = {
						openId:openId,
						productId: productId,
						amount:amount,
						price:price,
						count:parseInt(Emin_getId('true_num').value),
						productInfo:JSON.stringify(productInfo)
					}
				var str = "";
 
				for(var prop in obj){
				     str += prop + "=" + obj[prop] + "&"
				}
				
				window.location.href ='${basePath}/order/create.do?'+str+"&state=${woaId}";    
			});
				$('#next').on('tap',function(){					
					amount = (parseInt(Emin_getId('true_num').value)+1) * price;	
					calculate.innerHTML = "("+amount+")";
					if(Emin_getId('true_num').value == '1斤') {
						parseInt(Emin_getId('true_num').value) = 10;
						amount = parseInt(Emin_getId('true_num').value) * price;	
						calculate.innerHTML = "("+amount+")";
					}
				})
				$('#minus').on('tap',function(){
					if(Emin_getId('true_num').value == '1斤') {
						parseInt(Emin_getId('true_num').value) = 10;
						amount = (parseInt(Emin_getId('true_num').value)-1) * price;	
						calculate.innerHTML = "("+amount+")";
					}
					amount = (parseInt(Emin_getId('true_num').value)-1) * price;
					calculate.innerHTML = "("+amount+")";
				})
			var next_add = Emin_getId("next");
			var minus = Emin_getId("minus");

			mui('.mui-input-numbox').numbox(
				
				
				mui('.m-num_out').on('tap', 'span', function(e) {
					
					
					Emin_getId('true_num').value = this.innerHTML;
					
					minus.disabled = false;
					amount = parseInt(Emin_getId('true_num').value) * price;
					calculate.innerHTML = "("+amount+")";
					if(Emin_getId('true_num').value == '1斤') {						
						amount = 10 * price;							
						calculate.innerHTML = "("+amount+")";
						next_add.disabled = true;
						
					} else {
						next_add.disabled = false;
					}
				
					
					e.stopPropagation();
					e.preventDefault();
				})
				
			);
		</script>
	</body>

</html>