/*
 * ! Ext JS Library 3.0.3 Copyright(c) 2006-2009 Ext JS, LLC licensing@extjs.com
 * http://www.extjs.com/license
 */
var moduleList = []
String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
}
var msgEL = null;
var keys = ""
Emin = new Ext.app.App({
	passwordWindow : null,
	init : function() {
		Ext.QuickTips.init(false);
		
		Ext.form.Field.prototype.msgTarget="side";
		
		
	},
	map : null,
	getModules : function(mask) {

		/*var responsea = Ext.lib.Ajax.getConnectionObject().conn;

		for (var i = 0; i < modules.length; i++) {
			
			responsea.open("POST", basePath
							+ "admin/common/loadModule.do?moduleName="
							+ modules[i], false);
			responsea.send(null);
			// alert(responsea.responseText)
			var module = eval(responsea.responseText);
			moduleList.push(new module())

		}*/
		//var personWin = new Emin.PERSONWindow()
		var m = new Emin.PERSONWindow();
		
		moduleList.push(new Emin.PERSONWindow())
		return moduleList;
	},

	// config for the start menu
	getStartConfig : function() {
		return {
			title : "admin",
			iconCls : 'user',
			toolItems : [{
				text : '修改密码',
				iconCls : 'settings',
				scope : this,
				handler : function() {
					if (Emin.passwordWindow == null) {}
					Emin.passwordWindow.show(Ext.getBody())
				}
			},/*
				 * '-',{ text:'设置', iconCls:'settings', scope:this,
				 * handler:function(){
				 *  } },
				 */'-', {
				text : '注销',
				iconCls : 'logout',
				scope : this,
				handler : function() {

					Ext.Msg.confirm("提示", "是否确认注销系统？", function(b) {
								if (b == "yes") {
									Ext.Ajax.request({
												method : "post",
												url : basePath
														+ "person!logOut",
												success : function(result,
														request) {
													window.location
															.replace(basePath);
												}
											})

								}
							})

				}
			}]
		};
	}
});
var passwordForm=new Ext.form.FormPanel({
	frame:true,
	items:[{
		xtype:"textfield",
		fieldLabel:"旧密码",
		allowBlank:false,
		inputType:"password",
		name:"password"
	},{
		xtype:"textfield",
		fieldLabel:"新密码",
		allowBlank:false,
		inputType:"password",
		id:"newPassword1",
		name:"newPassword"
	},{
		xtype:"textfield",
		fieldLabel:"再次输入新密码",
		allowBlank:false,
		inputType:"password",
		id:"newPassword2"
	}],
	labelAlign:"right",
	buttons:[{
		text:"确定",
		handler:function(){
			var pwd1=passwordForm.getForm().findField("newPassword1");
			var pwd2=passwordForm.getForm().findField("newPassword2");
			if(pwd1.getValue()!=pwd2.getValue()){
				showErrorMsg("两次输入的新密码不一致");
			}else{
				if(passwordForm.getForm().isValid()){
					passwordForm.getForm().submit({
						url:basePath+"person!updatePassword",
						success:function(f,action){
							showMsg("密码修改成功");
							passwordWin.hide();
						},
						failure:function(f,action){
							showErrorMsg(action.result.message);
						}
					});
				}
			}
		}
	}],
	buttonAlign:"center",
	width:400,
	height:150,
	bodyStyle:"padding:10px"
});
var passwordWin=new Ext.Window({
	items:[passwordForm],
	title:"修改密码",
	closeAction:"hide"
});
Emin.passwordWindow = passwordWin
function exportReport(url,data){
			
    var param = [];
    for (var key in data){
        var value = data[key];
        if (value.constructor == Array){
            value.forEach(function(_value){
                param.push(key + "=" + _value);
            });
        }else{
            param.push(key + '=' + value);
        }
    }
    param = param.join("&")
	 if(Emin.downloadFrame == undefined){
				var downloadFrame = document.createElement("iframe")
				downloadFrame.style.display="none";
				Emin.downloadFrame = downloadFrame;
				document.body.appendChild(Emin.downloadFrame)
	 }
	Emin.downloadFrame.src=url+"?"+param;
	
}