package com.cooloongwu.coolchat.utils;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.cooloongwu.coolchat.base.AppConfig;
import com.cooloongwu.greendao.gen.ChatDao;
import com.cooloongwu.greendao.gen.ContactDao;
import com.cooloongwu.greendao.gen.ConversationDao;
import com.cooloongwu.greendao.gen.DaoMaster;
import com.cooloongwu.greendao.gen.DaoSession;
import com.cooloongwu.greendao.gen.GroupDao;

/**
 * 操作GreenDAO的类
 * Created by CooLoongWu on 2016-9-28 14:25.
 */

public class GreenDAOUtils {

    private static GreenDAOUtils instance;
    private DaoMaster.DevOpenHelper devOpenHelper;

    public static GreenDAOUtils getInstance(Context context) {
        if (instance == null) {
            instance = new GreenDAOUtils(context);
        }
        return instance;
    }

    public GreenDAOUtils(Context context) {
        String dataBaseName = AppConfig.getUserDB(context);
        LogUtils.e("加载的用户的数据库：" + dataBaseName);
        devOpenHelper = new DaoMaster.DevOpenHelper(context, dataBaseName, null);
    }

    private DaoSession getDaoSession() {
        return new DaoMaster(devOpenHelper.getWritableDb()).newSession();
    }

    public ContactDao getContactDao() {
        return getDaoSession().getContactDao();
    }

    public GroupDao getGroupDao() {
        return getDaoSession().getGroupDao();
    }

    public ConversationDao getConversationDao() {
        return getDaoSession().getConversationDao();
    }

    public ChatDao getChatDao() {
        return getDaoSession().getChatDao();
    }

    public void clearAllData() {
        getContactDao().deleteAll();
        getGroupDao().deleteAll();
        getConversationDao().deleteAll();
        getChatDao().deleteAll();
    }
}
