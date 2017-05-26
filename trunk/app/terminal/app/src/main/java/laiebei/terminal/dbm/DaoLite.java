package laiebei.terminal.dbm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import laiebei.terminal.common.utilcode.DeviceUtils;
import laiebei.terminal.common.utilcode.EmptyUtils;
import laiebei.terminal.dbm.dao.DaoMaster;
import laiebei.terminal.dbm.dao.DaoSession;
import laiebei.terminal.dbm.dao.SessionDao;
import laiebei.terminal.dbm.dao.VentoutOrderDao;
import laiebei.terminal.dbm.domain.Session;
import laiebei.terminal.dbm.domain.VentoutOrder;
import laiebei.terminal.exceptions.ExceptionCode;
import laiebei.terminal.exceptions.RunException;


/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/3/26 17:21.
 * 对外接口:
 */

public class DaoLite {
	private final static String dbName = "lb_db";
	public static final DaoLite INSTANCE = new DaoLite();
	private DaoMaster.DevOpenHelper openHelper;
	private DaoSession daoSession;
	private Context context;

	private DaoLite(){}

	public void InitDao(Context context){
		this.context = context;
		openHelper = new DaoMaster.DevOpenHelper(context, dbName);
//获取可写数据库
		SQLiteDatabase db = openHelper.getWritableDatabase();
		//获取数据库对象
		DaoMaster daoMaster = new DaoMaster(db);
		//获取Dao对象管理者
		daoSession = daoMaster.newSession();
	}

	/**
	 * 获取可读数据库
	 */
	private SQLiteDatabase getReadableDatabase() throws RunException {
		if(openHelper == null){
			throw new RunException("db","helper");
		}
		SQLiteDatabase db = openHelper.getReadableDatabase();
		return db;
	}

	/**
	 * 获取可写数据库
	 */
	private SQLiteDatabase getWritableDatabase() {
		if (openHelper == null) {
			throw new RunException("db","helper");
		}
		SQLiteDatabase db = openHelper.getWritableDatabase();
		return db;
	}

	private <V,T> V findById(T id, Class<?> clazz){
		DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
		DaoSession daoSession = daoMaster.newSession();
		AbstractDao<?, ?>  dao = daoSession.getDao(clazz);
		QueryBuilder<V> qb = (QueryBuilder<V>) dao.queryBuilder();
		qb.where(dao.getPkProperty().eq(id));
		try{
			List<V> lists = qb.list();
			if(EmptyUtils.isEmpty(lists)){
				return null;
			}
			return lists.get(0);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}

	}


	private <V,T> V findByKeyword(Property property, T keyword, Class<?> clazz){
		AbstractDao<?, ?>  dao = daoSession.getDao(clazz);
		QueryBuilder<V> qb = (QueryBuilder<V>) dao.queryBuilder();
		qb.where(property.eq(keyword));
		try{
			List<V> lists = qb.list();
			if(EmptyUtils.isEmpty(lists)){
				return null;
			}
			return lists.get(0);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	private <V,T> List<V> findListByKeyword(Property property, T keyword, Class<?> clazz){
		AbstractDao<?, ?>  dao = daoSession.getDao(clazz);
		QueryBuilder<V> qb = (QueryBuilder<V>) dao.queryBuilder();
		qb.where(property.eq(keyword));
		try{
			List<V> lists = qb.list();
			if(EmptyUtils.isEmpty(lists)){
				return null;
			}
			return lists;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}

	}


	/***************************Session 表读写 Start************************************/
	public void  saveOrUpdateSession(Session session) {
		DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
		DaoSession daoSession = daoMaster.newSession();
		SessionDao dao = daoSession.getSessionDao();
		if(EmptyUtils.isEmpty(session)){
			dao.insert(session);
		}else{
			Session member = findById(session.getIpcCode(),Session.class);
			if(EmptyUtils.isEmpty(member)){
				dao.insert(session);
			}else{
				dao.update(session);
			}
		}

	}

	public void saveOrUpdateSession(String session){
		Session member = new Session();
		member.setSession(session);
		member.setIpcCode(DeviceUtils.getSerialID());
		member.setCreatTime(System.currentTimeMillis());
		member.setUpdateTime(System.currentTimeMillis());
		saveOrUpdateSession(member);
	}

	public Session querySession(String ipcCode)throws RunException{
		if(EmptyUtils.isEmpty(ipcCode)){
			throw new RunException(ExceptionCode.T_PARAMETER_ERROR,"");
		}
		return findById(ipcCode,Session.class);
	}

	/***************************Session 表读写 End************************************/

	/****************************Vendout Start*****************************************/
	public void  saveOrUpdateOrder(VentoutOrder order) throws RunException{
		if(EmptyUtils.isEmpty(order)){
			throw new RunException(ExceptionCode.T_PARAMETER_ERROR,"");
		}
		DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
		DaoSession daoSession = daoMaster.newSession();
		VentoutOrderDao dao = daoSession.getVentoutOrderDao();
		VentoutOrder member = findByKeyword(VentoutOrderDao.Properties.OrderId,order.getOrderId(),VentoutOrder.class);
		if(EmptyUtils.isEmpty(member)){
			dao.insert(order);
		}else{
			order.setId(member.getId());
			dao.update(order);
		}


	}

	public void saveOrUpdateOrder(String orderId,Long productId,int channel, int value, int status, boolean isReport){
		VentoutOrder member = new VentoutOrder();
		member.setOrderId(orderId);
		member.setChannel(channel);
		member.setValue(value);
		member.setCreatTime(System.currentTimeMillis());
		member.setStatus(status);
		member.setReport(isReport);
		member.setProductId(productId);
		saveOrUpdateOrder(member);
	}

	public VentoutOrder queryOrder(String keyword)throws RunException{
		if(EmptyUtils.isEmpty(keyword)){
			throw new RunException(ExceptionCode.T_PARAMETER_ERROR,"");
		}
		return findByKeyword(VentoutOrderDao.Properties.OrderId,keyword,VentoutOrder.class);
	}

	/**
	 * 根据上报状态查询订单列表
	 * */
	public List<VentoutOrder> queryOrderList(boolean isReport){
		return findListByKeyword(VentoutOrderDao.Properties.IsReport,isReport,VentoutOrder.class);
	}
	/****************************Vendout Start*****************************************/
}
