/*
 * wxconf window
 */
Emin.OPERATORWindow = Ext.extend(Ext.app.Module, {
    id:'OPERATOR-win',
    init : function(){
        this.launcher = {
            text: '合同管理',
            iconCls:'icon-operator',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('OPERATOR-win');
		if(!win){
			var desktop = this.app.getDesktop();
			var relateRestaurantVos = [];
			/****从服务器上拉取公众号信息列表*****/
			pageStore = new Ext.data.JsonStore({
			    id:'id',
			    url: 'operator/page.do',
				totalProperty:"total",
				root:"rows",
				fields: ["id","name","dept","telephone","wechatId","status","memo","restaurantVos"]
			});   
			pageStore.load({
				params:{
					start:0,
					limit:20
				}
			}); 
			
            

			var statusStore = new Ext.data.JsonStore({
	    	   id:'id',
	    	   totalProperty:"total",
	    	   root:"rows",
	    	   url: 'category/list.do',
	    	   fields: ['id', 'name']
			}) ;
	        statusStore.load();
	       	var selectedDept = '';
	        var deptStore = new Ext.data.JsonStore({
			    data: [{'id':'1','name':'店长'},{'id':'2','name':'经理'},{'id':'3','name':'服务员'}],
			    fields: ['id', 'name']
			}) ;
			var columns = [  
				{
					id:"name",   
					name:"name",   
		            header:"运营者",   
		            width:100,   
		            dataIndex:"name"
		        },{
		        	id:"dept",  
		        	name:"dept",   
		            header:"职务",   
		            width:100,   
		            dataIndex:"dept",
		            renderer:function(v,m,record){
		             	return v?deptStore.getById(v).get("name"):'未知';
		            }
		        },  
		        {  
		        	id:"restaurantVos",  
		        	name:"restaurantVos",   
		            header:"绑定餐厅",   
		            width:150,   
		            dataIndex:"restaurantVos",
		            renderer:function(v,m,record){
	             		//v 表示设备管理列表数据中绑定餐厅的id
						var bindRestaurants = "";
						if(v&&v.length>0){
							for(var i=0;i<v.length;i++){
								bindRestaurants+=v[i].name+",";
							}
							return bindRestaurants.substring(0,bindRestaurants.length-1);
						}
						return '暂无绑定';
						
		            } 
		        },
		        {  
		        	id:"telephone", 
		        	name:"telephone",   
		            header:"电话",   
		            width:150,     
		            dataIndex:"telephone"
		        },
		        {  
		        	id:"status", 
		            name:"status",
		            header:"状态",     
		            width:80,   
		            dataIndex:"status",
		            renderer:function(v,m,record){
		            	return v?'已绑定':'暂未登录';
		            }  
		        },
		        {  
		            id:"memo",
		            name:"memo",
		            header:"备注",   
		            width:150, 
		            dataIndex:"memo",  
		        },
		        {
		        	dataIndex:'operation',
                      xtype:'uxactioncolumn',  
                      header:'操作',  
                      autoWidth:false,  
                      width:180,                 
                      items: [{  
                          iconCls:'icon-edit',  
                          tooltip:'修改',  
                          text:'修改',  
                          stopSelection:false,  
                          scope:this,  
                          handler:function(grid, rowIndex, colIndex){  
                        	var record =  grid.getStore().getAt(rowIndex)
                			relateRestaurantVos = record.get("restaurantVos");
                			var strs = '';
                			for(var i=0;i<relateRestaurantVos.length;i++){
                				strs += relateRestaurantVos[i].name+",";
                			}
                			strs = strs.substring(0,strs.length-1);
                			record.set("restaurantVos_temp",strs);  
                        	wcDetailWin.show();
                        	wcDetailForm.getForm().loadRecord(record);
                          }  
                      },{  
                          iconCls:'icon-delete',  
                          tooltip:'删除',  
                          text:'删除',  
                          stopSelection:false,  
                          scope:this,  
                          handler:function(grid, rowIndex, colIndex){  
                        	var record =  grid.getStore().getAt(rowIndex)
    						Ext.Msg.wait("正在删除","请稍后")
                        	Ext.Ajax.request({
								url:"operator/delete.do",
								params:{
									id:record.data.id
								},
								success:function(response,request){
									Ext.Msg.hide()
									var d = Ext.decode(response.responseText)
									if(!d.success&&d.message!=""){
										showErrorMsg(d.message)
									}else{
										pageStore.reload();	
									}
								}
							})
                          }  
                      }]
	        	} 
		    ];
		    var add_bar = {  
	            text: "新增",  
	            iconCls: "icon-add",  
	            handler: function(){ 
	            	setTimeout(function(){
	            		wcDetailForm.getForm().setValues(null);
	                	wcDetailWin.show();
	            	})
	            }  
	       };
			var refresh_bar = {  
	            text: "刷新",  
	            iconCls: "icon-refresh",  
	            handler: function(){ 
	            	pageStore.reload();//刷新列表数据
	            }  
	       };
	       var keywordTf = new Ext.form.TextField({
	       			id:"keyword",
	       			name:"keyword",
	       			fieldLabel:"关键字",
				    inputType:'text',
				    emptyText:'输入餐厅名称、运营者名字进行搜索',
		            width:300,
		            style:'margin-right:15px'
			});
			var searchBtn = {
	            text: "搜索",  
	            iconCls: "icon-search",  
	            handler: function(){  
					pageStore.load({
						params:{
							start:0,
							limit:20,
							keyword:Ext.get("keyword").getValue()=='输入餐厅名称、运营者名字进行搜索'?'':Ext.get("keyword").getValue(),
						}
					});//刷新列表数据
	            } 
			};
			
			var grid = new Ext.grid.EditorGridPanel({  
			    width: 500,  
			    height: 300,  
			    frame: true,  
			    tbar: [  
			       add_bar,
			       refresh_bar,
			       "->",
			       keywordTf,
			       searchBtn
			    ],  
			    store: pageStore,
			    columns: columns,  
			    region:"center",
			    autoExpandColumn: "restaurantVos",
				bbar:[new Ext.PagingToolbar({
        			pageSize : 10,
					store : pageStore,
					beforePageText : "第",
					afterPageText : "页，共{0}页",
					lastText : "尾页",
					nextText : "下一页",
					prevText : "上一页",
					firstText : "首页",
					refreshText : "刷新",
					displayInfo : true,
					displayMsg : '一共 {2}条运营者信息',
					emptyMsg : "没有运营者信息"
				})]
			});  
			
			
			
			//窗口
			win = desktop.createWindow({
				id:'OPERATOR-win',
              	listeners:{
              		'close':function(r){
              			wcDetailWin.close();
              			restaurantWin.close();
              		}
				},
                title:'运营者信息管理',
                height:500,               
				items:[grid],
                iconCls: 'icon-wxconf',
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'border'
           });
			
			var deptCbx = new Ext.form.ComboBox({
				width:120,
	            fieldLabel:'职务',
			    store: deptStore ,
			    displayField:'name',
			    valueField:'id',
			    mode: 'local',
			    emptyText:'请选择职务...',
			    typeAhead: true,
			    triggerAction: 'all',
			    selectOnFocus:true,
	            editable: false,
				hiddenName:"dept",
	            listeners:{
					select:function(e){
						selectedDept = e.value;
					}
				}
			});
			var wcDetailForm=new Ext.form.FormPanel({
				labelAlign: 'left',  //标签位置  
                buttonAlign: 'center',  
				labelWidth:100,
				anchor:"95%",
                frame: true,                
				defaultType: 'textfield',  //默认类型  
                items:[{
                	xtype:"hidden",
                	id:"id",
                	name:"id",
                },{
                	xtype:"hidden",
                	id:"status",
                	name:"status",
                },{
                	id:"name",
                	name:"name",
                	fieldLabel:"姓名",
                	allowBlank:false,
                	width:300,
                },deptCbx,{
                	id:"telephone",
                	name:"telephone",
                	fieldLabel:"电话",
                	allowBlank:false,
                	width:300,
                },{
                	id:"restaurantVos_temp",
                	name:"restaurantVos_temp",
                	fieldLabel:"绑定餐厅",
                	allowBlank:false,
                	width:300,
                	listeners:{
                		focus:function(){
                			restaurantWin.show();
                		}
                	}
                },{
                	xtype:"textarea",
                	id:"memo",
                	name:"memo",
                	fieldLabel:"备注",
                	width:300,
                }],
                
                
				
			});
			
			var wcDetailWin=new Ext.Window({
				items:[wcDetailForm],
				modal:true,//模态化
				constrain :true,
				resizable :false,
				width:480,
				autoHeight:true,   
                autoScroll:true,
                bodyStyle:"max-height:700px",
				buttons:[{
					text:"确定",
					handler:function(){
						 if (wcDetailForm.getForm().isValid()) { 
							var fvalues = wcDetailForm.getForm().getValues();
							fvalues['status'] = fvalues.status?fvalues.status:0;
							fvalues['restaurantVos'] = relateRestaurantVos;
							delete fvalues['restaurantVos_temp'];
    						Ext.Msg.wait("正在保存","请稍后")
							Ext.Ajax.request({
								url:"operator/saveOrUpdate.do",
								params:{
									jsonStr:JSON.stringify(fvalues)
								},
								success:function(response,request){
									Ext.Msg.hide()
									var d = Ext.decode(response.responseText)
									if(d.message!=""){
										showErrorMsg("保存修改报错")
									}else{
										wcDetailForm.getForm().reset();
										pageStore.reload();	
										wcDetailWin.hide();
									}
								}
							})
			            } 
					}
				}],
				buttonAlign:"center",
				title:"运营者信息详情",
				closeAction:"hide",//关闭事件触发hide事件
				listeners:{
					hide:function(){
						wcDetailForm.getForm().reset();
					}
				},
			});
			
			
			var restaurantStore = new Ext.data.JsonStore({
			    id:'id',
			    url: 'restaurant/page.do',
				totalProperty:"total",
				root:"rows",
				fields: ['id', 'name', 'runType','contractVo','telephone','province','city','area','address','operatorList','memo']
			});
			restaurantStore.load({
				params:{
					limit:20,
					start:0
				}
			});
			var sm = new Ext.grid.CheckboxSelectionModel()	
			
			var restaurantColums = [
				new Ext.grid.RowNumberer(),
				sm,{
	            id:"contractVo",   
	            header:"合同信息",   
	            width:150,   
	            dataIndex:"contractVo",
	            renderer:function(v,m,record){
	            	if(v){
		            	return v.contractNum
	            	}else{
	            		return "";
	            	}
	            }
	        },  
	        {  
	            id:"name",   
	            header:"餐厅名称",   
	            width:150,   
	            dataIndex:"name"  
	        },  
	        {  
	            id:"runType",
	            header:"使用场景",     
	            width:80,   
	            dataIndex:"runType",
	            renderer:function(v,m,record){
	             	return v?statusStore.getById(v).data.name:'未知'
	            }
	        },
	        {  
	            id:"telephone",   
	            header:"联系电话",   
	            width:100,     
	            dataIndex:"telephone",
	        },
	        {  
	            id:"address",   
	            header:"详细地址",     
	            width:150,   
	            dataIndex:"address",
	            renderer:function(v,m,record){
	             	var drow = record.data;
	             	return drow.province?drow.province:""+drow.city?drow.city:""+drow.area?drow.area:""+v;
	            }
	        } 
	  		];
	  		
	  		var restaurant_refresh_bar = {  
	            text: "刷新",  
	            iconCls: "icon-refresh",  
	            handler: function(){  
	            	restaurantStore.reload();//刷新列表数据
	            }  
	       };
	       var restaurant_confirm_bar = {  
	            text: "确定",  
	            iconCls: "icon-confirm",  
	            handler: function(){  
	            	var records = sm.getSelections();
	            	relateRestaurantVos = [];
	            	if(records.length>0){
	            		var strs = ""; 
	            		for(var i=0;i<records.length;i++){
	            			var d = records[i].data;
	            			strs += d.name + ",";
	            			relateRestaurantVos.push(d);
	            		}
	            		strs = strs.substring(0,strs.length-1);
	            		Ext.getCmp("restaurantVos_temp").setValue(strs);
	            		
	            	}
	            	
            		restaurantWin.hide();
	            }  
	       };
	       var restaurant_keywordTf = new Ext.form.TextField({
	       			id:"restaurant_keyword",
	       			name:"keyword",
	       			fieldLabel:"关键字",
			    	emptyText:'输入餐厅名称、合同编号搜索',
				    inputType:'text',
		            width:300,
		            style:'margin-right:15px'
			});
	       var restaurant_selectedRunType = '';
			var restaurant_statusCbx = new Ext.form.ComboBox({
				width:120,
				id:'searchRunType',
	            fieldLabel:'使用场景',
			    store: statusStore ,
			    displayField:'name',
			    valueField:'id',
			    mode: 'local',
			    emptyText:'请选择状态...',
			    typeAhead: true,
			    triggerAction: 'all',
			    selectOnFocus:true,
	            editable: false,
				hiddenName:"id",
	            listeners:{
					select:function(e){
						restaurant_selectedRunType = e.value;
					}
				}
			});
			var restaurant_searchBtn = {
	            text: "搜索",  
	            iconCls: "icon-search",  
	            handler: function(){  
					restaurantStore.load({
						params:{
							start:0,
							limit:10,
							keyword:Ext.get("restaurant_keyword").getValue()!='输入餐厅名称、合同编号搜索'?Ext.get("restaurant_keyword").getValue():'',
							runType:restaurant_selectedRunType
						}
					});//刷新列表数据
	            } 
			};
			var restaurantGrid = new Ext.grid.EditorGridPanel({  
				    anchor: '100%',  
	            	height:400,     
				    frame: true,  
				    tbar: [  
				       restaurant_refresh_bar,
				       "->",
				       restaurant_keywordTf,
				       restaurant_statusCbx,
				       restaurant_searchBtn
				    ],  
				    store: restaurantStore,
				    sm:sm,
				    columns: restaurantColums,
				    region:"center",
				    autoExpandColumn: "address", 
					bbar:[new Ext.PagingToolbar({
	        			pageSize : 10,
						store : restaurantStore,
						beforePageText : "第",
						afterPageText : "页，共{0}页",
						lastText : "尾页",
						nextText : "下一页",
						prevText : "上一页",
						firstText : "首页",
						refreshText : "刷新",
						displayInfo : true,
						displayMsg : '一共 {2}条餐厅信息',
						emptyMsg : "没有餐厅信息"
					}),"->",restaurant_confirm_bar]
				});  
			
			var restaurantWin=new Ext.Window({
				title:'选择您要绑定的餐厅',
				closeAction:"hide",//关闭事件触发hide事件
				modal:true,//模态化
				constrain :true,
				resizable :false,
				width:780,
	            autoHeight:true, 
	            bodyStyle:"min-height:300px",
				items:[restaurantGrid],
	            iconCls: 'icon-operator',
	            shim:false,
	            animCollapse:false,
	            constrainHeader:true,
	            layout: 'border'
			});
        	
		}
        win.show();
    }
});

