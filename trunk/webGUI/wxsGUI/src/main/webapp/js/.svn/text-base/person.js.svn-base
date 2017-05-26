/*
 * person window
 */
var officialAccountsStore  = null,oa_type_store = null;
Emin.PERSONWindow = Ext.extend(Ext.app.Module, {
    id:'person-win',
    init : function(){
        this.launcher = {
            text: '微信配置',
            iconCls:'icon-wxconf',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('person-win');
		if(!win){
			/****从服务器上拉取公众号信息列表*****/
			officialAccountsStore = new Ext.data.JsonStore({
			    id:'id',
			    url: '/wxs/woa/validList.do',
			    fields: ['id', 'companyName', 'companyCode',
			    'type','appId','appSecret','token']
			});   
			officialAccountsStore.load(); 
			
			var typeMaps = [[1,"订阅"], [2,"服务"], [3,"企业"], [4,"小程序"]];
			oa_type_store = new Ext.data.SimpleStore({  
                fields: ["type_id","oa_type"],  
                data: typeMaps 
            }) ;
			var columns = [  
		        {  
		            id:"id",   
		            header:"编号",   
		            width:50,   
		            dataIndex:"id"  
		        },  
		        {  
		            id:"companyName",   
		            header:"企业名称",       
		            width:150,   
		            dataIndex:"companyName",   
		            editor:new Ext.form.TextField({  
		                allowBlank:false  
		            })  
		        }, 
		        {  
		            id:"companyCode",   
		            header:"微信号",   
		            width:100,     
		            dataIndex:"companyCode",   
		            editor:new Ext.form.TextField({  
		                allowBlank:false  
		            })  
		        },
		        {  
		            id:"type",
		            header:"类型",     
		            width:80,   
		            dataIndex:"type",   
		            editor: new Ext.form.ComboBox({  
		                editable: false,  
		                displayField: "oa_type",  
		                valueField:'type_id',
		                mode: "local",  
		                triggerAction: "all",  
		                store: oa_type_store
		            }),
		            renderer:function(v,m,record){
		             	return (typeMaps[v-1])[1];
		            }
		        },
		        {  
		            id:"appId",   
		            header:"appId",     
		            width:150,   
		            dataIndex:"appId",   
		            editor:new Ext.form.TextField({  
		                allowBlank:false  
		            })  
		        }, 
		        {  
		            id:"appSecret",   
		            header:"appSecret",    
		            width:200,    
		            dataIndex:"appSecret",   
		            editor:new Ext.form.TextField({  
		                allowBlank:false  
		            })  
		        },
		        {  
		            id:"token",
		            header:"Token",     
		            width:100,   
		            dataIndex:"token",   
		            editor:new Ext.form.TextField({  
		                allowBlank:false  
		            })  
		        },
		        {
		        	dataIndex:'operation',
                      xtype:'uxactioncolumn',  
                      header:'操作',  
                      autoWidth:false,  
                      width:80,                 
                      items: [{  
                          iconCls:'icon-delete',  
                          tooltip:'删除',  
                          text:'删除',  
                          stopSelection:false,  
                          scope:this,  
                          handler:function(grid, rowIndex, colIndex){  
                        	  var delObj = grid.getStore().getAt(rowIndex);
                        	  deleteOaById(delObj.data.id);//执行删除数据的函数
                          }  
                      }]
	        	} 
		    ];
			var add_bar = {  
	            text: "新增",  
	            iconCls: "icon-add",  
	            handler: function(){  
	            	//显示添加数据窗口
	          		if (Emin.addOfficialAccountWindow) {
						Emin.addOfficialAccountWindow.show(Ext.getBody());
					}
	            }  
	        };

			var refresh_bar = {  
	            text: "刷新",  
	            iconCls: "icon-refresh",  
	            handler: function(){  
	            	officialAccountsStore.reload();//刷新列表数据
	            }  
	       };
	       
			var grid = new Ext.grid.EditorGridPanel({  
			    width: 500,  
			    height: 300,  
			    frame: true,  
			    tbar: [  
			       add_bar,
			       refresh_bar
			    ],  
			    store: officialAccountsStore,
			    columns: columns,  
			    region:"center",
			    autoExpandColumn: "token", 
			    listeners:{//监听列表值发生改变后执行代码
			    	afteredit:function(e){
			    		var record = e.record;//发生改变的行数据
			    		updateOrAddOa(record.data);//更新数据
			    	}
			    }
			});  
			
			
			
			//窗口
			win = desktop.createWindow({
	                id: 'person-win',
	              	listeners:{
					},
	                title:'微信配置',
	                height:500,               
					items:[grid],
	                iconCls: 'icon-wxconf',
	                shim:false,
	                animCollapse:false,
	                constrainHeader:true,
	                layout: 'border'
	            });
        
			initOaAddWindow();
		}
        win.show();
    }
});

function initOaAddWindow(){
	/***********
	 * 添加公众号账户表单
	 */
	var addOfficialAccountForm=new Ext.form.FormPanel({
		frame:true,
		labelAlign:"right",
		buttonAlign:"center",
		width:400,
		height:185,
		bodyStyle:"padding:10px",
		items:[{
			xtype:"textfield",
			fieldLabel:"企业名称",
			allowBlank:false,
			inputType:"text",
			id:"companyName",
			name:"companyName",
			width:200,
		},{
			xtype:"textfield",
			fieldLabel:"微信号",
			allowBlank:false,
			inputType:"text",
			id:"companyCode",
			name:"companyCode",
			width:200,
		},
		new Ext.form.ComboBox({
		 	fieldLabel:'类型',
		    store: new Ext.data.JsonStore({  
                fields: ["type_id","oa_type"],  
                data: [{type_id:1,oa_type:"订阅"}, {type_id:2,oa_type:"服务"}, {type_id:2,oa_type:"企业"}, {type_id:2,oa_type:"小程序"}] 
            }) ,
		    displayField:'oa_type',
		    valueField:'type_id',
		    mode: 'local',
		    emptyText:'请选择...',
            blankText: '请选择一个类型',
		    typeAhead: true,
		    triggerAction: 'all',
		    selectOnFocus:true,
            editable: false,
			hiddenName:"type",
			listeners :{
				select:function(e){//监听select事件
					console.dir(e);
				}
			}
		}),
//		{
//			xtype:"textfield",
//			fieldLabel:"类型",
//			allowBlank:false,
//			inputType:"text",
//			id:"type",
//			name:'type',
//			width:200,
//		},
		{
			xtype:"textfield",
			fieldLabel:"appId",
			allowBlank:false,
			inputType:"text",
			id:"appId",
			name:'appId',
			width:200,
		},{
			xtype:"textfield",
			fieldLabel:"appSecret",
			allowBlank:false,
			inputType:"text",
			id:"appSecret",
			name:'appSecret',
			width:200,
		},{
			xtype:"textfield",
			fieldLabel:"token",
			allowBlank:false,
			inputType:"text",
			id:"token",
			name:'token',
			width:200,
		}],
		
	});
	var addOfficialAccountWin=new Ext.Window({
		items:[addOfficialAccountForm],
		closeAction:"hide",//关闭事件触发hide事件
		modal:true,//模态化
		constrain :true,
		resizable :false,
		listeners:{
			hide:function(){//监听hide事件
				addOfficialAccountForm.getForm().reset();
			}
		},
		buttons:[{
			text:"确定",
			handler:function(){
				 if (addOfficialAccountForm.getForm().isValid()) {  
	                addOfficialAccountForm.getForm().submit({  
	                   	url :'/wxs/woa/saveOrUpdate.do',  
	                   	waitTitle: '请稍等...',  
	                   	waitMsg: '正在提交信息...',  
	                    success: function(fp, o) {  
	                        if (o.result.success) {  
	                        	messageWindow({message:"保存成功"});  
	             				addOfficialAccountWin.hide(); //关闭窗口  
	                            officialAccountsStore.reload();  
	                        }else {  
	                        	Ext.Msg.alert("信息提示","添加时出现异常1！");  
	                      	}  
	                 	},  
	                  	failure: function() {  
	                    	Ext.Msg.alert("信息提示","添加时出现异常2！");
	                  	}  
	               });  
	            } 
			}
		}],
		buttonAlign:"center",
		title:"添加公众号账户",
		closeAction:"hide"
	});
	Emin.addOfficialAccountWindow = addOfficialAccountWin;	
	
}


function updateOrAddOa(oa){
	Ext.Ajax.request({
		url:'/wxs/woa/saveOrUpdate.do',
		params:oa,
		success: function(response,request) {  
		 	var responseData = Ext.decode(response.responseText)
			if(responseData.success){
				officialAccountsStore.reload();
			}else{
				messageWindow({message:responseData.message})
			}

     	},  
      	failure: function() {  
        	Ext.Msg.alert("信息提示","添加时出现异常！");
      	}  
	});
	
}

function deleteOaById(id){
	Ext.Msg.confirm("提示", "是否确认删除数据？", function(b) {
		if (b == "yes") {
			Ext.Ajax.request({
				url:'/wxs/woa/delete.do',
				params:{id:id},
				method:'post',
				success: function(response,request) {  
				 	var responseData = Ext.decode(response.responseText)
					if(responseData.success){
						officialAccountsStore.reload();
					}else{
						messageWindow({message:responseData.message});
					}
		
		     	},  
		      	failure: function() {  
		        	Ext.Msg.alert("信息提示","添加时出现异常！");
		      	}  
			});

		}
	})
	
	
}


