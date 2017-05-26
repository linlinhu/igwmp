/***************数据加载 控制*******************/

/****
 * 加载广告
 */
var _box=$("#scrollAds"),
	_pages = $('#lunbopages'),
	_ul=$("ul",_box)[0],
	lunboLen='',
	video = '', 
	circles = '',
	lis = '',
	lunboindex=1,
	nextIndex=2,
	timer="";
	imgWidth=_box.width();
var lunboEvent = document.createEvent("MouseEvents");
	lunboEvent.initEvent("click", false, false);
function loadAds(){
   plus.adv.getAwaitUrls(function(result){
		var r = JSON.parse(result[0]);
		if(r.success){
			var html = "",
				adData = r.rows;
			lunboLen = r.total;
			_ul.innerHTML = "";
			_pages.css({width:lunboLen*30});
			for(var i=0;i<lunboLen;i++){
				if(adData[i].type =='1'){
				
					html+=' <li type="'+adData[i].type+'" times="'+adData[i].times+'"><video class="videoYX" poster="img/ad-default.png" src="'+adData[i].url+'" onended = "videoEnd()" preload="auto"></video></li>'
				}else{
				
					html+='<li type="'+adData[i].type+'" times="'+adData[i].times+'"><img data-src="'+adData[i].url+'" src = "img/ad-default.png" class="lazy"/></li>'
				}
			};
			_ul.innerHTML = html;
			cacheImg(_ul);
			var _div='';
		 	for(var i=0;i<lunboLen;i++){
		 		_div+='<div></div>';
		 	}
		 	_pages.html(_div);
		 	circles=$("div",_pages); 
		 	lis=$("li",_box);
		 	var _first=lis[0].cloneNode(true),
		 		_last=lis[lunboLen-1].cloneNode(true);
		 	_ul.appendChild(_first);
		 	_ul.insertBefore(_last,lis[0]);
		 	lunboLen+=2;
		 	lis=$("li",_box);
		 	lunboindex=1;
		 	nextIndex=2;
		 	_ul.style.width=lunboLen*imgWidth+"px";
		 	circles[0].style.background="rgba(93,160,119,0.6)";
		 	_ul.style.left=-imgWidth+"px";
		 	/*timer=setInterval(lunboMove,10000);*/
		 	setTimeout(lunboMove,5000);
		};
		/*cacheImg(_ul);*/
	},function(result){
		keyStatus=0;
		showAlert("获取广告数据失败");
	});
}
function loadWins(){
    var bindEl = document.getElementById('winLst');
    winLstWrapEl.setAttribute('style', 'margin-left: 0px');
	bindEl.innerHTML = "";
	plus.master.Wines(function(result){
		var r = JSON.parse(result[0]);
		if(r.success){
			toPage('win-lst-moudle');
		    globalWinLst = r.rows;
		    var html = '';
//		    alert(globalWinLst.length);
		    for(var i=0;i<r.total;i++){
                var d = globalWinLst[i],
                	imageUrl = d.imgList;
                html+='<div class="win">'+
                    '<i class="winpic" win-lst-index="'+i+'" onclick="toWinDetail(this)">'+
                    "<img data-src = '"+imageUrl+"' src='img/win-list-default.png' class='lazy'/></i>"+
                    '<div class="mid" win-lst-index="'+i+'" onclick="toWinDetail(this)">'+
                        '<div class="name f22">'+d.name+'</div>'+
                        '<div class="memo">'+d.describe+'</div>'+
                        '<div class="price f22 m-color">￥'+d.sellingPrice+'</div>'+
                    '</div>'+
                    '<div class="others">'+
                        '<span class="left visitor">门市价：￥'+d.originalPrice+'</span>'+
                        '<span class="right">已卖出：<span class="m-color">'+d.sales+'两</span></span>'+
                    '</div>'+
                '</div>';
           }
		    bindEl.innerHTML = html;
		    cacheImg(bindEl);
		}else{
			errorAlert(r.code,true);
		}
	}, function(result){
		var r = JSON.parse(result[0]),
			code = r.code;
		errorAlert(code,true);
	});
}


/*****
 * 加载酒精详情
 * @param {Object} win
 */
