/****
 * 键盘初始化事件
 */
var defaultStr = $("#textBox").attr("default");
var inputContent = $("#textBox").html();
$(function(){
	var clicked = 0;
	if(inputContent.indexOf(defaultStr)>=0){
		inputContent = "";
		wordWidth = 13;
		index=0;
	}else{
		textWidth = $("#textBox").innerWidth();//文本的总宽度
		textLen = inputContent.length;//
		wordWidth = textWidth/textLen;
		index=inputContent.length;
	}
	/*****
	 * 键盘输入事件
	 */
	$("#keybord-pop-up dl.key>dd").click(function(){
		/*if(clicked==0){*/
			clicked = 1;
			defaultStr = $("#textBox").attr("default");
			inputContent = $("#textBox").html();
			textWidth = $("#textBox").innerWidth();//文本的总宽度
			textLen = inputContent.length;//
			wordWidth = textWidth/textLen?textWidth/textLen:13;
			if(inputContent.indexOf(defaultStr)>=0){
				inputContent = "";
				wordWidth = 13;
				index=0;
			}
			if(inputContent != ''){
				inputContent = $("#textBox").html();
			}
			if(defaultStr=='请输入6位取酒码'){
				if(index<6&&inputContent.length<6){
					index += 1;
					textLen = inputContent.length;//
					inputContent=inputContent.slice(0, index-1) + $(this).text() + inputContent.slice(index-1,textLen);
					stick.style.left = index*wordWidth+$("#keybord-input").position().left +10+'px';
					$("#textBox").html(inputContent);
				}
			}else{
				if(index<11&&inputContent.length<11){
					index += 1;
					textLen = inputContent.length;//
					inputContent=inputContent.slice(0, index-1) + $(this).text() + inputContent.slice(index-1,textLen);
					stick.style.left = index*wordWidth+$("#keybord-input").position().left +10+'px';
					$("#textBox").html(inputContent);
				}
			}
			
			/*setTimeout(function () { clicked = 0 }, 500);*/
		/*}*/
	})

	/******
	 * 逐个删除输入内容
	 */
	$("#keybord-pop-up dd.delete").click(function(){
		inputContent = $("#textBox").html();
		var ary = inputContent.split('')
		if(inputContent.indexOf(defaultStr)>=0){
			return false;
		}
		index -=1;
		if(index>=0){
			stick.style.left = index*wordWidth+$("#keybord-input").position().left +10+'px';
			ary.splice(index,1);
			inputContent = ary.join('')
			textBox.innerHTML=inputContent;
			textWidth = textBox.offsetWidth;
		}else{
			index = 0;
			return false;
		}
	})

	/*******
	 * 清空键盘
	 */
	$("#keybord-pop-up dd.clear").click(function(){
		var defaultStr = $("#textBox").attr("default");
		$("#textBox").html(defaultStr);
		inputContent = '';
		index=0;
		stick.style.left = $("#keybord-input").position().left +10+'px';
		
	})

	$("#keybord-pop-up .close").click(function(){
		index=0;
		$("#textBox").html(defaultStr);
		stick.style.left = $("#keybord-input").position().left +10+'px';
		$("#keybord-pop-up").addClass("hide");
	})

})
	var dds = document.getElementById('keybord-pop-up').getElementsByTagName('dd'),
		dds_len=dds.length;
	for(var i=0; i<dds_len; i++){
		dds[i].addEventListener("touchstart",function(){
			var box_width=this.offsetWidth,
				box_height=this.offsetHeight,
				_left=this.offsetLeft,
				_top=this.offsetTop;
			animateFun(_left,_top,box_width,box_height)	
		})
	}

	/********
	 * 显示输入键盘
	 * @param {Object} type 类型 0打酒 2登录名输入
	 */
	function showKeybord(type,targetFunction){
		$(".alert-msg").addClass('hide');
		if(!type||type==0){
			$("#textBox").attr("default","请输入6位取酒码");
			$("#textBox").html("请输入6位取酒码");
			$("#keybord-confirm").html("打酒");
			$("#keybord-confirm").attr("onclick",targetFunction);
		}
		if(type==1){
			var defaultValue = $("#waiterLoginName").html();
			$("#textBox").attr("default","请输入手机号码");
			$("#textBox").html(defaultValue);
			$("#keybord-confirm").html("确定");
			$("#keybord-confirm").attr("onclick",targetFunction);
		}
		if($("#keybord-pop-up").hasClass("hide"))
		$("#keybord-pop-up").removeClass("hide");
		
		defaultStr = $("#textBox").attr("default");
		inputContent = $("#textBox").html();
		if(inputContent.indexOf(defaultStr)>=0){
			inputContent = "";
			wordWidth = 13;
			index=0;
		}else{
			textWidth = $("#textBox").innerWidth();
			console.log("文本宽度",textWidth);
			index=inputContent.length;
			wordWidth = textWidth/index;
		}
		stick.style.left =wordWidth*index+ $("#keybord-input").position().left +10+'px';
	}
	function hideKeyBord(){
		if(!$("#keybord-pop-up").hasClass("hide"))
		$("#keybord-pop-up").addClass("hide");
	}


	function inputWaiterLoginName(){
		hideKeyBord();
		$("#waiterLoginName").html($("#textBox").html());
		showServiceList();
	}

	//设置酒水两数
	function setWinCount(winCount){
		$("#win-buy-count").html(winCount+"两");
		$("#win-buy-count").attr("win-count",winCount);
	}

	//酒水两数加一
	function plusWinCount(){
		var winCount = parseInt($("#win-buy-count").attr("win-count"));
		var plusCount = winCount+1;
		if(plusCount>10) plusCount=10;
		$("#win-buy-count").html(plusCount+"两");
		$("#win-buy-count").attr("win-count",plusCount);
	}

	//酒水两数减一
	function subWinCount(){
		var winCount = parseInt($("#win-buy-count").attr("win-count"));
		var subCount = winCount-1;
		if(subCount<=0) subCount=1;
		$("#win-buy-count").html(subCount+"两");
		$("#win-buy-count").attr("win-count",subCount);
	}
	//loader_animate定位
	function animateFun(left,top,_width,_height){
		var loader=document.getElementById('loader_animate');
		var ball_scale=document.getElementsByClassName('ball-scale')[0];
		loader.style.display='block';
		loader.style.width = _width+"px";
		loader.style.height = _height+"px";
		loader.style.left=left+'px';
		loader.style.top=top+'px';
		ball_scale.style.width=_width+30+"px"
		ball_scale.style.height=_width+30+"px"
		ball_scale.style.left = -15+'px';
		ball_scale.style.top = (_height-_width-30)/2+'px';
		console.log((_height-_width-30)-2)
		setTimeout (function(){
			loader.style.display='none'
		},500)
	}
	/*模拟光标*/
	var _inputValue = document.getElementById('keybord-input');
	var textBox = document.getElementById('textBox');

	var index='',
		textWidth ,//文本的总宽度
		textLen ,//
		wordWidth ,
		_left ,//输入框的left
		_top ,//输入框的top
		stick = document.getElementById('stick'),
		timer = '';
	function getPosition(){
		var touch = event.touches[0],//获取第一个触点
	    	x = touch.pageX, //页面触点X坐标
	    	y = touch.pageY,
	    	temp = 1,
	    	textWidth = $("#textBox").innerWidth(),//文本的总宽度
			textLen = inputContent.length,//
			wordWidth = textWidth/textLen;
		defaultStr = $("#textBox").attr("default");
		inputContent = $("#textBox").html();
	    _left = $("#keybord-input").position().left+$('.keybord-wrap').position().left-200;//输入框的left
		index = Math.floor((x-_left)/wordWidth)
		if(index>textLen){
			index = textLen
		}
		if(inputContent.indexOf(defaultStr)>=0){
			index=0;
		}
		stick.style.left = index*wordWidth+$("#keybord-input").position().left +10+'px';
	    stick.style.display='inline-block';
	}
	var stickTemp = 1;
	timer = setInterval(function(){
	    	stickTemp *= -1;
	    	if(stickTemp==1){
	    		stick.style.display='inline-block';
		}else{
			stick.style.display='none';
		}
	},600)
