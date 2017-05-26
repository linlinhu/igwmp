/*
 * USM window
 */
Emin.WXARTICALWindow = Ext.extend(Ext.app.Module, {
    id:'WXARTICAL-win',
    init : function(){
        this.launcher = {
            text: '图文库',
            iconCls:'icon-wxartical',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
		var codes = "${codes}";
        var desktop = this.app.getDesktop();
        var wg = new Ext.WindowGroup()
        wg.zseed = 7500
        var win = desktop.getWindow('WXARTICAL-win');
		if(!win){
			
			//图片数据源
			var imgStore = new Ext.data.JsonStore({
				url:"wxfile/list.do",
				method:"get",
				fields:["url","id"]
			})
			imgStore.load()
			//图片显示模板
			var imgTpl = new Ext.XTemplate(
			  '<tpl for=".">',  
              '<div class="thumb-wrap" id="img_{id}" style="float:left;margin:10px">',  
		      '<div class="thumb"><img src="{url}"></div>', 
		      '<span class="x-editable" style="text-align:center"><a href="javascript:;" name="select">使用</a>&nbsp;&nbsp;&nbsp;<a href="javascript:;" name="remove">删除</a></span>',
		      '</div>',
		      '</tpl>'
			)
			var imgView = new Ext.DataView({
        		tpl:imgTpl,
        		store:imgStore,
        		autoHeight:true,
				singleSelect:true,
				loadingText:"加载中...",
				overClass:'x-view-over',
				itemSelector:'div.thumb-wrap',
				emptyText:'没有图片',
				listeners:{
					click:function(d,i,n,e){
						var record = imgStore.getAt(i);
						if(e.target.name=="remove"){
							Ext.Msg.confirm("提示","是否删除此图片？",function(b){
								if(b=="yes"){
									Ext.Ajax.request({//wxfile/delete.do
										url:"wxfile/delete.do",
										params:{id:record.data.id},
										success:function(response,request){
											var d = Ext.decode(response.responseText)
											if(d.message!=""){
												showErrorMsg(d.message)
											}else{
												imgStore.reload()
											}
										}
									})
								}
							})
						}else if(e.target.name=="select"){
							articalForm.getForm().findField("picUrl").setValue(record.data.url)
							imgWindow.hide()
						}
					}
				}
        	})
			var imgWindow = new Ext.Window({
				manager:wg,
				tbar:[{
					text:'上传',
					handler:function(){
						new Ext.Window({
							width : 650,
							title : '文件上传',
							height : 300,
							modal:true,
							listeners:{
								close:function(){
									imgStore.reload()											
								}
							},
															
							layout : 'fit',
							items : [
								{
							        xtype:'uploadpanel',
									border : false,
									id:"upfilePanel",
									fileSize : "1 MB",//限制文件大小
									filePostName:"file",
									uploadUrl : basePath+'/wxfile/upload.do',
									flashUrl : 'js/uploadPanel/swfupload.swf',
									filePostName : 'file', //后台接收参数
									fileTypes : '*.jpg',
									postParams:{
										woaId:pageOfficialAccountId
									},
									fileTypesDescription:"所有JPG图片(最大1MB)"//可上传文件类型
								}
							]
						}).show(this.el);
					}
				}],
				modal:true,
				width:650,
				height:400,
				items:[imgView],
				autoScroll:true,
				closeAction:"hide"
			})
			//图文库数据源
			var store = new Ext.data.JsonStore({
				url:"article/page.do",
				totalProperty:"total",
				root:"rows",
				fields:["id","title","description","picUrl","url","size"]
			})
			store.load({
				params:{
					start:0,
					limit:20
				}
			})
			var editDisplay = codes.indexOf("edit")==-1?"display:none":"";
			var deleteDisplay = codes.indexOf("delete")==-1?"display:none":"";
			//图文库模板
        	var tpl = new Ext.XTemplate(
        	  '<tpl for=".">',        	  
        	  '<tpl if="size==1">',
              '<div class="thumb-wrap" id="artical_{id}" style="float:left;margin:10px;width:270px;position:relative">',              
		      '<div class="thumb" style="position:relative"><img src="{picUrl}" style="width:100%;height:150px">',
		      '<div class="x-editable" style="width:100%;text-align:left;postion:absolute;bottom:0;left:0;opacity:0.5;background-color:black;color:white;font-size:14px;min-height:30px;max-height:60px;overflow:hidden">{title}</div>',	     
		      '</div>',
		      '<div style="width:270px;text-align:center;">',
              '<a href="javascript:;" name="aritcal_edit" style="'+editDisplay+'"><img src="js/images/types/icons/edit.gif"   align="absmiddle" style="width:16px;height:16px"/>&nbsp;编辑</a>',
              '&nbsp;&nbsp;&nbsp;',
              '<a href="javascript:;"  name="aritcal_remove" style="'+deleteDisplay+'"><img src="js/images/types/icons/cancel.png"  align="absmiddle" style="width:16px;height:16px"/>&nbsp;删除</a>',
              '</div>',
		      '</div>',
		     
		      '</tpl>',
		      '<tpl if="size==2">',
		      '<div class="thumb-wrap" id="artical_{id}" style="float:left;margin:10px;width:270px;">', 		      
		      
		      '<div class="thumb" style="background-color:white;clear:both;height:62px">',
		      '<div class="x-editable" style="text-align:left;float:left;font-size:14px;line-height:60px;">{title}</div>',
		      '<img src="{picUrl}" style="width:60px;height:60px;float:right;"></div>', 
		      '<div style="width:270px;text-align:center;float:left">',
              '<a href="javascript:;" name="aritcal_edit" style="'+editDisplay+'"><img src="js/images/types/icons/edit.gif"   align="absmiddle" style="width:16px;height:16px"/>&nbsp;编辑</a>',
              '&nbsp;&nbsp;&nbsp;',
              '<a href="javascript:;"  name="aritcal_remove" style="'+deleteDisplay+'"><img src="js/images/types/icons/cancel.png"  align="absmiddle" style="width:16px;height:16px"/>&nbsp;删除</a>',
              '</div>',
		      '</div>',
		      '</tpl>',
		      '</tpl>'
        	)
        	var dataview = new Ext.DataView({
        		tpl:tpl,
        		store:store,
        		autoHeight:true,
				singleSelect:true,
				loadingText:"加载中...",
				//overClass:'x-view-over',
				itemSelector:'div.thumb-wrap',
				emptyText:'没有查询到图文',
				listeners:{
					click:function(d,i,n,e){
						var record = store.getAt(i);
						if(e.target.name=="aritcal_edit"){
							articalWindow.show(dataview.el)
							articalForm.getForm().loadRecord(record)
						}else if(e.target.name=="aritcal_remove"){
							Ext.Msg.confirm("提示","是否确定删除此图文？",function(b){
								if(b=="yes"){
									Ext.Ajax.request({
										url:"article/delete.do",
										params:{
											id:record.data.id
										},
										success:function(response,request){
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
					}
				}
        	})
        	var webPageStore = new Ext.data.JsonStore({
				url:"${basePath}ewm/weixin-web-page!loadWebPages",
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
						var url = "${basePath}ewm/weixin-web-page!viewWebPage?id="+record.data.id
						articalForm.getForm().findField("url").setValue(url)
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
        	var articalForm = new Ext.form.FormPanel({
        		layout:"form",
        		labelAlign:'right',
        		frame:true,
        		region:"center",
        		defaults:{
        			anchor:"90%"
        		},
        		labelWidth:70,
        		items:[{
        			xtype:"hidden",
        			name:"id",
        			value:"0"
        		},{
        			xtype:"radiogroup",
        			name:'size',
        			allowBlank:false,
        			blankText:"请选择类型",
        			fieldLabel:"类型",
        			items:[{
        				boxLabel:"小图",
        				name:"size",
        				inputValue:2,
        				checked:true
        			},{
        				boxLabel:"大图",
        				name:"size",
        				inputValue:1
        				
        			}]
        		},{
        			xtype:"trigger",
        			fieldLabel:"图片路径",
        			readOnly:true,
        			editable:false,
        			name:"picUrl",
        			style:'background:transparent',
        			allowBlank:false,
        			onTriggerClick:function(){
        			imgWindow.show(this.el)
        			},
        			/*listeners:{
        				focus:function(){
        					
        				}
        			},*/
        			blankText:"请选择图片"
        		},{
        			xtype:"textfield",
        			fieldLabel:"标题",
        			name:"title",
        			allowBlank:false,
        			blankText:"请填写标题"
        		},{
//        			xtype:"trigger",
        			xtype:"textfield",
        			fieldLabel:"链接",
        			name:"url",
//        			onTriggerClick:function(){
//        				webPageWin.show(this.el)
//        			},
        			allowBlank:false,
        			blankText:"请填写链接"
        		},{
        			xtype:"textfield",
        			fieldLabel:"描述",
        			name:"description"
        		}]
        	})
        	var articalWindow = new Ext.Window({
        		manager:wg,
        		title:"图文详细",
        		width:400,
        		height:230,
        		modal:true,        		
        		buttonAlign:"center",
        		buttons:[{
        			text:"保存",
        			handler:function(){
        				if(articalForm.getForm().isValid()){
        					articalForm.getForm().submit({
        						waitMsg:'正在保存图文...',
        						url:"article/saveOrUpdate.do",
        						params:{woaId:pageOfficialAccountId},
        						success:function(form,action){
        							store.reload()
        							articalWindow.hide()
        						},
        						failure:function(form,action){
        							showErrorMsg(action.result.message)
        						}
        							
        					})
        				}
        					
        			}
        		}],
        		layout:"border",
        		items:[articalForm],
        		closeAction:'hide',
        		listeners:{
        			hide:function(){
        				articalForm.getForm().reset()
        			}
        		}
        	})
        	var pageOfficialAccountId;
        	var OfficialAccountsStore = new Ext.data.JsonStore({
			    id:'id',
			    url: 'woa/validList.do',
			    method:'get',
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
						store.baseParams.woaId = pageOfficialAccountId;
						store.reload();
						imgStore.baseParams.woaId = pageOfficialAccountId;
						imgStore.reload();
					},
					afterRender : function(combo) {
				    }
				}
			});
        	var articalPanel = new Ext.Panel({
        		region:"center",
        		items:[dataview],
        		layout:"form",
        		frame:true,
        		tbar:[{
        			text:"添加图文",
        			disabled:codes.indexOf("add")==-1,
        			handler:function(){
        				if(!officialAccountCbx.isValid())
        				return false;
        				articalWindow.show(this.el)
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
					displayMsg : '一共 {2}条图文信息',
					emptyMsg : "没有图文信息",
				})
        	})
			win = desktop.createWindow({
				id:'WXARTICAL-win',
              	listeners:{
              		close:function(){
              			imgWindow.close()
              		}
				},
                title:'图文库',                        
				items:[articalPanel],
                iconCls: 'icon-wxartical',
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'border'
            });
        }
        win.show();
    }
});