function loadWinDetail(win){
	var winId = win.id,
	    winName = win.name,
	    winImg = win.imgDetails,
	    winSellPrice = win.sellingPrice,
	    winOrgPrice = win.originalPrice,
	    winSales = win.sales,
	    winFlavor = win.flavor,
	    winDegress = win.degress,
	    winProduct = win.product,
	    winRemainder = win.remainder,
	    winChannel = win.channel,
	    isEnable = win.enable;
    var wdMoule = document.getElementById("win-detail-moudle");
    wdMoule.setAttribute("data-id",winId);
    wdMoule.setAttribute("win-channel",winChannel);
    wdMoule.setAttribute("data-price",winSellPrice);
	(document.querySelectorAll("#win-detail-moudle>.info"))[0].innerHTML = '<i class=" win-detail">'+
							"<img data-src = '"+winImg+"' src='img/win-detail-default.png' class='lazy'/></i>"+
                       	'<div class="ln50">'+
                       		'<span class="f22">'+winName+'</span>'+
                       		'<span class="f22 m-color">￥'+winSellPrice+'</span>'+
                       		'<span class="visitor">门市价：￥'+winOrgPrice+'</span>'+
                       		'<span>已卖出：<span class="m-color">'+winSales+'两</span></span>'+
                       		'<span>剩余量：<span class="m-color">'+winRemainder+'两</span></span>'+
                       	'</div>'+
                       	'<div class="ln30">'+
                       		'<span>香型：'+winFlavor+'</span>'+
                       		'<span>酒精度：'+winDegress+'度</span>'+
                       		'<span>产地：'+winProduct+'</span>'+
                       	'</div>';
    document.getElementById("pay-confirm-info").innerHTML = '<div class="bot-line">'+
                            '<div class="f22">'+winName+'</div>'+
                            '<div class="ln50"><span style="float: left;">'+winDegress+'度</span><span style="float: right;">'+winFlavor+'</span></div>'+
                            '<div class="f22" style="text-align: right;border-bottom: 2px solid rgb(169,198,255);">￥'+winSellPrice+'元 * <span class="pay-count">1</span>两</div>'+
                            '<div class="f22 red-color" style="text-align: right;">实付款：<span class="pay-money">0.1</span>元</div>'+
                        '</div>';
    if(isEnable){
    	document.getElementById('win-status').innerHTML ='<div class="btn m-bg ln50" onclick="openSettlementPopup(0,'+isEnable+')" >结算</div>'+
    							'<div class="title ln30">品尝</div>'+
    							'<div class="btn red-bg ln50" onclick="openSettlementPopup(1,'+isEnable+')">先尝后买</div>'
    }else{
    	document.getElementById('win-status').innerHTML ='<div class="btn g-bg ln50" onclick="openSettlementPopup(0,'+isEnable+')" >结算</div>'+
    							'<div class="title ln30">品尝</div>'+
    							'<div class="btn g-bg ln50" onclick="openSettlementPopup(1,'+isEnable+')">先尝后买</div>'
    };
    cacheImg(wdMoule)
}
/*获取服务员登录二维码*/
var login_qrcode_url = '';
function getDeviceQrcode(url){
	$('.login-qrcode').html('');
	if(login_qrcode_url!=''){
		$('.login-qrcode').qrcode({
				render : "canvas",
				width: 148,
				height: 148,
				text:login_qrcode_url
		});
	}
	plus.devices.getQrcode(function(result){
		r = JSON.parse(result[0]);
		if(r.success){
			if(login_qrcode_url!=r.rows.url){
				$('.login-qrcode').html('');
				login_qrcode_url=r.rows.url;
				$('.login-qrcode').qrcode({
					render : "canvas",
					width: 148,
					height: 148,
					text: login_qrcode_url
				});
			}
			waiterListener();
		}
	},function(result){
		var r = JSON.parse(result[0]),
			code = r.code;
		errorAlert(code,false);
	},url);
}

