package com.marvin.commonlibs.init;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * 无侵入初始化SDK
 */
public class AutoInjectProvider extends ContentProvider {

    private AppProxy mAppsProxy;
    private Application app;

    /**
     * 拓展思路
     * 1. 模块初始化是否可以懒加载；
     * 2. 模块初始化是否可以在子线程；
     * 3. 模块初始化后是否可以卸载；(动态装载、按需装载)
     *
     * @return = =
     */
    @Override
    public boolean onCreate() {
//        app = (Application) getContext();
//        mAppsProxy = new AppProxy(app);
//        //各模块间onCreate()事件分发
//        mAppsProxy.onCreate(app);
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }
}
