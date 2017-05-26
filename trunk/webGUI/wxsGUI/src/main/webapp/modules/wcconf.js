/*
 * wxconf window
 */
Emin.WCCONFWindow = Ext.extend(Ext.app.Module, {
    id:'WCCONF-win',
    init : function(){
        this.launcher = {
            text: '设备管理',
            iconCls:'icon-wcconf',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('WCCONF-win');
		if(!win){
			var desktop = this.app.getDesktop();
			
            var uptId='',uptRunType = '',uptRestId = '';
            var detailRow = null,rBindStatus=true;
			/****从服务器上拉取公众号信息列表*****/
			var pageStore = new Ext.data.JsonStore({
			    id:'id',
			    url: 'device/page.do',
				totalProperty:"total",
				root:"rows",
				fields: ['id', 'code', 'ipcCode','simCode','runType','bindStatus','restaurantId','restaurantName','bindTime','brStatus','channelSum','alarmValue','warningValue','channelList']
			});   
			pageStore.load({
				params:{
					start:0,
					limit:20,
					restaurant:0
				}
			})
			
	       var statusStore = new Ext.data.JsonStore({
	    	   id:'id',
	    	   totalProperty:"total",
	    	   root:"rows",
	    	   url: 'category/list.do',
	    	   fields: ['id', 'name']
			}) ;
	       statusStore.load();
			
			
			
			var columns = [  
		        {  
		            id:"code",   
		            header:"设备资产编号",   
		            width:150,   
		            dataIndex:"code"  
		        },  
		        {  
		            id:"restaurantName",   
		            header:"绑定餐厅名称",       
		            width:150,   
		            dataIndex:"restaurantName"
		        }, 
		        {  
		            id:"ipcCode",   
		            header:"工控机编号",   
		            width:100,     
		            dataIndex:"ipcCode",   
		        },
		        {  
		            id:"runType",
		            header:"状态",     
		            width:80,   
		            dataIndex:"runType",   
		            renderer:function(v,m,record){
		            	return v?statusStore.getById(v).data.name:'未知'
		            }
		        },
		        {  
		            id:"simCode",   
		            header:"SIM卡号",     
		            width:150,   
		            dataIndex:"simCode",   
		        },
		        {
		        	dataIndex:'operation',
                      xtype:'uxactioncolumn',  
                      header:'操作',  
                      autoWidth:false,  
                      width:180,                 
                      items: [{  
                          iconCls:'icon-edit',  
                          tooltip:'修改',  
                          text:'修改',  
                          stopSelection:false,  
                          scope:this,  
                          handler:function(grid, rowIndex, colIndex){  
                        	detailRow = grid.getStore().getAt(rowIndex).data;
                        	uptId = detailRow.id;
                        	uptRunType = detailRow.runType;
                        	uptRestId = detailRow.restaurantId;
                        	if(!detailRow.bindStatus){
                        		newBindRestSet();
                        	}else{
                        		reBindRestSet();
                        	}
                        	
							var channelList = detailRow.channelList;
							/*******
							 * 渲染酒品通道集合数据
							 * @param {Object} channelList 通道设置信息集合
							 */
							channelWinSetFieldSet.removeAll();
			    			for(var i=1; i<=(channelList.length>6?6:channelList.length); i++){
								channelWinSetFieldSet.add(loadWcChannelRow(i,channelList[i-1]));
			    			}
			    			channelWinSetFieldSet.doLayout();

                        	wcDetailWin.show();
                        	wcDetailForm.getForm().setValues(detailRow);
                          }  
                      }]
	        	} 
		    ];
			var add_bar = {  
	            text: "添加",  
	            iconCls: "icon-add",  
	            handler: function(){  
	            	newBindRestSet();
                	wcDetailWin.show();
                	channelWinSetFieldSet.hide();
	            }  
	       };
			var refresh_bar = {  
	            text: "刷新",  
	            iconCls: "icon-refresh",  
	            handler: function(){  
	            	pageStore.reload();//刷新列表数据
	            }  
	       };
	       var keywordTf = new Ext.form.TextField({
	       			id:"keyword",
	       			name:"keyword",
	       			fieldLabel:"关键字",
				    inputType:'text',
				    emptyText:'输入餐厅名称、设备资产编号、工控机编号搜索',
		            width:300,
		            style:'margin-right:15px'
			});
	       var selectedRunType = '';
			var statusCbx = new Ext.form.ComboBox({
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
				hiddenName:"id",
	            listeners:{
					select:function(e){
						selectedRunType = e.value;
					}
				}
			});
			var searchBtn = {
	            text: "搜索",  
	            iconCls: "icon-search",  
	            handler: function(){  
					pageStore.load({
						params:{
							start:0,
							limit:20,
							keyword:Ext.get("keyword").getValue()!='输入餐厅名称、设备资产编号、工控机编号搜索'?Ext.get("keyword").getValue():'',
							runType:selectedRunType,
							restaurant:0
						}
					});//刷新列表数据
	            } 
			};
			
			var grid = new Ext.grid.EditorGridPanel({  
			    width: 500,  
			    height: 300,  
			    frame: true,  
			    tbar: [  
			       add_bar,
			       refresh_bar,
			       "->",
			       keywordTf,
			       statusCbx,
			       searchBtn
			    ],  
			    store: pageStore,
			    columns: columns,  
			    region:"center",
			    autoExpandColumn: "restaurantName", 
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
					displayMsg : '一共 {2}条设备信息',
					emptyMsg : "没有设备信息"
				})
			});  
			
			
			
			//窗口
			win = desktop.createWindow({
				id:'WCCONF-win',
              	listeners:{
              		'close':function(r){
              			wcDetailWin.close();
              			restaurantWin.close();
              		}
				},
                title:'设备管理',
                height:500,               
				items:[grid],
                iconCls: 'icon-wcconf',
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'border'
            });
        
			
			/***********
			 * 设备详情表单
			 */
			
			
			//Generate-Form-Title 生成表单标题
			function grtFtitle(lname,id){
				return {
						id:id,
						name:name,
			    		text:lname,
			    		autoWidth : true,
			    		style:"line-height:20px;margin:0 15px;"
			    };
			}
			
			//Generate-Alarm-Setting-Panel 生成预警设置面板
			function grtAsp(){
				return new Ext.Panel({
					width:535,	
					defaultType: 'label',  //默认类型  
					layout: 'table',  
        			layoutConfig: {columns: 6},//定义了一共要分3列  
			        items: [  
			            grtFtitle("短信预警值(两)"),  
				    	new Ext.form.NumberField({
				    		width:100,
				    		id:"alarmValue",
				    		name:"alarmValue"
				    	}),
				    	grtFtitle("售空值(两)"),
				    	new Ext.form.NumberField({
				    		width:100,
				    		id:"warningValue",
				    		name:"warningValue"
				    	})
			        ]  
				});
			}
			
			function newBindRestSet(){
				Ext.getCmp("bindTime").hide();
				Ext.getCmp("bindStatus").hide();
				Ext.getCmp("restaurantName").hide();
				Ext.getCmp("resetBind").setText("绑定新餐厅");
			}
			
			function reBindRestSet(){
				Ext.getCmp("bindTime").show();
				Ext.getCmp("bindStatus").show();
				Ext.getCmp("restaurantName").show();
				Ext.getCmp("resetBind").setText("重新绑定");
			}
			
			var winStore = new Ext.data.JsonStore({
			    id:'id',
			    url: 'product/page.do',
				totalProperty:"total",
				root:"rows",
				fields: ["id","name"]
			}); 
			
			/**
			 * 生成酒品通道行和数据
			 * @param {Object} no 行编号
			 * @param {Object} row 数据详情
			 */
			function loadWcChannelRow(no, row){
				return new Ext.Panel({
					id:"channel"+no,
					anchor:'95%',	
					defaultType: 'label',  //默认类型  
					layout: 'column',  
					bodyStyle:"margin-bottom:10px",
			        items: [  
			        	{
				    		text:"通道"+no,
				    		autoWidth : true,
				    		style:"line-height:20px;margin:0 15px"
					    },
				    	new Ext.form.ComboBox({
				    		width:150,
				    		value:row?row.liquorInfoId:'',
	   						store:winStore, 
						    displayField:'name',
						    valueField:'id',
						    mode: 'local',
						    emptyText:'请选择酒品...',
				            allowBlank : false,
						    typeAhead: true,
						    triggerAction: 'all',
						    selectOnFocus:true,
				            editable: false,
							hiddenName:"liquorInfoId",
				    	}),
			        	{
				    		text:"剩余量",
				    		autoWidth : true,
				    		style:"line-height:20px;margin:0 15px"
					    },
				    	new Ext.form.NumberField({
				    		width:100,
				    		value:row?row.allowance:'',
				            allowBlank : false,
				    	}),
			        	{
				    		text:"显示排序",
				    		autoWidth : true,
				    		style:"line-height:20px;margin:0 15px"
					    },
				    	new Ext.form.NumberField({
				    		width:80,
				            allowBlank : false,
				    		value:row?row.sort:'',
				    	}),{
					    	xtype:"hidden",
					    	value:row?row.id:'',
					    }
			        ]  
				});
				
			}
			
			
			
			var channelWinSetFieldSet = new Ext.form.FieldSet({
				checkboxToggle: false,     
			    title: '通道酒水设置(最多6个)',  
	    		autoHeight: true,  
				style:"padding-top:15px",
				items:[
			    ]
			});
			var restaurantDetailFieldSet = {
            	xtype: 'fieldset',  
			    anchor:"95%",
			    checkboxToggle: false,     
			    title: '绑定信息',  
			    defaultType: 'textfield',  //默认类型  
			    layout : 'column',
			    items:[
			    	{
                        name: 'bindTime',
                        id:'bindTime',
                        cls:'label-input',
                        readOnly:true,
                        width:150,
                        listeners:{
                            beforerender:function(r){
                            	r.setValue(r.value?r.value:'未知')
				            }
                        }
                    },
			    	{
                        name: 'bindStatus',
                        id:'bindStatus',
                        cls:'label-input',
                        readOnly:true,
                        width:100,
                        listeners:{
                            beforerender:function(r){
                            	r.setValue(r.value?'已绑定':'未绑定');
				            }
                        }
                    },
			    	{
                        name: 'restaurantName',
                        id:'restaurantName',
                        cls:'label-input',
                        style:"font-weight:bold",
                        readOnly:true,

                    },{
                    	xtype:"button",
                    	text: '重新绑定',  
                        name: 'resetBind',
                        id:'resetBind',
                        width:100,
                        style:"height:20px;width:50px;",
                        listeners:{
                        	click:function(){
								restaurantStore.load({
									params:{
										start:0,
										limit:10
									}
								}); 
                        		restaurantWin.show();
                        	}
                        }
                    }
			    ]
            };
			var wcDetailForm=new Ext.form.FormPanel({
				labelAlign: 'left',  //标签位置  
                buttonAlign: 'center',  
				labelWidth:100,
				anchor:"95%",
                frame: true,  
                items:[
                {
                	xtype: 'fieldset',  
				    anchor:"95%",
				    checkboxToggle: false,     
				    title: '设备信息',  
				    autoHeight: true,  
				    defaults: {width: 300},  
				    defaultType: 'textfield',  //默认类型  
				    items:[
				    	{  
                            fieldLabel: '*机器编码', 
                            allowBlank:false,
                            name: 'code',
                            id:'code',
                        },{  
                            fieldLabel: '*工控机编码', 
                            allowBlank:false, 
                            name: 'ipcCode',
                            id:'ipcCode',
                        },{
                        	fieldLabel: '*SIM卡号', 
                            allowBlank:false, 
                            name: 'simCode',
                            id:'simCode',
                        }
				    ]
                },restaurantDetailFieldSet,{
                	xtype: 'fieldset',  
				    checkboxToggle: false,     
				    title: '酒品信息',  
				    anchor:"95%",
				    autoHeight: true,  
				    defaultType: 'textfield',  //默认类型  
				    items:[
				    	{  
		                	xtype: 'numberfield',  
                        	fieldLabel: '酒柜通道数量',  
                        	labelSeparator :":",
                            name: 'channelSum',
                            id:'channelSum',
                            emptyText:0,
                            minValue:0,
                            maxValue:6,
                            width:100,
                            enableKeyEvents:true,
                            listeners:{
	                            beforerender:function(r){
	                            	r.setValue(r.value>6?6:r.value)
                            		if(r.getValue==0){
                            			channelWinSetFieldSet.hide();
                            		}else{
                            			channelWinSetFieldSet.show();
                            		}
					            },
                            	blur:function(r){
                            		var channelCount = r.getValue();
                            		if(!uptRestId&&channelCount>0){
                            			showMsg("请先绑定一个餐厅哦");
                            			r.setValue(0);
                            			return false;
                            		}
                            		var  channelCount = r.getValue();
                            		if(channelCount<=0){
                            			r.setValue(0);
                            			channelWinSetFieldSet.hide();
                            		}else{
                            			if(channelCount>6){
                                			r.setValue(6);
                            			}
                            			channelWinSetFieldSet.show();
                            		}
                        			var channelList = detailRow?detailRow.channelList:null;
									/*******
									 * 渲染酒品通道集合数据
									 * @param {Object} channelList 通道设置信息集合
									 */
									channelWinSetFieldSet.removeAll();
					    			for(var i=1; i<=r.getValue(); i++){
										channelWinSetFieldSet.add(loadWcChannelRow(i,channelList?channelList[i-1]:null));
					    			}
					    			channelWinSetFieldSet.doLayout();
                            	}
                            	
                            }
                        },channelWinSetFieldSet,{
		                	xtype: 'fieldset',  
						    checkboxToggle: false,     
						    title: '预警设置',  
				    		autoHeight: true,  
						    items:[
						    	grtAsp(),
						    ]
		                }
				    ]
                }
                			
                ],
                
                
				
			});
			var wcDetailWin=new Ext.Window({
				items:[wcDetailForm],
				closeAction:"hide",//关闭事件触发hide事件
				modal:true,//模态化
				constrain :true,
				resizable :false,
				width:666,
				height:500,   
                autoScroll:true,
				listeners:{
					hide:function(){//监听hide事件
						wcDetailForm.getForm().reset();
					}
				},
				buttons:[{
					text:"保存修改",
					handler:function(){ 
						if(wcDetailForm.getForm().isValid()){
						 	var cCount = Ext.get("channelSum").getValue();
						 	var channelList = [];
						 	for(var i=1;i<=cCount;i++){
						 		var keys = channelWinSetFieldSet.findById("channel"+i).items.keys;
						 		var obj = {
						 			liquorInfoId:Ext.getCmp(keys[1]).getValue(),
						 			liquorName:Ext.getCmp(keys[1]).getRawValue(),
						 			allowance:Ext.getCmp(keys[3]).getValue(),
						 			sort:Ext.getCmp(keys[5]).getValue(),
						 			id:Ext.getCmp(keys[6]).getValue()
						 		}
						 		channelList.push(obj);
						 	}
						 	var updateObj = {
						 		"id":uptId,
						 		"restaurantId":uptRestId,
							 	"code":Ext.getCmp("code").getValue(),
							 	"ipcCode":Ext.getCmp("ipcCode").getValue(),
							 	"simCode":Ext.getCmp("simCode").getValue(),
							 	"bindStatus":Ext.getCmp("bindStatus").getValue()=='已绑定'?1:0,
							 	"bindTime":new Date(Ext.getCmp("bindTime").getValue()).getTime(),
							 	"restaurantName":Ext.getCmp("restaurantName").getValue(),
							 	"channelSum":Ext.getCmp("channelSum").getValue(),
							 	"alarmValue":Ext.getCmp("alarmValue").getValue(),
							 	"warningValue":Ext.getCmp("warningValue").getValue(),
								"channelList":channelList
						 	}
							Ext.Msg.wait("正在保存","请稍后")
							Ext.Ajax.request({
								url:"device/saveOrUpdate.do",
								params:{
									jsonStr:JSON.stringify(updateObj)
								},
								success:function(response,request){
									Ext.Msg.hide()
									var d = Ext.decode(response.responseText)
									if(d.message!=""){
										showErrorMsg("保存报错")
									}else{
										pageStore.reload();	
										wcDetailWin.hide();
									}
								}
							})
						}
					}
				}],
				buttonAlign:"center",
				title:"设备详情",
				closeAction:"hide"
			});
		}
		var restaurantStore = new Ext.data.JsonStore({
		    id:'id',
		    url: 'restaurant/page.do',
			totalProperty:"total",
			root:"rows",
			fields: ['id', 'name', 'runType','contractVo','telephone','province','city','area','address','operatorList','memo']
		});   
		var restaurantColums = [{
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
            editor: new Ext.form.ComboBox({  
                editable: false,  
                displayField: "name",  
                valueField:'id',
                mode: "local",  
                triggerAction: "all",  
                store: statusStore
            }),
            renderer:function(v,m,record){
             	return v?statusStore.getById(v).data.name:'未知'
            }
        },
        {  
            id:"telephone",   
            header:"联系电话",   
            width:100,     
            dataIndex:"telephone",   
            editor:new Ext.form.TextField({  
                allowBlank:false  
            })  
        },
        {  
            id:"address",   
            header:"详细地址",     
            width:150,   
            dataIndex:"address",   
            editor:new Ext.form.TextField({  
                allowBlank:false  
            }),
            renderer:function(v,m,record){
             	var drow = record.data;
             	return drow.province?drow.province:""+drow.city?drow.city:""+drow.area?drow.area:""+v;
            }
        },
        {
        	dataIndex:'operation',
              xtype:'uxactioncolumn',  
              header:'操作',  
              autoWidth:false,  
              width:80,                 
              items: [{  
                  iconCls:'icon-add',  
                  tooltip:'选择',  
                  text:'选择',  
                  stopSelection:false,  
                  scope:this,  
                  handler:function(grid, rowIndex, colIndex){  
                	var selectObj = grid.getStore().getAt(rowIndex).data;
                	winStore.load({
                		params:{
                			categoryId:selectObj.runType
                		}
                	})
                	uptRestId = selectObj.id;
                	Ext.getCmp("bindTime").setValue(new Date().format("Y-m-d"));
                	Ext.getCmp("bindStatus").setValue("已绑定");
                	Ext.getCmp("restaurantName").setValue(selectObj.name);
            		reBindRestSet();
                	restaurantWin.hide();
                  }  
              }]
    	} 
  		];
  		
  		var restaurant_refresh_bar = {  
            text: "刷新",  
            iconCls: "icon-refresh",  
            handler: function(){  
            	restaurantStore.reload();//刷新列表数据
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
			hiddenName:"id",
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
			    columns: restaurantColums,  
			    region:"center",
			    autoExpandColumn: "address", 
				bbar:new Ext.PagingToolbar({
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
				})
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
        win.show();
    }
});

