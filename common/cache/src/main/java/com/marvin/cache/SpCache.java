package com.marvin.cache;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashSet;
import java.util.Set;

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
public final class SpCache {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private SpCache() {
    }

    /**
     * @param context context
     * @param name    fileName
     */
    @SuppressLint("CommitPrefEdits")
    public synchronized static SpCache init(@NonNull Context context, @NonNull String name) {
        SpCache spCache = new SpCache();
        spCache.setSharedPreferences(context.getSharedPreferences(name, Context.MODE_PRIVATE));
        spCache.setEditor(spCache.getSharedPreferences().edit());
        return spCache;
    }

    public synchronized SharedPreferences getSP() {
        return mSharedPreferences;
    }

    public synchronized SharedPreferences.Editor getEditor() {
        return mEditor;
    }

    /**
     * 存储数据(Long)
     */
    public synchronized void putLongValue(@NonNull String key, long value) {
        mEditor.putLong(key, value).apply();
    }

    /**
     * 存储数据(Int)
     */
    public synchronized void putIntValue(@NonNull String key, int value) {
        mEditor.putInt(key, value).apply();
    }

    /**
     * 存储数据(String)
     */
    public synchronized void putStringValue(@NonNull String key, @NonNull String value) {
        mEditor.putString(key, value).apply();
    }

    /**
     * 存储数据(StringSet)
     */
    public synchronized void putStringSetValue(@NonNull String key, @NonNull Set<String> value) {
        HashSet<String> newValues = new HashSet<>(value);
        mEditor.putStringSet(key, newValues).apply();
    }

    /**
     * 存储数据(boolean)
     */
    public synchronized void putBooleanValue(@NonNull String key, boolean value) {
        mEditor.putBoolean(key, value).apply();
    }

    /**
     * 取出数据(Long)
     */
    public synchronized long getLongValue(@NonNull String key, long defValue) {
        return mSharedPreferences.getLong(key, defValue);
    }

    /**
     * 取出数据(int)
     */
    public synchronized int getIntValue(@NonNull String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }

    /**
     * 取出数据(boolean)
     */
    public synchronized boolean getBooleanValue(@NonNull String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }

    /**
     * 取出数据(String)
     */
    public synchronized String getStringValue(@NonNull String key, @NonNull String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }

    /**
     * 取出数据(StringSet)
     */
    public synchronized Set<String> getStringSetValue(@NonNull String key, @Nullable Set<String> defValue) {
        Set<String> values = mSharedPreferences.getStringSet(key, defValue);
        return new HashSet<>(values);
    }

    public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public void setEditor(SharedPreferences.Editor editor) {
        mEditor = editor;
    }

    /**
     * 清空所有数据
     */
    public synchronized void clear() {
        mEditor.clear().apply();
    }

    /**
     * 移除指定数据
     */
    public synchronized void remove(String key) {
        mEditor.remove(key).apply();
    }

}