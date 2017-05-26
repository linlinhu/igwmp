/*
 * wxconf window
 */
Emin.WCENWindow = Ext.extend(Ext.app.Module, {
    id:'WCEN-win',
    init : function(){
        this.launcher = {
            text: '餐厅管理',
            iconCls:'icon-wcen',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('WCEN-win');
		if(!win){
			var desktop = this.app.getDesktop();
			
			var operatorList = [],uptObj={},globalRunType=1;
			/****从服务器上拉取公众号信息列表*****/
			pageStore = new Ext.data.JsonStore({
			    id:'id',
			    url: 'restaurant/page.do',
				totalProperty:"total",
				root:"rows",
				fields: ['id', 'name', 'runType','contractVo','telephone','province','city','area','address','operatorList','memo','businessScope','perCapitaConsumption']
			});   
			pageStore.load({
				params:{
					start:0,
					limit:20
				}
			}); 
			
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
		            id:"id",   
		            header:"编号",   
		            width:80,   
		            dataIndex:"id",
		        },             
				{
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
		            width:200,   
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
		             	return v?statusStore.getById(v).data.name:"未知"
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
		             	var pro = drow.province?drow.province:"";
		             	var city = drow.city?drow.city:"";
		             	var area = drow.area?drow.area:"";
		             	var address = drow.address?drow.address:"";
		             	return pro+city+area+address;
		            }
		        },
		        {
		        	dataIndex:'operation',
                      xtype:'uxactioncolumn',  
                      header:'操作',  
                      autoWidth:false,  
                      width:80,                 
                      items: [{  
                          iconCls:'icon-edit',  
                          tooltip:'修改',  
                          text:'修改',  
                          stopSelection:false,  
                          scope:this,  
                          handler:function(grid, rowIndex, colIndex){
                        	var record = grid.getStore().getAt(rowIndex);
                        	uptObj = grid.getStore().getAt(rowIndex).data;
                        	var contract = uptObj.contractVo;
                        	if(contract){
								contractFieldSet.removeAll();
	                        	contractFieldSet.add({
	                            	fieldLabel: '合同编号',
	                            	value:contract.contractNum,
	                            	cls:'label-input',
	                        	});
	                        	contractFieldSet.add({
	                            	fieldLabel: '合作方式',
	                            	value:contract.cooperationVO?contract.cooperationVO.name:'暂无',
	                            	cls:'label-input',
	                        	});
	                        	contractFieldSet.add({
	                            	value:contract.cooperationVO?contract.cooperationVO.memo:'暂无',
	                            	cls:'label-input',
	                        	});
				    			contractFieldSet.doLayout();
                        	}else{
                        		contractFieldSet.hide();
                        	}
							var operatorLst = uptObj.operatorList;
							if(operatorLst&&operatorLst.length>0){
								/*******
								 * 渲染酒品通道集合数据
								 * @param {Object} channelList 通道设置信息集合
								 */
								operatorFieldSet.removeAll();
				    			for(var i=0; i<operatorLst.length; i++){
									operatorFieldSet.add(loadOperatorRow(operatorLst[i]));
				    			}
				    			operatorFieldSet.add({
		                        	xtype:"button",
		                        	text: '重新绑定',  
		                            name: 'reBind',
		                            id:'reBind',
		                            width:100,
		                            style:"height:30px;margin-top:10px",
		                            listeners:{
		                            	click:function(){
		                            		opratorStore.load({
												params:{
													start:0,
													limit:20
												}
											}); 
		                            		operatorWin.show();
		                            	}
		                            }
		                        });
				    			operatorFieldSet.doLayout();
							}else{
								operatorFieldSet.removeAll();
								operatorFieldSet.add({
		                        	xtype:"button",
		                        	text: '绑定运营者',  
		                            name: 'reBind',
		                            id:'reBind',
		                            width:100,
		                            style:"height:30px;",
		                            listeners:{
		                            	click:function(){
		                            		opratorStore.load({
												params:{
													start:0,
													limit:20
												}
											}); 
		                            		operatorWin.show();
		                            	}
		                            }
		                        });
				    			operatorFieldSet.doLayout();
							}

                        	wcDetailWin.show();
                        	wcDetailForm.getForm().loadRecord(record);
                          }  
                      }]
	        	} 
		    ];
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
	       			emptyText:"输入餐厅名称、合同编号搜索",
				    inputType:'text',
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
							keyword:Ext.get("keyword").getValue()=='输入餐厅名称、合同编号搜索'?'':Ext.get("keyword").getValue(),
							runType:selectedRunType
						}
					});//刷新列表数据
	            } 
			};
			
			var grid = new Ext.grid.EditorGridPanel({  
			    width: 500,  
			    height: 300,  
			    frame: true,  
			    tbar: [  
			       refresh_bar,
			       "->",
			       keywordTf,
			       statusCbx,
			       searchBtn
			    ],  
			    store: pageStore,
			    columns: columns,  
			    region:"center",
			    autoExpandColumn: "address", 
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
					displayMsg : '一共 {2}条餐厅信息',
					emptyMsg : "没有餐厅信息"
				})
			});  
			
			
			
			//窗口
			win = desktop.createWindow({
				id:'WCEN-win',
              	listeners:{
              		'close':function(r){
              			wcDetailWin.close();
              			operatorWin.close();
              		}
				},
                title:'餐厅信息管理',
                height:500,               
				items:[grid],
                iconCls: 'icon-wcen',
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'border'
            });
            var contractFieldSet = new Ext.form.FieldSet({
			    anchor:"95%",
			    checkboxToggle: false,     
			    title: '企业业务资料',  
			    autoHeight: true,  
			    defaults: {width: 300},  
			    defaultType: 'textfield',  //默认类型  
			    items:[
			    ]
			});
			
            var operatorFieldSet = new Ext.form.FieldSet({
				checkboxToggle: false,     
			    title: '运营者绑定信息',  
			    anchor:"95%",
	    		autoHeight: true,  
				items:[
			    ]
			});
			
			function loadOperatorRow(row){
				return new Ext.Panel({
					anchor:'95%',	
					defaultType: 'textfield',  //默认类型  
					layout: 'column',  
			        items: [  
			        	{
                            cls:'label-input',
                            readOnly:true,
                            width:100,
                            value:row.name
                        },
			        	{
                            cls:'label-input',
                            readOnly:true,
                            width:100,
                            value:row.dept
                        },
			        	{
                            cls:'label-input',
                            readOnly:true,
                            width:100,
                            value:row.telephone
                        },
			        ]  
				});
				
			}
			var wcDetailForm=new Ext.form.FormPanel({
				id:"detailForm",
				labelAlign: 'left',  //标签位置  
                buttonAlign: 'center',  
				labelWidth:90,
				anchor:"95%",
                frame: true,  
                items:[
				new Ext.form.ComboBox({
					width:120,
		            fieldLabel:'选择使用场景',
				    store: statusStore ,
				    displayField:'name',
				    valueField:'id',
				    mode: 'local',
				    emptyText:'请选择状态...',
				    typeAhead: true,
				    triggerAction: 'all',
				    selectOnFocus:true,
		            editable: false,
		            hiddenName:"runType",
		            listeners:{
		            	select:function(v){
		            		globalRunType = v.getValue();
							if(globalRunType!=1){
								contractFieldSet.hide();
								operatorFieldSet.hide();
							}else{
								contractFieldSet.show();
								operatorFieldSet.show();
							}
		            	}
		            }
				}),
                contractFieldSet,
                operatorFieldSet,{
                	xtype: 'fieldset',  
				    checkboxToggle: false,     
				    title: '企业基础资料',  
				    anchor:"95%",
				    autoHeight: true,  
				    defaultType: 'textfield',  //默认类型
				    items:[
				    	{
                            fieldLabel: '*餐厅名称',
                            name:'name',
                            id:'name',
                            width: 320,
        					allowBlank:false,
				    	},
				    	new Ext.Panel({
							defaultType: 'textfield',  //默认类型  
							layout: 'column',  
				    		style:"margin-bottom:5px",
					        items: [{
					        	xtype:"label",
					        	text:'*省/市/区:',
					        	width:95,
					        	style:'line-height:20px;font-size:12px;'
					        },
				        	{
	                            name:'province',
	                            id:'province',
	                            width:100,
	                            style:"margin-right:10px",
        						allowBlank:false,
	                        },
				        	{
	                            name:'city',
	                            id:'city',
        						allowBlank:false,
	                            width:100,
	                            style:"margin-right:10px"
	                        },
				        	{
	                            name:'area',
	                            id:'area',
        						allowBlank:false,
	                            width:100,
	                        }
					        ]  
						}),
						{
                            fieldLabel: '详细地址',
                            name:'address',
                            id:'address',
                            width: 320
				    	},{
                            fieldLabel: '餐厅电话',
                            name:'telephone',
                            id:'telephone',
                            width: 320
				    	},
				    	new Ext.Panel({
							defaultType: 'textfield',  //默认类型  
							layout: 'column',  
				    		style:"margin-bottom:5px",
					        items: [{
					        	xtype:"label",
					        	text:'菜系:',
					        	width:95,
					        	style:'line-height:20px;font-size:12px;'
					        },
				        	{
		                        name:'businessScope',
		                        id:'businessScope',
		                        width:100,
		                        style:"margin-right:10px",
		                    },{
					        	xtype:"label",
					        	text:'人均消费:',
					        	width:80,
					        	style:'line-height:20px;font-size:12px;text-align:right;margin-right:5px'
					        },
				        	{
				        		xtype:"numberfield",
		                        name:'perCapitaConsumption',
		                        id:'perCapitaConsumption',
		                        width:100,
		                    }
					        ]  
						}),{
					        xtype:"textarea",
                            fieldLabel: '备注',
                            name:'memo',
                            id:'memo',
                            width: 320
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
				width:600,
				autoHeight:true,
				bodyStyle:"max-height:500px",
                autoScroll:true,
				listeners:{
					hide:function(){//监听hide事件
						wcDetailForm.getForm().reset();
					}
				},
				buttons:[{
					text:"确定",
					handler:function(){
						if (wcDetailForm.getForm().isValid()) { 
							var formValues = wcDetailForm.getForm().getValues();
							uptObj.runType = globalRunType;
							uptObj.name = formValues.name;
							uptObj.area = formValues.area;
							uptObj.address = formValues.address;
							uptObj.province = formValues.province;
							uptObj.city = formValues.city;
							uptObj.memo = formValues.memo;
							uptObj.telephone = formValues.telephone;
							uptObj.businessScope = formValues.businessScope;
							uptObj.perCapitaConsumption = formValues.perCapitaConsumption;
							Ext.Msg.wait("正在保存","请稍后")
							Ext.Ajax.request({
								url:"restaurant/saveOrUpdate.do",
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
										wcDetailWin.hide();
									}
								}
							})
							
							wcDetailWin.hide(); //关闭窗口 
			            } 
					}
				}],
				buttonAlign:"center",
				title:"餐厅信息详情",
				closeAction:"hide"
			});
			
			var opratorStore = new Ext.data.JsonStore({
			    id:'id',
			    url: 'operator/page.do',
				totalProperty:"total",
				root:"rows",
				fields: ["id","name","dept","telephone","wechatId","status","memo","restaurantVos"]
			});   
			pageStore.load({
				params:{
					start:0,
					limit:20
				}
			}); 
			
            
	       
			var operatorSm = new Ext.grid.CheckboxSelectionModel()	
			
			var operatorColumns = [  
				new Ext.grid.RowNumberer(),
				operatorSm,
				{
		            id:"name",   
		            header:"运营者",   
		            width:100,   
		            dataIndex:"name"
		        },{
		            id:"dept",   
		            header:"职务",   
		            width:100,   
		            dataIndex:"dept"
		        },  
		        {  
		            id:"restaurantVos",   
		            header:"绑定餐厅",   
		            width:150,   
		            dataIndex:"restaurantVos",
		            renderer:function(v,m,record){
	             		//v 表示设备管理列表数据中绑定餐厅的id
						var bindRestaurants = "";
						if(v&&v.length>0){
							for(var i=0;i<v.length;i++){
								bindRestaurants+=v[i].name+",";
							}
							return bindRestaurants.substring(0,bindRestaurants.length-1);
						}
						return '暂无绑定';
						
		            } 
		        },
		        {  
		            id:"telephone",   
		            header:"电话",   
		            width:60,     
		            dataIndex:"telephone"
		        },
		        {  
		            id:"status",   
		            header:"状态",     
		            width:80,   
		            dataIndex:"cooperationVO",
		            renderer:function(v,m,record){
		            	return v?'已绑定':'未绑定';
		            }  
		        },
		        {  
		            id:"memo",   
		            header:"备注",   
		            width:100,     
		            dataIndex:"备注"
		        }
		    ];
		    
			var operator_refresh_bar = {  
	            text: "刷新",  
	            iconCls: "icon-refresh",  
	            handler: function(){ 
	            	opratorStore.reload();//刷新列表数据
	            }  
	       };
	       var operator_keywordTf = new Ext.form.TextField({
	       			id:"keyword",
	       			name:"keyword",
	       			fieldLabel:"关键字",
				    inputType:'text',
				    emptyText:'输入餐厅名称、运营者名字进行搜索',
		            allowBlank : false,
		            width:300,
		            style:'margin-right:15px'
			});
			var operator_searchBtn = {
	            text: "搜索",  
	            iconCls: "icon-search",  
	            handler: function(){  
					opratorStore.load({
						params:{
							start:0,
							limit:20,
							keyword:Ext.get("keyword").getValue()=='输入餐厅名称、运营者名字进行搜索'?'':Ext.get("keyword").getValue(),
						}
					});//刷新列表数据
	            } 
			};
			var operator_confirm_bar =  {  
	            text: "确定",  
	            iconCls: "icon-confirm",  
	            handler: function(){  
	            	var operatorLst = operatorSm.getSelections();
	            	if(operatorLst.length>0){
	            		operatorList = [];
	            		operatorFieldSet.removeAll();
		    			for(var i=0; i<operatorLst.length; i++){
							operatorFieldSet.add(loadOperatorRow(operatorLst[i].data));
							operatorList.push(operatorLst[i].data);
		    			}
		    			uptObj.operatorList = operatorList;
		    			operatorFieldSet.add({
                        	xtype:"button",
                        	text: '重新选择绑定',  
                            name: 'reBind',
                            id:'reBind',
                            width:100,
                            style:"height:30px;margin-top:10px",
                            listeners:{
                            	click:function(){
                            		opratorStore.load({
										params:{
											start:0,
											limit:20
										}
									}); 
                            		operatorWin.show();
                            	}
                            }
                        });
		    			operatorFieldSet.doLayout();
						operatorWin.hide();
					}
	            }  
	       };
			
			var operatorGrid = new Ext.grid.EditorGridPanel({  
			    width: 500,  
			    height: 300,  
			    frame: true,  
			    tbar: [  
			       operator_refresh_bar,
			       "->",
			       operator_keywordTf,
			       operator_searchBtn
			    ],  
			    store: opratorStore,
			    sm:operatorSm,
			    columns: operatorColumns,  
			    region:"center",
			    autoExpandColumn: "restaurantVos", 
			    bbar:[new Ext.PagingToolbar({
	        			pageSize : 10,
						store : opratorStore,
						beforePageText : "第",
						afterPageText : "页，共{0}页",
						lastText : "尾页",
						nextText : "下一页",
						prevText : "上一页",
						firstText : "首页",
						refreshText : "刷新",
						displayInfo : true,
						displayMsg : '一共 {2}条运营者信息',
						emptyMsg : "没有运营者信息"
					}),"->",operator_confirm_bar]
			});  
			
			var operatorWin=new Ext.Window({
				title:'选择您要绑定的运营者',
				closeAction:"hide",//关闭事件触发hide事件
				modal:true,//模态化
				constrain :true,
				resizable :false,
				width:780,
	            autoHeight:true, 
	            bodyStyle:"min-height:300px",
				items:[operatorGrid],
	            iconCls: 'icon-operator',
	            shim:false,
	            animCollapse:false,
	            constrainHeader:true,
	            layout: 'border',
	            listeners:{
	            	hide:function(){
	            		operatorSm.clearSelections();
	            	}
	            }
			});
			
        	
		}
        win.show();
    }
});

