package com.marvin.cache;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static com.marvin.tools.CheckUtils.isNull;
import static com.marvin.tools.CheckUtils.nonNull;
import static com.marvin.tools.CheckUtils.requireNonNull;
import static com.marvin.tools.CheckUtils.requireNonNullOrEmpty;

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
public final class CacheManager {

    public static final int SP = 1;
    public static final int DISK = 2;
    public static final int MEMORY = 3;

    private CacheManager() {
    }

    public static CacheManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DISK, MEMORY, SP})
    public @interface CacheType {
    }

    private static final int DEFAULT_MEMORY_SIZE = 256;
    private static final MemoryLruCache<String, Object> memoryLruCache;
    private static final HashMap<String, Long> keepAliveTimes;
    private static final long TIME_FOREVER = -1;

    static {
        memoryLruCache = new MemoryLruCache<>(DEFAULT_MEMORY_SIZE);
        keepAliveTimes = new HashMap<>();
    }

    public static MemoryLruCache<String, Object> memory() {
        return memoryLruCache;
    }


    public <T> void putInMomory(@NonNull String key, @NonNull T value) {
        put(MEMORY, key, value, 0, TimeUnit.SECONDS);
    }

    public <T> void putInDisk(@NonNull String key, @NonNull T value) {
        put(DISK, key, value, 0, TimeUnit.SECONDS);
    }

    public <T> void putInSP(@NonNull String key, @NonNull T value) {
        put(SP, key, value, 0, TimeUnit.SECONDS);
    }

    public <T> void put(@CacheType int cacheType, @NonNull String key, @NonNull T value) {
        put(cacheType, key, value, 0, TimeUnit.SECONDS);
    }

    public <T> void put(@CacheType int cacheType, @NonNull String key, @NonNull T value, long keepAliveTime, @NonNull TimeUnit unit) {
        if (keepAliveTime < 0) {
            throw new IllegalArgumentException();
        }
        key = requireNonNullOrEmpty(key);
        value = requireNonNull(value);
        long overTime = (keepAliveTime == 0 ? TIME_FOREVER : System.currentTimeMillis() + unit.toSeconds(keepAliveTime));
        keepAliveTime(cacheType, key, overTime, unit);
        storeCacheData(cacheType, key, value);
    }

    private <T> void storeCacheData(int cacheType, String key, T value) {
        switch (cacheType) {
            case MEMORY:
                memoryLruCache.setData(key, value);
                break;
            case SP:

                break;
            case DISK:

                break;

            default:

                break;
        }
    }

    private void keepAliveTime(int cacheType, String key, long overTime, TimeUnit unit) {
        long currentTimeMillis = System.currentTimeMillis();
        keepAliveTimes.put(key, currentTimeMillis + unit.toSeconds(overTime));
        switch (cacheType) {
            case MEMORY:

                break;
            case SP:

                break;
            case DISK:

                break;

            default:

                break;
        }

    }

    public <T> T get(@NonNull String key) {
        Object data = memoryLruCache.getData(key);
        Long aliveTimes = keepAliveTimes.get(key);
        long currentTimeMillis = System.currentTimeMillis();
        if (nonNull(data) && (isNull(aliveTimes) || aliveTimes == TIME_FOREVER || aliveTimes < currentTimeMillis)) {
            return (T) data;
        }

        //todo get from diskCache
        return null;
    }

    public <T> T get(@NonNull String key, @CacheType int cacheType) {
        switch (cacheType) {
            case MEMORY:

                break;
            case SP:

                break;
            case DISK:

                break;

            default:

                break;
        }
        return null;
    }

    private static class SingletonHolder {
        private static final CacheManager INSTANCE = new CacheManager();
    }
}
