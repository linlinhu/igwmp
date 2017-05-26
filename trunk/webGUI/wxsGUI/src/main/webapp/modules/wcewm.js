/*
 *  Environment of Win-Cabinet's waiter Manage
 * 酒柜环境服务员管理
 */
Emin.WCEWMWindow = Ext.extend(Ext.app.Module, {
    id:'WCEWM-win',
    init : function(){
        this.launcher = {
            text: '服务员管理',
            iconCls:'icon-wcewm',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('WCEWM-win');
		if(!win){
			var desktop = this.app.getDesktop();
			var waiterId = '',uptObj={};
			var bindRestaurantVos = [];
			/****从服务器上拉取公众号信息列表*****/
			pageStore = new Ext.data.JsonStore({
			    id:'id',//http://192.168.0.60:8081/wxsGUI/
			    url: 'servant/page.do',
				totalProperty:"total",
				root:"rows",
				fields: ['id', 'name', 'gender','telephone','integral','historyIntegral','exchangeIntegral','restaurantVO','auditingStatus','auditingPerson','memo']
			});
			pageStore.load({
				params:{
					start:0,
					limit:20
				}
			});
			
		
		
            var genderMap = [[1,"男"], [2,"女"], [3,"模糊"]];
			genderStore = new Ext.data.SimpleStore({  
                fields: ["id","name"],  
                data: genderMap 
            }) ;
			var columns = [  
		        {  
		            id:"id",   
		            header:"编号",   
		            width:40,   
		            dataIndex:"id"  
		        },  
		        {  
		            id:"name",   
		            header:"姓名",   
		            width:90,   
		            dataIndex:"name",   
		        },  
		        {  
		            id:"restaurantVO",   
		            header:"餐厅",       
		            width:150,   
		            dataIndex:"restaurantVO", 
	             	renderer:function(v,m,record){
	             		//v 表示设备管理列表数据中绑定餐厅的id
						var bindRestaurants = "";
						if(v&&v.length>0){
							for(var i=0;i<v.length;i++){
								bindRestaurants+=v[i].name+",";
							}
							return bindRestaurants.substring(0,bindRestaurants.length-1);
						}
						return '';
						
		            }
		        }, 
		        {  
		            id:"telephone",   
		            header:"电话",   
		            width:100,     
		            dataIndex:"telephone",   
		        },
		        {  
		            id:"integral",
		            header:"现有积分",     
		            width:60,   
		            dataIndex:"integral",   
		        },
		        {  
		            id:"auditingStatus",
		            header:"状态",     
		            width:80,   
		            dataIndex:"auditingStatus",  
		            renderer:function(v){
		            	return v?'通过':"未通过"
		            }
		        },
		        {  
		            id:"auditingPerson",
		            header:"审核人",     
		            width:80,   
		            dataIndex:"auditingPerson", 
		        },
		        {  
		            id:"memo",
		            header:"审核备注",     
		            width:200,   
		            dataIndex:"memo"
		        },
		        {
		        	dataIndex:'operation',
                      xtype:'uxactioncolumn', 
                      header:'操作',  
                      autoWidth:false,  
                      width:100,
                      items: [{  
                          iconCls:'icon-edit',  
                          tooltip:'更新',  
                          text:'更新',  
                          stopSelection:false,  
                          scope:this, 
                          handler:function(grid, rowIndex, colIndex){  
                      		uptObj = grid.getStore().getAt(rowIndex);
                    		waiterId = uptObj.get("id");
                      		waiterDetailWin.show();
                      		waiterDetailForm.getForm().loadRecord(uptObj);
                      		var strs = '';
                      		var restaurantVO = uptObj.data.restaurantVO;
                      		for(var i=0;i<restaurantVO.length;i++){
                      			strs += restaurantVO[i].name+",";
                      		}
                      		waiterDetailForm.getForm().findField("bindRestaurant").setValue(strs.substring(0,strs.length-1))
                        }  
                    },{  
                        iconCls:'icon-audit',  
                        tooltip:'审核',  
                        text:'审核',  
                        stopSelection:false,  
                        scope:this, 
                        validRecord:true,  
                        valid:function(record){
                        	return record.data.auditingStatus!=1;
                        },
                        handler:function(grid, rowIndex, colIndex){  
                    		uptObj = grid.getStore().getAt(rowIndex);
                    		auditWaiterForm.getForm().loadRecord(uptObj);
                    		auditWaiterWin.setTitle("审核"+uptObj.data.name);
                    		auditWaiterWin.show();
                        }
                  }]
	        	} 
		    ];
		    
		    
	       var keywordTf = new Ext.form.TextField({
	       			id:"keyword",
	       			name:"keyword",
	       			fieldLabel:"关键字",
				    inputType:'text',
				    emptyText:'输入餐厅名称、服务员名字进行搜索',
		            width:300,
		            style:'margin-right:10px'
			});
			var searchBtn = {
	            text: "搜索",  
	            iconCls: "icon-search",  
	            handler: function(){  
					pageStore.load({
						params:{
							start:0,
							limit:20,
							keyword:Ext.get("keyword").getValue()=='输入餐厅名称、服务员名字进行搜索'?'':Ext.get("keyword").getValue()
						}
					});
					
					
	            } 
			};
			
			var service_bar = {  
	            text: "打酒记录",  
	            iconCls: "icon-service",  
	            handler: function(){  
	            	waiterId = '';
	            	serviceStore.load({
	    				params:{
	    					start:0,
	    					limit:20
	    				}
	    			})
					serviceWindow.show();
	            }  
	        };

			var exchange_bar = {  
	            text: "积分兑换记录",  
	            iconCls: "icon-exchange",  
	            handler: function(){  
	            	waiterId = '';
	            	exchangeStore.load({
	    				params:{
	    					start:0,
	    					limit:20
	    				}
	    			})
                   	exchangeWindow.show();
	            }  
	       };
			var grid = new Ext.grid.EditorGridPanel({  
			    width: 500,  
			    height: 300,  
			    frame: true,  
			    tbar: [  
			       keywordTf,
			       searchBtn,
			       "->",service_bar,exchange_bar
			    ],  
			    store: pageStore,
			    columns: columns,  
			    region:"center",
			    autoExpandColumn: "restaurantVO", 
			    bbar:new Ext.PagingToolbar({
        			pageSize : 20,
					store : pageStore,
					beforePageText : "第",
					afterPageText : "页，共{0}页",
					lastText : "尾页",
					nextText : "下一页",
					prevText : "上一页",
					firstText : "首页",
					refreshText : "刷新",
					displayInfo : true,
					displayMsg : '一共 {2}条服务员信息',
					emptyMsg : "没有服务员信息"
				})
			});  
			
			
			
			//窗口
			win = desktop.createWindow({
				id:'WCEWM-win',
              	listeners:{
              		'close':function(r){
              			waiterDetailWin.close();
              			auditWaiterWin.close();
              			serviceWindow.close();
              			exchangeWindow.close();
              			restaurantWin.close();
              		}
				},
                title:'服务员管理',
                height:500,               
				items:[grid],
                iconCls: 'icon-wcewm',
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'border'
            });
			

	        var deptStore = new Ext.data.JsonStore({
			    data: [{'id':'1','name':'男'},{'id':'2','name':'女'},{'id':'3','name':'模糊'}],
			    fields: ['id', 'name']
			}) ;
			var waiterDetailForm = new Ext.form.FormPanel({
			    labelAlign: 'left',  //标签位置  
				labelWidth:97,
				anchor:"95%",
                frame: true,  
			    items:[
					new Ext.form.FieldSet({
						id:"waiterDetailFieldSet",
						checkboxToggle: false,     
					    title: '基础资料',
						autoHeight: true,  
						items:[
						   {
								xtype:"hidden",
			                    name:'id',
			                    id:'id',
					    	},{
					    		xtype:"textfield",
			                    fieldLabel: '*姓名',
			                    name:'name',
			                    id:'name',
			                    width: 200,
					    	},
					    	new Ext.form.ComboBox({
								width:120,
					            fieldLabel:'性别',
							    store: deptStore ,
							    displayField:'name',
							    valueField:'id',
							    mode: 'local',
							    emptyText:'请选择性别...',
							    typeAhead: true,
							    triggerAction: 'all',
							    selectOnFocus:true,
					            editable: false,
								hiddenName:"gender",
							}),
							{
					    		xtype:"textfield",
			                    fieldLabel: '*所属餐厅',
			                    name:'bindRestaurant',
			                    id:'bindRestaurant',
			                    width: 200,
			                    listeners:{
			                    	focus:function(r){
			                    		restaurantStore.load({
					            			params:{
			                					start:0,
			                					limit:20,
					            			}
					            		})
					            		restaurantWin.show();
			                    	}
			                    }
					    	}
					    ]
					}),
					new Ext.form.FieldSet({
						id:"recordDetailFieldSet",
						checkboxToggle: false,     
					    title: '积分情况',  
						region:"center",
			    		autoHeight: true, 
						items:[
							new Ext.Panel({
								layout: 'column',  
								style:"margin-bottom:5px",
							    items: [{
							    	xtype:"label",
							    	text:'历史总积分:',
							    	width:100,
							    	style:'line-height:20px;font-size:12px;margin-right:5px'
							    },
								{
						    		xtype:"textfield",
							        name:'historyIntegral',
							        id:'historyIntegral',
							        width:120,
							        style:"margin-right:10px",
				                    cls:'label-input',
				                    readOnly:true,
							    },{
			                    	xtype:"button",
			                    	text: '查询打酒记录',  
			                        name: 'serviceRecordSearch',
			                        id:'serviceRecordSearch',
			                        width:100,
			                        style:"height:20px;width:50px;",
			                        listeners:{
			                        	click:function(){
			                        		serviceStore.load({
			                    				params:{
			                    					start:0,
			                    					limit:20,
			                    					waiterId:waiterId,
			                    				}
			                    			})
											serviceWindow.show();
			                        	}
			                        }
			                    }
							    ]  
							}),
							{
					    		xtype:"textfield",
			                    fieldLabel: '现有积分',
			                    name:'integral',
			                    id:'integral',
			                    cls:'label-input',
			                    readOnly:true,
			                    width: 200
					    	},
							new Ext.Panel({
								layout: 'column',  
								style:"margin-bottom:5px",
							    items: [{
							    	xtype:"label",
							    	text:'已兑换积分:',
							    	width:100,
							    	style:'line-height:20px;font-size:12px;margin-right:5px'
							    },
								{
						    		xtype:"textfield",
							        name:'exchangeIntegral',
							        id:'exchangeIntegral',
				                    cls:'label-input',
				                    readOnly:true,
							        width:120,
							        style:"margin-right:10px",
							    },{
			                    	xtype:"button",
			                    	text: '查询兑换记录',  
			                        name: 'exchangeRecordSearch',
			                        id:'exchangeRecordSearch',
			                        width:100,
			                        style:"height:20px;width:50px;",
			                        listeners:{
			                        	click:function(){
			                        		exchangeStore.load({
			            	    				params:{
			            	    					start:0,
			            	    					limit:20,
			                    					waiterId:waiterId,
			            	    				}
			            	    			})
			                        		exchangeWindow.show();
			                        	}
			                        }
			                    }
							    ]  
							})
					    ]
					})
				]
			})
			/****
			 * 服务员详细信息表单
			 */
			var waiterDetailWin=new Ext.Window({
				title:'更新服务员信息',
				modal:true,//模态化
				constrain :true,
				resizable :false,
				width:500,
	            autoHeight:true,
	            bodyStyle:"max-height:500px;",
				items:[
				    waiterDetailForm
				],
				buttons:[{
					type:"button",
					text:"保存",
					listeners:{
						click:function(){
							if(waiterDetailForm.getForm().isValid()){
								var values = waiterDetailForm.getForm().getValues();
								uptObj = uptObj.data;
								uptObj.name = values.name;
								uptObj.gender = values.gender;
								uptObj.restaurantVO = bindRestaurantVos;
								Ext.Msg.wait("正在更新","请稍后")
								Ext.Ajax.request({
									url:"servant/saveOrUpdate.do",
									params:{
										jsonStr:JSON.stringify(uptObj)
									},
									success:function(response,request){
										Ext.Msg.hide()
										var d = Ext.decode(response.responseText)
										if(d.message!=""){
											showErrorMsg(d.message)
										}else{
											pageStore.reload();
											waiterDetailWin.hide();
										}
									}
								})
							}
						}
					}
				}],
                autoScroll:true,
				buttonAlign:"center",
				closeAction:"hide",
				listeners:{
					hide:function(){
						waiterDetailForm.getForm().reset();
					}
				}
			});
			

			var auditWaiterForm = new Ext.form.FormPanel({
				defaultType: 'textfield',  //默认类型  
				frame:true,
			    labelAlign: 'left',  //标签位置  
				labelWidth:80,
				bodyStyle:"padding:10px 15px",
                items:[{
		            name:'auditingStatus',
					xtype:"radiogroup",
					fieldLabel: '审核状态',
					width:200,
					items:[
						{
				            name:'auditingStatus',
				        	xtype:"radio",
							boxLabel:"未通过",
							inputValue: '0',
						},{
				            name:'auditingStatus',
				        	xtype:"radio",
				        	boxLabel:"通过",
							inputValue: '1',
				        }
					]
				},{
                    fieldLabel: '审核人',
                    name:'auditingPerson',
                    id:'auditingPerson',
                    width: 200,
					allowBlank:false,
		    	},{
		    		xtype:"textarea",
                    fieldLabel: '审核备注',
                    width: 200,
                    name:'memo',
                    id:'memo',
		    	}
		    	],
			});
			/****
			 * 审核表单
			 */
			var auditWaiterWin=new Ext.Window({
				title:'审核',
				modal:true,//模态化
				constrain :true,
				resizable :false,
				width:360,
	            autoHeight:true,
	            bodyStyle:"max-height:500px;",
				items:[
				    auditWaiterForm
				],
				buttons:[{
					type:"button",
					text:"保存",
					listeners:{
						click:function(){
							if(auditWaiterForm.getForm().isValid()){
								var values = auditWaiterForm.getForm().getValues();
								uptObj = uptObj.data;
								uptObj.auditingStatus = values.auditingStatus;
								uptObj.auditingPerson = values.auditingPerson;
								uptObj.memo = values.memo;
								Ext.Msg.wait("正在更新","请稍后")
								Ext.Ajax.request({
									url:"servant/saveOrUpdate.do",
									params:{
										jsonStr:JSON.stringify(uptObj)
									},
									success:function(response,request){
										Ext.Msg.hide()
										var d = Ext.decode(response.responseText)
										if(d.message!=""){
											showErrorMsg(d.message)
										}else{
											pageStore.reload();
											auditWaiterWin.hide();
										}
									}
								})
							}
						}
					}
				}],
                autoScroll:true,
				buttonAlign:"center",
				closeAction:"hide",
				listeners:{
					hide:function(){
						auditWaiterForm.getForm().reset();
					}
				}
			});
            
            
            
			
			var serviceStore = new Ext.data.JsonStore({
				url:"waiterServiceRecord/page.do",
				totalProperty:"total",
				root:"rows",
				fields:["id","wineOutTime","waiterName","gainIntegral","restaurantName","orderNo","winName","outCapacity"]
			})
			
			var serviceCm = new Ext.grid.ColumnModel([
				new Ext.grid.RowNumberer(),
				{header:"打酒时间",dataIndex:"wineOutTime",width:200},
				{header:"服务员",dataIndex:"waiterName",width:80},
				{header:"获得积分",dataIndex:"gainIntegral",width:80},
				{header:"出酒餐厅",dataIndex:"restaurantName",width:150},
				{header:"订单编号",dataIndex:"orderNo",width:200},
				{header:"酒品名称",dataIndex:"winName",width:150},
				{header:"出酒量",dataIndex:"outCapacity",width:80}
			]);
			var servicePanel = new Ext.grid.GridPanel({
				cm:serviceCm,
				loadMask:true,
				stripeRows:true,
				store:serviceStore,
				viewConfig:{
					forceFit:true,
					enableRowBody:true
				},
				region:'center',
				tbar:[
					{
						xtype:"textfield",
	                	readOnly:true,
	                	name:'serviceBeginDate',
	                    id:'serviceBeginDate',
	                    cls:"Wdate",
	                	allowBlank:false,
	                	blankText:"请选择开始时间",
	                	listeners:{
	                		focus:function(){
	                			WdatePicker({el:this.el.dom,dateFmt:"yyyy-MM-dd"});
	                		}
	                	}
					},
					new Ext.form.Label({
						width:80,
						text:"至",
						style:"margin:0 5px"
					}),
					{
						xtype:"textfield",
	                	readOnly:true,
	                	name:'serviceEndDate',
	                    id:'serviceEndDate',
	                    cls:"Wdate",
	                	allowBlank:false,
	                	blankText:"请选择结束时间",
	                	listeners:{
	                		focus:function(){
	                			WdatePicker({el:this.el.dom,dateFmt:"yyyy-MM-dd"});
	                		}
	                	}
					},
			        new Ext.form.TextField({
			       			id:"serviceKeyword",
			       			name:"serviceKeyword",
			       			fieldLabel:"关键字",
						    inputType:'text',
						    emptyText:'输入餐厅名称、服务员名字、订单编号进行搜索',
				            allowBlank : false,
				            width:300,
				            style:'margin:0 10px'
					}),
			        {
			            text: "搜索",  
			            iconCls: "icon-search",  
			            handler: function(){  
			            	var beginDate = new Date(Ext.get("serviceBeginDate").getValue()).getTime();
			            	var endDate = new Date(Ext.get("serviceEndDate").getValue()).getTime();
			            	var keyword = Ext.get("serviceKeyword").getValue();
			            	serviceStore.load({
								params:{
									start:0,
									limit:20,
									waiterId:waiterId,
									beginDate:beginDate,
									endDate:endDate,
									keyword:keyword == '输入餐厅名称、服务员名字、订单编号进行搜索'?'':keyword
								}
							});
							
			            } 
					}
				],
				bbar:new Ext.PagingToolbar({
        			pageSize : 20,
					store : serviceStore,
					beforePageText : "第",
					afterPageText : "页，共{0}页",
					lastText : "尾页",
					nextText : "下一页",
					prevText : "上一页",
					firstText : "首页",
					refreshText : "刷新",
					displayInfo : true,
					displayMsg : '一共 {2}条服务记录信息',
					emptyMsg : "没有服务记录信息"
				})
			})
			
			var serviceWindow = new Ext.Window({
				modal:true,//模态化
                shim:false,
                animCollapse:false,
                constrainHeader:true,
				layout:"border",
				width:800,
			    frame: true,  
				closeAction:"hide",
				height:350,
				listeners:{
					hide:function(){	
						Ext.getCmp("serviceBeginDate").setValue("");
						Ext.getCmp("serviceEndDate").setValue("");
						Ext.getCmp("serviceKeyword").setValue("");
					}
				},
				title:'打酒记录',
				items:[servicePanel],
			})
        
        	        
			
			var exchangeStore = new Ext.data.JsonStore({
				url:"exchangeIntegral/page.do",
				totalProperty:"total",
				root:"rows",
				fields:["id","expendIntegralTime","waiterName","expendIntegral","restaurantName","orderNo","winName","orderStatus"]
			})
			
			var exchangeCm = new Ext.grid.ColumnModel([
				new Ext.grid.RowNumberer(),
				{header:"兑换时间",dataIndex:"expendIntegralTime",width:200},
				{header:"服务员",dataIndex:"waiterName",width:80},
				{header:"消耗积分",dataIndex:"expendIntegral",width:80},
				{header:"所属餐厅",dataIndex:"restaurantName",width:150},
				{header:"订单编号",dataIndex:"orderNo",width:200},
				{header:"兑换物品",dataIndex:"winName",width:150},
				{header:"状态",dataIndex:"orderStatus",width:80}
			]);
			var exchangePanel = new Ext.grid.GridPanel({
				cm:exchangeCm,
				loadMask:true,
				stripeRows:true,
				store:exchangeStore,
				viewConfig:{
					forceFit:true,
					enableRowBody:true
				},
				region:'center',
				tbar:[
					{
						xtype:"textfield",
	                	readOnly:true,
	                	name:'exchangeBeginDate',
	                    id:'exchangeBeginDate',
	                    cls:"Wdate",
	                	blankText:"请选择开始时间",
	                	listeners:{
	                		focus:function(){
	                			WdatePicker({el:this.el.dom,dateFmt:"yyyy-MM-dd"});
	                		}
	                	}
					},
					new Ext.form.Label({
						width:80,
						text:"至",
						style:"margin:0 5px"
					}),
					{
						xtype:"textfield",
	                	readOnly:true,
	                	name:'exchangeEndDate',
	                    id:'exchangeEndDate',
	                    cls:"Wdate",
	                	blankText:"请选择结束时间",
	                	listeners:{
	                		focus:function(){
	                			WdatePicker({el:this.el.dom,dateFmt:"yyyy-MM-dd"});
	                		}
	                	}
					},
			        new Ext.form.TextField({
			       			id:"exchangeKeyword",
			       			name:"exchangeKeyword",
			       			fieldLabel:"关键字",
						    inputType:'text',
						    emptyText:'输入餐厅名称、服务员名字、订单编号进行搜索',
				            width:300,
				            style:'margin:0 10px'
					}),
			        {
			            text: "搜索",  
			            iconCls: "icon-search",  
			            handler: function(){  
			            	var beginDate = new Date(Ext.get("exchangeBeginDate").getValue()).getTime();
			            	var endDate = new Date(Ext.get("exchangeEndDate").getValue()).getTime();
			            	var keyword = Ext.get("exchangeKeyword").getValue();
			            	exchangeStore.load({
								params:{
									start:0,
									limit:20,
									waiterId:waiterId,
									beginDate:beginDate,
									endDate:endDate,
									keyword:keyword=='输入餐厅名称、服务员名字、订单编号进行搜索'?'':keyword
								}
							});
			            } 
					}
				],
				bbar:new Ext.PagingToolbar({
        			pageSize : 20,
					store : serviceStore,
					beforePageText : "第",
					afterPageText : "页，共{0}页",
					lastText : "尾页",
					nextText : "下一页",
					prevText : "上一页",
					firstText : "首页",
					refreshText : "刷新",
					displayInfo : true,
					displayMsg : '一共 {2}条兑换记录信息',
					emptyMsg : "没有兑换记录信息"
				})
			})
			
			var exchangeWindow = new Ext.Window({
				modal:true,//模态化
                shim:false,
                animCollapse:false,
                constrainHeader:true,
				layout:"border",
				width:800,
			    frame: true,  
				closeAction:"hide",
				height:350,
				listeners:{
					hide:function(){
						Ext.getCmp("exchangeBeginDate").setValue("");
						Ext.getCmp("exchangeEndDate").setValue("");
						Ext.getCmp("exchangeKeyword").setValue("");
					}
				},
				title:'积分兑换记录',
				items:[exchangePanel],
			})
			

			var statusStore = new Ext.data.JsonStore({
	    	   id:'id',
	    	   totalProperty:"total",
	    	   root:"rows",
	    	   url: 'category/list.do',
	    	   fields: ['id', 'name']
			}) ;
	        statusStore.load();
			var restaurantStore = new Ext.data.JsonStore({
			    id:'id',
			    url: 'restaurant/page.do',
				totalProperty:"total",
				root:"rows",
				fields: ['id', 'name', 'runType','contractVo','telephone','province','city','area','address','operatorList','memo']
			});
			var sm = new Ext.grid.CheckboxSelectionModel()	
			
			var restaurantColums = [
				new Ext.grid.RowNumberer(),
				sm,{
	            id:"contractVo",   
	            header:"合同信息",   
	            width:150,   
	            dataIndex:"contractVo",
	            renderer:function(v,m,record){
	            	if(v){
		            	return v.contractNum
	            	}else{
	            		return "";
	            	}
	            }
	        },  
	        {  
	            id:"name",   
	            header:"餐厅名称",   
	            width:150,   
	            dataIndex:"name"  
	        },  
	        {  
	            id:"runType",
	            header:"使用场景",     
	            width:80,   
	            dataIndex:"runType",
	            renderer:function(v,m,record){
	             	return v?statusStore.getById(v).data.name:'未知'
	            }
	        },
	        {  
	            id:"telephone",   
	            header:"联系电话",   
	            width:100,     
	            dataIndex:"telephone",
	        },
	        {  
	            id:"address",   
	            header:"详细地址",     
	            width:150,   
	            dataIndex:"address",
	            renderer:function(v,m,record){
	             	var drow = record.data;
	             	return drow.province?drow.province:""+drow.city?drow.city:""+drow.area?drow.area:""+v;
	            }
	        } 
	  		];
	  		
	  		var restaurant_refresh_bar = {  
	            text: "刷新",  
	            iconCls: "icon-refresh",  
	            handler: function(){  
	            	restaurantStore.reload();//刷新列表数据
	            }  
	       };
	  		
	       var restaurant_confirm_bar = {  
	            text: "确定",  
	            iconCls: "icon-confirm",  
	            handler: function(){  
	            	var records = sm.getSelections();
	            	if(records.length>0){
	            		bindRestaurantVos = [];
	            		var strs = '';
	            		for(var i=0;i<records.length;i++){
	            			var r = records[i].data;
	            			strs += r.name+",";
	            			bindRestaurantVos.push(r);
	            		}
	            		strs = strs.substring(0,strs.length-1);
	            		waiterDetailForm.getForm().findField("bindRestaurant").setValue(strs);
	            		restaurantWin.hide();
					}
	            }  
	       };
	       var restaurant_keywordTf = new Ext.form.TextField({
	       			id:"restaurant_keyword",
	       			name:"keyword",
	       			fieldLabel:"关键字",
			    	emptyText:'输入餐厅名称、合同编号搜索',
				    inputType:'text',
		            width:300,
		            style:'margin-right:15px'
			});
	       var restaurant_selectedRunType = '';
			var restaurant_statusCbx = new Ext.form.ComboBox({
				width:120,
				id:'searchRunType',
	            fieldLabel:'使用场景',
			    store: statusStore ,
			    displayField:'name',
			    valueField:'id',
			    mode: 'local',
			    emptyText:'请选择状态...',
			    typeAhead: true,
			    triggerAction: 'all',
			    selectOnFocus:true,
	            editable: false,
	            listeners:{
					select:function(e){
						restaurant_selectedRunType = e.value;
					}
				}
			});
			var restaurant_searchBtn = {
	            text: "搜索",  
	            iconCls: "icon-search",  
	            handler: function(){  
					restaurantStore.load({
						params:{
							start:0,
							limit:10,
							keyword:Ext.get("restaurant_keyword").getValue()!='输入餐厅名称、合同编号搜索'?Ext.get("restaurant_keyword").getValue():'',
							runType:restaurant_selectedRunType
						}
					});//刷新列表数据
	            } 
			};
			var restaurantGrid = new Ext.grid.EditorGridPanel({  
				    anchor: '100%',  
	            	height:400,     
				    frame: true,  
				    tbar: [  
				       restaurant_refresh_bar,
				       "->",
				       restaurant_keywordTf,
				       restaurant_statusCbx,
				       restaurant_searchBtn
				    ],  
				    store: restaurantStore,
				    sm:sm,
				    columns: restaurantColums,
				    region:"center",
				    autoExpandColumn: "address", 
					bbar:[new Ext.PagingToolbar({
	        			pageSize : 10,
						store : restaurantStore,
						beforePageText : "第",
						afterPageText : "页，共{0}页",
						lastText : "尾页",
						nextText : "下一页",
						prevText : "上一页",
						firstText : "首页",
						refreshText : "刷新",
						displayInfo : true,
						displayMsg : '一共 {2}条餐厅信息',
						emptyMsg : "没有餐厅信息"
					}),"->",restaurant_confirm_bar]
				});  
			
			var restaurantWin=new Ext.Window({
				title:'选择您要绑定的餐厅',
				closeAction:"hide",//关闭事件触发hide事件
				modal:true,//模态化
				constrain :true,
				resizable :false,
				width:780,
	            autoHeight:true, 
	            bodyStyle:"min-height:300px",
				items:[restaurantGrid],
	            iconCls: 'icon-wcen',
	            shim:false,
	            animCollapse:false,
	            constrainHeader:true,
	            layout: 'border'
			});
 
		}
        win.show();
    }
});

