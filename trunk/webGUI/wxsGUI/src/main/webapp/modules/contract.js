/*
 * wxconf window
 */
Emin.CONTRACTWindow = Ext.extend(Ext.app.Module, {
    id:'CONTRACT-win',
    init : function(){
        this.launcher = {
            text: '合同管理',
            iconCls:'icon-contract',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('CONTRACT-win');
		if(!win){
			var contractNumArray ;
			var desktop = this.app.getDesktop();
			/****从服务器上拉取公众号信息列表*****/
			pageStore = new Ext.data.JsonStore({
			    id:'id',
			    url: 'contract/page.do',
				totalProperty:"total",
				root:"rows",
				fields: ["id","status","beginDate","endDate","companyName","legalPerson","regLicenceNo","regLicenceFile","accountName","accountBank","accountBankCardNo","cooperationVO","restaurantVO","contractNum"]
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
		            id:"contractNum",   
		            header:"合同编号",   
		            width:100,   
		            dataIndex:"contractNum"
		        },  
		        {  
		            id:"restaurantVO",   
		            header:"餐厅名称",   
		            width:150,   
		            dataIndex:"restaurantVO",
		            renderer:function(v,m,record){
		            	if(v){
			            	return v.name
		            	}else{
		            		return "";
		            	}
		            } 
		        },
		        {  
		            id:"legalPerson",   
		            header:"法人代表",   
		            width:100,     
		            dataIndex:"legalPerson"
		        },
		        {  
		            id:"cooperationVO",   
		            header:"合作方式",     
		            width:80,   
		            dataIndex:"cooperationVO",
		            renderer:function(v,m,record){
		            	if(v){
			            	return v.name
		            	}else{
		            		return "";
		            	}
		            }  
		        },
		        {  
		            id:"companyName",   
		            header:"企业名称",   
		            width:100,     
		            dataIndex:"companyName"
		        },
		        {  
		            id:"companyAddress",   
		            header:"地址",   
		            width:180,     
		            dataIndex:"restaurantVO",
		            renderer:function(v,m,record){
		            	if(v){
			            	var p = v.province?v.province:"";
			            	var c = v.city?v.city:"";
			            	var a = v.area?v.area:"";
			            	var address = v.address?v.address:"";
			            	return p+c+a+address;
		            	}else{
		            		return "";
		            	}
		            }  
		        },
		        {  
		            id:"status",   
		            header:"状态",   
		            width:80,     
		            dataIndex:"status",
		            renderer:function(v,m,record){
		            	return v?'已签署':'未签署';
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
                        	var uptObj = grid.getStore().getAt(rowIndex).data;
                        	var record =  grid.getStore().getAt(rowIndex)
                        	var restaurant = uptObj.restaurantVO;
                        	if(restaurant){
								contractFieldSet.removeAll();
	                        	contractFieldSet.add({
	                            	fieldLabel: '餐厅名称',
	                            	value:restaurant.name,
	                            	cls:'label-input',
	                        	});
	                        	contractFieldSet.add({
	                            	fieldLabel: '餐厅地址',
	                            	value:restaurant.province?restaurant.province:""+restaurant.city?restaurant.city:""+restaurant.area?restaurant.area:""+restaurant.address?restaurant.address:"",
	                            	cls:'label-input',
	                        	});
	                        	contractFieldSet.add({
	                            	fieldLabel: '餐厅电话',
	                            	value:restaurant.telephone,
	                            	cls:'label-input',
	                        	});
				    			contractFieldSet.doLayout();
				    			
				    			var operatorLst = restaurant.operatorList;
								if(operatorLst&&operatorLst.length>0){
									/*******
									 * 渲染酒品通道集合数据
									 * @param {Object} channelList 通道设置信息集合
									 */
									operatorFieldSet.removeAll();
					    			for(var i=0; i<operatorLst.length; i++){
										operatorFieldSet.add(loadOperatorRow(operatorLst[i]));
					    			}
					    			operatorFieldSet.doLayout();
								}else{
	                                operatorFieldSet.removeAll();
									operatorFieldSet.add({
										xtype:"label",
										text:"该餐厅暂未绑定运营者"
									});
					    			operatorFieldSet.doLayout();
								}
								
                        	}else{
                                contractFieldSet.removeAll();
								contractFieldSet.add({
									xtype:"label",
									text:"暂无餐厅信息"
								});
				    			contractFieldSet.doLayout();
				    			
                                operatorFieldSet.removeAll();
								operatorFieldSet.add({
									xtype:"label",
									text:"该餐厅暂未绑定运营者"
								});
				    			operatorFieldSet.doLayout();
                        	}
                        	var beginDate = parseFloat(record.get("beginDate"));
                        	if(beginDate){
                        		record.set("beginDate",new Date(beginDate).format("Y-m-d"))
                            }
                        	var endDate = parseFloat(record.get("endDate"));
                        	if(endDate){
                        		record.set("endDate",new Date(endDate).format("Y-m-d"))
                            }

                			contractNumArray = record.get("contractNum").split("-");
                			var cooperationVO = record.get("cooperationVO");
                			if(cooperationVO)
                			record.set('cooperationId',record.data.cooperationVO.id);
                			contractDetailWin.show();
                        	contractDetailForm.getForm().loadRecord(record);
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
				    inputType:'text',
				    emptyText:'输入餐厅名称、合同编号搜索',
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
			    autoExpandColumn: "companyName", 
			    bbar:[new Ext.PagingToolbar({
        			pageSize : 10,
					store : pageStore,
					beforePageText : "第",
					afterPageText : "页，共{0}页",
					lastText : "尾页",
					nextText : "下一页",
					prevText : "上一页",
					firstText : "首页",
					refreshText : "刷新",
					displayInfo : true,
					displayMsg : '一共 {2}条合同信息',
					emptyMsg : "没有合同信息"
				})]
			});  
			
			
			
			//窗口
			win = desktop.createWindow({
				id:'CONTRACT-win',
              	listeners:{
              		'close':function(r){
              			contractDetailWin.close();
              		}
				},
                title:'合同信息管理',
                height:500,               
				items:[grid],
                iconCls: 'icon-wxconf',
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'border'
            });
            var contractFieldSet = new Ext.form.FieldSet({
			    anchor:"95%",
			    checkboxToggle: false,     
			    title: '企业基础资料',  
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
			var cooperationStore = new Ext.data.JsonStore({
			   	url: 'cooperation/page.do',
			    root:"rows",
			    fields: ['id', 'name',"memo","roleType","profitPercent"]
			});
			cooperationStore.load({
				params:{
					roleType:1
				}
			});
			var contractDetailForm = new Ext.form.FormPanel({
		         id:"contractDetailForm",
		         defaultType: 'textfield',  //默认类型
		         items:[{xtype:"hidden",id:"id",name:"id"},
		         new Ext.Panel({
					defaultType: 'textfield',  //默认类型  
					layout: 'column',  
		    		style:"margin-bottom:5px",
			        items: [{
			        	xtype:"label",
			        	text:'合同编号:',
			        	width:105,
			        	style:'line-height:20px;font-size:12px;'
			        },
		        	{
			        	cls:'label-input',
                        name:'contractNum',
                        id:'contractNum',
                        width:100,
                        style:"margin-right:10px",
                    },
		        	{
                    	id:"status",
                    	id:"status",
                    	xtype:"radiogroup",
                    	width:180,
                    	items:[
							{
								name:"status",
								boxLabel:"未签署",
                    			inputValue: '0',
							},{
								name:"status",
	                        	xtype:"radio",
	                        	boxLabel:"已签署",
                    			inputValue: '1',
	                        }
                    	]
	                    ,
						listeners:{
							change:function(v){
								var centerStr = v.getValue()==0?'-N-':'-Y-';
								if(contractNumArray){
									contractDetailForm.getForm().findField("contractNum").setValue(contractNumArray[0]+centerStr+contractNumArray[2]);
								}
							}
						}
                    },
			        ]  
				}),
		        new Ext.Panel({
					defaultType: 'textfield',  //默认类型  
					layout: 'column',  
		    		style:"margin-bottom:5px",
			        items: [{
			        	xtype:"label",
			        	text:'合同期限:',
			        	width:105,
			        	style:'line-height:20px;font-size:12px;'
			        },
		        	{
                    	xtype:"textfield",
                    	readOnly:true,
                    	name:'beginDate',
                        id:'beginDate',
                        cls:"Wdate",
                    	blankText:"请选择开始时间",
                    	listeners:{
                    		focus:function(){
                    			WdatePicker({el:this.el.dom,dateFmt:"yyyy-MM-dd"});
                    		}
                    	}
                    },{
			        	xtype:"label",
			        	text:'——',
			        	width:33,
			        	style:'line-height:20px;font-size:12px;text-align:center;'
			        },
		        	{
                    	xtype:"textfield",
                    	readOnly:true,
                    	name:'endDate',
                        id:'endDate',
                        cls:"Wdate",
                    	blankText:"请选择结束时间",
                    	listeners:{
                    		focus:function(){
                    			WdatePicker({el:this.el.dom,dateFmt:"yyyy-MM-dd"});
                    		}
                    	}
                    }
			        ]  
				}),
		    	{
                    fieldLabel: '企业名称',
                    name:'companyName',
                    id:'companyName',
                    width: 320,
		    	},{
                    fieldLabel: '法定代表人',
                    name:'legalPerson',
                    id:'legalPerson',
                    width: 320
		    	},{
                    fieldLabel: '工商执照注册号',
                    name:'regLicenceNo',
                    id:'regLicenceNo',
                    width: 320
		    	},new Ext.form.ComboBox({
					width:300,
		            fieldLabel:'*合作方式',
				    store: cooperationStore ,
				    displayField:'name',
				    valueField:'id',
				    mode: 'local',
				    emptyText:'请选择方案...',
		            allowBlank : false,
				    typeAhead: true,
				    triggerAction: 'all',
				    selectOnFocus:true,
		            editable: false,
					hiddenName:"cooperationId",
				}),{
                    fieldLabel: '企业开户名称',
                    name:'accountName',
                    id:'accountName',
                    width: 320
		    	},{
                    fieldLabel: '企业开户银行',
                    name:'accountBank',
                    id:'accountBank',
                    width: 320
		    	},{
                    fieldLabel: '企业开户账号',
                    name:'accountBankCardNo',
                    id:'accountBankCardNo',
                    width: 320
		    	},
		    	{
		    		xtype:"hidden",
		    		name:"regLicenceFile",
		    		id:"regLicenceFile",
		    	}]
		    });
			
			var contractLicenceFileForm = new Ext.form.FormPanel({
		         id:"contractLicenceFileForm",
		         fileUpload:true,  
		         defaultType: 'textfield',  
		         items:[{
                    fieldLabel: '企业工商营业执照',
                    inputType:"file",
                    name:'file',
                    id:'file',
                    width: 320,
                    listeners:{
                    	change:function(v){
                    		contractLicenceFileForm.getForm().submit({
                    			url:"file/upload.do",
					            waitMsg:"正在上传...",
								success:function(form,result){
									var d = Ext.decode(result.response.responseText);
									if(d.success){
										contractDetailForm.getForm().findField("regLicenceFile").setValue(d.url);
										showMsg("营业执照上传成功！");
									}
								},
								failure:function(form,action){
									showMsg("圖片上傳失敗")
								}
                    		});
                    	}
                    }
		    	}]
		    });
			var contractDetailPanel=new Ext.Panel({
				labelAlign: 'left',  //标签位置  
                buttonAlign: 'center',  
				labelWidth:100,
				anchor:"95%",
                frame: true,  
                items:[{
                	xtype: 'fieldset',  
				    checkboxToggle: false,     
				    title: '合同基础资料',  
				    anchor:"95%",
				    autoHeight: true,  
				    items:[contractDetailForm,contractLicenceFileForm]
                },
                contractFieldSet,
                operatorFieldSet],
			});
			var contractDetailWin=new Ext.Window({
				items:[contractDetailPanel],
				closeAction:"hide",//关闭事件触发hide事件
				modal:true,//模态化
				constrain :true,
				resizable :false,
				width:600,
				autoHeight:true,   
                autoScroll:true,
                bodyStyle:"max-height:500px",
				listeners:{
					hide:function(){//监听hide事件
						contractDetailForm.getForm().reset();
					}
				},
				buttons:[{
					text:"确定",
					handler:function(){
						if (contractDetailForm.getForm().isValid()) {
							
							var uptObj = contractDetailForm.getForm().getValues();
							var beginDate = uptObj.beginDate;
							if(beginDate){
								uptObj.beginDate = new Date(beginDate).getTime();
							}
							var endDate = uptObj.endDate;
							if(endDate){
								uptObj.endDate = new Date(endDate).getTime();
							}
							var cooperationId = contractDetailForm.getForm().findField("cooperationId").getValue();
							uptObj.cooperationVO = cooperationStore.getById(cooperationId).data;
							delete uptObj['cooperationId'];
							Ext.Msg.wait("正在更新","请稍后")
							Ext.Ajax.request({
								url:"contract/saveOrUpdate.do",
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
										contractDetailWin.hide(); //关闭窗口 
									}
								}
							})    
			            } 
					}
				}],
				buttonAlign:"center",
				title:"合同信息详情",
				closeAction:"hide"
			});
        	
		}
        win.show();
    }
});

