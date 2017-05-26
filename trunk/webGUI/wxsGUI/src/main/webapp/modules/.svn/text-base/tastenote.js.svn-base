/*
 * USM window
 */
Emin.TASTENOTEWindow = Ext
		.extend(
				Ext.app.Module,
				{
					id : 'TASTENOTE-win',
					init : function() {
						this.launcher = {
							text : '酒评管理',
							iconCls : 'icon-em',
							handler : this.createWindow,
							scope : this
						}
					},

					createWindow : function() {
						var desktop = this.app.getDesktop();
						var win = desktop.getWindow('TASTENOTE-win');

						if (!win) {
							// 添加酒品使用的window
							var wg = new Ext.WindowGroup();
							wg.zseed = 7500;
							var data_tasteId;
							// 图片数据源

							// 图片显示模板

							var tasteStore = new Ext.data.JsonStore({
								// results 表示结果数
								// rows 表示从后台传过来的JSON数据
								// 自动加载(不能用store.load())
								url : "taste/page.do",
								totalProperty : "results",
								root : "rows",
								fields : [ 'id', 'productName', 'productImg' ],
							});
							tasteStore.load();

							var master_store = new Ext.data.JsonStore({
								url : "taster/list.do",
								fields : [ "id", "name" ],
								autoLoad : true,
								root : "data"
							})

							var sm = new Ext.grid.CheckboxSelectionModel();

							var tasteCm = new Ext.grid.ColumnModel(
									[
											new Ext.grid.RowNumberer(),
											// sm,
											{
												id : "img",
												header : "头像",
												width : 100,
												dataIndex : "productImg",
												sortable : false
											},
											{
												id : "name",
												header : "产品名称",
												width : 60,
												dataIndex : "productName",
												sortable : true
											},
											{
												header : "操作",
												dataIndex : "operation",
												align : "center",
												xtype : "uxactioncolumn",
												items : [
														{
															text : "查看评价详情",
															iconCls : "icon-edit",
															tooltip : "查看评价详情",
															stopSelection : false,
															scope : this,
															handler : function(
																	grid,
																	rowIndex,
																	columnIndex) {
																var uptObj = grid
																		.getStore()
																		.getAt(
																				rowIndex).data;
																console
																		.log(uptObj);
																tasteForm
																		.getForm()
																		.setValues(
																				uptObj);
																editWin.show();
															}
														},
														{
															text : "删除",
															iconCls : "icon-delete",
															tooltip : "删除酒",
															stopSelection : false,
															scope : this,
															handler : function(
																	grid,
																	rowIndex,
																	columnIndex) {
																var record = grid
																		.getStore()
																		.getAt(
																				rowIndex);
																var str = record.data.status == -1 ? "启用"
																		: "禁用";
																Ext.Msg
																		.confirm(
																				"提示",
																				"是否删除评价?",
																				function(
																						b) {
																					if (b == "yes") {
																						Ext.Msg
																								.wait(
																										"正在删除评价...",
																										"请稍后");
																						Ext.Ajax
																								.request({
																									url : "taste/delete.do",
																									method : "post",
																									params : {
																										id : record.data.id
																									},
																									success : function(
																											response,
																											request) {
																										Ext.Msg
																												.hide();
																										var result = Ext
																												.decode(response.responseText)
																										if (result.message != "") {
																											showErrorMsg(result.message)
																										} else {
																											tasteStore
																													.reload();
																										}
																									}
																								})
																					}
																				})
															}
														} ]
											} ]);
							var master_store = new Ext.data.JsonStore({
								url : "taster/list.do",
								fields : [ "id", "name" ],
								autoLoad : true,
								root : "data"
							})
							var comment = {
								columnWidth : .33,
								layout : 'form',
								xtype : 'fieldset',
								style : 'margin-left:10px',
								autoHeight : true,
								title : '添加大师评价',
								defaultType : 'textfield',
								items : [ new Ext.form.ComboBox({
									fieldLabel : '大师',
									store : master_store,
									valueField : 'id',
									displayField : 'name',
									mode : 'local',
									emptyText : '请选择...',
									blankText : '请选择大师',
									typeAhead : true,
									triggerAction : 'all',
									selectOnFocus : true,
									editable : true,
									hiddenName : "type",
									listeners : {
										select : function(e) { // 监听select事件
											console.dir(e);
										}
									}
								}), {
									xtype : "textarea",
									fieldLabel : '评价',
									name : "comment",
									allowBlank : false
								} ]
							}

							var tasteForm = new Ext.FormPanel(
									{
										style : "text-align:center:padding:20px",
										frame : true,
										style : "padding-top:10px",
										labelWidth : 85,
										labelAlign : "right",
										defaults : {
											anchor : "90%"
										},
										items : [
												{
													xtype : "textfield",
													name : "id",
													hidden : true,
													allowBlank : false
												},
												{
													xtype : "textfield",
													fieldLabel : "产品名称",
													name : "productName",
													allowBlank : false,
													editable : false
												},
												{
													xtype : "textfield",
													name : "productId",
													hidden : true,
													allowBlank : false
												}, {
													id : "masterName",
													xtype : "textfield",
													name : "name",
													hidden : true,
													allowBlank : true,

												}, {
													id : "masterID",
													xtype : "textfield",
													hidden : true,
													name : "wineTasterId",
													allowBlank : true,

												},{
													xtype : "button",
													hidden : true,
													name : "wineTasterId",
													allowBlank : true,
												}],
												buttons : [
															{
																text : '添加',
																handler : function() {
																	var data = search.getForm()
																			.getValues(false);
																	tasteStore.baseParams = data;
																	tasteStore.load({
																		params : {
																			start : 0,
																			limit : 20
																		}
																	});
																}
															}]
									});
							/*
							 * var data = userForm.getForm().getValues(false)
							 * productStore.baseParams = data;
							 * productStore.load();
							 */

							var editWin = new Ext.Window(
									{
										items : [ tasteForm, comment ],
										title : "添加或编辑评价",
										frame : true,
										width : 400,
										autoHeight : true,
										resizable : false,
										modal : true,
										closeAction : "hide",
										manager : wg,
										buttons : [ {
											text : "确定",
											handler : function() {
												if (tasteForm.getForm()
														.isValid()) {
													tasteForm
															.getForm()
															.submit(
																	{
																		url : "taste/saveOrUpdate.do",
																		success : function(
																				form,
																				action) {
																			tasteStore
																					.reload();
																			editWin
																					.hide();
																		},
																		failure : function(
																				form,
																				action) {
																			showErrorMsg(action.result.message);
																		}
																	});
												}
											}
										} ],
										buttonAlign : "center"
									});

							var search = new Ext.FormPanel({
								title : "查询",
								region : "west",
								width : 230,
								collapsible : true,
								frame : true,
								titleCollapse : true,
								bodyStyle : "padding-top:10px",
								labelAlign : "right",
								labelWidth : 50,
								defaults : {
									anchor : "90%"
								},
								items : [ {
									xtype : "textfield",
									fieldLabel : "产品名称",
									name : "name"
								} ],
								buttonAlign : 'center',
								buttons : [
										{
											text : '查询',
											handler : function() {
												var data = search.getForm()
														.getValues(false);
												tasteStore.baseParams = data;
												tasteStore.load({
													params : {
														start : 0,
														limit : 20
													}
												});
											}
										}, {
											text : '重置',
											handler : function() {
												search.getForm().reset();
											}
										} ]
							});

							var tasteTable = new Ext.grid.GridPanel(
									{
										store : tasteStore,
										cm : tasteCm,
										// sm: sm,
										enableColumnMove : false,
										enableColumnResize : false,
										stripeRows : true,
										region : "center",
										loadMask : true,
										viewConfig : {
											forceFit : true, // 自动填满
											getRowClass : function(record,
													index) {
												var c = record.data.status
												if (c < 0) {
													return 'rowdisable';
												}
												return '';
											}
										},
										buttonAlign : "left",
										bbar : new Ext.PagingToolbar({
											pageSize : 20,
											store : tasteStore,
											beforePageText : "第",
											afterPageText : "页，共{0}页",
											lastText : "尾页",
											nextText : "下一页",
											prevText : "上一页",
											firstText : "首页",
											refreshText : "刷新",
											displayInfo : true,
											displayMsg : '一共 {2}条评价信息',
											emptyMsg : "没有评价信息"
										}),
										listeners : {
											"afteredit" : function(e) {
												var record = e.record
												Ext.Ajax
														.request({
															url : "",
															params : e.record.data,
															success : function(
																	response,
																	request) {
																var data = Ext
																		.decode(response.responseText)
																if (data.message != "") {
																	showErrorMsg(data.message);
																	record
																			.reject();
																} else {
																	record
																			.commit();
																}
															}
														});
											},
											click : function(e) {
												console.dir(tasteStore);
											}
										}
									});

							win = desktop.createWindow({
								id : 'TASTENOTE-win',
								listeners : {

								},
								title : '酒评管理',
								height : 500,
								items : [ tasteTable, search ],
								iconCls : 'icon-em',
								shim : false,
								animCollapse : false,
								constrainHeader : true,
								layout : 'border'
							});
						}
						win.show();
					}
				});