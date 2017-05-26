/*
 * USM window
 */
Emin.PRODUCTWindow = Ext.extend(Ext.app.Module, {
			id: 'PRODUCT-win',
			init: function() {
				this.launcher = {
					text: '产品管理',
					iconCls: 'icon-em',
					handler: this.createWindow,
					scope: this
				}
			},
			createWindow: function() {
				var desktop = this.app.getDesktop();
				var win = desktop.getWindow('PRODUCT-win');

				if(!win) {
				//添加酒品使用的window
				var wg = new Ext.WindowGroup();
				wg.zseed = 7500;
					var data_productId;
			//图片数据源
			var imgStore = new Ext.data.JsonStore({
				url:"${basePath}ewm/admin/file!loadFiles",
				fields:["url","id"]
			});
			//图片显示模板
        	
				var productStore = new Ext.data.JsonStore({
					    //results 表示结果数  
					    //rows 表示从后台传过来的JSON数据  
					  	url: "product/page.do",
					    totalProperty:"total",  
					    root:"rows",  
					    fields:['id','number','name','productListImgs','productDetailImgs','mobileImgs','degree','type','senceName','wineryName','masterName','status','wineryId','senceId'] 
					});	
					
					productStore.load({
						params: {
							start: 0,
							limit: 20
						}
					});
			
					var sm = new Ext.grid.CheckboxSelectionModel();
					
					var productCm = new Ext.grid.ColumnModel(
						[
							new Ext.grid.RowNumberer(),
							//sm,
							{header:"商品图片",dataIndex:"productListImgs",sortable:false,renderer:function(v,m,r,c,s){
								if(v == 0){
									return "没有图片";
								}
								return "<img src="+v+" style='width:40px;height:40px;border-radius:24px' align='absmiddle'/>";
							}},
							{id:"productDetailImgs",header:'手机端图片',dataIndex:'productDetailImgs',hidden:true},
							{id:"mobileImgs",header:'终端图片',dataIndex:'mobileImgs',hidden:true},
							{id:"wineryId",  header: "酒厂id", width: 60,dataIndex:"wineryId", sortable: true},
							{id:"senceId",  header: "分类id", width: 60,dataIndex:"senceId", sortable: true},
							{id:"masterName", header: "名称", width: 60,dataIndex:"name", sortable: true},
							{id:"number", header: "编号", width: 60,dataIndex:"number", sortable: true},
    						{id:"degree", header: "度数", width: 60,dataIndex:"degree", sortable: true},
   							{id:"type", header: "香型", width: 100,dataIndex:"type", sortable: true},
    						{id:"sence", header: "使用场景", width: 100,dataIndex:"senceName", sortable: true },
    						{id:"wineryName", header: "酒厂", width: 100,dataIndex:"wineryName", sortable: true, resizable: false},
    					//	{id:"status", header: "状态", width: 100,dataIndex:"status", sortable: true, resizable: false},
							{ header: "操作",
								dataIndex: "operation",
								align: "center",
								xtype: "uxactioncolumn",
								items: [{
									text: "修改",
									iconCls: "icon-edit",
									tooltip: "修改",
									stopSelection: false,
									scope: this,
									handler: function(grid,rowIndex,columnIndex) {
										infoWin.show();
										var uptObj = grid.getStore().getAt(rowIndex).data;
										console.log(uptObj);
										productForm.getForm().setValues(uptObj);
										
										}
									},{
									text: "删除",
									iconCls: "icon-delete",
									tooltip: "删除酒",
									stopSelection: false,
									scope: this,
									handler: function(grid,rowIndex,columnIndex) {
										var record = grid.getStore().getAt(rowIndex);
										var str = record.data.status == -1 ? "启用" :"禁用";
										Ext.Msg.confirm(
												"提示","是否删除产品?",
												function(b) {
													if(b == "yes") {
														Ext.Msg.wait("正在删除产品...","请稍后");
														Ext.Ajax.request({
																url: "product/delete.do",
																method: "post",
																params: {
																	id: record.data.id
																},
																success: function(response,request) {
																	Ext.Msg.hide();
																	var result = Ext.decode(response.responseText)
																	if(result.message != "") {
																		showErrorMsg(result.message)
																	} else {
																		productStore.reload();
																	}
																}
															})
													}
												})
										}
									}
								]
							}
						]);

					var type_store = new Ext.data.JsonStore({
						fields: ["type_id", "type_name"],
						data: [{
							type_id: 1,
							type_name: "酱香型"
						}, {
							type_id: 2,
							type_name: "浓香型"
						}, {
							type_id: 3,
							type_name: "清香型"
						}, {
							type_id: 4,
							type_name: "兼香型"
						}, {
							type_id: 5,
							type_name: "凤香型"
						}, {
							type_id: 6,
							type_name: "董香型"
						}, {
							type_id: 7,
							type_name: "老白干型"
						}, {
							type_id: 8,
							type_name: "特香型"
						}, {
							type_id: 9,
							type_name: "馥郁香型"
						}]
					})
					
					var winery_store = new Ext.data.JsonStore({
						fields: ["id", "name"],
						url: "winery/list.do",
						autoLoad:true,
						root : "data"
					})
 
					var sence_store = new Ext.data.JsonStore({
						fields: ["id", "name"],
						url: "category/list.do",
						autoLoad:true,
						root : "rows"
					})

					var master_store = new Ext.data.JsonStore({
						url:"taster/list.do",
						fields: ["id", "name"],
						autoLoad:true,
						root : "data"
					})
					
					
					/************后台产品文件上传*******/
					var productListImgsForm = new Ext.form.FormPanel({
						style: "text-align:center:padding:20px",
						frame: true,
						style: "padding-top:10px",
						labelWidth: 95,
						labelAlign: "right",
						defaults: {
							anchor: "90%"
						},
				         id:"productListImg",
				         fileUpload:true,  
				         defaultType: 'textfield',  
				         items:[{
		                    fieldLabel: '后台产品图片',
		                    inputType:"file",
		                    name:'file',
		                    id:'file',
		                    width: 320,
		                    listeners:{
		                    	change:function(v){
		                    		productListImgsForm.getForm().submit({
		                    			url:"file/upload.do",
							            waitMsg:"正在上传...",
										success:function(form,result){
											var d = Ext.decode(result.response.responseText);
											if(d.success){
												showMsg("图片上传成功！");
												productForm.getForm().findField("productListImgs").setValue(d.url);
											//	Ext.getCmp('logoPic').autoEl.src=d.url
												//console.log(Ext.getCmp('logoPic').autoEl.src)
												
											}
										},
										failure:function(form,action){
											showMsg("图片上传失败")
										}
		                    		});
		                    	}
		                    }
				    	}]
				    });
					
					/************手机端图片上传*******/
					var productDetailImgsForm = new Ext.form.FormPanel({
						style: "text-align:center:padding:20px",
						frame: true,
						style: "padding-top:10px",
						labelWidth: 95,
						labelAlign: "right",
						defaults: {
							anchor: "90%"
						},
				         id:"productDetailImgsForm",
				         fileUpload:true,  
				         defaultType: 'textfield',  
				         items:[{
		                    fieldLabel: '手机端产品图片',
		                    inputType:"file",
		                    name:'file',
		                    id:'productDetailfile',
		                    width: 320,
		                    listeners:{
		                    	change:function(v){
		                    		productDetailImgsForm.getForm().submit({
		                    			url:"file/upload.do",
							            waitMsg:"正在上传...",
										success:function(form,result){
											var d = Ext.decode(result.response.responseText);
											if(d.success){
												showMsg("图片上传成功！");
												productForm.getForm().findField("productDetailImgs").setValue(d.url);
											//	Ext.getCmp('logoPic').autoEl.src=d.url
												//console.log(Ext.getCmp('logoPic').autoEl.src)
												
											}
										},
										failure:function(form,action){
											showMsg("图片上传失败")
										}
		                    		});
		                    	}
		                    }
				    	}]
				    });
					
					/************终端文件上传*******/
					var mobileImgsForm = new Ext.form.FormPanel({
						style: "text-align:center:padding:20px",
						frame: true,
						style: "padding-top:10px",
						labelWidth: 95,
						labelAlign: "right",
						defaults: {
							anchor: "90%"
						},
				         id:"mobileImgsForm",
				         fileUpload:true,  
				         defaultType: 'textfield',  
				         items:[{
		                    fieldLabel: '终端产品图片',
		                    inputType:"file",
		                    name:'file',
		                    id:'file1',
		                    width: 320,
		                    listeners:{
		                    	change:function(v){
		                    		mobileImgsForm.getForm().submit({
		                    			url:"file/upload.do",
							            waitMsg:"正在上传...",
										success:function(form,result){
											var d = Ext.decode(result.response.responseText);
											if(d.success){
												showMsg("图片上传成功！");
												productForm.getForm().findField("mobileImgs").setValue(d.url);
											//	Ext.getCmp('logoPic').autoEl.src=d.url
												//console.log(Ext.getCmp('logoPic').autoEl.src)
												
											}
										},
										failure:function(form,action){
											showMsg("图片上传失败")
										}
		                    		});
		                    	}
		                    }
				    	}]
				    });
					
					
					
					
					/*************添加商品form*******/
					var productForm = new Ext.FormPanel({
						style: "text-align:center:padding:20px",
						frame: true,
						style: "padding-top:10px",
						labelWidth: 95,
						labelAlign: "right",
						defaults: {
							anchor: "90%"
						},
						items: [{
								xtype: "hidden",
								fieldLabel: "id",
								name: "id",
								allowBlank: false
							},
							{
								xtype: "textfield",
								fieldLabel: "产品编号",
								name: "number",
								allowBlank: false
							},{
								xtype: "textfield",
								fieldLabel: "名称",
								name: "name",
								allowBlank: false
							}, {
								xtype: "textfield",
								fieldLabel: "度数",
								name: "degree",
								allowBlank: false
							}, new Ext.form.ComboBox({
								fieldLabel: '香型',
								store: type_store,
								valueField: 'type_name',
								displayField: 'type_name',
								mode: 'local',
								emptyText: '请选择...',
								blankText: '请选择香型',
								typeAhead: true,
								triggerAction: 'all',
								selectOnFocus: true,
								editable: true,
								hiddenName: "type",
								listeners: {
									select: function(e) { // 监听select事件
										console.dir(e);
									}
								}
							}), new Ext.form.ComboBox({
								fieldLabel: '酒厂',
								store: winery_store,
								valueField: 'id',
								displayField: 'name',
								mode: 'local',
								emptyText: '请选择一个酒厂',
								blankText: '请选择一个酒厂',
								typeAhead: true,
								triggerAction: 'all',
								selectOnFocus: true,
								editable: true,
								hiddenName: "wineryName",
								listeners: {
									select: function(e) { // 监听select事件
										Ext.getCmp("wineryId_add").setValue(this.value);
										console.dir(e);
									}
								}
							}),{
								id:"wineryId_add",
								xtype: "hidden",
								name: "wineryId",
								//hidden:true,
								allowBlank: false
							},new Ext.form.ComboBox({
								fieldLabel: '使用场景',
								store: sence_store,
								valueField: 'id',
								displayField: 'name',
								mode: 'local',
								emptyText: '请选择一个场景',
								blankText: '请选择一个场景',
								typeAhead: true,
								triggerAction: 'all',
								selectOnFocus: true,
								editable: true,
								hiddenName: "senceName",
								listeners: {
									select: function(e) { // 监听select事件
										console.dir(e);
										Ext.getCmp("senceId_add").setValue(this.value);
									}
								}
							}),{
								//hidden:true,
								id:"senceId_add",
								xtype: "hidden",
								name: "senceId",
								allowBlank: false
							},/*{
							    xtype : 'box',  
							    fieldLabel: '产品图片',
							    id : 'logoPic',
							    width: 80,
							    height: 80,  
							    autoWidth:true,
							    autoEl : {  
							        tag : 'img',  
							        src : "",
							        style : 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);width:100px' 
							    }
							},*/{
					    		xtype:"hidden",
					    		name:"mobileImgs",
					    		id:"mobileImg",
					    	},{
					    		xtype:"hidden",
					    		name:"productListImgs",
					    		id:"productListImg ",
					    	},{
					    		xtype:"hidden",
					    		name:"productDetailImgs",
					    		id:"productDetailImg ",
					    	},{
								xtype: "textarea",
								fieldLabel: "备注",
								name: "remark",
								allowBlank: true
							} 
						]
					});
					
				 
					var userValues = {
						    UserName: "Qi Fei",
						    Email: "youring2@gmail.com"
						};
					
					var infoWin = new Ext.Window({
						items: [productForm,productListImgsForm,productDetailImgsForm,mobileImgsForm],
						title: "",
						frame: true,
						width: 600,
						autoHeight: true,
						resizable: false,
						modal: true,
						closeAction: "hide",
						manager: wg,
						buttons: [{
							text: "确定",
							handler: function() {
								if(productForm.getForm().isValid()) {
									var formData = productForm.getForm().getValues();
									var dataStr={};
									dataStr.productId=formData.id;
									dataStr.productDetailId=0;
									dataStr.name=formData.name;
									dataStr.wineryId=formData.wineryId;
									dataStr.wineryName=productForm.getForm().findField('wineryName').lastSelectionText;
									dataStr.categoryId=formData.senceId;
									dataStr.categoryName= productForm.getForm().findField('senceName').lastSelectionText;
									dataStr.flavorTypes=formData.type;
									dataStr.degree=formData.degree;
									dataStr.number=formData.number;
									dataStr.unit=formData.unit;
									dataStr.capacity=21;
									dataStr.description=formData.remark;
									dataStr.productListImgs=[{name:'测试图片',url:formData.productListImgs,description:'但是开发和快乐的事富华大厦发'}];
									dataStr.productDetailImgs=[{name:'测试图片',url:formData.productDetailImgs,description:'但是开发和快乐的事富华大厦发'}];
									dataStr.mobileImgs=[{name:'测试图片',url:formData.mobileImgs,description:'但是开发和快乐的事富华大厦发'}];
									Ext.Msg.wait("正在添加...","请稍后");
									
									Ext.Ajax.request({
										url: "product/saveOrUpdate.do",
										params:{dataStr:Ext.util.JSON.encode(dataStr)},
										success: function(form, action) {
											productStore.reload();
											infoWin.hide();
											Ext.Msg.hide();
										},
										failure: function(form, action) {
											showErrorMsg(action.result.message);
										}
									});
								}else{
									showErrorMsg("验证失败");
								}
							}
						}],
						buttonAlign: "center"
					});
					
					var search = new Ext.FormPanel({
						title: "查询",
						region: "west",
						width: 230,
						collapsible: true,
						frame: true,
						titleCollapse: true,
						bodyStyle: "padding-top:10px",
						labelAlign: "right",
						labelWidth: 50,
						defaults: {
							anchor: "90%"
						},
						items: [{
							xtype: "textfield",
							fieldLabel: "名称",
							name: "name"
						}],
						buttonAlign: 'center',
						buttons: [{
							text: '查询',
							handler: function() {
								var data = search.getForm().getValues(false);
								productStore.baseParams = data;
								productStore.load({
									params: {
										start: 0,
										limit: 20
									}
								});
							}
						}, {
							text: '重置',
							handler: function() {
								search.getForm().reset();
							}
						}]
					});
					
					
					var productTable = new Ext.grid.GridPanel({
						store: productStore,  
						cm: productCm, 
						//sm: sm,
						enableColumnMove: false,
						enableColumnResize: false,
						stripeRows: true,
						region: "center",
						loadMask: true,
						viewConfig: {
							forceFit: true, // 自动填满
							getRowClass: function(record,index) {
								var c = record.data.status
								if(c < 0) {
									return 'rowdisable';
								}
								return '';
							}
						},
						buttonAlign: "left",			
						tbar: [{
							text: "添加产品",
							handler: function() {
								productForm.getForm().reset();
								infoWin.show();
							}
						}],
						bbar: new Ext.PagingToolbar({
							pageSize: 20,
							store: productStore,
							beforePageText: "第",
							afterPageText: "页，共{0}页",
							lastText: "尾页",
							nextText: "下一页",
							prevText: "上一页",
							firstText: "首页",
							refreshText: "刷新",
							displayInfo: true,
							displayMsg: '一共 {2}条员工信息',
							emptyMsg: "没有用户信息"
						}),
						listeners: {
							"afteredit": function(e) {
								var record = e.record
								Ext.Ajax.request({
										url: "",
										params: e.record.data,
										success: function(response,request) {
											var data = Ext.decode(response.responseText)
											if(data.message != "") {
												showErrorMsg(data.message);
												record.reject();
											} else {
												record.commit();
											}
										}
									});
							},
							click: function(e) {
								console.dir(productStore);
							}
						}
					});

					win = desktop.createWindow({
						id: 'PRODUCT-win',
						listeners: {
		              		'close':function(r){
		              			infoWin.close();
		              		}
						},
						title: '产品管理',
						height: 500,
						items: [productTable,search],
						iconCls: 'icon-em',
						shim: false,
						animCollapse: false,
						constrainHeader: true,
						layout: 'border'
					});
				}
				win.show();
			}
		});