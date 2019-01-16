package com.marvin.jetpack;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

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
 * Created by marvin on 2018/12/9
 */
public class DataModel<T> {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int EMPTY = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({SUCCESS, ERROR, EMPTY})
    public @interface Status {
    }

    private int statusCode;
    private String message;
    private T data;

    private DataModel(Builder<T> builder) {
        setStatusCode(builder.statusCode);
        setMessage(builder.message);
        setData(builder.data);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(@Status int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static String status2String(@Status int statusCode) {
        String statusStr = null;
        if (statusCode == SUCCESS) {
            statusStr = "SUCCESS";
        } else if (statusCode == ERROR) {
            statusStr = "ERROR";
        } else if (statusCode == EMPTY) {
            statusStr = "EMPTY";
        }
        return statusStr;
    }

    public static final class Builder<T> {
        private int statusCode;
        private String message;
        private T data;

        public Builder() {
        }

        public Builder<T> statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public DataModel<T> build() {
            return new DataModel<>(this);
        }
    }
}
