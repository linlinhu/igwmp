package laiebei.terminal.dbm.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import laiebei.terminal.dbm.domain.CabinetInfo;
import laiebei.terminal.dbm.domain.ChannelInfo;
import laiebei.terminal.dbm.domain.NetTraffic;
import laiebei.terminal.dbm.domain.Resources;
import laiebei.terminal.dbm.domain.Session;
import laiebei.terminal.dbm.domain.VentoutOrder;

import laiebei.terminal.dbm.dao.CabinetInfoDao;
import laiebei.terminal.dbm.dao.ChannelInfoDao;
import laiebei.terminal.dbm.dao.NetTrafficDao;
import laiebei.terminal.dbm.dao.ResourcesDao;
import laiebei.terminal.dbm.dao.SessionDao;
import laiebei.terminal.dbm.dao.VentoutOrderDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig cabinetInfoDaoConfig;
    private final DaoConfig channelInfoDaoConfig;
    private final DaoConfig netTrafficDaoConfig;
    private final DaoConfig resourcesDaoConfig;
    private final DaoConfig sessionDaoConfig;
    private final DaoConfig ventoutOrderDaoConfig;

    private final CabinetInfoDao cabinetInfoDao;
    private final ChannelInfoDao channelInfoDao;
    private final NetTrafficDao netTrafficDao;
    private final ResourcesDao resourcesDao;
    private final SessionDao sessionDao;
    private final VentoutOrderDao ventoutOrderDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        cabinetInfoDaoConfig = daoConfigMap.get(CabinetInfoDao.class).clone();
        cabinetInfoDaoConfig.initIdentityScope(type);

        channelInfoDaoConfig = daoConfigMap.get(ChannelInfoDao.class).clone();
        channelInfoDaoConfig.initIdentityScope(type);

        netTrafficDaoConfig = daoConfigMap.get(NetTrafficDao.class).clone();
        netTrafficDaoConfig.initIdentityScope(type);

        resourcesDaoConfig = daoConfigMap.get(ResourcesDao.class).clone();
        resourcesDaoConfig.initIdentityScope(type);

        sessionDaoConfig = daoConfigMap.get(SessionDao.class).clone();
        sessionDaoConfig.initIdentityScope(type);

        ventoutOrderDaoConfig = daoConfigMap.get(VentoutOrderDao.class).clone();
        ventoutOrderDaoConfig.initIdentityScope(type);

        cabinetInfoDao = new CabinetInfoDao(cabinetInfoDaoConfig, this);
        channelInfoDao = new ChannelInfoDao(channelInfoDaoConfig, this);
        netTrafficDao = new NetTrafficDao(netTrafficDaoConfig, this);
        resourcesDao = new ResourcesDao(resourcesDaoConfig, this);
        sessionDao = new SessionDao(sessionDaoConfig, this);
        ventoutOrderDao = new VentoutOrderDao(ventoutOrderDaoConfig, this);

        registerDao(CabinetInfo.class, cabinetInfoDao);
        registerDao(ChannelInfo.class, channelInfoDao);
        registerDao(NetTraffic.class, netTrafficDao);
        registerDao(Resources.class, resourcesDao);
        registerDao(Session.class, sessionDao);
        registerDao(VentoutOrder.class, ventoutOrderDao);
    }
    
    public void clear() {
        cabinetInfoDaoConfig.clearIdentityScope();
        channelInfoDaoConfig.clearIdentityScope();
        netTrafficDaoConfig.clearIdentityScope();
        resourcesDaoConfig.clearIdentityScope();
        sessionDaoConfig.clearIdentityScope();
        ventoutOrderDaoConfig.clearIdentityScope();
    }

    public CabinetInfoDao getCabinetInfoDao() {
        return cabinetInfoDao;
    }

    public ChannelInfoDao getChannelInfoDao() {
        return channelInfoDao;
    }

    public NetTrafficDao getNetTrafficDao() {
        return netTrafficDao;
    }

    public ResourcesDao getResourcesDao() {
        return resourcesDao;
    }

    public SessionDao getSessionDao() {
        return sessionDao;
    }

    public VentoutOrderDao getVentoutOrderDao() {
        return ventoutOrderDao;
    }

}