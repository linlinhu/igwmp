<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="${basePath}/weixinResources/css/mui.min.css">
		<title>服务员平台</title>
		<style>
			body {
				height: 100vh;
				width: 100vw;
				padding: 0;
				margin: 0;
				background-image: url(${basePath}/weixinResources/img/background1.png);
				background-size: cover;
				background-repeat: no-repeat;
				font-family: "微软雅黑";
			}
			
			.logo {
				position: absolute;
				width: 30vw;
				top: 8%;
				left: 35%;
			}
			
			.input_box {
				position: absolute;
				width: 90%;
				margin: 5%;
				top: 26%;
			}
			
			.input_box .input_title {
				color: #515151;
				margin-bottom: 1vh;
			}
			
			#loginBtn {
				width: 100%;
				background: #E94709;
				border: 1px solid #E94709;
				line-height: 30px;
				font-weight: bold;
				-webkit-user-modify: read-write-plaintext-only;
				color: #FDFAF9;
			}
			
			#applyBtn {
				width: 100%;
				background: transparent;
				border: 2px solid #E94709;
				line-height: 30px;
				font-weight: bold;
				-webkit-user-modify: read-write-plaintext-only;
				color: #E94709;
			}
			
			.g-grid {
				background: #1C1C1C;
				opacity: .8;
				height: 100vh;
				width: 100vw;
				position: fixed;
				left: 0;
				top: 0;
				z-index: 666;
				/*display: none;*/
			}
			
			.g-grid_window {
				width: 90vw;
				height: 64vh;
				position: fixed;
				left: 5vw;
				top: 20vh;
				z-index: 999;
				background: #FFFBF2;
				/*display: none;*/
			}
			
			.g-grid_window img {
				position: absolute;
				right: 0;
			}
			
			.g-grid_window input {
				height: 5vh;
			}
			
			ul li {
				margin-top: 2vh;
			}
			
			ul li:nth-of-type(1) {
				margin-top: 0;
			}
			
			ul li:nth-of-type(2) {
				margin-top: 0;
			}
			
			ul li input[type=text] {
				height: 6vh;
				border: 1px solid #e9e9e9;
			}
			
			.m-title {
				height: 5vh;
				line-height: 5vh;
				font-size: 1rem;
			}
			
			.send_btn {
				height: 5vh;
				background: #E94709;
				text-align: center;
				font-size: .8rem;
				color: #fff;
			}
			
			ul:nth-of-type(1) {
				margin-top: 6vh;
			}
			
			.u-submit {
				height: 6vh;
				background: #F15D53;
				color: #F8C0BD;
				position: absolute;
				bottom: 0;
				left: 0;
			}
			#grid,#grid_window{
				display:none;
			}
		</style>
	</head>

	<body>
		<div class="g-grid" id="grid"></div>
		<div class="g-grid_window" id="grid_window">
			<a id="close_btn"><img src="${basePath}/weixinResources/img/close.png" /></a>
			<ul class="mui-table-view mui-row ">
				<li class="mui-col-xs-3 mui-text-center m-title">真实姓名</li>
				<li class="mui-col-xs-8 ">
					<input type="text" name="name" id="name" value="" />
				</li>
				<li class="mui-col-xs-1 mui-text-center m-title"></li>

				<li class="mui-col-xs-3 mui-text-center m-title">绑定饭店</li>
				<li class="mui-col-xs-8 ">
					<input type="text" name="name" id="name" value="" />
				</li>
				<li class="mui-col-xs-1 mui-text-center m-title"></li>

				<li class="mui-col-xs-3 mui-text-center m-title">手机号码</li>
				<li class="mui-col-xs-8">
					<input type="text" name="phone" class="phone" id="cellphone_register" />
				</li>
				<li class="mui-col-xs-1 mui-text-center m-title"></li>

				<li class="mui-col-xs-3 mui-text-center m-title">验证码</li>
				<li class="mui-col-xs-4">
					<input type="text" id="validateCode_register" name="validate" class="validate" value="" />
				</li>
				<!--<li class="mui-col-xs-1 mui-text-center m-title"></li>
				<li class="m-title mui-col-xs-3"><button class=" mui-text-center mui-btn mui-btn-blue " >点击发送</button></li>-->

				<li class="mui-col-xs-4" style="margin-left: 4vw;"><button class=" mui-btn send_btn" id="sendCodeRegister">获取验证码</button></li>
			</ul>
			<button type="submit" id="register" class="mui-col-xs-12 u-submit">注册</button>
		</div>
		<div class="mui-content">
			<img class="logo" src="${basePath}/weixinResources/img/e_mark1.png" />
			<div class="input_box " style="border-radius:4px;">

				<ul class="mui-table-view mui-row m-validate">
					<li class="mui-col-xs-3 mui-text-center m-title">手机号码</li>
					<li class="mui-col-xs-8">
						<input type="text" name="phone" id="cellphone_login" class="phone" value="" />
					</li>
					<li class="mui-col-xs-3 mui-text-center m-title">验证码</li>
					<li class="mui-col-xs-4">
						<input type="text" name="validate" id="validateCode_login" class="validate" value="" />
					</li>
					<li class="mui-col-xs-4" style="margin-left: 6vw;"><button class="mui-btn send_btn" id="sendCodeLogin">获取验证码1</button></li>
				</ul>

			</div>
			<div class="input_box" style="border-radius:4px;top:70% ;">
				<input type="submit" value="登录" id="loginBtn" />
			</div>
			<div class="input_box" style="border-radius:4px;top:80% ;">
				<input type="submit" value="服务员账号申请" id="applyBtn" />
			</div>

		</div>
		<script type="text/javascript" src="${basePath}/weixinResources/js/jquery-2.1.4.min.js"></script>
		<script type="text/javascript" src="${basePath}/weixinResources/js/mui.js"></script>
		<script type="text/javascript" src="${basePath}/weixinResources/js/waiter/isServant.js"></script>
		<script src="${basePath}/weixinResources/js/jquery.cookie.js "></script>
		<script src="${basePath}/weixinResources/js/PluginValidate.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		

		    var openId='${openId}';
		    var state='${woaId}';	//公众号Id
		    var basePath='${basePath}';	//公众号Id
	   		var url = "${basePath}/wxservant/center.htm?openId="+openId+"&state="+state; //跳转到用户中心

	   		//判断用户是否是服务员,如果是，择自动登录
	/*   		mui.ready(function(){
	   			isServant(openId,url,basePath);
	   		})
	   		*/

			function closeRegisterWindow(){
				$('#grid').hide();
				$('#grid_window').hide();
			}
			function openRegisterWindow(){
				$('#grid').show();
				$('#grid_window').show();
			}
			$('#close_btn').on('tap', function() {
				closeRegisterWindow()
			})
			$('#applyBtn').on('tap', function() {
				openRegisterWindow()
			})
			
			

			/*****
			*	获取验证码方法
			* register: 0为注册 1为登录
			*/
			function getVerifyCode(cellphone,register){
				var flag = false;
				if(register==0){
					flag=true;
				}else{
					flag=false;
				}
				var url="${basePath}/wxservant/sendVerifyCode.do"
				mui.ajax({
					url:url,
					data:{
						phoneNum:document.querySelector("#cellphone").value,
						isRegister:flag
					},
					dataType:"json",
					success:function(data){
						if(data.success){
							
						}
					}
				})
			}


			/***************************************************注册提交***********************************************/
			//注册提交
			document.querySelector("#register").addEventListener("tap",function(){
			var url="${basePath}/wxservant/register.do"
				mui.ajax({
					url:url,
					data:{
						verifyCode:document.querySelector("#validateCode_register").value,
    		            phoneNum:document.querySelector("#cellphone_register").value,
    		            restaurantId:102,
    		            name:document.querySelector("#name").value;
					},
					dataType:"json",
					type:'post',
					success:function(data){
						if(data.success){
							alert(data.message)
							closeRegisterWindow()
						}else{
							alert(data.message)
						}
					},error:function(){
						alert("网络错误")
					}
				})
			})
			//注册手机验证码
			document.querySelector("#sendCodeRegister").addEventListener("tap",function(){
				var phoneNum=document.querySelector("#cellphone_register").value;
				getVerifyCode(phoneNum,0)
			})



		/***************************************************登录***********************************************/				
		//登录
		document.querySelector("#loginBtn").addEventListener("tap",function(){
			var url="${basePath}/wxservant/login.do"
				mui.ajax({
					url:url,
					data:{
						verifyCode:document.querySelector("#validateCode_login").value,
    		           	phoneNum:document.querySelector("#cellphone_login").value
					},
					dataType:"json",
					type:'post',
					success:function(data){
						if(data.success){
							window.location.href="${basePath}/wxservant/center.htm?state="+${woaId};
							closeRegisterWindow();
						}else{
							alert(data.message);
						}
					},error:function(){
						alert("网络错误")
					}
				})
			})
			
			//登录------手机验证码
			document.querySelector("#sendCodeLogin").addEventListener("tap",function(){
				var cellphone = document.querySelector("#cellphone_login").value;
				getVerifyCode(cellphone,1)
			})
			
		</script>
	</body>

</html>