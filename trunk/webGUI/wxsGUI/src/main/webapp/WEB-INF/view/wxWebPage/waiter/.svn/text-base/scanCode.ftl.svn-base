<!DOCTYPE html>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.1.0.js" ></script>
<script type="text/javascript" src="${basePath}/weixinResources/js/mui.js"></script>
<script type="text/javascript">
	mui.init({
		swipeBack:true //启用右滑关闭功能
	});
    var conf = ${jsAPIConf};
	conf.jsApiList=["onMenuShareTimeline","onMenuShareAppMessage","hideMenuItems","scanQRCode"];
	wx.config(conf);
	wx.ready(function(){
		wx.scanQRCode({
				    needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
				    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
				    success: function (res) {
						var url = res.resultStr;
						window.location.href=url;
				    }
				});
	})

</script>
</html>
