package com.fenboshi.fboshi.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.fenboshi.fboshi.bean.ArticelBean;
import com.fenboshi.fboshi.bean.HomeBanner;
import com.fenboshi.fboshi.bean.UserBean;
import com.fenboshi.fboshi.db.AppInfo;

import com.fenboshi.fboshi.greendao.gen.ArticelBeanDao;
import com.fenboshi.fboshi.greendao.gen.HomeBannerDao;
import com.fenboshi.fboshi.greendao.gen.UserBeanDao;
import com.fenboshi.fboshi.greendao.gen.AppInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig articelBeanDaoConfig;
    private final DaoConfig homeBannerDaoConfig;
    private final DaoConfig userBeanDaoConfig;
    private final DaoConfig appInfoDaoConfig;

    private final ArticelBeanDao articelBeanDao;
    private final HomeBannerDao homeBannerDao;
    private final UserBeanDao userBeanDao;
    private final AppInfoDao appInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        articelBeanDaoConfig = daoConfigMap.get(ArticelBeanDao.class).clone();
        articelBeanDaoConfig.initIdentityScope(type);

        homeBannerDaoConfig = daoConfigMap.get(HomeBannerDao.class).clone();
        homeBannerDaoConfig.initIdentityScope(type);

        userBeanDaoConfig = daoConfigMap.get(UserBeanDao.class).clone();
        userBeanDaoConfig.initIdentityScope(type);

        appInfoDaoConfig = daoConfigMap.get(AppInfoDao.class).clone();
        appInfoDaoConfig.initIdentityScope(type);

        articelBeanDao = new ArticelBeanDao(articelBeanDaoConfig, this);
        homeBannerDao = new HomeBannerDao(homeBannerDaoConfig, this);
        userBeanDao = new UserBeanDao(userBeanDaoConfig, this);
        appInfoDao = new AppInfoDao(appInfoDaoConfig, this);

        registerDao(ArticelBean.class, articelBeanDao);
        registerDao(HomeBanner.class, homeBannerDao);
        registerDao(UserBean.class, userBeanDao);
        registerDao(AppInfo.class, appInfoDao);
    }
    
    public void clear() {
        articelBeanDaoConfig.clearIdentityScope();
        homeBannerDaoConfig.clearIdentityScope();
        userBeanDaoConfig.clearIdentityScope();
        appInfoDaoConfig.clearIdentityScope();
    }

    public ArticelBeanDao getArticelBeanDao() {
        return articelBeanDao;
    }

    public HomeBannerDao getHomeBannerDao() {
        return homeBannerDao;
    }

    public UserBeanDao getUserBeanDao() {
        return userBeanDao;
    }

    public AppInfoDao getAppInfoDao() {
        return appInfoDao;
    }

}
