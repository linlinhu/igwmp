/*
 * USM window
 */
Emin.PERSONWindow = Ext.extend(Ext.app.Module, {
    id:'PERSON-win',
    init : function(){
        this.launcher = {
            text: '员工管理',
            iconCls:'icon-em',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('PERSON-win');
       
		if(!win){
        	var wg = new Ext.WindowGroup();
        	wg.zseed = 7500;
        	
        	var sexData=[{"value":"","text":"全部"},
				{"value":"1","text":"男"},
				{"value":"0","text":"女"}
			];
			var sexStore=new Ext.data.JsonStore({
				data:sexData,
				fields:["value","text"]
			});
			
			
        	//数据存储
        	/*var userStore = new Ext.data.Store({
        		proxy:new Ext.data.HttpProxy({
        			url:"person/page.do",
        			method:"post"
        		}),
        		render:new Ext.data.JsonReader({
        			totalProperty:"total",
	        		root:"rows"
	        	
        		},)
        	});
        	userStore.load({
        		params:{
        			start:0,
        			limit:20
        		}
        	});*/
        	var userStore = new Ext.data.JsonStore({
        		url:"person/page.do",
				totalProperty:"total",
	    		root:"rows",
	    		fields:["name","gender","mobilePhone","email","id"]
        	});
        	userStore.load({
        		params:{
        			start:0,
        			limit:20
        		}
        	});

		
        	var sm=new Ext.grid.CheckboxSelectionModel();
        	var userCm=new Ext.grid.ColumnModel(
        		[
        		new Ext.grid.RowNumberer(),
        		sm,
        		{header:"姓名",dataIndex:"name",align:"center",editor:new Ext.form.TextField({allowBlank:false})},
        		{header:"性别",dataIndex:"gender",align:"center",renderer:function(v,m,r,row,c,s){
        			if(v==1){
        				return "男";
        			}
        			return "女";
        		},editor:new Ext.form.ComboBox({
        			allowBlank:false,
        			store:sexStore,
					mode:"local",
					valueField:"value",
					displayField:"text",
					name:"sex",
					width:147,
					hiddenName:"sex",
					triggerAction:"all",
					editable :false
        		})},
        		{header:"电话",dataIndex:"mobilePhone",align:"center",editor:new Ext.form.TextField({
        			allowBlank:false,
        			regex:/^1[3|4|5|8][0-9]\d{8}$/
        		})},
        		
        		
        		{header:"操作",dataIndex:"operation",align:"center",xtype:"uxactioncolumn",items:[{
        			text:"删除",
        			iconCls:"icon-edit",
        			tooltip:"删除员工",
        			stopSelection:false,  
                    scope:this,
                    handler:function(grid,rowIndex,columnIndex){
                    	var record = grid.getStore().getAt(rowIndex);                    	
                    	var str = record.data.status==-1?"启用":"禁用";
                    	Ext.Msg.confirm("提示","是否删除此员工?",function(b){
                    		if(b=="yes"){
                    			Ext.Msg.wait("正在删除员工...","请稍后");
                    			Ext.Ajax.request({
                    				url:"person/delete.do",
                    				method:"post",
                    				params:{
                    					id:record.data.id
                    				},
                    				success:function(response,request){
                    					Ext.Msg.hide();
                    					var result = Ext.decode(response.responseText)
                    					if(result.message!=""){
                    						showErrorMsg(result.message)
                    					}else{
                    						userStore.reload();
                    					}
                    				}
                    			})
                    		}
                    	})
                    }
        		}]}
        		]
        	);
        	var userForm=new Ext.FormPanel({
								style:"text-align:center:padding:20px",
        						frame:true,
        						style:"padding-top:10px",
        						labelWidth:85,
        						labelAlign:"right",
        						defaults:{
        							anchor:"90%"
        						},
        						items:[{
        							xtype:"textfield",
        							fieldLabel:"姓名",
        							name:"name",
        							allowBlank:false
        						},{
        							xtype:"radiogroup",
        							fieldLabel:"性别",
        							name:"sex",
        							allowBlank:false,
        							items:[{
        								boxLabel:"男",
        								checked:true,
        								inputValue:"1",
        								name:"gender"
        							},{
        								boxLabel:"女",
        								inputValue:"2",
        								name:"gender"
        							}]
        						},{
        							xtype:"textfield",
        							fieldLabel:"电话",
        							name:"mobilePhone",
        							regex:/^1[3|4|5|8][0-9]\d{8}$/,
        							allowBlank:false,
        							invalidText:"电话号码格式不正确"
        						},{
        							xtype:"textfield",
        							fieldLabel:"邮箱",
        							name:"email"
        						}]
        					});
        	/*var data = userForm.getForm().getValues(false)
        	userStore.baseParams = data;
        	userStore.load();*/
        	var infoWin=new Ext.Window({
        					items:[userForm],
        					title:"添加员工",
        					frame:true,
        					width:400,
        					autoHeight:true,
        					resizable:false,
        					modal:true,
        					closeAction:"hide",
        					manager:wg,
        					buttons:[{
        						text:"确定",
        						handler:function(){
        							if(userForm.getForm().isValid()){
        								userForm.getForm().submit({
        									url:"person/saveOrUpdate.do",
        									
        									success:function(form,action){
        										userStore.reload();
        										infoWin.hide();
        									},
        									failure:function(form,action){
        										showErrorMsg(action.result.message);
        									}
        								});
        							}
        						}
        					}],
        					buttonAlign:"center"
        				});
			var search=new Ext.FormPanel({
					title:"查询",
					region:"west",
					width:230,
					collapsible:true,
					frame:true,
					titleCollapse:true,
					bodyStyle:"padding-top:10px",
					labelAlign:"right",
					labelWidth:50,
					defaults:{
					   anchor:"90%"
					},
					items:[{
						xtype:"textfield",
						fieldLabel:"姓名",
						name:"name"
					},{
						xtype:"textfield",
						fieldLabel:"电话",
						name:"mobile"
					}],
					buttonAlign:'center',
					buttons:[{
						text:'查询',
						handler:function(){
							var data = search.getForm().getValues(false);
							userStore.baseParams = data;
							userStore.load({
				        		params:{
				        			start:0,
				        			limit:20
				        		}
				        	});
						}
					},{
						text:'重置',
						handler:function(){
							search.getForm().reset();
						}
					}]
        	});
        	var userTable=new Ext.grid.EditorGridPanel({
        		store:userStore,
        		cm:userCm,
        		sm:sm,
        		enableColumnMove:false,
        		enableColumnResize:false,
        		stripeRows:true,
        		region:"center",
        		loadMask:true,
        		viewConfig:{
        			forceFit:true,//自动填满
        			getRowClass: function(record, index) {
			            var c = record.data.status
			            if (c < 0) {
			                return 'rowdisable';
			            }
			            return '';
			        }
        		},
        		buttonAlign:"left",
        		tbar:[{
        			text:"添加员工",
        			handler:function(){
        				
	        			infoWin.show();
	        		}
        		}],
        		bbar:new Ext.PagingToolbar({
        			pageSize : 20,
					store : userStore,
					beforePageText : "第",
					afterPageText : "页，共{0}页",
					lastText : "尾页",
					nextText : "下一页",
					prevText : "上一页",
					firstText : "首页",
					refreshText : "刷新",
					displayInfo : true,
					displayMsg : '一共 {2}条员工信息',
					emptyMsg : "没有用户信息"
				}),
				listeners:{
					"afteredit":function(e){
						var record = e.record
						Ext.Ajax.request({
							url:"",
							params:e.record.data,
							success:function(response,request){
								var data = Ext.decode(response.responseText)
								if(data.message!=""){
									showErrorMsg(data.message);
									record.reject();
								}else{
									record.commit();
								}
							}
						});
					},
					click:function(e){
						console.dir(userStore);
					}
				}
        	});

            win = desktop.createWindow({
                id: 'PERSON-win',
              	listeners:{
					
				},
                title:'员工管理',
                height:500,
				items:[userTable,search],
                iconCls: 'icon-em',
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'border'
            });
        }
        win.show();
    }
});