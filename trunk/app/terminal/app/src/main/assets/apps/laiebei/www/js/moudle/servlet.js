/***************页面跳转 控制*******************/
/*******
 * 跳转页面
 * @param {Object} pageName 页面名称 
 */
function toPage(pageName){
	var content = document.getElementsByClassName("main");
	for(var i=0;i<content.length;i++){
		var cur = content[i];
		if(cur.getAttribute("id")==pageName&&cur.className.indexOf("hide")>=0){
			cur.setAttribute("class",cur.className.replace(" hide"," animated fadeIn"))
            setTimeout(function(){
                cur.setAttribute("class",cur.className.replace(" animated fadeIn",""));
            },1000);
		}else{
			if(cur.className.indexOf("hide")<0)
			cur.setAttribute("class",cur.className+" hide");
		}
	}

}

/****
 * 返回主页
 */
function backToIndex(){
	controlInit();
	$('.put-code').attr('phone',0)
	toPage("index-moudle");
}

/*****
 * 打开二维码收银台
 */
function openSettlementPopup(type,isEnable){
	if(isEnable){
		var titleEl = document.querySelectorAll("#scanCounter-popUp .pu-bar>span");
		if(!type){
			titleEl[0].innerHTML = "订单结算";
	    	loadPayQrCode();
		}else{
			titleEl[0].innerHTML = "先尝后买";
			loadTastingQrCode();
		}
	    var scanPopUp = document.getElementById("scanCounter-popUp");
	    scanPopUp.setAttribute("class",scanPopUp.className.replace("hide",""));
	    (document.querySelectorAll("#pay-code-info .pay-code"))[0].innerHTML = '<div class="loader-inner ball-spin-fade-loader">'+
	      '<div></div>'+
	      '<div></div>'+
	      '<div></div>'+
	      '<div></div>'+
	      '<div></div>'+
	      '<div></div>'+
	      '<div></div>'+
	      '<div></div>'+
	    '</div>';
	
		var operEl = document.getElementById("pay-tip");
		if(operEl.className.indexOf(" red-color")>=0){
	        operEl.setAttribute("class",operEl.className.replace(" red-color",""));
	        operEl.setAttribute("class",operEl.className+" blue-color");
	    }
	    operEl.innerHTML = "请使用微信支付 ...秒";
	    var payMoneyEl = document.querySelectorAll("#pay-code-info .pay-money");
	    payMoneyEl[0].innerHTML = "...";
	    payMoneyEl[1].innerHTML = "...";
	}else{
		keyStatus = 0;
		showAlert('该机器不可购买当前酒品')
	}
	
}
/***
 * 二维码支付成功，显示打酒
 */
function loadServiceResult(){
    /*var scanPopUp = document.getElementById("scanCounter-popUp");
    scanPopUp.setAttribute("class",scanPopUp.className+" hide");*/
    toPage('service-result-moudle');
}

/*****
 * 打酒成功，完成服务
 */
function completeService(){
	//隐藏加载中
    var operEl = document.getElementById("service-loading");
    if(operEl.className.indexOf("hide")<0){
        operEl.setAttribute("class",operEl.className+" hide");
    }
    //显示出酒成功
    operEl = document.getElementById("service-success");
    if(operEl.className.indexOf("hide")>=0){
        operEl.setAttribute("class",operEl.className.replace("hide",""));
    }
	setTimeout(function(){
		console.log('返回倒计时')
		backToIndex();//3秒后自动返回首页
	},3000);
}


/********
 * 跳转至酒品详情页
 * @param {Object} self
 */
function toWinDetail(self){
	var winLstIndex = self.getAttribute("win-lst-index");
	loadWinDetail(globalWinLst[winLstIndex]);//加载酒品详情
	toPage('win-detail-moudle');
}
/*进入服务员登录页*/
function waiterLogin(){
	var url = 'http://igwmp.linebuy.com.cn/wxservant/loginMachine.htm';
	toPage('waiter-login-moudle');
	$('#waiterLoginName').text('请输入手机号码')
	getDeviceQrcode(url);
	$('.put-code').attr('phone','0')
	
}

/*****
 * 显示代打酒列表
 */
function showServiceList(){
	var waiter = document.getElementById("waiterLoginName").innerText;
		//waiter = 18982282624;
		var reg = /^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|(147))\d{8}$/;
		///^1[34578]\d{9}$/
	if(!(reg.test(waiter))){ 
		$(".alert-msg").removeClass('hide')
//      showAlert('手机号码错误，请重新输入！') 
//      keyStatus = 2;
   }else{
   		$("#loading-wait").removeClass('hide');
   		setTimeout(hideLoadingWait,20000);
		loadReplaces(waiter);
		
    }
    hideKeyBord();
}
/*从代打酒列表输入打酒码*/
function putCode(){
	showKeybord(0,'outWin()');
}

