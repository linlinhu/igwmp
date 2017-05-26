/*
 * USM window
 */
Emin.WXMRWindow = Ext.extend(Ext.app.Module, {
    id:'WXMR-win',
    init : function(){
        this.launcher = {
            text: '关键字回复设置',
            iconCls:'icon-wxmr',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
    	var codes = "${codes}";
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('WXMR-win');
		if(!win){
			var wg = new Ext.WindowGroup()
			wg.zseed = 7500
			
			var articalStore = new Ext.data.JsonStore({
				data:[],
				fields:["title","description","picUrl","id"]
			});
			var articalTpl = new Ext.XTemplate(
			
			'<tpl for=".">',
			  '<div style="width:270px;">',
				'<tpl if="xcount == 1">',
					
					'<div style="width:100%">{title}</div>',
					'<div style="width:100%"><img src="{picUrl}" style="width:270px;height:150px"/></div>',
					'<div style="width:100%">{description}</div>',
					
				'</tpl>',
				'<tpl if="xcount &gt; 1">',
					'<tpl if="xindex == 1">',
					               
				      '<div style="position:relative"><img src="{picUrl}" style="width:100%;height:150px">',
				      '<div style="width:100%;text-align:left;postion:absolute;bottom:0;left:0;opacity:0.5;background-color:black;color:white;font-size:14px;min-height:30px;max-height:60px;overflow:hidden">{title}</div>',	     
				      '</div>',
				      
					'</tpl>',
					'<tpl if="xindex &gt; 1">',
					 		      
				      '<div style="background-color:white;clear:both;height:62px">',
				      '<div style="text-align:left;float:left;font-size:14px;line-height:60px;">{title}</div>',
				      '<img src="{picUrl}" style="width:60px;height:60px;float:right;"></div>',			     
				      
					'</tpl>',
				'</tpl>',
			   '</div>',
			'</tpl>'
			)
			//图文库数据源
			var store = new Ext.data.JsonStore({
				url:"article/page.do",
				totalProperty:"total",
				root:"rows",
				fields:["id","title","description","picUrl","url","size"]
			})
//			store.load({
//				params:{
//					start:0,
//					limit:20
//				}
//			})

			//图文库模板
        	var tpl = new Ext.XTemplate(
        	  
        	  '<tpl for=".">',        	  
        	  '<tpl if="size==1">',
              '<div class="thumb-wrap" id="artical_{id}" style="float:left;margin:10px;width:270px;position:relative">',              
		      '<div class="thumb" style="position:relative"><img src="{picUrl}" style="width:100%;height:150px">',
		      '<div class="x-editable" style="width:100%;text-align:left;postion:absolute;bottom:0;left:0;opacity:0.5;background-color:black;color:white;font-size:14px;min-height:30px;max-height:60px;overflow:hidden">{title}</div>',	     
		      '</div>',		     
		      '</div>',		     
		      '</tpl>',
		      '<tpl if="size==2">',
		      '<div class="thumb-wrap" id="artical_{id}" style="float:left;margin:10px;width:270px;">', 		      
		      
		      '<div class="thumb" style="background-color:white;clear:both;height:62px">',
		      '<div class="x-editable" style="text-align:left;float:left;font-size:14px;line-height:60px;">{title}</div>',
		      '<img src="{picUrl}" style="width:60px;height:60px;float:right;"></div>', 		     
		      '</div>',
		      '</tpl>',
		      '</tpl>'
		      
        	)
        	var dataview = new Ext.DataView({
        		tpl:tpl,
        		store:store,
        		region:"center",
        		autoHeight:true,
				singleSelect:true,
				loadingText:"加载中...",
				//overClass:'x-view-over',
				itemSelector:'div.thumb-wrap',
				emptyText:'没有查询到图文',
				listeners:{
					dblclick:function(d,i,n,e){
						var record = store.getAt(i);
						if(articalStore.getCount()==0 && record.data.size==2){
							showWarningMsg('第一个图文必须用大图');
						}else{
							articalWindow.hide();
							articalStore.add(record)
							
							
						}
					}
				}
        	})
        	var articalWindow = new Ext.Window({
        		layout:"border",
        		width:900,
        		height:500,
        		modal:true,
        		closeAction:"hide",
        		manager:wg,
        		items:[dataview],        		
        		frame:true,        		
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
					emptyMsg : "没有图文信息"
				})
        	})
        	var webPageStore = new Ext.data.JsonStore({
				url:"webPage/webPages.do",
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
				title:"插入文章链接",
				sm:new Ext.grid.RowSelectionModel(),
				loadMask:true,
				stripeRows:true,
				store:webPageStore,
				tbar:[{xtype:"checkbox",boxLabel:"换行插入",id:"webPageInsertWay"}],
				listeners:{
					rowdblclick:function(g,i,e){
						var record = webPageStore.getAt(i)
						var url = "webPage/viewWebPage.do?id="+record.data.id
						var wrap = Ext.getCmp("webPageInsertWay").checked
    					var str = ""
    					if(wrap){
    						str+="\n";
    					}
    					var link = urlForm.getForm().getValues(false)
    					str+="<a href='"+url+"'>"+record.data.title+"</a>"
    					replyForm.getForm().findField("rContent").setValue(replyForm.getForm().findField("rContent").getValue()+str)
    					urlWindow.hide()
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
        	var urlForm = new Ext.form.FormPanel({
        		layout:"form",
        		region:"center",
        		title:'插入普通链接',
        		frame:true,
        		buttonAlign:"center",
        		buttons:[{
        			text:'确定',
        			handler:function(){
        				if(urlForm.getForm().isValid()){
        					var wrap = urlForm.findByType("checkbox")[0].checked
        					var str = ""
        					if(wrap){
        						str+="\n";
        					}
        					var link = urlForm.getForm().getValues(false)
        					str+="<a href='"+link.linkAddress+"'>"+link.linkName+"</a>"
        					replyForm.getForm().findField("rContent").setValue(replyForm.getForm().findField("rContent").getValue()+str)
        					urlWindow.hide()
        				}
        			}
        		}],
        		defaults:{anchor:"90%"},
        		labelAlign:"right",
        		labelWidth:85,
        		items:[{
        			xtype:"textfield",
        			fieldLabel:"链接文本",
        			name:"linkName",
        			allowBlank:false,
        			blankText:'请输入链接文本'
        		},{
        			xtype:"textfield",
        			fieldLabel:"链接地址",
        			name:"linkAddress",        			
        			allowBlank:false,
        			blankText:'请输入链接地址'
        		},{
        			xtype:"checkbox",
        			fieldLabel:"换行插入",
        			checked:false
        		}]
        	})
        	var tabp = new Ext.TabPanel({
        		activeItem:0,
        		deferredRender:false,
        		items:[urlForm,webPagePanel],
        		region:'center'
        	})
        	var urlWindow = new Ext.Window({
        		modal:true,
        		manager:wg,
        		width:400,
        		height:200,
        		closeAction:"hide",
        		layout:"border",
        		items:[tabp],
        		listeners:{
        			show:function(){
        				webPageStore.load({
        					params:{
        						start:0,
        						limit:20
        					}
        				})
        			},
        			hide:function(){
        				urlForm.getForm().reset()
        			}
        		}
        		
        	})
			//var replyFieldSet = new Ext.form.FieldSet({})
			var replyForm = new Ext.form.FormPanel({
				bodyStyle:"padding-top:10px",
				layout:'form',
				defaults:{
					anchor:"90%"
				},
				labelAlign:"right",
				labelWidth:85,
				frame:true,
				region:"center",
				items:[{
					xtype:"hidden",
					name:"id",
					value:0
				},{
					xtype:"textfield",
					name:"content",
					fieldLabel:"关键字",
					allowBlank:false,						
					blankText:'请填写关键字'
				},{
					xtype:"textfield",
					name:"remark",
					fieldLabel:"回复描述",
					allowBlank:false,						
					blankText:'请填写回复描述'
				},{
					xtype:"radiogroup",
					fieldLabel:"回复类型",
					name:"replyType",
					listeners:{
						change:function(){
							if(this.getValue()=="news"){
								replyForm.getForm().findField("rContent").reset()
								replyForm.getForm().findField("rContent").disable()
								replyForm.findByType("panel")[0].getEl().up('.x-form-item').setDisplayed(false);
								replyForm.findByType("panel")[1].getEl().up('.x-form-item').setDisplayed(true);
							}else{
								replyForm.getForm().findField("rContent").show();
								replyForm.getForm().findField("rContent").enable();
								
								replyForm.findByType("panel")[0].getEl().up('.x-form-item').setDisplayed(true);
								replyForm.findByType("panel")[1].getEl().up('.x-form-item').setDisplayed(false);
								articalStore.removeAll()
							}
						}
					},
					items:[{
						boxLabel:"图文",
						name:'replyType',
						checked:true,
						inputValue:"news"
					},{
						boxLabel:"文本",
						name:'replyType',
						checked:false,
						inputValue:"text"
					}]
				},{
					xtype:'panel',
					style:"background:transparent",
					layout:"form",
					name:"contentPanel",					
					fieldLabel:"回复内容",			
					items:[{
						xtype:'textarea',
						height:200,
						hideLabel:true,
						name:"rContent",
						anchor:"90%",		
						allowBlank:false,						
						blankText:'请填写回复内容'
					},{
						xtype:"button",
						text:'插入链接',
						handler:function(){
							urlWindow.show(this.el)
						}
					}]
					
					
				},{
					xtype:"panel",
					fieldLabel:"图文内容",
					layout:"fit",
					height:240,
					bodyStyle:"padding-left:10px;overflow-y:auto;overflow-x:hidden",					
					tbar:[{
						text:'添加',
						handler:function(){
							store.load({
								params:{
									woaId:pageOfficialAccountId,
									start:0,
									limit:20
								}
							})
							articalWindow.show(this.el)
						}
					},{
						text:'重置',
						handler:function(){
							articalStore.removeAll();
						}
					}],
					items:[{
						xtype:"dataview",
						anchor:"100%",
						itemSelector:"div.pre-wrap",
						bodyStyle:"overflow-x:hidden;",
						autoHeight:true,
						store:articalStore,						
						tpl:articalTpl
					}]
				},{
					fieldLabel:"作为提示项",
					xtype:'radiogroup',
					name:"preferences",
					items:[{
						boxLabel:"是",
						inputValue:"true",
						name:"preferences"
					},{
						boxLabel:"否",
						inputValue:"false",
						name:"preferences",
						checked:true
					}]
				}]
			})
			var replyWindow = new Ext.Window({
				manager:wg,
				height:485,
				title:"回复信息",
				width:470,
				bbar:["<font style='color:red'>注：用户发送的关键字没有匹配内容时，会将提示项作为目录发送给用户</font>"],
				closeAction:"hide",
				modal:true,
				listeners:{
					hide:function(){
						replyForm.getForm().reset()
						articalStore.removeAll()
					}
				},
				items:[replyForm],
				layout:'border',
				buttonAlign:'center',
				buttons:[{
					text:'保存',
					handler:function(){
						if(replyForm.getForm().isValid()){
							var articalIds = []
							Ext.each(articalStore.getRange(),function(record){
								articalIds.push(record.data.id)
							})						
							replyForm.getForm().submit({
								url:"keyReply/saveOrUpdate.do",
								params:{
									articalIds:articalIds.join(","),
									woaId:pageOfficialAccountId
								},
								waitMsg:'正在保存...',
								success:function(form,action){
									replyWindow.hide()
									replyStore.reload();
								},
								failure:function(form,action){
									showErrorMsg(action.result.message)
								
								}
							})
						}
					}
				}]
			})
			var replyStore = new Ext.data.JsonStore({
				url:"keyReply/list.do",
			    method:'get',
				fields:["content","replyType","rContent","articles","id","remark","preferences"]
			})
			replyStore.load()
			var editDisplay = codes.indexOf("edit")==-1?"display:none":"";
			var deleteDisplay = codes.indexOf("delete")==-1?"display:none":"";
			var replyTpl = new Ext.XTemplate(
			'<tpl for=".">',
			'<div class="thumb-wrap" style="width:300px;float:left;background:white">',
			'<div style="width:100%;text-align:center;font-size:14px">关键字:{content}</div>',
			'<div style="width:100%;text-align:center;font-size:14px">描述:{remark}</div>',
			'<tpl if="preferences==true">',
			'<div style="width:100%;text-align:center;font-size:14px;color:red">提示项</div>',
			'</tpl>',
			'<tpl if="replyType==\'news\'">',
				'<tpl for="articles">',
					'<tpl if="xcount == 1">',
						'<div class="pre-wrap" style="width:270px;margin:auto">',
						'<div style="width:100%">{title}</div>',
						'<div style="width:100%"><img src="{picUrl}" style="width:270px;height:150px"/></div>',
						'<div style="width:100%">{description}</div>',
						'</div>',
					'</tpl>',
					'<tpl if="xcount &gt; 1">',
						'<tpl if="xindex == 1">',
						 '<div class="pre-wrap" style="width:270px;margin:auto" >',              
					      '<div style="position:relative"><img src="{picUrl}" style="width:100%;height:150px">',
					      '<div style="width:100%;text-align:left;postion:absolute;bottom:0;left:0;opacity:0.5;background-color:black;color:white;font-size:14px;min-height:30px;max-height:60px;overflow:hidden">{title}</div>',	     
					      '</div>',
					      '</div>',
						'</tpl>',
						'<tpl if="xindex &gt; 1">',
						  '<div class="pre-wrap"  style="width:270px;margin:auto">',		      
					      '<div style="background-color:white;clear:both;height:62px">',
					      '<div style="text-align:left;float:left;font-size:14px;line-height:60px;">{title}</div>',
					      '<img src="{picUrl}" style="width:60px;height:60px;float:right;"></div>',			     
					      '</div>',
						'</tpl>',
					'</tpl>',
				'</tpl>',
			'<div style="width:300px;text-align:center;">',
          	'<a href="javascript:void(0);" name="event_edit" style="'+editDisplay+'"><img src="js/images/types/icons/edit.gif"   align="absmiddle" style="width:16px;height:16px"/>&nbsp;编辑</a>',
          	'&nbsp;&nbsp;&nbsp;',
          	'<a href="javascript:void(0);"  name="event_remove" style="'+deleteDisplay+'"><img src="js/images/types/icons/cancel.png"  align="absmiddle" style="width:16px;height:16px"/>&nbsp;删除</a>',
         	'</div>',
	      	'</div>',
			'</tpl>',
			'<tpl if="replyType==\'text\'">',
			'<div style="text-align:center">{rContent:this.parseContent(rContent)}</div>',
			'<div style="width:300px;text-align:center;">',
          	'<a href="javascript:void(0);" name="event_edit"><img src="js/images/types/icons/edit.gif"   align="absmiddle" style="width:16px;height:16px"/>&nbsp;编辑</a>',
          	'&nbsp;&nbsp;&nbsp;',
          	'<a href="javascript:void(0);"  name="event_remove"><img src="js/images/types/icons/cancel.png"  align="absmiddle" style="width:16px;height:16px"/>&nbsp;删除</a>',
         	'</div>',
			'</tpl>',
			
			'</div>',
			'</tpl>',
			{
				parseContent:function(value){					
					return value.replaceAll("\n","<br/>");
				}
			}
			)
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
						replyStore.baseParams.woaId = pageOfficialAccountId;
						replyStore.reload();
						store.baseParams.woaId = pageOfficialAccountId;
						store.reload();
						
					},
					afterRender : function(combo) {
				    }
				}
			});
			var replyPanel = new Ext.Panel({
				region:"center",
				frame:true,
				layour:"border",
				items:[{
					region:"center",
					xtype:"dataview",
					store:replyStore,
					overClass:'x-view-over',
					tpl:replyTpl,
					itemSelector:"div.thumb-wrap",
					listeners:{
						click:function(d,i,n,e){
							var record = replyStore.getAt(i)
							
							if(e.target.name=="event_edit"){
								record.data.rcontent = record.data.rContent;
								replyWindow.show()																
								if(record.data.replyType=="news"){
									replyForm.getForm().findField("rContent").reset()
									replyForm.getForm().findField("rContent").disable()
									replyForm.findByType("panel")[0].getEl().up('.x-form-item').setDisplayed(false);
									replyForm.findByType("panel")[1].getEl().up('.x-form-item').setDisplayed(true);
									articalStore.loadData(record.data.articles)
								}else{
									replyForm.getForm().findField("rContent").show();
									replyForm.getForm().findField("rContent").enable();
									
									replyForm.findByType("panel")[0].getEl().up('.x-form-item').setDisplayed(true);
									replyForm.findByType("panel")[1].getEl().up('.x-form-item').setDisplayed(false);
								}
								record.data.preferences+=""
								replyForm.getForm().loadRecord(record)
								
							}else if(e.target.name=="event_remove"){
								Ext.Msg.confirm("提示","是否删除此关键字回复？",function(b){
									if(b=="yes"){
										Ext.Ajax.request({
											url:"keyReply/delete.do",
											params:{
												id:record.data.id
											},
											success:function(response,request){
												var data = Ext.decode(response.responseText)
												if(data.message!=""){
													showErrorMsg(data.message);
												}else{
													replyStore.reload();
												}
											}
										})
									}
								})
							}
						}
							
					}
				}],
				tbar:[{
					text:'新建',
					disabled:codes.indexOf("add")==-1,
					handler:function(){
            			if(!officialAccountCbx.isValid()){
            				return false;
            			}
						replyWindow.show()
						replyForm.getForm().findField("rContent").reset()
						replyForm.getForm().findField("rContent").disable()
						replyForm.findByType("panel")[0].getEl().up('.x-form-item').setDisplayed(false);
						replyForm.findByType("panel")[1].getEl().up('.x-form-item').setDisplayed(true);
					}
				},"->",officialAccountCbx]
			})
			win = desktop.createWindow({
				id:'WXMR-win',
              	listeners:{
				},
                title:'关键字回复设置',
                height:500,                
				items:[replyPanel],
                iconCls: 'icon-wxmr',
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit'
            });
        }
        win.show();
    }
});