/*
 * USM window
 */
Emin.WXWPWindow = Ext.extend(Ext.app.Module, {
    id:'WXWP-win',
    init : function(){
        this.launcher = {
            text: '微文章',
            iconCls:'icon-wxer',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
    	var codes = "${codes}";
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('WXWP-win');
		if(!win){
			var pageOfficialAccountId=0;
	        var OfficialAccountsStore = new Ext.data.JsonStore({
			    id:'id',
			    url: 'woa/validList.do',
			    fields: ['id', 'companyName']
			}) ;
			OfficialAccountsStore.load();
			var officialAccountCbx = new Ext.form.ComboBox({
			 	fieldLabel:'企业公众号',
			    store: OfficialAccountsStore ,
			    displayField:'companyName',
			    valueField:'id',
			    mode: 'local',
			    emptyText:'请选择...',
	            allowBlank : false,
			    typeAhead: true,
			    triggerAction: 'all',
			    selectOnFocus:true,
	            editable: false,
				hiddenName:"id",
				listeners:{
					select:function(e){
						pageOfficialAccountId = e.value;
						tree.getRootNode().reload();
						tree.expandAll();
						store.baseParams.woaId = pageOfficialAccountId;
						store.reload();
					},
					afterRender : function(combo) {
				    }
				}
			});
			var wg = new Ext.WindowGroup()
			wg.zseed = 7500
			var store = new Ext.data.JsonStore({
				url:"webPage/webPages.do",
				method:"get",
				totalProperty:"total",
				root:"rows",
				fields:["id","category","title","auther","comments","info","createTime"]
			})
			var treeLoader = new Ext.tree.TreeLoader({
				dataUrl:"webPage/categoryTree.do",
				requestMethod :"POST",
				listeners:{
					beforeload:function(loader,node){
						treeLoader.baseParams.pid = node.attributes.id
						treeLoader.baseParams.woaId= pageOfficialAccountId;
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
									url:"webPage/saveCatrgory.do",
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
									url:"webPage/deleteCategory.do",
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
						store.baseParams.categoryId = node.attributes.id
						store.load({
							params:{
								start:0,
								limit:20
							}
						})
					},
					contextmenu:function(node,e){
						e.preventDefault();
						category = node
						contextMenu.showAt(e.getXY())
					}
				},
				tbar:[{
					text:'添加类别',
					handler:function(){
						if(!officialAccountCbx.isValid()) return false;
						var pnode = tree.getSelectionModel().getSelectedNode()
						var pid = 0
						if(pnode!=null){
							pid = pnode.attributes.id
						}
						
						Ext.Msg.prompt("提示","请输入类别名称",function(b,v){
							if(b=="ok" && v!=""){
								Ext.Msg.wait("正在保存类别...","请稍后")
								Ext.Ajax.request({
									url:"webPage/saveCatrgory.do",
									params:{
										woaId:pageOfficialAccountId,
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
			var webPageForm = new Ext.form.FormPanel({
				layout:"form",
				labelAlign:"right",
				region:'center',
				frame:true,
				labelWidth:85,
				defaults:{
					anchor:"90%"
				},
				items:[{xtype:"hidden",name:"id"},{xtype:"hidden",name:"cid"},{
					xtype:"textfield",
					fieldLabel:"标题",
					name:"title",
					enableKeyEvents:true,
					listeners:{
						keyup:function(){
							var ifr = document.getElementById("prev_container")
							var win = ifr.window || ifr.contentWindow;
							if(this.getValue()==""){
								win.syncTitle("标题"); // 调用iframe中的a函数
							}else{
								win.syncTitle(this.getValue()); // 调用iframe中的a函数
							}
							
						}
					},
					allowBlank:false,
					blankText:"请填写标题"
				},{
					xtype:"textfield",
					fieldLabel:"作者",
					name:"auther",
					allowBlank:false,
					blankText:"请填写作者",
					enableKeyEvents:true,
					listeners:{
						keyup:function(){
							var ifr = document.getElementById("prev_container")
							var win = ifr.window || ifr.contentWindow;
							if(this.getValue()==""){
								win.syncAuther("作者"); // 调用iframe中的a函数
							}else{
								win.syncAuther(this.getValue()); // 调用iframe中的a函数
							}
							
						}
					}
				},{
					xtype:"textarea",
					fieldLabel:"摘要",
					name:"comments",
					height:80,
					enableKeyEvents:true,
					listeners:{
						keyup:function(){
							var ifr = document.getElementById("prev_container")
							var win = ifr.window || ifr.contentWindow;
						
							win.syncComments(this.getValue().replaceAll("\n","<br/>")); // 调用iframe中的a函数
							
							
						}
					}
				},{
					xtype:"StarHtmleditor",
					fieldLabel:'内容',
					allowBlank:false,
					height:400,
					name:"info",
					blankText:"请填写内容",									
					listeners:{
						sync:function(){
							var d = this.getDoc();
							var c = d.body.innerHTML ;
							var ifr = document.getElementById("prev_container")
							var win = ifr.window || ifr.contentWindow;
							
							win.syncInfo(c); // 调用iframe中的a函数
							
							//alert(this.getValue())
						}
					}
				}]
				
			})
			

			var previewPanel = new Ext.Panel({
				region:"east",
				width:300,
				frame:true,
				html:'<div class="map-container-phone"><div class="map-container-phone-inner">'+
				'<div id="overiframe" unselectable="on" style=" background:url(about:_blank);"></div>'+
				'<iframe id="prev_container" class="clearfix" style="height:348px;width:100%;overflow:auto" src="webpage/preview.html" name="container" unselectable="on" scrolling="auto">'+				
				'</div>'+
				'</div>'
			})
			var webPageWindow = new Ext.Window({
				manager:wg,
				modal:true,
				layout:"border",
				width:950,
				closeAction:"hide",
				height:650,
				listeners:{
					hide:function(){
						webPageForm.getForm().reset()
						document.getElementById("prev_container").src = document.getElementById("prev_container").src
					}
				},
				title:'微文章信息',
				items:[webPageForm,previewPanel],
				buttonAlign:"center",
				buttons:[{
					text:"保存",
					handler:function(){
						if(webPageForm.getForm().isValid()){
							var d = webPageForm.getForm().getValues(false);
							d.info = webPageForm.getForm().findField("info").getDoc().body.innerHTML
							d.categoryId = d.cid || tree.getSelectionModel().getSelectedNode().attributes.id
							Ext.Msg.wait("正在保存文章...","请稍后")
							Ext.Ajax.request({
								url:"webPage/saveWebPage.do",
								params:d,
								method:"post",
								success:function(response,request){
									Ext.Msg.hide()
									var d = Ext.decode(response.responseText)
									if(d.message!=""){
										showErrorMsg(d.message)
									}else{
										store.reload()
										webPageWindow.hide()
									}
								}
							})
						}
					}
				}]
			})
			var sm = new Ext.grid.CheckboxSelectionModel()
			var cm = new Ext.grid.ColumnModel([
			new Ext.grid.RowNumberer(),
			sm,
			{header:"类别",dataIndex:"categoryName",width:100,renderer:function(v,m,r,row,c,s){
				return r.data.category.name
			}},
			{header:"标题",dataIndex:"title"},
			{header:"操作",dataIndex:"operation",xtype:"uxactioncolumn",items:[
			{text:"编辑",iconCls:"icon-edit",tooltip:"编辑文章",handler:function(grid,rowIndex,columnIndex){
				var record = store.getAt(rowIndex)
				record.data.cid = record.data.category.id
				
				
				webPageWindow.show()
				webPageForm.getForm().loadRecord(record)
				var d = webPageForm.getForm().findField("info").getDoc();
				var c = d.body.innerHTML ;
				var ifr = document.getElementById("prev_container")
				ifr.src = ifr.src
				var win = ifr.window || ifr.contentWindow;
				setTimeout(function(){
					win.syncInfo(record.data.info); // 调用iframe中的a函数
					win.syncComments(record.data.comments);
					win.syncTitle(record.data.title);
					win.syncAuther(record.data.auther);
					
				},1000)
				
				
				
				
			}}
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
					text:'新建',
					disabled:codes.indexOf("add")==-1,
					iconCls:"icon-add",
					handler:function(){
						if(!officialAccountCbx.isValid()) return false;
						var node = tree.getSelectionModel().getSelectedNode()
						if(node==null){
							showWarningMsg("请选择类别");
							return;
						}
						webPageWindow.show()
					}
				},{
					text:'删除',
					iconCls:"icon-remove",
					handler:function(){
						var records = sm.getSelections()
						if(records.length==0){
							showWarningMsg("请选择要删除的文章")
							return;
						}
						var ids = []
						Ext.each(records,function(record){
							ids.push(record.data.id)
						})
						Ext.Msg.confirm("提示","是否确定删除所选文章?",function(b){
							if(b=="yes"){
								Ext.Msg.wait("正在执行操作...","请稍后")
								Ext.Ajax.request({
									url:"webPage/deleteWebPages.do",
									params:{
										ids:ids.join(",")
									},
									success:function(response,request){
										Ext.Msg.hide()
										var d = Ext.decode(response.responseText)
										if(d.message!=""){
											showErrorMsg(d.message)
										}else{
											store.reload()
										}
									}
								})
							}
						})
					}
				},"->",officialAccountCbx],
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
					displayMsg : '一共 {2}条文章信息',
					emptyMsg : "没有文章信息"
				})
			})
			win = desktop.createWindow({
				id:'WXWP-win',
              	listeners:{
				},
                title:'微文章',
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