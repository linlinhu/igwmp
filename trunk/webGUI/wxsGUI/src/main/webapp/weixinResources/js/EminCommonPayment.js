
			function wap_pay(channelCode,orderNumber,amount,openId,successCallBack) {
				
				var parmentUrl = '/wxs/pay/create.do';
				
				var xhr = new XMLHttpRequest();
				xhr.open("POST", parmentUrl, false);
				xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				var obj = {
					channelCode: channelCode,
					amount: amount,
					openId:openId,
					orderNumber:orderNumber
				}
				var str = "";
 
				for(var prop in obj){
				        str += prop + "=" + obj[prop] + "&"
				}
				
				xhr.onreadystatechange = function () {
					
           		 if (xhr.readyState == 4 && xhr.status == 200) {  // 304未修改
           		 	
           		 	var charge = JSON.parse(xhr.responseText);
           		 	var paymentNo = charge.id;
           		 	var paymentNumber = paymentNo.replace("_","-");
           		 	
	                pingpp.createPayment(JSON.stringify(charge), function(result, err) {

	                	successCallBack(result,err,paymentNumber);
	         
					});
	                
	                
	            }
	         	};
				xhr.send(str);
			}
		
			
//			function wap_pay_complete(paymentNumber) {
//				var parmentUrl = '/wxs/pay/success.do';	
//				var xhr = new XMLHttpRequest();
//				xhr.open("POST", parmentUrl, false);
//				xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//				
//				var obj = {
//					paymentNumber: paymentNumber	
//				}
//				var str = "";
// 
//				for(var prop in obj){
//					str += prop + "=" + obj[prop] + "&"
//				}
//				
//				xhr.onreadystatechange = function () {
//					
//           		 if (xhr.readyState == 4 && xhr.status == 200) {  // 304未修改
//           		 	alert( xhr.responseText);  
//           		 }
//	         	};
//				xhr.send(str);
//				
//			
//			}