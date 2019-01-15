package com.marvin.cache;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

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
public interface Cache {

    int TIME_SECOND = 1;
    int TIME_MINUTE = TIME_SECOND * 60;
    int TIME_HOUR = TIME_MINUTE * 60;
    int TIME_DAY = TIME_HOUR * 24;
    int TIME_MONTH = TIME_DAY * 30;

    //---------------put-------------------

    default void putBitmap(@NonNull String key, @NonNull Bitmap bitmap) {

    }

    default void putString(@NonNull String key, @NonNull String value) {

    }

    default void putSerializable(@NonNull String key, @NonNull Serializable value) {

    }

    default void putDrawable(@NonNull String key, @NonNull Drawable value) {

    }

    default void putBytes(@NonNull String key, @NonNull byte[] value) {

    }

    default <T> void putArrayList(@NonNull String key, @NonNull ArrayList<T> value) {

    }

    void put(@NonNull String key, @NonNull Object value);

    default void put(@NonNull String key, @NonNull Object value, MemoryLruCache.Function<String, Object> function) {
    }

    //---------------get-------------------

    default String getString(@NonNull String key) throws ClassCastException {
        return null;
    }

    default Bitmap getBitmap(@NonNull String key) throws ClassCastException {
        return null;
    }

    default Drawable getDrawable(@NonNull String key) throws ClassCastException {
        return null;
    }

    default byte[] getBytes(@NonNull String key) throws ClassCastException {
        return null;
    }

    default <T> T getSerializable(@NonNull String key) throws ClassCastException {
        return null;
    }

    default <T> ArrayList<T> getArrayList(@NonNull String key) throws ClassCastException {
        return null;
    }

    <T> T get(@NonNull String key) throws ClassCastException;
}
