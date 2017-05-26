/*
 * ! Ext JS Library 3.0.3 Copyright(c) 2006-2009 Ext JS, LLC licensing@extjs.com
 * http://www.extjs.com/license
 */
var moduleList = [];
//"PERSON","WXCONF","WXMENU","WXARTICAL","WXMR","WXER","WXWP"
 

//var modules = ["PERSON","ORGM","PERM","WXUSER","WXCONF","WXMENU","WXARTICAL","WXMR","WXER","WXWP","WCCONF","WCEN","WCEWM","WCPP","SMLTEST","PRODUCT","WINERY","CATEGORY","TASTENOTE"];
 

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

		var responsea = Ext.lib.Ajax.getConnectionObject().conn;
		for (var i = 0; i < modules.length; i++) {
			responsea.open("POST", "common/loadModule.do?moduleName="
							+ modules[i], false);
			responsea.send(null);
			// alert(responsea.responseText)
			var module = eval(responsea.responseText);
			moduleList.push(new module());

		} 
//		moduleList.push(new Emin.WXARTICALWindow());
//		moduleList.push(new Emin.WCCONFWindow());
//		moduleList.push(new Emin.WCENWindow());
//		moduleList.push(new Emin.WCEWMWindow());
//		moduleList.push(new Emin.WCPPWindow());
//		moduleList.push(new Emin.CONTRACTWindow());
//		moduleList.push(new Emin.OPERATORWindow());
//		moduleList.push(new Emin.OPERCONFWindow());
//		moduleList.push(new Emin.DIVIDEWindow());
		return moduleList;
	},

	// config for the start menu
	getStartConfig : function() {
		return {
			title : "",
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
												url : "logout.do",
												success : function(result,
														request) {
													window.location
															.replace("login.html");
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
		id:"newPassword",
		name:"newPassword"
	},{
		xtype:"textfield",
		fieldLabel:"再次输入新密码",
		allowBlank:false,
		inputType:"password",
		id:"confirmPassword"
	}],
	labelAlign:"right",
	buttons:[{
		text:"确定",
		handler:function(){
			var newPassword=passwordForm.getForm().findField("newPassword");
			var confirmPassword=passwordForm.getForm().findField("confirmPassword");
			if(newPassword.getValue()!=confirmPassword.getValue()){
				showErrorMsg("两次输入的新密码不一致");
			}else{
				if(passwordForm.getForm().isValid()){
					passwordForm.getForm().submit({
						url:"modifyPassword.do",
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