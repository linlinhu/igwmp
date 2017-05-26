package laiebei.terminal.dbm.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import laiebei.terminal.dbm.domain.VentoutOrder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "VENTOUT_ORDER".
*/
public class VentoutOrderDao extends AbstractDao<VentoutOrder, Long> {

    public static final String TABLENAME = "VENTOUT_ORDER";

    /**
     * Properties of entity VentoutOrder.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property OrderId = new Property(1, String.class, "orderId", false, "ORDER_ID");
        public final static Property ProductId = new Property(2, Long.class, "productId", false, "PRODUCT_ID");
        public final static Property CreatTime = new Property(3, Long.class, "creatTime", false, "CREAT_TIME");
        public final static Property Channel = new Property(4, int.class, "channel", false, "CHANNEL");
        public final static Property Value = new Property(5, int.class, "value", false, "VALUE");
        public final static Property Status = new Property(6, int.class, "status", false, "STATUS");
        public final static Property ResultCode = new Property(7, int.class, "resultCode", false, "RESULT_CODE");
        public final static Property IsReport = new Property(8, boolean.class, "isReport", false, "IS_REPORT");
    }


    public VentoutOrderDao(DaoConfig config) {
        super(config);
    }
    
    public VentoutOrderDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"VENTOUT_ORDER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ORDER_ID\" TEXT NOT NULL ," + // 1: orderId
                "\"PRODUCT_ID\" INTEGER," + // 2: productId
                "\"CREAT_TIME\" INTEGER," + // 3: creatTime
                "\"CHANNEL\" INTEGER NOT NULL ," + // 4: channel
                "\"VALUE\" INTEGER NOT NULL ," + // 5: value
                "\"STATUS\" INTEGER NOT NULL ," + // 6: status
                "\"RESULT_CODE\" INTEGER NOT NULL ," + // 7: resultCode
                "\"IS_REPORT\" INTEGER NOT NULL );"); // 8: isReport
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"VENTOUT_ORDER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, VentoutOrder entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getOrderId());
 
        Long productId = entity.getProductId();
        if (productId != null) {
            stmt.bindLong(3, productId);
        }
 
        Long creatTime = entity.getCreatTime();
        if (creatTime != null) {
            stmt.bindLong(4, creatTime);
        }
        stmt.bindLong(5, entity.getChannel());
        stmt.bindLong(6, entity.getValue());
        stmt.bindLong(7, entity.getStatus());
        stmt.bindLong(8, entity.getResultCode());
        stmt.bindLong(9, entity.getIsReport() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, VentoutOrder entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getOrderId());
 
        Long productId = entity.getProductId();
        if (productId != null) {
            stmt.bindLong(3, productId);
        }
 
        Long creatTime = entity.getCreatTime();
        if (creatTime != null) {
            stmt.bindLong(4, creatTime);
        }
        stmt.bindLong(5, entity.getChannel());
        stmt.bindLong(6, entity.getValue());
        stmt.bindLong(7, entity.getStatus());
        stmt.bindLong(8, entity.getResultCode());
        stmt.bindLong(9, entity.getIsReport() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public VentoutOrder readEntity(Cursor cursor, int offset) {
        VentoutOrder entity = new VentoutOrder( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // orderId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // productId
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // creatTime
            cursor.getInt(offset + 4), // channel
            cursor.getInt(offset + 5), // value
            cursor.getInt(offset + 6), // status
            cursor.getInt(offset + 7), // resultCode
            cursor.getShort(offset + 8) != 0 // isReport
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, VentoutOrder entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setOrderId(cursor.getString(offset + 1));
        entity.setProductId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setCreatTime(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setChannel(cursor.getInt(offset + 4));
        entity.setValue(cursor.getInt(offset + 5));
        entity.setStatus(cursor.getInt(offset + 6));
        entity.setResultCode(cursor.getInt(offset + 7));
        entity.setIsReport(cursor.getShort(offset + 8) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(VentoutOrder entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(VentoutOrder entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(VentoutOrder entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}