package com.marvin.commonlibs;

import android.app.Application;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.multidex.MultiDex;

import com.marvin.commonlibs.init.AppProxy;
import com.marvin.commonlibs.init.AutoInjectProvider;
import com.marvin.tools.CrashHandler;
import com.marvin.tools.ThreadUtils;
import com.marvin.tools.ToastUtils;


/**
 * ┏┓　   ┏┓
 * ┏┛┻━━━━━┛┻━┓
 * ┃　　　　   ┃
 * ┃　━　━　   ┃
 * ████━████   ┃
 * ┃　　　　   ┃
 * ┃　 ┻　    ┃
 * ┗━┓      ┏━┛
 * 　┃      ┃
 * 　┃ 0BUG ┗━━━┓
 * 　┃0Error     ┣┓
 * 　┃0Warning   ┏┛
 * 　┗┓┓┏━┳┓┏┛ ━
 * 　　┃┫┫ ┃┫┫
 * 　　┗┻┛ ┗┻┛
 * Created by marvin on 2018/11/15
 */
public class AutoInjectApp extends Application {

    private static AutoInjectApp instance;

    private AppProxy mAppsProxy;
    private Application app;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        instance = this;

    }

    /**
     * module初始化 {@link AutoInjectProvider#onCreate()}
     * <p>
     * 顶级App初始化相关SDK 请复写
     * {@link #initInMainProcess(Application)}
     * {@link #initInOtherProcess(Application)}
     */
    @Override
    public void onCreate() {
        super.onCreate();
        if (ThreadUtils.isMainProcess(this)) {
            initInMainProcess(this);
        } else {
            initInOtherProcess(this);
        }
    }

    @CallSuper
    protected void initInOtherProcess(Application application) {
    }

    @CallSuper
    protected void initInMainProcess(Application application) {
        mAppsProxy = new AppProxy(application);
        mAppsProxy.onCreate(application);
        CrashHandler.init(application);
        ToastUtils.init(this, true);
    }

    public static AutoInjectApp getInstance() {
        return instance;
    }
}