function ReplaceEntry(record){
	var r = JSON.parse(record);
	if(r.success){
		hideLoadingWait();
		if(r.hasOwnProperty("phone")){
			$('.put-code').attr('phone',r.phone)
		}else{
			$('.put-code').attr('phone',0)
		}
		index = 0;
		var d = r.rows,
			len = r.total,
			rowcount = len,//数据总数
			pagerow = 5,//每一页显示的数目
	        pagenum = 1,//当前页
	        pagecount = Math.ceil(rowcount/pagerow);//总页数
			html = '',
			serviceDataList = $('#service-data-lst'),
			preBtn = $("#service-lst-pre-btn"),//上一页
			nxetBtn = $("#service-lst-next-btn");//下一页
		serviceDataList.html('');
		if(len>0){
			doListPage(pagerow,pagenum,rowcount,pagecount,preBtn,nxetBtn,d)
		}else{
			serviceDataList.append('<dl><dd>暂无数据</dd></dl>');
			$('.page-oper').addClass('hide');
		}
		toPage('service-lst-moudle');
	}else{
		hideLoadingWait();
		errorAlert(r.code,false);
	}
}

/*
 代打酒
 */
function loadReplaces(waiter){
	waiterListener();
	plus.master.Replaces(function(result){
		//统一使用监听进入服务员列表，实现扫码登录无法回调
	}, function(result){
		hideLoadingWait();
		var r = JSON.parse(result[0]),
			code = r.code;
		errorAlert(code,false);
	},waiter);
}

function waiterListener(){
	plus.waiter.createJob(function(result){
		console.log("准备进入服务员列表" + result);
		ReplaceEntry(result);
		plus.waiter.cancelJob();//注销监听
	});
}

/********
 * 加载支付二维码及支付信息
 */
function loadPayQrCode(){
    var wdMoule = document.getElementById("win-detail-moudle");
	var winId = wdMoule.getAttribute("data-id");
	var winChannel = wdMoule.getAttribute("win-channel");
	var winPrice = wdMoule.getAttribute("data-price");
	var winCount = document.getElementById("win-buy-count").getAttribute("win-count");
	(document.querySelectorAll("#pay-confirm-info .pay-count"))[0].innerHTML = winCount;
	plus.master.Buy(function(result){
		var r = JSON.parse(result[0]);
		if(r.success){
			var d = r.rows;
			var payMoneyEl = document.querySelectorAll("#pay-code-info .pay-money");
			payMoneyEl[0].innerHTML = d.pay;
            payMoneyEl[1].innerHTML = d.pay;
			(document.querySelectorAll("#pay-code-info .pay-code"))[0].innerHTML = "";

			$("div.pay-code").qrcode({ 
			    render : "canvas", //canvas方式 
			    width: $("div.pay-code").width(), //宽度 
			    height:$("div.pay-code").height(), //高度 
			    text: d.payQr
			}); 
			initCountDown = 60;
			payInterval = setInterval("countDownPay(0)",1000);
			payListener();//支付监听
		}
	}, function(result){
		var r = JSON.parse(result[0]),
			code = r.code;
		errorAlert(code,false);
	},winId,winCount,winPrice,winChannel);

}
/****
 * 支付监听
 */
function payListener(){
	plus.order.createJob(function(result){
		var r = JSON.parse(result);
		console.log("接收到订单，注册酒柜监听" + r.success);
		if(r.success){
			var scanPopUp = document.getElementById("scanCounter-popUp");
   			scanPopUp.setAttribute("class",scanPopUp.className+" hide");   
			
		}else{
			takeWinListenerCancel();
			errorAlert(r.code,false); 
		}
		plus.order.cancelJob();//注销监听
	});
	takeWinListener();
	
}
/*******
 * 加载先品后买二维码
 */
function loadTastingQrCode(){
    var wdMoule = document.getElementById("win-detail-moudle");
	var winId = wdMoule.getAttribute("data-id");
	var winChannel = wdMoule.getAttribute("win-channel");
	(document.querySelectorAll("#pay-confirm-info .pay-count"))[0].innerHTML = "...";
	plus.master.Tasting(function(result){
		var r = JSON.parse(result[0]);
		if(r.success){
			var d = r.rows;
			var payMoneyEl = document.querySelectorAll("#pay-code-info .pay-money");
			payMoneyEl[0].innerHTML = d.pay;
            payMoneyEl[1].innerHTML = d.pay;
            (document.querySelectorAll("#pay-code-info .pay-code"))[0].innerHTML = "";
			$("div.pay-code").qrcode({ 
			    render : "canvas", //canvas方式 
			    width: $("div.pay-code").width(), //宽度 $("div.pay-code").width()
			    height:$("div.pay-code").height(), //高度 $("div.pay-code").height()
			    text: d.payQr
			}); 
			initCountDown = 60;
			payInterval = setInterval("countDownPay(1)",1000);
			console.log("注册支付监听");
			payListener();
		}
	}, function(result){
		var r = JSON.parse(result[0]),
			code = r.code;
		errorAlert(code,false);
	},winId,winChannel);
}