/*分页函数*/
function doListPage(pagerow,pagenum,rowcount,pagecount,preBtn,nxetBtn,d){
	//参数依次为每一页显示的数目，当前页，数据总数，总页数，上一页，下一页，需要分页的数据
    showPage(pagerow,pagenum,rowcount);
    	preBtn.attr("disabled", true);
   	preBtn.click(function(){
    	nxetBtn.removeAttr("disabled");
    	if((pagenum-1)<=1){
        	pagenum = 1;
        	preBtn.attr("disabled", true);
    	}else{
        	pagenum -= 1;
        	preBtn.removeAttr("disabled");
      } 
      showPage(pagerow,pagenum,rowcount)
     });
     
    nxetBtn.click(function(){
    	preBtn.removeAttr("disabled");
    	if ((pagenum + 1) >= pagecount){
        	pagenum=pagecount;
        	nxetBtn.attr("disabled", true);
    	}else{
        	pagenum += 1;
        	nxetBtn.removeAttr("disabled");
      	}
      	showPage(pagerow,pagenum,rowcount)
    });
    
    function showPage(pagerow,pagenum,rowcount){
    	var temp='',
        	html='';
      	for(var i=0;i<pagerow;i++){
        	temp=(pagenum-1)*pagerow+i;
        	if(temp<rowcount){
        		if(d[temp].enable){
        			html+="<dl><dd>"+(temp+1)+"</dd><dd>"+d[temp].name+"</dd><dd>"+d[temp].quantity+"两</dd><dd>"+d[temp].table+"</dd><dd><div class='btn red-bg' code='"+d[temp].code+"' onclick='takeWine("+d[temp].code+",0,0)'>打酒</div></dd></dl>"
        		}else{
        			html+="<dl><dd>"+(temp+1)+"</dd><dd>"+d[temp].name+"</dd><dd>"+d[temp].quantity+"两</dd><dd>"+d[temp].table+"</dd><dd><div class='btn '  onclick='unableTakeWine()'>打酒</div></dd></dl>"
        		}
          		
        	}
      	}
      	serviceDataList.html(html)
      	if(pagecount>1){
	    	if(pagenum>1&&pagenum<pagecount){
		    	$('#service-lst-pre-btn').addClass('bright');
		    	$('#service-lst-next-btn').addClass('bright');
		    }else if(pagenum==1){
		    	$('#service-lst-pre-btn').removeClass('bright');
		    	$('#service-lst-next-btn').addClass('bright');
	    	}else if(pagenum==pagecount){
	    		$('#service-lst-pre-btn').addClass('bright');
		    	$('#service-lst-next-btn').removeClass('bright');
	    	}
	    }
    }
}

function showWinLst(){
	loadWins();
}
//取酒
function outWin(){
	var code = document.getElementById("keybord-input").innerText,
		waiter = $('.put-code').attr('phone')
		num=1;
	if(code=="请输入6位取酒码" || code==''||code.length<6){
		keyStatus = 1;
		showAlert('请输入6位取酒码')
	}else{
		$("#loading-wait").removeClass('hide');
		setTimeout(hideLoadingWait,20000);
		takeWine(code,1,waiter);
	}
	hideKeyBord();
}
/*****
 * 支付倒计时
 */
var initCountDown = 60;
function countDownPay(type){
	var operEl = document.getElementById("pay-tip");
	initCountDown--;
	if(initCountDown<=0){
	    operEl.innerHTML = "二维码已过期，点击刷新";
	    operEl.setAttribute("class",operEl.className.replace(" blue-color",""));
	    operEl.setAttribute("class",operEl.className+" red-color");
		operEl.onclick = function(){
			openSettlementPopup(type,true);
			operEl.click(function(){});
		};
		clearInterval(payInterval);
		return false;
	}
    operEl.innerHTML = "请使用微信支付 "+initCountDown+"秒";
}
/*****
 * 取消支付
 */
function cancelPay(){
	clearInterval(payInterval);
	var scanPopUp = document.getElementById("scanCounter-popUp");
	scanPopUp.setAttribute("class",scanPopUp.className+" hide");
}

/********
 * 支付成功
 */
function afterPay(){	
	clearInterval(payInterval);
	loadServiceResult();
}
/*提示用户，在当前用户不可取酒*/
function unableTakeWine(){
	keyStatus=0
	showAlert('此种酒不可在当前酒柜取，请更换酒柜！')
	setTimeout(function(){
		hideAlert()
	},3000)
};
/*去设置页面*/
function toSetUpMoudle(){
	toPage('set-up-moudle');
	screenInit()
}
/*错误信息提示方法*/
function errorAlert(code,backToIndex){
	//code：错误码  backToIndex：返回首页，值为false或者true
	console.log("回调错误:" + code+backToIndex);
	if(backToIndex){
		/*keyStatus=3*/
	}
	switch(code){
		case PARAM_ERR:
		case PARAM_OVERFLOW:
		case PARAM_UNDERFLOW:
		case RULE_BREAK:
			showAlert("请输入正确的参数");
			break;
		case NOT_EXIST:
			showAlert("数据不存在");
			break;
		case VERIFY_ERR:
			showAlert("数据校验错误");
			break;
		case TIMEOUT:
			showAlert("订单下发超时");
			break;
		case MACHINE_SERVER:
		case ORDER_SERVER:
		case PRODUCT_SERVER:
		case MACHINE_NO_CONFIG:
			showAlert("没有该产品");
			break;
		case PRICE_SERVER:
			showAlert("错误的购买价格");
			break;
		case PAY_SERVER:
			showAlert("付款错误，请稍后再试");
			break;
		case TAKE_CODE_ERROR:
		case TAKE_SERVER:
			showAlert("取酒码错误");
			break;
		case MACHINE_NOT_CHANNEL:
			showAlert("机器没有配置任何酒品，请联系来e杯");
			break;
		case CHANNEL_WINE_NO_ENOUGH:
			showAlert("该酒在当前机器上酒量不足，请更换购买或联系来e杯");
			break;
		case WAITER_NOT_EXIST:
			showAlert("您没有注册为我们的服务人员，请先进行注册");
			break;
		case WAITER_NOT_LIST:
			showAlert("您名下没有可代取就酒，请进一步确认");
			break;
		case NOT_NETWORK:
		case CLIENT_BREAK:
		case CLIENT_TIMEOUT:
		case CLIENT_COMMUNION:
			showAlert("网络连接错误,请联系来e杯");
			break;
		default:
			showAlert("未知错误");
			break;
	}
}
function hideLoadingWait(){
	$("#loading-wait").addClass('hide');
}

