package com.marvin.tools;

import android.content.Context;

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
 * Created by marvin on 2018/12/18
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";
    private static Context sContext;
    private Thread.UncaughtExceptionHandler defaultCrashHandler;

    public static void init(Context context) {
        sContext = context;
        Thread.setDefaultUncaughtExceptionHandler(getInstance());
    }

    private CrashHandler() {
        this.defaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    public static CrashHandler getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (!handleCrash(t, e) && defaultCrashHandler != null) {
            defaultCrashHandler.uncaughtException(t, e);
        } else {
            //todo do some thing
            LogUtils.e(e);
        }
    }

    private boolean handleCrash(Thread t, Throwable e) {
        LogUtils.e(e);
        return false;
    }

    private static class SingletonHolder {
        private static final CrashHandler INSTANCE = new CrashHandler();
    }
}
