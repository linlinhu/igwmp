/*
 *  Environment of Win-Cabinet's waiter Manage
 * 酒柜环境服务员管理
 */
Emin.SMLTESTWindow = Ext.extend(Ext.app.Module, {
    id:'SMLTEST-win',
    init : function(){
        this.launcher = {
            text: '测试',
            iconCls:'icon-smltest',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('SMLTEST-win');
		if(!win){
			var desktop = this.app.getDesktop();
			pageStore = new Ext.data.JsonStore({
                id:'id',
                url: 'device/pageTest.do',
                totalProperty:"total",
                root:"rows",
                fields: ['id', 'code', 'ipc_code','channel_sum','create_time','lastmodify_time','product_model','restaurant_name','sim_code','status','machine_control_id']
			});
            pageStore.load();
            var columns = [
                {
                    id:"id",
                    header:"id",
                    width:150,
                    dataIndex:"id"
                },{
                    id:"code",
                    header:"设备资产编号",
                    width:150,
                    dataIndex:"code"
                },
                {
                    id:"restaurant_name",
                    header:"绑定餐厅名称",
                    width:150,
                    dataIndex:"restaurant_name"
                },
                {
                    id:"ipc_code",
                    header:"工控机编号",
                    width:100,
                    dataIndex:"ipc_code",
                },
                {
                    id:"sim_code",
                    header:"SIM卡号",
                    width:150,
                    dataIndex:"sim_code",
                },
                {
                    id:"machine_control_id",
                    header:"机器状态控制",
                    width:150,
                    dataIndex:"machine_control_id",
                },
                {
                    id:"create_time",
                    header:"创建时间",
                    width:150,
                    dataIndex:"create_time",
                },
                {
                    id:"product_model",
                    header:"产品型号",
                    width:150,
                    dataIndex:"product_model",
                }
            ];
		    
	       var keywordTf = new Ext.form.TextField({
	       			id:"keyword",
	       			name:"keyword",
	       			fieldLabel:"关键字",
				    inputType:'text',
				    emptyText:'输入关键字进行搜索',
		            allowBlank : true,
		            width:300,
		            style:'margin-right:10px'
			});
			var searchBtn = {
	            text: "搜索",  
	            iconCls: "icon-search",  
	            handler: function(){  
					pageStore.baseParams.keyword = Ext.get("keyword").getValue();
            		pageStore.load();//刷新列表数据
	            } 
			};
			

			var grid = new Ext.grid.EditorGridPanel({
			    width: 500,  
			    height: 300,  
			    frame: true,  
			    tbar: [  
			       keywordTf,
			       searchBtn
			    ],
			    store: pageStore,
			    columns: columns,
			    region:"center",
			    autoExpandColumn: "restaurant_name",  //列自动伸展
			    listeners:{//监听列表值发生改变后执行代码
			    	afteredit:function(e){
			    		var record = e.record;//发生改变的行数据
			    	}
			    },
                bbar:new Ext.PagingToolbar({
                    pageSize : 20,
                    store : pageStore,
                    beforePageText : "第",
                    afterPageText : "页，共{0}页",
                    lastText : "尾页",
                    nextText : "下一页",
                    prevText : "上一页",
                    firstText : "首页",
                    refreshText : "刷新",
                    displayInfo : true,
                    displayMsg : '一共 {2}条记录',
                    emptyMsg : "没有记录噢"
                })
			});

			//窗口
			win = desktop.createWindow({
                id: 'SMLTEST-win',
              	listeners:{
				},
                title:'sml-test',
                height:500,               
				items:[grid],
                iconCls: 'icon-wcconf',
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'border'
            });
		}
        win.show();
    }
});

