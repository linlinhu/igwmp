/*
 * person window
 */
Emin.PERSONWindow = Ext.extend(Ext.app.Module, {
    id:'person-win',
    init : function(){
        this.launcher = {
            text: '微信配置',
            iconCls:'icon-wxconf',
            handler : this.createWindow,
            scope: this
        }
    },
    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('person-win');
		if(!win){
			//请求载入数据
			var personStore = new Ext.data.JsonStore({
				data:[{id:1,name:"张三",age:18,sex:"男"}],
				fields:["id","name","age","sex"]
			})
			//confStore.load();
			//列模型
			var columnModel = new Ext.grid.ColumnModel([
				new Ext.grid.RowNumberer(),
				{header:"姓名",dataIndex:"name"},
				{header:"年龄",dataIndex:'age'},
				{header:"性别",dataIndex:"sex",renderer:function(v,metadata,record,rowIndex,columnIndex,store){
					 return v;
				}}
			])
			//可编辑面板
			var grid = new Ext.grid.EditorGridPanel({
				store:personStore,
				cm:columnModel,
				clicksToEdit:2,
				enableHdMenu:false,
				tbar:[{
					text:"保存",
					handler:function(){
					}
				}],
				region:"center",
				sm:new Ext.grid.RowSelectionModel(),
				viewConfig:{
					forceFit:true
				}
			})
			//窗口
			win = desktop.createWindow({
	                id: 'person-win',
	              	listeners:{
					},
	                title:'微信配置',
	                height:500,               
					items:[grid],
	                iconCls: 'icon-wxconf',
	                shim:false,
	                animCollapse:false,
	                constrainHeader:true,
	                layout: 'border'
	            });
	        }
	        win.show();
    }
});