/*取酒请求*/
//num：1 表示散客打酒，num：0 表示服务员打酒
function takeWine(code,num,waiter){
	console.log("取酒码:" + code + "  nulmber:" + num+"服务员:" + waiter);
	plus.master.Take(function(result){
		hideLoadingWait();
		var r = JSON.parse(result[0]);
		if(r.success){
			takeWinListener();
		}else{
			code = r.code;
			errorAlert(code,false);
			keyStatus=num;
		}
		hideKeyBord();
	}, function(result){
		hideLoadingWait();
		var r = JSON.parse(result[0]),
			code = r.code;
		errorAlert(code,false);
		},code,waiter);
}
/*出酒监听 */
var takeWinstatus = false;

function takeWinListenerCancel(){
	plus.Cabinet.cancelJob();
}

function takeWinListener(){
	keyStatus = 0;
	plus.Cabinet.createJob(function(result){
		var r = JSON.parse(result);
		console.log("出酒返回信息" + result);
		if(r.success){
		    var status = r.rows.status,
			resultCode =r.rows.resultCode;
			switch(status){
				case 1:
				takeWinstatus = false;
				showAlert('出酒准备,请放置取酒器');
				$("#alert-form .close").addClass('hide');
				break;
				case 2:
				if(!takeWinstatus){
					hideAlert();
					$("#alert-form .close").removeClass('hide');
					serviceWinOut();
					takeWinstatus = true;
				}
				break;
				case 3:
				completeService();
				takeWinstatus = false;
				plus.Cabinet.cancelJob()
				break;
				case 4:
				$("#alert-form .close").removeClass('hide');
				switch(resultCode){
					case CABINET_CONFIG_ERR:
					showAlert('机器配置错误');
					break;
					case CABINET_MOUDLE_ERR:
					showAlert('模式错误');
					break;
					case CABINET_WAY_ERR:
					showAlert('通道号越界');
					break;
					case CABINET_VALUE_ERR:
					showAlert('出酒量越界');
					break;
					case CABINET_MACHINE_ERR:
					showAlert('设备异常，请联系来e杯');
					break;
					case CABINET_SOLD_OUT:
					showAlert('该酒品售空，请联系来e杯');
					break;
					case CABINET_CUP_OUT:
					showAlert('出酒中杯子被拿出');
					break;
					case CABINET_TIMEOUT:
					showAlert('您太长时间没有放入杯子，请检查');
					break;
					default:
					showAlert('未知错误，请联系来e杯');
					break;
				};
				plus.Cabinet.cancelJob();
				toPage("error-moudle");
				break;
				default:
				;
			}
		}else{
			var code = r.rows.resultCode;
			console.log("出酒错误码:" + code);
			switch(code){
				case CABINET_REPEAT:
				showAlert('该订单已经出过酒，如有疑问，请联系来e杯');
				break;
				case CABINET_OVERFLOW:
				showAlert('该订单出酒量错误，如有疑问，请联系来e杯');
				serviceWinOut();
				break;
				case CABINET_NOT_DEVICE:
				showAlert('本机器处于不可销售状态，如有疑问，请联系来e杯');
				break;
				showAlert('未知错误，请联系来e杯');
				default:
				;
			}
			plus.Cabinet.cancelJob();
			toPage("error-moudle");
		}
		
	});
}

/*
 * 出酒信息
 * 说明：通知出酒  测试使用
 * orderId：订单号  测试随意 
 * channel：通道号  1-6
 * value：出酒量  ml 20-2000
 * */
function vendout(orderId,channel, value){
	takeWinListener();
	plus.devices.vendout(function(result){
		
	},function(result){

	},orderId,channel,value);
	
}
