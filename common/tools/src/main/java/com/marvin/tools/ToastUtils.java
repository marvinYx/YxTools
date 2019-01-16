package com.marvin.tools;

import android.app.Application;
import android.content.Context;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.widget.Toast;

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
public final class ToastUtils {

    private static Toast sToast;
    private static Context sContext;

    private static boolean sReplaced = false;

    private ToastUtils() {

    }

    public static void init(@NonNull Application context) {
        init(context, false);
    }

    public static void init(@NonNull Application context, boolean replaced) {
        sContext = context;
        sToast = Toast.makeText(sContext, "", Toast.LENGTH_SHORT);
        sReplaced = replaced;
    }

    @MainThread
    public static void show(@NonNull String str) {
        show(str, 0);
    }

    @MainThread
    public static void show(@NonNull String str, int Duration) {
        CheckUtils.requireNonNull(sContext, "init() must be Called!");
        sToast = sReplaced ? sToast : Toast.makeText(sContext, "", Duration);
        sToast.setText(str);
        sToast.setDuration(Duration);
        sToast.show();
    }

    public static void cancel() {
        CheckUtils.requireNonNull(sToast, "init() must be Called!");
        sToast.cancel();
    }
}
