package com.hr.fire.inspection.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.YearCheck;

import com.hr.fire.inspection.entity.YearCheckResult;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "t_year_check_result".
*/
public class YearCheckResultDao extends AbstractDao<YearCheckResult, Long> {

    public static final String TABLENAME = "t_year_check_result";

    /**
     * Properties of entity YearCheckResult.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property YearCheckId = new Property(1, Long.class, "yearCheckId", false, "YEAR_CHECK_ID");
        public final static Property IsPass = new Property(2, String.class, "isPass", false, "IS_PASS");
        public final static Property ImageUrl = new Property(3, String.class, "imageUrl", false, "IMAGE_URL");
        public final static Property VideoUrl = new Property(4, String.class, "videoUrl", false, "VIDEO_URL");
        public final static Property Descrption = new Property(5, String.class, "descrption", false, "DESCRPTION");
        public final static Property BottleInfoId = new Property(6, Long.class, "bottleInfoId", false, "BOTTLE_INFO_ID");
        public final static Property TargetId = new Property(7, Long.class, "targetId", false, "TARGET_ID");
    }

    private DaoSession daoSession;

    private Query<YearCheckResult> itemInfo_CheckResultListQuery;

    public YearCheckResultDao(DaoConfig config) {
        super(config);
    }
    
    public YearCheckResultDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"t_year_check_result\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"YEAR_CHECK_ID\" INTEGER," + // 1: yearCheckId
                "\"IS_PASS\" TEXT," + // 2: isPass
                "\"IMAGE_URL\" TEXT," + // 3: imageUrl
                "\"VIDEO_URL\" TEXT," + // 4: videoUrl
                "\"DESCRPTION\" TEXT," + // 5: descrption
                "\"BOTTLE_INFO_ID\" INTEGER," + // 6: bottleInfoId
                "\"TARGET_ID\" INTEGER);"); // 7: targetId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"t_year_check_result\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, YearCheckResult entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long yearCheckId = entity.getYearCheckId();
        if (yearCheckId != null) {
            stmt.bindLong(2, yearCheckId);
        }
 
        String isPass = entity.getIsPass();
        if (isPass != null) {
            stmt.bindString(3, isPass);
        }
 
        String imageUrl = entity.getImageUrl();
        if (imageUrl != null) {
            stmt.bindString(4, imageUrl);
        }
 
        String videoUrl = entity.getVideoUrl();
        if (videoUrl != null) {
            stmt.bindString(5, videoUrl);
        }
 
        String descrption = entity.getDescrption();
        if (descrption != null) {
            stmt.bindString(6, descrption);
        }
 
        Long bottleInfoId = entity.getBottleInfoId();
        if (bottleInfoId != null) {
            stmt.bindLong(7, bottleInfoId);
        }
 
        Long targetId = entity.getTargetId();
        if (targetId != null) {
            stmt.bindLong(8, targetId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, YearCheckResult entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long yearCheckId = entity.getYearCheckId();
        if (yearCheckId != null) {
            stmt.bindLong(2, yearCheckId);
        }
 
        String isPass = entity.getIsPass();
        if (isPass != null) {
            stmt.bindString(3, isPass);
        }
 
        String imageUrl = entity.getImageUrl();
        if (imageUrl != null) {
            stmt.bindString(4, imageUrl);
        }
 
        String videoUrl = entity.getVideoUrl();
        if (videoUrl != null) {
            stmt.bindString(5, videoUrl);
        }
 
        String descrption = entity.getDescrption();
        if (descrption != null) {
            stmt.bindString(6, descrption);
        }
 
        Long bottleInfoId = entity.getBottleInfoId();
        if (bottleInfoId != null) {
            stmt.bindLong(7, bottleInfoId);
        }
 
        Long targetId = entity.getTargetId();
        if (targetId != null) {
            stmt.bindLong(8, targetId);
        }
    }

    @Override
    protected final void attachEntity(YearCheckResult entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public YearCheckResult readEntity(Cursor cursor, int offset) {
        YearCheckResult entity = new YearCheckResult( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // yearCheckId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // isPass
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // imageUrl
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // videoUrl
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // descrption
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6), // bottleInfoId
            cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7) // targetId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, YearCheckResult entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setYearCheckId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setIsPass(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setImageUrl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setVideoUrl(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDescrption(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setBottleInfoId(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
        entity.setTargetId(cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(YearCheckResult entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(YearCheckResult entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(YearCheckResult entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "checkResultList" to-many relationship of ItemInfo. */
    public List<YearCheckResult> _queryItemInfo_CheckResultList(Long targetId) {
        synchronized (this) {
            if (itemInfo_CheckResultListQuery == null) {
                QueryBuilder<YearCheckResult> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.TargetId.eq(null));
                itemInfo_CheckResultListQuery = queryBuilder.build();
            }
        }
        Query<YearCheckResult> query = itemInfo_CheckResultListQuery.forCurrentThread();
        query.setParameter(0, targetId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getYearCheckDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getItemInfoDao().getAllColumns());
            builder.append(" FROM t_year_check_result T");
            builder.append(" LEFT JOIN t_year_check T0 ON T.\"YEAR_CHECK_ID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN t_item_info T1 ON T.\"BOTTLE_INFO_ID\"=T1.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected YearCheckResult loadCurrentDeep(Cursor cursor, boolean lock) {
        YearCheckResult entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        YearCheck yearCheck = loadCurrentOther(daoSession.getYearCheckDao(), cursor, offset);
        entity.setYearCheck(yearCheck);
        offset += daoSession.getYearCheckDao().getAllColumns().length;

        ItemInfo itemInfo = loadCurrentOther(daoSession.getItemInfoDao(), cursor, offset);
        entity.setItemInfo(itemInfo);

        return entity;    
    }

    public YearCheckResult loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<YearCheckResult> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<YearCheckResult> list = new ArrayList<YearCheckResult>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<YearCheckResult> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<YearCheckResult> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
