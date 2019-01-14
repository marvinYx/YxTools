package com.marvin.tools;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Supplier;

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
 * Created by marvin on 2018/12/20
 */
public final class CheckUtils {
    private CheckUtils() {
        TextUtils textUtils;
    }

    //============number===================


    //============TextUtils===================
    public static boolean equals(CharSequence a, CharSequence b) {
        return TextUtils.equals(a, b);
    }

    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static boolean isDigitsOnly(CharSequence str) {
        return TextUtils.isDigitsOnly(str);
    }

    //=============Objects===================

    public static boolean equals(Object a, Object b) {
        return Objects.equals(a, b);
    }

    public static boolean deepEquals(Object a, Object b) {
        return Objects.deepEquals(a, b);
    }

    public static String toString(Object o) {
        return Objects.toString(o);
    }

    public static String toString(Object o, String nullDefault) {
        return Objects.toString(o, nullDefault);
    }

    public static <T> int compare(T a, T b, Comparator<? super T> c) {
        return Objects.compare(a, b, c);
    }

    public static <T> T requireNonNull(T obj) {
        return Objects.requireNonNull(obj);
    }

    public static <T> T requireNonNull(T obj, String message) {
        return Objects.requireNonNull(obj, message);
    }

    @SuppressLint("NewApi")
    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }

    @SuppressLint("NewApi")
    public static boolean nonNull(Object obj) {
        return Objects.nonNull(obj);
    }

    @SuppressLint("NewApi")
    public static <T> T requireNonNull(T obj, Supplier<String> messageSupplier) {
        return Objects.requireNonNull(obj, messageSupplier);
    }

    //============is Legitimate===================
    public static String requireNonNullOrEmpty(String s) {
        if (s == null || s.length() <= 0) {
            throw new IllegalArgumentException("argument is empty!");
        }
        return s;
    }
}
