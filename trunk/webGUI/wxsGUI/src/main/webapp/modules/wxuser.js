/*
 * USM window
 */
Emin.WXUSERWindow = Ext.extend(Ext.app.Module, {
    id:'WXUSER-win',
    init : function(){
        this.launcher = {
            text: '微粉丝',
            iconCls:'icon-user',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var app = this.app;
        var win = desktop.getWindow('WXUSER-win');
		if(!win){
			var wg = new Ext.WindowGroup();
        	wg.zseed = 7500;
			//数据存储
        	var userStore = new Ext.data.JsonStore({
        		url:"wxUser/page.do",
        		totalProperty:"total",
        		root:"rows",
        		fields:["id","nickname","subscribeTime","sex","headimgurl","mobile"]
        	});
        	userStore.load({
        		params:{
        			start:0,
        			limit:20
        		}
        	});
        	
        	
        	//列模型
        	var userCm = new Ext.grid.ColumnModel([
        		new Ext.grid.RowNumberer(),
        		{header:"头像",dataIndex:"img",autoWidth:true,renderer:function(v,m,r,row,c,s){
        			return "<img src='"+r.data.headimgurl+"' style='width:40px;height:40px;border-radius:20px' align='absmiddle'/>"
        		}},
        		{id:"nicknamecolumn",header:"昵称", width:200,dataIndex:"nickname",renderer:function(v,m){
        			m.attr="style='line-height:40px'"
        			return v
        		}},
        		{header:"性别",dataIndex:"sex",autoWidth:true,renderer:function(v,m){
        			m.attr="style='line-height:40px'"
        			var sex = "未知"
        			switch(v){
        				case 1:sex = "男";break;
        				case 2:sex = "女";break;
        			}
        			return sex;
        		}},
        		{header:"手机号",dataIndex:"mobile"},
        		{header:"关注时间",dataIndex:"subscribeTime",autoWidth:true,renderer:function(v){
        			return new Date(v).format("Y-m-d H:i")
        		}}
        	]);
        	
        	
			var userTable = new Ext.grid.EditorGridPanel({
				store:userStore,
				cm:userCm,
				enableColumnMove:false,
        		enableColumnResize:false,
        		stripeRows:true,
        		sm:new Ext.grid.RowSelectionModel(),
        		region:"center",
        		tbar:["昵称:",{
					xtype:"textfield",
					fieldLabel:"昵称"
					
				},{
					text:"查询",
					iconCls:"icon-search",
					handler:function(){
						var name = userTable.getTopToolbar().items.itemAt(1).getValue();
						userStore.baseParams.nickname = name
						userStore.load({
							params:{
								start:0,
								limit:20
							}
						})
					}
				}],
        		loadMask:true,
			    viewConfig:{
        			autoFill:true//自动填满
        		},
        		bbar:new Ext.PagingToolbar({
        			pageSize : 20,
					store : userStore,
					beforePageText : "第",
					afterPageText : "页，共{0}页",
					lastText : "尾页",
					nextText : "下一页",
					prevText : "上一页",
					firstText : "首页",
					refreshText : "刷新",
					displayInfo : true,
					displayMsg : '一共 {2}条用户信息',
					emptyMsg : "没有用户信息"
				})
			})
			
			//窗口
			win = desktop.createWindow({
	                id: 'WXUSER-win',
	              	listeners:{
					},
	                title:'微粉丝',
	                height:500,               
					items:[userTable],
	                iconCls: 'icon-user',
	                shim:false,
	                animCollapse:false,
	                constrainHeader:true,
	                layout: 'border'
	            });
	        }
	        win.show();
    }
});