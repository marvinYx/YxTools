package com.marvin.tools;

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
public final class TransUtils {
    private TransUtils() {
    }

    public Integer toInteger(int i) {
        return Integer.valueOf(i);
    }

    public Integer toInteger(String s) {
        return Integer.valueOf(s);
    }

    public Integer toInteger(String s, int radix) {
        return Integer.valueOf(s, radix);
    }

    public String toString(Object obj) {
        return String.valueOf(obj);
    }

    public String toString(char[] data) {
        return String.valueOf(data);
    }

    public String toString(boolean b) {
        return String.valueOf(b);
    }

    public String toString(char c) {
        return String.valueOf(c);
    }

    public String toString(int i) {
        return String.valueOf(i);
    }

    public String toString(long l) {
        return String.valueOf(l);
    }

    public String toString(float f) {
        return String.valueOf(f);
    }

    public String toString(double d) {
        return String.valueOf(d);
    }

}
