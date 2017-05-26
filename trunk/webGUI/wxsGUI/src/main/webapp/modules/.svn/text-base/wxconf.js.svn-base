/*
 * wxconf window
 */
Emin.WXCONFWindow = Ext.extend(Ext.app.Module, {
    id:'WXCONF-win',
    init : function(){
        this.launcher = {
            text: '微信公众号',
            iconCls:'icon-wxconf',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('WXCONF-win');
		if(!win){
			var codes = "${codes}";
			var desktop = this.app.getDesktop();
			/****从服务器上拉取公众号信息列表*****/
			officialAccountsStore = new Ext.data.JsonStore({
			    id:'id',
			    url: 'woa/validList.do',
			    fields: ['id', 'companyName', 'companyCode',
			    'type','appId','appSecret','token']
			});   
			officialAccountsStore.load(); 
			
			var typeStore = new Ext.data.JsonStore({  
                fields: ["id","name"],  
                data: [{id:1,name:"订阅"}, {id:2,name:"服务"}, {id:3,name:"企业"}, {id:4,name:"小程序"}] 
            });
			var columns = [   
		        {  
		            id:"companyName",   
		            header:"企业名称",       
		            width:150,   
		            dataIndex:"companyName", 
		        }, 
		        {  
		            id:"companyCode",   
		            header:"微信号",   
		            width:150,     
		            dataIndex:"companyCode",   
		        },
		        {  
		            id:"type",
		            header:"类型",     
		            width:60,   
		            dataIndex:"type",  
		            renderer:function(v,m,record){
		             	return v?typeStore.getById(v).data.name:'未知'
		            }
		        },
		        {  
		            id:"appId",   
		            header:"appId",     
		            width:150,   
		            dataIndex:"appId",  
		        }, 
		        {  
		            id:"appSecret",   
		            header:"appSecret",    
		            width:250,    
		            dataIndex:"appSecret",
		        },
		        {  
		            id:"token",
		            header:"Token",     
		            width:100,   
		            dataIndex:"token",  
		        },
		        {
		        	dataIndex:'operation',
                      xtype:'uxactioncolumn',  
                      header:'操作',  
                      autoWidth:false,  
                      width:160,                 
                      items: [{  
                          iconCls:'icon-edit',  
                          tooltip:'编辑',  
                          text:'编辑',  
          	              hidden:codes.indexOf("edit")==-1,
                          stopSelection:false,  
                          scope:this,  
                          handler:function(grid, rowIndex, colIndex){  
                        	  var obj = grid.getStore().getAt(rowIndex);
                        	  editWxConfForm.getForm().loadRecord(obj);
                        	  editWxConfWin.show();
                          }  
                      },{  
                          iconCls:'icon-delete',  
                          tooltip:'删除',  
                          text:'删除',  
          	              hidden:codes.indexOf("delete")==-1,
                          stopSelection:false,  
                          scope:this,  
                          handler:function(grid, rowIndex, colIndex){  
                        	  var delObj = grid.getStore().getAt(rowIndex);
                        	  Ext.Msg.confirm("提示", "是否确认删除数据？", function(b) {
								if (b == "yes") {
									Ext.Ajax.request({
										url:'woa/delete.do',
										params:{id:delObj.data.id},
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
								        	Ext.Msg.alert("信息提示","删除时出现异常！");
								      	}  
									});
						
								}
							})
                          }  
                      }]
	        	} 
		    ];
			var add_bar = {  
	            text: "新增",  
	            iconCls: "icon-add",  
	            disabled:codes.indexOf("add")==-1,
	            handler: function(){  
	            	editWxConfWin.show();
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
			});  
			
			
			
			//窗口
			win = desktop.createWindow({
			 	id:'WXCONF-win',
              	listeners:{
				},
                title:'微信公众号',
                height:500,               
				items:[grid],
                iconCls: 'icon-wxconf',
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'border'
            });
        
			/***********
			 * 保存公众号账户表单
			 */
			var editWxConfForm=new Ext.form.FormPanel({
				frame:true,
				labelAlign:"right",
				buttonAlign:"center",
				width:500,
				height:185,
				bodyStyle:"padding:10px",
				items:[{
					xtype:"hidden",
					id:"id",
					name:"id"
				},{
					xtype:"textfield",
					fieldLabel:"企业名称",
					allowBlank:false,
					inputType:"text",
					id:"companyName",
					name:"companyName",
					width:300,
				},{
					xtype:"textfield",
					fieldLabel:"微信号",
					allowBlank:false,
					inputType:"text",
					id:"companyCode",
					name:"companyCode",
					width:300,
				},
				new Ext.form.ComboBox({
				 	fieldLabel:'类型',
				    store: typeStore,
				    displayField:'name',
				    valueField:'id',
				    mode: 'local',
				    emptyText:'请选择...',
		            blankText: '请选择一个类型',
				    typeAhead: true,
				    triggerAction: 'all',
				    selectOnFocus:true,
		            editable: false,
					hiddenName:"type",
				}),
				{
					xtype:"textfield",
					fieldLabel:"appId",
					allowBlank:false,
					inputType:"text",
					id:"appId",
					name:'appId',
					width:300,
				},{
					xtype:"textfield",
					fieldLabel:"appSecret",
					allowBlank:false,
					inputType:"text",
					id:"appSecret",
					name:'appSecret',
					width:300,
				},{
					xtype:"textfield",
					fieldLabel:"token",
					allowBlank:false,
					inputType:"text",
					id:"token",
					name:'token',
					width:300,
				}],
				
			});
			var editWxConfWin=new Ext.Window({
				items:[editWxConfForm],
				closeAction:"hide",//关闭事件触发hide事件
				modal:true,//模态化
				constrain :true,
				resizable :false,
				listeners:{
					hide:function(){//监听hide事件
						editWxConfForm.getForm().reset();
					}
				},
				buttons:[{
					text:"保存",
					handler:function(){
						 if (editWxConfForm.getForm().isValid()) {  
			                editWxConfForm.getForm().submit({  
			                   	url :'woa/saveOrUpdate.do',  
			                   	waitTitle: '请稍等...',  
			                   	waitMsg: '正在提交信息...',  
			                    success: function(fp, o) {  
			                        if (o.result.success) {  
			             				editWxConfWin.hide(); //关闭窗口  
			                            officialAccountsStore.reload();  
			                        }else {  
			                        	Ext.Msg.alert("信息提示",o.result.message);  
			                      	}  
			                 	},  
			                  	failure: function() {  
			                    	Ext.Msg.alert("信息提示","保存时出现异常！");
			                  	}  
			               });  
			            } 
					}
				}],
				buttonAlign:"center",
				title:"微信公众号信息",
				closeAction:"hide"
			});
		}
        win.show();
    }
});

