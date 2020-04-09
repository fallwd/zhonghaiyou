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
<<<<<<< HEAD
import com.hr.fire.inspection.entity.StandardInfo;
import com.hr.fire.inspection.entity.StandardType;
=======
>>>>>>> d88805a10bf5aeb2eb8f3466adf58ca12cc4a4ce

import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.ItemInfoDao;
import com.hr.fire.inspection.dao.YearCheckDao;
import com.hr.fire.inspection.dao.YearCheckResultDao;
<<<<<<< HEAD
import com.hr.fire.inspection.dao.StandardInfoDao;
import com.hr.fire.inspection.dao.StandardTypeDao;
=======
>>>>>>> d88805a10bf5aeb2eb8f3466adf58ca12cc4a4ce

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
<<<<<<< HEAD
    private final DaoConfig standardInfoDaoConfig;
    private final DaoConfig standardTypeDaoConfig;
=======
>>>>>>> d88805a10bf5aeb2eb8f3466adf58ca12cc4a4ce

    private final CheckTypeDao checkTypeDao;
    private final ItemInfoDao itemInfoDao;
    private final YearCheckDao yearCheckDao;
    private final YearCheckResultDao yearCheckResultDao;
<<<<<<< HEAD
    private final StandardInfoDao standardInfoDao;
    private final StandardTypeDao standardTypeDao;
=======
>>>>>>> d88805a10bf5aeb2eb8f3466adf58ca12cc4a4ce

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

<<<<<<< HEAD
        standardInfoDaoConfig = daoConfigMap.get(StandardInfoDao.class).clone();
        standardInfoDaoConfig.initIdentityScope(type);

        standardTypeDaoConfig = daoConfigMap.get(StandardTypeDao.class).clone();
        standardTypeDaoConfig.initIdentityScope(type);

=======
>>>>>>> d88805a10bf5aeb2eb8f3466adf58ca12cc4a4ce
        checkTypeDao = new CheckTypeDao(checkTypeDaoConfig, this);
        itemInfoDao = new ItemInfoDao(itemInfoDaoConfig, this);
        yearCheckDao = new YearCheckDao(yearCheckDaoConfig, this);
        yearCheckResultDao = new YearCheckResultDao(yearCheckResultDaoConfig, this);
<<<<<<< HEAD
        standardInfoDao = new StandardInfoDao(standardInfoDaoConfig, this);
        standardTypeDao = new StandardTypeDao(standardTypeDaoConfig, this);
=======
>>>>>>> d88805a10bf5aeb2eb8f3466adf58ca12cc4a4ce

        registerDao(CheckType.class, checkTypeDao);
        registerDao(ItemInfo.class, itemInfoDao);
        registerDao(YearCheck.class, yearCheckDao);
        registerDao(YearCheckResult.class, yearCheckResultDao);
<<<<<<< HEAD
        registerDao(StandardInfo.class, standardInfoDao);
        registerDao(StandardType.class, standardTypeDao);
=======
>>>>>>> d88805a10bf5aeb2eb8f3466adf58ca12cc4a4ce
    }
    
    public void clear() {
        checkTypeDaoConfig.clearIdentityScope();
        itemInfoDaoConfig.clearIdentityScope();
        yearCheckDaoConfig.clearIdentityScope();
        yearCheckResultDaoConfig.clearIdentityScope();
<<<<<<< HEAD
        standardInfoDaoConfig.clearIdentityScope();
        standardTypeDaoConfig.clearIdentityScope();
=======
>>>>>>> d88805a10bf5aeb2eb8f3466adf58ca12cc4a4ce
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

<<<<<<< HEAD
    public StandardInfoDao getStandardInfoDao() {
        return standardInfoDao;
    }

    public StandardTypeDao getStandardTypeDao() {
        return standardTypeDao;
    }

=======
>>>>>>> d88805a10bf5aeb2eb8f3466adf58ca12cc4a4ce
}
