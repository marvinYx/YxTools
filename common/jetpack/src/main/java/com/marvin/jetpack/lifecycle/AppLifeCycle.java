package com.marvin.jetpack.lifecycle;

import android.app.Application;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

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
public class AppLifeCycle {

    public void attachBaseContext(Context context) {

    }

    public void onCreate(Application application) {

    }

    /**
     * 模拟测试使用,勿依赖此函数；
     * 系统不一定会调用
     *
     * @param application
     */
    @VisibleForTesting
    public void onTerminate(Application application) {
    }

}
