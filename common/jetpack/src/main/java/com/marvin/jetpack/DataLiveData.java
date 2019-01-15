package com.marvin.jetpack;

import android.arch.lifecycle.MediatorLiveData;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.marvin.tools.ThreadUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
public class DataLiveData<T> extends MediatorLiveData<DataModel<T>> {
    private List<StatusListener> mStatusListener;
    private Function<T, DataModel<T>> dataParseFunction;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActive() {
        super.onActive();
        if (mStatusListener != null && mStatusListener.size() > 0) {
            mStatusListener.forEach(StatusListener::onActive);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onInactive() {
        super.onInactive();
        if (mStatusListener != null && mStatusListener.size() > 0) {
            mStatusListener.forEach(StatusListener::onInactive);
        }
    }

    public void success(@NonNull T data) {
        putValue(DataModel.SUCCESS, "success", data);
    }

    public void error(@NonNull String msg) {
        putValue(DataModel.ERROR, msg, null);
    }

    public void empty(@NonNull String msg) {
        putValue(DataModel.EMPTY, TextUtils.isEmpty(msg) ? "data is empty!" : msg, null);
    }

    private void putValue(@DataModel.Status int statusCode, @NonNull String msg, @Nullable T data) {
        put(getDataModel(statusCode, msg, data));
    }

    private DataModel<T> getDataModel(@DataModel.Status int statusCode, @NonNull String msg, @Nullable T data) {
        return new DataModel.Builder<T>()
                .statusCode(statusCode)
                .message(msg)
                .data(data)
                .build();
    }

    @Nullable
    public Function<T, DataModel<T>> getDataParseFunction() {
        return dataParseFunction;
    }

    public void setDataParseFunction(@NonNull Function<T, DataModel<T>> dataParseFunction) {
        this.dataParseFunction = dataParseFunction;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setValueWithFunc(@Nullable T value, @Nullable Function<T, DataModel<T>> tempFunction) {
        if (tempFunction == null && dataParseFunction == null) {
            throw new UnsupportedOperationException("Function must be initialized before used");
        }
        DataModel<T> dataModel;
        dataModel = dataParseFunction != null ? dataParseFunction.apply(value) : null;
        dataModel = tempFunction != null ? tempFunction.apply(value) : dataModel;
        put(dataModel);
    }

    private void put(DataModel<T> dataModel) {
        if (ThreadUtils.isMainThread()) {
            setValue(dataModel);
        } else {
            postValue(dataModel);
        }
    }

    public void addStatusListener(@NonNull StatusListener statusListener) {
        if (mStatusListener == null) {
            mStatusListener = new ArrayList<>();
        }
        mStatusListener.add(statusListener);
    }

    public interface StatusListener {
        /**
         * 活跃状态 0 -> N
         */
        void onActive();

        /**
         * 活跃状态 N -> 0
         */
        void onInactive();
    }

}
