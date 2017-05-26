<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		html {width: 100%;height: 100%;overflow: auto;margin:0;background: #F0F0F0;}
		body {width: 100%;height: 100%;max-width: 600px;margin: auto;}
		a {text-decoration: none;} 
	</style>
	<title>${companyName}</title>
</head>
<body onload="init();">
	<div id="title" style="font-size: x-large;padding: 10px;">
	${title}
	</div>
	<div style="color: #8c8c8c;padding: 10px;">
	<span>${date}</span>&nbsp;&nbsp;<a id="auther" href="javascript:;" style="color: #607fa6;">${auther}</a>
	</div>
	<div>
		<fieldset id="comments" style="border:1px solid #AAA;padding:3px;margin: 10px;background:#f8f7f5;">
			<legend style="margin-left: 10px;background:#469408;color: #FFF;border-radius:3px;font-weight: bolder;padding: 3px 5px;">摘要</legend>
			<div style="text-indent: 2em;padding: 5px;line-height: 150%;">${comments}</div>
		</fieldset>
	</div>
	<div id="info" style="margin: 10px;border-bottom: 1px solid #AAA;padding-bottom: 20px;">
		${info}
		<br><br><label style="color: #AAA;font-size: smaller;float: right;">结束</label>
	</div>
	<div style="text-align:center">
		
	长按二维码选择识别来关注我们！<br>
	<img src="http://open.weixin.qq.com/qr/code/?username=${companyCode}" style="width:60%" id="gh_code"/>
	</div>
</body>
<script type="text/javascript">
function init(){
	var imgs = document.getElementsByTagName("IMG");
	for(var i in imgs){	
		if(imgs[i].id=="gh_code"){
			continue;
		}
		if(imgs[i].width>200){
			imgs[i].style.width= "100%";
		}
		
	}
}
</script>
</html>

