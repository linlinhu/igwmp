package laiebei.terminal.dbm.domain;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

import laiebei.terminal.dbm.dao.CabinetInfoDao;
import laiebei.terminal.dbm.dao.ChannelInfoDao;
import laiebei.terminal.dbm.dao.DaoSession;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/4/23 17:27.
 * 对外接口:
 */
@Entity
public class CabinetInfo {
	@Id
	private Long id;
	private String version;//单片机版本号
	private int gallery;//单片机通道数

	@ToMany(referencedJoinProperty = "cabinetId")
	private List<ChannelInfo> channelInfos;
	/** Used to resolve relations */
	@Generated(hash = 2040040024)
	private transient DaoSession daoSession;
	/** Used for active entity operations. */
	@Generated(hash = 787979874)
	private transient CabinetInfoDao myDao;

	@Generated(hash = 1897985119)
	public CabinetInfo(Long id, String version, int gallery) {
					this.id = id;
					this.version = version;
					this.gallery = gallery;
	}

	@Generated(hash = 1973192954)
	public CabinetInfo() {
	}

	public Long getId() {
					return this.id;
	}

	public void setId(Long id) {
					this.id = id;
	}

	public String getVersion() {
					return this.version;
	}

	public void setVersion(String version) {
					this.version = version;
	}

	public int getGallery() {
					return this.gallery;
	}

	public void setGallery(int gallery) {
					this.gallery = gallery;
	}

	/**
	 * To-many relationship, resolved on first access (and after reset).
	 * Changes to to-many relations are not persisted, make changes to the target entity.
	 */
	@Generated(hash = 1762063270)
	public List<ChannelInfo> getChannelInfos() {
					if (channelInfos == null) {
									final DaoSession daoSession = this.daoSession;
									if (daoSession == null) {
													throw new DaoException("Entity is detached from DAO context");
									}
									ChannelInfoDao targetDao = daoSession.getChannelInfoDao();
									List<ChannelInfo> channelInfosNew = targetDao
																	._queryCabinetInfo_ChannelInfos(id);
									synchronized (this) {
													if (channelInfos == null) {
																	channelInfos = channelInfosNew;
													}
									}
					}
					return channelInfos;
	}

	/** Resets a to-many relationship, making the next get call to query for a fresh result. */
	@Generated(hash = 1991896456)
	public synchronized void resetChannelInfos() {
					channelInfos = null;
	}

	/**
	 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
	 * Entity must attached to an entity context.
	 */
	@Generated(hash = 128553479)
	public void delete() {
					if (myDao == null) {
									throw new DaoException("Entity is detached from DAO context");
					}
					myDao.delete(this);
	}

	/**
	 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
	 * Entity must attached to an entity context.
	 */
	@Generated(hash = 1942392019)
	public void refresh() {
					if (myDao == null) {
									throw new DaoException("Entity is detached from DAO context");
					}
					myDao.refresh(this);
	}

	/**
	 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
	 * Entity must attached to an entity context.
	 */
	@Generated(hash = 713229351)
	public void update() {
					if (myDao == null) {
									throw new DaoException("Entity is detached from DAO context");
					}
					myDao.update(this);
	}

	/** called by internal mechanisms, do not call yourself. */
	@Generated(hash = 1285212740)
	public void __setDaoSession(DaoSession daoSession) {
					this.daoSession = daoSession;
					myDao = daoSession != null ? daoSession.getCabinetInfoDao() : null;
	}






}
