/*
 * USM window
 */
Emin.ORGMWindow = Ext.extend(Ext.app.Module, {
    id:'ORGM-win',
    init : function(){
        this.launcher = {
            text: '组织管理',
            iconCls:'icon-orgn',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('ORGM-win');
		if(!win){
//			var codes = "${codes}";
//			codes = codes.split(",");
			
			var wg = new Ext.WindowGroup()
			wg.zseed = 7500
			var store = new Ext.data.JsonStore({
				url:"org/member.do",
				totalProperty:"total",
				root:"rows",
				fields:["id","name","mobilePhone","email","gender"]
			})
			var treeLoader = new Ext.tree.TreeLoader({
				dataUrl:"org/tree.do",
				requestMethod :"POST",
				listeners:{
					beforeload:function(loader,node){
						treeLoader.baseParams.pid = node.attributes.id
					}
				}
			})
			var category = null;
			var contextMenu = new Ext.menu.Menu({
				items:[{
					iconCls:"icon-edit",
					text:"编辑",
					handler:function(){
						Ext.Msg.prompt("提示","请输入类别名称",function(b,v){
							if(b=="ok" && v!=""){
								Ext.Msg.wait("正在保存类别...","请稍后")
								Ext.Ajax.request({
									url:"org/save.do",
									params:{
										id:category.attributes.id,
										pid:category.attributes.pid,
										name:v
									},
									success:function(response,request){
										Ext.Msg.hide()
										var d = Ext.decode(response.responseText)
										if(d.message!=""){
											showErrorMsg(d.message)
										}else{
											category.parentNode.reload()
											
										}
									}
								})
							}
						},this,false,category.attributes.name);
					}
				},{
					iconCls:"icon-delete",
					text:"删除",
					handler:function(){
						Ext.Msg.confirm("提示","是否确认删除此类别？",function(b){
							if(b=="yes"){
								Ext.Ajax.request({
									url:"org/delete.do",
									params:{
										id:category.attributes.id
									},
									success:function(response,request){
										var d = Ext.decode(response.responseText)
										if(d.message!=""){
											showErrorMsg(d.message)
										}else{
											category.parentNode.reload()
											
										}
									}
								})
							}
						})
					}
				}]
			})
			var tree = new Ext.tree.TreePanel({
				loader:treeLoader,
				rootVisible:false,
				root:new Ext.tree.AsyncTreeNode({
					id:"0",
					text:'root'
				}),
				listeners:{
					click:function(node){
						refreshPLstBySelOid(node.attributes.id);
					},
					contextmenu:function(node,e){
						e.preventDefault();
						category = node
						contextMenu.showAt(e.getXY())
					}
				},
				tbar:[{
					text:'添加组织',
					//disabled:codes.indexOf("add")==-1,
					handler:function(){
						var pnode = tree.getSelectionModel().getSelectedNode()
						var pid = 0
						if(pnode!=null){
							pid = pnode.attributes.id
						}
						Ext.Msg.prompt("提示","请输入组织名称",function(b,v){
							if(b=="ok" && v!=""){
								Ext.Msg.wait("正在保存类别...","请稍后")
								Ext.Ajax.request({
									url:"org/save.do",
									params:{
										pid:pid,
										name:v
									},
									success:function(response,request){
										Ext.Msg.hide()
										var d = Ext.decode(response.responseText)
										if(d.message!=""){
											showErrorMsg(d.message)
										}else{
											if(pnode!=null){
												pnode.reload()
											}else{
												tree.getRootNode().reload()
												tree.expandAll()
											}
											
										}
									}
								})
							}
						},this,false,"");
					}
				}],
				autoScroll:true,
				region:"west",
				width:240
			})
			
			
			
			
			var freeStore = new Ext.data.JsonStore({
				url:"org/unallocated.do",
				totalProperty:"total",
				root:"rows",
				fields:["id","name","mobilePhone","email","gender"]
			})
			freeStore.load({
				params:{
					start:0,
					limit:20
				}
			})
			var freeSm = new Ext.grid.CheckboxSelectionModel()
			var freeCm = new Ext.grid.ColumnModel([
				new Ext.grid.RowNumberer(),
				freeSm,
				{header:"编号",dataIndex:"id",width:60},
				{header:"姓名",dataIndex:"name"},
				{header:"手机",dataIndex:"mobilePhone"},
				{header:"邮箱",dataIndex:"email",width:150},
				{header:"性别",dataIndex:"gender",width:80},
				{header:"操作",dataIndex:"operation",xtype:"uxactioncolumn",
					items:[
						{
							text:"分配",
							iconCls:"icon-add",
							tooltip:"分配",
							handler:function(grid,rowIndex,columnIndex){
								var record = freeStore.getAt(rowIndex)
								//record.data 选中实体;
								allotPersonToOrg(record.data.id);
							}
						}
					]
				}
			]);
			var freeMembersPanel = new Ext.grid.GridPanel({
				cm:freeCm,
				sm:freeSm,
				loadMask:true,
				stripeRows:true,
				store:freeStore,
				viewConfig:{
					forceFit:true,
					enableRowBody:true
				},
				region:'center',
				tbar:[{
					text:'分配',
					iconCls:"icon-add",
					handler:function(){
						var records = freeSm.getSelections()
						if(records.length==0){
							showWarningMsg("请选择要分配的员工！")
							return;
						}
						var ids = []
						Ext.each(records,function(record){
							ids.push(record.data.id)
						})
						allotPersonToOrg(ids);
					}
				}],
				bbar:new Ext.PagingToolbar({
        			pageSize : 20,
					store : freeStore,
					beforePageText : "第",
					afterPageText : "页，共{0}页",
					lastText : "尾页",
					nextText : "下一页",
					prevText : "上一页",
					firstText : "首页",
					refreshText : "刷新",
					displayInfo : true,
					displayMsg : '一共 {2}条人员信息',
					emptyMsg : "没有人员信息"
				})
			})
			
			/*******
			 * 获取选中的组织节点ID
			 */
			function getSelOrgId(){
				var pnode = tree.getSelectionModel().getSelectedNode();
				var pid = 0;
				if(pnode!=null){
					pid = pnode.attributes.id
				}
				return pid;
			}
			/*********
			 * 将员工分配到组织
			 * @param {Object} personIds 员工ID，可用逗号隔开
			 */
			function allotPersonToOrg(personIds){
				var oid = getSelOrgId();
				Ext.Msg.wait("正在分配组织...","请稍后")
				Ext.Ajax.request({
					url:"org/assign.do",
					params:{
						orgId:oid,
						personIds:personIds
					},
					success:function(response,request){
						Ext.Msg.hide()
						var d = Ext.decode(response.responseText)
						if(d.message!=""){
							showErrorMsg(d.message)
						}else{
							refreshPLstBySelOid(oid);
							freeStore.load({
								params:{
									start:0,
									limit:20
								}
							});
						}
					}
				})
			}
			/*****
			 * 根据选中的组织节点ID刷新已分配员工列表
			 */
			function refreshPLstBySelOid(selOid){
				store.baseParams.orgId = selOid;
				store.load({
					params:{
						start:0,
						limit:20
					}
				})
				
			}
			
			/*********
			 * 将员工从当前选中组织移除
			 * @param {Object} personIds 员工ID，可用逗号隔开
			 */
			function removePersonFromOrg(personIds){
				var oid = getSelOrgId();
				Ext.Msg.wait("正在移除...","请稍后")
				Ext.Ajax.request({
					url:"org/remove.do",
					params:{
						orgId:oid,
						personIds:personIds
					},
					success:function(response,request){
						Ext.Msg.hide()
						var d = Ext.decode(response.responseText)
						if(d.message!=""){
							showErrorMsg(d.message)
						}else{
							refreshPLstBySelOid(oid);
							freeStore.load({
								params:{
									start:0,
									limit:20
								}
							});
						}
					}
				})
			}
			var freeMembersWindow = new Ext.Window({
				modal:true,//模态化
                shim:false,
                animCollapse:false,
                constrainHeader:true,
				layout:"border",
				width:700,
			    frame: true,  
				closeAction:"hide",
				height:350,
				listeners:{
					hide:function(){
					}
				},
				title:'待分配员工列表',
				items:[freeMembersPanel],
			})
			
			var sm = new Ext.grid.CheckboxSelectionModel()
			var cm = new Ext.grid.ColumnModel([
			new Ext.grid.RowNumberer(),
			sm,
			{header:"编号",dataIndex:"id",width:60},
			{header:"姓名",dataIndex:"name"},
			{header:"手机",dataIndex:"mobilePhone"},
			{header:"邮箱",dataIndex:"email",width:150},
			{header:"性别",dataIndex:"gender",width:80},
			{header:"操作",dataIndex:"operation",xtype:"uxactioncolumn",
			items:[
				{
					text:"移除",
					iconCls:"icon-remove",
					tooltip:"移除",
					handler:function(grid,rowIndex,columnIndex){	
						Ext.Msg.confirm("提示","是否确定移除该员工?",function(b){
							if(b=="yes"){
								var record = store.getAt(rowIndex)
								removePersonFromOrg(record.data.id);
							}
						})
					}
				}
			]}
			]);
			
			var webPagePanel = new Ext.grid.GridPanel({
				cm:cm,
				sm:sm,
				loadMask:true,
				stripeRows:true,
				store:store,
				viewConfig:{
					forceFit:true,
					enableRowBody:true
				},
				region:'center',
				tbar:[{
					text:'分配新员工',
					iconCls:"icon-add",
					handler:function(){
						var node = tree.getSelectionModel().getSelectedNode()
						if(node==null){
							showWarningMsg("请选择一个组织");
							return;
						}
						freeMembersWindow.show()
					}
				},{
					text:'移除',
					iconCls:"icon-remove",
					handler:function(){
						var records = sm.getSelections()
						if(records.length==0){
							showWarningMsg("请选择要移除的员工！")
							return;
						}
						var ids = []
						Ext.each(records,function(record){
							ids.push(record.data.id)
						})
						Ext.Msg.confirm("提示","是否确定移除所选员工?",function(b){
							if(b=="yes"){
								removePersonFromOrg(ids);
							}
						})
					}
				}],
				bbar:new Ext.PagingToolbar({
        			pageSize : 20,
					store : store,
					beforePageText : "第",
					afterPageText : "页，共{0}页",
					lastText : "尾页",
					nextText : "下一页",
					prevText : "上一页",
					firstText : "首页",
					refreshText : "刷新",
					displayInfo : true,
					displayMsg : '一共 {2}条成员信息',
					emptyMsg : "没有未分配组织的成员信息"
				})
			})
			win = desktop.createWindow({
				id:'ORGM-win',
              	listeners:{
				},
                title:'组织管理',
                height:500,                
				items:[tree,webPagePanel],
                iconCls: 'icon-wxwp',
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'border'
            });
        }
        win.show();
    }
});