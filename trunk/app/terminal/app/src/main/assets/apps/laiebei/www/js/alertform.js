/*显示提示框*/
var alertForm = $('#alert-form'),
	alertContent = $('.alert-content',alertForm);
	
function showAlert(str){
	alertContent.text(str);
	alertForm.removeClass('hide');
}
var keyStatus = 0;
/*关闭提示框*/
function hideAlert(num){
	alertForm.addClass('hide');
	
	//1 显示取酒码键盘
	if(keyStatus==1){
		showKeybord(0,'outWin()');
	}
	//2 显示输入电话键盘
	if(keyStatus==2){
		showKeybord(1,'inputWaiterLoginName()');
	}
	//3 返回首页
	if(keyStatus==3){
		backToIndex()
	}
	
	if(keyStatus!=0){
		keyStatus=0;
	}
}
