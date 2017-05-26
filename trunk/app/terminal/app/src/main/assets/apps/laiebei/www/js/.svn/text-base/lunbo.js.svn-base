
	var carousel=0;
	video.click(function(){
		console.log('视频被点击'+carousel)
	});
	function lunboMove(){
		var _left=-nextIndex*imgWidth,
			liType = lis[nextIndex].getAttribute('type');
			liTimes = lis[nextIndex].getAttribute('times')*1000;
			video = $(".videoYX",lis[nextIndex])[0];
		for(var i=0;i<lunboLen-2;i++){
			circles[i].style.background="rgba(93,160,119,0.2)";
		}
		var currIndex=0;
		if(nextIndex===0){
			currIndex=lunboLen-3
		}else if(nextIndex===lunboLen-1){
			currIndex=0
		}else{
			currIndex=nextIndex-1
		}
		circles[currIndex].style.background="rgba(93,160,119,0.6)";
		lunboAnimate(_ul,100,{left:_left},function(){//调用函数实现轮播
			lunboindex=nextIndex;
			nextIndex++;
			if(nextIndex>=lunboLen){  
			//当循环到最后一张图片时，切换到第二张（相同图片，克隆产生）
				_ul.style.left=-imgWidth+"px";
				lunboindex=1;
				nextIndex=2;
			}
			if(nextIndex<=1){
				_ul.style.left=-(lunboLen-2)*imgWidth+"px";
				nextIndex=lunboLen-1;
				lunboindex=lunboLen-2;	
			}
		});
		if(liType == '1'){
			clearInterval(timer);
			/*video.play();*/
			setTimeout(function(){
				carousel+=1;
				video.dispatchEvent(lunboEvent);
			},101);
		}else{
			setTimeout(lunboMove,liTimes)
		}
	}
function lunboAnimate(element, speed, options, fn) {
	// 先取消当前元素上之前的运动动画
	clearInterval(element.timer);
	// 保存各属性运动前的初始值
	var beforeMoved = {};
	// 保存各属性运动的范围
	var changeDistance = {};
	// 获取各运动初始的初始值
	for (var attr in options) {
		beforeMoved[attr] = parseFloat(element.style.left) || 0;
		changeDistance[attr] = options[attr] - beforeMoved[attr];
	}

	// 记录运动起始时间
	var startTime = +new Date();
	// 启动定时器，实现运动动画
	element.timer = setInterval(function(){
		// 计算已运动时间
		var elapsed = Math.min(+new Date() - startTime, speed);
		// 换算各属性当前次属性值
		for (var attr in options) {
			// 计算当前遍历到的 attr 属性值
			var value = elapsed * changeDistance[attr] / speed + beforeMoved[attr];
			// 设置 element 元素CSS属性值
			element.style[attr] = value + (attr === "opacity" ? "" : "px");
			// IE
			if (attr === "opacity") {
				element.style.filter = "alpha(opacity="+ (value * 100) +")";
			}
		}
		// 判断是否停止定时器
		if (elapsed === speed) {
			clearInterval(element.timer);
			// 判断，有要继续执行的函数，则调用
			fn && fn();
		}
	}, 30);
}
function videoEnd(){
	/*timer=setInterval(lunboMove,3000)*/
	setTimeout(lunboMove,liTimes)
}
