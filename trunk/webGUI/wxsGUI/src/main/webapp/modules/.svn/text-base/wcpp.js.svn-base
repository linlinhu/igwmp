/*
 *  Products of Win-Cabinet's Price
 * 酒柜产品价格
 */
Emin.WCPPWindow = Ext.extend(Ext.app.Module, {
    id:'WCPP-win',
    init : function(){
        this.launcher = {
            text: '酒品价格管理',
            iconCls:'icon-wcpp',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('WCPP-win');
		if(!win){
			var desktop = this.app.getDesktop();
			
			pageStore = new Ext.data.JsonStore({
			    id:'id',
			    url: 'price/page.do',
				totalProperty:"total",
				root:"rows",
				fields: ['id','productId','productName', 'runType', 'purchPrice','originalPrice','sellingPrice']
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
            
			var columns = [  
		        {  
		            id:"productId",   
		            header:"编号",   
		            width:80,   
		            dataIndex:"productId",
		        },  
		        {  
		            id:"productName",   
		            header:"酒品名称",   
		            width:120,   
		            dataIndex:"productName",
		        },  
		        {  
		            id:"runType",
		            header:"使用场景",     
		            width:80,   
		            dataIndex:"runType",   
		            renderer:function(v,m,record){
		            	var status = v?statusStore.getById(v):null;
		             	return status?status.data.name:''
		            }
		        },
		        {  
		            id:"purchPrice",
		            header:"进价",     
		            width:80,   
		            dataIndex:"purchPrice"
		        },
		        {  
		            id:"originalPrice",
		            header:"原价",     
		            width:80,   
		            dataIndex:"originalPrice"
		        },
		        {  
		            id:"sellingPrice",
		            header:"售价",     
		            width:80,   
		            dataIndex:"sellingPrice"
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
                        	  priceDetailWin.show();
                          	  var record =  grid.getStore().getAt(rowIndex)
                          	  priceDetailWin.show();
                        	  priceDetailForm.getForm().loadRecord(record);
                          }  
                      },{  
                            iconCls:'icon-edit',  
                            tooltip:'清除价格',  
                            text:'清除价格',  
                            stopSelection:false,  
                            scope:this,  
                            handler:function(grid, rowIndex, colIndex){  
                        		var uptObj = grid.getStore().getAt(rowIndex);
                        		uptObj.set("purchPrice","0");
                        		uptObj.set("sellingPrice","0");
                        		uptObj.set("originalPrice","0");
                        		uptObj.commit();
                          }  
                      }]
	        	} 
		    ];
		    

			var selectedRunType = '';
			var statusCbx = new Ext.form.ComboBox({
				width:120,
				id:'searchRunType',
				fieldLabel:'使用场景',
				store: statusStore ,
				displayField:'name',
				valueField:'id',
				mode: 'local',
				emptyText:'请选择状态...',
				typeAhead: true,
	            allowBlank : false,
				triggerAction: 'all',
				selectOnFocus:true,
				editable: false,
				hiddenName:"id",
			    listeners:{
					select:function(e){
						selectedRunType = e.value;
					}
				}
			});
	       var keywordTf = new Ext.form.TextField({
	       			id:"keyword",
	       			name:"keyword",
	            	allowBlank : false,
				    emptyText:'输入酒品名称搜索',
	       			fieldLabel:"关键字",
				    inputType:'text',
		            width:300,
		            style:'margin-right:10px'
			});
			var searchBtn = {
	            text: "搜索",  
	            iconCls: "icon-search",  
	            handler: function(){
            		pageStore.load({
						params:{
							start:0,
							limit:20,
							keyword:Ext.get("keyword").getValue()=='输入酒品名称搜索'?'':Ext.get("keyword").getValue(),
							runType:selectedRunType
						}
					});//刷新列表数据
	            } 
			};
			
			var grid = new Ext.grid.EditorGridPanel({  
			    width: 500,  
			    height: 300,  
			    frame: true,  
			    tbar: [  
			       keywordTf,
			       statusCbx,
			       searchBtn
			    ],  
			    store: pageStore,
			    columns: columns,  
			    region:"center",
			    autoExpandColumn: "productName", 
				bbar:new Ext.PagingToolbar({
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
					displayMsg : '一共 {2}条酒品价格信息',
					emptyMsg : "没有酒品价格信息"
				})
			});  
			
			
			
			//窗口
			win = desktop.createWindow({
				id:'WCPP-win',
              	listeners:{
              		'close':function(r){
              			priceDetailWin.close();
              		}
				},
                title:'酒品价格管理',
                height:500,               
				items:[grid],
                iconCls: 'icon-wcpp',
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'border'
            });
			
			var priceDetailForm = new Ext.form.FormPanel({
				defaultType: 'textfield',  //默认类型  
				frame:true,
			    labelAlign: 'left',  //标签位置  
				labelWidth:80,
				bodyStyle:"padding:10px 15px",
                items:[
				{
					xtype:"hidden",
                    name:'id',
                    id:'id',
		    	},{
					xtype:"hidden",
                    name:'runType',
                    id:'runType',
		    	},{
                    fieldLabel: '酒品编号',
                    name:'productId',
                    id:'productId',
                    cls:'label-input',
                    readOnly:true,
                    width: 200,
		    	},{
                    fieldLabel: '酒品名称',
                    name:'productName',
                    id:'productName',
                    cls:'label-input',
                    readOnly:true,
                    width: 200,
		    	},
				{
                    fieldLabel: '使用场景',
                    name:'runType',
                    id:'runType',
                    cls:'label-input',
                    readOnly:true,
                    width: 200
		    	},{
                	xtype: 'numberfield',
                    fieldLabel: '进价',
                    name:'purchPrice',
                    id:'purchPrice',
                    allowBlank:true,
                    width: 200
		    	},{
                	xtype: 'numberfield',
                    fieldLabel: '原价',
                    name:'originalPrice',
                    id:'originalPrice',
                    allowBlank:true,
                    width: 200
		    	},{
                	xtype: 'numberfield',
                    fieldLabel: '实际售价',
                    name:'sellingPrice',
                    id:'sellingPrice',
                    allowBlank:true,
                    width: 200
		    	}
		    	],
			});
			/****
			 * 价格详细信息表格
			 */
			var priceDetailWin=new Ext.Window({
				title:'修改价格',
				modal:true,//模态化
				constrain :true,
				resizable :false,
				width:360,
	            autoHeight:true,
	            bodyStyle:"max-height:500px;",
				items:[
				    priceDetailForm
				],
				buttons:[{
					type:"button",
					text:"保存",
					listeners:{
						click:function(){
							if(priceDetailForm.getForm().isValid()){
								var values = priceDetailForm.getForm().getValues();
								Ext.Ajax.request({
									url:"price/saveOrUpdate.do",
									params:{
										jsonStr:JSON.stringify(values)
									},
									success:function(response,request){
										var data = Ext.decode(response.responseText)
										if(data.message!=""){
											showErrorMsg(data.message);
										}else{
											pageStore.reload();
											priceDetailWin.hide();
										}
									}
								});
							}
						}
					}
				}],
                autoScroll:true,
				buttonAlign:"center",
				closeAction:"hide",
				listeners:{
					hide:function(){
						priceDetailForm.getForm().reset();
					}
				}
			});
 
		}
        win.show();
    }
});

