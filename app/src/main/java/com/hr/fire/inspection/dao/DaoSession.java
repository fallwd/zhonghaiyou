package com.hr.fire.inspection.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.hr.fire.inspection.entity.CheckPerson;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.StandardInfo;
import com.hr.fire.inspection.entity.StandardType;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;

import com.hr.fire.inspection.dao.CheckPersonDao;
import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.CompanyInfoDao;
import com.hr.fire.inspection.dao.ItemInfoDao;
import com.hr.fire.inspection.dao.StandardInfoDao;
import com.hr.fire.inspection.dao.StandardTypeDao;
import com.hr.fire.inspection.dao.YearCheckDao;
import com.hr.fire.inspection.dao.YearCheckResultDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig checkPersonDaoConfig;
    private final DaoConfig checkTypeDaoConfig;
    private final DaoConfig companyInfoDaoConfig;
    private final DaoConfig itemInfoDaoConfig;
    private final DaoConfig standardInfoDaoConfig;
    private final DaoConfig standardTypeDaoConfig;
    private final DaoConfig yearCheckDaoConfig;
    private final DaoConfig yearCheckResultDaoConfig;

    private final CheckPersonDao checkPersonDao;
    private final CheckTypeDao checkTypeDao;
    private final CompanyInfoDao companyInfoDao;
    private final ItemInfoDao itemInfoDao;
    private final StandardInfoDao standardInfoDao;
    private final StandardTypeDao standardTypeDao;
    private final YearCheckDao yearCheckDao;
    private final YearCheckResultDao yearCheckResultDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        checkPersonDaoConfig = daoConfigMap.get(CheckPersonDao.class).clone();
        checkPersonDaoConfig.initIdentityScope(type);

        checkTypeDaoConfig = daoConfigMap.get(CheckTypeDao.class).clone();
        checkTypeDaoConfig.initIdentityScope(type);

        companyInfoDaoConfig = daoConfigMap.get(CompanyInfoDao.class).clone();
        companyInfoDaoConfig.initIdentityScope(type);

        itemInfoDaoConfig = daoConfigMap.get(ItemInfoDao.class).clone();
        itemInfoDaoConfig.initIdentityScope(type);

        standardInfoDaoConfig = daoConfigMap.get(StandardInfoDao.class).clone();
        standardInfoDaoConfig.initIdentityScope(type);

        standardTypeDaoConfig = daoConfigMap.get(StandardTypeDao.class).clone();
        standardTypeDaoConfig.initIdentityScope(type);

        yearCheckDaoConfig = daoConfigMap.get(YearCheckDao.class).clone();
        yearCheckDaoConfig.initIdentityScope(type);

        yearCheckResultDaoConfig = daoConfigMap.get(YearCheckResultDao.class).clone();
        yearCheckResultDaoConfig.initIdentityScope(type);

        checkPersonDao = new CheckPersonDao(checkPersonDaoConfig, this);
        checkTypeDao = new CheckTypeDao(checkTypeDaoConfig, this);
        companyInfoDao = new CompanyInfoDao(companyInfoDaoConfig, this);
        itemInfoDao = new ItemInfoDao(itemInfoDaoConfig, this);
        standardInfoDao = new StandardInfoDao(standardInfoDaoConfig, this);
        standardTypeDao = new StandardTypeDao(standardTypeDaoConfig, this);
        yearCheckDao = new YearCheckDao(yearCheckDaoConfig, this);
        yearCheckResultDao = new YearCheckResultDao(yearCheckResultDaoConfig, this);

        registerDao(CheckPerson.class, checkPersonDao);
        registerDao(CheckType.class, checkTypeDao);
        registerDao(CompanyInfo.class, companyInfoDao);
        registerDao(ItemInfo.class, itemInfoDao);
        registerDao(StandardInfo.class, standardInfoDao);
        registerDao(StandardType.class, standardTypeDao);
        registerDao(YearCheck.class, yearCheckDao);
        registerDao(YearCheckResult.class, yearCheckResultDao);
    }
    
    public void clear() {
        checkPersonDaoConfig.clearIdentityScope();
        checkTypeDaoConfig.clearIdentityScope();
        companyInfoDaoConfig.clearIdentityScope();
        itemInfoDaoConfig.clearIdentityScope();
        standardInfoDaoConfig.clearIdentityScope();
        standardTypeDaoConfig.clearIdentityScope();
        yearCheckDaoConfig.clearIdentityScope();
        yearCheckResultDaoConfig.clearIdentityScope();
    }

    public CheckPersonDao getCheckPersonDao() {
        return checkPersonDao;
    }

    public CheckTypeDao getCheckTypeDao() {
        return checkTypeDao;
    }

    public CompanyInfoDao getCompanyInfoDao() {
        return companyInfoDao;
    }

    public ItemInfoDao getItemInfoDao() {
        return itemInfoDao;
    }

    public StandardInfoDao getStandardInfoDao() {
        return standardInfoDao;
    }

    public StandardTypeDao getStandardTypeDao() {
        return standardTypeDao;
    }

    public YearCheckDao getYearCheckDao() {
        return yearCheckDao;
    }

    public YearCheckResultDao getYearCheckResultDao() {
        return yearCheckResultDao;
    }

}
