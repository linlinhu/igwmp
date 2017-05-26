/*
 * USM window
 */
Emin.DIVIDEWindow = Ext.extend(Ext.app.Module, {
	id: 'DIVIDE-win',
	init: function() {
		this.launcher = {
			text: '分成比例',
			iconCls: 'icon-em',
			handler: this.createWindow,
			scope: this
		}
	},

	createWindow: function() {
		var desktop = this.app.getDesktop();
		var win = desktop.getWindow('DIVIDE-win');

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
			
			var divideStore = new Ext.data.JsonStore({
				//results 表示结果数  
				//rows 表示从后台传过来的JSON数据  
                url: 'cooperation/page.do',  
                totalProperty:"total",
                root:"rows",
                fields: ['id', 'name', 'memo','roleType','profitPercent','operation']
			});
			divideStore.load();
			var role_store = new Ext.data.JsonStore({
				fields: ["id", "name"],
				data:[{
					id:1,
					name:'餐厅'
				},{
					id:2,
					name:'店长'
				},{
					id:3,
					name:'服务员'
				}]
			})

			var sm = new Ext.grid.CheckboxSelectionModel();

			var teasterCm = new Ext.grid.ColumnModel(
				[
					new Ext.grid.RowNumberer(),
					//sm,
					{ id: "name", header: "名称", width: 60, dataIndex: "name", sortable: false },
					{ id: "roleType", header: "角色", width: 100, dataIndex: "roleType", sortable: true,renderer:function(value){
							console.log("角色type:"+value)
							if(value==3){
								return "服务员"
							}else if(value==2){
								return "店长"
							}else{
								return "餐厅"
							}
						}
					},
					{ id: "profitPercent", header: "分成比例(%)", width: 100, dataIndex: "profitPercent", sortable: true },
					{ id: "memo", header: "备注", width: 100, dataIndex: "memo", sortable: false },
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
								editWin.show();
								var uptObj = grid.getStore().getAt(rowIndex).data;
								divideForm.getForm().setValues(uptObj);
							
							}
						}, {
							text: "删除",
							iconCls: "icon-delete",
							tooltip: "删除方案",
							stopSelection: false,
							scope: this,
							handler: function(grid, rowIndex, columnIndex) {
								var record = grid.getStore().getAt(rowIndex);
								var str = record.data.status == -1 ? "启用" : "禁用";
								Ext.Msg.confirm(
									"提示", "是否删除方案?",
									function(b) {
										if(b == "yes") {
											Ext.Msg.wait(
												"正在删除方案...", "请稍后");
											Ext.Ajax.request({
												url: "cooperation/delete.do",
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
														divideStore.reload();
													}
												}
											})
										}
									})
							}
						}]
					}
				]);

			var divideForm = new Ext.FormPanel({
				style: "text-align:center:padding:20px",
				frame: true,
				style: "padding-top:10px",
				labelWidth: 85,
				labelAlign: "right",
				defaults: {
					anchor: "90%"
				},
				items: [{
						xtype: "hidden",
						name: "id",
						value: '',
						//hidden:true,
						allowBlank: true
					},{
						xtype: "textfield",
						fieldLabel: "方案名称",
						name: "name",
						allowBlank: false
					},new Ext.form.ComboBox({
						fieldLabel: '角色',
						store: role_store,
						valueField: 'id',
						displayField: 'name',
						mode: 'local',
						emptyText: '请选择...',
						blankText: '请选择角色',
						typeAhead: true,
						triggerAction: 'all',
						selectOnFocus: true,
						editable: false,
						hiddenName: "roleType",
						listeners: {
							select: function(e) { // 监听select事件
								console.dir(e);
								Ext.getCmp("roleId").setValue(this.value);
							}
						}
					}),{
						id:"roleId",
						xtype: "hidden",
						name: "role",
						allowBlank: false
					},{
						xtype: "textfield",
						fieldLabel: "分成比例(%)",
						name: "profitPercent",
						allowBlank: false,
						regex: /^\d\.([1-9]{1,2}|[0-9][1-9])$|^[1-9]\d{0,1}(\.\d{1,2}){0,1}$|^100(\.0{1,2}){0,1}$/,
                        regexText: '请输入正确的数据类型',
                        allowDecimals: true,
					}, {
						xtype: "textarea",
						fieldLabel: "备注",
						name: "memo",
						allowBlank: true
					}
				]
			});
			
			var editWin = new Ext.Window({
				items: [divideForm],
				title: "方案信息",
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
						if(divideForm.getForm().isValid()) {
							var formData = divideForm.getForm().getValues();
							var dataStr={};
							dataStr.id=formData.id;
							dataStr.name=formData.name;
							dataStr.memo=formData.memo;
							dataStr.roleType=formData.role;
							dataStr.profitPercent=formData.profitPercent;
							Ext.Msg.wait("正在保存...","请稍后");
							Ext.Ajax.request({
								url: "cooperation/saveOrUpdate.do",
								params:{jsonStr:Ext.util.JSON.encode(dataStr)},
								success: function(form, action) {
									divideStore.reload();
									editWin.hide();
									Ext.Msg.hide();
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
				width: 280,
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
					fieldLabel: "方案名称",
					name: "keyword"
				},new Ext.form.ComboBox({
					fieldLabel: '角色',
					store: role_store,
					valueField: 'id',
					displayField: 'name',
					mode: 'local',
					emptyText: '请选择...',
					blankText: '请选择角色',
					typeAhead: true,
					triggerAction: 'all',
					selectOnFocus: true,
					editable: false,
					hiddenName: "roleType",
					listeners: {
						select: function(e) { // 监听select事件
							console.dir(e);
						}
					}
				})],
				buttonAlign: 'center',
				buttons: [{
					text: '查询',
					handler: function() {
						var data = search.getForm().getValues(false);
						divideStore.baseParams = data;
						divideStore.load({
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

			var divideTable = new Ext.grid.GridPanel({
				store: divideStore,
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
					text: "添加分成方案",
					handler: function() {
						divideForm.getForm().reset();
						editWin.show();
					}
				}],
				bbar: new Ext.PagingToolbar({
					pageSize: 20,
					store: divideStore,
					beforePageText: "第",
					afterPageText: "页，共{0}页",
					lastText: "尾页",
					nextText: "下一页",
					prevText: "上一页",
					firstText: "首页",
					refreshText: "刷新",
					displayInfo: true,
					displayMsg: '一共 {2}条方案信息',
					emptyMsg: "没有方案信息"
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
						console.dir(divideStore);
					}
				}
			});

			win = desktop.createWindow({
				id: 'DIVIDE-win',
				listeners: {

				},
				title: '分成比例',
				height: 500,
				items: [divideTable, search],
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