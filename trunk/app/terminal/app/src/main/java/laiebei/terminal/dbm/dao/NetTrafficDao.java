package laiebei.terminal.dbm.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import laiebei.terminal.dbm.domain.NetTraffic;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NET_TRAFFIC".
*/
public class NetTrafficDao extends AbstractDao<NetTraffic, Long> {

    public static final String TABLENAME = "NET_TRAFFIC";

    /**
     * Properties of entity NetTraffic.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Device = new Property(1, String.class, "device", false, "DEVICE");
        public final static Property BootTime = new Property(2, Long.class, "bootTime", false, "BOOT_TIME");
        public final static Property Duration = new Property(3, Long.class, "duration", false, "DURATION");
        public final static Property NowTime = new Property(4, Long.class, "nowTime", false, "NOW_TIME");
        public final static Property TotalFlow = new Property(5, Long.class, "totalFlow", false, "TOTAL_FLOW");
        public final static Property BusinessFlow = new Property(6, Long.class, "businessFlow", false, "BUSINESS_FLOW");
        public final static Property ImageFlow = new Property(7, Long.class, "imageFlow", false, "IMAGE_FLOW");
        public final static Property FileDownFlow = new Property(8, Long.class, "fileDownFlow", false, "FILE_DOWN_FLOW");
        public final static Property Describe = new Property(9, String.class, "describe", false, "DESCRIBE");
    }


    public NetTrafficDao(DaoConfig config) {
        super(config);
    }
    
    public NetTrafficDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NET_TRAFFIC\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"DEVICE\" TEXT NOT NULL ," + // 1: device
                "\"BOOT_TIME\" INTEGER NOT NULL ," + // 2: bootTime
                "\"DURATION\" INTEGER," + // 3: duration
                "\"NOW_TIME\" INTEGER NOT NULL ," + // 4: nowTime
                "\"TOTAL_FLOW\" INTEGER NOT NULL ," + // 5: totalFlow
                "\"BUSINESS_FLOW\" INTEGER," + // 6: businessFlow
                "\"IMAGE_FLOW\" INTEGER," + // 7: imageFlow
                "\"FILE_DOWN_FLOW\" INTEGER," + // 8: fileDownFlow
                "\"DESCRIBE\" TEXT);"); // 9: describe
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NET_TRAFFIC\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NetTraffic entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getDevice());
        stmt.bindLong(3, entity.getBootTime());
 
        Long duration = entity.getDuration();
        if (duration != null) {
            stmt.bindLong(4, duration);
        }
        stmt.bindLong(5, entity.getNowTime());
        stmt.bindLong(6, entity.getTotalFlow());
 
        Long businessFlow = entity.getBusinessFlow();
        if (businessFlow != null) {
            stmt.bindLong(7, businessFlow);
        }
 
        Long imageFlow = entity.getImageFlow();
        if (imageFlow != null) {
            stmt.bindLong(8, imageFlow);
        }
 
        Long fileDownFlow = entity.getFileDownFlow();
        if (fileDownFlow != null) {
            stmt.bindLong(9, fileDownFlow);
        }
 
        String describe = entity.getDescribe();
        if (describe != null) {
            stmt.bindString(10, describe);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NetTraffic entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getDevice());
        stmt.bindLong(3, entity.getBootTime());
 
        Long duration = entity.getDuration();
        if (duration != null) {
            stmt.bindLong(4, duration);
        }
        stmt.bindLong(5, entity.getNowTime());
        stmt.bindLong(6, entity.getTotalFlow());
 
        Long businessFlow = entity.getBusinessFlow();
        if (businessFlow != null) {
            stmt.bindLong(7, businessFlow);
        }
 
        Long imageFlow = entity.getImageFlow();
        if (imageFlow != null) {
            stmt.bindLong(8, imageFlow);
        }
 
        Long fileDownFlow = entity.getFileDownFlow();
        if (fileDownFlow != null) {
            stmt.bindLong(9, fileDownFlow);
        }
 
        String describe = entity.getDescribe();
        if (describe != null) {
            stmt.bindString(10, describe);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public NetTraffic readEntity(Cursor cursor, int offset) {
        NetTraffic entity = new NetTraffic( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // device
            cursor.getLong(offset + 2), // bootTime
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // duration
            cursor.getLong(offset + 4), // nowTime
            cursor.getLong(offset + 5), // totalFlow
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6), // businessFlow
            cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7), // imageFlow
            cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8), // fileDownFlow
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // describe
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NetTraffic entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDevice(cursor.getString(offset + 1));
        entity.setBootTime(cursor.getLong(offset + 2));
        entity.setDuration(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setNowTime(cursor.getLong(offset + 4));
        entity.setTotalFlow(cursor.getLong(offset + 5));
        entity.setBusinessFlow(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
        entity.setImageFlow(cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7));
        entity.setFileDownFlow(cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8));
        entity.setDescribe(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(NetTraffic entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(NetTraffic entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(NetTraffic entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}