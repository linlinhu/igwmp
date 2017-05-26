/*
 * wxconf window
 */
Emin.OPERCONFWindow = Ext.extend(Ext.app.Module, {
    id:'OPERCONF-win',
    init : function(){
        this.launcher = {
            text: '运营配置',
            iconCls:'icon-operconf',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('OPERCONF-win');
		if(!win){
			var desktop = this.app.getDesktop();
			var operFlag = 0;//操作标识符，0表示新店装机，1表示移机，2表示拆机
			var uptDeviceObj = null;//移机对象
			var globalRunType = 1;//全局使用场景
			var restaurantEntity = '';//全局餐厅ID
			var contractNumArray ;

			var statusStore = new Ext.data.JsonStore({
	    	   id:'id',
	    	   totalProperty:"total",
	    	   root:"rows",
	    	   url: 'category/list.do',
	    	   fields: ['id', 'name']
			}) ;
	        statusStore.load();
			/**设置自动生成合同编号*/
			function setContractNum(){
				Ext.Ajax.request({
					url:"contract/getRgstNum.do",
					params:{
						isSigned:true
					},
					success:function(response,request){
						var d = Ext.decode(response.responseText)
						if(d.message!=""){
							showErrorMsg(d.message)
						}else{
							var contractNum = d.result;
							contractNumArray = contractNum.split("-");
							contractDetailForm.getForm().findField("contractNum").setValue(contractNumArray[0]+"-N-"+contractNumArray[2]);
						}
					}
				})
			}
			var restaurantDetailForm = new Ext.form.FormPanel({
				defaultType: 'textfield',  //默认类型  
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
								Ext.getCmp("contractFieldSet").hide();
							}else{

								Ext.getCmp("contractFieldSet").show();
							}
		            	}
		            }
				}),
				{
					xtype:"hidden",
                    name:'id',
                    id:'id',
		    	},{
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
			        	width:100,
			        	style:'line-height:20px;font-size:12px;margin-right:5px'
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
			        	width:100,
			        	style:'line-height:20px;font-size:12px;margin-right:5px'
			        },
		        	{
                        name:'businessScope',
                        id:'businessScope',
                        width:120,
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
				
				
                ],
			});
			/***********
			 * 餐厅-合同合作方式
			 */
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
		         bodyStyle:"background: inherit;border:none;",
		         items:[
		         new Ext.Panel({
					defaultType: 'textfield',  //默认类型  
					layout: 'column',  
		    		style:"margin-bottom:5px;",
		         	bodyStyle:"background: inherit;border:none;",
			        items: [{
			        	xtype:"label",
			        	text:'*合同编号:',
			        	width:105,
			        	style:'line-height:20px;font-size:12px;'
			        },
		        	{
                        name:'contractNum',
                        id:'contractNum',
                        width:100,
                        style:"margin-right:10px",
                        cls:'label-input',
                        readOnly:true,
                    	allowBlank:false,
                    },
		        	{
                    	xtype:"radiogroup",
		         		style:"background: inherit;border:none;",
		         		bodyStyle:"background: inherit;border:none;",
                    	allowBlank:false,
                    	width:180,
                    	items:[
							{
	                            name:'status',
								boxLabel:"未签署",
                    			inputValue: '0',
                    			checked:true
							},{
	                            name:'status',
	                        	xtype:"radio",
	                        	boxLabel:"已签署",
                    			inputValue: '1'
	                        }
                    	],
    					listeners:{
    						change:function(v){
    							var centerStr = v.getValue()==0?'-N-':'-Y-';
    							contractDetailForm.getForm().findField("contractNum").setValue(contractNumArray[0]+centerStr+contractNumArray[2]);
        						
    						}
    					}
                    },
			        ]  
				}),
		        new Ext.Panel({
					defaultType: 'textfield',  //默认类型  
					layout: 'column',  
		    		style:"margin-bottom:5px;",
		         	bodyStyle:"background: inherit;border:none;",
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
					id:'cooperationId',
					name:'cooperationId',
		            fieldLabel:'*合作方式',
					allowBlank:false,
				    store: cooperationStore ,
				    displayField:'name',
				    valueField:'id',
				    mode: 'local',
				    emptyText:'请选择方案...',
				    typeAhead: true,
				    triggerAction: 'all',
				    selectOnFocus:true,
		            editable: false,
					hiddenName:"id",
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
		    	},{
                    xtype:"hidden",
                    name:'contractId',
                    id:'contractId',
		    	},{
                    xtype:"hidden",
                    name:'regLicenceFile',
                    id:'regLicenceFile',
		    	}]
		    });
			var contractLicenceFileForm = new Ext.form.FormPanel({
		         id:"contractLicenceFileForm",
		         defaultType: 'textfield',  //默认类型
		         bodyStyle:"background: inherit;border:none;",
		         fileUpload:true,  
		         items:[{
                    fieldLabel: '*工商营业执照',
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
			/****
			 * 添加餐厅信息
			 */
			var restaurantWin=new Ext.Window({
				title:'新店',
				modal:true,//模态化
				constrain :true,
				resizable :false,
				width:620,
	            autoHeight:true,
	            bodyStyle:"max-height:500px",
				items:[
					new Ext.Panel({
					    labelAlign: 'left',  //标签位置  
						labelWidth:120,
						anchor:"95%",
		                frame: true,  
					    items:[
							new Ext.form.FieldSet({
								id:"restaurantFieldSet",
								checkboxToggle: false,     
							    title: '餐厅资料',
								autoHeight: true,  
								width:565,
								items:[
								   restaurantDetailForm
							    ]
							}),
							new Ext.form.FieldSet({
								id:"contractFieldSet",
								checkboxToggle: false,     
							    title: '企业合同资料',  
								region:"center",
					    		autoHeight: true,  
								width:565,
								items:[
									contractDetailForm,contractLicenceFileForm
							    ]
							})
						]
					})
				],
				buttons:[{
					type:"button",
					text:"下一步",
					listeners:{
						click:function(){
							var rForm = restaurantDetailForm.getForm();
							var cform = contractDetailForm.getForm();
							if(rForm.isValid()){
								globalRunType = rForm.findField("runType").getValue();
								var restaurantValid = rForm.isValid();
								var contractValid = cform.isValid();
								if(globalRunType==1&&!restaurantValid&&!contractValid){//运营
									return false;
								}
								
								if((globalRunType==2||globalRunType==3)&&!restaurantValid){//活动展示和测试
									return false;
								}
								//根据餐厅选择的使用场景查相应场景的酒品
			                	winStore.load({
			                		params:{
			                			categoryId:globalRunType
			                		}
			                	})
								//获取表单中的数据
								restaurantEntity = restaurantDetailForm.getForm().getValues();
								
								if(globalRunType==1){//运营环境中，需要保存合同资料
									var cvalues = cform.getValues();
									var contractId = cform.findField("contractId").getValue();
									cvalues["id"] = contractId;
									delete cvalues["contractId"];
									var beginDate = cvalues["beginDate"];
									var endDate = cvalues["endDate"];
									cvalues["beginDate"] = new Date(beginDate).getTime();
									cvalues["endDate"] = new Date(endDate).getTime();
									cvalues["cooperationVO"] = cooperationStore.getById(Ext.getCmp("cooperationId").getValue()).data;
									restaurantEntity["contractVo"] = cvalues;
								}
								Ext.Msg.wait("正在保存","请稍后")
								Ext.Ajax.request({
									url:"restaurant/saveOrUpdate.do",
									params:{
										jsonStr:JSON.stringify(restaurantEntity)
									},
									success:function(response,request){
										Ext.Msg.hide()
										var d = Ext.decode(response.responseText)
										if(d.message!=""){
											showErrorMsg("保存修改报错")
										}else{
											restaurantEntity = JSON.parse(d.result);
											rForm.findField("id").setValue(restaurantEntity.id);
											if(globalRunType==1){//保存了合同，将合同的ID赋值，返回上一步对该合同进行修改操作
												restaurantEntity = JSON.parse(d.result);
												var rid = restaurantEntity.id;
												rForm.findField("id").setValue(rid);
												Ext.Ajax.request({
													url:"contract/page.do",
													params:{
														restaurantId:rid
													},
													success:function(response,request){
														Ext.Msg.hide()
														var d = Ext.decode(response.responseText)
														cform.findField("contractId").setValue((d.rows)[0].id);
														restaurantWin.hide();
														deviceWin.show();
													}
												})
											}else{
												restaurantWin.hide();
												deviceWin.show();
											}
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
			});
			
			
			/********
			 * 酒柜信息即酒水通道设置
			 */
			var winCabinet = {
				id:"addWinCabinetInfo",
            	xtype: 'fieldset',  
			    anchor:"95%",
			    checkboxToggle: false,     
			    title: '添加酒柜信息',  
			    autoHeight: true,  
			    defaults: {width: 300},  
			    defaultType: 'textfield',  //默认类型  
			    items:[
			    	{  
                        fieldLabel: '*机器编码',  
                        name: 'code',
                        id:'code',
                        allowBlank:false,
                    },{  
                        fieldLabel: '*工控机编码',  
                        name: 'ipcCode',
                        id:'ipcCode',
                        allowBlank:false,
                    },{
                    	fieldLabel: '*SIM卡号',  
                        name: 'simCode',
                        id:'simCode',
                        allowBlank:false,
                    }
			    ]
            };
            
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
        			bodyStyle:"background: inherit;border:none;",
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
					bodyStyle:"background: inherit;border:none;",
			        items: [  
			        	{
				    		text:"通道"+no,
				    		autoWidth : true,
				    		style:"line-height:20px;margin:0 15px 10px;"
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
							style:"margin-bottom:10px",
				    	}),
			        	{
				    		text:"剩余量",
				    		autoWidth : true,
				    		style:"line-height:20px;margin:0 15px 10px;"
					    },
				    	new Ext.form.NumberField({
				    		width:100,
				    		value:row?row.allowance:'',
				            allowBlank : false,
							style:"margin-bottom:10px",
				    	}),
			        	{
				    		text:"显示排序",
				    		autoWidth : true,
				    		style:"line-height:20px;margin:0 15px 10px;"
					    },
				    	new Ext.form.NumberField({
				    		width:80,
				            allowBlank : false,
				    		value:row?row.sort:'',
							style:"margin-bottom:10px",
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
			    anchor:"98%",
	    		autoHeight: true,  
				width:565,
				style:"padding-top:15px",
				items:[
					loadWcChannelRow(1,null)
			    ]
			});
            var channelSet = {
            	xtype: 'fieldset',  
			    checkboxToggle: false,     
			    title: '配置酒水',  
			    anchor:"95%",
			    autoHeight: true,  
			    defaultType: 'textfield',  //默认类型  
			    items:[
			    	{  
	                	xtype: 'checkbox',  
	                	hidden:true,
                    	boxLabel : '继续沿用该机器移机前的酒水参数',  
                        name: 'extendHistorySet',
                        id:'extendHistorySet',
                        hideLabel :true,
                        listeners:{
                        	check:function(r){
                        		if(!uptDeviceObj) return false;
                        		
                        		Ext.getCmp("code").setValue(uptDeviceObj.code);
                    			Ext.getCmp("ipcCode").setValue(uptDeviceObj.ipcCode);
                    			Ext.getCmp("simCode").setValue(uptDeviceObj.simCode);
                        		if(r.checked){
                        			Ext.getCmp("channelSum").setValue(uptDeviceObj.channelSum);
                        			
                        			var channelList = uptDeviceObj.channelList;
                        			/*******
									 * 渲染酒品通道集合数据
									 * @param {Object} channelList 通道设置信息集合
									 */
									channelWinSetFieldSet.removeAll();
					    			for(var i=1; i<=(channelList.length>6?6:channelList.length); i++){
										channelWinSetFieldSet.add(loadWcChannelRow(i,channelList[i-1]));
					    			}
					    			channelWinSetFieldSet.doLayout();
                        		}else{
                        			Ext.getCmp("channelSum").setValue(1);
                        			/*******
									 * 渲染酒品通道集合数据
									 * @param {Object} channelList 通道设置信息集合
									 */
									channelWinSetFieldSet.removeAll();
									channelWinSetFieldSet.add(loadWcChannelRow(1,null));
					    			channelWinSetFieldSet.doLayout();
                        		}
                        	}
                        }
                    },
			    	{  
	                	xtype: 'numberfield',  
                    	fieldLabel: '酒柜通道数量',  
                    	labelSeparator :":",
                        name: 'channelSum',
                        id:'channelSum',
                        emptyText:1,
                        minValue:1,
                        maxValue:6,
                        width:100,
                        enableKeyEvents:true,
                        listeners:{
                            beforerender:function(r){
                            	r.setValue(r.value>6?6:r.value)
				            },
                        	blur:function(r){
                        		var  channelCount = r.getValue();
                        		if(channelCount<0||channelCount>6){
                        			r.setValue(1);
                        		}
								/*******
								 * 渲染酒品通道集合数据
								 * @param {Object} channelList 通道设置信息集合
								 */
								channelWinSetFieldSet.removeAll();
				    			for(var i=1; i<=r.getValue(); i++){
									channelWinSetFieldSet.add(loadWcChannelRow(i,null));
				    			}
				    			channelWinSetFieldSet.doLayout();
                        	}
                        	
                        }
                    },channelWinSetFieldSet,{
	                	xtype: 'fieldset',  
					    checkboxToggle: false,     
					    title: '预警设置',  
			    		autoHeight: true,  
						width:565,
					    items:[
					    	grtAsp(),
					    ]
	                }
			    ]
            }
			var deviceWin=new Ext.Window({
				title:'酒柜及酒水设置',
				closeAction:"hide",//关闭事件触发hide事件
				modal:true,//模态化
				constrain :true,
				resizable :false,
				width:700,
	            autoHeight:true, 
	            bodyStyle:"min-height:300px;padding:15px",
	            buttonAlign:"center",
				items:[winCabinet,channelSet],
				buttons:[{
					type:"button",
					text:"上一步",
					listeners:{
						click:function(){
							restaurantWin.show();
							deviceWin.hide();
						}
					}
				},{
					type:"button",
					text:"完成设置",
					listeners:{
						click:function(){
							var cCount = Ext.get("channelSum").getValue();
						 	var channelList = [];
						 	for(var i=1;i<=cCount;i++){
						 		var keys = channelWinSetFieldSet.findById("channel"+i).items.keys;
						 		console.info(Ext.getCmp(keys[3]).getValue());
						 		var obj = {
						 			liquorInfoId:Ext.getCmp(keys[1]).getValue(),
						 			liquorName:Ext.getCmp(keys[1]).getRawValue(),
						 			allowance:Ext.getCmp(keys[3]).getValue(),
						 			sort:Ext.getCmp(keys[5]).getValue(),
						 			id:Ext.getCmp(keys[6]).getValue(),
									alarmValue:Ext.getCmp("alarmValue").getValue(),
									warningValue:Ext.getCmp("warningValue").getValue()
						 		}
						 		channelList.push(obj);
						 	}
							// uptDeviceObj.id = "33";
						 	var updateObj = {
						 		"id":uptDeviceObj?uptDeviceObj.id:'',//id为空表示新增，不为空则为修改
						 		"restaurantId":restaurantEntity?restaurantEntity.id:'',
						 		"restaurantName":restaurantEntity?restaurantEntity.name:'', //获取上一次保存的餐厅名称
							 	"code":Ext.getCmp("code").getValue(),
							 	"ipcCode":Ext.getCmp("ipcCode").getValue(),
							 	"simCode":Ext.getCmp("simCode").getValue(),
							 	"channelSum":Ext.getCmp("channelSum").getValue(),
								"channelList":channelList,
								"machineControl":{"runType":globalRunType}
						 	}
							Ext.Msg.wait("正在保存","请稍后")
							Ext.Ajax.request({
								url:"device/handle.do",
								params:{
									jsonStr:JSON.stringify(updateObj)
								},
								success:function(response,request){
									Ext.Msg.hide()
									var d = Ext.decode(response.responseText)
									if(d.message!=""){
										showErrorMsg(d.message)
									}else{
						                showMsg("完成设置");
						                restaurantDetailForm.getForm().reset();
										contractDetailForm.getForm().reset();
									}
								}
							})						}
					}
				}],
			});
			
			
			/**********
			 * 移机或拆机设备列表窗口
			 */
			var deviceStore = new Ext.data.JsonStore({
			    id:'id',
			    url: 'device/page.do',
				totalProperty:"total",
				root:"rows",
				fields: ['id', 'code', 'ipcCode','simCode','runType','bindStatus','restaurantId','restaurantName','bindTime','brStatus','channelSum','alarmValue','warningValue','channelList','restaurant']
			});   
			
			var sm = new Ext.grid.CheckboxSelectionModel({listeners:{
				beforerowselect:function(){
					if(operFlag==1){//旧机移机 只能选择一行
						sm.clearSelections();
						return true;
					}
					if(operFlag==2){//拆机，可选择多行
						return true;
					}
				}
			}})
			var deviceColums = [
				new Ext.grid.RowNumberer(),
				sm,  
		        {  
		            id:"restaurantName",   
		            header:"餐厅名称",       
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
		             	return v?statusStore.getById(v).data.name:''
		            }
		        },
		        {  
		            id:"restaurant",
		            header:"详细地址",     
		            width:80,   
		            dataIndex:"restaurant",   
		            renderer:function(v,m,record){
		             	var pro = v.province?v.province:"";
		             	var city = v.city?v.city:"";
		             	var area = v.area?v.area:"";
		             	var address = v.address?v.address:"";
		             	return pro+city+area+address;
		            }
		        },
	  		];
	  		var device_refresh_bar = {  
	            text: "刷新",  
	            iconCls: "icon-refresh",  
	            handler: function(){  
	            	deviceStore.reload();//刷新列表数据
	            }  
	       };
	       
	        var device_confirm_bar = {
	            text: "确定",  
	            iconCls: "icon-confirm",  
	            handler: function(){  
	            	var records = sm.getSelections();
	            	if(records.length>0){
	            		if(operFlag==1){//移机
	            			//选择的设备对象，包含从后台获取的餐厅信息，酒水信息
	            			uptDeviceObj = records[0].data;
	            			
							uptDeviceWin.hide();
							restaurantWin.show();
							setContractNum();
	            		}
	            		if(operFlag==2){//拆机
	            			var removeIds = new Array();
	            			for(var i=0;i<records.length;i++){
	            				removeIds[i] = records[i].get("id");
	            			}
	            			Ext.Msg.wait("正在拆机","请稍后")
							Ext.Ajax.request({
								url:"device/cancelBind.do",
								params:{
									ids:removeIds
								},
								success:function(response,request){
									Ext.Msg.hide()
                                    alert("完成设置");
									var d = Ext.decode(response.responseText)
									if(d.message!=""){
										showErrorMsg(d.message)
									}else{
										deviceStore.reload();//刷新设备列表
									}
								}
							})
	            		}
						
					}
	            }  
	       };
	        var device_keywordTf = new Ext.form.TextField({
	       			id:"device_keyword",
	       			name:"keyword",
	       			fieldLabel:"关键字",
			    	emptyText:'输入餐厅名称、设备资产编号、工控机编号搜索',
				    inputType:'text',
		            width:300,
		            style:'margin-right:15px'
			});
	        var device_selectedRunType = '';
			var device_statusCbx = new Ext.form.ComboBox({
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
						device_selectedRunType = e.value;
					}
				}
			});
			var device_searchBtn = {
	            text: "搜索",  
	            iconCls: "icon-search",  
	            handler: function(){  
					deviceStore.load({
						params:{
							start:0,
							limit:10,
							keyword:Ext.get("device_keyword").getValue()!='输入餐厅名称、设备资产编号、工控机编号搜索'?Ext.get("device_keyword").getValue():'',
							runType:device_selectedRunType
						}
					});//刷新列表数据
	            } 
			};
			var uptDeviceGrid = new Ext.grid.EditorGridPanel({  
				    anchor: '100%',  
	            	height:400,     
				    frame: true,  
				    tbar: [  
				       device_refresh_bar,
				       "->",
				       device_keywordTf,
				       device_statusCbx,
				       device_searchBtn
				    ],  
				    store: deviceStore,
				    sm:sm,
				    columns: deviceColums,
				    region:"center",
				    autoExpandColumn: "restaurant", 
					bbar:[new Ext.PagingToolbar({
	        			pageSize : 10,
						store : deviceStore,
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
					}),"->",device_confirm_bar]
				});  
			var uptDeviceWin=new Ext.Window({
				title:'选择您要绑定的餐厅',
				closeAction:"hide",//关闭事件触发hide事件
				modal:true,//模态化
				constrain :true,
				resizable :false,
				width:780,
	            autoHeight:true, 
	            bodyStyle:"min-height:300px",
				items:[uptDeviceGrid],
	            iconCls: 'icon-update',
	            shim:false,
	            animCollapse:false,
	            constrainHeader:true,
	            layout: 'border'
			});
			//窗口
			win = desktop.createWindow({
				id:'OPERCONF-win',
              	listeners:{
              		'close':function(r){
              			restaurantWin.close();
              			uptDeviceWin.close();
              			deviceWin.close();
              		}
				},
				title:"运营配置",
                height:500,
				items:[  
			        {region:"west",width:"33%",items:[{
			        	id:"newSet",
			        	xtype:"label",
			        	text:"新店装机",
			        	style:"display:block;width:100px;height:100px;line-height:100px;color:#fff;font-size:16px;text-align:center;background:#E84709;margin:150px auto",
			        	listeners : {
						    render : function() {
						        Ext.fly(this.el).on('click', function(e, t) {
						        	uptDeviceObj = null;
						        	operFlag = 0;
									Ext.getCmp("extendHistorySet").hide();
									Ext.getCmp("addWinCabinetInfo").show();
									Ext.getCmp("code").setValue('');
	                    			Ext.getCmp("ipcCode").setValue('');
	                    			Ext.getCmp("simCode").setValue('');
	                    			Ext.getCmp("channelSum").setValue(1);
                        			/*******
									 * 渲染酒品通道集合数据
									 * @param {Object} channelList 通道设置信息集合
									 */
									channelWinSetFieldSet.removeAll();
									channelWinSetFieldSet.add(loadWcChannelRow(1,null));
					    			channelWinSetFieldSet.doLayout();
						        	restaurantWin.show();
						        	setContractNum();
						        });
							}
						},
						scope : this
			        }]},
			        {region:"east",width:"33%",items:[{
			        	id:"deleteSet",
			        	xtype:"label",
			        	text:"拆机",
			        	style:"display:block;width:100px;height:100px;line-height:100px;color:#fff;font-size:16px;text-align:center;background:#E84709;margin:150px auto",
			        	listeners : {
						    render : function() {
						        Ext.fly(this.el).on('click', function(e, t) {
						        	operFlag = 2;//拆机
						        	deviceStore.load({
										params:{
											start:0,
											limit:20,
											restaurant:1
											
										}
									})
						        	uptDeviceWin.show();
						        });
							}
						},
						scope : this
			        }]},
			        {region:"center",items:[{
			        	id:"updateSet",
			        	xtype:"label",
			        	text:"旧机移机",
			        	style:"display:block;width:100px;height:100px;line-height:100px;color:#fff;font-size:16px;text-align:center;background:#E84709;margin:150px auto",
			        	listeners : {
						    render : function() {
						        Ext.fly(this.el).on('click', function(e, t) {
									Ext.getCmp("extendHistorySet").show();
									Ext.getCmp("addWinCabinetInfo").hide();
						        	operFlag = 1;//移机
						        	deviceStore.load({
										params:{
											start:0,
											limit:20,
											restaurant:1
										}
									})
						        	uptDeviceWin.show();
						        });
							}
						},
						scope : this
			        }]}
				],
                iconCls: 'icon-operconf',
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'border'
            });
       }
        win.show();
    }
});

