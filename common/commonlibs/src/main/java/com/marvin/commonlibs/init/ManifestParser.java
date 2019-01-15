package com.marvin.commonlibs.init;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.marvin.jetpack.lifecycle.AppLifeCycle;
import com.marvin.tools.LogUtils;

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
public class ManifestParser {
    public static final String MODULE_VALUE = "ModuleApp";

    public static List<AppLifeCycle> parse(Context context) {
        List<AppLifeCycle> modules = new ArrayList<>();
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);

            if (appInfo.metaData != null) {
                for (String key : appInfo.metaData.keySet()) {
                    if (MODULE_VALUE.equals(appInfo.metaData.get(key))) {
                        AppLifeCycle mApp = NewInstance(key);
                        if (mApp == null) {
                            throw new NullPointerException(key + ":object == null");
                        } else {
                            modules.add(mApp);
                        }
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("APP解析失败", e);
        }
        return modules;
    }

    private static AppLifeCycle NewInstance(String className) {
        Class<?> aClass;
        Object object = null;
        try {
            aClass = Class.forName(className);
            object = aClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            LogUtils.e(e);
        }
        return (AppLifeCycle) object;
    }
}
