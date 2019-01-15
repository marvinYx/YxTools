package com.marvin.tools;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
public class ThreadUtils {

    private static Handler sHandler;
    private static ThreadPoolExecutor sIOExecutor;
    private static ThreadPoolExecutor sComputeExecutor;

    static {
        sHandler = new Handler(Looper.getMainLooper());
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        sIOExecutor = new ThreadPoolExecutor(corePoolSize + 1, corePoolSize * 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        sComputeExecutor = new ThreadPoolExecutor(corePoolSize * 2, corePoolSize * 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static boolean isMainProcess(@Nullable Context context) {
        if (context == null) {
            return false;
        }
        return context.getApplicationContext().getPackageName().equals(getCurrentProcessName(context));
    }

    private static String getCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                processName = process.processName;
            }
        }
        return processName;
    }

    public static Handler Main() {
        return sHandler;
    }

    public static void Main(Runnable runnable) {
        Main(runnable, 0);
    }

    public static void Main(Runnable runnable, long delayedTime) {
        sHandler.postDelayed(runnable, delayedTime);
    }

    public static ThreadPoolExecutor compute() {
        return sComputeExecutor;
    }

    public static ThreadPoolExecutor IO() {
        return sIOExecutor;
    }

}
