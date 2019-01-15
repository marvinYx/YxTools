package com.marvin.cache;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;

import java.util.HashMap;

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
public class MemoryLruCache<K, V> extends LruCache<K, V> {
    private HashMap<K, Function<K, V>> acquires;
    private boolean autoRecover = false;

    public MemoryLruCache(int maxSize) {
        super(maxSize);
        acquires = new HashMap<>();
    }

    /**
     * @param maxSize  缓存大小
     * @param function 恢复回调
     */
    public MemoryLruCache(int maxSize, Function<K, V> function) {
        super(maxSize);
    }

    @Nullable
    public V getData(@NonNull K key) {
        return get(key);
    }

    public MemoryLruCache<K, V> setData(@NonNull K key, @NonNull V value) {
        put(key, value);
        return this;
    }

    public void clearData() {
        evictAll();
    }

    public MemoryLruCache<K, V> addAcquire(K key, Function<K, V> acquire) {
        acquires.put(key, acquire);
        return this;
    }

    @Override
    protected void entryRemoved(boolean evicted, @NonNull K key, @NonNull V oldValue, @Nullable V newValue) {
    }

    /**
     * {@link #get(Object)} 数据的时候，如果获取不到数据
     * 此方法会被调用，可以用作数据恢复函数
     *
     * @param key 键
     * @return 值
     */
    @Nullable
    @Override
    protected V create(@NonNull K key) {
        Function<K, V> kvFunction = acquires.get(key);
        if (kvFunction != null) {
            return kvFunction.apply(key);
        }
        return null;
    }

    @Override
    protected int sizeOf(@NonNull K key, @NonNull V value) {
        return super.sizeOf(key, value);
    }

    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t);
    }
}
