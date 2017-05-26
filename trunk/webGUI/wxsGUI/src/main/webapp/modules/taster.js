/*
 * USM window
 */
Emin.TASTERWindow = Ext.extend(Ext.app.Module, {
	id: 'TASTER-win',
	init: function() {
		this.launcher = {
			text: '大师管理',
			iconCls: 'icon-em',
			handler: this.createWindow,
			scope: this
		}
	},

	createWindow: function() {
		var desktop = this.app.getDesktop();
		var win = desktop.getWindow('TASTER-win');

		if(!win) {
			//添加酒品使用的window
			var wg = new Ext.WindowGroup();
			wg.zseed = 7500;
			var data_tasterId;
			//图片数据源
			var imgStore = new Ext.data.JsonStore({
				url: "${basePath}ewm/admin/file!loadFles",
				fields: ["url", "id"]
			});
			//图片显示模板

			var tasterStore = new Ext.data.JsonStore({
				//results 表示结果数  
				//rows 表示从后台传过来的JSON数据  
				url: "taster/page.do",
				//自动加载(不能用store.load())  
				totalProperty: "results",
				root: "rows",
				fields: ['id', 'img', 'name', 'honor', 'description'],
			});
			tasterStore.load();
			var sm = new Ext.grid.CheckboxSelectionModel();

			var tasterCm = new Ext.grid.ColumnModel(
				[
					new Ext.grid.RowNumberer(),
					//sm,
					{ id: "img", header: "头像", width: 60, dataIndex: "img", sortable: false },
					{ id: "name", header: "名称", width: 60, dataIndex: "name", sortable: true },
					{ id: "honor", header: "主要荣誉", width: 150, dataIndex: "honor", sortable: true },
					{ id: "description", header: "大师描述", width: 100, dataIndex: "description", sortable: false },
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
								var uptObj = grid.getStore().getAt(rowIndex).data;
								console.log(uptObj);
								editTasterForm.getForm().setValues(uptObj);
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
								var str = record.data.status == -1 ? "启用" : "禁用";
								Ext.Msg.confirm(
									"提示", "是否删除大师?",
									function(b) {
										if(b == "yes") {
											Ext.Msg.wait(
												"正在删除员工...", "请稍后");
											Ext.Ajax.request({
												url: "taster/delete.do",
												url: "",
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
														tasterStore.reload();
													}
												}
											})
										}
									})
							}
						}]
					}
				]);

			var addTasterForm = new Ext.FormPanel({
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
						fieldLabel: "姓名",
						name: "name",
						allowBlank: false
					}, {
						xtype: "textarea",
						fieldLabel: "主要荣耀",
						name: "honor",
						allowBlank: true,

					}, {
						xtype: "textarea",
						fieldLabel: "大师描述",
						name: "description",
						allowBlank: false
					},
					new Ext.form.TextField({
						name: 'imgFile',
						fieldLabel: '头像上传',
						inputType: 'file',
						allowBlank: true,
						blankText: '请浏览图片'
					}), new Ext.form.TextField({
						LabelStyle: "margin-top:20px",
						name: 'imgFile',
						fieldLabel: '描述长图上传',
						inputType: 'file',
						allowBlank: true,
						blankText: '请浏览图片'
					})
				]
			});

			var editTasterForm = new Ext.FormPanel({
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
						name: "id",
						hidden:true,
						allowBlank: false
					},{
						xtype: "textfield",
						fieldLabel: "姓名",
						name: "name",
						allowBlank: false
					}, {
						xtype: "textarea",
						fieldLabel: "主要荣耀",
						name: "honor",
						allowBlank: true,

					}, {
						xtype: "textarea",
						fieldLabel: "大师描述",
						name: "description",
						allowBlank: false
					},
					new Ext.form.TextField({
						name: 'imgFile',
						fieldLabel: '头像上传',
						inputType: 'file',
						allowBlank: true,
						blankText: '请浏览图片'
					}), new Ext.form.TextField({
						LabelStyle: "margin-top:20px",
						name: 'imgFile',
						fieldLabel: '描述长图上传',
						inputType: 'file',
						allowBlank: true,
						blankText: '请浏览图片'
					})
				]
			});
			/*
			 * var data = userForm.getForm().getValues(false)
			 * productStore.baseParams = data; productStore.load();
			 */

			var infoWin = new Ext.Window({
				items: [addTasterForm],
				title: "添加大师",
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
						if(addTasterForm.getForm().isValid()) {
							addTasterForm.getForm().submit({
								url: "taster/saveOrUpdate.do",
								success: function(form, action) {
									tasterStore.reload();
									infoWin.hide();
									addTasterForm.getForm().reset();
								},
								failure: function(form, action) {
									showErrorMsg(action.result.message);
									addTasterForm.getForm().reset();
								}
							});
						}
						
					}
				}],
				buttonAlign: "center"
			});

			var editWin = new Ext.Window({
				items: [editTasterForm],
				title: "编辑大师",
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
						if(editTasterForm.getForm().isValid()) {
							editTasterForm.getForm().submit({
								url: "taster/saveOrUpdate.do",
								success: function(form, action) {
									tasterStore.reload();
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
						tasterStore.baseParams = data;
						tasterStore.load({
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

			var tasterTable = new Ext.grid.GridPanel({
				store: tasterStore,
				cm: tasterCm,
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
					text: "添加大师",
					handler: function() {
						infoWin.show();
					}
				}],
				bbar: new Ext.PagingToolbar({
					pageSize: 20,
					store: tasterStore,
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
						console.dir(tasterStore);
					}
				}
			});

			win = desktop.createWindow({
				id: 'TASTER-win',
				listeners: {

				},
				title: '大师管理',
				height: 500,
				items: [tasterTable, search],
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