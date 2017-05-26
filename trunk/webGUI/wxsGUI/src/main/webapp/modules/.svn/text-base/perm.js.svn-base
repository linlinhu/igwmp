/*
 * USM window
 */
Emin.PERMWindow = Ext.extend(Ext.app.Module, {
    id:'PERM-win',
    init : function(){
        this.launcher = {
            text: '权限管理',
            iconCls:'icon-perm',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('PERM-win');
		if(!win){
			var wg = new Ext.WindowGroup()
			wg.zseed = 7500
//			var store = new Ext.data.JsonStore({
//				url:"permission/orgOperations.do"
//			})
//			
			var operStore = new Ext.data.JsonStore({
				url:"permission/all.do",
				fields:["id","name","remark","menuId","menuName"]
			})
			operStore.load();
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
					}
				},
				autoScroll:true,
				region:"west",
				width:240
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
			 * 给组织分配权限
			 * @param {Object} objectIds 权限编号
			 */
			function permissionToOrg(objectIds){
				var oid = getSelOrgId();
				Ext.Msg.wait("正在分配权限...","请稍后")
				Ext.Ajax.request({
					url:"permission/save.do",
					params:{
						orgId:oid,
						operationIds:objectIds
					},
					success:function(response,request){
						Ext.Msg.hide()
						var d = Ext.decode(response.responseText)
						if(d.message!=""){
							showErrorMsg(d.message)
						}else{
							messageWindow({message:"权限修改成功"})
						}
					}
				})
			}
			/*****
			 * 根据组织编号刷新权限
			 */
			function refreshPLstBySelOid(selOid){
				Ext.Ajax.request({
					url:"permission/orgOperations.do",
					params:{
						orgId:selOid
					},
					success:function(response,request){
						var data = Ext.decode(response.responseText)
						if(data && data.length>0){
							var indexs = []
							for(var i=0;i<data.length;i++){
								//遍历数据store对比id，获取数据行对应下表
								var index = operStore.findBy(function(record,id){
									if(record.data.id==data[i]){
										indexs.push(operStore.indexOf(record))
									}
								})
							}
							webPagePanel.getSelectionModel().selectRows(indexs)
						}
						
					}
				})	
				
			}
			
		
			
			
			var sm = new Ext.grid.CheckboxSelectionModel()
			var cm = new Ext.grid.ColumnModel([
				new Ext.grid.RowNumberer(),
				sm,
				{header:"编号",dataIndex:"id",width:60},
				{header:"名称",dataIndex:"name"},
				{header:"备注",dataIndex:"mobilePhone"},
				{header:"菜单编号",dataIndex:"email",width:150},
				{header:"菜单名",dataIndex:"gender",width:80},
			]);
			var webPagePanel = new Ext.grid.GridPanel({
				cm:cm,
				sm:sm,
				loadMask:true,
				stripeRows:true,
				store:operStore,
				viewConfig:{
					forceFit:true,
					enableRowBody:true
				},
				region:'center',
				tbar:[{
					text:'保存',
					iconCls:"icon-add",
					handler:function(){
						var records = sm.getSelections()
						if(records.length==0){
							
						}
						var ids = []
						Ext.each(records,function(record){
							ids.push(record.data.id)
						})
						permissionToOrg(ids);
					}
				}],
			})
			win = desktop.createWindow({
				id:'PERM-win',
              	listeners:{
				},
                title:'权限管理',
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