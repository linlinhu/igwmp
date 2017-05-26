/*
 * USM window
 */
Emin.WXMENUWindow = Ext.extend(Ext.app.Module, {
    id:'WXMENU-win',
    init : function(){
        this.launcher = {
            text: '自定义菜单配置',
            iconCls:'icon-wxmenu',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
		var codes = "${codes}";
        var desktop = this.app.getDesktop();
        var wg = new Ext.WindowGroup()
        wg.zseed = 7500
        var win = desktop.getWindow('WXMENU-win');
		if(!win){
			
        var pageOfficialAccountId;
			 //定义树的加载器 
        var treeloader = new Ext.ux.tree.TreeGridLoader({ 
            dataUrl : "wxmenu/tree.do",
            listeners:{
            	beforeload:function(loadder,node){            		
            		treeloader.baseParams.pid = node.attributes.id
            	}
            }
        }); 
        var checkAdd = function(n) {
        //Ext.Msg.alert('Message', Ext.util.Format.htmlEncode(n.city).replace(/ /g, "&nbsp;") + '<br>' + n.nodePath + '<br>' + n.nodeParentPath + '<br>' + n.nodeParentId);
	        var exist = treegrideditor.hasNode(n.pid, {
	            name: n.name
	        });
	        if (exist) {
	        	showWarningMsg("菜单已存在")
	           
	            return false;
	        }
	        return true;
	    };
	    
	    var checkEdit = function(n) {
	        //Ext.Msg.alert('Message', Ext.util.Format.htmlEncode(n.city).replace(/ /g, "&nbsp;") + '<br>' + n.nodePath + '<br>' + n.nodeParentPath + '<br>' + n.nodeParentId);
	        var exist = treegrideditor.hasNode(n.pid, {
	            name: n.name
	        }, false, false, {
	            id: n.id
	        }, false);
	        if (exist) {
	            showWarningMsg("菜单已存在")
	            return false;
	        }
	        return true;
	    };
	    
	    var checkRemove = function(n) {
	    	console.log(n)
	        //Ext.Msg.alert('Message', Ext.util.Format.htmlEncode(n.city).replace(/ /g, "&nbsp;") + '<br>' + n.nodePath + '<br>' + n.nodeParentPath + '<br>' + n.nodeParentId);
	        if (!n.leaf && n.pid==0) {
	           showWarningMsg("该菜单下存在子菜单")
	            return false;
	        }
	        return true;
	    };
        //定义treegrid 
      	var webPageStore = new Ext.data.JsonStore({
				url:"",
				totalProperty:"total",
				root:"rows",
				fields:["id","category","title"]
			})
		
		var wcm = new Ext.grid.ColumnModel([
			new Ext.grid.RowNumberer(),
		
			{header:"类别",dataIndex:"categoryName",width:100,renderer:function(v,m,r,row,c,s){
				return r.data.category.name
			}},
			{header:"标题",dataIndex:"title"}			
		]);
		var webPagePanel = new Ext.grid.GridPanel({
			cm:wcm,
			sm:new Ext.grid.RowSelectionModel(),
			loadMask:true,
			stripeRows:true,
			store:webPageStore,
			listeners:{
				rowdblclick:function(g,i,e){
					var record = webPageStore.getAt(i)
					var url = ""+record.data.id
					menuForm.getForm().findField("activekey").setValue(url)
					webPageWin.hide()
				}
			},
			viewConfig:{
				forceFit:true,
				enableRowBody:true
			},
			region:'center',			
			bbar:new Ext.PagingToolbar({
    			pageSize : 20,
				store : webPageStore,
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
		var webPageWin = new Ext.Window({
			title:"文章列表",
			closeAction:"hide",
			items:[webPagePanel],
			modal:true,
			manager:wg,
			layout:"border",
			listeners:{
				show:function(){
					webPageStore.load({
						params:{
							start:0,
							limit:20
						}
					})
				}
			},
			width:400,
			height:300
		})
        var menuFieldSet = new Ext.form.FieldSet({
        	labelWidth:100,
        	labelAlign:"right",
        	layout:"form",
        	title:'菜单信息',
        	region:'center',
        	defaults:{
        		anchor:"90%"
        	},
        	items:[{xtype:"hidden",name:"id",value:"0"},
        	{xtype:"hidden",name:"pid",value:"0"},{
        		xtype:'textfield',
        		fieldLabel:"名称",
        		name:"name",
        		allowBlank:false,
        		blankText:'请填写名字'
        	},{
        		xtype:"radiogroup",
        		fieldLabel:"类型",
        		name:"activetype",
        		allowBlank:false,
        		listeners:{
        			change:function(){
        				
        				if(this.getValue()=="view"){
        					menuForm.getForm().findField("activekey").trigger.setDisplayed(true)
        				}else{
        					menuForm.getForm().findField("activekey").trigger.setDisplayed(false)
        				}
        			}
        		},
        		items:[{
        			name:"activetype",
        			boxLabel:"一般链接",
        			inputValue:"view",
        			checked:true
        		},{
        			name:"activetype",
        			boxLabel:"授权链接",
        			inputValue:"snsapi_base"        			
        		},{
        			name:"activetype",
        			boxLabel:"用户信息链接",
        			inputValue:"snsapi_userinfo"        			
        		},{
        			name:"activetype",
        			boxLabel:"按钮",
        			inputValue:"click"        			
        		}]
        	},{
//        		xtype:'trigger',
        		xtype:'textfield',
        		fieldLabel:"事件代码/链接",        		
        		name:"activekey",
//        		onTriggerClick:function(){
//        			webPageWin.show()
//        		},
        		allowBlank:false,
        		blankText:'请填写事件代码/链接'
        	},{        		
        		xtype:'numberfield',
        		fieldLabel:"顺序",        		
        		name:"sort",
        		allowDecimals:false,
        		allowBlank:false,
        		blankText:'请填写顺序'        	
        	}
        	]
        })
        var pageOfficialAccountId;
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
						treeloader.baseParams.woaId = pageOfficialAccountId;
						treeGrid.getRootNode().reload();
            			treeGrid.expandAll()
					},
					afterRender : function(combo) {
//						var oa_store_reader = OfficialAccountsStore.reader;
//						console.info(oa_store_reader);
//						var frow = (OfficialAccountsStore.reader.jsonData)[0];
//						pageOfficialAccountId = frow.id;
//						treeloader.baseParams.woaId = pageOfficialAccountId;
//						treeGrid.getRootNode().reload();
//          			treeGrid.expandAll()
//				        var firstValue = frow.companyName;
//				        combo.setValue(firstValue);//同时下拉框会将与name为firstValue值对应的 text显示
				    }
				}
			});
        var menuForm = new Ext.form.FormPanel({
        	frame:true,
        	layout:"border",
        	region:'center',
        	items:[menuFieldSet]        	
        })
        var menuWindow = new Ext.Window({
        	manager:wg,
        	closeAction:"hide",
        	items:[menuForm],
        	layout:"border",
        	width:660,
        	height:240,
        	listeners:{
        		hide:function(){
        			menuForm.getForm().reset()
        		}
        	},
        	buttonAlign:"center",
        	buttons:[{
        		text:"保存",
        		handler:function(){
        			if(menuForm.getForm().isValid()){
        				menuForm.getForm().submit({
        					url:"wxmenu/saveOrUpdate.do",
        					params:{woaId:pageOfficialAccountId},
        					waitMsg:'正在保存菜单信息...',
        					success:function(form,action){
        						menuWindow.hide()
        						treeGrid.getRootNode().reload()
        						treeGrid.expandAll()
        					},
        					failure:function(form,action){
        						showErrorMsg(action.result.message)
        					}
        				})
        			}
        		}
        	}]
        })
        var treeGrid = new Ext.ux.tree.TreeGridEditor({ 
            title : '微信公众号自定义菜单列表',                //表头名称，去掉则不显示表头
           	region:"center",    
            tbar:[{
            	text:"添加菜单",
	            disabled:codes.indexOf("add")==-1,
            	handler:function(){            	     		
            		if(officialAccountCbx.isValid()){
            			menuWindow.show();	
            		}
            		
            	}
            },{
            	text:"生成公众号自定义菜单",
	            disabled:codes.indexOf("generate")==-1,
            	handler:function(){ 
            		if(!officialAccountCbx.isValid()){
            			return false;
            		}
            		Ext.Msg.wait("正在生成菜单...","请稍候");
            		Ext.Ajax.request({
            			url:"wxmenu/generate.do",
            			params:{woaId:pageOfficialAccountId},
            			success:function(response,request){
            				Ext.Msg.hide()
            				var d = Ext.decode(response.responseText)
            				if(d.message!=""){
            					showErrorMsg(d.message)
            				}else{
            					showMsg('微信公众号自定义菜单已生成,请前往查看!若未更新请重新关注')
            					treeGrid.getRootNode().reload()
            					treeGrid.expandAll()
            				}
            			}
            		})
            	}
            },'->',officialAccountCbx],
            singleEdit: true,
            maxDepth: 2,//最大层级深度          
            animate : true,  
            mouseoverShowObar: false,
            enableHdMenu: false,   
    		columnResize: true,
    		useArrows:true,
            enableDD : true, 
            rootVisible : false, 
            forceFit:false,
            containerScroll : true, 
            enableSort:false,           //默认为true，控件根据列的内容排序；置为false，则按照数据顺序显示
            columns : [ { 
                header : '菜单名称', 
                autoWidth:true,
                displayTip: true,
                dataIndex : 'name',
                tpl: new Ext.XTemplate('{name}'),
                inputCfg: {
                allowBlank: false,
                trimmed: true,
                blankText:'请输入菜单名称'
                }
                
            },{ 
                header : '菜单类型', 
                dataIndex : 'activetype',
                autoWidth: true,
                align : 'center',
                disableEdit:true,
                tpl: new Ext.XTemplate('{[this.btntypeText(values)]}', {
	                btntypeText: function(v) {
	                
	                    if(v.activetype=="view"){
	                		return "一般链接"
	                	}else if(v.activetype=="click"){
	                		return "按钮"
	                	}else if(v.activetype=="snsapi_base"){
	                		return "授权链接"
	                	}else if(v.activetype=="snsapi_userinfo"){
	                		return "用户信息链接"
	                	}
	                	return "菜单"
	                }
           		})
           		
                
            },{ 
                header : '事件代码/链接', 
                dataIndex : 'activekey',  
                autoWidth: true,
                align : 'center',
                tpl: new Ext.XTemplate('{activekey:this.activekeyText}',{
                	activekeyText:function(v) {
	                return v
	                }
                }),
                inputCfg:{
                	allowBlank: false,
                	trimmed: true
                }
            },{ 
                header : '是否已生成', 
                dataIndex : 'audit',
                disableEdit:true,
                autoWidth: true,
                tpl: new Ext.XTemplate('{audit:this.auditText}', {
	                auditText: function(v) {
	                	
	                    if(v==true){
	                		return "是"
	                	}
	                	return "否"
	                }
           		})
               
            },{ 
                header : '顺序', 
                dataIndex : 'sort',
                disableEdit:true,
                autoWidth: true,
                tpl: new Ext.XTemplate('{sort}')
               
            }], 
            rootNodeId: '0',
            addNode:function(node){
            	menuForm.getForm().findField("pid").setValue(node.id)
            	menuWindow.show()
            },
            editNode:function(node){
            	var n = this.getNodeById(node.id)
            	menuWindow.show()
            	menuForm.getForm().loadRecord(new Ext.data.Record(n.attributes))
            	if(n.childNodes.length!=0){
            		menuForm.getForm().findField("activekey").disable()
            		menuForm.getForm().findField("activetype").disable()
            		menuForm.getForm().findField("activekey").clearInvalid()
            	}else{
            		menuForm.getForm().findField("activekey").enable()
            		menuForm.getForm().findField("activetype").enable()
            	}
            	menuForm.doLayout()
            	
            	
            },
            removeNode:function(node){
            	var n = this.getNodeById(node.id)
            	if(n.childNodes.length!=0){
            		showWarningMsg("该菜单下有子菜单")
            		return
            	}
            	Ext.Msg.confirm("提示","是否确定删除此菜单?",function(b){
            		if(b=="yes"){
            			Ext.Msg.wait("正在执行操作...","请稍后");
            			Ext.Ajax.request({
		            		url:"wxmenu/delete.do",
		            		params:{
		            			id:n.attributes.id
		            		},
		            		success:function(response,request){
		            			Ext.Msg.hide()
		            			var d = Ext.decode(response.responseText)
		            			if(d.message!=""){
		            				showErrorMsg(d.message)
		            			}else{
		            				treeGrid.getRootNode().reload()
            						treeGrid.expandAll()
		            			}
		            		}
		            	})
            		}
            	})
            	
            },
            //dataUrl : 'treegrid-data.json',
            loader : treeloader ,
            //绑定加载器 
            // 设置Obar
        	obarCfg: {
	            column: {
	                header: '操作',
	                dataIndex: 'id',
	                autoWidth: true
	         	},
	            btns: [{
	                id: 'add',
		            disabled:codes.indexOf("add")==-1,
	                // 最大层级按钮状态，取值范围：'normal','hidden','disabled','uncreated'
	                deepestState: 'uncreated'
	               
	            }, {
	                id: 'edit',
		            disabled:codes.indexOf("edit")==-1,
	                // 最大层级按钮状态，取值范围：'normal','hidden','disabled','uncreated'
	                deepestState: 'normal'	             
	                
	               
	            }, {
	                id: 'remove',
		            disabled:codes.indexOf("delete")==-1,
	                request: {
	                   
	                    
	                },
	                // 删除节点校验函数
	                validator: checkRemove
	            }],
            
            // Obar事件
            //
            //   beforeaddnode  添加树节点之前，如果返回false，则中止添加操作
            //     'beforeaddnode': function(tree, node)
            //         tree : Ext.ux.tree.TreeGridEditor
            //         node : Ext.tree.TreeNode  当前树节点
            //         return Boolean
            //
            //   addnode  添加树节点
            //     'addnode': function(tree, parent, node)
            //         tree : Ext.ux.tree.TreeGridEditor
            //         parent : Ext.tree.TreeNode  父树节点
            //         node : Ext.tree.TreeNode  新增树节点
            //         return void
            //
            //   beforeeditnode  修改树节点之前，如果返回false，则中止修改操作
            //     'beforeeditnode': function(tree, node)
            //         tree : Ext.ux.tree.TreeGridEditor
            //         node : Ext.tree.TreeNode  当前树节点
            //         return Boolean
            //
            //   editnode  修改树节点
            //     'editnode': function(tree, node)
            //         tree : Ext.ux.tree.TreeGridEditor
            //         node : Ext.tree.TreeNode  当前树节点
            //         return void
            //
            //   beforeremovenode  删除树节点之前，如果返回false，则中止删除操作
            //     'beforeremovenode': function(tree, node)
            //         tree : Ext.ux.tree.TreeGridEditor
            //         node : Ext.tree.TreeNode  当前树节点
            //         return Boolean
            //
            //   removenode  删除树节点，如果返回false，则中止删除操作
            //     'beforeremovenode': function(tree, node, response)
            //         tree : Ext.ux.tree.TreeGridEditor
            //         node : Ext.tree.TreeNode  当前树节点
            //         response : String/Object  服务端响应消息
            //         return Boolean
            //
            //   afterremovenode  删除树节点之后
            //     'afterremovenode': function(tree, nodeAttributes)
            //         tree : Ext.ux.tree.TreeGridEditor
            //         nodeAttributes : Object  当前树节点的属性
            //         return void
            //
            //   beforeupdatenode  保存树节点之前，如果返回false，则中止保存操作
            //     'beforeupdatenode': function(tree, node, editMode)
            //         tree : Ext.ux.tree.TreeGridEditor
            //         node : Ext.tree.TreeNode  当前树节点
            //         editMode : String  编辑模式，'add'新增下级节点，'edit'编辑当前节点
            //         return Boolean
            //
            //   updatenode  保存树节点，如果返回false，则中止保持操作
            //     'updatenode': function(tree, node, editMode, response)
            //         tree : Ext.ux.tree.TreeGridEditor
            //         node : Ext.tree.TreeNode  当前树节点
            //         editMode : String  编辑模式，'add'新增下级节点，'edit'编辑当前节点
            //         response : String/Object  服务端响应消息
            //         return Boolean
            //
            //   afterupdatenode  保存树节点之后
            //     'afterupdatenode': function(tree, node, editMode)
            //         tree : Ext.ux.tree.TreeGridEditor
            //         node : Ext.tree.TreeNode  当前树节点
            //         editMode : String  编辑模式，'add'新增下级节点，'edit'编辑当前节点
            //         return void
            //
            //   beforecanceledit 取消修改树节点之前，如果返回false，则中止取消操作
            //     'beforecanceledit': function(tree, node)
            //         tree : Ext.ux.tree.TreeGridEditor
            //         node : Ext.tree.TreeNode  当前树节点
            //         return Boolean
            //
            //   canceledit  取消修改树节点
            //     'canceledit': function(tree, node)
            //         tree : Ext.ux.tree.TreeGridEditor
            //         node : Ext.tree.TreeNode  当前树节点
            //         return void
            //
            //   requestfailure  Ajax请求失败
            //     'requestfailure': function(tree, node, fn, status)
            //         tree : Ext.ux.tree.TreeGridEditor
            //         node : Ext.tree.TreeNode  当前树节点
            //         fn : String  失败状态
            //         status : String  失败状态
            //         return void
            //
            listeners: {
                
            }
        }
        }); 
        treeGrid.expandAll();
		win = desktop.createWindow({
				id:'WXMENU-win',
              	listeners:{
              		close:function(){
              			menuWindow.close()
              		}
				},
                title:'自定义菜单配置',
                height:500,
                html:"",
				items:[treeGrid],
                iconCls: 'icon-wxmenu',
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit'
            });
        }
        win.show();
    }
});