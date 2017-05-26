
(function(){
	var scale = function (btn,bar,title){
		this.btn=document.getElementById(btn);
		this.bar=document.getElementById(bar);
		this.title=document.getElementById(title);
		this.step=this.bar.getElementsByTagName("div")[0];
		this.init()
	};
	var screenLight = 0.5;
	scale.prototype={
		init:function (){
			var f=this,g=document,b=window,m=Math;
			f.btn.ontouchstart=function (e){
				var x= (e.touches[0]).pageX;
				var l=this.offsetLeft;
				var max=f.bar.offsetWidth-this.offsetWidth+10;
				g.ontouchmove=function (e){
				    e.preventDefault();
					var thisX = (e.touches[0]).pageX;
					var to=m.min(max,m.max(-2,l+(thisX-x)));
					f.btn.style.left=(to-5)+'px';
					f.ondrag(m.round(m.max(0,to/max)*100),to);
					b.getSelection ? b.getSelection().removeAllRanges() : g.selection.empty();
				};
				g.ontouchend = new Function('this.ontouchmove=null');
			}
		},
		ondrag:function (pos,x){
			this.step.style.width=Math.max(0,x)+'px';
			this.title.innerHTML=pos+'%'+'<div class="trible"></div>';
			console.log('百分比:'+pos)
			console.log('位置:'+x)
			console.log('当前对象'+this.btn.id)
			if(this.btn.id=='btn_light'){
				plus.screen.setBrightness(pos/100);
				console.log('屏幕亮度：'+plus.screen.getBrightness())
			}else{
				plus.device.setVolume(pos/100);
				console.log('屏幕音量：'+plus.device.getVolume())
			}
		}
	}
	light = new scale('btn_light','bar_light','title_light');
	voice = new scale('btn_voice','bar_voice','title_voice');
})();
var light,voice,setUpStatus = 0;
function screenInit(){
	if(setUpStatus == 0){
		var screenLight = Math.floor(plus.screen.getBrightness()*100);
		var screenVoice = Math.floor(plus.device.getVolume()*100);
		light.step.style.width=screenLight*5+'px';
		light.title.innerHTML=screenLight+'%'+'<div class="trible"></div>';
		light.btn.style.left = screenLight*5-5+"px";
		
		voice.step.style.width=screenVoice*5+'px';
		voice.title.innerHTML=screenVoice+'%'+'<div class="trible"></div>';
		voice.btn.style.left = screenVoice*5-5+"px";
		setUpStatus == 1;
	}	
}

var g=document,b=window,m=Math;
light.bar.onclick = function(e){
		e = e || window.event;
		var l=this.offsetLeft+20;
		var thisX = e.pageX;
		var max=this.offsetWidth;
		var to=m.min(max,m.max(-2,thisX-l));
		setPosition(light,to)
}
voice.bar.onclick = function(e){
		e = e || window.event;
		var l=this.offsetLeft+20;
		var thisX = e.pageX;
		var max=this.offsetWidth;
		var to=m.min(max,m.max(-2,thisX-l));
		setPosition(voice,to)
}
function setPosition(ele,final){
	var pos = m.round(m.max(0,final/500)*100)
	ele.btn.style.left=(final-7)+'px';
	ele.ondrag(pos,final);
	b.getSelection ? b.getSelection().removeAllRanges() : g.selection.empty();
}
