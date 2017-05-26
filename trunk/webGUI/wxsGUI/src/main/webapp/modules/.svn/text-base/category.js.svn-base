/*
 * USM window
 */
Emin.CATEGORYWindow = Ext.extend(Ext.app.Module, {
	id: 'CATEGORY-win',
	init: function() {
		this.launcher = {
			text: '分类管理',
			iconCls: 'icon-em',
			handler: this.createWindow,
			scope: this
		}
	},

	createWindow: function() {
		var desktop = this.app.getDesktop();
		var win = desktop.getWindow('CATEGORY-win');

		if(!win) {
			//添加酒品使用的window
			var wg = new Ext.WindowGroup();
			wg.zseed = 7500;
			var data_teasterId;
			//图片数据源
			var imgStore = new Ext.data.JsonStore({
				url: "${basePath}ewm/admin/file!loadFles",
				fields: ["url", "id"]
			});
			//图片显示模板
			
			var categoryStore = new Ext.data.JsonStore({
				//results 表示结果数  
				//rows 表示从后台传过来的JSON数据  
				url: "category/page.do",
				totalProperty: "results",
				root: "rows",
				fields: ['id','name','status']
			});
			categoryStore.load({
				params: {
					start: 0,
					limit: 20
				}
			});
			//	url: "product/page.do",
			//totalProperty: "total",
			//root: "rows",
			/*fields: ["name", "gender", "mobilePhone",
				"email", "id"
			]*/

			// 数据存储
			/*
			 * var productStore = new Ext.data.Store({ proxy:new
			 * Ext.data.HttpProxy({ url:"person/page.do",
			 * method:"post" }), render:new
			 * Ext.data.JsonReader({ totalProperty:"total",
			 * root:"rows"
			 * 
			 * },) }); productStore.load({ params:{ start:0,
			 * limit:20 } });
			 */

			/*	productStore.load({
					params: {
						start: 0,
						limit: 20
					}
				});*/

			var sm = new Ext.grid.CheckboxSelectionModel();

			var teasterCm = new Ext.grid.ColumnModel(
				[
					new Ext.grid.RowNumberer(),
					//sm,
					{ id: "name", header: "分类名称", width: 60, dataIndex: "name", sortable: false },
					{
						header: "操作",
						dataIndex: "operation",
						align: "center",
						xtype: "uxactioncolumn",
						items: [{
							text: "修改",
							iconCls: "icon-edit",
							tooltip: "修改",
							stopSelection: false,
							scope: this,
							handler: function(grid, rowIndex, columnIndex) {
								var record = grid.getStore().getAt(rowIndex);
								var uptObj = grid.getStore().getAt(rowIndex).data;
								console.log(uptObj);
								console.log(record);
								editCategoryForm.getForm().setValues(uptObj);
								editWin.show();
							}
						}, {
							text: "删除",
							iconCls: "icon-delete",
							tooltip: "删除酒",
							stopSelection: false,
							scope: this,
							handler: function(grid, rowIndex, columnIndex) {
								var record = grid.getStore().getAt(rowIndex);
								//var str = record.data.status == -1 ? "启用" : "禁用";
								console.log(record.data.id)
								Ext.Msg.confirm(
									"提示", "是否删除分类?",
									function(b) {
										if(b == "yes") {
											Ext.Msg.wait(
												"正在删除分类...", "请稍后");
											Ext.Ajax.request({
												url: "category/delete.do",
												method: "post",
												params: {
													id: record.data.id
												},
												success: function(response, request) {
													Ext.Msg.hide();
													var result = Ext.decode(response.responseText)
													if(result.message != "") {
														showErrorMsg(result.message)
													} else {
														categoryStore.reload();
													}
												}
											})
										}
									})
							}
						}]
					}
				]);

			var addCategoryForm = new Ext.FormPanel({
				style: "text-align:center:padding:20px",
				frame: true,
				style: "padding-top:10px",
				labelWidth: 85,
				labelAlign: "right",
				defaults: {
					anchor: "90%"
				},
				items: [{
						xtype: "textfield",
						fieldLabel: "分类名称",
						name: "name",
						allowBlank: false
					}
				]
			});

			var editCategoryForm = new Ext.FormPanel({
				style: "text-align:center:padding:20px",
				frame: true,
				style: "padding-top:10px",
				labelWidth: 85,
				labelAlign: "right",
				defaults: {
					anchor: "90%"
				},
				items: [{
						xtype: "textfield",
						fieldLabel: "分类名称",
						name: "name",
						allowBlank: false
					},{
						xtype: "textfield",
						name: "id",
						hidden:true,
						allowBlank: false
					}
				]
			});

			var infoWin = new Ext.Window({
				items: [addCategoryForm],
				title: "添加分类",
				frame: true,
				width: 400,
				autoHeight: true,
				resizable: false,
				modal: true,
				closeAction: "hide",
				manager: wg,
				buttons: [{
					text: "确定",
					handler: function() {
						if(addCategoryForm.getForm().isValid()) {
							addCategoryForm.getForm().submit({
								url: "category/saveOrUpdate.do",
								success: function(form, action) {
									categoryStore.reload();
									infoWin.hide();
								},
								failure: function(form, action) {
									showErrorMsg(action.result.message);
								}
							});
						}
					}
				}],
				buttonAlign: "center"
			});

			var editWin = new Ext.Window({
				items: [editCategoryForm],
				title: "编辑分类",
				frame: true,
				width: 400,
				autoHeight: true,
				resizable: false,
				modal: true,
				closeAction: "hide",
				manager: wg,
				buttons: [{
					text: "确定",
					handler: function() {
						if(editCategoryForm.getForm().isValid()) {
							editCategoryForm.getForm().submit({
								url: "category/saveOrUpdate.do",
								success: function(form, action) {
									categoryStore.reload();
									editWin.hide();
								},
								failure: function(form, action) {
									showErrorMsg(action.result.message);
								}
							});
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
						categoryStore.baseParams = data;
						categoryStore.load({
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

			var categoryTable = new Ext.grid.GridPanel({
				store: categoryStore,
				cm: teasterCm,
				//sm: sm,
				enableColumnMove: false,
				enableColumnResize: false,
				stripeRows: true,
				region: "center",
				loadMask: true,
				viewConfig: {
					forceFit: true, // 自动填满
					getRowClass: function(record, index) {
						var c = record.data.status
						if(c < 0) {
							return 'rowdisable';
						}
						return '';
					}
				},
				buttonAlign: "left",
				tbar: [{
					text: "添加分类",
					handler: function() {
						infoWin.show();
					}
				}],
				bbar: new Ext.PagingToolbar({
					pageSize: 20,
					store: categoryStore,
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
							success: function(response, request) {
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
						console.dir(categoryStore);
					}
				}
			});

			win = desktop.createWindow({
				id: 'CATEGORY-win',
				listeners: {

				},
				title: '分类管理',
				height: 500,
				items: [categoryTable, search],
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