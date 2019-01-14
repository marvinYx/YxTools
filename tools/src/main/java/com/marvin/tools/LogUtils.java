package com.marvin.tools;

import android.support.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

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
 * Created by marvin on 2018/12/14
 */
public class LogUtils {

    static {
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    public static void d(Object msg) {
        Logger.d(msg);
    }

    public static void e(String msg) {
        Logger.e(msg);
    }

    public static void e(Throwable throwable) {
        e(throwable, "");
    }

    public static void e(Throwable throwable, String msg) {
        Logger.e(throwable, msg);

    }

    public static void w(String msg) {
        Logger.w(msg);
    }

    public static void v(String msg) {
        Logger.v(msg);
    }

    public static void i(String msg) {
        Logger.i(msg);
    }

    public static void wtf(String msg) {
        Logger.wtf(msg);
    }

    public static void json(String msg) {
        Logger.json(msg);
    }

    public static void xml(String msg) {
        Logger.xml(msg);
    }

}
