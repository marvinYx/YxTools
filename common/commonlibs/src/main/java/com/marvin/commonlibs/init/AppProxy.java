package com.marvin.commonlibs.init;

import android.app.Application;
import android.content.Context;

import com.marvin.jetpack.lifecycle.AppLifeCycle;

import java.util.ArrayList;
import java.util.List;

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
public class AppProxy extends AppLifeCycle {

    private List<AppLifeCycle> mAppLives;

    public AppProxy(Context context) {
        mAppLives = new ArrayList<>();
        mAppLives.addAll(ManifestParser.parse(context));
    }

    /**
     * @param context baseContext
     */
    @Override
    public void attachBaseContext(Context context) {
        if (mAppLives != null && mAppLives.size() > 0) {
            for (AppLifeCycle appLife : mAppLives) {
                appLife.attachBaseContext(context);
            }
        }
    }

    @Override
    public void onCreate(Application application) {
        if (mAppLives != null && mAppLives.size() > 0) {
            for (AppLifeCycle appLife : mAppLives) {
                appLife.onCreate(application);
            }
        }
    }

    @Override
    public void onTerminate(Application application) {
        if (mAppLives != null && mAppLives.size() > 0) {
            for (AppLifeCycle appLife : mAppLives) {
                appLife.onTerminate(application);
            }
        }
    }
}
