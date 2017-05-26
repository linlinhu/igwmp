

			function order(productId,count,price,amount,successCallBack) {
				var parmentUrl = '/wxs/order/create.do';
				
				var xhr = new XMLHttpRequest();
				xhr.open("POST", parmentUrl, false);
				xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				var obj = {
					productId: productId,
					count: count,
					price:price,
					amount:amount
				}
				var str = "";
 
				for(var prop in obj){
				        str += prop + "=" + obj[prop] + "&"
				}
				
				xhr.onreadystatechange = function () {
					
           		 if (xhr.readyState == 4 && xhr.status == 200) {  // 304未修改
           		 	
           		 	alert("成功");
	               
	            }
	         	};
				xhr.send(str);
				/*xhr.onreadystatechange = function() {
					if(xhr.readyState == 4 && xhr.status == 200) {
						alert('xhr.responseText:'+xhr.responseText);
						
					}
				}*/
			}
			
			