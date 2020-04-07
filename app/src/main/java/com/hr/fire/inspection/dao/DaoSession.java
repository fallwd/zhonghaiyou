package com.hr.fire.inspection.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;

import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.ItemInfoDao;
import com.hr.fire.inspection.dao.YearCheckDao;
import com.hr.fire.inspection.dao.YearCheckResultDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig checkTypeDaoConfig;
    private final DaoConfig itemInfoDaoConfig;
    private final DaoConfig yearCheckDaoConfig;
    private final DaoConfig yearCheckResultDaoConfig;

    private final CheckTypeDao checkTypeDao;
    private final ItemInfoDao itemInfoDao;
    private final YearCheckDao yearCheckDao;
    private final YearCheckResultDao yearCheckResultDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        checkTypeDaoConfig = daoConfigMap.get(CheckTypeDao.class).clone();
        checkTypeDaoConfig.initIdentityScope(type);

        itemInfoDaoConfig = daoConfigMap.get(ItemInfoDao.class).clone();
        itemInfoDaoConfig.initIdentityScope(type);

        yearCheckDaoConfig = daoConfigMap.get(YearCheckDao.class).clone();
        yearCheckDaoConfig.initIdentityScope(type);

        yearCheckResultDaoConfig = daoConfigMap.get(YearCheckResultDao.class).clone();
        yearCheckResultDaoConfig.initIdentityScope(type);

        checkTypeDao = new CheckTypeDao(checkTypeDaoConfig, this);
        itemInfoDao = new ItemInfoDao(itemInfoDaoConfig, this);
        yearCheckDao = new YearCheckDao(yearCheckDaoConfig, this);
        yearCheckResultDao = new YearCheckResultDao(yearCheckResultDaoConfig, this);

        registerDao(CheckType.class, checkTypeDao);
        registerDao(ItemInfo.class, itemInfoDao);
        registerDao(YearCheck.class, yearCheckDao);
        registerDao(YearCheckResult.class, yearCheckResultDao);
    }
    
    public void clear() {
        checkTypeDaoConfig.clearIdentityScope();
        itemInfoDaoConfig.clearIdentityScope();
        yearCheckDaoConfig.clearIdentityScope();
        yearCheckResultDaoConfig.clearIdentityScope();
    }

    public CheckTypeDao getCheckTypeDao() {
        return checkTypeDao;
    }

    public ItemInfoDao getItemInfoDao() {
        return itemInfoDao;
    }

    public YearCheckDao getYearCheckDao() {
        return yearCheckDao;
    }

    public YearCheckResultDao getYearCheckResultDao() {
        return yearCheckResultDao;
    }

}
